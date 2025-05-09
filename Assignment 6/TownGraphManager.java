import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {
    private Graph g1;

    public TownGraphManager() {
        g1 = new Graph();
    }
    
    
    /**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        g1.addEdge(new Town(town1), new Town(town2), weight, roadName);
        return true;
    }

    
    /**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
    @Override
    public String getRoad(String town1, String town2) {
        Road tempRoad = g1.getEdge(new Town(town1), new Town(town2));
        if (tempRoad != null) 
        {
            return tempRoad.getRoadName();
        }
        else
        return null;
    }

    
    /**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
    @Override
    public boolean addTown(String v) {
        Town tempTown = new Town(v);
        
        return g1.addVertex(tempTown);
    }

    
    /**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
    @Override
    public Town getTown(String name) {
        if (containsTown(name)) {
            return new Town(name);
        } 
        else 
        {
            return null;
        }
    }

    /**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
    @Override
    public boolean containsTown(String v) {
        Town tempTown = new Town(v);
        if (g1.vertexSet().contains(tempTown)) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    
    /**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Road tempToad = g1.getEdge(new Town(town1), new Town(town2));
        if (tempToad != null) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    /**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String> roads = new ArrayList<String>();
        Iterator<Road> it = g1.edgeSet().iterator();
        while (it.hasNext()) 
        {
            Road road = it.next();
            roads.add(road.toString());
            
        }
        
        Collections.sort(roads);
        return roads;
    }

    
    /**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        Town begin = new Town(town1);
        Town ending = new Town(town2);
        
        
        Road r = g1.getEdge(begin, ending);

        if (r != null && r.getRoadName().equals(road))
        {
            g1.removeEdge(begin, ending, r.getWeight(), r.getRoadName());
            return true;
            
        } 
        else 
        {
        	return false;
        	
        }
    }

    
    /**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
    @Override
    public boolean deleteTown(String v) {
        Town tempTown = new Town(v);
        return g1.removeVertex(tempTown);
    }

    /**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String> towns = new ArrayList<String>();
        Iterator<Town> it = g1.vertexSet().iterator();
        while (it.hasNext()) 
        {
            Town town = it.next();
            towns.add(town.toString());
            
        }
        Collections.sort(towns);
        return towns;
    }

    /**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        Town begin = new Town(town1);
        Town ending = new Town(town2);
        return g1.shortestPath(begin, ending);
        
    }

 //added as FXMainPane calls for the adding of a method named populateTownGraph
    public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(selectedFile);
        while (in.hasNextLine()) 
        {
            String line = in.nextLine();
            String[] splitLine = line.split("[,;]");
            
            //splitLine[0] = road name
            //splitLine[1] = distance
            //splitLine[2] = town1 name
            //splitLine[3] = town2 name

            String roadName = splitLine[0];
            int weight = Integer.parseInt(splitLine[1]);
            String town1Name = splitLine[2];
            String town2Name = splitLine[3];
            Town town1 = new Town(town1Name);
            Town town2 = new Town(town2Name);

            g1.addVertex(town1);
            g1.addVertex(town2);
            g1.addEdge(town1, town2, weight, roadName);
            
        }
        
        in.close();
    }
}
