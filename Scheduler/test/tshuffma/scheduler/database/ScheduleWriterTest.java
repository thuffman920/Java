package tshuffma.scheduler.database;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ability of this program to export
 * the present schedules for the employees to a file with a
 * certain file type
 * @author Tyler Huffman
 */
public class ScheduleWriterTest {

	/** This is the list of jobs in this company */
	public PositionReader jobs = new PositionReader();
	/** This is the reader of the previous schedule */
	public ScheduleReader reader = new ScheduleReader(jobs);
	/** This is the writer for the text file */
	public ScheduleWriter textWriter;
	/** This is the writer for the csv file */
	public ScheduleWriter csvWriter;
	
	/**
	 * This sets up the more important aspects before
	 * this programs tests the ability of the Schedule
	 * writer
	 * @throws java.lang.Exception if an error occurs
	 */
	@Before
	public void setUp() throws Exception {
		textWriter = new ScheduleWriter(".txt", reader.getEmployeeList(), reader.getPattern(), reader.getFullTime(), reader.getLunch()
				, reader.getHours(), reader.getStartDay());
		csvWriter = new ScheduleWriter(".csv", reader.getEmployeeList(), reader.getPattern(), reader.getFullTime(), reader.getLunch()
				, reader.getHours(), reader.getStartDay());
	}

	/**
	 * This tests the ability of this program to export the present
	 * schedule to a file of a certain file type
	 * Test method for {@link tshuffma.scheduler.database.ScheduleWriter#writeToFile()}.
	 */
	@Test
	public void testWriteToFile() {
		try {
			textWriter.writeToFile();
			String actual1 = "";
			Scanner reading = new Scanner(new File("present.txt"));
			while (reading.hasNextLine()) {
				String line = reading.nextLine();
				if (!line.equals(""))
					actual1 += line + "\n";
			}
			String expected1 = "Huffman, Tyler\t2:00p-10:00p SL\t2:00p-10:00p SL\t2:00p-10:00p SL\tOff\tOff\t"
					+ "2:00p-10:00p SL\t2:00p-10:00p SL\nReese, Ginny\t2:00p-10:00p sub\tOff\t2:00p-10:00p SB\t"
					+ "2:00p-10:00p sub\t2:00p-10:00p sub\t2:00p-10:00p sub\tOff\nDoe, Gabe\tOff\tOff\tOff\tOff\t"
					+ "Off\tOff\tOff\n";
			assertEquals(expected1, actual1);
			csvWriter.writeToFile();
			String actual2 = "";
			reading = new Scanner(new File("Schedule.csv"));
			while (reading.hasNextLine())
				actual2 += reading.nextLine() + "\n";
			String expected2 = "Huffman,Tyler,2:00p-10:00p SL,2:00p-10:00p SL,2:00p-10:00p SL,Off,Off,"
					+ "2:00p-10:00p SL,2:00p-10:00p SL,\nReese,Ginny,2:00p-10:00p sub,Off,2:00p-10:00p SB"
					+ ",2:00p-10:00p sub,2:00p-10:00p sub,2:00p-10:00p sub,Off,\nDoe,Gabe,Off,Off,Off,Off,"
					+ "Off,Off,Off,\n";
			assertEquals(expected2, actual2);
			reading.close();
			ScheduleWriter xlsWriter = new ScheduleWriter(".xls", reader.getEmployeeList(), reader.getPattern(), reader.getFullTime(), reader.getLunch()
					, reader.getHours(), reader.getStartDay());
			xlsWriter.writeToFile();
			ScheduleWriter xlsxWriter = new ScheduleWriter(".xlsx", reader.getEmployeeList(), reader.getPattern(), reader.getFullTime(), reader.getLunch()
					, reader.getHours(), reader.getStartDay());
			xlsxWriter.writeToFile();
		} catch (FileNotFoundException f) {
			
		}
		try {
			ScheduleWriter cantDo = new ScheduleWriter(".dot", reader.getEmployeeList(), reader.getPattern(), reader.getFullTime(), reader.getLunch()
					, reader.getHours(), reader.getStartDay());
			cantDo.writeToFile();
		} catch(IllegalArgumentException f) {
			assertEquals("Can't write in this form", f.getMessage());
		}
	}
}