package sstr;

import genTache.AbstractTache;
import genTache.TacheAperiodique;
import genTache.TachePeriodique;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.text.html.MinimalHTMLWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TasksManager
{

	private ArrayList<TacheAperiodique> tachesAperiodiques;
	private ArrayList<TachePeriodique> tachesPeriodiques;
	private int hyperperiode;

	/**
	 * Renvoit une copie de la tache avec son Ci mis à jour (cas d'une tache qui
	 * s'est executé partiellement)
	 */

	/**
	 * La fonction renvoie la prochaine tâche à se réveiller après ou pendant
	 * l'instant t
	 * 
	 * @param t
	 *            Indique le moment à partir duquel considérer les taches.
	 * @return La date du prochain réveil de tache
	 */
	public int nextReveil(int t)
	{
		// Attention
		int reveil = hyperperiode;
		int prochainReveil;
		// int min = hyperperiode;
		// On commence par parcourir les taches périodiques
		for (TachePeriodique tache : tachesPeriodiques)
		{
			prochainReveil = tache.getPi();
			// La formule suivante calcule le début de période le plus proche
			// après ou pendant l'instant t. La formule est en fait :
			// prochainReveil = t + (Pi - t%Pi)
			prochainReveil = t + 1
					+ (prochainReveil - ((t + 1) % prochainReveil));

			if (t == tache.getRi()) // cas ouù reveil pendant
			{
			System.out.println("prochain reveil : " + t);

				return t;
			}
			//System.out.println("prochain reveil : " + prochainReveil);
			// if (prochainReveil < reveil) // ? ??? toujours vrai !
			if (reveil > prochainReveil)
			{

				// reveil =t+ tache.getRi();
				reveil = prochainReveil;
			}
		}

		// On cherche ensuite dans les taches apériodiques
		 for (TacheAperiodique tache : tachesAperiodiques)
		 {
		 if (tache.getRi() < reveil && tache.getRi() > t)
		 reveil = tache.getRi();
		 }
		//System.out.println("nouveau nxt reveil : " + reveil + " avec t = " + t);
		return reveil;
	}

	/**
	 * Pour récupérer toutes les taches périodiques se réveillant à la date t
	 * 
	 */
	public ArrayList<TachePeriodique> getTachesP(int t, Writer w)
	{
		ArrayList<TachePeriodique> result = new ArrayList<TachePeriodique>();
		for (TachePeriodique tache : tachesPeriodiques)
		{
			if ((t == 0 && tache.getRi() == 0) || (t % tache.getPi()) == 0) // problème  si t = 0 j'ai changé getRI	 et	 getPI
			{
				result.add(new TachePeriodique(tache));
				w.addEvent(t, "START",tache.getId());
			}
		}
		return result;
	}

	/**
	 * Pour récupérer toutes les taches apériodiques se réveillant à la date t
	 * 
	 */
	public ArrayList<TacheAperiodique> getTachesA(int t, Writer w)
	{
		ArrayList<TacheAperiodique> result = new ArrayList<TacheAperiodique>();
		for (TacheAperiodique tache : tachesAperiodiques)
		{
			if (tache.getRi() == t)
			{
				result.add(new TacheAperiodique(tache));
				w.addEvent(tache.getRi(), "START",tache.getId());
			}
		
		}
		//	System.out.println("taaaacheA"+result);
		return result;
	}

	public ArrayList<TacheAperiodique> getTachesAperiodiques()
	{
		return tachesAperiodiques;
	}

	public ArrayList<TachePeriodique> getTachesPeriodiques()
	{
		return tachesPeriodiques;
	}

	public int getHyperperiode()
	{
		return hyperperiode;
	}

	/**
	 * Renvoyer la tache dont la période d'activationPi est petite
	 * 
	 * @param tab
	 *            Tableau source
	 *@return tache au Pi min.
	 */
	public int getPiMin(ArrayList<TachePeriodique> tab)
	{
		System.out.println(tab.toString());
		int min = tachesPeriodiques.get(0).getPi();
		int tachemin = tachesPeriodiques.get(0).getId();
		for (TachePeriodique p : tab)
		{

			if (p.getPi() < min)
			{
				min = p.getPi();
				tachemin = p.getId();
			}
		}
		System.out.println(min);
		return tachemin;
	}

	public TasksManager(String filename) throws FileNotFoundException
	{
		super();
		tachesAperiodiques = new ArrayList<TacheAperiodique>();
		tachesPeriodiques = new ArrayList<TachePeriodique>();
		AbstractTache[] tab;

		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());

		// Redirection du fichier vers un flux
		// d'entrée fichier
		FileInputStream fis = new FileInputStream(new File(filename));
		// Affichage sur la console du contenu de l'attribut synopsis

		// Désérialisation du fichier c:/temp/article.xml vers un nouvel
		// objet article
		tab = (AbstractTache[]) xstream.fromXML(fis);
		// // Affichage sur la console du contenu de l'attribut synopsis
		// for (int i = 0; i < nouveauTab.length; i++)
		// System.out.println(nouveauTab[i].getId());
		for (int i = 0; i < tab.length; i++)
		{
			if (tab[i] instanceof TacheAperiodique)
			{
				tachesAperiodiques.add((TacheAperiodique) tab[i]);
			}
			else
			{
				tachesPeriodiques.add((TachePeriodique) tab[i]);
			}
		}
		hyperperiode = PPCM(tachesPeriodiques);

	}

	protected int PPCM(ArrayList<TachePeriodique> t)
	{
		int i, x, y, z, NbArg;
		int Tab[];

		NbArg = t.size();
		Tab = new int[NbArg];

		for (i = 0; i < NbArg; i++)
			Tab[i] = t.get(i).getPi();

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

	protected int Calcule_PPCM(int Nb1, int Nb2)
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

	/*
	 * Tests de faisabilité
	 */

	protected float getRmCondNeccessaire()
	{
		float U = 0;
		for (int i = 0; i <= tachesPeriodiques.size() - 1; i++)
			U += (float) tachesPeriodiques.get(i).getCi()
					/ tachesPeriodiques.get(i).getPi();
		return U;
	}

	protected float getEDFCondSuffisanteDiEGALEhPi()
	{
		return getRmCondNeccessaire();
	}

	protected int getEDFCondSuffisanteDiINGPi()
	{
		int U = 0;
		for (int i = 1; i <= tachesPeriodiques.size(); i++)
			U += tachesPeriodiques.get(i).getCi()
					/ tachesPeriodiques.get(i).getDi();
		return U;
	}

	protected int getTBSCondNeccessaireEtSuffisante(int Up, int Us)
	{
		return Up + Us;
	}

	/**
	 *@param rk
	 *            Date de réveil de la requête occurrente
	 * 
	 *@param Cka
	 *            Durée d’exécution de la requête occurrente
	 * 
	 *@param dk_1
	 *            Échéance fictive de la requête précédente
	 * 
	 *@param Us
	 *            Largeur de bande CPU allouée au serveur
	 * 
	 * @return Calcul du dk nécessaire à l'algorithme TBS
	 */
	protected int getDk(int rk, int dk_1, int Cka, int Us)
	{
		int dk = 0;
		dk = Math.max(rk, dk_1) + Cka / Us;
		return dk;
	}
}
