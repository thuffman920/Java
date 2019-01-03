package pattern_creator.tyler.huffman.linked_list;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pattern_creator.tyler.huffman.block.Shape;
import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;

public class LinkedListTest {

	private double[] size1 = {3.45, 6.54, 67.654, 12.3, 4};
	private double[] size2 = {6.7, 6.7, 6.7, 6.7};
	private double[] size3 = {2.3, 4.6, 6.9, 9.2, 11.5};
	private double[] angle1 = {108.0, 108.0, 108.0, 108.0, 108.0};
	private double[] angle2 = {90.0, 90.0, 90.0, 90.0};
	private Shape shape1;
	private Shape shape2;
	private Shape shape3;
	private LinkedList<Shape> list1;
	private LinkedList<Shape> list2;
	
	@Before
	public void setUp() throws Exception {
		shape1 = new Shape(size1, angle1);
		shape2 = new Shape(size2, angle2);
		shape3 = new Shape(size3, angle1);
		list1 = new LinkedList();
		list1.addEnd(shape1);
		list1.addEnd(shape2);
		list2 = new LinkedList();
		list2.addEnd(shape1);
		list2.addEnd(shape3);
		list2.addEnd(shape2);
	}

	@Test
	public void testAddEnd() {
		assertEquals(shape1, list1.getIndex(0));
		assertEquals(shape2, list1.getIndex(1));
		double[] sizes4 = {1.2, 3.4, 5.456};
		double[] angle4 = {60, 60, 60};
		Shape shape4;
		try {
			shape4 = new Shape(sizes4, angle4);
			list1.addEnd(shape4);
			assertEquals(shape4, list1.getIndex(2));
		} catch (ExceedsBoundaryException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetIndex() {
		assertEquals(shape2, list1.getIndex(1));
		assertEquals(shape3, list2.getIndex(1));
	}

	@Test
	public void testGetIndexByItem() {
		assertTrue(1 == list2.getIndexByItem(shape3));
	}

	@Test
	public void testToString() {
		String first = "[3.45 (108.0), 6.54 (108.0), 67.654 (108.0), 12.3 (108.0), 4.0 (108.0)]\t[6.7 (90.0), 6.7 (90.0), 6.7 (90.0), 6.7 (90.0)]\t";
		assertEquals(first, list1.toString());
		String second = "[3.45 (108.0), 6.54 (108.0), 67.654 (108.0), 12.3 (108.0), 4.0 (108.0)]\t[2.3 (108.0), 4.6 (108.0), 6.9 (108.0), 9.2 (108.0), 11.5 (108.0)]\t[6.7 (90.0), 6.7 (90.0), 6.7 (90.0), 6.7 (90.0)]\t";
		assertEquals(second, list2.toString());
	}
	
	@Test
	public void testEqualsObject() {
		assertTrue(list1.equals(list1));
		LinkedList<Shape> list3 = new LinkedList();
		list3.addEnd(shape1);
		list3.addEnd(shape2);
		assertTrue(list1.equals(list3));
		assertFalse(list1.equals(null));
		LinkedList<Shape> list4 = new LinkedList();
		assertFalse(list1.equals(list4));
		assertFalse(list1.equals(list2));
		assertFalse(list1.equals(size1));
		list4.addEnd(shape1);
		list4.addEnd(shape2);
		list4.addEnd(shape3);
		assertFalse(list1.equals(list4));
	}
}
