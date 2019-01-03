package tyler.huffman.problem.two;

/**
 * @author Tyler Huffman
 */
public class AverageVector {

	public static int[][] array = {{1, 1, 1, 1}, {-1, 1, 1, 1}, {1, -1, 1, 1}, {1, 1, -1, 1}, {1, 1, 1, -1},
	                  {-1, -1, 1, 1}, {1, -1, -1, 1}, {1, 1, -1, -1}, {-1, 1, 1, -1}, {-1, 1, -1, 1},
	                  {1, -1, 1, -1}, {-1, -1, -1, 1}, {1, -1, -1, -1}, {-1, 1, -1, -1}, {-1, -1, 1, -1},
	                  {-1, -1, -1, -1}};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double sum = 0.0;
		for (int i = 0; i < 16; i++) {
			int E_p = array[i][0] * array[i][1] + array[i][1] * array[i][2] + array[i][2] * array[i][3]
					+ array[i][3] * array[i][0];
			sum += E_p * Math.exp(0 - E_p);
		}
		System.out.println("<E> = " + sum);
	}
}