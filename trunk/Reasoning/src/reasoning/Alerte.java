package reasoning;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import java.awt.image.BufferedImage;

import middleware.IReasoning;
import middleware.IEvent;

public class Alerte implements IReasoning {

	public Alerte() {
		// TODO Auto-generated constructor stub
		for (IConfigurationElement element : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Reasoning.ReasoningPExAn")) {
			try {
				IAnalyse an=(IAnalyse)element.createExecutableExtension("class");
				if(an!=null) an.makeAnalyse(null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				System.out.println("Analyseur non crée");
			}
		}
	}

	@Override
	public IEvent happenEvenement() {
		System.out.println("Hello je suis resonnig");
		IEvent e = (IEvent) new Event();
		return e;
	}

	@Override
	public void reasonedOnImame(BufferedImage im, int time) {

	}

}
