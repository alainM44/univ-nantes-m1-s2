package proxy;

//import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NotSelfArgHandler implements InvocationHandler
{
	Object target;

	public NotSelfArgHandler(Object o)
	{
		super();
		target = o;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		System.out.println("chat");
		System.out.println(method.getName());
		System.out.println(method.getAnnotation(NotSelfArgument.class));
			
		if (method.getAnnotation(NotSelfArgument.class) != null)
			for (Object a : args)
				if ((a == proxy) && (a == target))
					throw new RuntimeException("beuuuh");
		return method.invoke(target, args);
	}
}
