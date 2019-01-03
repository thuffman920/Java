package edu.ncsu.csc216.shipping_simulator.pkg;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * This tests the ability of the Regular
 * shipment without fully checking the 
 * ItemToShip interface
 * @author Tyler Huffman
 */
public class RegularBookShipmentTest {

	/** This initializes the first regular shipment */
	public RegularBookShipment regular1;
	/** The initializes the second regular shipment */
	public RegularBookShipment regular2;
	
	public ShipmentProcessStation[] station;
	
	/**
	 * This sets up the testing program while setting
	 * up everything necessary for the JUnit testing
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		regular1 = new RegularBookShipment(12, 4);
		regular2 = new RegularBookShipment(23, 5);
		station = new ShipmentProcessStation[6];
		station[0] = new ShipmentProcessStation()
	}

	/**
	 * This tests the placing a regular shipment
	 * into the line
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RegularBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		fail("Not yet implemented");
	}

	/**
	 * This tests the ability to retrieve the 
	 * the color of the regular shipment
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RegularBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Color color2 = new Color(0, 0, 255);
		assertEquals(color2, regular1.getColor());
		assertEquals(color2, regular2.getColor());
	}

}
