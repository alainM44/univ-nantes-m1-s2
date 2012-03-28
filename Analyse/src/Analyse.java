import java.awt.image.BufferedImage;
import reasoning.IAnalyse;

public class Analyse implements IAnalyse {

	/**
	 * Constructeur par defaut de l'objet Analyse
	 */
	public Analyse() {
	}

	@Override
	/**
	 * Méthode déterminant si une image doit être considérée comme étant sombre
	 * @param image L'image qui doit être analysée
	 * @return un boolean, true si l'image est sombre, false sinon
	 */
	public boolean makeAnalyse(BufferedImage image) {
		// palier à ne pas dépasser pour qu'une couleur soit considérée comme sombre
		int palier = 100;
		// valeur des canaux de couleurs
		int rgb = 0, red = 0, green = 0, blue = 0;
		// nombre de pixels sombres
		int dark = 0;

		// nombre de pixels en hauteur
		int hauteur = image.getHeight();
		// nombre de pixels en largeur
		int largeur = image.getWidth();

		// Parcours de chaque pixel de l'image
		// si l'image est trop grande, l'algorithme ne parcours que la moitié des pixels
		// Gain de temps d'execution théorique, mais pas indispensable.
		for (int i = 0; i < hauteur; i = hauteur > 1000 ? i + 2 : i + 1) {
			for (int j = 0; j < largeur; j = largeur > 1000 ? j + 2 : j + 1) {
				rgb = image.getRGB(j, i);
				red = (rgb >> 16) & 0xFF;
				green = (rgb >> 8) & 0xFF;
				blue = (rgb) & 0xFF;
				// Si l'une des couleurs est trop clair, ie si sa valeur est
				// trop éloignée de 0
				// Alors l'algorithme considère que la couleur est n'est pas sombre
				// sinon on incrémente le nombre de pixel sombre
				if (red < palier && green < palier && blue < palier) {
					++dark;
				}
			}
		}

		return (1.0 * dark / (hauteur * largeur) > 0.5);
	}

}
