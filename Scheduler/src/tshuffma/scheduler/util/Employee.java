package tshuffma.scheduler.util;

/**
 * This creates a new employee for this company
 * by adding the employees preferred and present schedule 
 * @author Tyler Huffman
 */
public class Employee {

	/** This is the name of the employee */
	private String name;
	/** This is the employee's preferred schedule */
	private Schedule preferredSchedule;
	/** This is the employee's present schedule */
	private Schedule presentSchedule; 
	/** This tells if the employee is full time or not */
	private boolean isFullTime = true;
	
	/**
	 * This initializes a new employee for this company
	 * @param name the name of the employee
	 * @param preferredSchedule the employee's preferred schedule
	 * @param presentSchedule the employee's present schedule
	 */
	public Employee(String name, Schedule preferredSchedule,
			Schedule presentSchedule, String time) {
		this.name = name;
		this.preferredSchedule = preferredSchedule;
		this.presentSchedule = presentSchedule;
		if (time.equals("p"))
			isFullTime = false;
	}
	
	/**
	 * This determines the name of the employee
	 * @return the name of the employee
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This determines the preferred schedules for this employee
	 * @return the preferred schedule for this employee
	 */
	public Schedule getPreferredSchedule() {
		return this.preferredSchedule;
	}
	
	/**
	 * This takes in a new preferred schedule and transforms the
	 * current preferred schedule to it
	 * @param preferredSchedule the new preferred schedule
	 */
	public void setPreferredSchedule(Schedule preferredSchedule) {
		this.preferredSchedule = preferredSchedule;
	}
	
	/**
	 * This finds and returns the present schedule for this employee
	 * @return the present schedule for this employee
	 */
	public Schedule getPresentSchedule() {
		return this.presentSchedule;
	}
	
	/**
	 * This takes a new schedule and changes the present schedule
	 * for this employee
	 * @param presentSchedule the new schedule for this employee
	 */
	public void setPresentSchedule(Schedule presentSchedule) {
		this.presentSchedule = presentSchedule;
	}
	
	/**
	 * Transform the full name of an employee into a more formal
	 * manner ([last name], [first name])
	 * @return the new formal manner
	 */
	public String toFormalName() {
		String first = name.substring(0, name.lastIndexOf(" "));
		String last = name.substring(name.lastIndexOf(" ") + 1, name.length());
		return last + ", " + first;
	}
	
	/**
	 * Transforms the preferred schedule of this employee
	 * into a string representation
	 * @return the string for the preferred schedule
	 */
	public String toPreferredString() {
		String time = "f";
		if (!isFullTime)
			time = "p";
		return this.toFormalName() + "\t" + time + "\t" + preferredSchedule.toString();
	}
	
	/**
	 * Transforms the present schedule of this employee into
	 * a string representation
	 * @return the string for the present schedule
	 */
	public String toPresentString() {
		String present = presentSchedule.toString();
		String[] parts = present.split("\t");
		present = "";
		for (int i = 0; i < 7; i++) {
			String first = parts[i].substring(0, parts[i].indexOf("-"));
			String second = parts[i].substring(parts[i].indexOf("-") + 1, parts[i].indexOf(" "));
			if (first.equals(second))
				parts[i] = "Off";
			present += "\t" + parts[i]; 
		}
		return this.toFormalName() + present;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isFullTime() {
		return isFullTime;
	}
	
	/**
	 * 
	 * @param time
	 */
	public void setFullTime(boolean time) {
		isFullTime = time;
	}
}