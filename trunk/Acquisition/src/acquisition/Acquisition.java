package acquisition;

import javax.imageio.ImageIO;
import javax.media.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.media.control.FrameGrabbingControl;
import javax.media.control.FramePositioningControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

public class Acquisition implements ControllerListener
{

	Buffer buf = null;
	Image img = null;
	BufferToImage btoi = null;
	private Player player = null;
	private Time timeCapture;

	// Constructeur
	/**
	 * Classe récupérant des images du fichier fourni en paramètre.
	 * @param a L'adresse du fichier à lire
	 * @param delai Le temps en secondes qui s'ecoule entre chaque prise d'image.
	 */
	public Acquisition(String a, double delai) throws NoPlayerException, IOException
	{
		super();
		timeCapture = new Time(delai);
		URL u = new URL("file:///" + a);
		System.out.println(u.getFile());
		/* La création du lecteur et le chargement du fichier à lire. */
		MediaLocator mrl = new MediaLocator("file:///" + a);
		Manager.createPlayer(u);
		player = Manager.createPlayer(mrl);
		/*
		 * L'ajout d'un écouteur sur le player pour pouvoir gérer les évenements
		 * de ce dernier
		 */
		player.addControllerListener(this);
		/*
		 * Cette fonction permet au player d'acquérir toutes les informations et
		 * toutes les ressources qui lui sont nécessaires sur le média
		 */
		System.out
				.println("Acquisition des ressources et des informations média en cours.");
		System.out.println("Patientez S'il vous plait.");
		player.realize();

	}

	/*
	 * On implimente la fonction controllerUpdate de la classe
	 * ControllerListener qui nous permet de gérer les évenemnts du player.
	 */
	public void controllerUpdate(ControllerEvent ce)
	{
		/*
		 * une fois les ressources et les informations nécessaires pour le média
		 * sont reconnus on passe au traitement.
		 */
		if (ce instanceof RealizeCompleteEvent)
		{
			int numImage = 1;
			int dest;
			FramePositioningControl fpc;
			FrameGrabbingControl fgc;
			Time duration = player.getDuration();
			int totalFrames = FramePositioningControl.FRAME_UNKNOWN;
			fpc = (FramePositioningControl) player
					.getControl("javax.media.control.FramePositioningControl");
			if (fpc == null)
			{
				System.out
						.println("Le média ne supporte pas les FramePositioningControl.");
			}
			else
			{
				/*
				 * On calcul le nombre d'images dans le média.
				 */
				totalFrames = fpc.mapTimeToFrame(duration);
				System.out.println("Nombre total d'images dans le média : "
						+ totalFrames);

				/*
				 * boucle de parcours des images de le média.
				 */
				dest =fpc.skip(1);
				while (player.getMediaTime().getNanoseconds()<=duration.getNanoseconds())
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
					creerImage(img, "img/captured"+numImage+".jpg");
					
					/*
					 * Ici vous pouvez soit enregistrez l'image ou bien
					 * l'afficher dans un JPanel ... je vous laisse e choix.
					 */

					System.out.println("Image " + numImage + "extraite");
					/*
					 * La fonction skip nous permet d'avancer dans les images
					 * par exemple si le player pointe sur l'image 45 de le
					 * média et on fait dest = fpc.skip(10); le player pointera
					 * sur l'image 55. Pour la fonction seek on peut accéder
					 * directement à l'image qu'on désire par exemple quelque
					 * soit l'image sur laquelle pointe le player et on fait
					 * dest = fpc.seek(10) le player pointera sur l'image 10.
					 */
					fpc.skip(fpc.mapTimeToFrame(timeCapture));// avec dest = fpc.seek(NomImage); ça
										// marche aussi.
					numImage += fpc.mapTimeToFrame(timeCapture);
				}
			}
		}
	}

	/*
	 * La fonction main. N'oubliez pas de changer le chemin de la video.
	 */
	public static void main(String[] args) throws NoPlayerException,
			IOException
	{
		new Acquisition("comptes/E074862X/workspace/Acquisition/lostinspace.mov", 5);
	}
	
	public void creerImage(Image image, String adr){
		 try{
		BufferedImage bufImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bufImage.getGraphics().drawImage(image, 0, 0, null);

        ImageIO.write(bufImage, "jpg",new File(adr));


    } catch (IOException e) {
    	e.printStackTrace();
    }
    System.out.println("Done");
 
    } 
}

 

