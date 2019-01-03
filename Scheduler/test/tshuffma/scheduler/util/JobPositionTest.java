package tshuffma.scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ability of the program to initialize
 * a new job position and set and return the important
 * aspects of this job position
 * @author Tyler Huffman
 */
public class JobPositionTest {

	/** This tests the slice line position */
	public JobPosition sliceLine;
	/** This test the salad bar position */
	public JobPosition saladBar;
	
	/**
	 * This sets up the important parts that need to
	 * be tested
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		sliceLine = new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0);
		saladBar = new JobPosition("salad bar", "SB", 1, 0, 6.0, 22.0);
		try {
			new JobPosition("sub bar", "sub", -2, 0, 7.0, 22.0);
		} catch (IllegalArgumentException e) {
			assertEquals("Not the correct number of employees", e.getMessage());
		}
	}

	/**
	 * This tests the ability to return the name of this job
	 * position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#getJobPosition()}.
	 */
	@Test
	public void testGetJobPosition() {
		assertEquals("slice line", sliceLine.getJobPosition());
		assertEquals("salad bar", saladBar.getJobPosition());
	}

	/**
	 * This tests the ability to return the maximum number
	 * of employees that are needed for this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#getMaxEmployees()}.
	 */
	@Test
	public void testGetMaxEmployees() {
		assertTrue(-1 == sliceLine.getMaxEmployees());
		assertTrue(1 == saladBar.getMaxEmployees());
	}

	/**
	 * This tests the ability to return the minimum number
	 * of employees that are needed for this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#getMinEmployees()}.
	 */
	@Test
	public void testGetMinEmployees() {
		assertTrue(1 == sliceLine.getMinEmployees());
		assertTrue(0 == saladBar.getMinEmployees());
	}

	/**
	 * This tests the ability to return the abbreviation
	 * of this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#getAbbreviation()}.
	 */
	@Test
	public void testGetAbbreviation() {
		assertEquals("SL", sliceLine.getAbbreviation());
		assertEquals("SB", saladBar.getAbbreviation());
	}

	/**
	 * This tests the ability of the program to set the
	 * maximum number of people needed for this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#setMaxEmployees(int)}.
	 */
	@Test
	public void testSetMaxEmployees() {
		sliceLine.setMaxEmployees(10);
		assertTrue(10 == sliceLine.getMaxEmployees());
		saladBar.setMaxEmployees(2);
		assertTrue(2 == saladBar.getMaxEmployees());
	}

	/**
	 * This tests the ability of the program to set the
	 * minimum number of people needed for this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#setMinEmployees(int)}.
	 */
	@Test
	public void testSetMinEmployees() {
		sliceLine.setMinEmployees(3);
		assertTrue(3 == sliceLine.getMinEmployees());
		saladBar.setMinEmployees(1);
		assertTrue(1 == saladBar.getMinEmployees());
	}

	/**
	 * This tests the ability of this program to set the
	 * abbreviation for this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#setAbbreviation(java.lang.String)}.
	 */
	@Test
	public void testSetAbbreviation() {
		sliceLine.setAbbreviation("Slice");
		assertEquals("Slice", sliceLine.getAbbreviation());
		saladBar.setAbbreviation("Salad");
		assertEquals("Salad", saladBar.getAbbreviation());
	}
	
	/**
	 * This tests the ability of this program to set the
	 * abbreviation for this job position
	 * Test method for {@link tshuffma.scheduler.util.JobPosition#toString()}.
	 */
	@Test
	public void testToString() {
		String result1 = "slice line\tSL\t6.0-22.0\t1\t-1";
		assertEquals(result1, sliceLine.toString());
		String result2 = "salad bar\tSB\t6.0-22.0\t0\t1";
		assertEquals(result2, saladBar.toString());
	}
}