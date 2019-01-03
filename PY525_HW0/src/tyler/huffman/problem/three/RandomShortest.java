package tyler.huffman.problem.three;

import java.util.Random;

/**
 * @author thuff
 *
 */
public class RandomShortest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		double[][] array = new double[7][2];
		double[][] paths = new double[7][7];
		for (int i = 0; i < 7; i++) {
			array[i][0] = rand.nextFloat();
			array[i][1] = rand.nextFloat();
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (j == i)
					paths[i][j] = 100000.01;
				else
					paths[i][j] = Math.sqrt(Math.pow(array[i][0] - array[j][0], 2) + 
							Math.pow(array[i][1] - array[j][1], 2));
			}
		}
	}
}