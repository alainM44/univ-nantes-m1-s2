package book;
/**
 * @author franck
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class BeingFixed extends Book_State {

	/**
	 * @see Book_State#reserve(Book)
	 */
	public void reserve(Book b, String user) {
		b.rm_res(user);
	}

	/**
	 * @see Book_State#deliver(Book)
	 */
	public void deliver(Book b) throws InvalidActionException {
		if (!b.isDamaged()) {
			if (b.getNb_res() > 0)
				b.setCurrent_state(new Reserved());
			else
				b.setCurrent_state(new Available());
		}
		else throw new InvalidActionException("Book is not repared");
			
	}

}
