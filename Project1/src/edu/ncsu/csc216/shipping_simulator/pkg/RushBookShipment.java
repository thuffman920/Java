package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;
/**
 * This creates the rush shipment
 * @author Tyler Huffman
 */
public class RushBookShipment extends ItemToShip {

	/** This initializes the black color 
	 * for the rush package */
	private Color color;
	
	/**
	 * This creates a rush shipment that is 
	 * ready for processing and shipping
	 * @param arrivalTime the arrival time of 
	 * 		the package
	 * @param processTime the process time of
	 * 		the package
	 */
	public RushBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		color = new Color(0, 0, 0);
	}
	
	/**
	 * This creates a rush shipment in the shortest line
	 * even the index is at 0
	 */
	public void getInLine(ShipmentProcessStation[] processStation) {
		int minimum = processStation[0].size();
		int index = 0;
		for (int i = 1; i < processStation.length; i++) {
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
	 * This returns the color of the rush
	 * shipment in the gui
	 * @return the color of the rush package
	 */
	public Color getColor() {
		return this.color;
	}
}
