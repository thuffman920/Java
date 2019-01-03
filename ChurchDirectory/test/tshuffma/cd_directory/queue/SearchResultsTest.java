/**
 * 
 */
package tshuffma.cd_directory.queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tshuffma.cd_directory.util.CD;
import tshuffma.cd_directory.util.CDCollection;

/**
 * @author Tyler
 *
 */
public class SearchResultsTest {

  /**  */
  private CDCollection cdCollection1;
  /**  */
  private CD[] cdArray1;
  /**  */
  private SearchResults results;
  
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
    results = new SearchResults(cdCollection1);
  }

  /**
   * Test method for {@link tshuffma.cd_directory.queue.SearchResults#lookAt(int)}.
   */
  @Test
  public void testLookAt() {
    String[] search = {"", "", "", "", "Sunday Evening", "",
        null, null, null, null};
    results.advancedSearch(search);
    assertEquals(cdArray1[1], results.lookAt(1));
  }

  /**
   * Test method for {@link tshuffma.cd_directory.queue.SearchResults#advancedSearch(java.lang.String[])}.
   */
  @Test
  public void testAdvancedSearch() {
    String[] search = {"", "9", "2015", "Sunday Morning", "Sunday Evening",
        "", "", "", "", ""};
    results.advancedSearch(search);
    assertEquals(cdArray1[0], results.lookAt(0));
    assertEquals(cdArray1[1], results.lookAt(1));
    String[] search3 = {"8", "9", null, null, null, "Wednesday Evening",
        null, null, null, null};
    try {
      results.advancedSearch(search3);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: Year is needed", g.getMessage());
    }
    String[] search4 = {"8", "9", "", null, null, "Wednesday Evening",
        null, null, null, null};
    try {
      results.advancedSearch(search4);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: Year is needed", g.getMessage());
    }
    String[] search5 = {"8", "9", "2015", null, null, null, null, null, null, null};
    try {
      results.advancedSearch(search5);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: Need at least one service parameter", g.getMessage());
    }
    String[] search6 = {"8", "9", "2015", "", "", "", null, null, null, null};
    try {
      results.advancedSearch(search6);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: Need at least one service parameter", g.getMessage());
    }
  }

  /**
   * Test method for {@link tshuffma.cd_directory.queue.SearchResults#search(java.lang.String[])}.
   */
  @Test
  public void testSearch() {
    String[] search = {"8", "9", "2015", "Sunday Morning", null, null,
        "Jason Cox", "1 Chronicles", "29", "1-14"};
    results.search(search);
    assertEquals(cdArray1[0], results.lookAt(0));
    String[] search1 = null;
    try {
      results.search(search1);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: Search parameters needed", g.getMessage());
    }
    String[] search2 = {"8", "9", "2015", "Sunday Morning", null, null};
    try {
      results.search(search2);
    } catch (IllegalArgumentException g) {
      assertEquals("Exception: Not enough parameters", g.getMessage());
    }
  }

  /**
   * Test method for {@link tshuffma.cd_directory.queue.SearchResults#searchCount()}.
   */
  @Test
  public void testSearchCount() {
    String[] search = {"8", "", "2015", "Sunday Morning", null,
        null, null, null, null, null};
    results.advancedSearch(search);
    assertEquals(2, results.searchCount());
  }
}
