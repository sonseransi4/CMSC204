import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
	
	
    private CourseDBStructure objStructure; //creates a CourseDBStructure object

    public CourseDBManager()//default, again makes courseDBStructure
    {
    	
        objStructure = new CourseDBStructure(10);
    }
    
    
    /**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) 
    {
    	
    	
        CourseDBElement objElem = new CourseDBElement(id, crn, credits, roomNum, instructor);
        //creates CourseDBElement object
        objStructure.add(objElem);
    }

    /**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
    @Override
    public CourseDBElement get(int crn) {
        try 
        {
            return objStructure.get(crn);
        } 
        catch (IOException e) //Error catch
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
    @Override
    public void readFile(File input) throws FileNotFoundException 
    {
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) 
        {
            String record = scan.nextLine();
            String[] recordSplit = record.split("\\s+", 5); //splits the name so that there can be space
            if (recordSplit.length < 5) {
            	continue;
            }// if input bad
            int crn = Integer.parseInt(recordSplit[1]);
            int credits = Integer.parseInt(recordSplit[2]);
            
            objStructure.add(new CourseDBElement(recordSplit[0], crn, credits, recordSplit[3], recordSplit[4]));
        }
        
        scan.close();
    }
    /**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */    
    @Override
    public ArrayList<String> showAll() 
    {
    	
        return objStructure.showAll();
    }
    
}
