package ai.lyan.callme;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ai.lyan.callme.contact.Conctact;

/**
 * Created by lyan on 12.11.17.
 */

public class AcceptActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_activity);

        Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
        startActivityForResult(intent, 1);
    }


    private void saveContact(Conctact conctact) {
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        conctact.acceptIntent(contactIntent);

        startActivityForResult(contactIntent, 1);
    }
}
