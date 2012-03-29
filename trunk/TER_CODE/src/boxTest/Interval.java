package boxTest;


public class Interval {
	
	private  double BINF;
	private  double BSUP;
	
	
	public Interval(double bINF, double bSUP) {
		super();
		BINF = bINF;
		BSUP = bSUP;
	}



	public Interval plus(Interval I1,Interval I2)
	{
		Interval result= new Interval(I1.BINF+I2.BSUP, I1.BSUP+I2.BINF);
		return result;
	}
	public Interval minus(Interval I1,Interval I2)
	{
		Interval result= new Interval(I1.BINF+I2.BINF, I1.BINF+I2.BSUP);
		return result;

	}
	
	public double getBINF() {
		return BINF;
	}
	public void setBINF(double bINF) {
		BINF = bINF;
	}
	public double getBSUP() {
		return BSUP;
	}
	public void setBSUP(double bSUP) {
		BSUP = bSUP;
	}
	
	

}
