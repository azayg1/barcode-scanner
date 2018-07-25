package info.androidhive.barcodereader;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.barcode.BarcodeFragment;

public class BarcodeActivity extends AppCompatActivity implements BarcodeFragment.BarcodeDetector {

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
        if (!TextUtils.isEmpty(barcode)) {
            Intent data = new Intent();
            data.putExtra(MainActivity.KEY_BARCODE, barcode);
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
