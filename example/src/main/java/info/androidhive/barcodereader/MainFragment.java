package info.androidhive.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static info.androidhive.barcodereader.MainActivity.KEY_BARCODE;

public class MainFragment extends BaseFragment implements View.OnClickListener {
    public static final int BARCODE_SCAN = 901;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.ivTop).setOnClickListener(this);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmen_main, container, false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTop:
                startActivityForResult(new Intent(getActivity(), BarcodeActivity.class), BARCODE_SCAN);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_SCAN && resultCode == Activity.RESULT_OK) {
            final String barcode = data.getStringExtra(KEY_BARCODE);
            new Handler(Looper.myLooper()).post(new Runnable() {
                @Override
                public void run() {
                    final BottomSheetDialogFragment myBottomSheet = BarcodeDetailFragment.newInstance(barcode);
                    myBottomSheet.show(getActivity().getSupportFragmentManager(), myBottomSheet.getTag());
                }
            });

        }
    }
}
