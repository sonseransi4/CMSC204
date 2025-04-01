

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 */
public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("HIS101",12345,2,"HUM212","Josef Stoompf");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("HIS101",12345,2,"HUM212","Josef Stoompf");
		dataMgr.add("ENG102",12346,3,"HUM345","Tomas Yo");
		dataMgr.add("CMSC207",54321,3,"SCW203","David Kane");
		dataMgr.add("MATH181",54325,4,"SCW243","James Matem");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:HIS101 CRN:12345 Credits:2 Instructor:Josef Stoompf Room:HUM212");
	 	assertEquals(list.get(1),"\nCourse:ENG102 CRN:12346 Credits:3 Instructor:Tomas Yo Room:HUM345");
		assertEquals(list.get(2),"\nCourse:CMSC207 CRN:54321 Credits:3 Instructor:David Kane Room:SCW203");
		assertEquals(list.get(3),"\nCourse:MATH181 CRN:54325 Credits:4 Instructor:James Matem Room:SCW243");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("MATH181 54325 4 SCW203 James Matem");
			inFile.print("CMSC207 54321 3 SCW203 David Kane");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("MATH181",dataMgr.get(54325).getID());
			assertEquals("CMSC207",dataMgr.get(54321).getID());
			assertEquals("SCW203",dataMgr.get(54321).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
