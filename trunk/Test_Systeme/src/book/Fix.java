package book;
/**
 * @author franck
 */
import java.util.*;
public class Fix extends Book_Event {

	/**
	 * @see Book_Event#execute(Book)
	 */
	public void execute(Book b, StringTokenizer parameters)  throws InvalidActionException {
		b.getCurrent_state().fix(b);
	}

}
