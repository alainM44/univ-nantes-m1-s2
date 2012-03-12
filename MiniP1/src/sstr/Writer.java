package sstr;

import genTache.TacheAperiodique;
import genTache.TachePeriodique;

import java.io.FileWriter;
import java.io.IOException;

public class Writer
{
	private String filename;

	private int DECIMAL_DIGITS;
	private int DURATION;
	private String lines;
	// LINE_NAME 0 A
	// LINE_NAME 1 B
	// LINE_NAME 2 C
	private String PALETTE;
	private int ZOOM_X;
	private int ZOOM_Y;
	// COLOR EXEC-E 0 orchid4
	private String DrawingOption;
	private String Events;
	private int last_date;// savoir si on doit sauter une ligne entre deux dates

	public Writer(String filename, TasksManager tm,int duree)
	{
		super();
		

		this.filename = filename;
		this.DECIMAL_DIGITS = 0;
		this.DURATION =duree;
		// LINE_NAME 0 A
		// LINE_NAME 1 B
		// LINE_NAME 2 C
		this.PALETTE = "Rainbow";
		this.ZOOM_X = 4;
		this.ZOOM_Y = 16;
		lines="";
		for (TachePeriodique tache : tm.getTachesPeriodiques())
			lines += "LINE_NAME" +" "+ tache.getId() + " " + tache.getId() + "\n";
		for (TacheAperiodique tache : tm.getTachesAperiodiques())
			lines += "LINE_NAME" +" "+ tache.getId() + " " + tache.getId() + "\n";
		// COLOR EXEC-E 0 orchid4
		System.out.println(lines);
		this.DrawingOption = "DECIMAL_DIGITS 0" + "\n" + "DURATION " + this.DURATION + "\n" + lines+
	
		"PALETTE " + this.PALETTE + "\n" + "ZOOM_X " + this.ZOOM_X + "\n" + "ZOOM_Y " + this.ZOOM_Y + "\n" +
		// COLOR EXEC-E 0 orchid4 ";
		"\n";
		this.Events = "";
		this.last_date = 0;
	}

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

	public void addEvent(int Time, String Event_Type, int Line, String Color)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + Event_Type + Integer.toString(Line) + " " + Color + "\n";
	}

	public void addEvent(int Time, String Event_Type, int Line)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + Event_Type + " " + Integer.toString(Line) + "\n";
	}

	public void addVLINE(int Time, int Line, String Text, String Color)
	{
		if (last_date != Time)
		{
			this.last_date = Time;
			this.Events += "\n";
		}
		this.Events += Integer.toString(Time) + " " + "VLINE" + Integer.toString(Line) + " " + Text + " " + Color + "\n";

	}

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
Runtime.getRuntime().exec("./kiwi/kiwi "+filename	);
	}

	public String getFilename()
	{
		return filename;
	}

}
