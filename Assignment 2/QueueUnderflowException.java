/**
 * Exception thrown when attempting to dequeue from an empty queue.
 */
public class QueueUnderflowException extends Exception
{
    public QueueUnderflowException(String errormessage) 
    {
        super(errormessage);
    }
}