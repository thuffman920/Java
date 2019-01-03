package tshuffma.scheduler.database;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import tshuffma.scheduler.queue.EmployeeQueue;
import tshuffma.scheduler.util.Day;
import tshuffma.scheduler.util.Employee;
import tshuffma.scheduler.util.JobPosition;
import tshuffma.scheduler.util.Schedule;

/**
 * This reads two files simultaneously and creates the preferred
 * and present schedules, which creates the list of employees
 * @author Tyler Huffman
 */
public class ScheduleReader {

	/** This is the list of employees for this company */
	private EmployeeQueue employees;
	/** This is the next line in the text files */
	private String line;
	/** This creates the schedule week for the present schedule */
	private String[] pattern = new String[7];
	/** This is the name of the text file for the preferred schedules */
	private String textPreferred = "preferred.txt";
	/** This is the name of the text file for the present schedules */
	private String textPresent = "present.txt";
	/** This is the reader of the jobs for this company */
	private PositionReader jobs;
	/** This creates the names of the days of the week */
	private String[] daysOfWeek = {"Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday"};
	/**  */
	private int fullTime = 0;
	/**  */
	private double lunch = 0.0;
	/**  */
	private int hours = 0;
	/**  */
	private int startDay = 0;
	
	/**
	 * This initializes schedule reader by taking in the list
	 * of jobs and the starting day for this schedule
	 * @param jobs the reader of the jobs for this company
	 * @param startDay the starting day for this schedule
	 */
	public ScheduleReader(PositionReader jobs) {
		this.jobs = jobs;
		try {
			Scanner fullPreferred = new Scanner(new File(textPreferred));
			Scanner fullPresent = new Scanner(new File(textPresent));
			employees = new EmployeeQueue();
			this.fullTextProcessor(fullPreferred, fullPresent);
		} catch (IOException g) {
			
		}
	}
	
	/**
	 * This takes in two scanners, which are reading the 
	 * preferred and present schedules to set up the list of
	 * employees in this company
	 * @param console the scanner for the preferred schedule
	 * @param console2 the scanner for the present schedule
	 * @throws IOException 
	 */
	private void fullTextProcessor(Scanner console, Scanner console2) {
		String first = console.nextLine();
		startDay = Integer.parseInt(first.substring(0, first.indexOf("\t")));
		for (int i = startDay; i < startDay + 7; i++)
			pattern[i - startDay] = daysOfWeek[i % 7];
		first = first.substring(first.indexOf("\t") + 1);
		fullTime = Integer.parseInt(first.substring(0, first.indexOf("\t")));
		first = first.substring(first.indexOf("\t") + 1);
		lunch = Double.parseDouble(first.substring(0, first.indexOf("\t")));
		hours = Integer.parseInt(first.substring(first.indexOf("\t") + 1));
		while (console.hasNextLine() && console2.hasNextLine()) {
			line = console.nextLine();
			String name = line.substring(line.indexOf(", ") + 2, line.indexOf("\t")) + " " + line.substring(0, line.indexOf(","));
			line = line.substring(line.indexOf("\t") + 1);
			String time = line.substring(0, line.indexOf("\t"));
			line = line.substring(line.indexOf("\t") + 1);
			Schedule preferredSchedule = lineTextProcessor();
			line = console2.nextLine();
			line = line.substring(line.indexOf("\t") + 1);
			Schedule presentSchedule = lineTextProcessor();
			employees.addToRear(new Employee(name, preferredSchedule, presentSchedule, time));
		}
	}
	
	/**
	 * This takes a new line from the schedule files and transforms
	 * it into a schedule object
	 * @return the schedule that is created from the new line
	 */
	private Schedule lineTextProcessor() {
		String[] list = line.split("\t");
		Day[] array = new Day[7];
		for (int i = 0; i < list.length; i++) {
			double startingTime = 0.0;
			double endingTime = 0.0;
			String[] position = {""};
			JobPosition[] job = {new JobPosition("", "", 0, 0, 0.0, 0.0)};
			if (!list[i].equals("Off")) {
				startingTime = Integer.parseInt(list[i].substring(0, 
						list[i].indexOf(":"))) + Integer.parseInt(
						list[i].substring(list[i].indexOf(":") + 1, list[i].indexOf("-") - 1)) / 60.0;
				if (list[i].charAt(list[i].indexOf("-") - 1) == 'p' && startingTime < 12) 
					startingTime += 12;
				else if (list[i].charAt(list[i].indexOf("-") - 1) == 'a' && startingTime == 12)
					startingTime = 0;
				list[i] = list[i].substring(list[i].indexOf("-") + 1, list[i].length());
				endingTime = Integer.parseInt(list[i].substring(0, 
						list[i].indexOf(":"))) + Integer.parseInt(
						list[i].substring(list[i].indexOf(":") + 1, list[i].indexOf(" ") - 1)) / 60.0;
				list[i] = list[i].substring(list[i].indexOf(" ") - 1, list[i].length());
				if (list[i].charAt(0) == 'p')
					endingTime += 12;
				position = list[i].substring(2, list[i].length()).split(",");
				job = new JobPosition[position.length];
				for (int j = 0; j < position.length; j++)
					job[j] = jobs.findJob(position[j]);
			}
			array[i] = new Day(pattern[i], startingTime, endingTime, job);
		}
		return new Schedule(array);
	}
	
	/**
	 * This retrieves the employees that have been assigned
	 * a present and preferred schedules
	 * @return the list of employees that are in this company
	 */
	public EmployeeQueue getEmployeeList() {
		return employees;
	}
	
	/**
	 * This holds the order of the work schedule 
	 * @return the pattern of the work schedule
	 */
	public String[] getPattern() {
		return pattern;
	}
	
	/**
	 * This determines the minimum number of hours for full time
	 * @return the number of hours for full time
	 */
	public int getFullTime() {
		return fullTime;
	}
	
	/**
	 * This determines the length of lunch time
	 * @return the length of lunch time
	 */
	public double getLunch() {
		return lunch;
	}
	
	/**
	 * This is the number of hours that have to pass in order
	 * for the employee to be able to have a lunch break
	 * @return the numbers need to pass for lunch
	 */
	public int getHours() {
		return hours;
	}
	
	public int getStartDay() {
		return this.startDay;
	}
}