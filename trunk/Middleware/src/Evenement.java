import middleware.IEvent;


public class Evenement implements IEvent {
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Le vidéo devient sombre pendant 3 secondes";
	}

}
