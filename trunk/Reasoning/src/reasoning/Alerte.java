package reasoning;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.ui.internal.ExceptionHandler;

import java.awt.image.BufferedImage;

import middleware.IReasoning;
import middleware.IEvent;

public class Alerte implements IReasoning {
	private final int nbseconde=3;
	private IAnalyse analyse;
	private int compteurSombre=0;
	private int frequence;
	public Alerte() throws CoreException {
		// TODO Auto-generated constructor stub
		for (IConfigurationElement element : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("Reasoning.ReasoningPExAn")) {
				 analyse=(IAnalyse)element.createExecutableExtension("class");
		}
	}
	
	public void setFrequence(int freq){
		frequence=freq;
	}
	
	@Override
	public IEvent getEvenement() {
		IEvent e=null;
		if (compteurSombre==frequence*nbseconde){
			e = (IEvent) new Event();
			compteurSombre=0;
		}
		return e;
	}

	public void reasonedOnImage(BufferedImage im) {
		if( analyse.makeAnalyse(im)){
			compteurSombre++;
		}
		else{
			compteurSombre=0;
		}
		
	}

	
}