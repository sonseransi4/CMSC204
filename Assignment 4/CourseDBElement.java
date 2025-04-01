public class CourseDBElement implements Comparable<CourseDBElement> {
	
	//initialize variables
    public String id;
    public String roomNum;
    
    public int crn;
    public String instruct;
    public int credits;

    //default 
    public CourseDBElement() 
    {
        this.id = "";
        this.credits = 0;
        this.roomNum = "";
        this.crn = 0;
        this.instruct = "";
    }

    //parameter
    public CourseDBElement(String id, int crn, int credits, String roomNum, String instruct) {
        this.id = id;
        this.crn = crn;
        this.credits = credits;
        this.roomNum = roomNum;
        this.instruct = instruct;
    }


    //getters
    public String getID() 
    { 
    	return id;
    }
    
    public int getCRN() { 
    	return crn; 
    	}
    public int getCredits() 
    { 
    	return credits;
    	}
    public String getRoomNum() 
    { 
    	return roomNum; 
    }
    public String getInstructorName() { 
    	return instruct; 
    	}

    // Setters
    public void setCRN(int CRN) 
    {
        this.crn = CRN;
    }
    
    public void setID(String courseID) 
    {
        this.id = courseID;
    }

    public void setCredits(int credits) 
    {
        this.credits = credits;
    }

    public void setRoomNum(String roomNumber) 
    {
        this.roomNum = roomNumber;
    }

    public void setInstructorName(String instructorName) 
    {
        this.instruct = instructorName;
    }

    @Override
    public int compareTo(CourseDBElement other) {
        return Integer.compare(this.crn, other.crn);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(crn);
    }
    
    
    @Override
    public String toString() //tostring method, creates string
    {
		String info="Course:"+id+" CRN:"+crn+" Credits:"+credits+" Instructor:"+instruct+" Room:"+roomNum;
		return info;
	}
}
