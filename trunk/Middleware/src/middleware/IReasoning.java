package middleware;

import java.awt.image.BufferedImage;

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
