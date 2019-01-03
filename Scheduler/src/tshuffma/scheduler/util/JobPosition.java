package tshuffma.scheduler.util;

/**
 * This initializes another job position for this company by
 * setting the maximum and minimum number of employees needed
 * for this job
 * @author Tyler Huffman
 */
public class JobPosition {

	/** This is the job position in this company */
	private String jobPosition;
	/** This is the abbreviation of this job position */
	private String jobPositionAbbrev;
	/** This is the maximum number of employees need for this job position */
	private int maxEmployees;
	/** This is the minimum number of employees need for this job position */
	private int minEmployees;
	/** This is the ending time for this job position */
	private double starting;
	/** This is the starting time for this job position */
	private double ending;
	
	/**
	 * This creates a new job position for this company
	 * @param jobPosition the name of this job position
	 * @param jobPositionAbbrev the abbreviation for this job
	 * @param maxEmployees the maximum number of employees
	 * @param minEmployees the minimum number of employees
	 */
	public JobPosition(String jobPosition, String jobPositionAbbrev,
			int maxEmployees, int minEmployees, double starting, double ending) {
		if (maxEmployees < -1)
			throw new IllegalArgumentException("Not the correct number of employees");
		this.jobPosition = jobPosition;
		this.jobPositionAbbrev = jobPositionAbbrev;
		this.maxEmployees = maxEmployees;
		this.minEmployees = minEmployees;
		this.starting = starting;
		this.ending = ending;
	}
	
	/**
	 * This retrieves the name of this job position
	 * @return the name of this job position
	 */
	public String getJobPosition() {
		return this.jobPosition;
	}
	
	/**
	 * This determines the maximum number of employees that is
	 * needed for this job position or is -1 if no maximum number
	 * @return the maximum number of employees
	 */
	public int getMaxEmployees() {
		return this.maxEmployees;
	}
	
	/**
	 * This finds and returns the minimum number of employees
	 * needed for this job position
	 * @return the minimum number of employees
	 */
	public int getMinEmployees() {
		return this.minEmployees;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getStarting() {
		return this.starting;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getEnding() {
		return this.ending;
	}
	
	/**
	 * This determines the abbreviation for this job position
	 * @return the abbreviation for this job position
	 */
	public String getAbbreviation() {
		return this.jobPositionAbbrev;
	}
	
	/**
	 * This changes the maximum number of employees needed for
	 * this job position
	 * @param maxEmployees the maximum number of employees
	 */
	public void setMaxEmployees(int maxEmployees) {
		this.maxEmployees = maxEmployees;
	}
	
	/**
	 * This changes the minimum number of employees needed for
	 * this job position
	 * @param minEmployees the minimum number of employees
	 */
	public void setMinEmployees(int minEmployees) {
		this.minEmployees = minEmployees;
	}
	
	/**
	 * This changes the abbreviation of this job position
	 * @param jobPositionAbbrev the abbreviation for this job position
	 */
	public void setAbbreviation(String jobPositionAbbrev) {
		this.jobPositionAbbrev = jobPositionAbbrev;
	}
	
	/**
	 * 
	 * @param starting
	 */
	public void setStarting(double starting) {
		this.starting = starting;
	}
	
	/**
	 * 
	 * @param ending
	 */
	public void setEnding(double ending) {
		this.ending = ending;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toString() {
		return "" + this.jobPosition + "\t"	+ this.jobPositionAbbrev + "\t" +  + this.starting + "-" + 
				this.ending + "\t" + this.minEmployees + "\t" + this.maxEmployees;
	}
}