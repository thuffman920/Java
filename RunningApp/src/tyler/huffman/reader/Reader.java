/**
 * 
 */
package tyler.huffman.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Tyler Huffman
 */
public class Reader {
	
	public Reader(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(filename));
		
		reader.close();
	}

}
