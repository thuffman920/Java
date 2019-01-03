/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.pkg;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the initialization of
 * rush shipment without the rechecking
 * of ItemToShip interface
 * @author Tyler
 *
 */
public class RushBookShipmentTest {

	/** This initializes the first rush shipment */
	public RushBookShipment rush1;
	/** This initializes the second rush shipment */
	public RushBookShipment rush2;
	
	/**
	 * This sets up the program to test and
	 * initialize the important parts to be used later
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		rush1 = new RushBookShipment(12, 5);
		rush2 = new RushBookShipment(23, 4);
	}

	/**
	 * This tests the ability to add the rush
	 * shipment to the process station
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RushBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		fail("Not yet implemented");
	}

	/**
	 * This finds out if the color is 
	 * returned correctly for the rush package
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RushBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Color color3 = new Color(0, 0, 0);
		assertEquals(color3, rush1.getColor());
		assertEquals(color3, rush2.getColor());
	}
}
