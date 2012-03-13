package book;
/**
 * @author franck
 *
 */
public class Borrowed extends Book_State {

	/**
	 * @see Book_State#reserve(Book)
	 */
	public void reserve(Book b, String user) {
		b.add_res(user);
	}

	/**
	 * @see Book_State#give_back(Book)
	 */
	public void give_back(Book b) {
		if (b.getNb_res() > 0)
			b.setCurrent_state(new Reserved());
		else
			b.setCurrent_state(new Available());
	}

}
