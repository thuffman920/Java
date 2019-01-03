package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * This creates a package which will be
 * processed and shipped
 * @author Tyler Huffman
 */
public abstract class ItemToShip {

	/** This initializes the arrival time of a package */
	private int arrivalTime;
	/** This initializes the wait time for the package */
	private int waitTime;
	/** The initializes the process time for the package */
	private int processTime;
	/** The initializes the station index */
	private int stationIndex;
	/** This determines if the package is waiting to
	 *  be processed */
	private boolean waitingProcessing;
	
	/**
	 * This creates the package that is need to be 
	 * processed and shipped
	 * @throws IllegalArgumentException is arrival time
	 * 		and/or processing time is negative
	 */
	public ItemToShip(int arrivalTime, int processTime) {
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException();
		}
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		waitingProcessing = true;
	}
	
	/**
	 * This returns the arrival time of the package
	 * that is waiting to be shipped
	 * @return the arrival Time of the package
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	/**
	 * This returns the wait time of the
	 * package
	 * @return the wait time of the package
	 */
	public int getWaitTime() {
		return this.waitTime;
	}
	
	/**
	 * This sets the wait time of the package
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	/**
	 * This returns the processing time for the
	 * package
	 * @return the process time of the package
	 */
	public int getProcessTime() {
		return this.processTime;
	}
	
	/**
	 * This finds the index of the station where 
	 * the package is located
	 * @return the station index of the package
	 */
	public int getStationIndex() {
		return this.stationIndex;
	}
	
	/**
	 * It determines if the package is waiting
	 * in the line to be processed
	 * @return true if waiting in line
	 */
	public boolean isWaitingInLineAtStation() {
		return this.waitingProcessing;
	}
	
	/**
	 * This removes the Item which needs
	 * to be shipped from the waiting line
	 */
	public void removeFromWaitingInLine() {
		this.waitingProcessing = false;
	}
	
	/**
	 * This sets the value of the station index
	 */
	protected void setStationIndex(int stationIndex) {
		this.stationIndex = stationIndex;
	}
	
	/**
	 * This places the package into the processing
	 * station
	 */
	abstract void getInLine(ShipmentProcessStation[] processStation);
	
	/**
	 * This finds out the color of the package
	 * in order to be determined which line to be placed
	 * in
	 */
	abstract Color getColor();
}