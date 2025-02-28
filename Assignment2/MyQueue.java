import java.util.ArrayList;

//Queue implementation
//@author Sonse Ransibrahmanakul
public class MyQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private int headIndex, tailIndex, currentSizing, maximCapac = 0;
    /**
     * Constructor to initialize the queue with a maximum capacity.
     * @param maxCapacity the maximum number of elements in the queue
     */
    public MyQueue(int maxCapacity) {
        this.maximCapac = maxCapacity;
        queue = (T[]) new Object[maxCapacity];
    }
    
    /**
     * Determines if Queue is empty
     * @return true if Queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        if (currentSizing == 0) 
        {
            return true;
            
        } else 
        {
            return false;
        }
    }

    /**
     * Determines if the Queue is Full
     * @return true if Queue is full, false if not
     */
    @Override
    public boolean isFull() 
    {
        if (currentSizing == maximCapac)
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Deletes and returns the element at the front of the Queue
     * @return the element at the front of the Queue
     * @throws QueueUnderflowException if queue is empty
     */
    @Override
    public T dequeue() throws QueueUnderflowException 
    {
        if (!isEmpty()) 
        {
            T removedElement = queue[headIndex];
            headIndex = (headIndex + 1) % maximCapac;
            currentSizing--;
            return removedElement;
        } else {
            throw new QueueUnderflowException("Queue is empty");
        }
    }

    /**
     * Returns number of elements in the Queue
     * @return the number of elements in the Queue
     */
    @Override
    public int size() {
        return currentSizing;
    }

    
    
    /**
     * Adds an element to the end of the Queue
     * @param e the element to add to the end of the Queue
     * @return true if the add was successful
     * @throws QueueOverflowException if queue is full
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) 
        {
            throw new QueueOverflowException("Queue is full");
        } else 
        {
            int indexToInsert = tailIndex;
            queue[indexToInsert] = e;
            tailIndex = (indexToInsert + 1) % maximCapac;
            currentSizing++;
            return true;
        }
    }

    /**
     * Returns the string representation of the elements in the Queue, 
     * the beginning of the string is the front of the queue
     * @return string representation of the Queue with elements
     */
    @Override
    public String toString() 
    {
        return toString("");
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     * @return string representation of the Queue with elements separated with the delimiter
     */
    @Override
    public String toString(String delimiter) 
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < currentSizing; i++) {
            result.append(queue[(headIndex + i) % maximCapac]);
            if (i < currentSizing - 1) 
            {
                result.append(delimiter);
            }
        }
        return result.toString();
    }
    /**
     * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
     * is the first element in the Queue
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
     * list reference within your Queue, you will be allowing direct access to the data of
     * your Queue causing a possible security breech.
     * @param list elements to be added to the Queue
     * @throws QueueOverflowException if queue is full
     */
    @Override
    public void fill(ArrayList<T> list) 
    {
        ArrayList<T> copyList = new ArrayList<>(list);
        for (T item : copyList) 
        {
            try {
                enqueue(item);
            } catch (QueueOverflowException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
}

/**
 * Exception thrown when attempting to dequeue from an empty queue.
 */
class QueueUnderflowException extends Exception
{
    public QueueUnderflowException(String errormessage) 
    {
        super(errormessage);
    }
}
/**
 * Exception thrown when attempting to enqueue an element into a full queue.
 */
class QueueOverflowException extends Exception {
    public QueueOverflowException(String errormessage) {
        super(errormessage);
    }
}
