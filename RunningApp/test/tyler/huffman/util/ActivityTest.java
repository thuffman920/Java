/**
 * 
 */
package tyler.huffman.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author thuff
 *
 */
public class ActivityTest {

	public Activity activity1;
	
	public Activity activity2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		activity1 = new Activity(4, 25, 2016);
		activity2 = new Activity(4, 26, 2016);
		try {
			new Activity(0, 20, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Month is out of bounds", g.getMessage());
		}
		try {
			new Activity(13, 20, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Month is out of bounds", g.getMessage());
		}
		try {
			new Activity(2, 0, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(1, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(3, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(5, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(7, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(8, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(10, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(12, 32, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(4, 31, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(6, 31, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(9, 31, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(11, 31, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
		try {
			new Activity(2, 30, 2016);
		} catch (IllegalArgumentException g) {
			assertEquals("Day is out of bounds", g.getMessage());
		}
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertEquals("04/25/2016", activity1.getDate());
		assertEquals("04/26/2016", activity2.getDate());
		activity1.setDate("04/27/2016");
		assertEquals("04/27/2016", activity1.getDate());
		activity1.setDate("10/07/2016");
		assertEquals("10/07/2016", activity1.getDate());
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getPotassium()}.
	 */
	@Test
	public void testGetPotassium() {
		activity1.setPotassium(2400.0);
		assertEquals(2400.0, activity1.getPotassium(), 0.00001);
		activity2.setPotassium(3700.0);
		assertEquals(3700.0, activity2.getPotassium(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getSodium()}.
	 */
	@Test
	public void testGetSodium() {
		activity1.setSodium(2400.0);
		assertEquals(2400.0, activity1.getSodium(), 0.00001);
		activity2.setSodium(3500.0);
		assertEquals(3500.0, activity2.getSodium(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getIron()}.
	 */
	@Test
	public void testGetIron() {
		activity1.setIron(54.0);
		assertEquals(54.0, activity1.getIron(), 0.00001);
		activity2.setIron(100.0);
		assertEquals(100.0, activity2.getIron(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getCalcium()}.
	 */
	@Test
	public void testGetCalcium() {
		activity1.setCalcium(200.0);
		assertEquals(200.0, activity1.getCalcium(), 0.00001);
		activity2.setCalcium(160.0);
		assertEquals(160.0, activity2.getCalcium(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getProtein()}.
	 */
	@Test
	public void testGetProtein() {
		activity1.setProtein(40.0);
		assertEquals(40.0, activity1.getProtein(), 0.00001);
		activity2.setProtein(30.0);
		assertEquals(30.0, activity2.getProtein(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#getVitaminD()}.
	 */
	@Test
	public void testGetVitaminD() {
		activity1.setVitaminD(25.0);
		assertEquals(25.0, activity1.getVitaminD(), 0.00001);
		activity2.setVitaminD(30.0);
		assertEquals(30.0, activity2.getVitaminD(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Activity#toString()}.
	 */
	@Test
	public void testToString() {
		activity1.setCalcium(200.0);
		activity1.setIron(230.0);
		activity1.setPotassium(3500.0);
		activity1.setProtein(50.0);
		activity1.setVitaminD(25.0);
		activity1.setSodium(3500.0);
		String expected = "25 Apr 2016 3500.0 3500.0 230.0 200.0"
				+ " 50.0 25.0";
		assertEquals(expected, activity1.toString());
	}

}
