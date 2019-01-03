/**
 * 
 */
package pattern_creator.tyler.huffman.saved;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import pattern_creator.tyler.huffman.block.Block;
import pattern_creator.tyler.huffman.block.Shape;
import pattern_creator.tyler.huffman.exceptions.CantReadFileException;
import pattern_creator.tyler.huffman.exceptions.CantWriteToFileException;
import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;
import pattern_creator.tyler.huffman.quilt.Quilt;

/**
 * @author thuffman
 *
 */
public class LastSavedTest {

	private LastSaved saved;
	private double[] sizes1 = {1.0, 1.0, 1.0, 1.0};
	private double[] angles1 = {90.0, 90.0, 90.0, 90.0};
	private double[] sizes2 = {1.0, 1.0, 1.0 * Math.sqrt(2)};
	private double[] angles2 = {45.0, 90.0, 45.0};
	private double[] sizes3 = {1.0 * Math.sqrt(2), 1.0 * Math.sqrt(2), 1.0};
	private Block[][] quilt = new Block[2][2];
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		saved = new LastSaved();
		quilt[0][0] = new Block(2, 2);

		quilt[0][0].addShape(new Shape(sizes1, angles1));
		quilt[0][0].addShape(new Shape(sizes1, angles1));
		quilt[0][0].addShape(new Shape(sizes1, angles1));
		quilt[0][0].addShape(new Shape(sizes1, angles1));
		quilt[0][1] = new Block(2, 2);
		quilt[0][1].addShape(new Shape(sizes2, angles2));
		quilt[0][1].addShape(new Shape(sizes3, angles2));
		quilt[0][1].addShape(new Shape(sizes3, angles2));
		quilt[1][0] = new Block(2, 2);
		quilt[1][0].addShape(new Shape(sizes2, angles2));
		quilt[1][0].addShape(new Shape(sizes3, angles2));
		quilt[1][0].addShape(new Shape(sizes3, angles2));
		quilt[1][1] = new Block(2, 2);
		quilt[1][1].addShape(new Shape(sizes1, angles1));
		quilt[1][1].addShape(new Shape(sizes1, angles1));
		quilt[1][1].addShape(new Shape(sizes1, angles1));
		quilt[1][1].addShape(new Shape(sizes1, angles1));
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.saved.LastSaved#saveData(pattern_creator.tyler.huffman.quilt.Quilt)}.
	 */
	@Test
	public void testSaveData() {
		Quilt pattern = new Quilt(quilt);
		saved.saveData(pattern);
		try {
			Scanner reader = new Scanner(new FileReader("temp.txt"));
			String result1 = "0\t0\t2\t2\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t"
					+ "[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t";
			assertEquals(result1, reader.nextLine());
			String result2 = "0\t1\t2\t2\t[1.0 (45.0), 1.0 (90.0), 1.4142135623730951 (45.0)]\t[1.4142135623730951 (45.0), 1.4142135623730951 (90.0), 1.0 (45.0)]\t"
					+ "[1.4142135623730951 (45.0), 1.4142135623730951 (90.0), 1.0 (45.0)]\t";
			assertEquals(result2, reader.nextLine());
			String result3 = "1\t0\t2\t2\t[1.0 (45.0), 1.0 (90.0), 1.4142135623730951 (45.0)]\t[1.4142135623730951 (45.0), 1.4142135623730951 (90.0), 1.0 (45.0)]\t"
					+ "[1.4142135623730951 (45.0), 1.4142135623730951 (90.0), 1.0 (45.0)]\t";
			assertEquals(result3, reader.nextLine());
			String result4 = "1\t1\t2\t2\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t"
					+ "[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t";
			assertEquals(result4, reader.nextLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link pattern_creator.tyler.huffman.saved.LastSaved#recoverData()}.
	 */
	@Test
	public void testRecoverData() {
		Quilt pattern = new Quilt(quilt);
		saved.saveData(pattern);
		Block[][] newQuilt = saved.recoverData().getQuilt();
		String result1 = "2\t2\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t"
				+ "[1.0 (90.0), 1.0 (90.0), 1.0 (90.0), 1.0 (90.0)]\t";
		assertEquals(result1, newQuilt[0][0].toString());
		String result2 = "2\t2\t[1.0 (45.0), 1.0 (90.0), 1.4142135623730951 (45.0)]\t[1.4142135623730951 (45.0), 1.4142135623730951 (90.0), 1.0 (45.0)]\t"
				+ "[1.4142135623730951 (45.0), 1.4142135623730951 (90.0), 1.0 (45.0)]\t";
		assertEquals(result2, newQuilt[0][1].toString());
		assertEquals(result2, newQuilt[1][0].toString());
		assertEquals(result1, newQuilt[1][1].toString());
	}

}
