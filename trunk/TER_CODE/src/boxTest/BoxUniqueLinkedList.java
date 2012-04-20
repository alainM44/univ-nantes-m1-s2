package boxTest;

import java.util.LinkedList;

import interfaces.IBox;

public class BoxUniqueLinkedList implements IBox {

	private int id;
	private LinkedList<Interval> list_coords;
	private LinkedList<Object> list_cara;

	public BoxUniqueLinkedList(int id, LinkedList<Interval> listCoords,
			LinkedList<Object> listCara) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara = listCara;

	}

	public BoxUniqueLinkedList(int i) {
		super();
		id = i;
		list_coords = new LinkedList<Interval>();
		list_cara = new LinkedList<Object>();

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
