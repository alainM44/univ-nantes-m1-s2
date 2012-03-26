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
		a.setFrequence(5);
		a.start();
		System.out.println("chat");
		a = null;
		System.out.println("chien");

	}

}
