package tshuffma.scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This ability of this program to construct a work
 * day and return the important details
 * @author Tyler Huffman
 */
public class DayTest {

	/** This is the first day to be tested */
	public Day day1;
	/** This is the second day to be tested */
	public Day day2;
	/** This is the slice line job position */
	public JobPosition sliceLine = new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0);
	/** This is the salad bar job position */
	public JobPosition saladBar = new JobPosition("salad bar", "SB", 1, 0, 6.0, 22.0);
	/**  */
	public JobPosition[] jobs1 = {sliceLine};
	/**  */
	public JobPosition[] jobs2 = {saladBar, sliceLine};
	
	/**
	 * This sets up the important parts 
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		
		day1 = new Day("Tuesday", 14, 22, jobs1);
		day2 = new Day("Wednesday", 14, 22, jobs2);
	}

	/**
	 * This tests the ability of this program to return the day
	 * of the week for this work day
	 * Test method for {@link tshuffma.scheduler.util.Day#getDayOfWeek()}.
	 */
	@Test
	public void testGetDayOfWeek() {
		assertEquals("Tuesday", day1.getDayOfWeek());
		assertEquals("Wednesday", day2.getDayOfWeek());
	}

	/**
	 * This tests the ability of this program to return the starting
	 * time for this work day
	 * Test method for {@link tshuffma.scheduler.util.Day#getStartingTime()}.
	 */
	@Test
	public void testGetStartingTime() {
		assertTrue(14 == day1.getStartingTime());
		assertTrue(14 == day2.getStartingTime());
	}

	/**
	 * This tests the ability of this program to return the ending time
	 * for this work day
	 * Test method for {@link tshuffma.scheduler.util.Day#getEndingTime()}.
	 */
	@Test
	public void testGetEndingTime() {
		assertTrue(22 == day1.getEndingTime());
		assertTrue(22 == day1.getEndingTime());
	}

	/**
	 * This tests the ability of this program to return the abbreviation
	 * of this job position
	 * Test method for {@link tshuffma.scheduler.util.Day#getJobPositionAbbreviation()}.
	 */
	@Test
	public void testGetJobPositionAbbreviation() {
		assertEquals("SL", day1.getJobPositionAbbreviation());
		assertEquals("SB,SL", day2.getJobPositionAbbreviation());
	}

	/**
	 * This tests the ability of this program to set the starting
	 * time for this work day
	 * Test method for {@link tshuffma.scheduler.util.Day#setStartingTime()}.
	 */
	@Test
	public void testSetStartingTime() {
		day1.setStartingTime(16);
		assertTrue(16 == day1.getStartingTime());
		day2.setStartingTime(15);
		assertTrue(15 == day2.getStartingTime());
	}

	/**
	 * This tests the ability of this program to set the ending
	 * time for this work day
	 * Test method for {@link tshuffma.scheduler.util.Day#setEndingTime()}.
	 */
	@Test
	public void testSetEndingTime() {
		day1.setEndingTime(20);
		assertTrue(20 == day1.getEndingTime());
		day2.setEndingTime(19);
		assertTrue(19 == day2.getEndingTime());
	}

	/**
	 * This tests the ability of this program to calculate
	 * the 12 hour period
	 * Test method for {@link tshuffma.scheduler.util.Day#getExactTime()}.
	 */
	@Test
	public void testGetExactTime() {
		assertEquals("2:00p", day1.getExactTime(14));
		assertEquals("10:00p", day1.getExactTime(22));
		assertEquals("4:15a", day1.getExactTime(4.25));
	}

	/**
	 * This tests the ability to change the details of this work day
	 * into a string form
	 * Test method for {@link tshuffma.scheduler.util.Day#toString()}.
	 */
	@Test
	public void testToString() {
		String result1 = "2:00p-10:00p SL";
		assertEquals(result1, day1.toString());
		String result2 = "2:00p-10:00p SB,SL";
		assertEquals(result2, day2.toString());
		Day day3 = new Day("Wednesday", 14, 14, jobs1);
		assertEquals("2:00p-2:00p SL", day3.toString());
	}

}
