package edu.ncsu.csc216.shipping_simulator.queues;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;
import edu.ncsu.csc216.shipping_simulator.simulation.Log;
/**
 * This creates the 
 * @author Tyler Huffman
 */
public class ShipmentProcessStation implements LineOfItems {

	/** This initializes the time when the processing
	 * station will be available */
	private int timeWhenAvailable;
	/** This initializes the line of shipments
	 * awaiting to be processed */
	private ShipmentQueue line;
	/** This initializes the log of all processed
	 * packages */
	private Log log;
	
	/**
	 * This creates a process Station for
	 * the conveyor belt and the shipment queue
	 * @param log the log information of the 
	 * 		fully processed packages
	 */
	public ShipmentProcessStation(Log log) {
		this.log = log;
		line = new ShipmentQueue();
		timeWhenAvailable = 0;
	}
	
	/**
	 * This is the length of the conveyor belt
	 * @return the size of the conveyor belt
	 */
	public int size() {
		return line.size();
	}
	
	/**
	 * This finds the next Item to be processed in
	 * line and is removed from the conveyor belt
	 * @return the next item needing to be processed
	 */
	public ItemToShip processNext() {
		ItemToShip nextItem = processNext();
		log.logItem(nextItem);
		return nextItem;
	}
	
	/**
	 * This checks to see if anything is left
	 * on the conveyor belt
	 * @return true if there is more packages
	 */
	public boolean hasNext() {
		if (line == null)
			return false;
		return true;
	}
	
	/**
	 * This returns the time that the next package
	 * will be processed
	 * @return the next times for processing
	 */
	public int departTimeNext() {
		if (line.front() == null)
			return Integer.MAX_VALUE;
		return departTimeNext();
	}
	
	/**
	 * This adds an item to the line of items
	 * while modifying the time when available
	 */
	public void addItemToLine(ItemToShip itemToShip) {
		line.add(itemToShip);
		timeWhenAvailable += departTimeNext();
	}
}