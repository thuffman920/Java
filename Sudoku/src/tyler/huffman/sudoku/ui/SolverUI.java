/**
 * 
 */
package tyler.huffman.sudoku.ui;

import java.util.Scanner;

/**
 * @author tshuf
 *
 */
public class SolverUI {

	/** This initializes the sudoku puzzle based upon the user's input */
	private int[][] puzzle = new int[9][9];
	
	public SolverUI() {
		for (int i = 0; i < 9; i++) {
			System.out.print("" + (i + 1));
			if (i == 0)
				System.out.print("st row: ");
			else if (i == 1)
				System.out.print("nd row: ");
			else if (i == 2)
				System.out.print("rd row: ");
			else
				System.out.print("th row: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			for (int j = 0; j < input.length(); j++)
				if (input.charAt(j) == ' ')
					puzzle[i][j] = -1;
				else
					puzzle[i][j] = (int)(input.charAt(j)) - (int)('0');
		}
	}
	
	public String toString() {
		String result = "[\n";
		for (int i = 0; i < 9; i++) {
			result += "[";
			for (int j = 0; j < 8; j++)
				result += "" + puzzle[i][j] + ",";
			result += "" + puzzle[i][8] + "]\n";
		}
		result += "]\n";
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SolverUI ui = new SolverUI();
		System.out.println("" + ui.toString());
	}
}