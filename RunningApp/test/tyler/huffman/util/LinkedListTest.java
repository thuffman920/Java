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
public class LinkedListTest {

	public LinkedList<Record> list;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new LinkedList<Record>();
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#get(int)}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#addSort(java.lang.Object)}.
	 */
	@Test
	public void testAddSort() {
		Record rd1 = new Record("04/25/2016 12:00:01.003 .568 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0");
		list.addSort(rd1);
		Record rd2 = new Record("04/25/2016 12:00:04.548 .568 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0");
		list.addSort(rd2);
		String expected = "04/25/2016 12:00:01.003 0.568 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0\n"
				+ "04/25/2016 12:00:04.548 0.568 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0\n";
		assertEquals(expected, list.toStringRecord());
		Record rd3 = new Record("04/25/2016 12:00:02.230 .358 04 28 2016 0.0 10.0 1.0 0.3 0.0 0.0");
		list.addSort(rd3);
		String expected2 = "04/25/2016 12:00:01.003 0.568 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0\n"
				+ "04/25/2016 12:00:02.230 0.358 04 28 2016 0.0 10.0 1.0 0.3 0.0 0.0\n"
				+ "04/25/2016 12:00:04.548 0.568 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0\n";
		assertEquals(expected2, list.toStringRecord());
		Record rd4 = new Record("04/13/2016 15:55:34.230 .358 04 28 2016 0.0 0.0 0.0 0.0 12.0 1.0");
		list.addSort(rd4);
		String expected3 = "04/13/2016 15:55:34.230 0.358 04 28 2016 0.0 0.0 0.0 0.0 12.0 1.0\n"
				+ "04/25/2016 12:00:01.003 0.568 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0\n"
				+ "04/25/2016 12:00:02.230 0.358 04 28 2016 0.0 10.0 1.0 0.3 0.0 0.0\n"
				+ "04/25/2016 12:00:04.548 0.568 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0\n";
		assertEquals(expected3, list.toStringRecord());
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#size()}.
	 */
	@Test
	public void testSize() {
		Record rd1 = new Record("04/25/2016 12:00:01.003 .568 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0");
		list.add(0, rd1);
		Record rd2 = new Record("04/25/2016 12:00:04.548 .568 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0");
		list.add(0, rd2);
		Record rd3 = new Record("04/25/2016 12:00:02.230 .358 04 28 2016 0.0 10.0 1.0 0.3 0.0 0.0");
		list.add(1, rd3);
		assertEquals(3, list.size(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#toStringRecord()}.
	 */
	@Test
	public void testToStringRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link tyler.huffman.util.LinkedList#toStringActivity()}.
	 */
	@Test
	public void testToStringActivity() {
		fail("Not yet implemented");
	}
}
