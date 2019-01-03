import java.io.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.*;

public class ReadExcel {

	private String inputFile;
	
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	public void read() throws IOException {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			for (int i = 0; i < sheet.getColumns(); i++) {
				for (int j = 0; j < sheet.getRows(); j++) {
					Cell cell = sheet.getCell(i, j);
					System.out.print(cell.getContents() + "\t");
				}
				System.out.println();
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) throws IOException {
		ReadExcel test = new ReadExcel();
		test.setInputFile("C://Users/Tyler/Documents/Dad's Database Example.xls");
		test.read();
	}
}
