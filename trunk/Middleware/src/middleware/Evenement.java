package middleware;

/**
 * 
 * 
 * Class implémentant IEvent
 * @author Bizet Chaline Marguerite Rince 
 * @see IEvent
 */
public class Evenement implements IEvent {
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Le vidéo devient sombre pendant 3 secondes";
	}

}
