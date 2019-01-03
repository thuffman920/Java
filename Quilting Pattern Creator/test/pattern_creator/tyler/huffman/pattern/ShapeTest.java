package pattern_creator.tyler.huffman.pattern;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pattern_creator.tyler.huffman.block.Shape;
import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;

/**
 * @author Tyler Huffman
 */
public class ShapeTest {

	private double[] sizes1 = {2.3, 2.3, 2.3, 2.3};
	private double[] sizes2 = {4.5, 4.5, 4.5, 4.5, 4.5, 4.5};
	private double[] angles1 = {90, 90, 90, 90};
	private double[] angles2 = {120, 120, 120, 120, 120, 120};
	private Shape shape1;
	private Shape shape2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shape1 = new Shape(sizes1, angles1);
		shape2 = new Shape(sizes2, angles2);
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#getSizes()}.
	 */
	@Test
	public void testGetSizes() {
		assertEquals(sizes1, shape1.getSizes());
		assertEquals(sizes2, shape2.getSizes());
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#getSideLength(int)}.
	 */
	@Test
	public void testGetSideLength() {
		assertTrue(shape1.getSideLength(2) == 2.3);
		assertTrue(shape2.getSideLength(4) == 4.5);
	}
	
	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#findSideLength(double, double)}.
	 */
	@Test
	public void testFindSideLength() {
		int list1[] = {0, 1, 2, 3};
		assertTrue(list1[0] == shape1.findSideLength(2.2, 0.2)[0]);
		assertTrue(list1[1] == shape1.findSideLength(2.2, 0.2)[1]);
		assertTrue(list1[2] == shape1.findSideLength(2.2, 0.2)[2]);
		assertTrue(list1[3] == shape1.findSideLength(2.2, 0.2)[3]);
	}
	
	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#getSides()}.
	 */
	@Test
	public void testGetSides() {
		assertTrue(shape1.getSides() == 4);
		assertTrue(shape2.getSides() == 6);
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#setSizes(double[])}.
	 */
	@Test
	public void testSetSizes() {
		double sizes3[] = {3.4, 56.4, 6.76, 1.23, .56};
		shape2.setSizes(sizes3);
		assertEquals(sizes3, shape2.getSizes());
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#toString()}.
	 */
	@Test
	public void testToString() {
		String result1 = "[2.3 (90.0), 2.3 (90.0), 2.3 (90.0), 2.3 (90.0)]";
		String result2 = "[4.5 (120.0), 4.5 (120.0), 4.5 (120.0), 4.5 (120.0), 4.5 (120.0), 4.5 (120.0)]";
		assertEquals(result1, shape1.toString());
		assertEquals(result2, shape2.toString());
	}
	
	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Shape#setSizes(double[])}.
	 */
	@Test
	public void testEquals() {
		try {
			Shape shape3 = new Shape(sizes1, angles1);
			assertFalse(shape1.equals(shape2));
			assertTrue(shape1.equals(shape1));
			assertFalse(shape1.equals(null));
			assertTrue(shape1.equals(shape3));
			assertFalse(shape1.equals(sizes1));
		} catch (ExceedsBoundaryException e) {
			e.printStackTrace();
		}

	}
}
