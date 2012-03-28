package acquisition;

import javax.media.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.control.FrameGrabbingControl;
import javax.media.control.FramePositioningControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.util.BufferToImage;

import middleware.IFlux;

/**
 * Plugin d'acquisition d'une image dans un fichier video.
 * @author E074862X
 *
 */
public class Acquisition implements ControllerListener, IFlux
{

	private Buffer buf;
	private Image img;
	private BufferToImage btoi;
	private Player player;
	private int frameFrequence;
	private FramePositioningControl fpc;
	private FrameGrabbingControl fgc;
	private BufferedImage bufImage;
	private URL u;
	private double debut;
	MediaLocator mrl;
	Object waitSync = new Object();
	boolean stateTransitionOK = true;
	int totalFrames = FramePositioningControl.FRAME_UNKNOWN;
	
	//Permet de stocker le temps jusqu'à l'appel de la méthode start
	double temporaryTime;

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
		frameFrequence = 1;
		fpc = null;
		fgc = null;
		bufImage = null;
		u = null;
		debut = 0;
		mrl = null;
		temporaryTime = 0;
	}

	/*
	 * On implimente la fonction controllerUpdate de la classe
	 * ControllerListener qui nous permet de gérer les évenements du player.
	 */
	public void controllerUpdate(ControllerEvent evt)
	{
		if (evt instanceof ConfigureCompleteEvent
				|| evt instanceof RealizeCompleteEvent
				|| evt instanceof PrefetchCompleteEvent)
		{
			synchronized (waitSync)
			{
				stateTransitionOK = true;
				waitSync.notifyAll();
			}
		}
		else if (evt instanceof ResourceUnavailableEvent)
		{
			synchronized (waitSync)
			{
				stateTransitionOK = false;
				waitSync.notifyAll();
			}
		}
		else if (evt instanceof EndOfMediaEvent)
		{
			player.setMediaTime(new Time(0));
			// p.start();
			// p.close();
			// System.exit(0);
		}
		else if (evt instanceof SizeChangeEvent)
		{}
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
		buf = fgc.grabFrame();
		btoi = new BufferToImage((VideoFormat) buf.getFormat());
		img = btoi.createImage(buf);
		BufferedImage bufImage = new BufferedImage(img.getWidth(null), img
				.getHeight(null), BufferedImage.TYPE_INT_RGB);
		fpc.skip(frameFrequence);
		int currentFrame = fpc.mapTimeToFrame(player.getMediaTime());
		if (currentFrame != FramePositioningControl.FRAME_UNKNOWN)
			System.err.println("Current frame: " + currentFrame);
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
		temporaryTime = t;
	}

	@Override
	public void setFile(String file)
	{
		System.out.println(file);
		try
		{
			u = new URL("file:///" + file);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Méthode d'initialisation de l'analyse
	 * @param ds Les données d'analyses contenant l'url du fichier
	 * @return Retourne true si l'initialisation s'est correctement déroulé, false sinon
	 * @throws InterruptedException 
	 */
	public boolean open(DataSource ds) throws InterruptedException
	{
		System.err.println("create player for: " + ds.getContentType());
		try
		{
			player = Manager.createPlayer(ds);
		}
		catch (Exception e)
		{
			System.err
					.println("Failed to create a player from the given DataSource: "
							+ e);
			return false;
		}

		player.addControllerListener(this);

		player.realize();
		//Permet d'attendre que player soit prêt
		if (!waitForState(player.Realized))
		{
			System.err.println("Failed to realize the player.");
			return false;
		}

		// Ajout des controlleurs
		fpc = (FramePositioningControl) player
				.getControl("javax.media.control.FramePositioningControl");
		fgc = (FrameGrabbingControl) player
				.getControl("javax.media.control.FrameGrabbingControl");

		if (fpc == null)
		{
			System.err
					.println("The player does not support FramePositioningControl.");
			return false;
		}

		if (fgc == null)
		{
			System.err
					.println("The player does not support FrameGrabbingControl.");
			return false;
		}

		Time duration = player.getDuration();

		if (duration != Duration.DURATION_UNKNOWN)
		{
			System.err.println("Movie duration: " + duration.getSeconds());

			totalFrames = fpc.mapTimeToFrame(duration);
			if (totalFrames != FramePositioningControl.FRAME_UNKNOWN)
				System.err.println("Total # of video frames in the movies: "
						+ totalFrames);
			else
				System.err
						.println("The FramePositiongControl does not support mapTimeToFrame.");

		}
		else
		{
			System.err.println("Movie duration: unknown");
		}

		// Prefetch the player.
		player.prefetch();
		if (!waitForState(player.Prefetched))
		{
			System.err.println("Failed to prefetch the player.");
			return false;
		}

		//change la frequence d'analyse
		if (temporaryTime!=0)
		{
			frameFrequence = fpc.mapTimeToFrame(new Time(temporaryTime));
		}
		// Definie le début d'analyse
		fpc.seek(fpc.mapTimeToFrame(new Time(debut)));
		return true;
	}

	/**
	 * Attend que le player soit prêt
	 * @param state L'état attendu pour le player
	 * @return true quand le player est synchro
	 * @throws InterruptedException 
	 */
	boolean waitForState(int state) throws InterruptedException
	{
		synchronized (waitSync)
		{
				while (player.getState() < state && stateTransitionOK)
					waitSync.wait();
		}
		return stateTransitionOK;
	}

	@Override
	public void start()
	{

		if (u == null)
		{
			prUsage();
			System.exit(0);
		}

		MediaLocator ml;

		if ((ml = new MediaLocator(u)) == null)
		{
			System.err.println("Cannot build media locator from: " + u);
			prUsage();
			System.exit(0);
		}

		DataSource ds = null;

		// Create a DataSource given the media locator.
		try
		{
			ds = Manager.createDataSource(ml);
		}
		catch (Exception e)
		{
			System.err.println("Cannot create DataSource from: " + ml);
			System.exit(0);
		}

		try
		{
			if (!open(ds))
				System.exit(0);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void prUsage()
	{
		System.err.println("Usage: java Acquisition <url>");
	}
}
