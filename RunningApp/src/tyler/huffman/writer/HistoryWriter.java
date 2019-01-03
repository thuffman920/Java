package tyler.huffman.writer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import tyler.huffman.reader.HistoryReader;
import tyler.huffman.util.LinkedList;
import tyler.huffman.util.Record;

/**
 * @author Tyler Huffman
 */
public class HistoryWriter {

	public LinkedList<Record> list;
	
	public PrintStream writer;
	
	public HistoryWriter(LinkedList<Record> list) {
		try {
			writer = new PrintStream(new File("history.txt"));
			this.list = list;
			writeFile();
			writer.close();
		} catch(IOException g) {
			
		}
	}
	
	public void writeFile() throws IOException {
		for (int i = 0; i < list.size(); i++)
			writer.println(list.get(i).toString());
	}
	
	public static void main(String[] args) {
		HistoryReader reader = new HistoryReader();
		Record record = new Record("04 28 2016 0.0 10.0 1.0 0.0 0.0 2.0");
		LinkedList<Record> newList = reader.getList();
		newList.addSort(record);
		for (int i = 0; i < newList.size(); i++)
			System.out.println("" + newList.get(i));
		new HistoryWriter(newList);
	}
}