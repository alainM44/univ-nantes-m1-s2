package boxTest;

import java.util.TreeMap;

import interfaces.IBox;

public class BoxUniqueTreeMap implements IBox {
	private int id;
	private TreeMap<Integer, Interval> list_coords;
	private TreeMap<Integer, Object> list_cara;

	public BoxUniqueTreeMap(int id, TreeMap<Integer, Interval> listCoords,
			TreeMap<Integer, Object> listCara) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara = listCara;

	}

	public BoxUniqueTreeMap(int i) {
		super();
		id = i;
		list_coords = new TreeMap<Integer, Interval>();
		list_cara = new TreeMap<Integer, Object>();

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
	
	@Override
	public Interval getCoord(int id)
	{
		return list_coords.get(id);
	}

	@Override
	public Interval getInterval(int id)
	{
		return (Interval) list_cara.get(id);
	}

	@Override
	public Number getNumber(int id)
	{
		return (Number) list_cara.get(id);
	}

	@Override
	public String getString(int id)
	{
		return (String) list_cara.get(id);
	}

}
