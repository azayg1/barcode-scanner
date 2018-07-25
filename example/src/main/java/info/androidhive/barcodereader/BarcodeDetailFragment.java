package info.androidhive.barcodereader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BarcodeDetailFragment extends BottomSheetDialogFragment {


    public static final String KEY_BARCODE = "key_barcode";
    private TextView barCode;

    public static BarcodeDetailFragment newInstance(String barcode) {
        BarcodeDetailFragment f = new BarcodeDetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY_BARCODE, barcode);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.barcode_fragment_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barCode = view.findViewById(R.id.tvBraCode);
        barCode.setText(getArguments().getString(KEY_BARCODE));
    }
}
