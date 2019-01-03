

/**
 * @author Tyler Huffman
 *
 */
public class PrinterInventory {

	private int numberOfCells;
	private Printer [] printerCalls;
	
	public PrinterInventory(int number) {
		this.numberOfCells = number;
		printerCalls = new Printer[numberOfCells];
	}
	
}
