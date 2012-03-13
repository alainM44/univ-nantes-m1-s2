package book;
/**
 * @author franck
 *
 */
public class InvalidActionException extends Exception {
	
	protected String message;
	
	public InvalidActionException(String msg) {
		message = msg;
	}	

	/**
	 * Returns the message.
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

}
