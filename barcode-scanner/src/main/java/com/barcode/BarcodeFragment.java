package com.barcode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.R;

public class BarcodeFragment extends Fragment implements BarcodeReader.BarcodeReaderListener {
    private static final String TAG = BarcodeFragment.class.getSimpleName();
    private BarcodeReader barcodeReader;
    private BarcodeDetector barcodeDetector;

    public BarcodeFragment() {

    }

    public static BarcodeFragment getInstance() {
        BarcodeFragment barcodeFragment = new BarcodeFragment();
        Bundle args = new Bundle();
        barcodeFragment.setArguments(args);
        return barcodeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);
        barcodeReader = (BarcodeReader) getChildFragmentManager().findFragmentById(R.id.barcode_fragment);
        barcodeReader.setListener(this);
        return view;
    }

    @Override
    public void onScanned(final Barcode barcode) {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        barcodeReader.playBeep();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show();
                if (barcodeDetector != null) {
                    barcodeDetector.onBarcodeDetect(barcode.displayValue);
                }
            }
        });
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Log.e(TAG, "onScannedMultiple: " + barcodes.size());
        StringBuilder codes = new StringBuilder();
        for (Barcode barcode : barcodes) {
            codes.append(barcode.displayValue).append(", ");
        }
        final String finalCodes = codes.toString();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Camera Permission Denied!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setBarcodeDetector(BarcodeDetector barcodeDetector) {
        this.barcodeDetector = barcodeDetector;
    }


    public interface BarcodeDetector {
        void onBarcodeDetect(String barcode);
    }


}
