/**
 * 
 */
package tyler.huffman.problem.one;

import java.util.Scanner;

/**
 * @author thuff
 *
 */
public class MatrixMult {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Enter dimension: ");
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		int a_array[][] = new int[n][n];
		int b_array[][] = new int[n][n];
		int c_array[][] = new int[n][n];
		System.out.print("Enter " + (n*n) + " entries for A matrix: ");
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				a_array[i][j] = console.nextInt();
		System.out.print("Enter " + (n*n) + " entries for B matrix: ");
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				b_array[i][j] = console.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++)
					c_array[i][j] += Math.pow(-1, i+j+k+3) * a_array[i][k] * b_array[k][j];
				System.out.print(c_array[i][j] + " ");
			}
			System.out.println();
		}
		console.close();
	}
}
