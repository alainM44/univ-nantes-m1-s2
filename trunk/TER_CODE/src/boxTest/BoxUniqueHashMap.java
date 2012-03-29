package boxTest;

import java.util.HashMap;

import interfaces.IBox;

public class BoxUniqueHashMap implements IBox {

	private int id;
	private HashMap<Integer, Interval> list_coords;
	private HashMap<Integer, Object> list_cara;

	public BoxUniqueHashMap(int id, HashMap<Integer, Interval> listCoords,
			HashMap<Integer, Object> listCara) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara = listCara;

	}

	public BoxUniqueHashMap(int i) {
		super();
		id = i;
		list_coords = new HashMap<Integer, Interval>();
		list_cara = new HashMap<Integer, Object>();

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
		list_cara.put(id, cara);
	}

	@Override
	public void addCara(int id, String cara) {
		list_cara.put(id, cara);
	}

	@Override
	public void addCara(int id, Interval cara) {
		list_cara.put(id, cara);
	}

	@Override
	public void addCoord(int id, Interval coord) {
		list_coords.put(id, coord);

	}

}
