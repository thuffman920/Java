package pattern_creator.tyler.huffman.block;

import java.util.Arrays;
import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;

/**
 * This is initializes a new shape with a various number of sides
 * and various angles, which is constructed inside of a quilting
 * block pattern
 * @author Tyler Huffman
 */
public class Shape {

	/** This is the lengths of each side of the shape */
	private double[] sizes;
	/** This is the angles between each side, starting with angle between first and last side */
	private double[] angles;
	
	/**
	 * This is the initializer for a certain shape, made by the user
	 * @param sizes the lengths of each side
	 * @param angles the angles between adjacent sides
	 * @throws ExceedsBoundaryException if the angles don't add up correctly
	 */
	public Shape(double[] sizes, double[] angles) throws ExceedsBoundaryException {
		this.sizes = sizes;
		double inter = 0.0;
		for (int i = 0; i < angles.length; i++)
			inter += angles[i];
		if (Math.abs(inter - (sizes.length - 2) * 180) > 1.0)
			throw new ExceedsBoundaryException("Internal angles don't add up");
		this.angles = angles;
	}
	
	/**
	 * This determines the angles between any adjacent side
	 * @return the angles between adjacent sides
	 */
	public double[] getAngles() {
		return this.angles;
	}
	
	/**
	 * This initializes the angles between any adjacent side
	 * @param angles the angles between adjacent sides
	 * @throws ExceedsBoundaryException 
	 */
	public void setAngles(double[] angles) throws ExceedsBoundaryException {
		if (angles.length != sizes.length)
			throw new ExceedsBoundaryException("The number of angles is different from the number of sizes");
		double inter = 0.0;
		for (int i = 0; i < angles.length; i++)
			inter += angles[i];
		if (Math.abs(inter - (angles.length - 2) * 180.0) >= 1.0)
			throw new ExceedsBoundaryException("Internal angles don't add up");
		this.angles = angles;
	}
	
	/**
	 * This returns the lengths of the sides of this shape
	 * @return the lengths of the sides
	 */
	public double[] getSizes() {
		return this.sizes;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public double getSideLength(int index) {
		return sizes[index];
	}
	
	/**
	 * 
	 * @param length
	 * @param tol
	 * @return
	 */
	public int[] findSideLength(double length, double tol) {
		int result[] = new int[sizes.length];
		int count = 0;
		for (int i = 0; i < sizes.length; i++) {
			if (Math.abs(sizes[i] - length) <= tol) {
				result[count] = i;
				count++;
			}
		}
		int newResult[] = new int[count];
		for (int i = 0; i < count; i++)
			newResult[i] = result[i];
		return newResult;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSides() {
		return sizes.length;
	}
	
	/**
	 * 
	 * @param sizes
	 * @throws ExceedsBoundaryException
	 */
	public void setSizes(double[] sizes) throws ExceedsBoundaryException {
		if (sizes.length != angles.length)
			throw new ExceedsBoundaryException("The number of angles is different from the number of sizes");
		this.sizes = sizes;
	}

	/**
	 * 
	 * @return the information about this shape in a string
	 */
	public String toString() {
		String result = "[" + sizes[0] + " (" + angles[0] + ")";
		for (int i = 1; i < sizes.length; i++)
			result += ", " + sizes[i] + " (" + angles[i] + ")";
		return result + "]";
	}
	
	/**
	 * 
	 * @return the hashcode for this shape
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(sizes);
		return result;
	}

	/**
	 * 
	 * @return true if the two shapes are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		if (!Arrays.equals(sizes, other.sizes))
			return false;
		return true;
	}
}