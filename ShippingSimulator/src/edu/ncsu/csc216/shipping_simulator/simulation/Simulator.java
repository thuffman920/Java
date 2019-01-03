package edu.ncsu.csc216.shipping_simulator.simulation;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;
import edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt;
import edu.ncsu.csc216.shipping_simulator.queues.LineOfItems;
import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

public class Simulator {

	/** The minimum number of stations */
	private static final int MIN_NUM_STATIONS = 3;
	/** The maximum number of stations */
	private static final int MAX_NUM_STATIONS = 9;
	/** The number of stations from the user */
	private int numStations;
	/** The number of shipments from the user */
	private int numShipments;
	/** The current number of steps taken */
	private int stepsTaken;
	/**  */
	private Log log;
	/**  */
	private EventCalendar eventCalendar;
	/**  */
	private ItemToShip currentShipment;
	/**  */
	private LineOfItems currentPackage;
	
	/**
	 * 
	 */
	public Simulator(int numStations, int numShipments) {
		if (numStations <= MAX_NUM_STATIONS && numStations >= MIN_NUM_STATIONS 
				|| numShipments < 1) {
			throw new IllegalArgumentException();
		}
		this.numStations = numStations;
		this.numShipments = numShipments;
		log = new Log();
		ShipmentProcessStation[] stations = new ShipmentProcessStation[this.numStations];
		ConveyorBelt conveyorBelt = new ConveyorBelt(this.numShipments, stations);
		eventCalendar = new EventCalendar(stations, conveyorBelt);
	}
	
	public void step() {
		currentShipment = null;
		currentPackage = eventCalendar.nextToBeProcessed();
		currentShipment = currentPackage.processNext();
		stepsTaken++;
	}
	
	/**
	 * The returns the number of steps taken
	 * up this point
	 * @return the number of steps taken
	 */
	public int getStepsTaken() {
		return this.stepsTaken;
	}
	
	/**
	 * The determines the number of steps the 
	 * program has taken. Two for every package,
	 * one for waiting and one for processing
	 * @return the total number of steps
	 */
	public int totalNumberOfSteps() {
		return 2 * log.getNumCompleted();
	}
	
	/**
	 * 
	 */
	public boolean moreSteps() {
		return currentPackage.hasNext();
	}
	
	/**
	 * 
	 */
	public int getCurrentIndex() {
		if (currentShipment == null)
			return -1;
		return currentShipment.getStationIndex();
	}
	
	/** 
	 * This determines the color of the current
	 * package
	 * @return the color of the shipment that
	 * 		is being processed
	 */
	public Color getCurrentShipmentColor() {
		if (currentShipment != null)
			return null;
		return currentPackage.;
	}
	
	/**
	 * @return
	 */
	public boolean itemLeftSimulation() {
		return currentShipment.isWaitingInLineAtStation();
	}
	
	/**
	 * This returns the average wait time
	 * @return the average wait time
	 */
	public double averageWaitTime() {
		return log.averageWaitTime();
	}
	
	/**
	 * This returns the average processing 
	 * time
	 * @return the average processing time
	 */
	public double averageProcessTime() {
		return log.averageProcessTime();
	}
}
