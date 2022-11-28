package ch.fhnw.timechamps.exception;

/**
 * @author Lukas Kipfer
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
