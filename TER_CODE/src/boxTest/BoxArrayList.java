package boxTest;

import interfaces.IBox;

import java.util.ArrayList;

public class BoxArrayList implements IBox {
	private int id;
	private ArrayList<Interval> list_coords;
	private ArrayList<String> list_cara_string;
	private ArrayList<Number> list_cara_number;
	private ArrayList<Interval> list_cara_interval;

	public BoxArrayList(int id, ArrayList<Interval> listCoords,
			ArrayList<String> listCaraString, ArrayList<Number> listCaraNumber,
			ArrayList<Interval> listCaraInterval) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara_string = listCaraString;
		list_cara_number = listCaraNumber;
		list_cara_interval = listCaraInterval;
	}

	public BoxArrayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoxArrayList(int i) {
		super();
		id = i;
		list_coords = new ArrayList<Interval>(100);
		list_cara_string = new ArrayList<String>(7);
		list_cara_number = new ArrayList<Number>(7);
		list_cara_interval = new ArrayList<Interval>(7);
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
		list_cara_number.add(cara);
	}

	@Override
	public void addCara(int id, String cara) {
		list_cara_string.add(cara);
	}

	@Override
	public void addCara(int id, Interval cara) {
		list_cara_interval.add(cara);
	}

	@Override
	public void addCoord(int id, Interval coord) {
		list_coords.add(coord);

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
