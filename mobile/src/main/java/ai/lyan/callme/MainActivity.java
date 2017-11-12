package ai.lyan.callme;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import ai.lyan.callme.contact.Conctact;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        Button buttonShare = (Button) findViewById(R.id.btn_share);
        buttonShare.setOnClickListener((view) -> {
            Intent intent = new Intent(this, ShareActivity.class);
            Log.d(TAG, "Start");
            startActivity(intent);
        });

        Button buttonAccept = (Button) findViewById(R.id.btn_accept);
        buttonAccept.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AcceptActivity.class);
            startActivity(intent);
        });

    }
}
