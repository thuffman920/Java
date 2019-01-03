package tshuffma.scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ability of this program to construct 
 * a new employee for this company, which houses the 
 * preferred and present schedules
 * @author Tyler Huffman
 */
public class EmployeeTest {

	/** This is the slice line job position */
	public JobPosition sliceLine = new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0);
	/** This is the salad bar job position */
	public JobPosition saladBar = new JobPosition("salad bar", "SB", 1, 0, 6.0, 22.0);
	/**  */
	public JobPosition[] tylerJobs = {sliceLine};
	/**  */
	public JobPosition[] ginnyJobs = {saladBar, sliceLine};
	
	public JobPosition[] ginnyJobs1 = {saladBar};
	/** This is the list of preferred work days for the first employee */
	public Day[] tylerPreDays = new Day[7];
	/** This is the list of preferred work days for the second employee */
	public Day[] ginnyPreDays = new Day[7];
	/** This is the list of present work days for the first employee */
	public Day[] tylerDays = new Day[7];
	/** This is the list of present work days for the second employee */
	public Day[] ginnyDays = new Day[7];
	/** This is the preferred schedule for the first employee */
	public Schedule preschedule1;
	/** This is the present schedule for the first employee */
	public Schedule schedule1;
	/** This is the present schedule for the second employee */
	public Schedule preschedule2;
	/** This is the preferred schedule for the second employee */
	public Schedule schedule2;
	/** This is the first employee to be tested */
	public Employee tyler;
	/** This is the second employee to be tested */
	public Employee ginny;
	
	/**
	 * This sets up the important parts in order to be 
	 * tested later on in this program
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		tylerPreDays[0] = new Day("Wednesday", 6, 22, tylerJobs);
		tylerPreDays[1] = new Day("Thursday", 6, 22, tylerJobs);
		tylerPreDays[2] = new Day("Friday", 6, 22, tylerJobs);
		tylerPreDays[3] = new Day("Saturday", 6, 22, tylerJobs);
		tylerPreDays[4] = new Day("Sunday", 6, 22, tylerJobs);
		tylerPreDays[5] = new Day("Monday", 6, 22, tylerJobs);
		tylerPreDays[6] = new Day("Tuesday", 6, 22, tylerJobs);
		preschedule1 = new Schedule(tylerPreDays);
		tylerDays[0] = new Day("Wednesday", 14, 22, tylerJobs);
		tylerDays[1] = new Day("Thursday", 14, 22, tylerJobs);
		tylerDays[2] = new Day("Friday", 14, 22, tylerJobs);
		tylerDays[3] = new Day("Saturday", 14, 14, tylerJobs);
		tylerDays[4] = new Day("Sunday", 14, 14, tylerJobs);
		tylerDays[5] = new Day("Monday", 14, 22, tylerJobs);
		tylerDays[6] = new Day("Tuesday", 14, 22, tylerJobs);
		schedule1 = new Schedule(tylerDays);
		tyler = new Employee("Tyler Shane Huffman", preschedule1, schedule1, "p");
		ginnyPreDays[0] = new Day("Wednesday", 6, 22, ginnyJobs);
		ginnyPreDays[1] = new Day("Thursday", 6, 22, ginnyJobs);
		ginnyPreDays[2] = new Day("Friday", 6, 22, ginnyJobs);
		ginnyPreDays[3] = new Day("Saturday", 6, 22, ginnyJobs);
		ginnyPreDays[4] = new Day("Sunday", 6, 22, ginnyJobs);
		ginnyPreDays[5] = new Day("Monday", 6, 22, ginnyJobs);
		ginnyPreDays[6] = new Day("Tuesday", 6, 22, ginnyJobs);
		preschedule2 = new Schedule(ginnyPreDays);
		ginnyDays[0] = new Day("Wednesday", 14, 22, ginnyJobs1);
		ginnyDays[1] = new Day("Thursday", 14, 14, ginnyJobs1);
		ginnyDays[2] = new Day("Friday", 14, 22, ginnyJobs1);
		ginnyDays[3] = new Day("Saturday", 14, 22, ginnyJobs1);
		ginnyDays[4] = new Day("Sunday", 14, 22, ginnyJobs1);
		ginnyDays[5] = new Day("Monday", 14, 22, ginnyJobs1);
		ginnyDays[6] = new Day("Tuesday", 14, 14, ginnyJobs1);
		schedule2 = new Schedule(ginnyDays);
		ginny = new Employee("Ginny Reese", preschedule2, schedule2, "p");
	}

	/**
	 * This tests the ability of this program to retrieve the
	 * name of this person
	 * Test method for {@link tshuffma.scheduler.util.Employee#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Tyler Shane Huffman", tyler.getName());
		assertEquals("Ginny Reese", ginny.getName());
	}

	/**
	 * This tests the ability of this program to retrieve the 
	 * preferred schedule for this program
	 * Test method for {@link tshuffma.scheduler.util.Employee#getPreferredSchedule()}.
	 */
	@Test
	public void testGetPreferredSchedule() {
		assertEquals(preschedule1.toString(), tyler.getPreferredSchedule().toString());
		assertEquals(preschedule2.toString(), ginny.getPreferredSchedule().toString());
	}

	/**
	 * This tests the ability of this program to retrieve the 
	 * present schedule for this person
	 * Test method for {@link tshuffma.scheduler.util.Employee#getPresentSchedule()}.
	 */
	@Test
	public void testGetPresentSchedule() {
		assertEquals(schedule1.toString(), tyler.getPresentSchedule().toString());
		assertEquals(schedule2.toString(), ginny.getPresentSchedule().toString());
	}

	/**
	 * This tests the ability of this program to change the present
	 * schedule to a newer schedule
	 * Test method for {@link tshuffma.scheduler.util.Employee#setPreferredScehedule(tshuffma.scheduler.util.Schedule)}.
	 */
	@Test
	public void testSetPreferredScehedule() {
		Day[] tylerDays1 = new Day[7];
		tylerDays1[0] = new Day("Wednesday", 12, 22, tylerJobs);
		tylerDays1[1] = new Day("Thursday", 6, 22, tylerJobs);
		tylerDays1[2] = new Day("Friday", 6, 22, tylerJobs);
		tylerDays1[3] = new Day("Saturday", 6, 22, tylerJobs);
		tylerDays1[4] = new Day("Sunday", 6, 22, tylerJobs);
		tylerDays1[5] = new Day("Monday", 6, 22, tylerJobs);
		tylerDays1[6] = new Day("Tuesday", 6, 22, tylerJobs);
		Schedule schedule3 = new Schedule(tylerDays1);
		tyler.setPreferredSchedule(schedule3);
		assertEquals(schedule3.toString(), tyler.getPreferredSchedule().toString());
	}
	
	/**
	 * This tests the ability of this program to change the present
	 * schedule to a newer schedule
	 * Test method for {@link tshuffma.scheduler.util.Employee#setPresentScehedule(tshuffma.scheduler.util.Schedule)}.
	 */
	@Test
	public void testSetPresentScehedule() {
		Day[] tylerDays1 = new Day[7];
		tylerDays1[0] = new Day("Wednesday", 14, 22, tylerJobs);
		tylerDays1[1] = new Day("Thursday", 14, 14, tylerJobs);
		tylerDays1[2] = new Day("Friday", 14, 22, tylerJobs);
		tylerDays1[3] = new Day("Saturday", 14, 22, tylerJobs);
		tylerDays1[4] = new Day("Sunday", 14, 22, tylerJobs);
		tylerDays1[5] = new Day("Monday", 14, 22, tylerJobs);
		tylerDays1[6] = new Day("Tuesday", 14, 14, tylerJobs);
		Schedule schedule3 = new Schedule(tylerDays1);
		tyler.setPresentSchedule(schedule3);
		assertEquals(schedule3.toString(), tyler.getPresentSchedule().toString());
	}

	/**
	 * This tests the ability of the program to transform the person's
	 * name into a modified formation
	 * Test method for {@link tshuffma.scheduler.util.Employee#toFormalName()}.
	 */
	@Test
	public void testToFormalName() {
		assertEquals("Huffman, Tyler Shane", tyler.toFormalName());
		assertEquals("Reese, Ginny", ginny.toFormalName());
	}

	/**
	 * This tests the ability of the program to transform the preferred
	 * schedule into a string formation
	 * Test method for {@link tshuffma.scheduler.util.Employee#toPreferredString()}.
	 */
	@Test
	public void testToPreferredString() {
		String result1 = "Huffman, Tyler Shane\tp\t" + preschedule1.toString();
		assertEquals(result1, tyler.toPreferredString());
		String result2 = "Reese, Ginny\tp\t" + preschedule2.toString();
		assertEquals(result2, ginny.toPreferredString());
	}

	/**
	 * This tests the ability of the program to transform the present
	 * schedule into a string formation
	 * Test method for {@link tshuffma.scheduler.util.Employee#toPresentString()}.
	 */
	@Test
	public void testToPresentString() {
		String result1 = "Huffman, Tyler Shane\t2:00p-10:00p SL\t2:00p-10:00p SL\t2:00p-10:00p SL\tOff\tOff\t"
				+ "2:00p-10:00p SL\t2:00p-10:00p SL";
		assertEquals(result1, tyler.toPresentString());
		String result2 = "Reese, Ginny\t2:00p-10:00p SB\tOff\t2:00p-10:00p SB\t2:00p-10:00p SB\t"
				+ "2:00p-10:00p SB\t2:00p-10:00p SB\tOff";
		assertEquals(result2, ginny.toPresentString());
	}

	/**
	 * This tests the ability of the program to transform the present
	 * schedule into a string formation
	 * Test method for {@link tshuffma.scheduler.util.Employee#toPresentString()}.
	 */
	@Test
	public void testIsFullTime() {
		assertFalse(tyler.isFullTime());
		assertFalse(ginny.isFullTime());
	}
	
	/**
	 * This tests the ability of the program to transform the present
	 * schedule into a string formation
	 * Test method for {@link tshuffma.scheduler.util.Employee#setFullTime(boolean)}.
	 */
	@Test
	public void testsetFullTime() {
		tyler.setFullTime(true);
		assertTrue(tyler.isFullTime());
		ginny.setFullTime(true);
		assertTrue(ginny.isFullTime());
	}
}
