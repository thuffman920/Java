package edu.ncsu.csc216.shipping_simulator.queues;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;

/**
 * This creates a conveyor and places the 
 * packages on it in the order in which they arrived
 * @author Tyler Huffman
 */
public class ConveyorBelt implements LineOfItems {

	/** The number of items on the queue */
	private int queueFromFactory;
	/** The station for which this conveyor belt
	 * is with */
	private ShipmentProcessStation[] station;
	
	/**
	 * This creates the conveyor belt for
	 * the processing station
	 * @param queueFromFactory the number of packages
	 * @param station the processing station
	 */
	public ConveyorBelt(int queueFromFactory, ShipmentProcessStation[] station) {
		this.queueFromFactory = queueFromFactory;
		this.station = station;
	}
	
	/**
	 * This determines the length of the conveyor
	 * belt
	 * @return the length of the conveyor belt
	 */
	public int size() {
		return station.length;
	}
	
	/**
	 * This determines if any item is still on
	 * the conveyor betlt
	 * @return true if there a package is on the
	 * 		conveyor belt
	 */
	public boolean hasNext() {
		if (station == null)
			return false;
		return true;
	}
	
	/**
	 * This finds the next package that is waiting
	 * to be processed
	 * @return the next package on the conveyor belt
	 */
	public ItemToShip processNext() {
		ItemToShip nextItem = station[0].processNext();
		for (int i = 0; i < station.length - 1; i++) {
			ShipmentProcessStation temp = station[i + 1];
			station[i] = temp;
		}
		return nextItem;
	}
	
	/**
	 * This determines the departure time of the 
	 * next package that is on the conveyor belt
	 * @return the departure time
	 */
	public int departTimeNext() {
		if (station != null) {
			return Integer.MAX_VALUE;
		}
		return departTimeNext();
	}
}
