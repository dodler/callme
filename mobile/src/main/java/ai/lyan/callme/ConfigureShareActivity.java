package ai.lyan.callme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lyan on 16.11.17.
 */

public class ConfigureShareActivity extends AppCompatActivity {

    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String NAME = "name";
    public static final String COMPANY = "company";
    private EditText email;
    private EditText phone;
    private EditText name;
    private EditText company;
    private Button doneBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure_share);

        doneBtn = (Button) findViewById(R.id.done_btn);
        doneBtn.setOnClickListener((listener) -> {
                    Intent intent = fetchText();
                    finish();
                }
        );

        email = (EditText) findViewById(R.id.email_input);
        phone = (EditText) findViewById(R.id.phone_input);
        name = (EditText) findViewById(R.id.name_input);
        company = (EditText) findViewById(R.id.company_input);
    }

    private Intent fetchText() {
        Intent intent = new Intent();
        intent.putExtra(EMAIL, email.getText().toString());
        intent.putExtra(PHONE, phone.getText().toString());
        intent.putExtra(NAME, name.getText().toString());
        intent.putExtra(COMPANY, company.getText().toString());

        setResult(0, intent);
        return intent;
    }
}
