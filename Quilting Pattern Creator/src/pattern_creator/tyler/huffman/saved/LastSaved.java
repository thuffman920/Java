package pattern_creator.tyler.huffman.saved;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pattern_creator.tyler.huffman.block.Block;
import pattern_creator.tyler.huffman.block.Shape;
import pattern_creator.tyler.huffman.exceptions.CantReadFileException;
import pattern_creator.tyler.huffman.exceptions.CantWriteToFileException;
import pattern_creator.tyler.huffman.exceptions.ExceedsBoundaryException;
import pattern_creator.tyler.huffman.linked_list.LinkedList;
import pattern_creator.tyler.huffman.quilt.Quilt;

/**
 * @author Tyler Huffman
 */
public class LastSaved {
	
	private BufferedWriter writer;
	private Scanner reader;
	private String fileName = "temp.txt";
	
	public LastSaved() throws CantWriteToFileException, CantReadFileException {
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e) {
			throw new CantWriteToFileException("Can't write to file");
		}
		try {
			reader = new Scanner(new FileReader(fileName));
		}catch (IOException e) {
			throw new CantReadFileException("Can't read file");
		}
	}

	public int saveData(Quilt quilt) {
		int result = 0;
		Block[][] pattern = quilt.getQuilt();
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[i].length; j++) {
				try {
					if (pattern[i][j] == null)
						writer.write(i + "\t" + j + "\tnull");
					else
						writer.write(i + "\t" + j + "\t" + pattern[i][j].toString());
					writer.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Quilt recoverData() {
		Quilt result = null;
		String line = "";
		LinkedList<LinkedList<Block>> list = new LinkedList();
		LinkedList<Block> listing = new LinkedList();
		while (reader.hasNextLine() == true) {
			line = reader.nextLine();
			String[] parts = line.split("\t");
			Block next = new Block(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
			for (int i = 4; i < parts.length; i++) {
				if (parts[i].equals("null"))
					try {
						next.addShape(null);
					} catch (ExceedsBoundaryException e1) {
						e1.printStackTrace();
					}
				else {
					String[] sizes = parts[i].split(", ");
					double[] lengths = new double[sizes.length];
					double[] angles = new double[sizes.length];
					for (int j = 0; j < sizes.length; j++) {
						int dit = Math.max(sizes[j].indexOf("[") + 1, 0);
						lengths[j] = Double.parseDouble(sizes[j].substring(dit, sizes[j].indexOf(" ")));
						angles[j] = Double.parseDouble(sizes[j].substring(sizes[j].indexOf("(") + 1, sizes[j].indexOf(")")));
					}
					Shape newShape;
					try {
						newShape = new Shape(lengths, angles);
						next.addShape(newShape);
					} catch (ExceedsBoundaryException e) {
						e.printStackTrace();
					}
				}
			}
			if (Integer.parseInt(parts[1]) == 0 && Integer.parseInt(parts[0]) != 0) {
				list.addEnd(listing);
				listing = new LinkedList();
				listing.addEnd(next);
			} else {
				listing.addEnd(next);
			}
		}
		list.addEnd(listing);
		int rows = list.getSize();
		int columns = listing.getSize();
		Block[][] pattern = new Block[rows][columns];
		for (int i = 0; i < rows; i++) {
			LinkedList<Block> next = list.getIndex(i);
			for (int j = 0; j < columns; j++)
				pattern[i][j] = next.getIndex(j);
		}
		result = new Quilt(pattern);
		reader.close();
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LastSaved saved = new LastSaved();
			Block[][] quilt = new Block[2][2];
			quilt[0][0] = new Block(2, 2);
			double[] sizes1 = {1.0, 1.0, 1.0, 1.0};
			double[] angles1 = {90.0, 90.0, 90.0, 90.0};
			double[] sizes2 = {1.0, 1.0, 1.0 * Math.sqrt(2)};
			double[] angles2 = {45.0, 90.0, 45.0};
			double[] sizes3 = {1.0 * Math.sqrt(2), 1.0 * Math.sqrt(2), 1.0};
			double[] angles3 = {45.0, 90.0, 45.0};
			quilt[0][0].addShape(new Shape(sizes1, angles1));
			quilt[0][0].addShape(new Shape(sizes1, angles1));
			quilt[0][0].addShape(new Shape(sizes1, angles1));
			quilt[0][0].addShape(new Shape(sizes1, angles1));
			quilt[0][1] = new Block(2, 2);
			quilt[0][1].addShape(new Shape(sizes2, angles2));
			quilt[0][1].addShape(new Shape(sizes3, angles3));
			quilt[0][1].addShape(new Shape(sizes3, angles3));
			quilt[1][0] = new Block(2, 2);
			quilt[1][0].addShape(new Shape(sizes2, angles2));
			quilt[1][0].addShape(new Shape(sizes3, angles3));
			quilt[1][0].addShape(new Shape(sizes3, angles3));
			quilt[1][1] = new Block(2, 2);
			quilt[1][1].addShape(new Shape(sizes1, angles1));
			quilt[1][1].addShape(new Shape(sizes1, angles1));
			quilt[1][1].addShape(new Shape(sizes1, angles1));
			quilt[1][1].addShape(new Shape(sizes1, angles1));
			Quilt pattern = new Quilt(quilt);
			saved.saveData(pattern);
			Block[][] newQuilt = saved.recoverData().getQuilt();
			for (int i = 0; i < newQuilt.length; i++) {
				for (int j = 0; j < newQuilt[i].length; j++)
					System.out.println("" + newQuilt[i][j].toString());
			}
		} catch (CantWriteToFileException | CantReadFileException e) {
			e.printStackTrace();
		} catch (ExceedsBoundaryException e) {
			e.printStackTrace();
		}
	}

}
