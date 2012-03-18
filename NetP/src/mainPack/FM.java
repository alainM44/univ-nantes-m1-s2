package mainPack;


import middleware.IEvent;
import middleware.IInformation;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FM implements IInformation
{
	private FacebookClient ClientFB;
	private String AccessToken;

	
	public FM()
	{
		super();
		AccessToken="";
	 FacebookClient ClientFB = new DefaultFacebookClient();
		// TODO Auto-generated constructor stub
	}

	public void setClientFB(FacebookClient clientFB)
	{
		ClientFB = clientFB;
	}

	

	public void setAccessToken(String accessToken)
	{
		AccessToken = accessToken;
	}


	@Override
	public void postMessage(IEvent evFB)
	{
		FacebookType publishMessageResponse = ClientFB.publish("me/feed", FacebookType.class, Parameter.with("message","salut"));
		System.out.println("Published message ID: " + publishMessageResponse.getId());

		// TODO Auto-generated method stub
		
	}

//	
//	@Override
//	public void postMessage(IEvent evFB)
//	{
//		postMessageContribute(evFB);
//	}
//	
//
//	public void postMessageContribute(IEvent evFB,String at,String mess)
//	{
//		System.out.println("FM");
//		DefaultFacebookClient c= new DefaultFacebookClient(at);
//		FacebookType publishMessageResponse = c.publish("me/feed", FacebookType.class, Parameter.with("message", mess));
//		System.out.println("Published message ID: " + publishMessageResponse.getId());
//
//		//postMessage(evFB);
//		// TODO Auto-generated method stub
//		
//	}

	

}
