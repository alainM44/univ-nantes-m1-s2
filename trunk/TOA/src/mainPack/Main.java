package mainPack;

import personnePack.Client;
import personnePack.IPersonne;
import proxyHandlerPack.MonProxy;

public class Main 
{
	public static void main(String args[]) throws SecurityException, NoSuchMethodException 
	{
		IPersonne p = new Client();
		MonProxy proxy = new MonProxy();
		p = (IPersonne)proxy.proxyfy(p);
		p.setNom("TestNom");
		p.setPrenom("TestPreNom");
		p.setAge(20);

		((IPersonne) p).presentation();

		((UpperCaseAble)p).toggleUpperCase();

		System.out.println(((IPersonne)p).toString());
	}
}
