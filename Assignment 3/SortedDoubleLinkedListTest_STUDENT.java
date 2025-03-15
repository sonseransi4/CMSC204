import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Student testfor SortedDoubleLinkedList
public class SortedDoubleLinkedListTest_STUDENT 
{
    SortedDoubleLinkedList<String> strList;
    
    SortedDoubleLinkedList<Integer> intList;
    StringComparator compStr;
    
    IntegerComparator intComp;

    @Before
    public void setUp() throws Exception
    {
        compStr = new StringComparator();
        intComp = new IntegerComparator();
        
        strList = new SortedDoubleLinkedList<>(compStr);
        intList = new SortedDoubleLinkedList<>(intComp);
    }

    @After
    public void tearDown() throws Exception 
    {
        strList = null;
        intList = null;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToEnd() 
    {
        strList.addToEnd("Test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToFront()
    {
        strList.addToFront("DoubleLinked");
    }

    @Test
    public void testAddAndSort() 
    {
        intList.add(50);
        intList.add(10);
        
        intList.add(30);
        intList.add(20);
        
        assertEquals(Integer.valueOf(10), intList.getFirst());
        assertEquals(Integer.valueOf(50), intList.getLast());
    }

    @Test
    public void testIterator() 
    {
    	
        strList.add("Dog");
        strList.add("Apple");
        strList.add("Book");
        ListIterator<String> iterator = strList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Apple", iterator.next());
        assertEquals("Book", iterator.next());
        assertEquals("Dog", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNoSuchElementException() 
    {
        intList.add(5);
        intList.add(15);
        intList.add(25);
        ListIterator<Integer> iterator = intList.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next(); 
        
        
    }

    @Test
    public void testRemoveElement()
    {
        strList.add("Doggy");
        strList.add("Bluey");
        strList.add("Apple");
        
        assertTrue(strList.remove("Bluey", compStr));
        
        assertFalse(strList.remove("Pizza", compStr));
    }

    private class StringComparator implements Comparator<String> 
    {
    	
        @Override
        public int compare(String test1, String test2) {
            return test1.compareTo(test2);
            
            
        }
    }
    
    private class IntegerComparator implements Comparator<Integer> 
    
    {
        @Override
        public int compare(Integer test1, Integer test2)
        {
            return test1.compareTo(test2);
        }
    }
}
