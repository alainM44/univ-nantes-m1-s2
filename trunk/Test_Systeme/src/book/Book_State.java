package book;
/**
 * @author franck
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class Book_State {
	
	
	public void reserve(Book b, String user) throws InvalidActionException {
		throw new InvalidActionException("State : " + this.getClass().getName() + " unable to reserve");
	}
	public void deliver(Book b) throws InvalidActionException {
		throw new InvalidActionException("State : " + this.getClass().getName() + " unable to deliver");
	}
	public void borrow(Book b, String user) throws InvalidActionException {
		throw new InvalidActionException("State : " + this.getClass().getName() + " unable to borrow");
	}
	public void give_back(Book b) throws InvalidActionException {
		throw new InvalidActionException("State : " + this.getClass().getName() + " unable to give_back");
	}
	public void fix(Book b) throws InvalidActionException {
		throw new InvalidActionException("State : " + this.getClass().getName() + " unable to fix");
	}

}
