import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Box {
	private int id;
	private HashMap<Integer,Interval> list_coords;
	private HashMap<Integer, String> list_cara_string;
	private HashMap<Integer, Number> list_cara_number;
	private HashMap<Integer, Interval> list_cara_interval;

	public Box(int id, HashMap<Integer,Interval> listCoords,
			HashMap<Integer, String> listCaraString,
			HashMap<Integer, Number> listCaraNumber,
			HashMap<Integer, Interval> listCaraInterval) {
		super();
		this.id = id;
		list_coords = listCoords;
		list_cara_string = listCaraString;
		list_cara_number = listCaraNumber;
		list_cara_interval = listCaraInterval;
	}

	public Box(int i) {
		super();
		id=i;
		list_cara_string = new HashMap<Integer, String>();
		list_cara_number = new HashMap<Integer, Number>();
		list_cara_interval = new HashMap<Integer, Interval>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<Integer, Interval> getList_coords() {
		return list_coords;
	}

	public void setList_coords(HashMap<Integer, Interval> listCoords) {
		list_coords = listCoords;
	}
	

	public HashMap<Integer, String> getList_cara_string() {
		return list_cara_string;
	}

	public void setList_cara_string(HashMap<Integer, String> listCaraString) {
		list_cara_string = listCaraString;
	}

	public HashMap<Integer, Number> getList_cara_number() {
		return list_cara_number;
	}

	public void setList_cara_number(HashMap<Integer, Number> listCaraNumber) {
		list_cara_number = listCaraNumber;
	}

	public HashMap<Integer, Interval> getList_cara_interval() {
		return list_cara_interval;
	}

	public void setList_cara_interval(HashMap<Integer, Interval> listCaraInterval) {
		list_cara_interval = listCaraInterval;
	}
	
	
}
