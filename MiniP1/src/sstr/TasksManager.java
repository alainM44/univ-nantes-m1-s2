package sstr;

import genTache.AbstractTache;
import genTache.TacheAperiodique;
import genTache.TachePeriodique;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TasksManager {

	private ArrayList<TacheAperiodique> tachesAperiodiques;
	private ArrayList<TachePeriodique> tachesPeriodiques;
	private int hyperperiode;

	/**
	 * La fonction renvoie la prochaine méthode à se réveiller après ou pendant
	 * l'instant t
	 * 
	 * @param t
	 *            Indique le moment à partir duquel considérer les taches.
	 * @return La date du prochain réveil de tache
	 */
	public int nextReveil(int t) {
		// Attention
		int reveil = hyperperiode;
		int prochainReveil;

		// On commence par parcourir les taches périodiques
		for (TachePeriodique tache : tachesPeriodiques) {
			prochainReveil = tache.getPi();
			// La formule suivante calcule le début de période le plus proche
			// après ou pendant l'instant t. La formule est en fait :
			// prochainReveil = t + (Pi - t%Pi)
			prochainReveil = t + prochainReveil - t % prochainReveil;
			if (prochainReveil < reveil)
				reveil = tache.getRi();
		}

		// On cherche ensuite dans les taches apériodiques
		for (TacheAperiodique tache : tachesAperiodiques) {
			if (tache.getRi() < reveil && tache.getRi() >= t)
				reveil = tache.getRi();
		}
		return reveil;
	}

	public TasksManager(String filename) throws FileNotFoundException {
		super();
		tachesAperiodiques = new ArrayList<TacheAperiodique>();
		tachesPeriodiques = new ArrayList<TachePeriodique>();
		AbstractTache[] tab;

		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());

		// Redirection du fichier c:/temp/article.xml vers un flux
		// d'entrée fichier
		FileInputStream fis = new FileInputStream(new File(
				/home/alain/workspace/MiniP1/taches.xml));

		// Désérialisation du fichier c:/temp/article.xml vers un nouvel
		// objet article
		tab = (AbstractTache[]) xstream.fromXML(fis);
		// // Affichage sur la console du contenu de l'attribut synopsis
		// for (int i = 0; i < nouveauTab.length; i++)
		// System.out.println(nouveauTab[i].getId());
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] instanceof TacheAperiodique) {
				tachesAperiodiques.add((TacheAperiodique) tab[i]);
			} else {
				tachesPeriodiques.add((TachePeriodique) tab[i]);
			}
		}
		hyperperiode = PPCM(tachesPeriodiques);

	}

	protected int PPCM(ArrayList<TachePeriodique> t) {
		int i, x, y, z, NbArg;
		int Tab[];

		NbArg = t.size();
		Tab = new int[NbArg];

		for (i = 0; i < NbArg; i++)
			Tab[i] = t.get(i).getPi();

		x = Tab[0];
		z = 1;
		for (i = 1; i < NbArg; i++) {
			y = Tab[i];
			z = Calcule_PPCM(x, y);
			x = z;
		}

		return z;
	}

	protected int Calcule_PPCM(int Nb1, int Nb2) {
		int Produit, Reste, PPCM;

		Produit = Nb1 * Nb2;
		Reste = Nb1 % Nb2;
		while (Reste != 0) {
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

	protected int getRmCondNeccessaire() {
		int U = 0;
		for (int i = 1; i <= tachesPeriodiques.size(); i++)
			U += tachesPeriodiques.get(i).getCi()
					/ tachesPeriodiques.get(i).getPi();
		return U;
	}

	protected int getEDFCondSuffisanteDiEGALEhPi() {
		return getRmCondNeccessaire();
	}

	protected int getEDFCondSuffisanteDiINGPi() {
		int U = 0;
		for (int i = 1; i <= tachesPeriodiques.size(); i++)
			U += tachesPeriodiques.get(i).getCi()
					/ tachesPeriodiques.get(i).getDi();
		return U;
	}
	
	protected int getTBSCondNeccessaireEtSuffisante(int Up, int Us) {
		return Up+Us;
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
	protected int getDk(int rk, int dk_1, int Cka, int Us) {
		int dk = 0;
		dk = Math.max(rk, dk_1) + Cka / Us;
		return dk;
	}
}
