import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


import personnePack.Client;
import proxy.NotSelfArgHandler;

public class main
{


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		//
		//		Loader myloader = new Loader("toto");
		//		Voiture myvoiture = (Voiture) myloader.creerInstance();
		//		System.out.println(myvoiture.getCouleur());


		//EXO1
//		A a = new A(1, 2, 3);
//		B b = new B();
//		Converter converter = new Converter();
//		b = (B) converter.conversion(a, B.class);
//		System.out.println(a.getJ());
//		System.out.println(a.getK());
//		System.out.println(b.getJ());
//		System.out.println(b.getK());
		
		//EXO2
//		ClassLoader.getSystemClassLoader();
//		Ipersonne i = proxy(i);
//		InvocationHandler handler  = NotSelfArgument()
	
//		Ipersonne p1 =  new Joe();
//		
//			p1 = (Ipersonne) Proxy.newProxyInstance(
//				Ipersonne.class.getClassLoader(),
//				new Class[] {Ipersonne.class},
//				new NotSelfArgHandler(p1)
//				);
//		p1.setConjoint(p1);
//		


	}

}
