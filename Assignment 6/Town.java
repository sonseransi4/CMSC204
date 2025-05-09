public class Town implements Comparable<Town> {
	String townName;
	
	//constructor
	public Town(String townName) {
		this.townName = townName;
	}

	//hash code
	@Override
	public int hashCode() {
		return townName.hashCode();
	}
	
	//Overrides compareTo
	@Override
	public int compareTo(Town other) {
		if (this.townName.equals(other.getName()))
		{
			return 0;
		}
		return -1;
	}

	//Returns name
	protected String getName() {
		return this.townName;
	}
	
	//Checks if its equals, and returns true or false
	@Override
	public boolean equals(Object otherObj) {
		return townName.equals(((Town) otherObj).getName());
	}
	
	//The toString that returns name
	@Override
	public String toString() {
		return townName;
	}
}