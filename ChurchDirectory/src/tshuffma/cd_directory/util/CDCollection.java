package tshuffma.cd_directory.util;

import java.util.ArrayList;

/**
 * @author Tyler Huffman
 */
public class CDCollection {

  /** This is the array of sermons */
  private ArrayList<CD> cdList;
  
  /**
   * This creates a collection of sermons
   */
  public CDCollection() {
    cdList = new ArrayList<CD>();
  }
  
  /**
   * This adds a new sermon to the current
   * collection of sermons in a chronological
   * order
   * @param next the sermon to be added
   */
  public void addAt(CD next) {
    this.chronological(next, this.cdList);
  }
  
  public void addAll(ArrayList<CD> next) {
    CD item = null;
    int index = 0;
    while (index < next.size()) {
      item = next.get(index);
      this.addAt(item);
      index++;
    }
  }
  
  /**
   * 
   * @param next
   * @return
   */
  public CD getAt(CD next) {
    CD sendOut = null;
    for (int i = 0; i < cdList.size(); i++) {
      if (next.equals(cdList.get(i))) {
        sendOut = cdList.get(i);
      }
    }
    return sendOut;
  }
  
  /**
   * 
   * @param next
   * @return
   */
  public CD getItem(int index) {
    if (index < 0 || index >= cdList.size())
      throw new IndexOutOfBoundsException();
    return cdList.get(index);
  }
  
  /**
   * 
   * @param item
   */
  public void removeItem(CD item) {
    cdList.remove(item);
  }
  
  /**
   * 
   * @param date
   * @return
   */
  public ArrayList<CD> searchByDate(ArrayList<CD> list, String date) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (date.equals(list.get(i).getDate()))
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * 
   * @param month
   * @param year
   * @return
   */
  public ArrayList<CD> searchByMonth(ArrayList<CD> list, int month, 
      int year) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (month == cdList.get(i).getDetails().getMonth() && 
          year == cdList.get(i).getDetails().getYear()) {
        this.chronological(cdList.get(i), search);
      }
    }
    return search;
  }
  
  /**
   * 
   * @param day
   * @param year
   * @return
   */
  public ArrayList<CD> searchByDay(ArrayList<CD> list, int day, int year) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (day == list.get(i).getDetails().getDay() &&
          year == list.get(i).getDetails().getYear())
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * 
   * @param partOfDay
   * @return
   */
  public ArrayList<CD> searchByPartOfDay(ArrayList<CD> list, 
      String partOfDay) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (partOfDay.equals(list.get(i).getDetails().getPartOfDay())) {
        this.chronological(list.get(i), search);
      }
    }
    return search;
  }
  
  /**
   * 
   * @param partOfDay
   * @return
   */
  public ArrayList<CD> searchByPartOfDayList(ArrayList<CD> list, 
      String[] partOfDay) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < partOfDay.length; i++) {
      if (partOfDay[i] != null) {
        ArrayList<CD> next  = this.searchByPartOfDay(list, partOfDay[i]);
        for (int j = 0; j < next.size(); j++)
          this.chronological(next.get(j), search);
      }
    }
    return search;
  }
  
  /**
   * 
   * @param speaker
   * @return
   */
  public ArrayList<CD> searchBySpeaker(ArrayList<CD> list, 
      String speaker) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (speaker.equals(list.get(i).getSpeaker()))
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * 
   * @param bibleVerse
   * @return
   */
  public ArrayList<CD> searchByBibleVerse(ArrayList<CD> list,
      String bibleVerse) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (bibleVerse.equals(list.get(i).getBibleVerse()))
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * 
   * @param bibleBook
   * @return
   */
  public ArrayList<CD> searchByBibleBook(ArrayList<CD> list,
      String bibleBook) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (bibleBook.equals(list.get(i).getDetails().getBibleBook()))
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * 
   * @param bibleChapter
   * @return
   */
  public ArrayList<CD> searchByBibleChapter(ArrayList<CD> list,
      String bibleChapter) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (bibleChapter.equals(list.get(i).getDetails().getBibleChapter()))
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * 
   * @param bibleSection
   * @return
   */
  public ArrayList<CD> searchByBibleSection(ArrayList<CD> list,
      String bibleSection) {
    ArrayList<CD> search = new ArrayList<CD>();
    for (int i = 0; i < list.size(); i++) {
      if (bibleSection.equals(list.get(i).getDetails().getBibleSection()))
        this.chronological(list.get(i), search);
    }
    return search;
  }
  
  /**
   * This returns the collection into a 
   * string
   */
  public String toString() {
    String list = "";
    for (int i = 0; i < cdList.size(); i++) {
      list += cdList.get(i).toString() + "\n";
    }
    return list;
  }
  
  public ArrayList<CD> getCDList() {
    return this.cdList;
  }
  
  /**
   * This adds the next sermon into a 
   * chronological order
   * @param next the next sermon to added
   */
  private void chronological(CD next, ArrayList<CD> list) {
    int month = next.getDetails().getMonth();
    int day = next.getDetails().getDay();
    int year =  next.getDetails().getYear();
    String partOfDay = next.getDetails().getPartOfDay();
    Date input = new Date(month, day, year);
    boolean added = false;
    for (int i = 0; i < list.size(); i++) {
      int nextMonth = list.get(i).getDetails().getMonth();
      int nextDay = list.get(i).getDetails().getDay();
      int nextYear = list.get(i).getDetails().getYear();
      Date input1 = new Date(nextMonth, nextDay, nextYear);
      String nextPartOfDay = list.get(i).getDetails().getPartOfDay();
      int value = input.compareTo(input1);
      if (value < 0) {
        list.add(i, next);
        added = true;
        break;
      } else if (value == 0) {
        if (partOfDay.equals("Sunday Morning") && 
              nextPartOfDay.equals("Sunday Evening")) {
          list.add(i, next);
          added = true;
          break;
        } else if (partOfDay.equals("Sunday Evening") && 
            nextPartOfDay.equals("Sunday Morning")) {
          list.add(i + 1, next);
          added = true;
          break;
        } else if (partOfDay.equals(nextPartOfDay)) {
          added = true;
          break;
        }
      }
    }
    if (list.size() == 0) {
      added = true;
      list.add(0, next);
    }
    if (!added) 
      list.add(next);
  }
}