package tshuffma.scheduler.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ability of creating a schedule
 * for an employee for a certain week
 * @author Tyler Huffman
 */
public class ScheduleTest {

	/** This is the first collection of the days for this schedule */
	public Day[] days1 = new Day[7];
	/** This is the second collection of the days for this schedule */
	public Day[] days2 = new Day[7];
	/** This creates the slice line job position */
	public JobPosition sliceLine = new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0);
	/** This creates the salad bar job position */
	public JobPosition saladBar = new JobPosition("salad bar", "SB", 1, 0, 6.0, 22.0);
	/**  */
	public JobPosition[] jobs1 = {sliceLine};
	/**  */
	public JobPosition[] jobs2 = {saladBar, sliceLine};
	/** This is the first schedule that will be used for testing */
	public Schedule schedule1;
	/** This is the second schedule that will be used for testing */
	public Schedule schedule2;
	
	/**
	 * This sets up the important aspects that will be used for
	 * testing later on
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		days1[0] = new Day("Wednesday", 14, 22, jobs1);
		days1[1] = new Day("Thursday", 14, 22, jobs1);
		days1[2] = new Day("Friday", 14, 22, jobs1);
		days1[3] = new Day("Saturday", 14, 14, jobs1);
		days1[4] = new Day("Sunday", 14, 14, jobs1);
		days1[5] = new Day("Monday", 14, 22, jobs1);
		days1[6] = new Day("Tuesday", 14, 22, jobs1);
		schedule1 = new Schedule(days1);
		days2[0] = new Day("Wednesday", 14, 22, jobs2);
		days2[1] = new Day("Thursday", 14, 22, jobs2);
		days2[2] = new Day("Friday", 14, 22, jobs2);
		days2[3] = new Day("Saturday", 14, 14, jobs2);
		days2[4] = new Day("Sunday", 14, 14, jobs2);
		days2[5] = new Day("Monday", 14, 22, jobs2);
		days2[6] = new Day("Tuesday", 14, 22, jobs2);
		schedule2 = new Schedule(days2);
		Day[] days3 = new Day[7];
		days3[0] = new Day("Wednesday", 14, 22, jobs2);
		days3[1] = new Day("Thursday", 14, 22, jobs2);
		days3[2] = new Day("Frjuyday", 14, 22, jobs2);
		days3[3] = new Day("Saturday", 14, 14, jobs2);
		days3[4] = new Day("Sunday", 14, 14, jobs2);
		days3[5] = new Day("Monday", 14, 22, jobs2);
		days3[6] = new Day("Tuesday", 14, 22, jobs2);
		try {
			new Schedule(days3);
		} catch(IllegalArgumentException g) {
			assertEquals("This is not a real day", g.getMessage());
		}
	}

	/**
	 * This tests the ability to retrieve the work day in
	 * the schedule at a certain index
	 * Test method for {@link tshuffma.scheduler.util.Schedule#dayAt(int)}.
	 */
	@Test
	public void testDayAt() {
		assertEquals(days1[4].toString(), schedule1.dayAt(4).toString());
		try { 
			schedule1.dayAt(7).toString();
		} catch (IndexOutOfBoundsException f) {
			assertEquals("Index is greater than 7", f.getMessage());
		}
	}

	/**
	 * This tests the ability of returning the list of all
	 * days for this schedule
	 * Test method for {@link tshuffma.scheduler.util.Schedule#getListOfDays()}.
	 */
	@Test
	public void testGetListOfDays() {
		ArrayList<Day> list = schedule1.getListOfDays();
		assertEquals(days1[3].toString(), list.get(3).toString());
		ArrayList<Day> list2 = schedule2.getListOfDays();
		assertEquals(days2[3].toString(), list2.get(3).toString());
	}

	/**
	 * This tests the ability of creating a string formation
	 * that depicts the schedule
	 * Test method for {@link tshuffma.scheduler.util.Schedule#toString()}.
	 */
	@Test
	public void testToString() {
		String result1 = days1[0].toString() + "\t" + days1[1].toString() + "\t" + days1[2].toString() + "\t"
				+ days1[3].toString() + "\t" + days1[4].toString() + "\t" + days1[5].toString() + "\t" + days1[6].toString();
		assertEquals(result1, schedule1.toString());
		String result2 = days2[0].toString() + "\t" + days2[1].toString() + "\t" + days2[2].toString() + "\t"
				+ days2[3].toString() + "\t" + days2[4].toString() + "\t" + days2[5].toString() + "\t" + days2[6].toString();
		assertEquals(result2, schedule2.toString());
	}
}