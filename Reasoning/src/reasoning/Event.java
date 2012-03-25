package reasoning;

import middleware.IEvent;

public class Event implements IEvent {

	private int seconde;
	private String aMessage;
	public Event() {
		seconde=0;
		aMessage="";
	}
	@Override
	public String getMessage() {
		return "At: "+seconde+" secondes"+"Event: "+aMessage ;
	}
	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}
	public int getSeconde() {
		return seconde;
	}
	public void setaMessage(String aMessage) {
		this.aMessage = aMessage;
	}
	public String getaMessage() {
		return aMessage;
	}

}
