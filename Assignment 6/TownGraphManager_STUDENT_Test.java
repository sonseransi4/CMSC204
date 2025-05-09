import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
    private TownGraphManagerInterface graph;
    private String[] town;

    @Before
    public void setUp() throws Exception {
        graph = new TownGraphManager();
        town = new String[12];

        for (int i = 1; i < 12; i++) {
            town[i] = "t" + i;
            graph.addTown(town[i]);
        }

        graph.addRoad(town[1], town[2], 3, "r1");
        graph.addRoad(town[1], town[3], 5, "r2");
        graph.addRoad(town[1], town[5], 7, "r3");
        graph.addRoad(town[3], town[7], 2, "r4");
        graph.addRoad(town[3], town[8], 4, "r5");
        graph.addRoad(town[4], town[8], 6, "r6");
        graph.addRoad(town[6], town[9], 2, "r7");
        graph.addRoad(town[9], town[10], 5, "r8");
        graph.addRoad(town[8], town[10], 3, "r9");
        graph.addRoad(town[5], town[10], 6, "r10");
        graph.addRoad(town[10], town[11], 4, "r11");
        graph.addRoad(town[2], town[11], 7, "r12");
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testAddRoad() {
        ArrayList<String> roads = graph.allRoads();
        assertEquals("r1", roads.get(0));
        assertEquals("r10", roads.get(1));
        assertEquals("r11", roads.get(2));
        assertEquals("r12", roads.get(3));
        graph.addRoad(town[4], town[11], 2,"r13");
        roads = graph.allRoads();
        assertEquals("r1", roads.get(0));
        assertEquals("r10", roads.get(1));
        assertEquals("r11", roads.get(2));
        assertEquals("r12", roads.get(3));
        assertEquals("r13", roads.get(4));
    }

    @Test
    public void testGetRoad() {
        assertEquals("r12", graph.getRoad(town[2], town[11]));
        assertEquals("r4", graph.getRoad(town[3], town[7]));
    }

    @Test
    public void testAddTown() {
        assertEquals(false, graph.containsTown("t12"));
        graph.addTown("t12");
        assertEquals(true, graph.containsTown("t12"));
    }

    @Test
    public void testDisjointGraph() {
        assertEquals(false, graph.containsTown("t12"));
        graph.addTown("t12");
        ArrayList<String> path = graph.getPath(town[1], "t12");
        assertFalse(path.size() > 0);
    }

    @Test
    public void testContainsTown() {
        assertEquals(true, graph.containsTown("t2"));
        assertEquals(false, graph.containsTown("t12"));
    }

    @Test
    public void testContainsRoadConnection() {
        assertEquals(true, graph.containsRoadConnection(town[2], town[11]));
        assertEquals(false, graph.containsRoadConnection(town[3], town[5]));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = graph.allRoads();
        assertEquals("r1", roads.get(0));
        assertEquals("r10", roads.get(1));
        assertEquals("r11", roads.get(2));
        assertEquals("r8", roads.get(10));
        assertEquals("r9", roads.get(11));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertEquals(true, graph.containsRoadConnection(town[2], town[11]));
        graph.deleteRoadConnection(town[2], town[11], "r12");
        assertEquals(false, graph.containsRoadConnection(town[2], town[11]));
    }

    @Test
    public void testDeleteTown() {
        assertEquals(true, graph.containsTown("t2"));
        graph.deleteTown(town[2]);
        assertEquals(false, graph.containsTown("t2"));
    }

    @Test
    public void testAllTowns() {
        ArrayList<String> roads = graph.allTowns();
        assertEquals("t1", roads.get(0));
        assertEquals("t10", roads.get(1));
        assertEquals("t11", roads.get(2));
        assertEquals("t2", roads.get(3));
        assertEquals("t8", roads.get(9));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = graph.getPath(town[1], town[11]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("t1 via r1 to t2 3 mi", path.get(0).trim());
        assertEquals("t2 via r12 to t11 7 mi", path.get(1).trim());
    }

    @Test
    public void testGetPathA() {
        ArrayList<String> path = graph.getPath(town[1], town[10]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("t1 via r2 to t3 5 mi", path.get(0).trim());
        assertEquals("t3 via r5 to t8 4 mi", path.get(1).trim());
        assertEquals("t8 via r9 to t10 3 mi", path.get(2).trim());
    }

    @Test
    public void testGetPathB() {
        ArrayList<String> path = graph.getPath(town[1], town[6]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("t1 via r2 to t3 5 mi", path.get(0).trim());
        assertEquals("t3 via r5 to t8 4 mi", path.get(1).trim());
        assertEquals("t8 via r9 to t10 3 mi", path.get(2).trim());
        assertEquals("t10 via r8 to t9 5 mi", path.get(3).trim());
        assertEquals("t9 via r7 to t6 2 mi", path.get(4).trim());
    }
}
