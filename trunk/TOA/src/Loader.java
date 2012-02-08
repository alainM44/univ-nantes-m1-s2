
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;



public class Loader
{
	Properties prop;
	public Loader(String filename) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
	{
		super();
		prop = new Properties();
		prop.load(new FileReader(filename));
	}

	public Object creerInstance() throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Class<?> d = Class.forName((String)prop.getProperty("class"));
		Object o =  d.newInstance();
		
		for ( Map.Entry <Object, Object> entry : prop.entrySet())
		{
			if(!entry.getKey().equals("class"))
			{
				Method setter = d.getMethod((String)entry.getKey(),String.class);
				setter.invoke(o,entry.getValue());
			}
		}
		return o;
	}
}
