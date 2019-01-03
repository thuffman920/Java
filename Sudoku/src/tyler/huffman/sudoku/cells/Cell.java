package tyler.huffman.sudoku.cells;

/**
 * 
 * @author Tyler Huffman
 */
public class Cell {

	/**  */
	private int[] possibles;
	/**  */
	private int[] available;
	/**  */
	private int numVar;
	/**  */
	private int row;
	/**  */
	private int column;
	
	/**
	 * 
	 * @param numVar
	 * @param available
	 * @param possibles
	 */
	public Cell(int row, int column, int numVar, int[] available, int[] possibles) {
		this.numVar = numVar;
		this.row = row;
		this.column = column;
		if (available.length == 1) {
			numVar = available[0];
			possibles = null;
		} else {
			for (int i = 0; i < possibles.length; i++) {
				int index = -1;
				for (int j = 0; j < available.length; j++)
					if (available[j] == possibles[i]) {
						index = j;
						break;
					}
				if (index == -1)
					possibles[i] = -1;
			}
			while (true) {
				int[] vars = new int[possibles.length - 1];
				int index = -1;
				for (int k = 0; k < possibles.length - 1; k++)
					if (possibles[k] == -1) {
						index = k;
						break;
					}
				if (index != -1) {
					for (int k = 0; k < possibles.length; k++)
						if (k < index)
							vars[k] = possibles[k];
						else if (k > index)
							vars[k - 1] = possibles[k];
					possibles = vars;
				} else
					break;
			}
			this.available = available;
			this.possibles = possibles;
		}
	}
	
	/**
	 * This determines the row position of the cell
	 * @return the row position of the cell
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * This determines the column position of the cell
	 * @return the column position of the cell
	 */
	public int getColumn() {
		return this.column;
	}
	
	/**
	 * 
	 * @return the value of this cell, 
	 */
	public int getVar() {
		return this.numVar;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDecided() {
		if (possibles == null)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getPossibles() {
		return this.possibles;
	}
	
	/**
	 * 
	 * @param item
	 */
	public void removeAvailable(int item) {
		this.available = remove(this.available, item);
		if (available.length == 1) {
			numVar = available[0];
			possibles = null;
		}
	}
	
	/**
	 * 
	 * @param item
	 */
	public void removePossibles(int item) {
		this.possibles = remove(this.possibles, item);
		if (possibles.length == 1) {
			numVar = possibles[0];
			possibles = null;
		}
	}
	
	/**
	 * 
	 * @param list
	 * @param item
	 * @return
	 */
	private int[] remove(int[] list, int item) {
		int index = -1;
		for (int i = 0; i < list.length; i++)
			if (list[i] == item) {
				index = i;
				break;
			}
		int[] vars = new int[possibles.length - 1];
		for (int k = 0; k < list.length; k++)
			if (k < index)
				vars[k] = list[k];
			else if (k > index)
				vars[k - 1] = list[k];
		list = vars;
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] available = {1, 8, 9};
		int[] possibles = {1, 3, 9};
		Cell a2b1 = new Cell(2, 1, -1, available, possibles);
		int[] bpossibles = a2b1.getPossibles();
		System.out.print("[");
		for (int i = 0; i < bpossibles.length - 1; i++)
			System.out.print("" + bpossibles[i] + ",");
		System.out.println("" + bpossibles[bpossibles.length - 1] + "]");
		a2b1.removePossibles(1);
		System.out.print("Is there only one: " + a2b1.isDecided());
		if (a2b1.isDecided())
			System.out.println("; The number is " + a2b1.getVar());
		else
			System.out.println("");
	}
}
