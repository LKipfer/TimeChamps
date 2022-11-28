package ch.fhnw.timechamps.exception;

/**
 * @author Lukas Kipfer
 * Source: https://www.youtube.com/watch?v=Gx4iBLKLVHk&t=3120s
 */
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) { //The string message refers to the String which we pass down from the EmployeeService class
        //We call the super method as it will create a message using the Constructor from the RuntimeException class
        super(message);
    }
}
