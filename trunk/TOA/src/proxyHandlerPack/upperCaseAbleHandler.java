package proxyHandlerPack;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import mainPack.UpperCaseAble;

public class upperCaseAbleHandler implements InvocationHandler
{
	private Object cible;
	private boolean upper = false;
	private Method isUpper, toggle;
	
	public	upperCaseAbleHandler(Object a) throws SecurityException, NoSuchMethodException
	{
		cible = a;
		upper = false;
		if (isUpper == null)
		{
			isUpper = UpperCaseAble.class.getMethod("isUpperCase", null);
			toggle =  UpperCaseAble.class.getMethod("toggleUpperCase", null);
		}
	}
	
	@Override
	public Object invoke(Object proxy, Method m, Object[] param) throws Throwable 
	{
		if (m.equals(toggle))
		{
			upper = !upper;
			return null;
		}
		if (m.equals(isUpper))
		{
			return upper;
		}
		
		Object result = m.invoke(cible, param); // presentation ou result
		
		if (upper && result != null && result.getClass().getCanonicalName().equals("java.lang.String"))
		{
			return ((String)result).toUpperCase();
		}
		
		return result;
	}
	
}
