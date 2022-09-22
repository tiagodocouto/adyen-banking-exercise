package exercise.three.banking.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

public class VeryBasicAuth implements Auth {
    private static final String MD5 = "MD5";

    private static VeryBasicAuth instance;

    private VeryBasicAuth() {
    }

    public static VeryBasicAuth getInstance() {
        if (instance == null)
            instance = new VeryBasicAuth();
        return instance;
    }

    @Override
    public String hash(final String password) {
        try {
            final var digest = MessageDigest.getInstance(MD5).digest(password.getBytes());
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
