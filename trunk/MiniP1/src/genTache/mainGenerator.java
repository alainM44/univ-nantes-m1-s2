package genTache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class mainGenerator
{

	static int temps = 100;

	/**
	 * Permet la génération d'un fichier contenant une liste de taches
	 * periodiques et aperiodiques. Les taches peuvent être générées
	 * aléatoirement ou non, cependant la génération aléatoire est souvent
	 * problématique puisqu'elle fait exploser l'hyperpériode.
	 * 
	 * @throws FileNotFoundException
	 */
	public static String FileGenerator() throws FileNotFoundException
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
		int Uap;
		int resteAp = 0;
		int reste = 0;
		ArrayList<Integer> ppcmValues = new ArrayList<Integer>();
		int ppcm = 0;
		AbstractTache[] tab;
		boolean genauto = true;
		boolean tachep = true;
		boolean tachea = true;
		String filename;
		Scanner in = new Scanner(System.in);

		System.out.println("Saisissez le nombre de tâches périodiques : ");
		nbTachesP = in.nextInt();
		System.out.println("Saisissez le nombre de tâches apériodiques : ");
		nbTachesAP = in.nextInt();
		tachea = (nbTachesAP != 0);
		tachep = (nbTachesP != 0);
		tab = new AbstractTache[nbTachesP + nbTachesAP];
		if (tachep)
		{
			System.out
					.println("Souhaitez vous une génération automatique pour les tâches périodiques ? (y/n)");
			genauto = in.next().equals("y");
			if (genauto)
			{
				System.out
						.println("Quel pourcentage d'utilisation maximale du processeur désirez-vous ?");
				pourcentage = in.nextInt();
				pourAperiodique = (int) (temps - (temps * pourcentage) / 100.0);
				U = (int) ((double) (U * pourcentage) / (double) 100);
				for (int i = 1; i <= nbTachesP; i++)
				{
					if (i == nbTachesP)
					{
						Pi = r.nextInt(temps) + 1;
						Ci = (int) (((double) U / (double) temps) * Pi);
						Di = Math.max(r.nextInt(Pi) + 1, Ci);
						reste = ((int) ((((double) Ci) / ((double) Pi)) * temps)) + 1;
					}
					else
					{
						do
						{
							Pi = r.nextInt(temps) + 1;
							Di = r.nextInt(Pi) + 1;
							Ci = r.nextInt(Math.min(Di + 1, Pi
									/ (nbTachesP - i + 1))) + 1;
							reste = ((int) ((((double) Ci) / ((double) Pi)) * temps)) + 1;
						} while (U - reste < nbTachesP + nbTachesAP - i);
					}
					U = U - reste;
					tab[i - 1] = new TachePeriodique(i, Ci, Di, Pi);
					ppcmValues.add(Pi);
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
					ppcmValues.add(Pi);
					U -= reste;
				}
			}
			ppcm = PPCM(ppcmValues);
			System.out.println(ppcm);
			System.out.println(U);
		}
		if (tachea)
		{
			System.out
					.println("Souhaitez vous une génération automatique pour les tâches apériodiques ? (y/n)");
			genauto = in.next().equals("y");
			if (genauto)
			{
				System.out.println("Merci de fournir un Uap");
				Uap = in.nextInt();
				resteAp = (int) ((((double) (Uap) * (double) (ppcm))) / 100.0);
				for (int i = nbTachesP + 1; i <= nbTachesP + nbTachesAP; i++)
				{
					if (i == nbTachesAP + nbTachesP)
					{
						ri = r.nextInt(ppcm);
						Ci = resteAp;
					}
					else
					{
						ri = r.nextInt(ppcm);
						// TODO : Di inutile
						Ci = r.nextInt(Math.min(ppcm - ri, resteAp - nbTachesAP
								+ i - nbTachesP)) + 1;
					}
					resteAp = resteAp - Ci;
					tab[i - 1] = new TacheAperiodique(i, Ci, 0, ri);
					System.out.println(" " + Ci + " " + ri);
				}

			}
			else
			{
				for (int i = nbTachesP + 1; i < nbTachesP + nbTachesAP + 1; i++)
				{
					resteAp = (int) (((double) reste / (double) temps) * ppcm);
					System.out.println("Attention il ne vous reste que "
							+ resteAp + " unités de temps disponibles");
					System.out
							.println("Merci d'entrer les valeurs pour les paramètres de la tache "
									+ i);
					System.out.println("Valeur pour Ci :");
					Ci = in.nextInt();
					// System.out.println("Valeur pour Di :");
					// Di = in.nextInt();
					System.out.println("Valeur pour ri :");
					ri = in.nextInt();
					tab[i - 1] = new TacheAperiodique(i, Ci, 0, ri);
					resteAp = resteAp - Ci;
				}
			}

			System.out.println(resteAp);
		}
	

		File taches = new File("taches.xml");
		FileOutputStream fos = new FileOutputStream(taches);
		xstream.toXML(tab, fos);
		// xstream.toXML(taches, fos);
		return "taches.xml";
	}

	public static int PPCM(ArrayList<Integer> t)
	{
		int i, x, y, z, NbArg;
		int Tab[];

		NbArg = t.size();
		Tab = new int[NbArg];

		for (i = 0; i < NbArg; i++)
			Tab[i] = t.get(i);

		x = Tab[0];
		z = 1;
		for (i = 1; i < NbArg; i++)
		{
			y = Tab[i];
			z = Calcule_PPCM(x, y);
			x = z;
		}

		return z;
	}

	public static int Calcule_PPCM(int Nb1, int Nb2)
	{
		int Produit, Reste, PPCM;

		Produit = Nb1 * Nb2;
		Reste = Nb1 % Nb2;
		while (Reste != 0)
		{
			Nb1 = Nb2;
			Nb2 = Reste;
			Reste = Nb1 % Nb2;
		}
		PPCM = Produit / Nb2;
		// System.out.println("PGCD = " + Nb2 + " PPCM = " + PPCM);
		return PPCM;
	} // fin Calcule_PPCM

	public static void main(String[] args) throws Exception
	{
		FileGenerator();
		
	}
}
