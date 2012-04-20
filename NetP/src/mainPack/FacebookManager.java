package mainPack;



import middleware.IEvent;
import middleware.IInformation;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FacebookManager implements IInformation
{

	private FacebookClient ClientFB;
	private String AccessToken;

	public FacebookManager()
	{
		super();
		AccessToken = "";
		ClientFB = new DefaultFacebookClient();
	}

	public FacebookManager(String accessToken)
	{
		super();
		AccessToken = accessToken;
		ClientFB = new DefaultFacebookClient(accessToken);
	}

	public FacebookClient getClientFB()
	{
		return ClientFB;
	}

	public void setClientFB(FacebookClient clientFB)
	{
		ClientFB = new DefaultFacebookClient(AccessToken);
	}

	public String getAccessToken()
	{
		return AccessToken;
	}

	public void setAccessToken(String accessToken)
	{
		System.setProperty("http.proxyHost","proxy.ensinfo.sciences.univ-nantes.prive");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("https.proxyHost","proxy.ensinfo.sciences.univ-nantes.prive");
		System.setProperty("https.proxyPort", "3128");

		AccessToken = accessToken;

		ClientFB = new DefaultFacebookClient(AccessToken);

	}

	@Override
	public void postMessage(IEvent evFB)
	{
		System.out.println("AT: "+AccessToken);
		FacebookType publishMessageResponse = ClientFB.publish("me/feed", FacebookType.class, Parameter.with("message",evFB.getMessage()));
		System.out.println("Published message ID: " + publishMessageResponse.getId());
	}

	@Override
	public String toString()
	{
		return this.AccessToken;
	}



	@Override
	public void postPicture(IEvent evFB)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postVideo(IEvent ecVF)
	{
		// TODO Auto-generated method stub
		
	}

}
