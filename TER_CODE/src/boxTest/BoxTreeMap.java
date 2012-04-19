package boxTest;

import java.util.TreeMap;

import interfaces.IBox;

public class BoxTreeMap implements IBox {
	private int id;
	private TreeMap<Integer, Interval> list_coords;
	private TreeMap<Integer, String> list_cara_string;
	private TreeMap<Integer, Number> list_cara_number;
	private TreeMap<Integer, Interval> list_cara_interval;

	public BoxTreeMap(int id, TreeMap<Integer, Interval> listCoords,
			TreeMap<Integer, String> listCaraString, TreeMap<Integer, Number> listCaraNumber,
			TreeMap<Integer, Interval> listCaraInterval) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara_string = listCaraString;
		list_cara_number = listCaraNumber;
		list_cara_interval = listCaraInterval;
	}

	public BoxTreeMap(int i) {
		super();
		id = i;
		list_coords = new TreeMap<Integer, Interval>();
		list_cara_string = new TreeMap<Integer, String>();
		list_cara_number = new TreeMap<Integer, Number>();
		list_cara_interval = new TreeMap<Integer, Interval>();
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
	
	@Override
	public Interval getCoord(int id)
	{
		return list_coords.get(id);
	}

	@Override
	public Interval getInterval(int id)
	{
		return list_cara_interval.get(id);
	}

	@Override
	public Number getNumber(int id)
	{
		return list_cara_number.get(id);
	}

	@Override
	public String getString(int id)
	{
		return list_cara_string.get(id);
	}
}
