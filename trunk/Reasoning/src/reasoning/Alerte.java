package reasoning;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.ui.internal.ExceptionHandler;

import java.awt.image.BufferedImage;

import middleware.IReasoning;
import middleware.IEvent;

public class Alerte implements IReasoning {
	private IAnalyse analyse;
	private boolean detection;
	public Alerte() throws CoreException {
		// TODO Auto-generated constructor stub
		for (IConfigurationElement element : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Reasoning.ReasoningPExAn")) {
				 analyse=(IAnalyse)element.createExecutableExtension("class");
		}
	}

	@Override
	public IEvent happenEvenement() {
		IEvent e=null;
		if (detection) e = (IEvent) new Event();
		return e;
	}

	@Override
	public void reasonedOnImame(BufferedImage im, double time) {
		Object o =analyse.makeAnalyse(im);
		//traitement de l'objet envoyer par l'analyse
	}

}
