package tshuffma.scheduler.queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tshuffma.scheduler.util.Day;
import tshuffma.scheduler.util.Employee;
import tshuffma.scheduler.util.JobPosition;
import tshuffma.scheduler.util.Schedule;

/**
 * This tests the ability of this program to hold
 * a list of employees, which holds the preferred and 
 * present schedules for each employee
 * @author Tyler Huffman
 */
public class EmployeeQueueTest {

	/** This is the slice line job position */
	public JobPosition sliceLine = new JobPosition("slice line", "SL", -1, 1, 6.0, 22.0);
	/** This is the salad bar job position */
	public JobPosition saladBar = new JobPosition("salad bar", "SB", 1, 0, 6.0, 22.0);
	/**  */
	public JobPosition[] tylerJobs = {sliceLine};
	/**  */
	public JobPosition[] ginnyJobs = {saladBar, sliceLine};
	/**  */
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
	/** This sets up the employee queue which will be tested */
	public EmployeeQueue queue;
	
	/**
	 * This sets up the important aspects for this testing
	 * program, which will be used later on
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
		tyler = new Employee("Tyler Huffman", preschedule1, schedule1, "p");
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
		queue = new EmployeeQueue();
	}

	/**
	 * This tests the ability of this program to return the 
	 * employee at a certain index of this list
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#getEmployee(int)}.
	 */
	@Test
	public void testGetEmployee() {
		queue.addToRear(tyler);
		assertEquals(tyler, queue.getEmployee(0));
		try {
			queue.getEmployee(1);
		} catch (IndexOutOfBoundsException g) {
			assertEquals("Outside of the bounds", g.getMessage());
		}
	}
	
	/**
	 * This tests the ability of this program to add a new 
	 * employee to the rear of the list
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#addToRear(tshuffma.scheduler.util.Employee)}.
	 */
	@Test
	public void testAddToRear() {
		queue.addToRear(ginny);
		assertEquals(ginny, queue.getEmployee(0));
		queue.addToRear(tyler);
		assertEquals(tyler, queue.getEmployee(1));
	}

	/**
	 * This tests the ability of this program to add a new
	 * employee at a certain index
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#addAtIndex(int, tshuffma.scheduler.util.Employee)}.
	 */
	@Test
	public void testAddAtIndex() {
		queue.addToRear(ginny);
		assertEquals(ginny, queue.getEmployee(0));
		queue.addAtIndex(0, tyler);
		assertEquals(tyler, queue.getEmployee(0));
		assertEquals(ginny, queue.getEmployee(1));
	}

	/**
	 * This tests the ability of this program to find an
	 * employee from the list of employees, based upon
	 * a full name
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#findEmployee(java.lang.String)}.
	 */
	@Test
	public void testFindEmployee() {
		queue.addToRear(ginny);
		queue.addToRear(tyler);
		assertEquals(tyler, queue.findEmployee("Tyler Huffman"));
		assertEquals(null, queue.findEmployee("Josh Sawyer"));
	}

	/**
	 * This tests the ability of this program to determine
	 * the size of this list
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#size()}.
	 */
	@Test
	public void testSize() {
		assertTrue(0 == queue.size());
		queue.addToRear(tyler);
		queue.addToRear(ginny);
		assertTrue(2 == queue.size());
	}

	/**
	 * This tests the ability of this program to determine
	 * if the list is empty
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(queue.isEmpty());
		queue.addToRear(tyler);
		queue.addToRear(ginny);
		assertFalse(queue.isEmpty());
	}

	/**
	 * This tests the ability of this program to convert 
	 * the list of employees into a string of preferred 
	 * schedules
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#toPreferredString()}.
	 */
	@Test
	public void testToPreferredString() {
		String result1 = ginny.toPreferredString() + "\n" + tyler.toPreferredString() + "\n";
		queue.addToRear(tyler);
		queue.addAtIndex(0, ginny);
		assertEquals(result1, queue.toPreferredString());
	}

	/**
	 * This tests the ability of this program to convert
	 * the list of employees into a string of present 
	 * schedules
	 * Test method for {@link tshuffma.scheduler.queue.EmployeeQueue#toPresentString()}.
	 */
	@Test
	public void testToPresentString() {
		String result1 = ginny.toPresentString() + "\n" + tyler.toPresentString() + "\n";
		queue.addToRear(ginny);
		queue.addToRear(tyler);
		assertEquals(result1, queue.toPresentString());
	}
}