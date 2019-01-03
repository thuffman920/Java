package pattern_creator.tyler.huffman.block;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;

/**
 * @author Tyler Huffman
 */
public class BlockTest {

	private double[] sizes1 = {4.0, 4.0, 4.0, 4.0};
	private double[] sizes2 = {16.0, 16.0, 16.0 * Math.sqrt(2)};
	private double[] sizes3 = {8.0 * Math.sqrt(2), 8.0 * Math.sqrt(2), 16};
	private double[] angles1 = {90.0, 90.0, 90.0, 90.0};
	private double[] angles2 = {45.0, 90.0, 45.0};
	private Shape shape1;
	private Shape shape2;
	private Shape shape3;
	private Block block1;
	private Block block2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shape1 = new Shape(sizes1, angles1);
		shape2 = new Shape(sizes2, angles2);
		shape3 = new Shape(sizes3, angles2);
		block1 = new Block(16, 16);
		block1.addShape(shape1);
		block1.addShape(shape1);
		block1.addShape(shape1);
		block1.addShape(shape1);
		block2 = new Block(16, 16);
		block2.addShape(shape2);
		block2.addShape(shape3);
		block2.addShape(shape3);
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#addShape(pattern_creator.tyler.huffman.block.Shape)}.
	 */
	@Test
	public void testAddShape() {
		Block block = new Block(20,20);
		double[] sizes = {Math.sqrt(Math.pow(11, 2) + Math.pow(4, 2)), 8, 5, 8, 6, 12};
		double[] angles = {180 - Math.toDegrees(Math.asin(11.0 / Math.sqrt(Math.pow(11, 2) + Math.pow(4, 2)))), Math.toDegrees(Math.asin(11.0 / Math.sqrt(Math.pow(11, 2) + Math.pow(4, 2)))), 90.0, 270.0, 90.0, 90.0};
		try {
			Shape next = new Shape(sizes, angles);
			block.addShape(next);
			block.addShape(next);
		} catch (ExceedsBoundaryException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#getArea()}.
	 */
	@Test
	public void testGetArea() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#getTotalArea()}.
	 */
	@Test
	public void testGetTotalArea() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#getHeight()}.
	 */
	@Test
	public void testGetHeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#setHeight(int)}.
	 */
	@Test
	public void testSetHeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#getWidth()}.
	 */
	@Test
	public void testGetWidth() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#setWidth(int)}.
	 */
	@Test
	public void testSetWidth() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.block.Block#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
