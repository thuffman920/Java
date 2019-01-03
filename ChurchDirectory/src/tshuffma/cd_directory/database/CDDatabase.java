package tshuffma.cd_directory.database;

import java.io.IOException;
import java.util.ArrayList;

import tshuffma.cd_directory.exception.RewriteException;
import tshuffma.cd_directory.exception.WrongDayException;
import tshuffma.cd_directory.exception.WrongMonthException;
import tshuffma.cd_directory.queue.SearchResults;
import tshuffma.cd_directory.util.CD;
import tshuffma.cd_directory.util.CDCollection;

/**
 * @author Tyler Huffman
 */
public class CDDatabase {

  /**  */
  private CDCollection collection;
  /**  */
  private CDReader reader;
  /**  */
  private CDWriter writer;
  /**  */
  private SearchResults results;
  
  /**
   * 
   * @param filename
   */
  public CDDatabase(String filename) {
    try {
      reader = new CDReader(filename);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    try {
      reader.setUpCollection();
      collection = reader.getCollection();
      writer = new CDWriter(filename, collection);
    } catch (RewriteException e) {
      collection = reader.getCollection();
      writer = new CDWriter(filename, collection);
      this.writeAll();
    }
    results = new SearchResults(collection);
  }
  
  /**
   * 
   * @param search
   * @return
   */
  public String advancedSearch(String[] search) {
    String result = "";
    ArrayList<CD> list = results.advancedSearch(search);
    int count = 0;
    while (list.get(count) != null) {
      result += list.get(count).toString() + "\n";
      count++;
    }
    return result;
  }
  
  /**
   * 
   * @param search
   * @return
   */
  public String search(String[] search) {
    String result = "";
    ArrayList<CD> list = results.search(search);
    int count = 0;
    while (list.get(count) != null) {
      result += list.get(count).toString() + "\n";
      count++;
    }
    return result;
  }
  
  /**
   * 
   * @param date
   * @param speaker
   * @param bibleVerse
   * @param songs
   * @throws WrongMonthException
   * @throws WrongDayException
   */
  public void addSermon(String date, String speaker, String 
      bibleVerse, String[] songs) throws WrongMonthException, WrongDayException {
    CD next = new CD(date, speaker, bibleVerse, songs);
    collection.addAt(next);
  }
  
  /**
   * 
   */
  public void writeAll() {
    writer.writeAll();
  }
  
  /**
   * 
   * @param next
   */
  public void removeSermon(CD next) {
    collection.removeItem(next);
  }
}