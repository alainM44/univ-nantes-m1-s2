package middleware;

/**
 * IIformation est l'interface définissant les «comportements» que doit
 * impérativement fournir les contributeurs du point d'extension NetP.
 * 
 * 
 * @author Bizet Chaline Marguerite Rince
 * 
 * @version 1.0
 */

public interface IInformation
{
	/**
	 * Poster un simple message sur un mur de compte facebook.
	 * 
	 * @param ecVF
	 *            Evenement contenant les informations à poster (ici le contenu
	 *            du message.
	 * @see IEvent
	 */
	public void postVideo(IEvent ecVF);

	/**
	 * Poster une photo avec texte ou non sur un mur de compte facebook.
	 * 
	 * @param evFB
	 *            Evenement contenant les informations à poster (ici le contenu
	 *            du message.
	 * @see IEvent
	 */
	public void postMessage(IEvent evFB);

	/**
	 * Poster une vidéo sur un mur de compte facebook.
	 * 
	 * @param evFB
	 *            Evenement contenant les informations à poster (ici le contenu
	 *            du message.
	 * @see IEvent
	 */
	public void postPicture(IEvent evFB);

	/**
	 * Fournir la clef de sécurité permissible . Indispensable pour chaque
	 * opération sur Facebook
	 * 
	 * @param accessToken
	 *            clef de sécurité sous la forme d'une chaîne de caractères.
	 */
	public void setAccessToken(String accessToken);

	/**
	 * Fonction permettant de prendre en compte les paramètres du proxy de la faculté.
	 */
	public void setProxy();

}
