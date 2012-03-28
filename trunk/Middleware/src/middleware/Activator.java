package middleware;

import java.awt.image.BufferedImage;

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
	private static IInformation currentInformationFactory;
	private static IReasoning Reasoning;
	private static IFlux flux;
	private static Activator plugin;
	private IEvent event;
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

	/*false
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
	 * @return the image descriptnstance or
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public void setInformationInstance() {
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.NetP")) {
			try {
				currentInformationFactory = (IInformation) elem
						.createExecutableExtension("class");

				System.out.println("instance crée");
			} catch (CoreException e1) {

				e1.printStackTrace();
			}
		}

	}

	public void postMessage(String token) {
		if (currentInformationFactory == null)
			setInformationInstance();

		currentInformationFactory.setAccessToken(token);
		currentInformationFactory.postMessage(event);

	}

	public void setFluxInstance() {
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.Acquisition")) {
			try {
				flux = (IFlux) elem.createExecutableExtension("class");
				System.out.println("instance crée");
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void intialiserReasoning() {
		if (Reasoning == null)
			setReasoningInstance();
	}

	public void intialiserFlux(String token) {
		if (Reasoning == null)
			setFluxInstance();
		System.out.println("Flux init");
		flux.setFile(token);
		flux.start();
	}

	public void setReasoningInstance() {
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.Reasoning")) {
			try {
				Reasoning = (IReasoning) elem
						.createExecutableExtension("class");
				System.out.println("instance crée");
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
			double freq = 1;// j'ai mis 1 au hasard
		}
	}
	void setEvent(IEvent e){
		event=e;
	}
	public void SendFluxToReasoning() {
		System.out.println("toto a une image");
		IEvent ev = null;
		BufferedImage itIm = flux.next();
		while (itIm != null && ev == null) {
			Reasoning.reasonedOnImage(itIm);
			ev = Reasoning.getEvenement();
			itIm = flux.next();
		}
		if (ev != null) {
			event= ev;
		}
	}

}
