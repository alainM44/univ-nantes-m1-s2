package sstr;

import genTache.AbstractTache;
import genTache.TacheAperiodique;
import genTache.TachePeriodique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Algorithms
{
	TasksManager tm;
	Writer w;

	public Algorithms(TasksManager tm, String filename)
	{
		super();
		String kiwifile = filename.replace("xml", "ktr");
		this.tm = tm;
		this.w = new Writer(kiwifile, tm, tm.PPCM(tm.getTachesPeriodiques()));
	}

	public void EdfBg() throws Exception
	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		int t = 0;
		int tempsCreux = 0;
		int tempsExecution = 0;
		int nbPremptions = 0;
		boolean tache_a_ajouter = false;
		ArrayList<Integer> tempAttenteTacheA = new ArrayList<Integer>(tm.getTachesAperiodiques().size());
		tabP.addAll(tm.getTachesP(t, w));
		tabA.addAll(tm.getTachesA(t, w));

		/* Affichages des test */
		int ppcm = tm.PPCM(tm.getTachesPeriodiques());
		tm.PrintEdfTEST();

		AbstractTache courante = null;
		TachePeriodique candidate = null;
		int ciCourante;
		int nextreveil;
		nextreveil = tm.nextReveil(t);

		System.out.println("Début");
		while (ppcm > 0)
		{
			System.out.println("debut de while t = " + t + " next reveil : " + nextreveil + "ppcm :" + ppcm);

			if (nextreveil == t && t != 0)
			{
				tabA.addAll(tm.getTachesA(t, w));
				tabP.addAll(tm.getTachesP(t, w));
				System.out.println("tabP apres rajout:" + tabP);
			}
			if (tabP.size() != 0) // il y a des taches périodique en attente
			{

				courante = plusGrandePrioriteEDF(tabP);
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);
				// System.out.println(courante);
				if (nextreveil - t >= ciCourante || (nextreveil == 0)) // la
				{
					if (nextreveil == (t + 1)) // on verifie que des taches
					// n'arrivents pas avant de
					// changer nxt reveil
					{
						tache_a_ajouter = true;
						// System.out.println("! ajouterP apres");
					}
					System.out.println("A t = " + t + " Tache " + courante.getId() + " s'execute :" + ciCourante + "et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t + ciCourante, "STOP", courante.getId());
					if (tache_a_ajouter)
					{
						ajoutTacheASinecessaire(tabA, t, t + ciCourante);
						tache_a_ajouter = false;
					}

					tabP.remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
				}
				else
				{
					candidate = plusGrandePrioriteEDF(tabP);
					if (!plusGrandePrioriteEDF(tabP, nextreveil).equals(courante)) // Préemption à next reveil de la tache
					// courante
					{
						System.out.println("préemptée par " + plusGrandePrioriteEDF(tabP, nextreveil));
						if (nextreveil == (t + 1)) // on verifie que des taches
						// n'arrivents pas avant de
						// changer nxt reveil
						{
							tache_a_ajouter = true;
							// System.out.println("! ajouterP apres");
						}

						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + " TacheP " + courante.getId() + " s'execute : " + (nextreveil - t));
						w.addEvent(t + (nextreveil - t), "EXEC-E", courante.getId());
						// mise a jour de la tache préemptée
						TachePeriodique maj = new TachePeriodique(tabP.get(tabP.indexOf(courante)).getId(), tabP.get(tabP.indexOf(courante)).getCi() - (nextreveil - t), tabP.get(tabP.indexOf(courante)).getDi(), tabP.get(tabP.indexOf(courante)).getPi());
						tabP.set((tabP.indexOf(courante)), maj);

						if (maj.getCi() == 0) // la mise a jour entraine un Ci à
							// 0 => on supprime la tache
							// //TODO( opération à
							// optimiser)
							tabP.remove(maj);
						if (tache_a_ajouter)
						{
							// System.out.println("! on a ajouté de ");
							tabP.addAll(tm.getTachesP(t + 1, w));
							tache_a_ajouter = false;
						}
						ajoutTacheASinecessaire(tabA, t, (nextreveil - t));
						ppcm -= (nextreveil - t);
						t += (nextreveil - t);
						nbPremptions++;
					}

					else
					{// la tache continue à s'executer
						if (nextreveil == (t + 1)) // on verifie que des taches
						// n'arrivents pas avant de
						// changer nxt reveil
						{
							tache_a_ajouter = true;
							// System.out.println("! ajouterP apres");
						}

						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + " Tache " + courante.getId() + " s'execute :" + ciCourante + " et se termine.");

						if (tache_a_ajouter)
						{
							ajoutTacheASinecessaire(tabA, t, t + ciCourante);
							// System.out.println("! on a ajouté :" + tabP + t);
							tache_a_ajouter = false;
						}
						w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
						w.addEvent(t + ciCourante, "STOP", courante.getId());

						tabP.remove(courante);
						t += ciCourante;
						ppcm -= ciCourante;
					}
				}
			}
			else if (tabA.size() != 0) // le tableau des taches P est vide et il
			// ya des tachesA
			{
				System.out.println("Apériodiques");
				courante = tabA.get(0);
				// TODO Vérifier que l'on a testé le reveil d'autres taches à
				// cet instant
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);

				if (nextreveil - t >= ciCourante) // la tache peut s'executer
				// pendant son Ci
				{
					System.out.println("A t = " + t + "TacheA " + courante.getId() + " s'execute :" + ciCourante + " et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t + ciCourante, "STOP", courante.getId());
					tabA.remove(courante);
					tm.getTachesAperiodiques().remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
					tempAttenteTacheA.add(t - courante.getRi() - courante.getCi()); // mise a jour du temps
					// System.out.println(tache_a_ajouter);
				}
				else
				{
					if (tabP.size() != 0) // la tache courante est préemptée
					{
						// TODO il manque peut être un cas à traiter
						candidate = plusGrandePrioriteEDF(tabP);
						courante.setCi(courante.getCi() - nextreveil + t);
						courante = candidate;
					}// fin de boucle
					else
					{// la tache continue à s'executer
						System.out.println("A t = " + t + " TacheA " + courante.getId() + " s'execute :" + ciCourante);
						t += ciCourante;
						ppcm -= ciCourante;
					}
					if (tache_a_ajouter)
					{
						// System.out.println("! on a ajouté de ");
						tabP.addAll(tm.getTachesP(t + 1, w));
						tache_a_ajouter = false;
					}
				}// fin else
			} // fin elsif
			else
			{
				System.out.println("A t = " + t + " temps creux");
				t += 1;
				ppcm -= 1;
				tempsCreux++;

			}

		}// fin while(ppcm!=0)
		tempsExecution = t;
		System.out.println("Fin");
		affichageAnalyse(tempsExecution, tempsCreux, nbPremptions, tempAttenteTacheA);
		w.generateFile();

	}

	@SuppressWarnings("unchecked")
	public void RmBg() throws Exception
	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		int t = 0;
		int tempsCreux = 0;
		int tempsExecution = 0;
		int nbPremptions = 0;
		boolean tache_a_ajouter = false;
		ArrayList tempAttenteTacheA = new ArrayList(tm.getTachesAperiodiques().size());
		tabP.addAll(tm.getTachesP(t, w));
		tabA.addAll(tm.getTachesA(t, w));

		AbstractTache courante = null;
		TachePeriodique candidate = null;
		int ciCourante;
		int nextreveil;
		nextreveil = tm.nextReveil(t);

		/* Affichage des tests */
		int ppcm = tm.PPCM(tm.getTachesPeriodiques());
		System.out.println("PPCM : " + ppcm);
		tm.PrintRMTEST();
		System.out.println("Début");

		while (ppcm > 0)
		{
			System.out.println("debut de while t = " + t + " next reveil : " + nextreveil + "ppcm :" + ppcm);

			if (nextreveil == t && t != 0)
			{
				tabA.addAll(tm.getTachesA(t, w));
				tabP.addAll(tm.getTachesP(t, w));
				// DEBUG
				// System.out.println("tabP apres rajout:" + tabP);
			}
			if (tabP.size() != 0) // il y a des taches périodique en attente
			{
				courante = plusGrandePrioriteRM(tabP);
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);
				if (nextreveil - t >= ciCourante || (nextreveil == 0)) // la
				{
					System.out.println("A t = " + t + "Tache " + courante.getId() + " s'execute :" + ciCourante + "et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					ajoutTacheASinecessaire(tabA, t, t + ciCourante);
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t + ciCourante, "STOP", courante.getId());
					tabP.remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
				}
				else
				// on récupère les nouvelles taches se reveillant.
				{
					if (!plusGrandePrioriteRM(tabP, nextreveil).equals(courante))
					// Tache préemptée
					{
						System.out.println("préemptée par " + plusGrandePrioriteRM(tabP, nextreveil));
						ajoutTacheASinecessaire(tabA, t, (nextreveil - t));
						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "(next t =" + nextreveil + ")" + " TacheP " + courante.getId() + " sexecute : " + (nextreveil - t));
						w.addEvent(t + (nextreveil - t), "EXEC-E", courante.getId());

						// Mise a jour de la tache préemptée
						TachePeriodique maj = new TachePeriodique(tabP.get(tabP.indexOf(courante)).getId(), tabP.get(tabP.indexOf(courante)).getCi() - (nextreveil - t), tabP.get(tabP.indexOf(courante)).getDi(), tabP.get(tabP.indexOf(courante)).getPi());
						tabP.set((tabP.indexOf(courante)), maj);

						// la mise a jour entraine un Ci à 0 => on supprime la
						// tache //TODO( opération à optimiser)
						if (maj.getCi() == 0)
							tabP.remove(maj);

						ppcm -= (nextreveil - t);
						t += (nextreveil - t);
						nbPremptions++;

					}

					else
					{// la tache continue à s'executer
						if (nextreveil == (t + 1)) // on verifie que des taches
						// n'arrivents pas avant de
						// changere nxt reveil
						{
							tache_a_ajouter = true;
							// System.out.println("! ajouterP apres");
						}

						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "Tache " + courante.getId() + " sexecute :" + ciCourante + "et se termine");

						if (tache_a_ajouter)
						{
							ajoutTacheASinecessaire(tabA, t, t + ciCourante);
							// System.out.println("! on a ajouté :" + tabP + t);
							tache_a_ajouter = false;
						}
						w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
						w.addEvent(t + ciCourante, "STOP", courante.getId());
						tabP.remove(courante);
						t += ciCourante;
						ppcm -= ciCourante;
					}
				}
			}
			else if (tabA.size() != 0) // le tableau des taches P est vide et il
			// ya des taches A
			{
				System.out.println("Apériodiques");
				courante = tabA.get(0);
				// TODO Vérifier que l'on a testé le reveil d'autres taches à
				// cet instant
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);
				if (nextreveil - t >= ciCourante) // la tache peut s'executer
				// pendant son Ci
				{
					System.out.println("A t = " + t + "TacheA " + courante.getId() + " sexecute :" + ciCourante + " et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t + ciCourante, "STOP", courante.getId());
					tabA.remove(courante);
					tm.getTachesAperiodiques().remove(courante);

					t += ciCourante;
					ppcm -= ciCourante;

					tempAttenteTacheA.add(t - courante.getRi() - courante.getCi()); // mise a jour du temps
					// d'attente de la tache
					// apériodique
				}
				else
				// on récupère les nouvelles taches se reveillant.
				{
					System.out.println("else");
					// t=nextreveil;
					tabP.addAll(tm.getTachesP(nextreveil, w));
					tabA.addAll(tm.getTachesA(nextreveil, w));
					if (tabP.size() != 0) // la tache courante est préemptée
					{
						// TODO il manque peut être un cas à traiter
						candidate = plusGrandePrioriteRM(tabP);
						courante.setCi(courante.getCi() - nextreveil + t);
						courante = candidate;
					}
					else
					{// la tache continue à s'executer
						System.out.println("A t = " + t + "TacheA " + courante.getId() + " s'execute :" + ciCourante);
						t += ciCourante;
						ppcm -= ciCourante;
						System.out.println("else");
					}// fin else
				}// fin else
			} // fin elsif
			else
			{
				System.out.println("A t = " + t + "temps creux");
				t += 1;
				ppcm -= 1;
				tempsCreux++;
			}
		}
		tempsExecution = t;
		System.out.println("Fin");
		affichageAnalyse(tempsExecution, tempsCreux, nbPremptions, tempAttenteTacheA);
		w.generateFile();
	}

	public void EdfTbs() throws IOException
	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();

		int nbPremptions = 0;
		int tempsCreux = 0;
		int tempsExecution = 0;
		HashMap<Integer, Integer> tempAttenteTacheA = new HashMap<Integer, Integer>();

		int hyperperiode = tm.PPCM(tm.getTachesPeriodiques());
		double Us = tm.calculeUs();
		int t = 0;
		int nextReveil;
		int preemptions = 0;
		boolean preempte = false;
		AbstractTache courante, candidate;
		double inverseU;
		if (Us != 0)
			inverseU = 1.0 / Us;
		else
			inverseU = 0;
		tabP.addAll(tm.getTachesP(t, w));
		tabA.addAll(tm.getTachesA(t, w));
		calculeTempsAttenteAp(tempAttenteTacheA, tabA, t);
		// On effectue l'ordonnancement jusqu'à l'hyperpériode
		while (t < hyperperiode)
		{
			// On ajoute les taches qui se réveillent à t
			courante = plusGrandePrioritéTBS(inverseU, tabP, tabA);
			for (TachePeriodique tache : tabP)

				System.out.println("Tache " + tache.getId() + " Pi = " + tache.getPi() + " di = " + tache.getDi() + " ci = " + tache.getCi());
			for (TacheAperiodique tache : tabA)
				System.out.println("Tache " + tache.getId() + " ri = " + tache.getRi() + " di = " + tache.getDi() + " ci = " + tache.getCi());

			preempte = false;
			w.addEvent(t, "EXEC-B", courante.getId());
			nextReveil = tm.nextReveil(t);

			System.out.println("Nouveau réveil de la tache " + courante.getId() + " à t = " + t);
			// Si le prochain reveil est avant la fin de l'execution de la tache
			// courante, on regarde si elle préempte ou non.
			while (nextReveil < t + courante.getCi() && !preempte)
			{
				tabP.addAll(tm.getTachesP(nextReveil, w));
				tabA.addAll(tm.getTachesA(nextReveil, w));
				calculeTempsAttenteAp(tempAttenteTacheA, tabA, nextReveil);
				candidate = plusGrandePrioritéTBS(inverseU, tabP, tabA);
				// Si elle ne preempte pas on cherche le reveil suivant
				if (candidate == courante)
				{
					courante.setCi(courante.getCi() - (nextReveil - t));
					tempsExecution += nextReveil - t;
					t = nextReveil;
					nextReveil = tm.nextReveil(nextReveil);
					System.out.println(courante.getId() + " s'execute encore à t = " + t);
				}
				else
				// Si elle preempte, on change le booleen preempte pour sortir
				// de la boucle
				{
					preempte = true;
					preemptions++;
					w.addEvent(nextReveil, "EXEC-E", courante.getId());
					courante.setCi(courante.getCi() - (nextReveil - t));
					System.out.println(courante.getId() + " préemptée par " + candidate.getId() + "à t = " + nextReveil);
					tempsExecution += nextReveil - t;
					nbPremptions++;
					t = nextReveil;
					nextReveil = tm.nextReveil(nextReveil);
				}
			}
			// En sortant de la boucle, soit une tache à préempter soit la tache
			// courante s'est terminée
			if (!preempte)
			{
				w.addEvent(t + courante.getCi(), "EXEC-E", courante.getId());
				w.addEvent(t + courante.getCi(), "STOP", courante.getId());
				deleteAbstractTache(courante, tabP, tabA);

				tempsExecution += courante.getCi();
				t += courante.getCi();
				calculeTempsAttenteAp(tempAttenteTacheA, courante, t, true);

				System.out.println(courante.getId() + " fini à t = " + (t));

				tabP.addAll(tm.getTachesP(t, w));
				tabA.addAll(tm.getTachesA(t, w));
				calculeTempsAttenteAp(tempAttenteTacheA, tabA, t);
				courante = plusGrandePrioritéTBS(inverseU, tabP, tabA);
				// S'il n'y a plus de tache à executer.
				if (courante == null)
				{
					tempsCreux += nextReveil - t;
					t = nextReveil;
					nextReveil = tm.nextReveil(nextReveil);
					tabP.addAll(tm.getTachesP(t, w));
					tabA.addAll(tm.getTachesA(t, w));
					calculeTempsAttenteAp(tempAttenteTacheA, tabA, t);
				}
			}

		}
		ArrayList<Integer> tempAttenteA = new ArrayList<Integer>(tempAttenteTacheA.values());
		System.out.println(tempAttenteA);
		System.out.println("Fin" + tabP);
		affichageAnalyse(tempsExecution, tempsCreux, nbPremptions, tempAttenteA);
		w.generateFile();
	}

	/**
	 * Vérifie que suite à l'execution d'une tacheP, une ou des tachesA sont
	 * arrivées. Si oui, les rajoutes dans le tableau des taches A
	 * 
	 * @param tab
	 *            tableau des taches A
	 * @param deb
	 *            debut de l'intervalle de temps à étudier
	 * @param fin
	 *            fin de l'intervalle de temps à étudier
	 * 
	 */
	public void ajoutTacheASinecessaire(ArrayList<TacheAperiodique> tab,
			int deb, int fin)
	{
		for (TacheAperiodique tache : tm.getTachesAperiodiques())
		{
			if ((tache.getRi() <= fin) && (tache.getRi() > deb))
			{

				tab.add(tache);
				System.out.println("Arrivée de tacheA : " + tache.getId() + "se reveillant entre " + deb + "et" + fin + "  " + tab);
				w.addEvent(tache.getRi(), "START", tache.getId());
			}
		}

	}

	/**
	 * Vérifie que suite à l'execution d'une tache, une ou des tachesP sont
	 * arrivées. Si oui, les rajoutes dans le tableau des taches A
	 * 
	 * @param tab
	 *            tableau des taches
	 * @param deb
	 *            debut de l'intervalle de temps à étudier
	 * @param fin
	 *            fin de l'intervalle de temps à étudier
	 * 
	 */
	public ArrayList<TachePeriodique> TacheParrivant(
			ArrayList<TachePeriodique> tab, int deb, int fin)
	{
		ArrayList<TachePeriodique> result = new ArrayList<TachePeriodique>();
		// System.out.println("eeee : "+tab );
		for (TachePeriodique tache : tm.getTachesPeriodiques())
		{
			if (((tache.getPi() % deb) <= fin % deb) && ((tache.getPi() % deb) > deb % deb))
			{
				result.add(tache);

			}
		}
		return result;
	}

	/**
	 * Affichage sur la sortie des standard des divers données demandée dans le
	 * cahier des charges
	 * 
	 * @param tempqExe
	 *            Temps d'execution
	 * @param tempsCreux
	 *            Temps creux
	 * @param nbP
	 *            Nombre de préemptions
	 * @param tabTacheATemps
	 *            Tables des temps d'attentes des taches apériodiques
	 */
	public void affichageAnalyse(int tempqExe, int tempsCreux, int nbP,
			ArrayList<Integer> tabTacheATemps)
	{

		float moy = (float) 0.0;
		for (int i = 0; i < tabTacheATemps.size(); i++)
		{
			moy += (float) tabTacheATemps.get(i);
		}
		moy = moy / (float) tabTacheATemps.size();
		Object min = Collections.min(tabTacheATemps);
		Object max = Collections.max(tabTacheATemps);
		// Object max = Collections.sum(tabTacheATemps);
		System.out.println("****BILAN ET ANALYSE****");
		System.out.println("Temps d'execution : " + tempqExe);
		System.out.println("Temps creux : " + tempsCreux);
		System.out.println("Utilisation du processeur :" + ((tempqExe - tempsCreux) * 100) / tempqExe);
		System.out.println("Nombre de préemptions :" + nbP);
		System.out.println("****TacheAp****");
		System.out.println("Temps de réponse min : " + min);
		System.out.println("Temps de réponse max : " + max);
		System.out.println("Temps de réponse moy : " + moy);
	}

	/**
	 * 
	 * @param t
	 *            Tableau de tache à étudierTache
	 * @return La tache périodique ayant la priorité la plus forte selon RM
	 */
	public TachePeriodique plusGrandePrioriteRM(ArrayList<TachePeriodique> t)
	{
		int pi = t.get(0).getPi();
		TachePeriodique result = t.get(0);
		for (TachePeriodique tache : t)
		{
			if (tache.getPi() < pi /* && (tache.getCi() !=0) */)
				result = tache;
		}
		if (result == null)
			System.out.println("error plusGrandePrioriteRM renvoit null");
		return result;
	}

	/**
	 * 
	 * @param t
	 *            Tableau de tache à étudierTache
	 * @return La tache périodique ayant la priorité la plus forte selon RM
	 */
	public TachePeriodique plusGrandePrioriteRM(ArrayList<TachePeriodique> tab,
			int t)
	{
		int pi = tab.get(0).getPi();
		ArrayList<TachePeriodique> tab2 = new ArrayList<TachePeriodique>();
		tab2.addAll(tm.getTachesP(t));
		tab2.addAll(tab);
		TachePeriodique result = tab2.get(0);
		// System.out.println(t);
		for (TachePeriodique tache : tab2)
		{
			if (tache.getPi() < pi && t != 0 && (tache.getPi() % t == 0))
				result = tache;
		}
		// if (result == null)
		// System.out.println("error plusGrandePrioriteRM renvoit null");
		return result;
	}

	/**
	 * 
	 * @param t
	 *            Tableau de tache à étudierTache
	 * @return La tache périodique ayant la priorité la plus forte selon RM
	 */
	public TachePeriodique plusGrandePrioriteEDF(ArrayList<TachePeriodique> t)
	{
		int pi = t.get(0).getDi();
		TachePeriodique result = t.get(0);
		for (TachePeriodique tache : t)
		{
			if (tache.getDi() < pi /* && (tache.getCi() !=0) */)
				result = tache;
		}
		if (result == null)
			System.out.println("error plusGrandePrioriteEDF renvoit null");
		return result;
	}

	/**
	 * 
	 * @param t
	 *            Tableau de tache à étudierTache
	 * @return La tache périodique ayant la priorité la plus forte selon RM
	 */
	public TachePeriodique plusGrandePrioriteEDF(
			ArrayList<TachePeriodique> tab, int t)
	{
		int pi = tab.get(0).getDi();
		ArrayList<TachePeriodique> tab2 = new ArrayList<TachePeriodique>();
		tab2.addAll(tm.getTachesP(t));
		tab2.addAll(tab);
		TachePeriodique result = tab2.get(0);
		// System.out.println(t);
		for (TachePeriodique tache : tab2)
		{
			if (tache.getDi() < pi && t != 0 && (tache.getDi() % t == 0))
				result = tache;
		}
		// if (result == null)
		// System.out.println("error plusGrandePrioriteRM renvoit null");
		return result;
	}

	/**
	 * Recherche la taches parmi les périodiques et les apériodiques celle qui a
	 * le di le plus faible. L'algorithme commence par donner aux taches
	 * apériodiques une valeur pour le di
	 * 
	 * @param inverseU
	 *            L'inverse de l'utilisation processeur pour permettre
	 *            d'affecter des di aux taches apériodiques
	 * @param tabP
	 *            Liste des taches périodiques
	 * @param tabA
	 *            Liste des taches apériodiques
	 * @return La tache qui a la plus grande priorité, dans ce cas le di le plus
	 *         petit
	 */
	public AbstractTache plusGrandePrioritéTBS(double inverseU,
			ArrayList<TachePeriodique> tabP, ArrayList<TacheAperiodique> tabA)
	{
		createDiForTachesA(inverseU, tabA);
		AbstractTache retour;
		if (tabP.size() != 0)
			retour = tabP.get(0);

		else if (tabA.size() != 0)
			retour = tabA.get(0);
		else
			return null;
		for (AbstractTache tache : tabP)
		{
			if (tache.getDi() < retour.getDi())
				retour = tache;
		}
		for (AbstractTache tache : tabA)
		{
			if (tache.getDi() < retour.getDi())
				retour = tache;
		}
		return retour;
	}

	/**
	 * Compare le minimun de deux valeur
	 * 
	 * @param old
	 *            valeur jusque là minimun
	 * @param atester
	 *            valeur candidate a être minimun
	 * @return minimun entre old et atester
	 */
	public int checkMin(int old, int atester)
	{
		if (old <= atester)
			return old;
		else
			return atester;
	}

	/**
	 * Compare le maximum de deux valeur
	 * 
	 * @param old
	 *            valeur jusque là maximun
	 * @param atester
	 *            valeur candidate a être maximun
	 * @return maximun entre old et a tester
	 */
	public int checkMax(int old, int atester)
	{
		if (old >= atester)
			return old;
		else
			return atester;
	}

	/**
	 * Change les di des taches Apériodiques dont leur di est à 0 pour le mettre
	 * à la valeur : max(t, di_k-1) + Ci_k * inverseU pour la kième tache du
	 * tableau. di_k-1 est donc le di de la tache avant celle étudiée, si il n'y
	 * en as pas cette valeur est nulle
	 * 
	 * @param inverseU
	 *            Correspont à l'inverse du facteur d'utilisation du processeur
	 *            alloué pour les taches apériodiques
	 * @param taches
	 *            L'ensemble des taches étudiées. Leur di peut-être déjà
	 *            fixé(!=0), dans ce cas il n'est pas recalculé
	 */
	public void createDiForTachesA(double inverseU,
			ArrayList<TacheAperiodique> taches)
	{
		int diPrecedant = 0;
		int max;
		for (TacheAperiodique tache : taches)
		{
			if (tache.getDi() == 0)
			{
				max = Math.max(diPrecedant, tache.getRi());
				tache.setDi((int) (max + tache.getCi() * inverseU));
			}
			else
			{
				diPrecedant = tache.getRi() + tache.getDi();
			}
		}
	}

	public boolean deleteAbstractTache(AbstractTache toDelete,
			ArrayList<TachePeriodique> tabP, ArrayList<TacheAperiodique> tabA)
	{
		for (TachePeriodique tache : tabP)
		{
			if (tache.getId() == toDelete.getId())
			{
				tabP.remove(tache);
				return true;
			}
		}
		for (TacheAperiodique tache : tabA)
		{
			if (tache.getId() == toDelete.getId())
			{
				tabA.remove(tache);
				return true;
			}
		}
		return false;
	}

	public void calculeTempsAttenteAp(HashMap<Integer, Integer> tempsAp,
			AbstractTache tache, int t, boolean stop)
	{
		if (tache instanceof TacheAperiodique)
		{
			if (tempsAp.get(tache.getId()) == null)
				tempsAp.put(tache.getId(), t);
			else if (stop)
			{
				tempsAp.put(tache.getId(), t - tempsAp.get(tache.getId()));
			}
		}
	}

	public void calculeTempsAttenteAp(HashMap<Integer, Integer> tempsAp,
			ArrayList<TacheAperiodique> tabAp, int t)
	{
		for (TacheAperiodique tache : tabAp)
		{
			calculeTempsAttenteAp(tempsAp, tache, t, false);
		}
	}
}