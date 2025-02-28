/**
 * Exception thrown when attempting to pop from or access the top of an empty stack.
 */
public class StackUnderflowException extends Exception {
    public StackUnderflowException(String errormessage) {
        super(errormessage);
    }
}