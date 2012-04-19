package interfaces;

import boxTest.Interval;

public interface IBox {

	public int getId();

	public void setId(int id);



	
	public void addCara(int id, Number cara);
	
	public void addCara(int id, String cara);
	
	public void addCara(int id, Interval cara);
	
	public void addCoord(int id, Interval coord);
	
	public Number getNumber(int id);
	
	public String getString(int id);
	
	public Interval getInterval(int id);
	
	public Interval getCoord(int id);
}
