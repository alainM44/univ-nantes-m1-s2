package middleware;

import java.awt.image.BufferedImage;

public interface IReasoning
{
	/**
	 * 
	 * @return null s'il n'y pas d'evenement
	 */
	public  IEvent happenEvenement();
	/**
	 * 
	 * @param im un image
	 * @param freq en seconde
	 */
	public void reasonedOnImame(BufferedImage im, double freq); 
}
