import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
    private GraphInterface<Town, Road> graph;
    private Town[] towns;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        towns = new Town[12];

        String[] names = {"Olney", "Rockville", "Bethesda", "Gaithersburg", "SilverSpring", "ChevyChase", "Potomac", "Clarksburg", "Takoma", "Germantown", "Kensington", "Wheaton"};
        for (int i = 0; i < 12; i++) {
            towns[i] = new Town(names[i]);
            graph.addVertex(towns[i]);
        }

        graph.addEdge(towns[0], towns[1], 3, "road1");
        graph.addEdge(towns[0], towns[2], 6, "road2");
        graph.addEdge(towns[0], towns[4], 5, "road3");
        graph.addEdge(towns[2], towns[6], 2, "road4");
        graph.addEdge(towns[2], towns[7], 4, "road5");
        graph.addEdge(towns[3], towns[7], 3, "road6");
        graph.addEdge(towns[5], towns[8], 4, "road7");
        graph.addEdge(towns[8], towns[9], 5, "road8");
        graph.addEdge(towns[7], towns[9], 3, "road9");
        graph.addEdge(towns[4], towns[9], 6, "road10");
        graph.addEdge(towns[9], towns[10], 2, "road11");
        graph.addEdge(towns[1], towns[10], 5, "road12");
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testGetEdge() {
        assertEquals(new Road(towns[1], towns[10], 5, "road12"), graph.getEdge(towns[1], towns[10]));
        assertEquals(new Road(towns[2], towns[6], 2, "road4"), graph.getEdge(towns[2], towns[6]));
    }

    @Test
    public void testAddEdge() {
        assertFalse(graph.containsEdge(towns[2], towns[4]));
        graph.addEdge(towns[2], towns[4], 3, "road13");
        assertTrue(graph.containsEdge(towns[2], towns[4]));
    }

    @Test
    public void testAddVertex() {
        Town newTown = new Town("Langley Park");
        assertFalse(graph.containsVertex(newTown));
        graph.addVertex(newTown);
        assertTrue(graph.containsVertex(newTown));
    }

    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge(towns[1], towns[10]));
        assertFalse(graph.containsEdge(towns[2], towns[4]));
    }

    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex(new Town("Rockville")));
        assertFalse(graph.containsVertex(new Town("Hyattsville")));
    }
  

    @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(towns[0]);
        ArrayList<String> names = new ArrayList<>();
        for (Road road : roads)
            names.add(road.getRoadName());
        Collections.sort(names);
        assertEquals("road1", names.get(0));
        assertEquals("road2", names.get(1));
        assertEquals("road3", names.get(2));
    }

    @Test
    public void testRemoveEdge() {
        assertTrue(graph.containsEdge(towns[1], towns[10]));
        graph.removeEdge(towns[1], towns[10], 5, "road12");
        assertFalse(graph.containsEdge(towns[1], towns[10]));
    }

    @Test
    public void testRemoveVertex() {
        assertTrue(graph.containsVertex(towns[1]));
        graph.removeVertex(towns[1]);
        assertFalse(graph.containsVertex(towns[1]));
    }

    @Test
    public void testVertexSet() {
        Set<Town> townSet = graph.vertexSet();
        assertTrue(townSet.contains(towns[0]));
        assertTrue(townSet.contains(towns[9]));
        assertTrue(townSet.contains(towns[10]));
        assertTrue(townSet.contains(towns[1]));
        assertTrue(townSet.contains(towns[2]));
    }

  
}
