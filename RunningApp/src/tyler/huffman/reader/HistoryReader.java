/**
 * 
 */
package tyler.huffman.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tyler.huffman.util.LinkedList;
import tyler.huffman.util.Record;

/**
 * @author Tyler Huffman
 */
public class HistoryReader {

	public Scanner reader;
	
	public LinkedList<Record> list;
	
	public HistoryReader() {
		try {
			reader = new Scanner(new File("history.txt"));
			list = new LinkedList<Record>();
			readText();
			reader.close();
		} catch(FileNotFoundException g) {
			
		}
	}
	
	public void readText() {
		String next = "";
		int i = 0;
		while (reader.hasNextLine()) {
			next = reader.nextLine();
			Record newRecord = new Record(next);
			list.addSort(newRecord);
			i++;
		}
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < list.size(); i++)
			result += list.get(i).toString() + "\n";
		return result;
	}
	
	public LinkedList<Record> getList() {
		return list;
	}
	
	public static void main(String[] args) {
		HistoryReader reader = new HistoryReader();
		System.out.println("Inside of File:\n" + reader.toString());
	}
}
