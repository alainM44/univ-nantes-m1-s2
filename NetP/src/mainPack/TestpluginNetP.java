package mainPack;

/**
 * Test du plugin NetP
 * @author Bizet Chaline Marguerite Rince
 *
 */
public class TestpluginNetP
{

	/**
	 * @param args
	 *            Fournir le token d'acces en entrée.
	 */
	public static void main(String[] args)
	{
		FacebookManager fm = new FacebookManager(args[0]);
		EventfaceB ev = new EventfaceB(1, "Test du plugin NetP");
		fm.postMessage(ev);

	}

}
