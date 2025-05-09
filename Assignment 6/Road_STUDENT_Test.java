import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Road_STUDENT_Test {

    private Town olney;
    private Town rockville;
    private Town bethesda;
    private Road streetOne;
    private Road streetOneAlt;
    private Road sesameStreet;

    @BeforeEach
    public void setUp() {
        olney = new Town("Olney");
        rockville = new Town("Rockville");
        bethesda = new Town("Bethesda");

        streetOne = new Road(olney, rockville, 3, "StreetOne");
        streetOneAlt = new Road(olney, rockville, 8, "StreetOne");
        sesameStreet = new Road(olney, bethesda, 6, "Sesame Street");
    }

    @Test
    public void hashTest() {
        assertEquals(streetOne.hashCode(), streetOneAlt.hashCode());
    }

    @Test
    public void hasTownTest() {
        assertTrue(streetOne.has(olney));
        assertTrue(streetOne.has(rockville));
        assertFalse(streetOne.has(bethesda));
    }
    
    @Test
    public void testgettersandsetters() {
        assertEquals("StreetOne", streetOne.getRoadName());
        assertEquals(3, streetOne.getWeight());
        assertEquals(olney, streetOne.getFront());
        assertEquals(rockville, streetOne.getEnd());
    }


    @Test
    public void toStringtest() {
        assertEquals("StreetOne", streetOne.toString());
    }
    
    
    
}
