import java.awt.image.BufferedImage;
import reasoning.IAnalyse;


public class Analyse implements IAnalyse {

	public Analyse() {
	}
	@Override
	public Object makeAnalyse(BufferedImage bi) {
		System.out.println("Hello je suis l'analyseur du reasoning");
		return null;
	}

}
