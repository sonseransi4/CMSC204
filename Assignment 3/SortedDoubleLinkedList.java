import java.util.ListIterator;
import java.util.Comparator;


public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> 
{
    private Comparator<T> comptr;

   
    public SortedDoubleLinkedList(Comparator<T> inputComp)//takes comparator and calls basicdoublelinkedlist
    
    
    {
        super();
        
        this.comptr = inputComp;
    }

    //adds an element in order, without disrupting the order
    public void add(T inputItem) 
    {
        Node<T> slot = new Node<>(inputItem);
        
        if (head == null) //checks if list is empty
        { 
            head = slot;
            ending = slot;
        } 
        else if (comptr.compare(inputItem, head.item) <= 0) //insert to front
        { 
            slot.next = head;
            head.prev = slot;
            head = slot;
        } 
        else if (comptr.compare(inputItem, ending.item) >= 0) //inserts toback
        { 
            slot.prev = ending;
            ending.next = slot;
            ending = slot;
        } 
        else //last else for the middle
        { 
            Node<T> current = head;
            while (current != null && comptr.compare(inputItem, current.item) > 0)
            {
                current = current.next; //puts it into the correct place
            }
            
            slot.next = current;
            slot.prev = current.prev;
            if (current.prev != null) //checks if prev node exists
            { 
            	
                current.prev.next = slot;
            }
            current.prev = slot;
        }
        
        
        amount++;
    }

    
    

    
    @Override
    public boolean remove(T val, Comparator<T> comp) 
    {
        return super.remove(val, comp);
    }
    
    @Override
    public void addToFront(T val) //exception
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addToEnd(T val) //exception
    {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public ListIterator<T> iterator()
    {
        return super.iterator();
    }

    
    
    
    
    
}
