import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
public class MyQueueTest {
    private MyQueue<String> stringQ;
    private MyQueue<Double> doubleQ;
    private String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    private ArrayList<String> fillList = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception 
    {
        stringQ = new MyQueue<>(5);
        stringQ.enqueue(a);
        stringQ.enqueue(b);
        stringQ.enqueue(c);

        doubleQ = new MyQueue<>(5);
        doubleQ.enqueue(1.0);
        doubleQ.enqueue(2.0);
        doubleQ.enqueue(3.0);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringQ = null;
        doubleQ = null;
    }

    @Test
    public void testIsEmpty() 
    {
		assertEquals(false,stringQ.isEmpty());
		try {
			stringQ.dequeue();
			stringQ.dequeue();
			stringQ.dequeue();
		} catch (QueueUnderflowException e) {
			
			e.printStackTrace();
		}
		assertEquals(true, stringQ.isEmpty());
	}
    
    
    @Test
    public void testDequeue() 
    {
        //Checks if its empty
        assertAll(
            () -> assertEquals(a, stringQ.dequeue()),
            () -> assertEquals(b, stringQ.dequeue()),
            () -> assertEquals(c, stringQ.dequeue())
        );
        assertThrows(QueueUnderflowException.class, () -> stringQ.dequeue());
    }

    @Test
    public void testDequeueStudent() 
    {
        //studen test on the dequeue
        assertEquals("1.02.03.0", doubleQ.toString());
        assertDoesNotThrow(() -> doubleQ.enqueue(4.0));
        assertEquals("1.02.03.04.0", doubleQ.toString());
        assertDoesNotThrow(() -> doubleQ.dequeue());
        assertEquals("2.03.04.0", doubleQ.toString());
    }

    @Test
    public void testSize() 
    {
        //sees if the size changed
        assertEquals(3, stringQ.size());
        assertDoesNotThrow(() -> stringQ.enqueue(d));
        assertEquals(4, stringQ.size());
        assertDoesNotThrow(() -> {
            stringQ.dequeue();
            stringQ.dequeue();
        });
        assertEquals(2, stringQ.size());
    }
    @Test
    public void testEnqueue()
    {
        //overflow and test eqneue
        assertEquals(3, stringQ.size());
        assertDoesNotThrow(() -> assertTrue(stringQ.enqueue(d)));
        assertEquals(4, stringQ.size());
        assertDoesNotThrow(() -> assertTrue(stringQ.enqueue(e)));
        assertEquals(5, stringQ.size());
        assertThrows(QueueOverflowException.class, () -> stringQ.enqueue(f));
    }
    @Test
    public void testEnqueueStudent()
    {
        //Enqeueue test 
        assertEquals("1.02.03.0", doubleQ.toString());
        assertDoesNotThrow(() -> doubleQ.enqueue(4.0));
        assertEquals("1.02.03.04.0", doubleQ.toString());
        assertDoesNotThrow(() -> doubleQ.dequeue());
        assertEquals("2.03.04.0", doubleQ.toString());
    }

    @Test
    public void testIsFull() 
    {
        //IsFull test with full queue
        assertFalse(stringQ.isFull());
        assertDoesNotThrow(() -> {
            stringQ.enqueue(d);
            stringQ.enqueue(e);
        });
        assertTrue(stringQ.isFull());
    }

    @Test
    public void testToString()
    {
        //toString behavio test
        assertEquals("abc", stringQ.toString());
        assertDoesNotThrow(() -> stringQ.enqueue(d));
        assertEquals("abcd", stringQ.toString());
        assertDoesNotThrow(() -> stringQ.enqueue(e));
        assertEquals("abcde", stringQ.toString());
    }

    @Test
    public void testToStringStudent() 
    {
        //double queue toString test
        assertEquals("1.02.03.0", doubleQ.toString());
        assertDoesNotThrow(() -> doubleQ.enqueue(4.0));
        assertEquals("1.02.03.04.0", doubleQ.toString());
        assertDoesNotThrow(() -> doubleQ.dequeue());
        assertEquals("2.03.04.0", doubleQ.toString());
    }

    @Test
    public void testToStringDelimiter() 
    {
        //tests toString
        assertEquals("a%b%c", stringQ.toString("%"));
        assertDoesNotThrow(() -> stringQ.enqueue(d));
        assertEquals("a&b&c&d", stringQ.toString("&"));
        assertDoesNotThrow(() -> stringQ.enqueue(e));
        assertEquals("a/b/c/d/e", stringQ.toString("/"));
    }
    @Test
    public void testFill() 
    {
        //tests with list
        fillList.add("apple");
        fillList.add("banana");
        fillList.add("carrot");
        stringQ = new MyQueue<>(5);
        stringQ.fill(fillList);
        assertEquals(3, stringQ.size());
        assertAll(
            () -> assertEquals("apple", stringQ.dequeue()),
            () -> assertEquals("banana", stringQ.dequeue()),
            () -> assertEquals("carrot", stringQ.dequeue())
        );
    }
}
