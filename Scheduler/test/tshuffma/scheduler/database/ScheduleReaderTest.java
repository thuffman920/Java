package tshuffma.scheduler.database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ability of this program to read two files,
 * which are the preferred and present schedules for each
 * employee in this company
 * @author Tyler Huffman
 */
public class ScheduleReaderTest {

	/** This is the reader that creates a list of jobs */
	public PositionReader jobs;
	/** This is the scheduler reader meant for testing */
	public ScheduleReader reading;
	
	/**
	 * This sets up the more important aspects that will be
	 * later tested on
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		jobs = new PositionReader();
		reading = new ScheduleReader(jobs);
	}

	/**
	 * This tests the ability of this program to retrieve
	 * the list of employees made from the two files
	 * Test method for {@link tshuffma.scheduler.database.ScheduleReader#getEmployeeList()}.
	 */
	@Test
	public void testGetEmployeeList() {
		String result1 = "Huffman, Tyler\tp\t6:00a-10:00p SL\t6:00a-10:00p SL\t6:00a-10:00p SL\t6:00a-10:00p SL"
				+ "\t6:00a-10:00p SL\t6:00a-10:00p SL\t6:00a-10:00p SL\nReese, Ginny\tp\t6:00a-10:00p sub,SB,SL\t"
				+ "6:00a-10:00p sub,SB,SL\t6:00a-10:00p sub,SB,SL\t6:00a-10:00p sub,SB,SL\t12:00p-10:00p sub,SB,SL\t6:00a-10:00p sub,SB,SL\t"
				+ "6:00a-10:00p sub,SB,SL\nDoe, Gabe\tp\t2:00p-2:00p Wok\t2:00p-2:00p Wok\t12:00p-10:00p Wok"
				+ "\t12:00p-10:00p Wok\t12:00p-10:00p Wok\t2:00p-2:00p Wok\t2:00p-2:00p Wok\n";
		assertEquals(result1, reading.getEmployeeList().toPreferredString());
		String result2 = "Huffman, Tyler\t2:00p-10:00p SL\t2:00p-10:00p SL\t2:00p-10:00p SL\tOff\t"
				+ "Off\t2:00p-10:00p SL\t2:00p-10:00p SL\nReese, Ginny\t2:00p-10:00p sub\tOff\t"
				+ "2:00p-10:00p SB\t2:00p-10:00p sub\t2:00p-10:00p sub\t2:00p-10:00p sub\tOff\n"
				+ "Doe, Gabe\tOff\tOff\tOff\tOff\tOff\tOff\tOff\n";
		assertEquals(result2, reading.getEmployeeList().toPresentString());
	}
	
	
	@Test
	public void testGetPattern() {
		String[] result = {"Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};
		for (int i = 0; i < 7; i++)
			assertEquals(result[i], reading.getPattern()[i]);
	}
	
	@Test
	public void testGetFullTime() {
		assertTrue(40 == reading.getFullTime());
	}
	
	@Test
	public void testGetLunch() {
		assertTrue(.5 == reading.getLunch());
	}
	
	@Test
	public void testGetHours() {
		assertTrue(6 == reading.getHours());
	}
}