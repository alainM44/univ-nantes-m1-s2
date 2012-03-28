package acquisition;

public class main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Acquisition a = new Acquisition();
		
		a.setFile("/comptes/E074862X/workspace/Acquisition/darkcity.mov");
		a.start();
		a.getDuree();
//a.next();
//a.next();

	}

}
