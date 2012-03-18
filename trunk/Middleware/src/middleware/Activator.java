package middleware;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import middleware.IInformation;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "Middleware";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator()
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public void transform()
	{
		System.out.println("chat");
		EventfaceB ev = new EventfaceB(1, "restFB Test3");
		// TODO Auto-generated method stub
		for (IConfigurationElement element : RegistryFactory.getRegistry().getConfigurationElementsFor("Middleware.Acquisition"))
		{
			System.out.println("Acquistion"+element.getAttribute("Nom"));
		}
		for (IConfigurationElement element : RegistryFactory.getRegistry().getConfigurationElementsFor("Middleware.Reasoning"))
		{
			System.out.println(element.getAttribute("Reasoning"+"Alerte"));
			/*
			 * for(String s:element.getAttributeNames()){
			 * System.out.println("#"+s+"#"); }
			 */

		}
		for (IConfigurationElement elem : RegistryFactory.getRegistry().getConfigurationElementsFor("Middleware.NetP"))
		{
			System.out.println("NetP"+elem.getAttribute("class"));
			try
			{
				
				IInformation fbm = (IInformation) elem.createExecutableExtension("class");
				
				System.out.println("yes");
				//fbm.postMessage(ev);
			} catch (CoreException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
