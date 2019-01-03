/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.pkg;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * This creates a test case for the
 * international book shipment
 * @author Tyler Huffman
 */
public class InternationalBookShipmentTest {

	/** This initializes the first international shipment */
	public InternationalBookShipment international1;
	/** This initializes the second international shipment */
	public InternationalBookShipment international2;
	/** This creates an collection of processing stations */
	public ShipmentProcessStation[] station;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		international1 = new InternationalBookShipment(23, 4);
		international2 = new InternationalBookShipment(12, 3);
		station = new ShipmentProcessStation[3];
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.InternationalBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		international1.getInLine(station);
		assertEquals(station[3].processNext(), international1);
	}

	/**
	 * This tests the capability of getting 
	 * the color of the international shipment
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.InternationalBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Color internationalColor = new Color(255, 0, 0);
		assertEquals(internationalColor, international1.getColor());
	}

	/**
	 * This tests the results for the arrival
	 * time for the international shipment
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		assertEquals(23, international1.getArrivalTime());
		assertEquals(12, international2.getArrivalTime());
	}

	/**
	 * This tests the results for getting the
	 * wait time for the international shipment
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		international1 = new InternationalBookShipment(12, 4);
		assertEquals(0, international1.getWaitTime());
	}

	/**
	 * This tests the capability for setting
	 * the wait time for the international shipment
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		international1.setWaitTime(4);
		assertEquals(4, international1.getWaitTime());
		international2.setWaitTime(12);
		assertEquals(12, international2.getWaitTime());
	}

	/**
	 * This tests the ability for retrieving 
	 * the process time for the international
	 * shipment
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		assertEquals(4, international1.getProcessTime());
		assertEquals(3, international2.getProcessTime());
	}

	/**
	 * This tests the getters ability to
	 * retrieve the station index
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getStationIndex()}.
	 */
	@Test
	public void testGetStationIndex() {
		ShipmentProcessStation[] station1 = new ShipmentProcessStation[4];
		international1.getInLine(station1);
		assertEquals(3, international1.getStationIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#isWaitingInLineAtStation()}.
	 */
	@Test
	public void testIsWaitingInLineAtStation() {
		InternationalBookShipment international3 = new InternationalBookShipment(21, 5);
		assertTrue(international3.isWaitingInLineAtStation());
		InternationalBookShipment international4 = new InternationalBookShipment(11, 4);
		assertTrue(international4.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#removeFromWaitingInLine()}.
	 */
	@Test
	public void testRemoveFromWaitingInLine() {
		international1.removeFromWaitingInLine();
		assertFalse(international1.isWaitingInLineAtStation());
		international2.removeFromWaitingInLine();
		assertFalse(international2.isWaitingInLineAtStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setStationIndex(int)}.
	 */
	@Test
	public void testSetStationIndex() {
		international1.setStationIndex(7);
		assertEquals(7, international1.getStationIndex());
		international2.setStationIndex(8);
		assertEquals(8, international2.getStationIndex());
	}

}
