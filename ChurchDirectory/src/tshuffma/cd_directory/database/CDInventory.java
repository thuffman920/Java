package tshuffma.cd_directory.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Tyler Huffman
 */
public class CDInventory {

	/** This is the Excel file which contains the data */
	private String inputFile;
	private String date;
	private String speaker;
	private String bibleVerse;
	private String[] songs;
	private XSSFWorkbook inputWorkbook;
	private String inputTextFile;
	
	public CDInventory(String inputFile) throws IOException {
	  this.inputFile = inputFile + ".xlsx";
    this.inputTextFile = inputFile + ".txt";
	  FileInputStream inputFileName = null;
		try {
			new FileInputStream(inputTextFile);
		} catch (FileNotFoundException d) {
			this.createText();
		}
		try {
			inputFileName = new FileInputStream(inputFile);
			inputWorkbook = new XSSFWorkbook(inputFileName);
		} catch (FileNotFoundException e) {
			this.createExcel();
		}
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public void setBibleVerse(String bibleVerse){
		this.bibleVerse = bibleVerse;
	}
	
	public void setSongs(String songs) {
		
		String readLine = "";
		this.songs = new String[5];
		for (int i = 0; i < 5; i++) {
			if (songs.indexOf("\n") != -1) {
				readLine = songs.substring(0, songs.indexOf("\n"));
				this.songs[i] = readLine;
				songs = songs.substring(songs.indexOf("\n") + 1, songs.length());
			} else {
				this.songs[i] = songs;
				break;
			}
		}
	}
	
	public String[] setSearchFor(String searchFor, String knownParts) {
		String readLine = "";
		String[] searchForArray = new String[3];
		if (searchFor.indexOf("\n") != -1 && knownParts.substring(0, knownParts.indexOf("\t")).equals("Date")){
			readLine = searchFor.substring(0,  searchFor.indexOf("\n"));
			searchForArray[0] = readLine;
			searchFor = searchFor.substring(searchFor.indexOf("\n"), searchFor.length());
			knownParts = knownParts.substring(knownParts.indexOf("\t"), knownParts.length());
		} else {
		  searchForArray[0] = "";
		}
		if (searchFor.indexOf("\n") != -1 && knownParts.substring(0, knownParts.indexOf("\t")).equals("Speaker")){
		  readLine = searchFor.substring(0,  searchFor.indexOf("\n"));
      searchForArray[1] = readLine;
      searchFor = searchFor.substring(searchFor.indexOf("\n"), searchFor.length());
	    knownParts = knownParts.substring(knownParts.indexOf("\t"), knownParts.length());
	  }else {
	    searchForArray[1] = "";
	  }
		if (searchFor.indexOf("\n") != -1 && knownParts.substring(0, knownParts.indexOf("\t")).equals("Bible Verse")){
      readLine = searchFor.substring(0,  searchFor.indexOf("\n"));
      searchForArray[2] = readLine;
      searchFor = searchFor.substring(searchFor.indexOf("\n"), searchFor.length());
      knownParts = knownParts.substring(knownParts.indexOf("\t"), knownParts.length());
    } else {
      searchForArray[2] = "";
    }
		return searchForArray;
	}
	
	public void createExcel() {
		inputWorkbook = new XSSFWorkbook();
		inputWorkbook.getCreationHelper();
		Sheet sheet1 = inputWorkbook.createSheet("Sunday Morning");
		Row row1 = sheet1.createRow((short)0);
		row1.createCell(0).setCellValue("Date:");
		row1.createCell(1).setCellValue("Speaker:");
		row1.createCell(2).setCellValue("Bible Verse:");
		row1.createCell(3).setCellValue("Congregational:");
		row1.createCell(4).setCellValue("Songs:");
		Sheet sheet2 = inputWorkbook.createSheet("Sunday Evening");
		Row row2 = sheet2.createRow((short)0);
		row2.createCell(0).setCellValue("Date:");
		row2.createCell(1).setCellValue("Speaker:");
		row2.createCell(2).setCellValue("Bible Verse:");
		row2.createCell(3).setCellValue("Congregational:");
		row2.createCell(4).setCellValue("Songs:");
		Sheet sheet3 = inputWorkbook.createSheet("Wednesday Evening");
		Row row3 = sheet3.createRow((short)0);
		row3.createCell(0).setCellValue("Date:");
		row3.createCell(1).setCellValue("Speaker:");
		row3.createCell(2).setCellValue("Bible Verse:");
		row3.createCell(3).setCellValue("Congregational:");
		row3.createCell(4).setCellValue("Songs:");
		this.autoSizeColumns(sheet3, 4);
		this.autoSizeColumns(sheet2, 4);
		this.autoSizeColumns(sheet1, 4);
		FileOutputStream fileNameOut = null;
		try {
			fileNameOut = new FileOutputStream(inputFile);
			inputWorkbook.write(fileNameOut);
			fileNameOut.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException b) {
			b.getMessage();
		}
	}
	
	public void createText() {
		File inputTextFileName = new File(inputTextFile);
		PrintStream outputTextFileName = null;
		try {
			outputTextFileName = new PrintStream(inputTextFileName);
			outputTextFileName.println("Date:\tService:\tSpeaker:"
					+ "\tBible Verse:\tSongs:\n");
		} catch (FileNotFoundException w) {
			w.getMessage();
		}
	}
	
	public boolean writeExcel(String sheetName, int j) throws IOException {
		inputWorkbook.getCreationHelper();
		int count = 0;
		Sheet sheet = inputWorkbook.getSheet(sheetName);
		if (j == 0) {
		  count = this.findCountExcel(sheet);
		} else {
		  count = j;
		}
		Row row = sheet.createRow(count);
		row.createCell(0).setCellValue(this.date);
		row.createCell(1).setCellValue(this.speaker);
		row.createCell(2).setCellValue(this.bibleVerse);
		for (int i = 0; i < this.songs.length; i++){
			row.createCell(3 + i).setCellValue(this.songs[i]);
		}
		this.autoSizeColumns(sheet, songs.length + 2);
		FileOutputStream inputFileOut = null;
		try{
			inputFileOut = new FileOutputStream(inputFile);
			inputWorkbook.write(inputFileOut);
			inputFileOut.close();
		} catch (IOException e) {
			e.getMessage();
		}
		for (int i = 0; i < 2; i++) {
			if (row.getCell(0) == null){
				return false;
			}
		}
		return true;
	}
	
	public void autoSizeColumns(Sheet sheetName, int number) {
	  for (int i = 0; i < number; i++){
	    sheetName.autoSizeColumn(i);
	  }
	}
	
	public boolean writeText(String service) throws IOException {
		PrintStream outputTextFileName = new PrintStream(inputTextFile);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
		    new File(inputTextFile)));
		while(bufferedReader.readLine() != null) {
		  bufferedReader.readLine();
		}
		outputTextFileName.println("" + this.date + "\t" + service + "\t" +
				this.speaker + "\t" + this.bibleVerse + "\t" + this.toString(this.songs));
		boolean added = this.findCountText(service);
		outputTextFileName.close();
		bufferedReader.close();
		return added;
	}
	
	public int findCountExcel(Sheet sheet) {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public boolean findCountText(String service) throws IOException{
		BufferedReader inputTextFileName = new BufferedReader(
				new FileReader(new File(this.inputTextFile)));
		String readLine = "";
		String supposeToBeLine = "" + this.date + "\t" + service + "\t" +
				this.speaker + "\t" + this.bibleVerse + "\t" + this.toString(this.songs) + "\n";
		boolean lastLine = false;
		while ((readLine = inputTextFileName.readLine()) != null) {
			for (int i = 0; i < readLine.length(); i++) {
				if (readLine.equalsIgnoreCase(supposeToBeLine)) {
					lastLine = true;
				}
			}
		}
		inputTextFileName.close();
		return lastLine;

	}
	
	public String toString(String[] songs) {
	  String returnString = "";
	  for (int i = 0; i < songs.length; i++) {
	    returnString = returnString + songs[i] + "\t";
	  }
	  return returnString;
	}
	
	public String searchFor(String[] searchLimits, String service, String knownParts) {
		Sheet sheetIndex = inputWorkbook.getSheet(service);
		String searchResults = this.searchingExcel(searchLimits, sheetIndex, knownParts);
		if (searchResults.equals("\n")) {
		  return "No Results.";
		} else {
		  return "Date:\n" + searchResults;
		}
	}
	
	public String searchingExcel(String[] searchLimits, Sheet sheetIndex, String knownParts) {
		String searchResults = "";
		int date = 0;
		int speaker = 0;
		int bibleVerse = 0;
		for (int i = 0; i < 3; i++) {
		  int j = knownParts.indexOf("\t");
		  if (knownParts.substring(0, j).equalsIgnoreCase("Date")) {
		    date++;
		  } else if (knownParts.substring(0, j).equalsIgnoreCase("Speaker")) {
		    speaker++;
		  } else if (knownParts.substring(0, j).equalsIgnoreCase("Bible Verse")) {
		    bibleVerse++;
		  }
		  knownParts = knownParts.substring(j, knownParts.length());
		}
		int countArray = 0;
		for (int i = 0; i < searchLimits.length; i++) {
			if (searchLimits[i] != null) {
				countArray++;
			}
		}
		int countSearch = 0;
		for (int i = 1; i < sheetIndex.getLastRowNum(); i++) {
			Row row = sheetIndex.getRow(i);
			if (date != 0) {
			  this.date = row.getCell(0).toString();
			  System.out.println("" + this.date);
			  if (this.date.equalsIgnoreCase(searchLimits[0])){
			    countSearch++;
			  }
			}
			if (speaker != 0) {
			  this.speaker = row.getCell(1).toString();
			  System.out.println("" + this.speaker);
			  if (this.speaker.equalsIgnoreCase(searchLimits[1])){
			    countSearch++;
			  }
			}
			if (bibleVerse != 0) {
			  this.bibleVerse = row.getCell(2).toString();
			  System.out.println("" + this.bibleVerse);
			  if (this.bibleVerse.equalsIgnoreCase(searchLimits[2])) {
			    countSearch++;
			  }
			}
			for (int j = 0; i < this.songs.length; i++){
			  this.songs[j] = row.getCell(3 + j).toString();
			}
      try {
        this.writeExcel(sheetIndex.getSheetName(), i);
      } catch (IOException w) {
        w.getMessage();
      }
			if (countSearch == countArray) {
				searchResults = searchResults + row.getCell(0).toString() + " " + 
				    sheetIndex + "\n";
			}
		}
		return searchResults;
	}
	
	public String searchAll(String[] searchItems, String knownParts) {
		Sheet sheetIndex1 = inputWorkbook.getSheet("Sunday Morning");
		Sheet sheetIndex2 = inputWorkbook.getSheet("Sunday Evening");
		Sheet sheetIndex3 = inputWorkbook.getSheet("Wednesday Evening");
		String count1 = this.searchingExcel(searchItems, sheetIndex1, knownParts);
		String count2 = this.searchingExcel(searchItems, sheetIndex2, knownParts);
		String count3 = this.searchingExcel(searchItems, sheetIndex3, knownParts);
		return "Date:\n" + count1 + count2 + count3;
	}
}