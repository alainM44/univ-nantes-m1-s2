/**
 * @author Baudry - Fleurey
 *
 */

package book;
import java.util.*;

import java.lang.String;
public class Book {

	protected String title;
	protected boolean damaged;
	protected ArrayList reservations;

	protected static Hashtable commandsMap;
	
	protected Book_State current_state;

	protected void init_commandsMap() {
		if (commandsMap == null) {
			commandsMap = new Hashtable();
			commandsMap.put("RESERVE", new Reserve());
			commandsMap.put("FIX", new Fix());
			commandsMap.put("RETURN", new GiveBack());
			commandsMap.put("BORROW", new Borrow());
			commandsMap.put("DELIVER", new Deliver());
			commandsMap.put("SETDAMAGED", new SetDamaged());
			commandsMap.put("SETREPAIRED", new SetRepaired());
		}
	}

	protected Book_Event getCmd(String c) {

		return (Book_Event) commandsMap.get(c);
	}

	public Book(String title) {
		this.title = title;
		current_state = new Ordered();
		damaged = false;
		reservations = new ArrayList();
		init_commandsMap();
	}


	public void execute(String cmd, StringTokenizer parameters) throws InvalidActionException {

		Book_Event be;
		if ((be = getCmd(cmd)) != null)
			be.execute(this, parameters);
		else
			throw new InvalidActionException("Invalid command : " + cmd);

	}

	/**
	 * Returns the current_state. 
	 * @return Book_State     
	 */
	public Book_State getCurrent_state() {
		return current_state;
	}

	/**
	 * Sets the current_state. 
	 * @param current_state The current_state to set     
	 */
	public void setCurrent_state(Book_State current_state) {
		this.current_state = current_state;
	}

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		int i = 0;
		return title;
	}

	/**
	 * Returns the damaged.
	 * @return boolean
	 */
	public boolean isDamaged() {
		return damaged;
	}

	/**
	 * Returns the nb_res.
	 * @return int
	 */
	public int getNb_res() {
		return reservations.size();
	}

	/**
	 * Sets the damaged.
	 * @param damaged The damaged to set
	 */
	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}
	
	public boolean has_res(String user) {
		return reservations.contains(user);
	}

	public void add_res(String user) {
		if (!reservations.contains(user)) reservations.add(user);
	}

	public void rm_res(String user) {
		if (reservations.contains(user)) reservations.remove(user);
	}

}
