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
public class Activator extends AbstractUIPlugin
{

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
	 * false (non-Javadoc)
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
	 * @return the image descriptnstance or
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	/**
	 * Fonction qui permet de charger le plugin NetP
	 */
	public void setInformationInstance()
	{
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.NetP"))
		{
			try
			{
				currentInformationFactory = (IInformation) elem
						.createExecutableExtension("class");
			}
			catch (CoreException e1)
			{

				e1.printStackTrace();
			}
		}

	}
	/**
	 * Fonction qui permet de poster un message d'alerte par l'intermediaire du plugin NetP
	 * 
	 * @param token 
	 */
	public void postMessage(String token)
	{
		if (currentInformationFactory == null)
			setInformationInstance();
		System.out.println("avant setacces");
		currentInformationFactory.setAccessToken(token);
		System.out.println("nouveau message");
		if(event==null)
			System.err.println("nullllllll");
		currentInformationFactory.postMessage(event);

	}
	
	/**
	 * Fonction qui permet de charger un plugin d'acquisition d'image 
	 */
	public void setFluxInstance()
	{
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.Acquisition"))
		{
			try
			{
				flux = (IFlux) elem.createExecutableExtension("class");
				System.out.println("instance crée");
			}
			catch (CoreException e1)
			{
				e1.printStackTrace();
			}
		}

	}
	/**
	 * Fonction qui permet de charger le plugin reasoning
	 */
	public void intialiserReasoning()
	{
		if (Reasoning == null)
			setReasoningInstance();
	}
/**
 * Fonction qui permet d'initialiser le plugin d'acquisition
 * @param path
 */
	public void intialiserFlux(String path)
	{
		if (Reasoning == null)
			setFluxInstance();
		System.out.println("Flux init");
		flux.setFile(path);
		flux.start();
	}

	/**
	 * Fonction qui permet de charger le plugin Reasoning
	 */
	public void setReasoningInstance()
	{
		for (IConfigurationElement elem : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Middleware.Reasoning"))
		{
			try
			{
				Reasoning = (IReasoning) elem
						.createExecutableExtension("class");
				System.out.println("instance crée");
			}
			catch (CoreException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	/**
	 * Fonction qui permet de faire transiter les images
	 *  du plugin d'aquisition au plugin de raisonnement
	 */
	public void sendFluxToReasoning()
	{
		System.out.println("toto a une image");
		IEvent ev = null;
		BufferedImage itIm = flux.next();
		System.out.println(itIm);
		while (itIm != null && ev == null)
		{
			Reasoning.reasonedOnImage(itIm);
			ev = Reasoning.getEvenement();
			itIm = flux.next();
		}
		if (ev != null)
		{
			event = ev;
			System.out.println("zeee");
		}
	}

}
