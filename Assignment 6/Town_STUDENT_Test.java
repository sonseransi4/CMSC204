import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Town_STUDENT_Test {

    private Town olney1;
    private Town olney2;
    private Town rockville;

    @BeforeEach
    public void setUp() {
        olney1 = new Town("Olney");
        olney2 = new Town("Olney");
        rockville = new Town("Rockville");
    }

    @Test
    public void getNameTest() {
        assertEquals("Olney", olney1.getName());
        assertEquals("Rockville", rockville.getName());
    }

    @Test
    public void sameNameTesting() {
        assertEquals(0, olney1.compareTo(olney2), "same name goes to 0");
    }


    @Test
    public void hashTest() {
        assertEquals(olney1.hashCode(), olney2.hashCode(), "same hash codes");
        assertNotEquals(olney1.hashCode(), rockville.hashCode(), "different hash chodes");
    }
    
    @Test
    public void sameNameTest() {
        assertTrue(olney1.equals(olney2), "same name is equal");
    }

    
    @Test
    public void difNameTest() {
        assertFalse(olney1.equals(rockville), "Different names and so not equal");
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(null, olney1, "town is not equal to null");
    }

    @Test
    public void toStringTest() {
        assertEquals("Olney", olney1.toString());
    }
    
    
    
}
