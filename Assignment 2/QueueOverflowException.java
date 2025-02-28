/**
 * Exception thrown when attempting to enqueue an element into a full queue.
 */
public class QueueOverflowException extends Exception {
    public QueueOverflowException(String errormessage) {
        super(errormessage);
    }
}