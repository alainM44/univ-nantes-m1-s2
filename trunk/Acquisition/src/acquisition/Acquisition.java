package acquisition;

import javax.media.*;
import java.awt.Image;
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
	 
	 
	//Constructeur
	public Acquisition(String a)
	{
	super();
	    try
	    {
	    /*La création du lecteur et le chargement du fichier à lire.*/
	    player = Manager.createPlayer( new MediaLocator("file:///" +a) );
	    /*L'ajout d'un écouteur sur le player pour pouvoir gérer les évenements
	     de ce dernier*/
	    player.addControllerListener( this ) ;
	    /*Cette fonction permet au player d'acquérir toutes les informations et
	     toutes les ressources qui lui sont nécessaires sur le média*/
	    System.out.println("Acquésition des ressources et des informations média en cours.");
	    System.out.println("Patientez S'il vous plait.");    
	    player.realize();
	    }
	    catch (Exception e)
	    {
	    /*Traitement des erreurs qui peuvent survenir lors de la création du lecteur.*/
	    	System.out.println(e);
	    System.out.println("Error creating player");
	    return;
	    }
	}
	 
	/*On implimente la fonction controllerUpdate de la classe ControllerListener qui nous permet
	   de gérer les évenemnts du player.*/
	public void controllerUpdate( ControllerEvent ce )
	{
	  /*
	   une fois les ressources et les informations nécessaires pour le média sont reconnus
	   on passe au traitement.*/
	if ( ce instanceof RealizeCompleteEvent )
	{
	    int NomImage = 1;
	    int dest;
	    FramePositioningControl fpc;
	    FrameGrabbingControl fgc;
	    Time duration = player.getDuration();
	    int totalFrames = FramePositioningControl.FRAME_UNKNOWN;
	    fpc = (FramePositioningControl) player.getControl("javax.media.control.FramePositioningControl");
	    if (fpc == null)
	    {
	      System.out.println("Le média ne supporte pas les FramePositioningControl.");
	    }
	    else
	    {
	        /*
	        On calcul le nombre d'images dans le média.
	        */
	      totalFrames = fpc.mapTimeToFrame(duration);
	      System.out.println("Nombre total des images dans le média : " + totalFrames);
	 
	      /*
	      boucle de parcours des images de le média.
	       */
	      while (NomImage <= totalFrames)
	      {
	        /*
	         La fonction skip nous permet d'avancer dans les images par exemple si 
	         le player pointe sur l'image 45 de le média et on fait dest = fpc.skip(10);
	         le player pointera sur l'image 55.
	         Pour la fonction seek on peut accéder directement à l'image qu'on désire
	         par exemple quelque soit l'image sur laquelle pointe le player et on fait
	         dest = fpc.seek(10) le player pointera sur l'image 10.
	         */
	        dest = fpc.skip(1);//avec dest = fpc.seek(NomImage); ça marche aussi.
	        /*
	        On capture l'image pointé par le player.     
	         */
	        fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
	        /*
	        On met l'image dans un buffer.
	         */
	        buf = fgc.grabFrame();
	        /*
	         On convertit l'image dans le buffer.
	         */
	        btoi = new BufferToImage( (VideoFormat) buf.getFormat());
	        img = btoi.createImage(buf);
	        /*
	Ici vous pouvez soit enregistrez l'image ou bien l'afficher dans un JPanel ...
	je vous laisse e choix.
	*/
	 
	        System.out.println("Image " + NomImage + "extraite");
	        NomImage = NomImage + 1;
	      }
	    }
	  }
	}

	/*La fonction main.
	N'oubliez pas de changer le chemin de la video.
	*/
	public static void main(String[] args)
	{
	 new Acquisition("/comptes/E074862X/workspace/Acquisition/DELTA.MPG" );
	}
}


