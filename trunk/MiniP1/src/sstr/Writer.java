package sstr;

import genTache.TacheAperiodique;
import genTache.TachePeriodique;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant la création du fichier .ktr. L'objet instancié pourra être
 * appellé au fur et à mesure des algortihmes d'ordonnancement pour écrire dans
 * le fichier
 * 
 * @author E11A932Q
 * 
 */

public class Writer
{
	private String filename;
	private int DURATION;
	private String lines;
	private String PALETTE;
	private int ZOOM_X;
	private int ZOOM_Y;
	private String DrawingOption;
	private String Events;
	private int last_date;// savoir si on doit sauter une ligne entre deux dates

	public Writer(String filename, TasksManager tm, int duree)
	{
		super();
		this.filename = filename;
		this.DURATION = duree;
		this.PALETTE = "Rainbow";
		this.ZOOM_X = 4;
		this.ZOOM_Y = 16;
		lines = "";
		for (TachePeriodique tache : tm.getTachesPeriodiques())
			lines += "LINE_NAME" + " " + tache.getId() + " " + tache.getId() + "\n";
		for (TacheAperiodique tache : tm.getTachesAperiodiques())
			lines += "LINE_NAME" + " " + tache.getId() + " " + tache.getId() + "\n";
		this.DrawingOption = "DECIMAL_DIGITS 0" + "\n" + "DURATION " + this.DURATION + "\n" + lines +

		"PALETTE " + this.PALETTE + "\n" + "ZOOM_X " + this.ZOOM_X + "\n" + "ZOOM_Y " + this.ZOOM_Y + "\n" +
		"\n";
		this.Events = "";
		this.last_date = 0;
	}

	/**
	 * Ajout un événement dont les caractéristiques sont les parmètres suivants
	 * 
	 * 
	 * @param Time
	 *            date de l'évenement
	 * @param Event_Type
	 *            nature de l'évenement
	 * @param Line
	 *            tâche concernée
	 * @param Text
	 *            description de l'évenement
	 * @param Color
	 *            Couleur de l'évenement
	 * 
	 */
	public void addEvent(int Time, String Event_Type, int Line, String Text,
			String Color)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + Event_Type + Integer.toString(Line) + " " + Text + " " + Color + "\n";

	}

	/**
	 * Ajout un événement dont les caractéristiques sont les parmètres suivants:
	 * 
	 * 
	 * @param Time
	 *            date de l'évenement
	 * @param Event_Type
	 *            nature de l'évenement
	 * @param Line
	 *            tâche concernée
	 * @param Color
	 *            Couleur de l'évenement
	 * 
	 */
	public void addEvent(int Time, String Event_Type, int Line, String Color)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + Event_Type + Integer.toString(Line) + " " + Color + "\n";
	}

	/**
	 * Ajout un événement dont les caractéristiques sont les parmètres suivants:
	 * 
	 * @param Time
	 *            date de l'évenement
	 * @param Event_Type
	 *            nature de l'évenement
	 * @param Line
	 *            tâche concernée
	 */
	public void addEvent(int Time, String Event_Type, int Line)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + Event_Type + " " + Integer.toString(Line) + "\n";
	}

	/**
	 * Ajout une ligne verticale avec les caractéristiques suivantes
	 * 
	 * @param Time
	 *            date de l'évenement
	 * @param Event_Type
	 *            nature de l'évenement
	 * @param Line
	 *            tâche concernée
	 */
	public void addVLINE(int Time, int Line, String Text, String Color)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + "VLINE" + Integer.toString(Line) + " " + Text + " " + Color + "\n";

	}

	/**
	 * Génération du fichier .ktr et lancement de kwi
	 * 
	 * @throws IOException
	 */
	public void generateFile() throws IOException
	{
		FileWriter writer = null;

		// String texte = "texte à indsfsdfsérer"+"\n"+" à la fin du fichier";
		try
		{

			writer = new FileWriter(this.filename, false);
			writer.write(DrawingOption + Events, 0, (DrawingOption + Events).length());
		} catch (IOException ex)
		{
			ex.printStackTrace();
		} finally
		{
			if (writer != null)
			{
				try
				{
					writer.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Runtime.getRuntime().exec("./kiwi/kiwi " + filename);  // ouverture de kiwi avec le nouveau fichier généré.
	}

	public String getFilename()
	{
		return filename;
	}

}
