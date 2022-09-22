package exercise.three.banking.encryption;

public interface Auth {
    String hash(final String password);

    default boolean verify(final String hash, final String attempt) {
        return hash.equals(hash(attempt));
    }
}
