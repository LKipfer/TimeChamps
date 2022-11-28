package ch.fhnw.timechamps.exception;

/**
 * @author Lukas Kipfer
 */

public class TimestampNotFoundException extends RuntimeException {
    public TimestampNotFoundException(String message) {
        super(message);
    }
}
