package tshuffma.cd_directory.util;

/**
 * This creates an object which
 * houses the extensive information
 * for each sermon
 * @author Tyler Huffman
 */
public class CDDetails {

  /** This is the sermon's bible verse book */
  private String bibleBook;
  /** This is the sermon's bible verse chapter */
  private String bibleChapter;
  /** This is the sermon's bible verse section */
  private String bibleSection;
  /** This is the sermons month */
  private int month;
  /** This is the sermons day */
  private int day;
  /** This is the sermons year */
  private int year;
  /** This is the sermons part of the day */
  private String partOfDay;
  
  /**
   * This creates an extensive amount of information
   * for the sermon
   * @param month the month of the sermon
   * @param day the day of the sermon
   * @param year the year of the sermon
   * @param partOfDay the part of the day of
   *    the sermon
   * @param bibleBook the bible verse's book for
   *    the sermon
   * @param bibleChapter the bible verse's chapter
   *    for the sermon
   * @param bibleSection the bible verse's section
   *    for the sermon
   * @throws IllegalArgumentException if any item
   *    is null
   */
  public CDDetails(int month, int day, int year, String partOfDay,
      String bibleBook, String bibleChapter, String bibleSection) {
    if (partOfDay == null || bibleBook == null || bibleChapter == null
        || bibleSection == null) 
      throw new IllegalArgumentException("Exception: More information"
          + " is needed");
    this.month = month;
    this.day = day;
    this.year = year;
    this.partOfDay = partOfDay.trim();
    this.bibleBook = bibleBook.trim();
    this.bibleChapter = bibleChapter.trim();
    this.bibleSection = bibleSection.trim();
  }
  
  /**
   * This returns the sermon's bible
   * verse's book
   * @return the sermon's bible verse's
   *    book
   */
  public String getBibleBook() {
    return this.bibleBook;
  }
  
  /**
   * This returns the sermon's bible verse's
   * chapter
   * @return the sermon's bible verse's
   *    chapter
   */
  public String getBibleChapter() {
    return this.bibleChapter;
  }
  
  /**
   * This returns the sermon's bible verse's
   * section
   * @return the sermon's bible verse's 
   *    section
   */
  public String getBibleSection() {
    return this.bibleSection;
  }
  
  /**
   * This return the sermon's month
   * for when the sermon was delivered
   * @return the serrmon's month it
   *    was delivered
   */
  public int getMonth() {
    return this.month;
  }
  
  /**
   * This return the sermon's day
   * for when the sermon was delivered
   * @return the sermon's day it 
   *    was delivered
   */
  public int getDay() {
    return this.day;
  }
  
  /**
   * This returns the sermon's year 
   * for when the sermon was delivered
   * @return the sermon's year it
   *    was delivered
   */
  public int getYear() {
    return this.year;
  }
  
  /**
   * This returns the part of the day in
   * which the sermon was delivered
   * @return the part of the day the
   *    sermon was delivered
   */
  public String getPartOfDay() {
    return this.partOfDay;
  }

  /**
   * This determines if the following
   * object is exactly the same as the
   * another object
   * @param obj the another object
   * @return true if the objects are the
   *    same
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    CDDetails other = (CDDetails) obj;
    if (!bibleBook.equals(other.bibleBook))
      return false;
    if (!bibleChapter.equals(other.bibleChapter))
      return false;
    if (!bibleSection.equals(other.bibleSection))
      return false;
    if (day != other.day)
      return false;
    if (month != other.month)
      return false;
    if (!partOfDay.equals(other.partOfDay)) 
      return false;
    if (year != other.year)
      return false;
    return true;
  }
}