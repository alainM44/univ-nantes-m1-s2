package boxTest;

import java.util.LinkedList;

import interfaces.IBox;

public class BoxLinkedList implements IBox {
	private int id;
	private LinkedList<Interval> list_coords;
	private LinkedList<String> list_cara_string;
	private LinkedList<Number> list_cara_number;
	private LinkedList<Interval> list_cara_interval;

	public BoxLinkedList(int id, LinkedList<Interval> listCoords,
			LinkedList<String> listCaraString,
			LinkedList<Number> listCaraNumber,
			LinkedList<Interval> listCaraInterval) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara_string = listCaraString;
		list_cara_number = listCaraNumber;
		list_cara_interval = listCaraInterval;
	}

	public BoxLinkedList(int i) {
		super();
		id = i;
		list_coords = new LinkedList<Interval>();
		list_cara_string = new LinkedList<String>();
		list_cara_number = new LinkedList<Number>();
		list_cara_interval = new LinkedList<Interval>();
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
		list_cara_number.add(id,cara);
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
