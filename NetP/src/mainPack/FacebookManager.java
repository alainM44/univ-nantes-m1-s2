package mainPack;

import java.io.InputStream;

import middleware.IEvent;
import middleware.IInformation;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

/**
 * <b>FacebookManager est la classe proposée implémentant IIformation proposée
 * par le contributeur NetP<b>
 * <p>
 * FacebookManager contient les attributs suivants :
 * <ul>
 * <li>Un FacebookClient, représentant le titulaire du compte Facebook.</li>
 * <li>Un AccessToken, clef de sécurité temporaire.</li>
 * </ul>
 * </p>
 * 
 * @author Bizet Chaline Marguerite Rince
 * 
 * @version 1.0
 */

public class FacebookManager implements IInformation
{

	/**
	 * Le compte Facebook à utiliser.
	 */

	private FacebookClient ClientFB;
	/**
	 * Clef de sécurité temporaire à fournir par le propriétaire
	 */
	private String AccessToken;

	/**
	 * Constructeur par défaut.
	 */
	public FacebookManager()
	{
		super();
		AccessToken = "";
		ClientFB = new DefaultFacebookClient();
	}

	/**
	 * Constructeur de la classe FacebookManager
	 * 
	 * @param accessToken
	 *            clef de sécurité temporaire
	 */
	public FacebookManager(String accessToken)
	{
		super();
		AccessToken = accessToken;
		ClientFB = new DefaultFacebookClient(accessToken);
	}

	/**
	 * Retourne le client facebook
	 * 
	 * @return l'instance courante du compte client utilisé
	 * @see FacebookManager#ClientFB
	 */
	public FacebookClient getClientFB()
	{
		return ClientFB;
	}

	/**
	 * Setteur de ClientFB
	 * 
	 * @param clientFB
	 *            nouvelle valeur pour ClientFb
	 * @see FacebookManager#ClientFB
	 */
	public void setClientFB(FacebookClient clientFB)
	{
		ClientFB = new DefaultFacebookClient(AccessToken);
	}

	/**
	 * 
	 * @return l'acces token sous la forme d'une chaîne de caractère
	 * @see FacebookManager#AccessToken
	 */
	public String getAccessToken()
	{
		return AccessToken;
	}

	public void setAccessToken(String accessToken)
	{
		AccessToken = accessToken;
		ClientFB = new DefaultFacebookClient(AccessToken);
	}

	/**
	 * Implémentation de la méthode de IIformation.
	 * 
	 * @see IInformation#postMessage(IEvent)
	 */
	@Override
	public void postMessage(IEvent evFB)
	{
		System.out.println("salut");
		FacebookType publishMessageResponse = ClientFB.publish("me/feed", FacebookType.class, Parameter.with("message", evFB.getMessage()));
		System.out.println("Published message ID: " + publishMessageResponse.getId());
	}


	@Override
	public void postPicture(IEvent evFB) {
		// TODO Auto-generated method stub
		
	}


	public void postVideo(IEvent ecVF)
	{
		// TODO Auto-generated method stub

	}

}
