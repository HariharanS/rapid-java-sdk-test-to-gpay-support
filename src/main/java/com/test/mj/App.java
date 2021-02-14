package com.test.mj;

import java.io.*;
import java.nio.charset.*;
import org.apache.commons.io.*;
import com.google.crypto.tink.apps.paymentmethodtoken.GooglePaymentsPublicKeysManager;
import com.google.crypto.tink.apps.paymentmethodtoken.PaymentMethodTokenRecipient;


public class App {
    public static void main(String[] args) {

        // need replace a new PrivateKey here
        String base64PrivateKey = "";

        try {

            // replace this path with actual file path
            File file = new File("payload1.txt");
            String encryptedMessage = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            String decryptedMessage = new PaymentMethodTokenRecipient.Builder()
                    .fetchSenderVerifyingKeysWith(GooglePaymentsPublicKeysManager.INSTANCE_TEST)
                    .recipientId("gateway:eway")
                    .protocolVersion("ECv2").addRecipientPrivateKey(base64PrivateKey).build().unseal(encryptedMessage);

            System.out.println(decryptedMessage);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}