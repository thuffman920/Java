/**
 * 
 */
package tshuffma.cd_directory.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tyler
 *
 */
public class CDDetailsTest {

  /** This initializes the first cd details */
  private CDDetails cdDetails1;
  /** This initializes the first cd */
  private CD cd1;
  /** This initializes the second cd 
   * through the first cd */
  private CDDetails cdDetails2;
  /** This initializes the second cd */
  private CD cd2;
  /** This initializes the third cd
   * through the second cd */
  private CDDetails cdDetails3;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    cdDetails1 = new CDDetails(8, 9, 2015, "Sunday Morning", 
        "1 Chronicles", "29", "1-14");
    String[] songs1 = new String[3];
    songs1[0] = "There's Power In The Blood";
    songs1[1] = "Praise God, It's Settled, I'm Saved";
    songs1[2] = "He Knows My Name";
    cd1 = new CD("08/09/2015 Sunday Morning", "Jason Cox", 
        "1 Chronicles 29:1-14", songs1);
    String[] songs2 = new String[4];
    songs2[0] = "'Tis So Sweet To Trust In Jesus";
    songs2[1] = "Jesus Rescued Me";
    songs2[2] = "Without A Doubt";
    songs2[3] = "He Is Here";
    cd2 = new CD("08/09/2015 Sunday Evening", "Jason Hyatt",
        "Exodus 12:1-12", songs2);
    cdDetails2 = cd1.getDetails();
    cdDetails3 = cd2.getDetails();
    try {
      new CDDetails(8, 9, 2015, null, "Exodus", "12", "1-12");
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: More information is needed", g.getMessage());
    }
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getBibleBook()}.
   */
  @Test
  public void testGetBibleBook() {
    assertEquals("1 Chronicles", cdDetails1.getBibleBook());
    assertEquals("1 Chronicles", cdDetails2.getBibleBook());
    assertEquals("Exodus", cdDetails3.getBibleBook());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getBibleChapter()}.
   */
  @Test
  public void testGetBibleChapter() {
    assertEquals("29", cdDetails1.getBibleChapter());
    assertEquals("29", cdDetails2.getBibleChapter());
    assertEquals("12", cdDetails3.getBibleChapter());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getBibleSection()}.
   */
  @Test
  public void testGetBibleSection() {
    assertEquals("1-14", cdDetails1.getBibleSection());
    assertEquals("1-14", cdDetails2.getBibleSection());
    assertEquals("1-12", cdDetails3.getBibleSection());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getMonth()}.
   */
  @Test
  public void testGetMonth() {
    assertEquals(8, cdDetails1.getMonth());
    assertEquals(8, cdDetails2.getMonth());
    assertEquals(8, cdDetails3.getMonth());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getDay()}.
   */
  @Test
  public void testGetDay() {
    assertEquals(9, cdDetails1.getDay());
    assertEquals(9, cdDetails2.getDay());
    assertEquals(9, cdDetails3.getDay());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getYear()}.
   */
  @Test
  public void testGetYear() {
    assertEquals(2015, cdDetails1.getYear());
    assertEquals(2015, cdDetails2.getYear());
    assertEquals(2015, cdDetails3.getYear());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#getPartOfDay()}.
   */
  @Test
  public void testGetPartOfDay() {
    assertEquals("Sunday Morning", cdDetails1.getPartOfDay());
    assertEquals("Sunday Morning", cdDetails2.getPartOfDay());
    assertEquals("Sunday Evening", cdDetails3.getPartOfDay());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDDetails#equals(tshuffma.cd_directory.util.CDDetails)}.
   */
  @Test
  public void testEqualsCDDetails() {
    assertFalse(cdDetails1.equals(cdDetails3));
    CDDetails details1 = new CDDetails(9, 9, 2015, "Sunday Morning",
        "1 Chronicles", "29", "1-14");
    assertFalse(cdDetails1.equals(details1));
    CDDetails details2 = new CDDetails(8, 10, 2015, "Sunday Morning",
        "1 Chronicles", "29", "1-14");
    assertFalse(cdDetails1.equals(details2));
    CDDetails details3 = new CDDetails(8, 9, 2016, "Sunday Morning",
        "1 Chronicles", "29", "1-14");
    assertFalse(cdDetails1.equals(details3));
    CDDetails details4 = new CDDetails(8, 9, 2015, "Sunday Morning",
      "Exodus", "29", "1-14");
    assertFalse(cdDetails1.equals(details4));
    CDDetails details5 = new CDDetails(8, 9, 2015, "Sunday Morning",
        "1 Chronicles", "12", "1-14");
    assertFalse(cdDetails1.equals(details5));
    CDDetails details6 = new CDDetails(8, 9, 2015, "Sunday Morning",
        "1 Chronicles", "29", "1-12");
    assertFalse(cdDetails1.equals(details6));
  }
}
