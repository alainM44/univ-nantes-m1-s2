package book;
/**
 * @author franck
 */

public class Reserved extends Book_State {

	/**
	 * @see Book_State#reserve(Book)
	 */
	public void reserve(Book b, String user) {
		b.add_res(user);
	}

	/**
	 * @see Book_State#borrow(Book)
	 */
	public void borrow(Book b, String user) {
		b.rm_res(user);
		b.setCurrent_state(new Borrowed());
	}

}
