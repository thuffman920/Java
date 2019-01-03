package pattern_creator.tyler.huffman.exceptions;

/**
 * This is the error that is thrown whenever the shapes
 * exceed the area of the given block
 * @author Tyler Huffman
 */
public class ExceedsBoundaryException extends Exception {

	/** This is the serial number for this exception */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the generic constructor for the error that explains
	 * that the new shape exceeds the area of the given block
	 */
	public ExceedsBoundaryException() {
		super();
	}

	/**
	 * This is the constructor with an attached message that explains
	 * that the new shape exceeds the area of the given block
	 * @param message the error message
	 */
	public ExceedsBoundaryException(String message) {
		super(message);
	}
}