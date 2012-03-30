package reasoning;

import middleware.IEvent;

public class Event implements IEvent {

	private String message;
	public Event() {
		message="";
	}
	
	public void setaMessage(String aMessage) {
		this.message = aMessage;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
