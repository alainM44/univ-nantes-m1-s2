package genTache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class mainGenerator
{

	static int temps = 1000;

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException
	{

		java.util.Random r = new java.util.Random();
		XStream xstream = new XStream();
		int nbTachesP = 0;
		int nbTachesAP = 0;
		int Ci;
		int Pi;
		int Di;
		int ri;
		int pourcentage;
		int pourAperiodique;
		int U = temps;
		int reste;
		AbstractTache[] tab;
		boolean genauto = true;
		Scanner in = new Scanner(System.in);

		System.out.println("Saisissez le nombre de tâches périodiques : ");
		nbTachesP = in.nextInt();
		System.out.println("Saisissez le nombre de tâches apériodiques : ");
		nbTachesAP = in.nextInt();
		tab = new AbstractTache[nbTachesP + nbTachesAP];
		System.out
				.println("Souhaitez vous une génération automatique pour les tâches périodiques ? (y/n)");
		genauto = in.next().equals("y");
		if (genauto)
		{
			System.out
					.println("Quel pourcentage d'utilisation maximale du processeur désirez-vous ?");
			pourcentage = in.nextInt();
			pourAperiodique = temps - (temps * pourcentage) / 100;
			U = (U * pourcentage) / 100;
			for (int i = 1; i <= nbTachesP; i++)
			{
				do
				{
					Pi = r.nextInt(temps) + 1;
					Di = r.nextInt(Pi) + 1;
					Ci = r.nextInt(Di) + 1;
					reste = ((int) ((((double) Ci) / ((double) Pi)) * temps)) + 1;
				} while (U - reste < nbTachesP + nbTachesAP - i);
				U = U - reste;
				tab[i - 1] = new TachePeriodique(i, Ci, Di, Pi);
				System.out.println(Pi + " " + Ci + " " + Di + " " + U);
			}
			U += pourAperiodique;

		}
		else
		{
			for (int i = 1; i < nbTachesP + 1; i++)
			{
				System.out.println("Vous avez " + U
						+ " unités de temps disponibles");
				System.out
						.println("Merci d'entrer les valeurs pour les paramètres de la tache "
								+ i);
				System.out.println("Valeur pour Ci :");
				Ci = in.nextInt();
				System.out.println("Valeur pour Di :");
				Di = in.nextInt();
				System.out.println("Valeur pour Pi :");
				Pi = in.nextInt();
				tab[i - 1] = new TachePeriodique(i, Ci, Di, Pi);
				reste = ((int) ((((double) Ci) / ((double) Pi)) * temps)) + 1;
				U -= reste;
			}
		}
		System.out.println(U);
		System.out
				.println("Souhaitez vous une génération automatique pour les tâches apériodiques ? (y/n)");
		genauto = in.next().equals("y");
		if (genauto)
		{
			for (int i = nbTachesP + 1; i <= nbTachesP + nbTachesAP; i++)
			{
				ri = r.nextInt(temps);
				//TODO : Di inutile
				Di = r.nextInt(Math.min(temps - ri, U - nbTachesAP + i
						- nbTachesP)) + 1;
				Ci = r.nextInt(Di) + 1;
				U = U - Di;
				tab[i - 1] = new TacheAperiodique(i, Ci, Di, ri);
				System.out.println(" " + Ci + " " + Di + " " + U + " " + ri);
			}

		}
		else
		{
			for (int i = nbTachesP + 1; i < nbTachesP + nbTachesAP + 1; i++)
			{
				System.out.println("Attention il ne vous reste que " + U
						+ " unités de temps disponibles");
				System.out
						.println("Merci d'entrer les valeurs pour les paramètres de la tache "
								+ i);
				System.out.println("Valeur pour Ci :");
				Ci = in.nextInt();
//				System.out.println("Valeur pour Di :");
//				Di = in.nextInt();
				System.out.println("Valeur pour ri :");
				ri = in.nextInt();
				tab[i - 1] = new TacheAperiodique(i, Ci, 0, ri);
				U = U - Ci;
			}
		}
	
	    // Instanciation d'un fichier c:/temp/article.xml
	    File taches = new File("/comptes/E11A932Q/workspace/MiniP1/taches.xml");
	    FileOutputStream fos = new FileOutputStream(taches);
	    xstream.toXML(tab,fos);
	//	xstream.toXML(taches, fos);

	}

}
