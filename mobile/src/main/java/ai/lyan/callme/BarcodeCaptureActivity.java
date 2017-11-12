package ai.lyan.callme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.CommonStatusCodes;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by lyan on 12.11.17.
 */

public class BarcodeCaptureActivity extends AppCompatActivity
implements BarcodeReader.{
    private static final String LOG_TAG = BarcodeCaptureActivity.class.toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZXingScannerView zXingScannerView = new ZXingScannerView(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                }
            }
        }
    }
}
