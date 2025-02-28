/**
 * Exception thrown when attempting to push an element onto a full stack.
 */
public class StackOverflowException extends Exception {
    public StackOverflowException(String errormessage) {
        super(errormessage);
    }
}