package tshuffma.cd_directory.database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tshuffma.cd_directory.exception.RewriteException;
import tshuffma.cd_directory.util.CD;

/**
 * This tests the programs functionality 
 * of reading the collection of sermons
 * from the database
 * @author Tyler Huffman
 */
public class CDReaderTest {

  /** This initializes the first cd reader */
  private CDReader reader;
  /** This initializes the second cd reader */
  private CDReader reader1;
  /** This initializes the third cd reader */
  private CDReader reader2;
  /** This initializes the fourth cd reader */
  private CDReader reader3;
  /** This initializes the fifth cd reader */
  private CDReader reader4;
  /** This initializes the sixth cd reader */
  private CDReader reader5;
  /** This initializes the first cd sermon */
  private CD firstCD;
  /** This initializes the second cd sermon */
  private CD secondCD;
  /** This initializes the full string of 
   * collection */
  private String listing;
  
  /**
   * This sets up the testing program 
   * reading the collection of sermons
   * @throws java.lang.Exception if an error occurs
   */
  @Before
  public void setUp() throws Exception {
    reader = new CDReader("C:\\Users\\Tyler\\Documents\\ChurchDirectory\\Testing\\CD_Example");
    reader1 = new CDReader("C:\\Users\\Tyler\\Documents\\ChurchDirectory\\Testing\\CD_Example1");
    reader2 = new CDReader("C:\\Users\\Tyler\\Documents\\ChurchDirectory\\Testing\\CD_Example2");
    reader3 = new CDReader("C:\\Users\\Tyler\\Documents\\ChurchDirectory\\Testing\\CD_Example3");
    reader4 = new CDReader("C:\\Users\\Tyler\\Documents\\ChurchDirectory\\Testing\\CD_Example4");
    reader5 = new CDReader("C:\\Users\\Tyler\\Documents\\ChurchDirectory\\Testing\\CD_Example5");
    String[] songs1 = new String[3];
    songs1[0] = "Victory In Jesus";
    songs1[1] = "Because He Loved Me";
    songs1[2] = "Jesus Is Precious";
    firstCD = new CD("07/19/2015 Sunday Morning", "Zach Plemmons",
        "Mark 4:35-41", songs1);
    String[] songs2 = new String[5];
    songs2[0] = "Since Jesus Came Into My Heart";
    songs2[1] = "Without A Doubt";
    songs2[2] = "Jesus Rescued Me";
    songs2[3] = " ";
    songs2[4] = "Didn't I";
    secondCD = new CD("07/19/2015 Sunday Evening", "Zach Plemmons",
        "Matthew 7:13-29", songs2);
    listing = "07/19/2015 Sunday Morning\tZach Plemmons\tMark 4:35-41\t" 
        + "Victory In Jesus\tBecause He Loved Me\tJesus Is Precious\t\n07/19/2015 "
        + "Sunday Evening\tZach Plemmons\tMatthew 7:13-29\tSince Jesus Came Into "
        + "My Heart\tWithout A Doubt\tJesus Rescued Me\t \tDidn't I\t\n07/26/2015 "
        + "Sunday Morning\tKeith Watkins\tMatthew 7:1-5\tHow Firm A Foundation\t \t"
        + " \t \tHe Whispers Sweet Peace To Me\t\n08/02/2015 Sunday Morning\tKeith Watkins\t"
        + "John 14:1-3\tI Know My Name Is There\tGod Is Still Good\tJesus Rescued "
        + "Me\t\n08/02/2015 Sunday Evening\tKeith Watkins\tProverbs 24:16-17\tThe "
        + "Gloryland Way\tNew Name\tGod's Been Good\t\n08/09/2015 Sunday Morning\t"
        + "Jason Cox\t1 Chronicles 29:1-4\tThere's Power In The Blood\tPraise God, "
        + "It's Settled, I'm Saved\tHe Knows My Name\t\n08/09/2015 Sunday Evening\t"
        + "Jason Hyatt\tExodus 12:1-12\tTis So Sweet To Trust In Jesus\tJesus "
        + "Rescued Me\tWithout A Doubt\t \tHe Is Here\t\n";
  }

  /**
   * This tests the ability of the program
   * to set up the complete list of sermons
   * from the database
   * Test method for {@link tshuffma.cd_directory.database.CDReader#setUpCollection()}.
   */
  @Test
  public void testSetUpCollection() {
    try {
      reader.setUpCollection();
      assertEquals(firstCD.toString(), reader.getCollection().
          getCDList().get(0).toString());
      assertEquals(secondCD.toString(), reader.getCollection().
          getCDList().get(1).toString());
    } catch (RewriteException e) {
      e.printStackTrace();
    }
    try {
      reader1.setUpCollection();
    } catch (RewriteException g) {
      assertEquals("Exception: An error occurred need to rewrite all "
          + "collections", g.getMessage());
    }
    try {
      reader2.setUpCollection();
    } catch (RewriteException g) {
      assertEquals("Exception: An error occurred need to rewrite all "
          + "collections", g.getMessage());
      assertEquals(listing, reader2.toString());
    }
    try {
      reader3.setUpCollection();
    } catch (RewriteException g) {
      assertEquals("Exception: An error occurred need to rewrite all "
          + "collections", g.getMessage());
    }
    try {
      reader4.setUpCollection();
    } catch (Exception g) {
    }
    try {
      reader5.setUpCollection();
    } catch (IllegalArgumentException | RewriteException g) {
      assertEquals("Exception: Not enough information", g.getMessage());
    } 
  }

  /**
   * This tests the ability to return
   * the full list of sermons
   * Test method for {@link tshuffma.cd_directory.database.CDReader#getCollection()}.
   */
  @Test
  public void testGetCollection() {
    try {
      reader.setUpCollection();
      assertEquals(listing, reader.toString());
    } catch (RewriteException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * This tests the ability of returning
   * the complete list into a string
   * Test method for {@link tshuffma.cd_directory.database.CDReader#toString()}.
   */
  @Test
  public void testToString() {
    try {
      reader.setUpCollection();
    } catch (RewriteException e) {
      e.printStackTrace();
    }
    assertEquals(listing, reader.toString());
  }
  
  /**
   * This tests the ability to convert
   * the excel date retrieve string into
   * an acceptable string 
   * Test method for {@link tshuffma.cd_directory.database.CDReader#changeDate(java.lang.String)}.
   */
  @Test
  public void testChangeDate() {
    assertEquals("07/19/2015", reader.changeDate("19-Jul-2015"));
    assertEquals("12/08/2016", reader.changeDate("08-Dec-2016"));
  }
}