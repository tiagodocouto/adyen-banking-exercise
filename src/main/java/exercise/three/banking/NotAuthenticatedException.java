package exercise.three.banking;

public class NotAuthenticatedException extends Exception {
    public NotAuthenticatedException() {
        super("Client Not Authenticated");
    }
}
