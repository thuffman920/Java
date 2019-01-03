package tshuffma.scheduler.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tshuffma.scheduler.util.JobPosition;

/**
 * This sets up a reader, which will take a text file and
 * converts it into a list of job positions 
 * @author Tyler Huffman
 */
public class PositionReader {

	/** This is the file that houses the list of saved job positions */
	private File databaseJobs;
	/** This is the name of the file that stores the saved job positions */
	private String file = "positions.txt";
	/** This is the list of saved job positions, which is read in from the file */
	private ArrayList<JobPosition> jobs;
	
	/**
	 * This initializes the reader, for which houses the
	 * list of job positions
	 */
	public PositionReader() {
		databaseJobs = new File(file);
		jobs = new ArrayList<JobPosition>();
		Scanner fullTextScanner = null;
		try {
			fullTextScanner = new Scanner(databaseJobs);
			this.fullJobs(fullTextScanner);
			fullTextScanner.close();
		} catch (FileNotFoundException g) {
			
		}
	}
	
	/**
	 * This searches through the list of job positions for 
	 * a certain job that equals the inputted job position abbreviation
	 * @param abbreviation the job position abbreviation
	 * @return the job position associated with the abbreviation
	 */
	public JobPosition findJob(String abbreviation) {
		for (int i = 0; i < jobs.size(); i++)
			if (jobs.get(i).getAbbreviation().equals(abbreviation))
				return jobs.get(i);
		return null;
	}
	
	/**
	 * This reads the file of job positions and converts
	 * them back into a list of job positions
	 * @param fullTextScanner the file reader
	 */
	private void fullJobs(Scanner fullTextScanner) {
		while (fullTextScanner.hasNextLine()) {
			String line = fullTextScanner.nextLine();
			String name = line.substring(0, line.indexOf("\t"));
			line = line.substring(line.indexOf("\t") + 1);
			String abbr = line.substring(0, line.indexOf("\t"));
			line = line.substring(line.indexOf("\t") + 1);
			double starting = Double.parseDouble(line.substring(0, line.indexOf("-")));
			double ending = Double.parseDouble(line.substring(line.indexOf("-") + 1, line.indexOf("\t")));
			line = line.substring(line.indexOf("\t") + 1);
			int min = Integer.parseInt(line.substring(0, line.indexOf("\t")));
			int max = Integer.parseInt(line.substring(line.indexOf("\t") + 1));
			jobs.add(new JobPosition(name, abbr, max, min, starting, ending));
		}
	}
	
	/**
	 * This adds a new job to the list of current job positions
	 * @param job the new job that will be added
	 */
	public void addJob(JobPosition job) {
		for (int i = 0; i < jobs.size(); i++)
			if (jobs.get(i).getAbbreviation().equalsIgnoreCase(job.getAbbreviation()))
				return;
		jobs.add(job);
	}
	
	/**
	 * This takes the list of job positions and writes them
	 * back to a text file for later processing
	 */
	public void writeToFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < jobs.size() - 1; i++) {
				writer.write(jobs.get(i).toString());
				writer.newLine();
			}
			writer.write(jobs.get(jobs.size() - 1).toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int findJobIndex(String abbreviation) {
		for (int i = 0; i < jobs.size(); i++)
			if (jobs.get(i).getAbbreviation().equals(abbreviation))
				return i;
		return -1;
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return jobs.size();
	}
}