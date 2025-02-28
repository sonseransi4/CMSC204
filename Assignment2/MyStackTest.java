import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
public class MyStackTest {
    private MyStack<String> stringS;
    private MyStack<Double> doubleS;
    private String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    private ArrayList<String> fill = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception 
    {
        stringS = new MyStack<>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);
        doubleS = new MyStack<>(4);
        doubleS.push(3.0);
        doubleS.push(4.0);
        doubleS.push(5.0);
    }

    @AfterEach
    public void tearDown() throws Exception 
    {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() {
        //Sees if its empty
        assertFalse(stringS.isEmpty());
        try {
            stringS.pop();
            stringS.pop();
            stringS.pop();
        } 
        catch (StackUnderflowException ex)
        {
            fail("unexpected exception in testIsEmpty");
        }
        assertTrue(stringS.isEmpty());
    }

    @Test
    public void testIsFull() {
        //Sees if the test is full
        assertFalse(stringS.isFull());
        try {
            stringS.push(d);
            stringS.push(e);
        }
        catch (StackOverflowException ex) 
        {
            fail("unexpected exception in testIsFull");
        }
        assertTrue(stringS.isFull());
    }

    @Test
    public void testPop() {
        //TestsPop
        try 
        {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            assertThrows(StackUnderflowException.class, () -> stringS.pop());
        } 
        catch (StackUnderflowException ex) {
            fail("unexpected exception in testPop");
        }
    }

    @Test
    public void testPopStudent() {
        //Double stack
        assertEquals("3.0,4.0,5.0", doubleS.toString(","));
        try {
            doubleS.pop();
        } 
        catch (StackUnderflowException ex) 
        {
            fail("unexpected exception in testPopStudent");
        }
        assertEquals("3.04.0", doubleS.toString());
    }

    @Test
    public void testTop() {
        //Checks for last pushed element
        try 
        {
            assertEquals(c, stringS.top());
            stringS.push(d);
            assertEquals(d, stringS.top());
            stringS.pop();
            stringS.pop();
            assertEquals(b, stringS.top());
        } 
        catch (StackUnderflowException | StackOverflowException ex) {
            fail("unexpected exception in testTop");
        }
    }

    @Test
    public void testSize() 
    {
        //Tests if the right size
        assertEquals(3, stringS.size());
        try {
            stringS.push(d);
        } 
        catch (StackOverflowException ex)
        {
            fail("unexpected exception in testSize push");
        }
        assertEquals(4, stringS.size());
        try 
        {
            stringS.pop();
            stringS.pop();
        } 
        catch (StackUnderflowException ex)
        {
            fail("unexpected exception in testSize pop");
        }
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        //testPush example
        try 
        {
            assertEquals(3, stringS.size());
            assertTrue(stringS.push(d));
            assertEquals(4, stringS.size());
            assertTrue(stringS.push(e));
            assertEquals(5, stringS.size());
            assertThrows(StackOverflowException.class, () -> stringS.push(f));
        } 
        catch (StackOverflowException ex)
        {
            fail("unexpected exception in testPush");
        }
    }

    @Test
    public void testPushStudent() {
        //stack push functional
        assertEquals("3.0,4.0,5.0", doubleS.toString(","));
        try {
            doubleS.push(1.0);
        } catch (StackOverflowException ex) {
            fail("unexpected exception in testPushStudent");
        }
        assertEquals("3.04.05.01.0", doubleS.toString());
    }

    @Test
    public void testToString() {
        //checks toString orde
        assertEquals("abc", stringS.toString());
        try {
            stringS.push(d);
        } catch (StackOverflowException ex) {
            fail("unexpected exception in testToString push");
        }
        assertEquals("abcd", stringS.toString());
        try {
            stringS.push(e);
        } catch (StackOverflowException ex) {
            fail("unexpected exception in testToString push");
        }
        assertEquals("abcde", stringS.toString());
    }

    @Test
    public void testToStringStudent() {
        //test for double stack toString with delimite
        assertEquals("3.0,4.0,5.0", doubleS.toString(","));
        try {
            doubleS.pop();
        } catch (StackUnderflowException ex) {
            fail("unexpected exception in testToStringStudent pop");
        }
        assertEquals("3.04.0", doubleS.toString());
    }
    @Test
    public void testToStringDelimiter() {
        //sees if the toStringDelimiter works
        assertEquals("a%b%c", stringS.toString("%"));
        try 
        {
            stringS.push(d);
        } catch (StackOverflowException ex) 
        {
            fail("unexpected exception in testToStringDelimiter push");
        }
        assertEquals("a&b&c&d", stringS.toString("&"));
        try 
        {
            stringS.push(e);
        } 
        catch (StackOverflowException ex) 
        {
            fail("unexpected exception in testToStringDelimiter push");
        }
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() 
    {
       
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        stringS = new MyStack<>(5);
        stringS.fill(fill);
        assertEquals(3, stringS.size());
        try {
            assertEquals("carrot", stringS.pop());
            assertEquals("banana", stringS.pop());
            assertEquals("apple", stringS.pop());
        } catch (StackUnderflowException ex) {
            fail("unexpected exception in testFill");
        }
    }
}
