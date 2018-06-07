package info.androidhive.barcodereader;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.barcode.BarcodeFragment;

public class BarcodeFragmentTestActivity extends AppCompatActivity implements BarcodeFragment.BarcodeDetector {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_fragment_test);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BarcodeFragment bf = BarcodeFragment.getInstance();
        bf.setBarcodeDetector(this);
        ft.add(R.id.container, bf);
        ft.commit();
    }

    @Override
    public void onBarcodeDetect(String barcode) {

    }
}
