/**
 * 
 */
package tyler.huffman.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tyler Huffman
 */
public class FoodItemTest {

	public FoodItem item1;
	
	public FoodItem item2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item1 = new FoodItem("Cereal, Lucky Charms", "3/4 cup");
		item1.setPotassium(50);
		item1.setSodium(170);
		item1.setIron(4.5);
		item1.setCalcium(100);
		item1.setProtein(2000);
		item1.setVitaminD(0.001);
		item2 = new FoodItem("Broccoli", "1 spear");
		item2.setPotassium(98);
		item2.setSodium(10);
		item2.setIron(1.8);
		item2.setCalcium(10);
		item2.setProtein(900);
		item2.setVitaminD(0);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getServing()}.
	 */
	@Test
	public void testGetServing() {
		assertEquals("3/4 cup", item1.getServing());
		assertEquals("1 spear", item2.getServing());
		item1.setServing("1 cup");
		assertEquals("1 cup", item1.getServing());
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Lucky Charms Cereal", item1.getName());
		assertEquals("Broccoli", item2.getName());
		item1.setName("Cereal, Foot Loops");
		assertEquals("Foot Loops Cereal", item1.getName());
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getPotassium()}.
	 */
	@Test
	public void testGetPotassium() {
		assertEquals(50, item1.getPotassium(), 0.00001);
		assertEquals(98, item2.getPotassium(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getSodium()}.
	 */
	@Test
	public void testGetSodium() {
		assertEquals(170, item1.getSodium(), 0.00001);
		assertEquals(10, item2.getSodium(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getIron()}.
	 */
	@Test
	public void testGetIron() {
		assertEquals(4.5, item1.getIron(), 0.00001);
		assertEquals(1.8, item2.getIron(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getCalcium()}.
	 */
	@Test
	public void testGetCalcium() {
		assertEquals(100, item1.getCalcium(), 0.00001);
		assertEquals(10, item2.getCalcium(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getProtein()}.
	 */
	@Test
	public void testGetProtein() {
		assertEquals(2000, item1.getProtein(), 0.00001);
		assertEquals(900, item2.getProtein(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#getVitaminD()}.
	 */
	@Test
	public void testGetVitaminD() {
		assertEquals(0.001, item1.getVitaminD(), 0.00001);
		assertEquals(0, item2.getVitaminD(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#toStringGUI()}.
	 */
	@Test
	public void testToStringGUI() {
		String expected1 = "Lucky Charms Cereal\n  Serving: 3/4 cup\n\t"
				+ "Sodium: 170.0 mg\n\tPotassium: 50.0 mg\n\tProtein: 2000.0 mg\n"
				+ "\tCalcium: 100.0 mg\n\tIron: 4.5 mg\n\tVitamin D: 0.001 mg";
		assertEquals(expected1, item1.toStringGUI());
		String expected2 = "Broccoli\n  Serving: 1 spear\n\t"
				+ "Sodium: 10.0 mg\n\tPotassium: 98.0 mg\n\tProtein: 900.0 mg\n"
				+ "\tCalcium: 10.0 mg\n\tIron: 1.8 mg\n\tVitamin D: 0.0 mg";
		assertEquals(expected2, item2.toStringGUI());
	}

	/**
	 * Test method for {@link tyler.huffman.util.FoodItem#toStringText()}.
	 */
	@Test
	public void testToStringText() {
		assertEquals("Cereal, Lucky Charms (3/4 cup) 50.0 170.0 4.5 100.0 2000.0 0.001", item1.toStringText());
		assertEquals("Broccoli (1 spear) 98.0 10.0 1.8 10.0 900.0 0.0", item2.toStringText());
	}

}
