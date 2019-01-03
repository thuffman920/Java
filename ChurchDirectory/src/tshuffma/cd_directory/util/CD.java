package tshuffma.cd_directory.util;

import java.util.Arrays;
import tshuffma.cd_directory.exception.WrongDayException;
import tshuffma.cd_directory.exception.WrongMonthException;

/**
 * @author Tyler Huffman
 */
public class CD {

  /** This is the bible verse for the sermon */
  private String bibleVerse;
  /** This is the sermons date */
  private String date;
  /** This is the sermon's speaker */
  private String speaker;
  /** This is the sermon's songs */
  private String[] songs;
  /** This is the extensive details for the sermon */
  private CDDetails cdDetails;
  
  /**
   * This creates an object with certain
   * information along with extensive break
   * down
   * @param date the sermon's date
   * @param speaker the speaker of the sermon
   * @param bibleVerse the sermon's bible verse
   * @param songs the songs before the sermon
   * @throws WrongMonthException if the month does
   *    not exist
   * @throws WrongDayException is the day does not
   *    exist
   */
  public CD(String date, String speaker, String bibleVerse,
      String[] songs) throws WrongMonthException, WrongDayException {
    if (date == null || speaker == null || bibleVerse == null ||
        songs == null) 
      throw new IllegalArgumentException("Exception: More information"
          + " is needed");
    this.date = date;
    this.bibleVerse = bibleVerse;
    int month = Integer.parseInt(date.substring(0, 2));
    if (month < 0 || month > 12) {
      throw new WrongMonthException();
    }
    int day = Integer.parseInt(date.substring(3, 5));
    if (day < 0 || day > 31) {
      throw new WrongDayException();
    }
    int year = Integer.parseInt(date.substring(6, 10));
    String partOfDay = date.substring(11, date.length());
    this.speaker = speaker;
    String bibleBook = bibleVerse.substring(0, bibleVerse.lastIndexOf(" "));
    String bibleChapter = bibleVerse.substring(bibleVerse.lastIndexOf(" ") + 1,
        bibleVerse.indexOf(":"));
    String bibleSection = bibleVerse.substring(bibleVerse.indexOf(":") + 1,
        bibleVerse.length());
    this.songs = songs;
    if (partOfDay.equals("") || speaker.equals("") || 
        bibleVerse.equals("") || this.songsToString().equals(""))
      throw new IllegalArgumentException("Exception: More information"
          + " is needed");
    cdDetails = new CDDetails(month, day, year, partOfDay, bibleBook, 
        bibleChapter, bibleSection);
  }
  
  /**
   * This returns the speaker that
   * delivered the sermon
   * @return the speaker of the sermon
   */
  public String getSpeaker() {
    return this.speaker;
  }
  
  /**
   * This returns the bible verse used
   * during the sermon
   * @return the bible verse for the sermon
   */
  public String getBibleVerse() {
    return this.bibleVerse;
  }
  
  /**
   * This returns the songs that were song
   * before the sermon
   * @return the songs before the sermon
   */
  public String[] getSongs() {
    return this.songs;
  }
  
  /**
   * This returns all of the songs that
   * were sung before the sermon
   * @return the songs in a string
   */
  public String songsToString() {
    String songs = "";
    for (int i = 0; i < this.songs.length; i++) {
      if (this.songs[i] != null)
        songs += this.songs[i] + "\t";
    }
    return songs;
  }
  
  /**
   * This returns the date of the 
   * sermon
   * @return the date of the sermon
   */
  public String getDate() {
    return this.date;
  }
  
  public String getSimpleDate() {
    return this.getDetails().getMonth() + "/" + this.getDetails().getDay()
        + "/" + this.getDetails().getYear();
  }
  
  /**
   * This returns the extensive 
   * information for this sermon
   * @return the extensive details
   */
  public CDDetails getDetails() {
    return this.cdDetails;
  }
  
  /**
   * This returns the string of 
   * information for this sermon
   * @return the string of information
   */
  public String toString() {
    return date + "\t" + speaker + "\t" + bibleVerse + "\t" + 
        this.songsToString();
  }

  /**
   * This determines if this object
   * is the same as another object
   * @param obj another object
   * @return true if they are the same
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof CD))
      return false;
    CD other = (CD) obj;
    if (!cdDetails.equals(other.cdDetails))
      return false;
    if (!Arrays.equals(songs, other.songs))
      return false;
    if (!speaker.equals(other.speaker))
      return false;
    return true;
  }
  

}