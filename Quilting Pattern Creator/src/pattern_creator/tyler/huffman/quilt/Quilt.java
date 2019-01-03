/**
 * 
 */
package pattern_creator.tyler.huffman.quilt;

import pattern_creator.tyler.huffman.block.Block;

/**
 * @author thuffman
 *
 */
public class Quilt {

	private Block[][] quilt;
	
	public Quilt(Block[][] quilt) {
		this.quilt = quilt;
	}
	
	public void changeDimensions(int x, int y) {
		Block[][] newQuilt = null;
		if (x >= quilt.length && y < quilt[0].length)
			newQuilt = new Block[x][quilt[0].length];
		else if (x < quilt.length && y >= quilt[0].length)
			newQuilt = new Block[quilt.length][y];
		else if (x < quilt.length && y < quilt[0].length)
			newQuilt = new Block[quilt.length][quilt[0].length];
		else
			newQuilt = new Block[x][y];
		for (int i = 0; i < quilt.length; i++)
			for (int j = 0; j < quilt[0].length; j++)
				newQuilt[i][j] = quilt[i][j];
		this.quilt = newQuilt;
	}
	
	
	public void addNewBlock(Block next, int x, int y) {
		if (x >= quilt.length && y >= quilt[0].length)
			throw new IndexOutOfBoundsException("Both indices are out of bounds");
		else if (x >= quilt.length)
			throw new IndexOutOfBoundsException("First index is out of bounds");
		else if (y >= quilt[0].length)
			throw new IndexOutOfBoundsException("Second index is out of bounds");
		this.quilt[x][y] = next;
	}
	
	public Block[][] getQuilt() {
		return this.quilt;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
