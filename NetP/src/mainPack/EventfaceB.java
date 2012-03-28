package mainPack;

import java.awt.image.BufferedImage;

import middleware.IEvent;

/**
 * Classe décrivant une action à poster sur facebook destiné à être posté sur
 * facebook
 * 
 * @author Bizet Chaline Marguerite Rince
 * 
 */
public class EventfaceB implements IEvent
{
	/**
	 * Un événement est composé d'un Id et du message à poster
	 */
	private Integer id;
	private String message;

	/**
	 * Constructeur par défaut
	 */
	public EventfaceB()
	{
		super();

	}

	/**
	 * Evenement facebook avec image
	 * 
	 * @param id
	 *            id de l'événement
	 * @param message
	 *            texte à poster sur Facebook
	 */
	public EventfaceB(Integer id, String message, BufferedImage image)
	{
		super();
		this.id = id;
		this.message = message;
	}

	/**
	 * Evenement facebook sans image
	 * 
	 * @param id
	 *            id de l'événement
	 * @param message
	 *            texte à poster sur Facebook
	 */
	public EventfaceB(Integer id, String message)
	{
		super();
		this.id = id;
		this.message = message;
	}

	/**
	 * Getters and Setters
	 * 
	 * @return
	 */
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}


}
