package reasoning;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.ui.internal.ExceptionHandler;

import java.awt.image.BufferedImage;

import middleware.IReasoning;
import middleware.IEvent;

public class Alerte implements IReasoning {
	private  final int nbImagesombre= 75;
	private IAnalyse analyse;
	private int compteurSombre = 0;
	private Event e;
	

	public Alerte() throws CoreException {
		// TODO Auto-generated constructor stub
		for (IConfigurationElement element : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Reasoning.ReasoningPExAn")) {
			analyse = (IAnalyse) element.createExecutableExtension("class");
		}
		e=null;
	}


	@Override
	/*
	 * Fonction qui renvois un evenement
	 */
	public IEvent getEvenement() {
		if (compteurSombre == nbImagesombre){
			compteurSombre = 0;
			e=new Event();
			e.setaMessage("Il fait nuit");
		}
		return e;
	}
	/**
	 * Fonction qui permet d'analyser un image
	 * @param im image a analyser 
	 */
	public void reasonedOnImage(BufferedImage im) {
		System.out.println(" ya un image");
		if (im != null) {//fin de l'annalyse
			if (analyse.makeAnalyse(im)) {
				compteurSombre++;
			} else {
				compteurSombre = 0;
			}

		} else {
			e= new Event();
			e.setaMessage("Il ne s'est rien passé");
		}

	}
}