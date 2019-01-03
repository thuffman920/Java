package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;
/**
 * This creates the Regular shipping 
 * package
 * @author Tyler Huffman
 */
public class RegularBookShipment extends ItemToShip {

	/** This initializes the blue color 
	 * for the regular shipment */
	private Color color;
	
	/**
	 * This creates a regular package that is 
	 * ready for processing and shipping
	 * @param arrivalTime the arrival time of 
	 * 		the package
	 * @param processTime the process time of
	 * 		the package
	 */
	public RegularBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		color = new Color(0, 0, 255);
	}
	
	/**
	 * This places the regular shipment into 
	 * a process station, except not in index
	 * zero
	 */
	public void getInLine(ShipmentProcessStation[] processStation) {
		int minimum = processStation[1].size();
		int index = 0;
		for (int i = 2; i < processStation.length; i++) {
			if (Math.min(minimum, processStation[i].size()) == 
					processStation[i].size()) {
				if (minimum != processStation[i].size()) {
					minimum = processStation[i].size();
					index = i;
				}
			}
		}
		processStation[index].addItemToLine(this);
	}
	
	/**
	 * This returns the color of the regular
	 * shipment to the gui
	 */
	public Color getColor() {
		return this.color;
	}
}
