package ai.lyan.callme.contact;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lyan on 11.11.17.
 */
public class ConctactTest {

    public static final String testContact = "{\"phone\":\"12\",\"name\":\"test\"}";

    @Test
    public void testJson() {
        Conctact test = Conctact.Builder.init().withPhoneNumber("12").withName("test").build();
        String contact = test.toString();
        assertEquals(contact, testContact);
    }

    @Test
    public void testFromJson() {
        Conctact conctact = new Conctact(testContact);
        assertEquals(conctact.toString(), testContact);
    }
}