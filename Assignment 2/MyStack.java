import java.util.ArrayList;
import java.util.Arrays;

//Stack Implementation
//@author Sonse Ransibrahmanakul
public class MyStack<T> implements StackInterface<T> 
{
    private int topIndex, maxCapacity;
    private T[] stack;
    
    /**
     * Constructor to initialize the stack with a specified capacity.
     * @param maxCapacity the maximum number of elements in the stack
     */
    public MyStack(int maxCapacity) 
    {
        this.maxCapacity = maxCapacity;
        stack = (T[]) new Object[maxCapacity];
        topIndex = -1;
    }
    
    /**
     * Determines if Stack is empty
     * @return true if Stack is empty, false if not
     */
    @Override
    public boolean isEmpty() 
    {
        if (topIndex == -1)
            return true;
        else 
        {
            return false;
        }
    }
    
    /**
     * Determines if Stack is full
     * @return true if Stack is full, false if not
     */
    @Override
    public boolean isFull() {
        if (topIndex == maxCapacity - 1) 
        
            return true;
        else 
            return false;
    }
    
    /**
     * Deletes and returns the element at the top of the Stack
     * @return the element at the top of the Stack
     * @throws StackUnderflowException if stack is empty
     */
    @Override
    public T pop() throws StackUnderflowException 
    {
        if (!isEmpty()) 
        {
            T removedElement = stack[topIndex];
            topIndex = topIndex - 1;
            return removedElement;
        } 
        else
        {
            throw new StackUnderflowException("Stack is empty");
        }
    }
    
    /**
     * Returns the element at the top of the Stack, does not pop it off the Stack
     * @return the element at the top of the Stack
     * @throws StackUnderflowException if stack is empty
     */
    @Override
    public T top() throws StackUnderflowException
    {
        if (!isEmpty())
        {
            return stack[topIndex];
        }
        else 
        {
            throw new StackUnderflowException("Stack is empty");
        }
    }
    
    /**
     * Number of elements in the Stack
     * @return the number of elements in the Stack
     */
    @Override
    public int size() {
        return topIndex + 1;
    }
    
    /**
     * Adds an element to the top of the Stack
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     * @throws StackOverflowException if stack is full
     */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) 
        {
            throw new StackOverflowException("Stack is full");
        } 
        else 
        {
            topIndex = topIndex + 1;
            stack[topIndex] = e;
            return true;
        }
    }
    /**
     * Returns the elements of the Stack in a strin from bottom to top, the beginning 
     * of the String is the bottom of the stack
     * @return an string which represent the Objects in the Stack from bottom to top
     */
    @Override
    public String toString() 
    {
        return toString("");
    }
  
    /**
     * Returns the string representation of the elements in the Stack, the beginning of the 
     * string is the bottom of thestack
     * Place the delimiter between all elements of the Stack
     * @param delimiter the delimiter to be used between elements
     * @return string representation of the Stack from bottom to top with elements 
     * separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        StringBuilder result = new StringBuilder();
        boolean firstElement = true;
        // Iterate from the bottom (index 0) to the top of the stack
        for (int i = 0; i <= topIndex; i++) 
        {
            T element = stack[i];
            if (element != null) 
            {
                if (!firstElement) 
                {
                    result.append(delimiter);
                }
                result.append(element.toString());
                firstElement = false;
            }
        }
        return result.toString();
    }
    /**
     * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
     * is the first bottom element of the Stack
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
     * list reference within your Stack, you will be allowing direct access to the data of
     * your Stack causing a possible security breech.
     * @param list elements to be added to the Stack from bottom to top
     * @throws StackOverflowException if stack gets full
     */
    @Override
    public void fill(ArrayList<T> list) {
        ArrayList<T> copyList = new ArrayList<>(list);
        for (T item : copyList) {
            try {
                push(item);
            } catch (StackOverflowException ex) {
                ex.printStackTrace();
            }
        }
    }
}

