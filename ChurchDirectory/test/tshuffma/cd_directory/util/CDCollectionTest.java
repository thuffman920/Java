/**
 * 
 */
package tshuffma.cd_directory.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tshuffma.cd_directory.exception.WrongDayException;
import tshuffma.cd_directory.exception.WrongMonthException;

/**
 * 
 * @author Tyler Huffman
 */
public class CDCollectionTest {

  /**  */
  private CDCollection cdCollection1;
  /**  */
  private CD[] cdArray1;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    cdCollection1 = new CDCollection();
    String[] array = new String[3];
    array[0] = "There's Power In The Blood";
    array[1] = "Praise God, It's Settled, I'm Saved";
    array[2] = "He Knows My Name";
    cdArray1 = new CD[4];
    cdArray1[0] = new CD("08/09/2015 Sunday Morning", "Jason Cox",
        "1 Chronicles 29:1-14", array);
    cdCollection1.addAt(cdArray1[0]);
    array = new String[4];
    array[0] = "'Tis So Sweet To Trust In Jesus";
    array[1] = "Jesus Rescued Me";
    array[2] = "Without A Doubt";
    array[3] = "He Is Here";
    cdArray1[1] = new CD("08/09/2015 Sunday Evening", "Jason Hyatt",
        "Exodus 12:1-12", array);
    cdCollection1.addAt(cdArray1[1]);
    array = new String[3];
    array[0] = "I Know My Name Is There";
    array[1] = "God Is Still Good";
    array[2] = "Jesus Rescued Me";
    cdArray1[2] = new CD("08/02/2015 Sunday Morning", "Keith Watkins",
        "John 14:1-3", array);
    cdCollection1.addAt(cdArray1[2]);
    array = new String[4];
    array[0] = "The Gloryland Way";
    array[1] = "New Name";
    array[2] = "God's Been Good";
    array[3] = "Waiting In The Water";
    cdArray1[3] = new CD("08/02/2015 Sunday Evening", "Keith Watkins",
        "Proverbs 24:16-17", array);
    cdCollection1.addAt(cdArray1[3]);
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#addAt(tshuffma.cd_directory.util.CD)}.
   */
  @Test
  public void testAddAt() {
    String[] array = new String[2];
    array[0] = "How Firm A Foundation";
    array[1] = "He Whispers Sweet Peace To Me";
    CD sermon1 = null;
    try {
      sermon1 = new CD("07/26/2015 Sunday Morning", "Keith Watkins",
          "Matthew 7:1-5", array);
      cdCollection1.addAt(sermon1);
      assertEquals(sermon1, cdCollection1.getAt(sermon1));
    } catch (WrongMonthException | WrongDayException e) {
      e.printStackTrace();
    }
    cdCollection1.addAt(cdArray1[0]);
    try {
      assertEquals(sermon1, cdCollection1.getItem(0));
      assertEquals(cdArray1[2], cdCollection1.getItem(1));
      assertEquals(cdArray1[3], cdCollection1.getItem(2));
      assertEquals(cdArray1[0], cdCollection1.getItem(3));
      assertEquals(cdArray1[1], cdCollection1.getItem(4));
    } catch (IndexOutOfBoundsException g) {
      g.printStackTrace();
    }
    CDCollection collection = new CDCollection();
    collection.addAt(cdArray1[1]);
    collection.addAt(cdArray1[0]);
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#addAll(java.util.ArrayList)}.
   */
  @Test
  public void testAddAll() {
    CDCollection cdCollection = new CDCollection();
    ArrayList<CD> next = new ArrayList<CD>();
    next.add(0, cdArray1[0]);
    next.add(cdArray1[1]);
    cdCollection.addAll(next);
    assertEquals(cdArray1[0], cdCollection.getItem(0));
    assertEquals(cdArray1[1], cdCollection.getItem(1));
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#getAt(tshuffma.cd_directory.util.CD)}.
   */
  @Test
  public void testGetAt() {
    assertEquals(cdArray1[1], cdCollection1.getAt(cdArray1[1]));
    assertEquals(cdArray1[3], cdCollection1.getAt(cdArray1[3]));
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#getItem(int)}.
   */
  @Test
  public void testGetItem() {
    assertEquals(cdArray1[2], cdCollection1.getItem(0));
    try {
      cdCollection1.getItem(4);
    } catch (IndexOutOfBoundsException g){
      assertEquals(cdArray1[1], cdCollection1.getItem(3));
    }
  }
  
  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#removeItem(tshuffma.cd_directory.util.CD)}.
   */
  @Test
  public void testRemoveItem() {
    cdCollection1.removeItem(cdArray1[3]);
    assertFalse(cdArray1[3].equals(cdCollection1.getItem(1)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByDate(java.util.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchByDate() {
    ArrayList<CD> actual = cdCollection1.searchByDate(cdCollection1.getCDList(),
        "08/02/2015 Sunday Evening");
    assertTrue(cdArray1[3].equals(actual.get(0)));
    actual = cdCollection1.searchByDate(cdCollection1.getCDList(),
        "08/09/2015 Sunday Evening");
    assertTrue(cdArray1[1].equals(actual.get(0)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByMonth(java.util.ArrayList, int, int)}.
   */
  @Test
  public void testSearchByMonth() {
    ArrayList<CD> expected = new ArrayList<CD>();
    expected.add(0, cdArray1[2]);
    expected.add(cdArray1[3]);
    expected.add(cdArray1[0]);
    expected.add(cdArray1[1]);
    ArrayList<CD> actual = cdCollection1.searchByMonth(cdCollection1.getCDList(),
        8, 2015);
    assertTrue(expected.get(0).equals(actual.get(0)));
    assertTrue(expected.get(1).equals(actual.get(1)));
    assertTrue(expected.get(2).equals(actual.get(2)));
    assertTrue(expected.get(3).equals(actual.get(3)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByDay(java.util.ArrayList, int, int)}.
   */
  @Test
  public void testSearchByDay() {
    ArrayList<CD> expected = new ArrayList<CD>();
    expected.add(0, cdArray1[0]);
    expected.add(cdArray1[1]);
    ArrayList<CD> actual = cdCollection1.searchByDay(
        cdCollection1.getCDList(), 9, 2015);
    assertTrue(expected.get(0).equals(actual.get(0)));
    assertTrue(expected.get(1).equals(actual.get(1)));
    expected = new ArrayList<CD>();
    expected.add(0, cdArray1[2]);
    expected.add(cdArray1[3]);
    actual = cdCollection1.searchByDay(cdCollection1.getCDList(),
        2, 2015);
    assertTrue(expected.get(0).equals(actual.get(0)));
    assertTrue(expected.get(1).equals(actual.get(1)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByPartOfDay(java.uti.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchByPartOfDay() {
    ArrayList<CD> expected = new ArrayList<CD>();
    expected.add(0, cdArray1[3]);
    expected.add(cdArray1[1]);
    ArrayList<CD> actual = cdCollection1.searchByPartOfDay(
        cdCollection1.getCDList(), "Sunday Evening");
    assertTrue(expected.get(0).equals(actual.get(0)));
    assertEquals(expected.toString(), actual.toString());
    expected = new ArrayList<CD>();
    expected.add(0, cdArray1[2]);
    expected.add(cdArray1[0]);
    actual = cdCollection1.searchByPartOfDay(cdCollection1.getCDList(),
        "Sunday Morning");
    assertTrue(expected.get(0).equals(actual.get(0)));
    assertEquals(actual.toString(), expected.toString());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByPartOfDayList(java.util.ArrayList, java.lang.String[])}.
   */
  @Test
  public void testSearchByPartOfDayList() {
    ArrayList<CD> expected = new ArrayList<CD>();
    expected.add(cdArray1[2]);
    expected.add(cdArray1[3]);
    expected.add(cdArray1[0]);
    expected.add(cdArray1[1]);
    String[] list = new String[2];
    list[0] = "Sunday Morning";
    list[1] = "Sunday Evening";
    ArrayList<CD> actual = cdCollection1.searchByPartOfDayList(
        cdCollection1.getCDList(), list);
    assertTrue(cdArray1[2].equals(actual.get(0)));
    assertTrue(cdArray1[3].equals(actual.get(1)));
    assertTrue(cdArray1[0].equals(actual.get(2)));
    assertTrue(cdArray1[1].equals(actual.get(3)));    
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchBySpeaker(java.util.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchBySpeaker() {
    ArrayList<CD> actual = cdCollection1.searchBySpeaker(
        cdCollection1.getCDList(), "Jason Cox");
    assertTrue(cdArray1[0].equals(actual.get(0)));
    ArrayList<CD> expected = new ArrayList<CD>();
    expected.add(0, cdArray1[2]);
    expected.add(cdArray1[3]);
    actual = cdCollection1.searchBySpeaker(cdCollection1.getCDList(),
        "Keith Watkins");
    assertTrue(expected.get(0).equals(actual.get(0)));
    assertTrue(expected.get(1).equals(actual.get(1)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByBibleVerse(java.util.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchByBibleVerse() {
    ArrayList<CD> actual = cdCollection1.searchByBibleVerse(
        cdCollection1.getCDList(), "1 Chronicles 29:1-14");
    assertTrue(cdArray1[0].equals(actual.get(0)));
    actual = cdCollection1.searchByBibleVerse(
        cdCollection1.getCDList(), "Exodus 12:1-12");
    assertTrue(cdArray1[1].equals(actual.get(0)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByBibleBook(java.util.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchByBibleBook() {
    ArrayList<CD> actual = cdCollection1.searchByBibleBook(
        cdCollection1.getCDList(), "John");
    assertTrue(cdArray1[2].equals(actual.get(0)));
    actual = cdCollection1.searchByBibleBook(cdCollection1.getCDList(),
        "1 Chronicles");
    assertTrue(cdArray1[0].equals(actual.get(0)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByBibleChapter(java.util.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchByBibleChapter() {
    ArrayList<CD> actual = cdCollection1.searchByBibleChapter(
        cdCollection1.getCDList(), "29");
    assertTrue(cdArray1[0].equals(actual.get(0)));
    actual = cdCollection1.searchByBibleChapter(cdCollection1.getCDList(),
        "14");
    assertTrue(cdArray1[2].equals(actual.get(0)));  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#searchByBibleSection(java.util.ArrayList, java.lang.String)}.
   */
  @Test
  public void testSearchByBibleSection() {
    ArrayList<CD> actual = cdCollection1.searchByBibleSection(
        cdCollection1.getCDList(), "1-3");
    assertTrue(cdArray1[2].equals(actual.get(0)));
    actual = cdCollection1.searchByBibleSection(cdCollection1.getCDList(),
        "16-17");
    assertTrue(cdArray1[3].equals(actual.get(0)));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#toString()}.
   */
  @Test
  public void testToString() {
    String listOfSermons = "08/02/2015 Sunday Morning\tKeith Watkins\t"
        + "John 14:1-3\tI Know My Name Is There\tGod Is Still Good\t"
        + "Jesus Rescued Me\t\n08/02/2015 Sunday Evening\tKeith Watkins\t"
        + "Proverbs 24:16-17\tThe Gloryland Way\tNew Name\tGod's Been Good\t"
        + "Waiting In The Water\t\n08/09/2015 Sunday Morning\tJason Cox\t"
        + "1 Chronicles 29:1-14\tThere's Power In The Blood\tPraise God, "
        + "It's Settled, I'm Saved\tHe Knows My Name\t\n08/09/2015 Sunday "
        + "Evening\tJason Hyatt\tExodus 12:1-12\t'Tis So Sweet To Trust In "
        + "Jesus\tJesus Rescued Me\tWithout A Doubt\tHe Is Here\t\n";
    assertEquals(listOfSermons, cdCollection1.toString());
  }

  /**
   * Test method for {@link tshuffma.cd_directory.util.CDCollection#getCDList()}.
   */
  @Test
  public void testGetCDList() {
    ArrayList<CD> list = new ArrayList<CD>();
    list.add(0, cdArray1[2]);
    list.add(cdArray1[3]);
    list.add(cdArray1[0]);
    list.add(cdArray1[1]);
    assertEquals(list, cdCollection1.getCDList());
  }
}
