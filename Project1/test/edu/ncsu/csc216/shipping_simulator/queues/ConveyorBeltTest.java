/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.simulation.Simulator;

/**
 * This tests the capability of the initialization
 * and processes of the Conveyor Belt 
 * @author Tyler Huffman
 */
public class ConveyorBeltTest {

	/** This initializes the first conveyor belt */
	public ConveyorBelt conveyorBelt1;
	/** This initializes the second conveyor belt */
	public ConveyorBelt conveyorBelt2;
	
	public Simulator simulator;
	
	/**
	 * This starts the testing program for the 
	 * conveyor belt
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		simulator = new Simulator(100, 4);
		conveyorBelt1 = new ConveyorBelt(3, );
	}

	/**
	 * This test the size of the conveyor belt
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#size()}.
	 */
	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * This tests if there is any other package left
	 * on the conveyor belt
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		fail("Not yet implemented");
	}

	/**
	 * This 
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#processNext()}.
	 */
	@Test
	public void testProcessNext() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#departTimeNext()}.
	 */
	@Test
	public void testDepartTimeNext() {
		fail("Not yet implemented");
	}

}
