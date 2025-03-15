import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//junit test for BasicDoubleLinkedList
public class BasicDoubleLinkedListTest_STUDENT 
{
    BasicDoubleLinkedList<String> testStr;
    BasicDoubleLinkedList<Integer> intTest;

    
    
    @Before
    public void setUp() throws Exception 
    {
        testStr =new BasicDoubleLinkedList<>();
        intTest =new BasicDoubleLinkedList<>();
        testStr.addToEnd("Abc");
        testStr.addToEnd("name");
        testStr.addToFront("testing");
        intTest.addToEnd(10);
        intTest.addToEnd(20);
        intTest.addToFront(5);
    }

    @After
    public void tearDown() throws Exception 
    {
    	
        testStr = null;
        intTest = null;
    }

    @Test
    public void testGetSize() 
    {
        assertEquals(3, testStr.getSize());
        
        assertEquals(3, intTest.getSize());
    }

    @Test
    public void testGetFirst() 
    {
        assertEquals("testing", testStr.getFirst());
        assertEquals(Integer.valueOf(5), intTest.getFirst());
    }

    @Test
    public void testGetLast() 
    {
        assertEquals("name", testStr.getLast());
        assertEquals(Integer.valueOf(20), intTest.getLast());
    }

    @Test
    public void testRetrieveFirstElement() 
    {
        assertEquals("testing", testStr.retrieveFirstElement());
        
        assertEquals("Abc",testStr.getFirst());
        assertEquals(2, testStr.getSize());
        
        assertEquals(Integer.valueOf(5),intTest.retrieveFirstElement());
        assertEquals(Integer.valueOf(10), intTest.getFirst());
        
        assertEquals(2,intTest.getSize());
    }

    @Test
    public void testRetrieveLastElement() 
    {
        assertEquals("name",testStr.retrieveLastElement());
        assertEquals("Abc", testStr.getLast());
        assertEquals(2, testStr.getSize());
        
        assertEquals(Integer.valueOf(20), intTest.retrieveLastElement());
        assertEquals(Integer.valueOf(10),intTest.getLast());
        assertEquals(2, intTest.getSize());
    }

    @Test
    public void testIterator() 
    {
        ListIterator<String> iter =testStr.iterator();
        assertTrue(iter.hasNext());
        assertEquals("testing", iter.next());
        assertEquals("Abc", iter.next());
        assertEquals("name",iter.next());
        assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNoSuchElementException() 
    {
        ListIterator<String> iter =testStr.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next(); 
    }

    @Test
    public void testRemove() 
    {
        Comparator<String> comp =String::compareTo;
        assertTrue(testStr.remove("Abc",comp));
        assertFalse(testStr.remove("ordering", comp));
        assertEquals(2, testStr.getSize());
    }

    @Test
    public void testToArrayList()
    {
        ArrayList<String> list =testStr.toArrayList();
        assertEquals(3, list.size());
        assertEquals("testing", list.get(0));
        assertEquals("Abc", list.get(1));
        assertEquals("name", list.get(2));
    }
}
