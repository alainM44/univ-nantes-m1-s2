package middleware;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import middleware.IInformation;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "Middleware";

	// The shared instance
	private static Activator plugin;
	private static IFlux Acquisition;
	private static IInformation fbm;
	private static IReasoning Reasoning;
	/**
	 * The constructor
	 */
	public Activator() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
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
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
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
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public void transform() {
		System.out.println("chat");
		// IInformation fbm;
		EventfaceB ev = new EventfaceB(1, "restFB Test3");
		// TODO Auto-generated method stub
		for (IConfigurationElement element : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.Acquisition")) {
			System.out.println("Acquistion" + element.getAttribute("Nom"));
		}
		for (IConfigurationElement element : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.Reasoning")) {
			try {
				System.out.println(element.getAttribute("class"));
				Reasoning=(IReasoning)element.createExecutableExtension("class");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (Reasoning!=null){
			System.out.println(Reasoning.happenEvenement().getMessage());
		}
		
		
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.NetP")) {
			System.out.println("NetP   " + elem.getAttribute("class"));
			try {
				System.out.println("yes1");
				//fbm = (IInformation) elem.createExecutableExtension("class");
				System.out.println("yes12");

				// fbm.postMessage(ev);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	//	System.out.println(fbm);
		//fbm.setAccessToken("AAAEqzYLDMcIBABXntYsSIheJiZC2AEZBwZCqfagmidbZB4ftiB0HQDjEhVuUn18jMdxZAlC95QoiZCezq8Hz88ujZBZCR7aZBu9cYJ82IiHNb2iDVkhFxeFP0");
	
		//fbm.postMessage(ev);
		traitement();
	}
	
	private void traitement(){
		double freq=1;//j'ai mis 1 au hasard
		Acquisition.setFrequence(freq);
		Acquisition.start();
		IEvent ev=null;
		BufferedImage itIm =Acquisition.next() ;
		while(itIm!=null && ev==null){
			Reasoning.reasonedOnImame(itIm,freq );
			ev=Reasoning.happenEvenement();
		}
		if (ev!=null){
			fbm.postMessage(ev);
		}
	}
}
