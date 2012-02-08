import java.lang.reflect.Method;

public class Converter
{

	public Converter()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isSetter(Method m)
	{
	
		return m.getName().startsWith("set") ;//&& (m.getReturnType() == null);
			//	&& m.getParameterTypes().length == 1;
	}

	public Method findMatchingGetter(Object source, Method setteur)
			throws SecurityException, NoSuchMethodException
	{
		Method result = source.getClass().getMethod(
				"g" + setteur.getName().substring(1), null);

		//CE TEST MARCHE PAS
//		if (setteur.getParameterTypes().getClass().isAssignableFrom(
//				result.getReturnType().getClass())) // SENS A VERIFIER
//		{
//			System.out.println("match");
//			return result;
//		}
	//		System.out.println("ERREUR getteur null");
		//return null;
		return result;
	}

	public Object conversion(Object source, Class<?> targetClass)
			throws Exception
	{
		Object result = targetClass.newInstance();
		for (Method m : targetClass.getMethods())
		{
			if (isSetter(m))
			{
				System.out.println("setteur");
				Method getter = findMatchingGetter(source, m);
				if (getter != null)
				{
					m.invoke(result, getter.invoke(source, null));
					System.out.println("set" + m.getName());
				}

			}
			}
		return result;
	}

}
