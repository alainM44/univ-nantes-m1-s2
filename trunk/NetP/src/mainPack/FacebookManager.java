package mainPack;

import middleware.IEvent;
import middleware.IInformation;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FacebookManager implements IInformation {

	private FacebookClient ClientFB;
	private String AccessToken;

	public FacebookManager() {
		super();
		AccessToken = "";
		ClientFB = new DefaultFacebookClient();
	}

	public FacebookManager(String accessToken) {
		super();
		AccessToken = accessToken;
		ClientFB = new DefaultFacebookClient(accessToken);
	}

	public FacebookClient getClientFB() {
		return ClientFB;
	}

	public void setClientFB(FacebookClient clientFB) {
		ClientFB = new DefaultFacebookClient(AccessToken);
	}

	public String getAccessToken() {
		return AccessToken;
	}

	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
		ClientFB = new DefaultFacebookClient(AccessToken);
	}

	@Override
	public void postMessage(IEvent evFB) {
		System.out.println("debut possssst");
		FacebookType publishMessageResponse = ClientFB.publish("me/feed",
				FacebookType.class, Parameter.with("message", "salur"));
		System.out.println("Published message ID: "
				+ publishMessageResponse.getId());
		System.out.println("fin post");

	}

	@Override
	public String toString() {
		return this.AccessToken;
	}

}
