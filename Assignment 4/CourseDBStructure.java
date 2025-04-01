import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {
	
	
	
	
    private LinkedList<CourseDBElement>[] table; //initialize hashtable and size of the hashtable
    private int hashsizing;

    
    //checks if the number is prime
    public static boolean primeTest(int num)
    {
    	
        if (num <= 1) 
        	return false;
        for (int i = 2; i * i <= num; i++) 
        {
            if (num % i == 0) 
            	return false;
        }
        
        return true;
    }

    public CourseDBStructure(int amount) //changes into prime number
    {
        int x = (int) Math.floor(amount / 1.5);
        
        while (x * x > x) 
        {
            if (x % 4 == 3 && primeTest(x))
            {
                hashsizing = x;
                break;
            }
            x++;
        }
        
        table = new LinkedList[hashsizing];
    }

    public CourseDBStructure(String str, int amount) 
    {
        hashsizing = amount;
        
        table = new LinkedList[hashsizing];
    }
    /** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
    @Override
    public void add(CourseDBElement element) 
    {
        int hashCode = Math.abs(element.getCRN()) % hashsizing;

        if (table[hashCode] == null) 
        {
            table[hashCode] = new LinkedList<>();
        }

        
        LinkedList<CourseDBElement> temp = table[hashCode];

        for (int i = 0; i < temp.size(); i++) 
        {
        	
            if (temp.get(i).getCRN() == element.getCRN())
            {
            	
                temp.remove(i);
                temp.add(element);
                return;
                
            }
        }

        temp.add(element);
    }

    /**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
    @Override
    public CourseDBElement get(int crn) throws IOException 
    {
        int hashCode = Math.abs(crn) % hashsizing;
        if (table[hashCode] != null) 
        {
            for (CourseDBElement e : table[hashCode]) 
            {
                if (e.getCRN() == crn) 
                {
                    return e;
                }
            }
        }
        throw new IOException("CRN not found");
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
        ArrayList<String> record = new ArrayList<>();
        for (int i = 0; i < hashsizing; i++)
        {
        	
            if (table[i] != null) 
            {
                for (CourseDBElement element : (LinkedList<CourseDBElement>) table[i])
                {
                    record.add(element.toString());
                }
            }
        }
        
       
        Collections.sort(record); 
        //found out and used collections to try to prevent failure in the test 
        //did not work necessarily
        
        return record;
    }
    
    /**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
    @Override
    public int getTableSize() 
    {
        return hashsizing;
    }
}
