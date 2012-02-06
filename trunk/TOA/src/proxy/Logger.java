package proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Logger implements InvocationHandler
{
	private Object taget;

	public Logger()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable{
		
			System.out.println("call"+method.getName()+ "on"+taget);
			
		return method.invoke(taget, args);
	}
	
	
}
