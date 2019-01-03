package tshuffma.cd_directory.queue;

import java.util.ArrayList;
import tshuffma.cd_directory.util.CD;
import tshuffma.cd_directory.util.CDCollection;

/**
 * 
 * @author Tyler Huffman
 */
public class SearchResults {

  /**  */
  private ArrayList<CD> searchResults;
  /**  */
  private CDCollection collection;
  
  /**
   * 
   */
  public SearchResults(CDCollection collection) {
    this.searchResults = new ArrayList<CD>();
    this.collection = collection;
  }
  
  /**
   * 
   * @param index
   * @return
   */
  public CD lookAt(int index) {
    return this.searchResults.get(index);
  }
  
  /**
   * 
   * @param search
   * @return
   */
  public ArrayList<CD> advancedSearch(String[] search) {
    searchResults = new ArrayList<CD>();
    return searchResults = this.finding(search);
  }
  
  /**
   * 
   * @param search
   * @return
   */
  public ArrayList<CD> search(String[] search) {
    searchResults = new ArrayList<CD>();
    return searchResults = this.finding(search);
  }
  
  /**
   * 
   * @return
   */
  public int searchCount() {
    return searchResults.size();
  }
  
  /**
   * 
   * @param search
   * @return
   */
  private ArrayList<CD> finding(String[] search) {
    ArrayList<CD> listing = new ArrayList<CD>();
    ArrayList<CD> total = collection.getCDList();
    if (search == null) 
      throw new IllegalArgumentException("Exception: Search parameters needed");
    else if (search.length != 10)
      throw new IllegalArgumentException("Exception: Not enough parameters");
    if (search[0] != null && search[1] != null && search[2] != null
        && !search[0].equals("") && !search[1].equals("") && !search[2].equals("")){
      listing.addAll(collection.searchByMonth(total, 
          Integer.parseInt(search[0]), Integer.parseInt(search[2])));
      listing.addAll(collection.searchByMonth(listing, 
          Integer.parseInt(search[1]), Integer.parseInt(search[2])));
    } else if ((search[0] == null || search[0].equals("")) && (search[1] == null 
        || search[1].equals("")) && (search[2] == null || search[2].equals("")))
      listing = total;
    else if (search[2] == null || search[2].equals(""))
      throw new IllegalArgumentException("Exception: Year is needed");
    else if (search[0] == null || search[0].equals(""))
      listing.addAll(collection.searchByDay(total,
          Integer.parseInt(search[1]), Integer.parseInt(search[2])));
    else if (search[1] == null || search[1].equals(""))
      listing.addAll(collection.searchByMonth(total,
          Integer.parseInt(search[0]), Integer.parseInt(search[2])));
    if ((search[3] == null || search[3].equals("")) && (search[4] == null ||
        search[4].equals("")) && (search[5] == null || search[5].equals("")))
      throw new IllegalArgumentException("Exception: Need at least "
        + "one service parameter");
    else {
      String[] partOfDay = {search[3], search[4], search[5]};
      listing = collection.searchByPartOfDayList(listing,
          partOfDay);
    }
    if (search[6] != null && !search[6].equals("")) 
      listing = collection.searchBySpeaker(listing, search[6]);
    if (search[7] != null && !search[7].equals(""))
      listing = collection.searchByBibleBook(listing, search[7]);
    if (search[8] != null && !search[8].equals(""))
      listing = collection.searchByBibleChapter(listing, search[8]);
    if (search[9] != null && !search[9].equals(""))
      listing = collection.searchByBibleSection(listing, search[9]);
    return listing;
  }
}