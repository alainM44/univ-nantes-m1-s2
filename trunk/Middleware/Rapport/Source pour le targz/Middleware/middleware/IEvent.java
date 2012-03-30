package middleware;

/**
 * IEvent est l'interface définissant les «comportements» que doit
 * impérativement fournir un évévement. Evenements utilisés par les implémentation de IIformation
 * 
 * 
 * @author Bizet Chaline Marguerite Rince
 * 
 * @version 1.0
 */
public interface  IEvent {
	/**
	 * Renvoit le message de l'événement 
	 * @return un message sous la forme d'une string.
	 */
	public String getMessage();
}
