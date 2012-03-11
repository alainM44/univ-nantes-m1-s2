package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sstr.Algorithms;
import sstr.TasksManager;

public class main
{

	static String Methode;
	static String algo;
	static String fichier;

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

	public static String lireLettre()
	{// lecture d'un double
		String ligne_lue = lireString();
		String result = ligne_lue.substring(0, 1);
		return result;
	}

	public static void menu()
	{
		System.out.println("********************");
		System.out.println("*****GENUIS TASK****");
		System.out.println("********************");
		System.out.println("Nom du fichier");
		String adresse = main.lireString();
		if (adresse.charAt(0) == '/')
			fichier = adresse;
		else
			fichier += adresse;
		//System.out.println("Merci pour" + fichier);
		System.out.println("Choix de l'algorithme d'ordonancement");
		System.out.println("Tapez r pour utiliser RM-BG ");
		System.out.println("Tapez e pour utiliser EDF-TBS");
		algo = main.lireLettre();

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		TasksManager tm = new TasksManager("cours_RMBG.xml"); // TEST RM_BG
		Algorithms al = new Algorithms(tm);
	al.RmBg();
//		al.EdfBg();
//		al.EdfTbs(0.25);
		

		//		menu();
		//
		//		TasksManager tm = new TasksManager(mainGenerator.FileGenerator());
		//		Algorithms al = new Algorithms(tm);
		//		if (algo.equals("r"))
		//			al.RmBg();
		//
		//		else if (algo.equals("e"))
		//			al.EdfTbs();
		//		else
		//			System.out.println("Error pas d'algorithme valide selectionné");

	}

}
