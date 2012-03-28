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
	public IEvent getEvenement() {
		if (compteurSombre == nbImagesombre){
			compteurSombre = 0;
			e.setaMessage("Il fait nuit");
		}
		return e;
	}

	public void reasonedOnImage(BufferedImage im) {
		if (im != null) {//fin de l'annalyse
			if (analyse.makeAnalyse(im)) {
				compteurSombre++;
			} else {
				compteurSombre = 0;
			}

		} else {
			e= new Event();
		}

	}
}