package middleware;

public interface IInformation
{
	public void postVideo(IEvent ecVF);

	public void postMessage(IEvent evFB);

	public void postPicture(IEvent evFB);

	public void setAccessToken(String accessToken);

}
