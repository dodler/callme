package ai.lyan.callme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

import ai.lyan.callme.contact.Conctact;

import static java.lang.Math.min;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final float SCALE_FACTOR = 0.9f;
    private ImageView qrImageView;
    private Conctact activeContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startConfigure();

        qrImageView = (ImageView) findViewById(R.id.qr_img_view);
        updateContact();

        Button buttonAccept = (Button) findViewById(R.id.btn_accept);
        buttonAccept.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AcceptActivity.class);
            startActivity(intent);
        });

    }

    private void startConfigure() {
        Intent intent = new Intent(this, ConfigureShareActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) { // fixme introduce code constant
            String name = data.getStringExtra(ConfigureShareActivity.NAME);
            String company = data.getStringExtra(ConfigureShareActivity.COMPANY);
            String email = data.getStringExtra(ConfigureShareActivity.EMAIL);
            String phone = data.getStringExtra(ConfigureShareActivity.PHONE);

            activeContact = Conctact.Builder.init().withName(name)
                    .withPhoneNumber(phone)
                    .withEmail(email)
                    .withOrganization(company).build();

            updateContact();
        }
    }

    private void updateContact() {
        if (activeContact == null) {
            return;
        }
        Bitmap img = generateQRBitMap(activeContact.toString());
        if (img != null) {
            qrImageView.setImageBitmap(img);
        }
    }


    private Bitmap generateQRBitMap(final String content) {

        Log.d("tag", "generating image");

        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();

        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 512, 512, hints);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            Point size = getDisplaySize();

            int dstWidth = Math.round(width * SCALE_FACTOR * (size.x / width));
            int dstHeight = Math.round(height * SCALE_FACTOR * (size.y / height));

            int res = min(dstWidth, dstHeight);

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp, res, res, true);

            return scaledBitmap;
        } catch (WriterException e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(getApplicationContext(), "Failed to update QR code for contact", Toast.LENGTH_LONG).show();
        }

        return null;
    }

    private Point getDisplaySize() {
        Point point = new Point();
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        defaultDisplay.getSize(point);
        return point;
    }
}
