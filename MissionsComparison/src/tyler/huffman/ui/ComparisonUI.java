/**
 * 
 */
package tyler.huffman.ui;

import java.io.FileNotFoundException;
import tyler.huffman.list.List;
import tyler.huffman.reader.Reader;
import tyler.huffman.util.Compare;

/**
 * @author thuff
 *
 */
public class ComparisonUI {

	private Compare compare;
	
	public ComparisonUI(String file1, String file2) throws FileNotFoundException {
		Reader reader1 = new Reader(file1);
		Reader reader2 = new Reader(file2);
		compare = new Compare(reader1.getFullData(), reader2.getFullData());
	}
	
	public String toString(List<List<String>> fullData) {
		String result = "";
		List<String> next = new List<String>();
		for (int i = 0; i < fullData.size(); i++) {
			next = fullData.get(i);
			result += next.toString() + "\n";
		}
		return result;
	}
	
	public List<List<String>> getFirstDifference() {
		return compare.getFirstDifference();
	}
	
	public List<List<String>> getSecondDifference() {
		return compare.getSecondDifference();
	}
	
	public static void main(String[] args) {
		try {
			new ComparisonUI("Test1.csv", "Test2.csv");
			System.out.println("Finished!");
		} catch (FileNotFoundException g) {
			System.out.println("" + g.getMessage());
		}
	}
	
	public int getMax() {
		return compare.getMax();
	}
}