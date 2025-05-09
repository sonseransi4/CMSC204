import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The root interface in the graph hierarchy. A mathematical graph-theory graph
 * object G(V,E) contains a set V of vertices and a set
 * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
 *
 * Through generics, a graph can be typed to specific classes for vertices
 * V and edges E<T>. Such a graph can contain
 * vertices of type V and all sub-types and Edges of type
 * E and all sub-types.
 */
public class Graph implements GraphInterface<Town, Road> {
	
    private Set<Town> townSet;
    private Map<Town, Town> prev;
    private Map<Town, Integer> size;
    private Set<Road> roadSets;
    private Map<Town, Set<Road>> adj;

   
    
    public Graph() {
        townSet = new HashSet<Town>();
        roadSets = new HashSet<Road>();
        
        adj = new HashMap<Town, Set<Road>>();
    }

    
    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) 
        {
            throw new NullPointerException("verticies can't be null");
        }
        if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
            throw new IllegalArgumentException("verticies don't exist on the graph");
        }
        
        Set<Road> connectingRoads = adj.get(sourceVertex);
        if (connectingRoads != null) 
        {
            for (Road road : connectingRoads)
            {
                if (road.has(sourceVertex) && road.has(destinationVertex)) {
                    return road;
                    
                }
            }
        }
        
        return null;
    }
    
    
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null)
        {
            throw new NullPointerException("verticies can't be null");
        }
        if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
        {
            throw new IllegalArgumentException("needs both verticies on the graph");
        }

        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        if (roadSets.contains(road))
        {
            return null;
        }
        roadSets.add(road);

        
        if (!adj.containsKey(sourceVertex)) 
        {
            adj.put(sourceVertex, new HashSet<Road>());
        }
        
        if (!adj.containsKey(destinationVertex)) {
            adj.put(destinationVertex, new HashSet<Road>());
        }

        adj.get(sourceVertex).add(road);
        adj.get(destinationVertex).add(road);
        return road;
        
    }

    
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town v) {
        if (v == null) 
        {
            return false;
        }

        if (townSet.contains(v)) 
        {
            return false;
        } else 
        {
            townSet.add(v);
            adj.put(v, new HashSet<Road>());
            return true;
        }
    }

    
    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        Road temp = getEdge(sourceVertex, destinationVertex);
        if (temp != null) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    @Override
    public boolean containsVertex(Town v) {
        if (townSet.contains(v))
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet() {
        if (roadSets == null) 
        {
            return Collections.emptySet();
        }
        return new HashSet<Road>(roadSets);
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (vertex == null) 
        {
            throw new NullPointerException("verticies can't be null");
        }
        if (!containsVertex(vertex)) 
        {
            throw new IllegalArgumentException("Verticies not on the graph");
        }
        
        Set<Road> roadEdj = adj.get(vertex);
        if (roadEdj == null) 
        {
            return Collections.emptySet();
        }
        return new HashSet<Road>(roadEdj);
        
    }

    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road road = new Road(sourceVertex, destinationVertex, weight, description);

        if (adj.containsKey(sourceVertex)) 
        {
            adj.get(sourceVertex).remove(road);
        }

        if (adj.containsKey(destinationVertex)) 
        {
            adj.get(destinationVertex).remove(road);
        }

        roadSets.remove(road);
        return road;
    }

    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town v) {
        if (!townSet.contains(v)) 
        {
            return false;
        }

        Set<Road> linked = new HashSet<Road>();
        Set<Road> roads = adj.get(v);

        if (roads != null) 
        {
            Road[] roadArray = roads.toArray(new Road[0]);
            for (int i = 0; i < roadArray.length; i++) {
                linked.add(roadArray[i]);
            }
        }

        Road[] connectedArray = linked.toArray(new Road[0]);
        for (int i = 0; i < connectedArray.length; i++) 
        {
            Road tempRoad = connectedArray[i];
            Town townT = tempRoad.getEnd().equals(v) ? tempRoad.getFront() : tempRoad.getEnd();
            if (adj.containsKey(townT)) 
            {
                adj.get(townT).remove(tempRoad);
            }
            roadSets.remove(tempRoad);
        }

        townSet.remove(v);
        adj.remove(v);
        return true;
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {
        return townSet;
    }

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);

        ArrayList<String> path = new ArrayList<String>();
        Town townTemp = destinationVertex;

        if (!prev.containsKey(townTemp)) 
        {
            return path;
        }

        while (townTemp != null && prev.get(townTemp) != null) 
        {
            Town lastNode = prev.get(townTemp);
            Road tempR = getEdge(lastNode, townTemp);

            if (tempR != null) 
            {
                String strPart = lastNode.getName() + " via " + tempR.getRoadName() + " to " + townTemp.getName() + " " + tempR.getWeight() + " mi";
                path.add(0, strPart);
            }

            townTemp = lastNode;
        }

        return path;
    }

    
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        size = new HashMap<Town, Integer>();
        prev = new HashMap<Town, Town>();
        PriorityQueue<Town> prior = new PriorityQueue<Town>(Comparator.comparingInt(size::get));

        Town[] townArray = townSet.toArray(new Town[0]);
        for (int i = 0; i < townArray.length; i++) {
            Town town = townArray[i];
            size.put(town, Integer.MAX_VALUE);
            prev.put(town, null);
        }

        size.put(sourceVertex, 0);
        prior.add(sourceVertex);

        while (!prior.isEmpty()) {
            Town current = prior.poll();

            Set<Road> edges = edgesOf(current);
            Road[] roadArrEd = edges.toArray(new Road[0]);

            for (int x = 0; x < roadArrEd.length; x++) 
            {
                Road roadSet = roadArrEd[x];
                Town connects = roadSet.getFront().equals(current) ? roadSet.getEnd() : roadSet.getFront();
                int alt = size.get(current) + roadSet.getWeight();

                if (alt < size.get(connects)) 
                {
                    size.put(connects, alt);
                    prev.put(connects, current);
                    prior.add(connects);
                }
            }
        }
    }
}