package main;

import genTache.mainGenerator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import sstr.Algorithms;
import sstr.TasksManager;

/**
 * Main
 * 
 * @author MARGUERITE alain
 * @author RINCE Romain
 * 
 * 
 */
public class main
{
	static int choix;
	static int exemple;
	static String Methode;
	static String algo;
	static String fichier;
	static TasksManager tm;
	static Algorithms al;

	/**
	 * Lire une chaîne de caractère sur l'entrée standard
	 * 
	 * @return chaîne lue
	 */
	public static String lireString()
	{// lecture d'une chaine
		String ligne_lue = null;
		try
		{
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			ligne_lue = entree.readLine();
		} catch (IOException err)
		{
			System.exit(0);
		}
		return ligne_lue;
	}

	/**
	 * Lire un double sur l'entrée standard
	 * 
	 * @return double lu
	 */
	public static double lireDouble()
	{// lecture d'un double
		double x = 0; // valeur à lire
		try
		{
			String ligne_lue = lireString();
			x = Double.parseDouble(ligne_lue);
		} catch (NumberFormatException err)
		{
			System.out.println("***Erreur de données Double attendu***");
			System.exit(0);
		}
		return x;
	}

	/**
	 * Lecture d'un entier
	 * 
	 * @return entier lu
	 */
	public static int lireInt()
	{// lecture d'un double
		int x = 0; // valeur à lire
		try
		{
			String ligne_lue = lireString();
			x = Integer.parseInt(ligne_lue);
		} catch (NumberFormatException err)
		{
			System.out.println("***Erreur de données Integer attendu***");
			System.exit(0);
		}
		return x;
	}

	/**
	 * Lire un char sur l'entrée standard
	 * 
	 * @return char lu.
	 */
	public static String lireLettre()
	{// lecture d'un double
		String ligne_lue = lireString();
		String result = ligne_lue.substring(0, 1);
		return result;
	}

	/**
	 * Affichage pour la choix des algorithmes
	 * 
	 * @throws Exception
	 */
	public static void menuExemples() throws Exception
	{
		System.out.println("********************");
		System.out.println("*****GENIUS TASK****");
		System.out.println("********************");
		System.out.println("Tapez 1 pour RM BG");
		System.out.println("Tapez 2 pour EDF-BG");
		System.out.println("Tapez 3 pour TBS-BG");
		exemple = main.lireInt();
		switch (exemple)
		{
		case 1:
			fichier = "examples/cours_RMBG.xml";
			tm = new TasksManager(fichier); // TEST RM_BG
			al = new Algorithms(tm, fichier);
			al.RmBg();
			break;
		case 2:
			fichier = "examples/cours_EDFBG.xml";
			tm = new TasksManager(fichier); // TEST EDF_BG
			al = new Algorithms(tm, fichier);
			al.EdfBg();
			break;
		case 3:
			fichier = "examples/cours_EDFTBS.xml"; // TEST EDFTBS
			tm = new TasksManager(fichier);
			al = new Algorithms(tm, fichier);
			al.EdfTbs();
			break;
		default:
			System.out.println("***Erreur de données Integer attendu***");
			break;
		}
	}

	public static void menu() throws Exception
	{
		System.out.println("********************");
		System.out.println("*****GENIUS TASK****");
		System.out.println("********************");
		System.out.println("Tapez 1 pour une démonstration des différents algorithmes");
		System.out.println("Tapez 2 pour une commencer par une génération des tâches");
		choix = main.lireInt();

		switch (choix)
		{
		case 1:
			menuExemples();
			break;
		case 2:

			System.out.println("Choix de l'algorithme d'ordonancement");
			System.out.println("Tapez 1 pour RM BG");
			System.out.println("Tapez 2 pour EDF-BG");
			System.out.println("Tapez 3 pour TBS-BG");
			choix = main.lireInt();
			tm = new TasksManager(mainGenerator.FileGenerator());
			al = new Algorithms(tm, "output.ktr");
			switch (choix)
			{
			case 1:
				al.RmBg();
				break;
			case 2:
				al.EdfBg();
			case 3:
				al.EdfTbs();
			default:
				System.out.println("erreur veuillez saisir 1 2 ou 3");
				break;
			}
			break;

		default:
			break;
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception
	{

		menu();

	}

}
