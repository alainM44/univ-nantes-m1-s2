import java.awt.image.BufferedImage;
import reasoning.IAnalyse;

public class Analyse implements IAnalyse {

	public Analyse() {
	}

	@Override
	public boolean makeAnalyse(BufferedImage image) {
		int palier = 50;
		int rgb = 0, red = 0, green = 0, blue = 0;
		int dark = 0;

		int hauteur = image.getHeight();
		int largeur = image.getWidth();

		for (int i = 0; i < hauteur; i = hauteur > 1000 ? i + 2 : i + 1) {
			for (int j = 0; j < largeur; j = largeur > 1000 ? j + 2 : j + 1) {
				rgb = image.getRGB(j, i);
				red = (rgb >> 16) & 0xFF;
				green = (rgb >> 8) & 0xFF;
				blue = (rgb) & 0xFF;
				// Si l'une des couleurs est trop sombre, ie si sa valeur est
				// trop proche de 0
				// Alors l'algorithme considère que la couleur est sombre
				if (red < palier || green < palier || blue < palier) {
					++dark;// Attention ! cycle processeur en moins si ++dark au
							// lieu de dark++ ! peut-etre !
				}
			}
		}

		return (1.0 * dark / (hauteur * largeur) > 0.5);
	}

}
