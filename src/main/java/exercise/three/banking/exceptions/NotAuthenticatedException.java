package exercise.three.banking.exceptions;

public class NotAuthenticatedException extends Exception {
    public NotAuthenticatedException() {
        super("Client Not Authenticated");
    }
}
