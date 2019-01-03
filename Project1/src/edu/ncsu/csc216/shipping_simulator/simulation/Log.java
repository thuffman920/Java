package edu.ncsu.csc216.shipping_simulator.simulation;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;

/**
 * This creates logged information about
 * the packages that have been successfully
 * processed
 * @author Tyler Huffman
 */
public class Log {

	/** This initializes the number of packages
	 * that are done being processed */
	private int numCompleted;
	/** This initializes the current wait time */
	private int totalWaitTime;
	/** This initializes the current processing time */
	private int totalProcessTime;
	
	/**
	 * This initializes the the log database
	 * for whenever a package is done processing
	 * it is logged
	 */
	public Log() {
		this.numCompleted = 0;
		this.totalProcessTime = 0;
		this.totalWaitTime = 0;
	}
	
	/**
	 * This returns the number of packages 
	 * that have been completely processed
	 * @return the number of packages completed
	 */
	public int getNumCompleted() {
		return this.numCompleted;
	}
	
	/**
	 * This creates a logged record of the item that
	 * has been already processed
	 */
	public void logItem(ItemToShip nextItem) {
		this.numCompleted++;
		this.totalProcessTime += nextItem.getProcessTime();
		this.totalWaitTime += nextItem.getWaitTime();
	}
	
	/**
	 * This calculates the averaged wait time of
	 * all process packages
	 * @return the average wait time of all 
	 * 		processed packages
	 */
	public double averageWaitTime() {
		return this.totalWaitTime / (this.numCompleted * 1.00);
	}
	
	/**
	 * The calculates the average processing
	 * time for all of the packages
	 * @return the average process time for
	 * 		all packages
	 */
	public double averageProcessTime() {
		return this.totalProcessTime / (this.numCompleted * 1.00);
	}
}
