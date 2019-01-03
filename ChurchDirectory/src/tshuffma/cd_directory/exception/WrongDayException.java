package tshuffma.cd_directory.exception;

/**
 * @author Tyler
 *
 */
public class WrongDayException extends Exception {

  private static final long serialVersionUID = 1L;

  private static final String MESSAGE = 
      "Exception: The day is wrong";
  
  public WrongDayException() {
    this(MESSAGE);
  }
  
  public WrongDayException(String message) {
    super(message);
  }
}