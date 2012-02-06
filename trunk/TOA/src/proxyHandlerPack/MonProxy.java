package proxyHandlerPack;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import mainPack.UpperCaseAble;

public class MonProxy
{
	public Object proxyfy (Object cible) throws SecurityException, NoSuchMethodException
	{
		InvocationHandler handler = new upperCaseAbleHandler(cible);
		Class<?>[]interf = addInterf(cible.getClass().getInterfaces(), UpperCaseAble.class);
		Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), interf, handler);
		
		return proxy;
	}
	
	private Class<?>[] addInterf(Class<?>[] interfacesInternes, Class<?> interfaceExterne)
	{
		int taille = interfacesInternes.length + 1;
		Class<?>[] interfProxy = new Class<?>[taille];
				
		for (int i = 0; i < interfacesInternes.length; i ++)
		{
			interfProxy[i] = interfacesInternes[i];			
		}
		interfProxy[taille-1] = interfaceExterne;
		
		return interfProxy;
	}
}
