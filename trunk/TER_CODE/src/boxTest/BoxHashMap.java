package boxTest;

import java.util.HashMap;
import interfaces.IBox;

public class BoxHashMap implements IBox {
	private int id;
	private HashMap<Integer, Interval> list_coords;
	private HashMap<Integer, String> list_cara_string;
	private HashMap<Integer, Number> list_cara_number;
	private HashMap<Integer, Interval> list_cara_interval;

	public BoxHashMap(int id, HashMap<Integer, Interval> listCoords,
			HashMap<Integer, String> listCaraString, HashMap<Integer, Number> listCaraNumber,
			HashMap<Integer, Interval> listCaraInterval) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara_string = listCaraString;
		list_cara_number = listCaraNumber;
		list_cara_interval = listCaraInterval;
	}

	public BoxHashMap(int i) {
		super();
		id = i;
		list_coords = new HashMap<Integer, Interval>();
		list_cara_string = new HashMap<Integer, String>();
		list_cara_number = new HashMap<Integer, Number>();
		list_cara_interval = new HashMap<Integer, Interval>();
	}

	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public void addCara(int id, Number cara) {
		list_cara_number.put(id, cara);
	}
	@Override
	public void addCara(int id, String cara) {
		list_cara_string.put(id, cara);
	}
	@Override
	public void addCara(int id, Interval cara) {
		list_cara_interval.put(id, cara);
	}
	@Override
	public void addCoord(int id, Interval coord) {
		list_coords.put(id, coord);

	}

}
