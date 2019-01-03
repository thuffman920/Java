package pattern_creator.tyler.huffman.exceptions;

/**
 * This result whenever the program can not write
 * to the temporary file
 * @author Tyler Huffman
 */
public class CantWriteToFileException extends Exception {

	/**	The serial number of this exception */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the constructor for the error that explains
	 * that the program can't write to the temporary file
	 * @param message the error message
	 */
	public CantWriteToFileException(String message) {
		super(message);
	}
	
	/**
	 * This is a plain construction which explains that the
	 * program can't write to the temporary file
	 */
	public CantWriteToFileException() {
		super();
	}
}
