import java.util.Objects;

public class Road implements Comparable<Road> {

    private Town front;
    private Town end;
    private int weight;
    private String roadName;

    public Road(Town t1, Town t2, int weight, String roadName) {
        this.front = t1;
        this.end = t2;
        this.weight = weight;
        this.roadName = roadName;
    }


    //gets the end
    protected Town getEnd() {
        return this.end;
    }

    //gets the front
    protected Town getFront() {
        return this.front;
    }
    
    //returns the weight of the road
    protected int getWeight() {
        return this.weight;
    }

    //returns the name of the road
    protected String getRoadName() {
        return this.roadName;
    }

    //checks to see if it contains
    public boolean has(Town town) {
        if (town == null) 
        {
            return false;
        } 
        else 
        {
            if (town.equals(this.front))
            {
                return true;
                
            }
            
            else if (town.equals(this.end)) {
                return true;
            } 
            else 
            {
                return false;
            }
            
        }
    }
    
    //Overridden compareTo method
    @Override
    public int compareTo(Road othRoad) {
        if (this.equals(othRoad)) 
        {
            return 0;
        } 
        else 
        {
            return this.weight - othRoad.weight;
        }
    }


    @Override
	public boolean equals(Object otherobj) { 
		Road other = (Road) otherobj;
		return Objects.equals(roadName, other.roadName);
	}

    @Override
	public int hashCode() {
	    return roadName.hashCode();
	}
    
//toString method that returns the name of the road
    @Override
    public String toString() {
        return this.roadName;
    }
}
