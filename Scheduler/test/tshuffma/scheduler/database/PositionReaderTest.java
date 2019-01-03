package tshuffma.scheduler.database;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import tshuffma.scheduler.util.JobPosition;

/**
 * This tests the ability of this program to bring
 * the saved job positions from a text file, whcih will
 * be used later
 * @author Tyler Huffman
 */
public class PositionReaderTest {

	/** This initializes the sub bar job position */
	public JobPosition sub = new JobPosition("sub bar", "sub", 2, 1, 7.0, 22.0);
	/** This initializes the slice line job position */
	public JobPosition sliceLine = new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0);
	/** This initializes the salad bar job position */
	public JobPosition saladBar = new JobPosition("salad bar", "SB", 1, 0, 6.0, 22.0);
	/** This initializes the asian bar job position */
	public JobPosition asianBar = new JobPosition("asian bar", "Wok", 2, 1, 6.0, 22.0);
	/** This creates a new job position reader which will be tested */
	public PositionReader jobPosition;
	
	/**
	 * This sets up the more important aspects that will aid
	 * in testing the position reader
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter("positions.txt"));
		writer.write(sub.toString());
		writer.newLine();
		writer.write(sliceLine.toString());
		writer.newLine();
		writer.write(saladBar.toString());
		writer.newLine();
		writer.write(asianBar.toString());
		writer.newLine();
		writer.close();
		jobPosition = new PositionReader();
	}

	/**
	 * This tests the ability of this program to find a
	 * certain job in the list of job positions from an
	 * abbreviation
	 * Test method for {@link tshuffma.scheduler.database.PositionReader#findJob(java.lang.String)}.
	 */
	@Test
	public void testFindJob() {
		JobPosition position = jobPosition.findJob("SL");
		assertEquals(sliceLine.getAbbreviation(), position.getAbbreviation());
		assertEquals(sliceLine.getJobPosition(), position.getJobPosition());
		assertTrue(sliceLine.getMaxEmployees() == position.getMaxEmployees());
		assertTrue(sliceLine.getMinEmployees() == position.getMinEmployees());
	}

	/**
	 * This tests the ability of this program to add a new job 
	 * to this company, which will be added back to the text file
	 * Test method for {@link tshuffma.scheduler.database.PositionReader#addJob(tshuffma.scheduler.util.JobPosition)}.
	 */
	@Test
	public void testAddJob() {
		JobPosition cook = new JobPosition("cook", "cook", 1, 1, 5.0, 22.0);
		jobPosition.addJob(cook);
		JobPosition position = jobPosition.findJob("cook");
		assertEquals(cook, position);
		jobPosition.addJob(new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0));
		assertEquals(sliceLine.getAbbreviation(), jobPosition.findJob("SL").getAbbreviation());
	}

	/**
	 * This tests the ability of this program to write the list
	 * of jobs back to the text file
	 * Test method for {@link tshuffma.scheduler.database.PositionReader#writeToFile()}.
	 */
	@Test
	public void testWriteToFile() {
		JobPosition cook = new JobPosition("cook", "cook", 1, 1, 5.0, 22.0);
		jobPosition.addJob(cook);
		jobPosition.writeToFile();
		String results = "";
		try {
			Scanner reader = new Scanner(new File("positions.txt"));
			while (reader.hasNextLine())
				results += reader.nextLine() + "\n";
			reader.close();
			assertTrue(jobPosition.findJob("you") == null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String actual = "sub bar\tsub\t7.0-22.0\t1\t2\nslice line\tSL\t6.0-22.0\t1\t-1\n"
				+ "salad bar\tSB\t6.0-22.0\t0\t1\nasian bar\tWok\t6.0-22.0\t1\t2\ncook\tcook\t5.0-22.0\t1\t1\n";
		assertEquals(actual, results);
	}
	
	/**
	 * 
	 * Test method for {@link tshuffma.scheduler.database.PositionReader#size()}.
	 */
	@Test
	public void testSize() {
		assertTrue(4 == jobPosition.size());
	}
}