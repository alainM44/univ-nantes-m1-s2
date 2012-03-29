package boxTest;

import java.util.ArrayList;

import interfaces.IBox;

public class BoxUniqueArrayList implements IBox {

	private int id;
	private ArrayList<Interval> list_coords;
	private ArrayList<Object> list_cara;

	public BoxUniqueArrayList(int id, ArrayList<Interval> listCoords,
			ArrayList<Object> listCara) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara = listCara;

	}

	public BoxUniqueArrayList(int i) {
		super();
		id = i;
		list_coords = new ArrayList<Interval>();
		list_cara = new ArrayList<Object>();

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
		list_cara.add(cara);
	}

	@Override
	public void addCara(int id, String cara) {
		list_cara.add(cara);
	}

	@Override
	public void addCara(int id, Interval cara) {
		list_cara.add(cara);
	}

	@Override
	public void addCoord(int id, Interval coord) {
		list_coords.add(coord);

	}


}
