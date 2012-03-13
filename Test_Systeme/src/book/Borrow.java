package book;
/**
 * @author franck
 */

import java.util.*;
public class Borrow extends Book_Event {

	/**
	 * @see Book_Event#execute(Book)
	 */
	public void execute(Book b, StringTokenizer parameters) throws InvalidActionException {
		if (parameters.hasMoreTokens()) {
			String user = parameters.nextToken();
			b.getCurrent_state().borrow(b, user);
		}
		else throw new InvalidActionException("Missing user parameter");
	}

}
