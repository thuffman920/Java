package tshuffma.scheduler.util;

/**
 * This creates a new work day for a certain employee
 * in this business
 * @author Tyler Huffman
 */
public class Day {

	/** This is the day of the week for this work day */
	private String dayOfWeek;
	/** This is the list of job positions for this work day */
	private JobPosition[] jobPosition;
	/** This is the starting time for this work day */
	private double startingTime;
	/** This is the ending time for this work day */
	private double endingTime;
	
	/**
	 * This initializes a new work day for an employee
	 * @param dayOfWeek the day of the week
	 * @param startingTime the starting time of the day
	 * @param endingTime the ending time of the day
	 * @param jobPosition the position of the person's day
	 */
	public Day(String dayOfWeek, double startingTime,
			double endingTime, JobPosition[] jobPosition) {
		this.dayOfWeek = dayOfWeek;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.jobPosition = jobPosition;
	}
	
	/**
	 * This determines the day of the week for this person's work day
	 * @return the day of the week for this person's work day
	 */
	public String getDayOfWeek() {
		return this.dayOfWeek;
	}
	
	/**
	 * This finds and returns the starting time for this person's
	 * work day
	 * @return the starting time for this person's work day
	 */
	public double getStartingTime() {
		return this.startingTime;
	}
	
	/**
	 * This returns the ending time for this person's work day
	 * @return the ending time for this person's work day
	 */
	public double getEndingTime() {
		return this.endingTime;
	}
	
	/**
	 * This finds out the abbreviation for this person's work day
	 * @return the abbreviation of this person's work day
	 */
	public String getJobPositionAbbreviation() {
		String result = "";
		for (int i = 0; i < jobPosition.length - 1; i++)
			result += this.jobPosition[i].getAbbreviation() + ",";
		result += this.jobPosition[jobPosition.length - 1].getAbbreviation();
		return result;
	}
	
	/**
	 * This sets up the starting time for this person's work day
	 * @param startingTime the starting time for this person's work day
	 */
	public void setStartingTime(double startingTime) {
		this.startingTime = startingTime;
	}
	
	/**
	 * This replaces the ending time for this person's work day
	 * @param endingTime the ending time for this person's work day
	 */
	public void setEndingTime(double endingTime) {
		this.endingTime = endingTime;
	}
	
	/**
	 * This converts the military time number into the 12-hour time
	 * period and adds am or pm to it
	 * @param time the military time period
	 * @return the 12-hour time period
	 */
	public String getExactTime(double time) {
		String result = "";
		int hour = (int)(time);
		time = time - hour;
		String period = "a";
		if (hour > 12) {
			hour = hour - 12;
			period = "p";
		} else if (hour == 12)
			period = "p";
		else if (hour == 0) {
			period = "a";
			hour = 12;
		}
		result += hour + ":";
		int minute = (int)(time * 60);
		if (minute < 10)
			return result + "0" + minute + period;
		return  result + minute + period;
	}
	
	/**
	 * This returns the details of this person's work day
	 * @return the details of this person's work day
	 */
	public String toString() {
		return this.getExactTime(startingTime) + "-" + this.getExactTime(endingTime) + " " +
				this.getJobPositionAbbreviation();
	}
}