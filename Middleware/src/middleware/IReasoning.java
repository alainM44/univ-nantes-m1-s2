package middleware;

import java.awt.image.BufferedImage;

public interface IReasoning {
	/**
	 * 
	 * @return null s'il n'y pas d'evenement
	 */
	public IEvent getEvenement();

	/**
	 * 
	 * @param im
	 *            un image
	 * @param time
	 *            en seconde
	 */
	public void reasonedOnImage(BufferedImage im);
}
