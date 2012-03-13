package book;
/**
 * @author Baudry - Fleurey
 *
 */

import java.util.*;

public abstract class Book_Event {

	public abstract void execute(Book b, StringTokenizer parameters) throws InvalidActionException;

}
