/**
 * 
 */
package tshuffma.cd_directory.exception;

/**
 * @author Tyler Huffman
 */
public class RewriteException extends Exception {

  /**  */
  private static final long serialVersionUID = 1L;
  /**  */
  private static final String MESSAGE = "Exception: An error occurred"
      + " need to rewrite all collections";
  
  /**
   * 
   */
  public RewriteException() {
    this(MESSAGE);
  }
  
  /**
   * 
   * @param message
   */
  public RewriteException(String message) {
    super(message);
  }
}
