package pattern_creator.tyler.huffman.exceptions;

/**
 * This is the error that results because the program
 * can't read the temporary file
 * @author Tyler Huffman

 */
public class CantReadFileException extends Exception {

	/**	This is the serial number for this exception */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the constructor with an attached message that
	 * explains that the program can't read from the temporary
	 * file
	 * @param message the error message
	 */
	public CantReadFileException(String message) {
		super(message);
	}
	
	/**
	 * This is the generic constructor that explains that
	 * the program can't read from the temporary file
	 */
	public CantReadFileException() {
		super();
	}
}
