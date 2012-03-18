package mainPack;


import middleware.IEvent;
import middleware.IInformation;

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
		ClientFB = clientFB;
	}

	public String getAccessToken()
	{
		return AccessToken;
	}

	public void setAccessToken(String accessToken)
	{
		AccessToken = accessToken;
	}

	public void postMessage(EventfaceB evFB)
	{
		FacebookType publishMessageResponse = ClientFB.publish("me/feed", FacebookType.class, Parameter.with("message", evFB.getMessage()));
		System.out.println("Published message ID: " + publishMessageResponse.getId());

	}

	@Override
	public void postMessage(IEvent evFB)
	{
		postMessage(evFB);
		// TODO Auto-generated method stub
		
	}

}
