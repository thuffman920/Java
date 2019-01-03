package tshuffma.cd_directory.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tshuffma.cd_directory.exception.DifferenceInDatabaseException;
import tshuffma.cd_directory.exception.RewriteException;
import tshuffma.cd_directory.exception.WrongDayException;
import tshuffma.cd_directory.exception.WrongMonthException;
import tshuffma.cd_directory.util.CD;
import tshuffma.cd_directory.util.CDCollection;

/**
 * @author Tyler Huffman
 */
public class CDReader {

  /**  */
  private CDCollection collection;
  /**  */
  private BufferedReader br;
  /**  */
  private XSSFWorkbook excelReader;
  
  
  /**
   * 
   * @param filename
   * @throws IOException 
   */
  public CDReader(String filename) throws IOException {
    collection = new CDCollection();
    br = new BufferedReader(new FileReader(filename + ".txt"));
    FileInputStream fis = new FileInputStream(filename + ".xlsx");
    excelReader = new XSSFWorkbook(fis);
  }
  
  /**
   * 
   * @param fullLine
   * @return
   * @throws WrongDayException
   * @throws WrongMonthException
   */
  private CD createCD(String fullLine) throws WrongDayException,
        WrongMonthException {
    int count = 0;
    int index = 0;
    String[] list = new String[7];
    String date = "";
    String speaker = "";
    String bibleVerse = "";
    while ((index = fullLine.indexOf("\t")) > 0) {
      if (count == 0)
        date = fullLine.substring(0, index);
      else if (count == 1)
        speaker = fullLine.substring(0, index);
      else if (count == 2)
        bibleVerse = fullLine.substring(0, index);
      else
        list[count - 3] = fullLine.substring(0, index);
      fullLine = fullLine.substring(index + 1, fullLine.length());
      count++;
    }
    if (count < 3)
      throw new IllegalArgumentException("Exception: Not enough "
          + "information");
    else
      list[count - 2] = fullLine;
    CD newCD = new CD(date, speaker, bibleVerse, list);
    return newCD;
  }
  
  /**
   * @throws RewriteException 
   * 
   */
  public void setUpCollection() throws RewriteException {
    CD next = null;
    String nextLine = "";
    CDCollection collectionText = new CDCollection();
    CDCollection collectionExcel = new CDCollection();
    String[] sheets = {"Sunday Morning", "Sunday Evening", 
        "Wednesday Evening", "Special Event"};
    try {
      br.readLine();
      while ((nextLine = br.readLine()) != null) {
        next = this.createCD(nextLine);
        collectionText.addAt(next);
      }
      for (int i = 0; i < sheets.length; i++) {
        int rows = excelReader.getSheet(sheets[i]).getLastRowNum();
        for (int k = 1; k <= rows; k++) {
          Row row = excelReader.getSheet(sheets[i]).getRow(k);
          String line = this.changeDate(row.getCell(0).toString()) + 
              " " + sheets[i] + "\t";
          for (int j = 1; j < 8; j++) {
            if (row.getCell(j).toString().equals("")) {
              line += " \t";
            } else {
              line += row.getCell(j).toString() + "\t";
            }
          }
          collectionExcel.addAt(this.createCD(line.trim()));
        }
      }
      this.compareCollections(collectionText, collectionExcel);
      collection = collectionText;
    } catch (WrongDayException | WrongMonthException g) {
      g.printStackTrace();
    } catch (IOException f) {
      f.getMessage();
    } catch (DifferenceInDatabaseException h) {
      collection = this.correlatingFinalList(collectionText, collectionExcel);
      throw new RewriteException();
    }
  }
  
  private CDCollection correlatingFinalList(CDCollection text,
      CDCollection excel) {
    CDCollection fullList = new CDCollection();
    ArrayList<CD> textList = text.getCDList();
    ArrayList<CD> excelList = excel.getCDList();
    int min = Math.min(textList.size(), excelList.size());
    if (min == textList.size() && min != excelList.size()) {
      fullList.addAll(textList);
      fullList.addAll(this.correlate(textList, excelList));
    } else if (min == excelList.size() && min != textList.size()) {
      fullList.addAll(excelList);
      fullList.addAll(this.correlate(excelList, textList));
    } else {
      fullList.addAll(textList);
      fullList.addAll(this.correlate(textList, excelList));
    }
    return fullList;
  }

  private void compareCollections(CDCollection text, 
      CDCollection excel) throws DifferenceInDatabaseException {
    if (text.getCDList().size() != excel.getCDList().size())
      throw new DifferenceInDatabaseException();
    for (int i = 0; i < text.getCDList().size(); i++) {
      if (!text.getItem(i).equals(excel.getItem(i)))
        throw new DifferenceInDatabaseException();
    }
  }

  private ArrayList<CD> correlate(ArrayList<CD> min, ArrayList<CD> max) {
    ArrayList<CD> differ = new ArrayList<CD>();
    for (int i = 0; i < max.size(); i++) {
      CD next = max.get(i);
      boolean added = false;
      for (int j = 0; j < min.size(); j++) {
        if (next.equals(min.get(j)))
          added = true;
      }
      if (!added)
        differ.add(next);
    }
    return differ;
  }
  
  public String changeDate(String date) {
    String[] list = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
        "Aug", "Sep", "Oct", "Nov", "Dec"};
    String newDate = "";
    String mon = date.substring(3,  6);
    String year = date.substring(7, date.length());
    String day = date.substring(0, 2);
    for (int i = 0; i < list.length; i++) {
      if (mon.equals(list[i]) && i < 9) {
        newDate += "0" + (i + 1);
        break;
      } else if (mon.equals(list[i])) {
        newDate += (i + 1);
        break;
      }
    }
    newDate += "/" + day + "/" + year;
    return newDate;
  }
  
  public CDCollection getCollection() {
    return collection;
  }
  
  /**
   * 
   * @return 
   */
  public String toString() {
    return collection.toString();
  }
}