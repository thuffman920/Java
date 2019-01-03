package tyler_huffman.family_tree.exceptions;

/**
 * @author Tyler Huffman
 */
public class ConflictingNodesException extends Exception {

	/** This is the unchanging serial version of this exception */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This the exception that is thrown when an element in
	 * the tree list is full and the user is trying to add a
	 * new person
	 */
	public ConflictingNodesException() {
		super();
	}
	
	/**
	 * This takes in a message to tell the user that there is 
	 * a problem with adding two persons into the same node
	 * @param message the message to the user
	 */
	public ConflictingNodesException(String message) {
		super(message);
	}
}