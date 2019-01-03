package pattern_creator.tyler.huffman.block;

import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;
import pattern_creator.tyler.huffman.linked_list.LinkedList;

/**
 * @author Tyler Huffman
 */
public class Block {

	private int width;
	private int height;
	private LinkedList<Shape> list_shapes;
	private double area;
	private double totalArea;
	
	public Block(int width, int height) {
		this.width = width;
		this.height = height;
		area = 0.0;
		list_shapes = new LinkedList();
		totalArea = width * height;
	}
	
	public void addShape(Shape next) throws ExceedsBoundaryException {
		if (next == null)
			list_shapes.addEnd(next);
		double a1 = areaOfShape(next);
		if (area  + a1 > totalArea)
			throw new ExceedsBoundaryException("The shape does not fit in block");
		list_shapes.addEnd(next);
		area += a1;
	}
	
	public double getArea() {
		return this.area;
	}
	
	public double getTotalArea() {
		return this.totalArea;
	}
	
	private double areaOfShape(Shape next) {
		int noTris = next.getSides() - 2;
		double[] lens = next.getSizes();
		double[] alpha = new double[noTris - 1];
		double[] angs = next.getAngles();
		double area = 0.0;
		double[] c = new double[noTris];
		for (int i = 0; i < noTris; i++) {
			double s = 0.0;
			double A_i = 0.0;
			if (i == 0) {
				c[0] = Math.sqrt(Math.pow(lens[0],2) + Math.pow(lens[1], 2) - 2.0 * lens[0] * lens[1] * Math.cos(Math.toRadians(angs[1])));
				if (noTris > 1)
					alpha[0] = Math.toRadians(angs[2]) - Math.asin(lens[0] * Math.sin(Math.toRadians(angs[1])) / (1.0 * c[0]));
				s = (lens[0] + lens[1] + c[0]) / 2.0;
				A_i = Math.sqrt(s * (s - lens[0]) * (s - lens[1]) * (s - c[0]));
			} else if (i == noTris - 1) {
				s = (lens[lens.length - 1] + lens[lens.length - 2] + c[noTris - 2]) / 2.0;
				A_i = Math.sqrt(s * (s - lens[lens.length - 1]) * (s - lens[lens.length - 2]) * (s - c[noTris - 2]));
			} else {
				c[i] = Math.sqrt(Math.pow(lens[i + 1], 2) + Math.pow(c[i - 1], 2) - 2.0 * lens[i + 1] * c[i - 1] * Math.cos(alpha[i - 1]));
				alpha[i] = Math.toRadians(angs[i + 2]) - Math.asin(c[i - 1] * Math.sin(alpha[i - 1]) / (1.0 * c[i]));
				s = (lens[i + 1] + c[i - 1]  + c[i]) / 2.0;
				A_i = Math.sqrt(s * (s - lens[i + 1]) * (s - c[i - 1]) * (s - c[i]));
			}
			area += A_i;
		}
		return area;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public String toString() {
		return "" + width + "\t" + height + "\t" + list_shapes.toString();
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + ((list_shapes == null) ? 0 : list_shapes.hashCode());
		result = prime * result + width;
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (height != other.height)
			return false;
		if (list_shapes == null) {
			if (other.list_shapes != null)
				return false;
		} else if (!list_shapes.equals(other.list_shapes))
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	public static void main(String[] args) {

	}
}