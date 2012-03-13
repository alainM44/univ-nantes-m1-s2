package book;
/**
 * @author franck
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Available extends Book_State {

	/**
	 * @see Book_State#reserve(Book)
	 */
	public void reserve(Book b, String user) {
		b.add_res(user);
		b.setCurrent_state(new Reserved());
	}

	/**
	 * @see Book_State#borrow(Book)
	 */
	public void borrow(Book b, String user) {
		b.rm_res(user);
		b.setCurrent_state(new Borrowed());
	}


	/**
	 * @see Book_State#fix(Book)
	 */
	public void fix(Book b) throws InvalidActionException {
		if (b.isDamaged())
			b.setCurrent_state(new BeingFixed());
		else
			throw new InvalidActionException("The book is not domaged");
	}

}
