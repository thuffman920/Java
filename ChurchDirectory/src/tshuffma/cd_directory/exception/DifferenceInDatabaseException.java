/**
 * 
 */
package tshuffma.cd_directory.exception;

/**
 * @author Tyler
 *
 */
public class DifferenceInDatabaseException extends Exception {

  /**  */
  private static final long serialVersionUID = 1L;
  /**  */
  private static final String MESSAGE = "Exception: There are "
      + "differences in databases";
  
  /**
   * 
   */
  public DifferenceInDatabaseException() {
    this(MESSAGE);
  }
  
  /**
   * 
   * @param message
   */
  public DifferenceInDatabaseException(String message) {
    super(message);
  }
}
