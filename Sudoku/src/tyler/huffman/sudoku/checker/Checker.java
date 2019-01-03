/**
 * 
 */
package tyler.huffman.sudoku.checker;

/**
 * @author tshuf
 *
 */
public class Checker {

	/**  */
	private boolean isValid;
	
	public Checker(int[][] puzzle) {
		int[][] offset = new int[9][2];
		for (int i = 0; i < 9; i++) {
			offset[i][0] = i % 3;
			offset[i][1] = (int)(i / 3);
		}
		boolean[] list = new boolean[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				if (puzzle[i][j] > 0 && list[puzzle[i][j] - 1]) {
					isValid = false;
					return;
				} else if (puzzle[i][j] > 0)
					list[puzzle[i][j] - 1] = true;
			list = new boolean[9];
		}
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++)
				if (puzzle[i][j] > 0 && list[puzzle[i][j] - 1]) {
					isValid = false;
					return;
				} else if (puzzle[i][j] > 0) 
					list[puzzle[i][j] - 1] = true;
			list = new boolean[9];
		}
		for (int k = 0; k < 9; k++) {
			int max1 = 9;
			if (offset[k][0] < 2)
				max1 = offset[k + 1][0] * 3;
			int max2 = 9;
			if (offset[k][1] < 2)
				max2 = (offset[k][1] + 1) * 3;
			for (int i = offset[k][1] * 3; i < max2; i++)
				for (int j = offset[k][0] * 3; j < max1; j++)
					if (puzzle[i][j] > 0 && list[puzzle[i][j] - 1]) {
						isValid = false;
						return;
					} else if (puzzle[i][j] > 0)
						list[puzzle[i][j] - 1] = true;
			list = new boolean[9];
		}
		isValid = true;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] puzzle = {
						{4,2,7,1,-1,-1,3,6,8},
						{-1,-1,5,-1,-1,6,3,-1,-1},
						{6,-1,3,-1,-1,-1,1,-1,-1},
						{2,-1,-1,-1,1,-1,4,-1,-1},
						{3,4,-1,-1,6,7,-1,5,1},
						{8,-1,1,-1,5,-1,-1,2,-1},
						{-1,9,-1,-1,-1,-1,7,3,-1},
						{7,-1,4,3,-1,-1,2,-1,9},
						{-1,3,2,-1,9,4,6,-1,-1},};
		long startTime = System.nanoTime();
		Checker check = new Checker(puzzle);
		long endTime = System.nanoTime();
		System.out.println("This puzzle is " + check.isValid() + "; Time execution is " + (endTime - startTime));
	}

}
