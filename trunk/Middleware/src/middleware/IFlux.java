package middleware;

import java.awt.image.BufferedImage;

public interface IFlux
{
	/**
	 * Défini le moment où commence l'acquisition 
	 * @param t Temps de début d'acquisition en secondes
	 */
	void setDebut(double t);
	
	/**
	 * Défini la frequence d'acquisition
	 * @param t Temps entre chaque d'acquisition en secondes
	 */
	void setFrequence(double t);

	/**
	 * Défini le fichier à étudier. Obligatoire !
	 * @param file L'adresse du fichier à étudier.
	 */
	void setFile(String file);
	
	/**
	 * Donne la durée de la vidéo en secondes.
	 * 
	 * @return Le temps de la vidéo en secondes.
	 */
	double getDuree();

	/**
	 * Permet d'obtenir l'image capturé.
	 * 
	 * @return Un BufferedImage contenant l'image capturée.
	 */
	BufferedImage next();
	
	/**
	 * Permet de dire au plugin de lancer la saisie. Et que les paramètres ont été spécifiés
	 */
	void start();
}
