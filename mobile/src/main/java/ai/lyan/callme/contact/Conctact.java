package ai.lyan.callme.contact;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.provider.ContactsContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyan on 11.11.17.
 */

public class Conctact {

    private Conctact(){
        contactMap = new HashMap<>();
    }

    public Conctact(String fromJson) {
        this();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();

        Gson gson = new Gson();
        contactMap = gson.fromJson(fromJson, type);
    }

    private Map<String, String> contactMap;

    public void setName(String firstName) {
        contactMap.put(ContactsContract.Intents.Insert.NAME, firstName);
    }

    public void setOrganization(String organization) {
        contactMap.put(ContactsContract.Intents.Insert.COMPANY, organization);
    }

    public void setEmail(String email) {
        contactMap.put(ContactsContract.Intents.Insert.EMAIL, email);
    }

    public void setPhoneNumber(String phoneNumber) {
        contactMap.put(ContactsContract.Intents.Insert.PHONE, phoneNumber);
    }

    public void acceptIntent(Intent intent) {
        for (String contactType : contactMap.keySet()) {
            intent.putExtra(contactType, contactMap.get(contactType));
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(contactMap);
    }

    public static final class Builder {

        private final Conctact contact;

        private Builder() {
            this.contact = new Conctact();
        }

        public static final Builder init() {
            return new Builder();
        }

        public Builder withName(String firstName) {
            contact.setName(firstName);
            return this;
        }

        public Builder withEmail(String email) {
            contact.setEmail(email);
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            contact.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder withOrganization(String organization) {
            contact.setOrganization(organization);
            return this;
        }

        public Conctact build() {
            return contact;
        }
    }
}
