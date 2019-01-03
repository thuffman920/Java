package tshuffma.cd_directory.exception;

/**
 * @author Tyler
 *
 */
public class WrongMonthException extends Exception {

  private static final long serialVersionUID = 1L;
  
  private static final String MESSAGE = 
      "Exception: The month is wrong";
  
  public WrongMonthException() {
    this(MESSAGE);
  }
  
  public WrongMonthException(String message) {
    super(message);
  }
}