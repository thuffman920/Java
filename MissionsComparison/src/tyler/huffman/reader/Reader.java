/**
 * 
 */
package tyler.huffman.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tyler.huffman.list.List;

/**
 * @author Tyler Huffman
 *
 */
public class Reader {

	private Scanner fileReader;
	
	private List<List<String>> fullData;
	
	public Reader(String filename) throws FileNotFoundException {
		try {
			fileReader = new Scanner(new File(filename));
		} catch (FileNotFoundException g) {
			throw new FileNotFoundException("The " + filename + " doesn't exist.");
		}
		this.buildList();
		fileReader.close();
	}
	
	public void buildList() {
		List<List<String>> fullData = new List<List<String>>();
		int i = 0;
		while (fileReader.hasNextLine()) {
			String next = fileReader.nextLine();
			fullData.add(this.readLine(next, ++i));
		}
		this.fullData = fullData;
	}
	
	public List<String> readLine(String line, int lineNum) {
		int index = 0;
		List<String> list = new List<String>();
		if (lineNum != 1)
			list.add("" + lineNum);
		else 
			list.add("Line No.:");
		while ((index = line.indexOf(",")) != -1) {
			String result = line.substring(0, index);
			list.add(result);
			line = line.substring(index + 1, line.length());
		}
		list.add(line);
		return list;
	}
	
	public List<List<String>> getFullData() {
		return this.fullData;
	}
}