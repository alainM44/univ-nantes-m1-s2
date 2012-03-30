package middleware;

import java.awt.image.BufferedImage;
/**
 * IReasoning est l'interface définissant les «comportements» que doit
 * impérativement fournir les contributeurs du point d'extension Raisoning.
 * 
 * 
 * @author Bizet Chaline Marguerite Rince
 * 
 * @version 1.0
 */
public interface IReasoning {
	/**
	 * 
	 * @return un evenement
	 */
	public IEvent getEvenement();

	/**
	 *Fonction qui permet de faire un analyse sur un image
	 *
	 *@param im Une image
	 */
	public void reasonedOnImage(BufferedImage im);
}
