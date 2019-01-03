package tshuffma.scheduler.util;

import java.util.ArrayList;

/**
 * This creates a specific schedule for a certain person
 * in this company
 * @author Tyler Huffman
 */
public class Schedule {

	/** This initializes the maximum number of days in a week */
	private static final int NUM_OF_DAYS = 7;
	/** This initializes a list of scheduled days per person */
	private ArrayList<Day> listOfDays;
	/** This creates the names of the days of the week */
	private String[] daysOfWeek = {"Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday"};
	
	/**
	 * This initializes the list of days for an employee
	 * @param days the array of days for an employee
	 */
	public Schedule(Day[] days) {
		this.listOfDays = new ArrayList<Day>();
		for (int i = 0; i < days.length; i++) {
			for (int j = 0; j < NUM_OF_DAYS; j++) {
				if (days[i].getDayOfWeek().equalsIgnoreCase(daysOfWeek[j])) {
					this.listOfDays.add(days[i]);
					break;
				}
			}
		}
	}
	
	/**
	 * This determines the particular work day for this day
	 * for this employee
	 * @param index the particular day which we are looking for
	 * @return the schedule made for that work day
	 * @throws IndexOutOfBoundsException if the index is greater than or equal to 7
	 */
	public Day dayAt(int index) {
		if (index >= NUM_OF_DAYS)
			throw new IndexOutOfBoundsException("Index is greater than 7");
		return listOfDays.get(index);
	}
	
	/**
	 * This determines the list of the work days for this 
	 * particular employee for this company
	 * @return the list of work days for this person
	 */
	public ArrayList<Day> getListOfDays() {
		return listOfDays;
	}
	
	/**
	 * This puts the employee's schedule into a string form
	 * @return the full schedule for this employee
	 */
	public String toString() {
		String output = "" + listOfDays.get(0).toString();
		for (int i = 1; i < listOfDays.size(); i++)
			output += "\t" + listOfDays.get(i).toString();
		return output;
	}
}