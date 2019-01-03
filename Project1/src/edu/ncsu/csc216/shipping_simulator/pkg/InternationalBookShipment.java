package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;
/**
 * This creates the international Shipment
 * @author Tyler Huffman
 */
public class InternationalBookShipment extends ItemToShip {

	/** This initializes the blue color 
	 * for the international shipment */
	private Color color;
	
	/**
	 * This creates the international book shipment object
	 * @param arrivalTime the arrival time of the package
	 * @param processTime the process time of the package
	 */
	public InternationalBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		color = new Color(255, 0, 0);
	}
	
	/**
	 * This places the international book shipment into
	 * the line of other shipments
	 * @param processStation the collection of the packages
	 * 			waiting in this line
	 */
	public void getInLine(ShipmentProcessStation[] processStation) {
		int minimum = 0;
		int min = 1;
		int index = 0;
		if (processStation.length >= 3 && processStation.length < 5) {
			index = processStation.length - 1;
		} else {
			if (processStation.length >= 5 && processStation.length < 9) {
				min++;
			}
			else if (processStation.length == 9) {
				min += 2;
			}
			minimum = processStation[min].size();
			for (int i = processStation.length - min; i < processStation.length; i++) {
				if (Math.min(minimum, processStation[i].size()) == 
						processStation[i].size()) {
					if (minimum != processStation[i].size()) {
						minimum = processStation[i].size();
						index = i;
					}
				}
			}
		}
		processStation[index].addItemToLine(this);
	}
	
	/**
	 * This returns the color of the international
	 * shipment to the GUI
	 * @return the color of the international shipment
	 */
	public Color getColor() {
		return this.color;
	}
}
