package tshuffma.cd_directory.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tshuffma.cd_directory.util.CD;
import tshuffma.cd_directory.util.CDCollection;

/**
 * 
 * @author Tyler Huffman
 */
public class CDWriter {

  /**  */
  private XSSFWorkbook excelWriter;
  /**  */
  private PrintStream textWriter;
  /**  */
  private File inputTextFile;
  /**  */
  private String inputFile;
  /**  */
  private String filename;
  /**  */
  private CDCollection collection;
  
  /**
   * 
   * @param filename
   */
  public CDWriter(String filename, CDCollection collection) {
    this.filename = filename;
    this.inputTextFile = new File(filename + ".txt");
    this.inputFile = filename + ".xlsx";
    this.collection = collection;
    this.setUp();
    try {
      excelWriter = new XSSFWorkbook(new FileInputStream(
         this.inputFile)); 
    } catch (IOException g) {
      this.createExcel();
    }
  }
  
  /**
   * 
   */
  public void createExcel() {
    excelWriter = new XSSFWorkbook();
    excelWriter.getCreationHelper();
    Sheet sheet1 = excelWriter.createSheet("Sunday Morning");
    Row row1 = sheet1.createRow((short)0);
    row1.createCell(0).setCellValue("Date:");
    row1.createCell(1).setCellValue("Speaker:");
    row1.createCell(2).setCellValue("Bible Verse:");
    row1.createCell(3).setCellValue("Congregational:");
    row1.createCell(4).setCellValue("Songs:");
    row1.createCell(5);
    row1.createCell(6);
    row1.createCell(7).setCellValue("Special Song:");
    Sheet sheet2 = excelWriter.createSheet("Sunday Evening");
    Row row2 = sheet2.createRow((short)0);
    row2.createCell(0).setCellValue("Date:");
    row2.createCell(1).setCellValue("Speaker:");
    row2.createCell(2).setCellValue("Bible Verse:");
    row2.createCell(3).setCellValue("Congregational:");
    row2.createCell(4).setCellValue("Songs:");
    row2.createCell(5);
    row2.createCell(6);
    row2.createCell(7).setCellValue("Special Song:");
    Sheet sheet3 = excelWriter.createSheet("Wednesday Evening");
    Row row3 = sheet3.createRow((short)0);
    row3.createCell(0).setCellValue("Date:");
    row3.createCell(1).setCellValue("Speaker:");
    row3.createCell(2).setCellValue("Bible Verse:");
    row3.createCell(3).setCellValue("Congregational:");
    row3.createCell(4).setCellValue("Songs:");
    row3.createCell(5);
    row3.createCell(6);
    row3.createCell(7).setCellValue("Special Song:");
    this.autoSizeColumns(sheet3, 4);
    this.autoSizeColumns(sheet2, 4);
    this.autoSizeColumns(sheet1, 4);
    FileOutputStream fileNameOut = null;
    try {
      fileNameOut = new FileOutputStream(inputFile);
      excelWriter.write(fileNameOut);
      fileNameOut.close();
    } catch (FileNotFoundException e) {
      e.getMessage();
    } catch (IOException b) {
      b.getMessage();
    }
  }
  
  /**
   * 
   */
  public void createText() {
    File inputTextFileName = new File(filename);
    PrintStream outputTextFileName = null;
    try {
      outputTextFileName = new PrintStream(inputTextFileName);
      outputTextFileName.println("Date:\tService:\tSpeaker:"
          + "\tBible Verse:\tCongregational:\tSongs:\t      "
          + "\t      \tSpecial Singing:\n");
    } catch (FileNotFoundException w) {
      w.getMessage();
    }
  }
  
  /**
   * 
   * @param sheetName
   * @param number
   */
  private void autoSizeColumns(Sheet sheetName, int number) {
    for (int i = 0; i < number; i++){
      sheetName.autoSizeColumn(i);
    }
  }
  
  private void setUp() {
    try {
      textWriter = new PrintStream(inputFile);
    } catch (FileNotFoundException g) {
      try {
        inputTextFile.createNewFile();
        this.createText();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
  }
  
  public void writeAll() {
    this.text();
    this.excel("Sunday Morning");
    this.excel("Sunday Evening");
    this.excel("Wednesday Evening");
  }
  
  private void text() {
    int count = 0;
    CD next = null;
    while ((next = collection.getItem(count)) != null) {
      textWriter.println(next.toString());
    }
  }
  
  private void excel(String sheetName) {
    excelWriter.getCreationHelper();
    int count = 1;
    Sheet sheet = excelWriter.getSheet(sheetName);
    CDCollection newCollection = new CDCollection();
    newCollection.addAll(collection.searchByPartOfDay(
        collection.getCDList(), sheetName));
    while (sheet.getRow(count).getCell(0).getStringCellValue() != null) {
      Row row = sheet.getRow(count);
      CD item = newCollection.getItem(count - 1);
      row.getCell(0).setCellValue(item.getSimpleDate());
      row.getCell(1).setCellValue(item.getSpeaker());
      row.getCell(2).setCellValue(item.getBibleVerse());
      String[] songs = item.getSongs();
      for (int i = 0; i < songs.length; i++) 
        row.getCell(i + 3).setCellValue(songs[i]);
      this.autoSizeColumns(sheet, songs.length + 2);
      count++;
    }
    FileOutputStream inputFileOut = null;
    try{
      inputFileOut = new FileOutputStream(inputFile);
      excelWriter.write(inputFileOut);
      inputFileOut.close();
    } catch (IOException e) {
      e.getMessage();
    }
  }
}