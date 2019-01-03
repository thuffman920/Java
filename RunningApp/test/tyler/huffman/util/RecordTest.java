package tyler.huffman.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tyler Huffman
 */
public class RecordTest {

	public Record record1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		record1 = new Record("04/25/2016 12:00:01.003 .234 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0");
	}

	/**
	 * Test method for {@link tyler.huffman.util.Record#getElapseTime()}.
	 */
	@Test
	public void testGetElapseTime() {
		assertEquals(.234, record1.getElapseTime(), 0.00001);
	}

	/**
	 * Test method for {@link tyler.huffman.util.Record#getEndTime()}.
	 */
	@Test
	public void testGetEndTime() {
		assertEquals("04/25/2016 12:00:01.003", record1.getEndTime());
	}

	/**
	 * Test method for {@link tyler.huffman.util.Record#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("04/25/2016 12:00:01.003 0.234 04 28 2016 0.0 0.0 0.0 0.0 0.0 0.0",
				record1.toString());
	}
	
	/**
	 * Test method for {@link tyler.huffman.util.Record#compareTo(tyler.huffman.util.Record)}.
	 */
	@Test
	public void testCompareTo() {
		int actual1 = record1.compareTo(new Record("04/25/2016 12:00:04.568 .546 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0"));
		assertEquals(-1, actual1, 0.00001);
		int actual2 = record1.compareTo(new Record("04/25/2016 11:58:54.568 .546 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0"));
		assertEquals(1, actual2, 0.00001);
		int actual3 = record1.compareTo(new Record("04/25/2016 12:00:01.003 .546 04 28 2016 100.0 0.0 0.0 0.0 0.0 0.0"));
		assertEquals(0, actual3, 0.00001);
	}
}