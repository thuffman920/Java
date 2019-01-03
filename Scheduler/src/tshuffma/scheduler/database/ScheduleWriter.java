package tshuffma.scheduler.database;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

import tshuffma.scheduler.queue.EmployeeQueue;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Tyler Huffman
 */
public class ScheduleWriter {

	/** This is the file extension for writing the schedule */
	private String fileType;
	/** This is the list of employees in this company */
	private EmployeeQueue employees;
	/** This is the week schedule pattern */
	private String[] pattern = new String[7];
	/** The days of the week in numerical order */
	private String[] dayOfWeeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday"};
	/**  */
	private int fullTime;
	/**  */
	private double lunch;
	/**  */
	private int hours;
	
	private int startDay;
	
	/**
	 * 
	 * @param fileType
	 * @param employees
	 */
	public ScheduleWriter(String fileType, EmployeeQueue employees, String[] pattern, int fullTime,
			double lunch, int hours, int startDay) {
		this.fileType = fileType;
		this.employees = employees;
		int day = 0;
		for (int i = 0; i < 7; i++) {
			if (pattern[0].equals(dayOfWeeks[i])) {
				day = i + 1;
				break;
			}
		}
		for (int i = 0; i < 7; i++) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			Calendar date = Calendar.getInstance();
			int diff = (day + i) - date.get(Calendar.DAY_OF_WEEK);
			if (!(diff > 0))
				diff += 7;
			date.add(Calendar.DAY_OF_MONTH, diff);
			this.pattern[i] = pattern[i].substring(0,  3) + ", " + df.format(date.getTime());
		}
		this.startDay = startDay;
		this.fullTime = fullTime;
		this.lunch = lunch;
		this.hours = hours;
	}
	
	/**
	 * This exports the	present schedules to the new file, 
	 * based upon the file type
	 * @throws java.lang.IllegalArgumentException if the file type is not known
	 */
	public void writeToFile() {
		if (fileType.equals(".xls"))
			this.writeXLS();
		else if (fileType.equals(".xlsx"))
			this.writeXLSX();
		else if (fileType.equals(".csv"))
			this.writeCSV();
		else if (!fileType.equals(".txt"))
			throw new IllegalArgumentException("Can't write in this form");
		this.writeTXT();
	}
	
	/**
	 * This writes the present schedules which is based
	 * upon the file type .xls, writing to the a excel chart
	 */
	private void writeXLS() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Work Schedule");
		Object[][] data = {
				{"Last", "First", pattern[0], "", "", pattern[1], "", "", pattern[2], "", "",
					pattern[3], "", "", pattern[4], "", "", pattern[5], "", "", pattern[6], "", ""}, 
				{"", "", "From", "To", "Job", "From", "To", "Job", "From", 
						"To", "Job", "From", "To", "Job", "From", "To", "Job", "From", "To", "Job", "From", "To", "Job"}
		};
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		int rowi = 0;
		for (Object[] aBook : data) {
			Row row = sheet.createRow(rowi++);
			int columnj = 0;
			for (Object field : aBook) {
				Cell cell = row.createCell(columnj++);
				cell.setCellValue((String) field);
				cell.setCellStyle(style);
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
		sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
		for (int i = 2; i < 22; i += 3)
			sheet.addMergedRegion(new CellRangeAddress(0,0,i,i + 2));
		for (int i = 0; i < employees.size(); i++) {
			Row row = sheet.createRow(i + 2);
			String[] parts = employees.getEmployee(i).toPresentString().split("[- \t,\n]");
			int offset = 0;
			for (int j = 0; j < parts.length; j++) {
				if (parts[j].equalsIgnoreCase("Off")) {
					Cell cell = row.createCell(j + offset);
					cell.setCellValue("Off");
					row.createCell(j + offset + 1).setCellValue("");
					row.createCell(j + offset + 2).setCellValue("");
					sheet.addMergedRegion(new CellRangeAddress(i + 2, i + 2, j + offset, j + offset + 2));
					cell.setCellStyle(style);
					offset += 2;
				} else if (!parts[j].equals("")) {
					Cell cell = row.createCell(j + offset);
					cell.setCellValue((String) parts[j]);
					cell.setCellStyle(style);
				} else 
					offset -= 1;
			}
		}
		for (int i = 0; i < 23; i++)
			sheet.autoSizeColumn(i);
		try{
			FileOutputStream outputStream = new FileOutputStream("Schedule" + fileType);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This writes the present schedules which is based
	 * upon the file type .xlsx, writing to the a newly formatted
	 * excel chart
	 */
	private void writeXLSX() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Work Schedule");
		Object[][] data = {
				{"Last", "First", pattern[0], "", "", pattern[1], "", "", pattern[2], "", "",
					pattern[3], "", "", pattern[4], "", "", pattern[5], "", "", pattern[6], "", ""}, 
				{"", "", "From", "To", "Job", "From", "To", "Job", "From", 
						"To", "Job", "From", "To", "Job", "From", "To", "Job", "From", "To", "Job", "From", "To", "Job"}
		};
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		int rowi = 0;
		for (Object[] aBook : data) {
			Row row = sheet.createRow(rowi++);
			int columnj = 0;
			for (Object field : aBook) {
				Cell cell = row.createCell(columnj++);
				cell.setCellValue((String) field);
				cell.setCellStyle(style);
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
		sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
		for (int i = 2; i < 22; i += 3)
			sheet.addMergedRegion(new CellRangeAddress(0,0,i,i + 2));
		for (int i = 0; i < employees.size(); i++) {
			Row row = sheet.createRow(i + 2);
			String[] parts = employees.getEmployee(i).toPresentString().split("[- \t,\n]");
			int offset = 0;
			for (int j = 0; j < parts.length; j++) {
				if (parts[j].equalsIgnoreCase("Off")) {
					Cell cell = row.createCell(j + offset);
					cell.setCellValue("Off");
					row.createCell(j + offset + 1).setCellValue("");
					row.createCell(j + offset + 2).setCellValue("");
					sheet.addMergedRegion(new CellRangeAddress(i + 2, i + 2, j + offset, j + offset + 2));
					cell.setCellStyle(style);
					offset += 2;
				} else if (!parts[j].equals("")) {
					Cell cell = row.createCell(j + offset);
					cell.setCellValue((String) parts[j]);
					cell.setCellStyle(style);
				} else 
					offset -= 1;
			}
		}
		for (int i = 0; i < 23; i++)
			sheet.autoSizeColumn(i);
		try{
			FileOutputStream outputStream = new FileOutputStream("Schedule" + fileType);
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This writes the present schedules which is based
	 * upon the file type .txt, writing to the a text file
	 */
	private void writeTXT() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("present.txt"));
			BufferedWriter writer1 = new BufferedWriter(new FileWriter("preferred.txt"));
			writer1.write(startDay + "\t" + fullTime + "\t" + lunch + "\t" + hours);
			writer1.newLine();
			for (int i = 0; i < employees.size() - 1; i++) {
				writer.write(employees.getEmployee(i).toPresentString());
				writer.newLine();
				writer1.write(employees.getEmployee(i).toPreferredString());
				writer1.newLine();
			}
			writer.write(employees.getEmployee(employees.size() - 1).toPresentString());
			writer1.write(employees.getEmployee(employees.size() - 1).toPreferredString());
			writer1.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This writes the present schedules which is based
	 * upon the file type .csv, writing to the a file
	 * that can be read by excel
	 */
	private void writeCSV() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Schedule" + fileType));
			for (int i = 0; i < employees.size(); i++) {
				String[] parts = employees.getEmployee(i).toPresentString().split("[\t\n]");
				for (int j = 0; j < parts.length; j++) {
					if (j == 0)
						writer.write(parts[j].substring(0, parts[j].indexOf(",")) + "," + 
								parts[j].substring(parts[j].indexOf(",") + 2) + ",");
					else
						writer.write(parts[j] + ",");
				}
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}