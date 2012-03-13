package book;

/**
 * @author franck
 */
import java.util.*;
public class SetDamaged extends Book_Event {

	/**
	 * @see com.fleurey.Book.Book_Event#execute(Book)
	 */
	public void execute(Book b, StringTokenizer parameters) throws InvalidActionException {
		b.setDamaged(true);
	}

}
