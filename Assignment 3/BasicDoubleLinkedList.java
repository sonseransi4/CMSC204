import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import java.util.Comparator;

public class BasicDoubleLinkedList<T> 
{
	
    protected int amount = 0;
    
    
    protected Node<T> head, ending;


    //Generic inner class for node
    public static class Node<T> 
    {
        T item;
        
        Node<T> prev, next;

        public Node(T item) 
        {
        	
            this.item = item;
            this.prev = null;
            this.next = null;
            
            
        }
    }

    //gets the first item
    public T getFirst() 
    
    {
        if (head == null) 
        	
        {
            return null;
        } 
        else 
        {
            return head.item;
            
        }
        
        
    }

    //gets teh last item
    public T getLast() 
    {
        if (ending == null) 
        {
            return null;
        } 
        else 
        {
            return ending.item;
        }
        
        
    }
    
    public ListIterator<T> iterator() 
    {
        return new DoubleLinkedListIterator(head);
    }
    
    public T retrieveFirstElement() 
    {
        if (head == null) { 
        	
            return null;
        }
        
        T fElement = head.item;
        
        //if only one element
        if (head == ending) 
        {
            head = null;
            ending = null;
        }
        else 
        {
            head = head.next;
            head.prev = null;
        }
        
        amount--;
        
        
        return fElement;
    }

    public T retrieveLastElement()
    {
        if (ending == null) 
        {
            return null;
        }
        
        T lElement = ending.item;
        
        //if only one element
        if (head == ending) 
        {
            head = null;
            ending = null;
        } 
        else
        {
            ending = ending.prev;
            ending.next = null;
        }
        
        amount--;
        
        
        return lElement;
        
    }

    //adds a value to the front
    public void addToFront(T val) 
    {
    	
    	
        Node<T> holdVal = new Node<>(val); //temporarily holds the value
        if (head == null) 
        {
        	
        	
            head = holdVal;
            ending = holdVal;
        } 
        else 
        {
            holdVal.next = head;
            head.prev = holdVal;
            head = holdVal;
            
        }
        
        amount++;
    }

    //adds a value to the end
    public void addToEnd(T val) 
    {
    	
        Node<T> holdVal = new Node<>(val); //temporarily holds the value
        if (head == null) 
        	
        {
            head = holdVal;
            ending = holdVal;
        } 
        else
        {
            holdVal.prev = ending;
            ending.next = holdVal;
            
            ending = holdVal;
        }
        
        amount++;
    }
    
    public ArrayList<T> toArrayList()
    {
    	
        Node<T> headTemp = head;
        
        ArrayList<T> arrList = new ArrayList<>();
        
        while (headTemp != null) 
        {
        	
            arrList.add(headTemp.item);
            headTemp = headTemp.next;
        }
        return arrList;
    }

    
    //removes and returns true or false if successful or not
    public boolean remove(T value, Comparator<T> temp)
    {
    	
        Node<T> holdVal = head;

        while (holdVal != null) 
        {
            if (temp.compare(holdVal.item, value) == 0) 
            {
            	//checks if null, if not updates for the head
                if (holdVal.prev == null) 
                {
                    head = holdVal.next;
                }
                else 
                {
                    holdVal.prev.next = holdVal.next;
                }
                
                //cchechks if null if not proceeds to update for ending
                if (holdVal.next == null) 
                {
                    ending = holdVal.prev;
                }
                else {
                    holdVal.next.prev = holdVal.prev;
                }
                
                amount--;
                return true;
            }
            
            holdVal = holdVal.next;
        }
        return false;
    }

    //returns the amount
    public int getSize() 
    {
        return amount;
    }
    
    //Generic inner listiterator class
    private class DoubleLinkedListIterator implements ListIterator<T> {
        private Node<T> current;
        private Node<T> lastRetur = null;

        public DoubleLinkedListIterator(Node<T> startNode) 
        {
            this.current = startNode;
        }

        @Override
        public boolean hasNext() 
        {
        	
            if (current != null)
            {
                return true;
            } 
            else 
            {
                return false;
            }
        }

        @Override
        public T next() 
        {
        	
            if (hasNext()) 
            {
                lastRetur = current;
                T data = current.item;
                current = current.next;
                
                return data;
            } 
            else //if hasnext is not true, it throws exception
            {
                throw new NoSuchElementException();
            }
            
        }

        @Override
        public boolean hasPrevious() 
        {
        	
            if (lastRetur != null)
            {
                return true;
            } 
            else 
            {
                return false;
            }
        }
        
        @Override
        public T previous() 
        {
        	
            if (hasPrevious()) 
            {
                current = lastRetur;
                lastRetur = lastRetur.prev;
                
                return current.item;
            } 
            else //if hasprevious is false, throws exception
            {
                throw new NoSuchElementException();
            }
            
            
        }

        
        
        @Override
        public void add(T e) 
        {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public int previousIndex() 
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) 
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
        
        
        
        @Override
        public int nextIndex() 
        {
            throw new UnsupportedOperationException();
        }
     

        
    }
}
