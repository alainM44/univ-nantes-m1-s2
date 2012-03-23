package acquisition;

import javax.imageio.ImageIO;
import javax.media.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.control.FrameGrabbingControl;
import javax.media.control.FramePositioningControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

import middleware.IFlux;

public class Acquisition implements ControllerListener, IFlux
{

	private Buffer buf;
	private Image img;
	private BufferToImage btoi;
	private Player player;
	private Time timeFrequence;
	private FramePositioningControl fpc;
	private FrameGrabbingControl fgc;
	private BufferedImage bufImage;
	private URL u;
	private double debut;
	MediaLocator mrl;

	// Constructeur
	/**
	 * Classe récupérant des images du fichier fourni en paramètre.
	 * 
	 * @param a
	 *            L'adresse du fichier à lire
	 * @param delai
	 *            Le temps en secondes qui s'ecoule entre chaque prise d'image.
	 */
	public Acquisition()
	{
		buf = null;
		img = null;
		btoi = null;
		player = null;
		timeFrequence = new Time(1);
		fpc = null;
		fgc = null;
		bufImage = null;
		u = null;
		debut = 0;
		mrl = null;
	}

	/*
	 * On implimente la fonction controllerUpdate de la classe
	 * ControllerListener qui nous permet de gérer les évenemnts du player.
	 */
	public void controllerUpdate(ControllerEvent ce)
	{
		Time duration = player.getDuration();
		/*
		 * une fois les ressources et les informations nécessaires pour le média
		 * sont reconnus on passe au traitement.
		 */
		if (ce instanceof RealizeCompleteEvent)
		{

			if (fpc == null)
			{
				System.out
						.println("Le média ne supporte pas les FramePositioningControl.");
			}
			else
			{

				/*
				 * boucle de parcours des images de le média.
				 */
				if (player.getMediaTime().getNanoseconds() <= duration
						.getNanoseconds())
				{

					/*
					 * On capture l'image pointé par le player.
					 */

					fgc = (FrameGrabbingControl) player
							.getControl("javax.media.control.FrameGrabbingControl");
					/*
					 * On met l'image dans un buffer.
					 */
					buf = fgc.grabFrame();
					/*
					 * On convertit l'image dans le buffer.
					 */
					btoi = new BufferToImage((VideoFormat) buf.getFormat());
					img = btoi.createImage(buf);
					bufImage = new BufferedImage(img.getWidth(null), img
							.getHeight(null), BufferedImage.TYPE_INT_RGB);
					/*
					 * Ici vous pouvez soit enregistrez l'image ou bien
					 * l'afficher dans un JPanel ... je vous laisse e choix.
					 */

					/*
					 * La fonction skip nous permet d'avancer dans les images
					 * par exemple si le player pointe sur l'image 45 de le
					 * média et on fait dest = fpc.skip(10); le player pointera
					 * sur l'image 55. Pour la fonction seek on peut accéder
					 * directement à l'image qu'on désire par exemple quelque
					 * soit l'image sur laquelle pointe le player et on fait
					 * dest = fpc.seek(10) le player pointera sur l'image 10.
					 */
					fpc.skip(fpc.mapTimeToFrame(timeFrequence));// avec dest =
					// fpc.seek(NomImage);
					// ça
					// marche aussi.
				}
				else
					bufImage = null;
			}
		}
	}

	/*
	 * La fonction main. N'oubliez pas de changer le chemin de la video.
	 */
	public static void main(String[] args) throws NoPlayerException,
			IOException
	{
		Acquisition a = new Acquisition();
		a.setDebut(0);
		a.setFile("comptes/E074862X/workspace/Acquisition/lostinspace.mov");
		a.setFrequence(5);
		a.start();
//		System.out.println(a.next().getHeight());
	}

	public void creerImage(Image image, String adr)
	{
		try
		{
			BufferedImage bufImage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			bufImage.getGraphics().drawImage(image, 0, 0, null);

			ImageIO.write(bufImage, "jpg", new File(adr));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Done");

	}

	@Override
	public double getDuree()
	{
		if (player != null)
			return player.getDuration().getSeconds();
		else
			return 0;
	}

	@Override
	public BufferedImage next()
	{
		player.realize();
		return bufImage;
	}

	@Override
	public void setDebut(double t)
	{
		debut = t;
	}

	@Override
	public void setFrequence(double t)
	{
		timeFrequence = new Time(t);
	}

	@Override
	public void setFile(String file)
	{
		try
		{
			u = new URL("file:///" + file);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		/* La création du lecteur et le chargement du fichier à lire. */
		mrl = new MediaLocator("file:///" + file);

	}

	@Override
	public void start()
	{
		try
		{
			Manager.createPlayer(u);
			player = Manager.createPlayer(mrl);
		}
		catch (NoPlayerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * L'ajout d'un écouteur sur le player pour pouvoir gérer les évenements
		 * de ce dernier
		 */
		player.addControllerListener(this);
		/*
		 * Cette fonction permet au player d'acquérir toutes les informations et
		 * toutes les ressources qui lui sont nécessaires sur le média
		 */
		fpc = (FramePositioningControl) player
				.getControl("javax.media.control.FramePositioningControl");
	//	fpc.skip(fpc.mapTimeToFrame(new Time(debut)));

	}

}
