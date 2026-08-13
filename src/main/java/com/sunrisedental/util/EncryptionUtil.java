package com.sunrisedental.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";

    private static final String SECRET_KEY = "1234567890123456";

    public static String encrypt(String text) throws Exception {

        SecretKeySpec key =
                new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted =
                cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedText) throws Exception {

        SecretKeySpec key =
                new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decrypted =
                cipher.doFinal(
                    Base64.getDecoder().decode(encryptedText)
                );

        return new String(decrypted);
    }
}