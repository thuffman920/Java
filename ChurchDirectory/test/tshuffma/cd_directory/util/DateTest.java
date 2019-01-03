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
public class DateTest {

  /**  */
  private Date date1;
  /**  */
  private Date date2;
  /**  */
  private Date date3;
  /**  */
  private Date date4;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    date1 = new Date(12, 31, 2016);
    date2 = new Date(1, 29, 2017);
    date3 = new Date(11, 1, 2016);
    date4 = new Date(11, 3, 1900);
    try {
      new Date(0, 1, 2015);
    } catch (IllegalArgumentException h) {
      assertEquals("Exception: Wrong day", h.getMessage());
    }
    try {
      new Date(2, 30, 2016);
    } catch (IllegalArgumentException h) {
      assertEquals("Exception: Wrong day", h.getMessage());
    }
    try {
      new Date(2, 29, 2015);
    } catch (IllegalArgumentException h) {
      assertEquals("Exception: Wrong day", h.getMessage());
    }
    try {
      new Date(1, 34, 2016);
    } catch (IllegalArgumentException h) {
      assertEquals("Exception: Wrong day", h.getMessage());
    }
    try {
      new Date(4, 31, 2015);
    } catch (IllegalArgumentException h) {
      assertEquals("Exception: Wrong day", h.getMessage());
    }
    
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#compareTo(tshuffma.cd_directory.util.Date)}.
   */
  @Test
  public void testCompareTo() {
    assertEquals(-30, date1.compareTo(date2));
    assertEquals(30, date2.compareTo(date1));
    assertEquals(60, date1.compareTo(date3));
    assertEquals(60, date2.compareTo(date3));
    assertEquals(-60, date3.compareTo(date2));
    Date date5 = new Date(2, 20, 2013);
    assertEquals(1095, date1.compareTo(date5));
    Date date6 = new Date(2, 16, 2016);
    assertEquals(-300, date6.compareTo(date1));
    Date date7 = new Date(3, 31, 2015);
    Date date8 = new Date(4, 1, 2015);
    assertEquals(1, date8.compareTo(date7));
    assertEquals(-1, date7.compareTo(date8));
    Date date9 = new Date(6, 8, 2014);
    Date date10 = new Date(5, 23, 2014);
    assertEquals(16, date9.compareTo(date10));
    Date date11 = new Date(7, 23, 2013);
    Date date12 = new Date(6, 11, 2013);
    assertEquals(-42, date12.compareTo(date11));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#calculateTotalDay(tshuffma.cd_directory.util.Date)}.
   */
  @Test
  public void testCalculateTotalDay() {
    assertEquals(31, date1.calculateTotalDay());
    assertEquals(31, date2.calculateTotalDay());
    assertEquals(30, date3.calculateTotalDay());
    assertEquals(30, date4.calculateTotalDay());
    Date date5 = new Date(2, 23, 2015);
    assertEquals(28, date5.calculateTotalDay());
    Date date6 = new Date(2, 24, 2016);
    assertEquals(29, date6.calculateTotalDay());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#yearIsLeapYear(int)}.
   */
  @Test
  public void testYearIsLeap() {
    assertTrue(date1.yearIsLeap());
    assertFalse(date2.yearIsLeap());
    assertFalse(date4.yearIsLeap());
    Date date5 = new Date(11, 4, 2000);
    assertTrue(date5.yearIsLeap());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#getDay()}.
   */
  @Test
  public void testGetDay() {
    assertEquals(31, date1.getDay());
    assertEquals(29, date2.getDay());
    assertEquals(1, date3.getDay());
    assertEquals(3, date4.getDay());
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#getMonth()}.
   */
  @Test
  public void testGetMonth() {
    assertEquals(12, date1.getMonth());
    assertEquals(1, date2.getMonth());
    assertEquals(11, date3.getMonth());
    assertEquals(11, date4.getMonth());    
  }
  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#getYear()}.
   */
  @Test
  public void testGetYear() {
    assertEquals(2016, date1.getYear());
    assertEquals(2017, date2.getYear());
    assertEquals(2016, date3.getYear());
    assertEquals(1900, date4.getYear());
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#isWednesday()}.
   */
  @Test
  public void testIsWednesday() {
    assertFalse(date3.isWednesday());
    Date date5 = new Date(12, 30, 2015);
    assertTrue(date5.isWednesday()); 
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.Date#isSunday()}.
   */
  @Test
  public void testIsSunday() {
    assertFalse(date1.isSunday());
    assertTrue(date2.isSunday());
  }
}