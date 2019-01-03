/**
 * 
 */
package tshuffma.cd_directory.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import tshuffma.cd_directory.exception.WrongDayException;
import tshuffma.cd_directory.exception.WrongMonthException;

/**
 * @author Tyler
 *
 */
public class CDTest {

  /** This initializes the first sermon */
  private CD sermon1;
  /** This initializes the second sermon */
  private CD sermon2;
  /** This initializes the songs for the first
   * sermon */
  private String[] songs1;
  /** This initializes the songs for the second
   * sermon */
  private String[] songs2;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    songs1 = new String[3];
    songs1[0] = "There's Power In The Blood";
    songs1[1] = "Praise God, It's Settled, I'm Saved";
    songs1[2] = "He Knows My Name";
    sermon1 = new CD("08/09/2015\tSunday Morning", "Jason Cox", 
        "1 Chronicles 29:1-14", songs1);
    songs2 = new String[4];
    songs2[0] = "'Tis So Sweet To Trust In Jesus";
    songs2[1] = "Jesus Rescued Me";
    songs2[2] = "Without A Doubt";
    songs2[3] = "He Is Here";
    sermon2 = new CD("08/09/2015\tSunday Evening", "Jason Hyatt",
        "Exodus 12:1-12", songs2);
    try {
      new CD("08/32/2015 Sunday Morning", "Jason Cox",
          "1 Chronicles 29:1-14", songs1);
    } catch (WrongDayException g) {
      assertEquals("Exception: The day is wrong", g.getMessage());
    }
    try {
      new CD("13/09,2015 Sunday Evening", "Jason Hyatt",
          "Exodus 12:1-12", songs2);
    } catch (WrongMonthException f) {
      assertEquals("Exception: The month is wrong", f.getMessage());
    }
    try {
      new CD("08/09/2015 Sunday Morning", "", "1 Chronicles 29:1-14",
          songs1);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: More information is needed", g.getMessage());
    }
    try {
      new CD(null, "Jason Cox", "Exodus 12:1-12", songs1);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: More information is needed", g.getMessage());
    }
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#getSpeaker()}.
   */
  @Test
  public void testGetSpeaker() {
    assertEquals("Jason Cox", sermon1.getSpeaker());
    assertEquals("Jason Hyatt", sermon2.getSpeaker());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#getBibleVerse()}.
   */
  @Test
  public void testGetBibleVerse() {
    assertEquals("1 Chronicles 29:1-14", sermon1.getBibleVerse());
    assertEquals("Exodus 12:1-12", sermon2.getBibleVerse());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#getSongs()}.
   */
  @Test
  public void testGetSongs() {
    assertTrue(Arrays.equals(songs1, sermon1.getSongs()));
    assertTrue(Arrays.equals(songs2, sermon2.getSongs()));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#songsToString()}.
   */
  @Test
  public void testSongsToString() {
    String listOfSongs1 = "There's Power In The Blood\tPraise God, "
        + "It's Settled, I'm Saved\tHe Knows My Name\t";
    assertEquals(listOfSongs1, sermon1.songsToString());
    String listOfSongs2 = "'Tis So Sweet To Trust In Jesus\tJesus Rescued "
        + "Me\tWithout A Doubt\tHe Is Here\t";
    assertEquals(listOfSongs2, sermon2.songsToString());
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#getDate()}.
   */
  @Test
  public void testGetDate() {
    assertEquals("08/09/2015\tSunday Morning", sermon1.getDate());
    assertEquals("08/09/2015\tSunday Evening", sermon2.getDate());
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#getSimpleDate()}.
   */
  @Test
  public void testGetSimpleDate() {
    assertEquals("8/9/2015", sermon1.getSimpleDate());
    assertEquals("8/9/2015", sermon2.getSimpleDate());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#getDetails()}.
   */
  @Test
  public void testGetDetails() {
    CDDetails cdDetails1 = new CDDetails(8, 9, 2015, "Sunday Morning",
        "1 Chronicles", "29", "1-14");
    assertTrue(cdDetails1.equals(sermon1.getDetails()));
    CDDetails cdDetails2 = new CDDetails(8, 9, 2015, "Sunday Evening",
        "Exodus", "12", "1-12");
    assertTrue(cdDetails2.equals(sermon2.getDetails()));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#toString()}.
   */
  @Test
  public void testToString() {
    String toString1 = "08/09/2015\tSunday Morning\tJason Cox\t"
        + "1 Chronicles 29:1-14\tThere's Power In The Blood\t"
        + "Praise God, It's Settled, I'm Saved\tHe Knows My Name\t";
    assertEquals(toString1, sermon1.toString());
    String toString2 = "08/09/2015\tSunday Evening\tJason Hyatt\t"
        + "Exodus 12:1-12\t'Tis So Sweet To Trust In Jesus\t"
        + "Jesus Rescued Me\tWithout A Doubt\tHe Is Here\t";
    assertEquals(toString2, sermon2.toString());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CD#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertFalse(sermon1.equals(sermon2));
    assertTrue(sermon1.equals(sermon1));
    assertFalse(sermon1.equals(songs1));
    assertFalse(sermon1.equals(null));
    CD sermon3 = null;
    try {
      sermon3 = new CD("08/09/2015 Sunday Evening", "Jason Cox",
          "1 Chronicles 29:1-14", songs1);
      assertFalse(sermon1.equals(sermon3));
    } catch (WrongMonthException | WrongDayException e) {
      e.printStackTrace();
    }
    CD sermon4 = null;
    try {
      sermon4 = new CD("08/09/2015\tSunday Morning", "Jason Cox", 
          "1 Chronicles 29:1-14", songs1);
      assertTrue(sermon1.equals(sermon4));
    } catch (WrongMonthException | WrongDayException e) {
      e.printStackTrace();
    }
    CD sermon5 = null;
    try {
      sermon5 = new CD("08/09/2015\tSunday Morning", "Jason Cox",
          "1 Chronicles 29:1-14", songs2);
      assertFalse(sermon1.equals(sermon5));
    } catch (WrongDayException | WrongMonthException g) {
      g.printStackTrace();
    }
    CD sermon6 = null;
    try {
      sermon6 = new CD("08/09/2015\tSunday Morning", "Jason Hyatt",
          "1 Chronicles 29:1-14", songs1);
      assertFalse(sermon1.equals(sermon6));
    } catch (WrongDayException | WrongMonthException g) {
      g.printStackTrace();
    }
  }

}
