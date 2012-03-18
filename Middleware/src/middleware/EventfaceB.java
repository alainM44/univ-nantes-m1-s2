package middleware;


import java.awt.image.BufferedImage;



/**
 * Classe décrivant une action à poster sur facebook destiné à être posté sur
 * facebook
 * 
 * @authors Bizet Marguerite Rince  nom de famille ???
 * 
 */
public class EventfaceB implements IEvent
{
	private Integer id;
	private String message;
	private BufferedImage image;

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
	 * @param image
	 *            image relative à l'événement
	 */
	public EventfaceB(Integer id, String message, BufferedImage image)
	{
		super();
		this.id = id;
		this.message = message;
		this.image = image;
	}
	
	/**
	 * Evenement facebook sans  image 
	 * 
	 * @param id
	 *            id de l'événement
	 * @param message
	 *            texte à poster sur Facebook
	 * @param image
	 *            image relative à l'événement
	 */
	public EventfaceB(Integer id, String message)
	{
		super();
		this.id = id;
		this.message = message;
		this.image = null;
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

	public BufferedImage getImage()
	{
		return image;
	}

	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

}
