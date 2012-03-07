package sstr;

import genTache.AbstractTache;
import genTache.TacheAperiodique;
import genTache.TachePeriodique;

import java.lang.Thread.State;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.sql.CommonDataSource;

public class Algorithms
{
	TasksManager tm;
	Writer w;

	public Algorithms(TasksManager tm)
	{
		super();
		this.tm = tm;
		this.w = new Writer("demo1.ktr", tm, tm.PPCM(tm.getTachesPeriodiques()));
	}

	public void EdfBg() throws Exception

	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		int t = 0;
		int tempsCreux = 0;
		int tempsExecution = 0;
		int nbPremptions = 0;
		int cpt = 0; /*
					 * cas ou une tache continue de s'executer on cpt++ puis on
					 * reteste
					 */
		boolean flag_cpt, preempte = false;
		int t_temp = 0;
		boolean tache_a_ajouter = false;
		ArrayList tempAttenteTacheA = new ArrayList(tm.getTachesAperiodiques()
				.size());
		tabP.addAll(tm.getTachesP(t, w));
		tabA.addAll(tm.getTachesA(t, w));

		/* Tests affichages des test */
		int ppcm = tm.PPCM(tm.getTachesPeriodiques());
		System.out.println("PPCM : " + ppcm);
		// System.out.println("RM cond necessaire" + tm.getRmCondNeccessaire());
		// //TODO
		if (tm.getRmCondNeccessaire() > 1)
			throw new Exception("test necesseraire négatif");

		AbstractTache courante = null;
		TachePeriodique candidate = null;
		int ciCourante;
		int nextreveil;
		nextreveil = tm.nextReveil(t);

		System.out.println("Début");

		while (ppcm > 0)
		{
			System.out.println("debut de while t = " + t + " next reveil : "
					+ nextreveil + "ppcm :" + ppcm);

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
				System.out.println(courante);
				if (nextreveil - t >= ciCourante || (nextreveil == 0)) // la
				{
					System.out.println("A t = " + t + "Tache "
							+ courante.getId() + " sexecute :" + ciCourante
							+ "et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					ajoutTacheASinecessaire(tabA, t, t + ciCourante);
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());

					w.addEvent(t + ciCourante, "STOP", courante.getId());

					tabP.remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
					preempte = false;
					// System.out.println("tabp apres suppr" + tabP);
				}
				else
				// on récupère les nouvelles taches se reveillant.
				{
					//
					candidate = plusGrandePrioriteEDF(tabP);
					if (!plusGrandePrioriteEDF(tabP, nextreveil).equals(
							courante))
					// préemptée
					{
						System.out.println("préemptée par "
								+ plusGrandePrioriteEDF(tabP, nextreveil));
						preempte = true;

						ajoutTacheASinecessaire(tabA, t, (nextreveil - t));
						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "(next t ="
								+ nextreveil + ")" + " TacheP "
								+ courante.getId() + " sexecute : "
								+ (nextreveil - t) + "fffffffff");
						w.addEvent(t + (nextreveil - t), "EXEC-E", courante
								.getId());
						TachePeriodique maj = new TachePeriodique(tabP.get(
								tabP.indexOf(courante)).getId(), tabP.get(
								tabP.indexOf(courante)).getCi()
								- (nextreveil - t), tabP.get(
								tabP.indexOf(courante)).getDi(), tabP.get(
								tabP.indexOf(courante)).getPi());
						tabP.set((tabP.indexOf(courante)), maj);

						if (maj.getCi() == 0)
							tabP.remove(maj);

						ppcm -= (nextreveil - t);
						t += (nextreveil - t);
						nbPremptions++;
					}

					else
					{// la tache continue à s'executer

						if(t==5)
							System.out.println(plusGrandePrioriteEDF(tabP,nextreveil));
						if (nextreveil == (t + 1)) // on verifie que des taches
						// n'arrivents pas avant de changer
						// nxt reveil
						{
							tache_a_ajouter = true;
						
							System.out.println("! ajouterP apres");
						}

						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "Tache "
								+ courante.getId() + " sexecute :" + ciCourante
								+ "et se terminz");

						if (tache_a_ajouter)
						{

							ajoutTacheASinecessaire(tabA, t, t + ciCourante);
							System.out.println("! on a ajouté :" + tabP + t);
							tache_a_ajouter = false;
						}

						w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
						w.addEvent(t + ciCourante, "STOP", courante.getId());

						tabP.remove(courante);
						t += ciCourante;
						ppcm -= ciCourante;
						preempte = false;

					}
				}
			}
			else if (tabA.size() != 0) // le tableau des taches P est vide et il
			// ya des taches A
			{
				System.out.println("apériodiques");
				courante = tabA.get(0);
				// TODO Vérifier que l'on a testé le reveil d'autres taches à
				// cet instant
				ciCourante = courante.getCi();

				if (nextreveil == (t + 1)) // on verifie que des taches
				// n'arrivents pas avant de changer
				// nxt reveil
				{
					tache_a_ajouter = true;
					// tabP.addAll(tm.getTachesP(t+1, w));
					System.out.println("! ajouterP apres");
				}

				nextreveil = tm.nextReveil(t);

				if (nextreveil - t >= ciCourante) // la tache peut s'executer
				// pendant son Ci
				{
					System.out.println("A t = " + t + "TacheA "
							+ courante.getId() + " sexecute :" + ciCourante
							+ " et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t + ciCourante, "STOP", courante.getId());
					tabA.remove(courante);
					tm.getTachesAperiodiques().remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;

					tempAttenteTacheA.add(t - courante.getRi()
							- courante.getCi()); // mise a jour du temps
					// d'attente de la tache
					// apériodique
					System.out.println(tache_a_ajouter);
					if (tache_a_ajouter)
					{

						tabP.addAll(tm.getTachesP(t, w));
						System.out.println("! on a ajouté :" + tabP + t);
						tache_a_ajouter = false;
					}

				}
				else
				// on récupère les nouvelles taches se reveillant.
				{
					System.out.println("else");
					// t=nextreveil;
					tabP.addAll(tm.getTachesP(nextreveil, w));
					tabA.addAll(tm.getTachesA(nextreveil, w));
					if (tabP.size() != 0) // la tache courante est
					// préemptée
					{
						candidate = plusGrandePrioriteEDF(tabP);
						courante.setCi(courante.getCi() - nextreveil + t);
						courante = candidate;
					}// fin de boucle
					else
					{// la tache continue à s'executer
						System.out
								.println("A t = " + t + "TacheA "
										+ courante.getId() + " sexecute :"
										+ ciCourante);
						t += ciCourante;
						ppcm -= ciCourante;
						System.out.println("else");
					}// fin else
					if (tache_a_ajouter)
					{
						System.out.println("! on a ajouté de ");
						tabP.addAll(tm.getTachesP(t + 1, w));
						tache_a_ajouter = false;
					}

				}// fin else
			} // fin elsif
			else
			{
				System.out.println("A t = " + t + "temps creux");
				t += 1;
				ppcm -= 1;
				tempsCreux++;

			}

		}// fin while(ppcm!=0)
		tempsExecution = t;
		System.out.println("Fin" + tabA + tabP);
		affichageAnalyse(tempsExecution, tempsCreux, nbPremptions,
				tempAttenteTacheA);
		w.generateFile();

	}

	@SuppressWarnings("unchecked")
	public void RmBg() throws Exception

	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		ArrayList<TachePeriodique> candidates = new ArrayList<TachePeriodique>();
		int t = 0;
		int tempsCreux = 0;
		int tempsExecution = 0;
		int nbPremptions = 0;
		int cpt = 0; /*
					 * cas ou une tache continue de s'executer on cpt++ puis on
					 * reteste
					 */
		boolean preempte = false;/* on consomme le cpt */
		boolean tache_a_ajouter = false;
		ArrayList tempAttenteTacheA = new ArrayList(tm.getTachesAperiodiques()
				.size());
		tabP.addAll(tm.getTachesP(t, w));
		tabA.addAll(tm.getTachesA(t, w));

		/* Tests affichages des test */
		int ppcm = tm.PPCM(tm.getTachesPeriodiques());
		System.out.println("PPCM : " + ppcm);
		System.out.println("RM cond necessaire" + tm.getRmCondNeccessaire());
		if (tm.getRmCondNeccessaire() > 1)
			throw new Exception("test necesseraire négatif");
		int t_temp = 0;
		AbstractTache courante = null;
		TachePeriodique candidate = null;
		int ciCourante;
		int nextreveil;
		nextreveil = tm.nextReveil(t);

		System.out.println("Début");

		while (ppcm > 0)// && t!=29)
		{
			System.out.println("debut de while t = " + t + " next reveil : "
					+ nextreveil + "ppcm :" + ppcm);

			if (nextreveil == t && t != 0)
			{
				tabA.addAll(tm.getTachesA(t, w));
				tabP.addAll(tm.getTachesP(t, w));
				System.out.println("tabP apres rajout:" + tabP);
			}
			if (tabP.size() != 0) // il y a des taches périodique en attente
			{

				courante = plusGrandePrioriteRM(tabP);
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);

				if (nextreveil - t >= ciCourante || (nextreveil == 0)) // la
				{
					System.out.println("A t = " + t + "Tache "
							+ courante.getId() + " sexecute :" + ciCourante
							+ "et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					ajoutTacheASinecessaire(tabA, t, t + ciCourante);
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());

					w.addEvent(t + ciCourante, "STOP", courante.getId());

					tabP.remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
					preempte = false;
					// System.out.println("tabp apres suppr" + tabP);
				}

				else
				// on récupère les nouvelles taches se reveillant.
				{

					if (!plusGrandePrioriteRM(tabP, nextreveil)
							.equals(courante))
					// préemptée
					{
						System.out.println("préemptée par "
								+ plusGrandePrioriteRM(tabP, nextreveil));
						preempte = true;

						ajoutTacheASinecessaire(tabA, t, (nextreveil - t));
						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "(next t ="
								+ nextreveil + ")" + " TacheP "
								+ courante.getId() + " sexecute : "
								+ (nextreveil - t) + "fffffffff");
						w.addEvent(t + (nextreveil - t), "EXEC-E", courante
								.getId());
						TachePeriodique maj = new TachePeriodique(tabP.get(
								tabP.indexOf(courante)).getId(), tabP.get(
								tabP.indexOf(courante)).getCi()
								- (nextreveil - t), tabP.get(
								tabP.indexOf(courante)).getDi(), tabP.get(
								tabP.indexOf(courante)).getPi());
						tabP.set((tabP.indexOf(courante)), maj);

						if (maj.getCi() == 0)
							tabP.remove(maj);

						ppcm -= (nextreveil - t);
						t += (nextreveil - t);
						nbPremptions++;

					}

					else
					{// la tache continue à s'executer

						if (nextreveil == (t + 1)) // on verifie que des taches
						// n'arrivents pas avant de changer
						// nxt reveil
						{
							tache_a_ajouter = true;
							// tabP.addAll(tm.getTachesP(t+1, w));
							System.out.println("! ajouterP apres");
						}

						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "Tache "
								+ courante.getId() + " sexecute :" + ciCourante
								+ "et se terminz");

						if (tache_a_ajouter)
						{

							ajoutTacheASinecessaire(tabA, t, t + ciCourante);
							System.out.println("! on a ajouté :" + tabP + t);
							tache_a_ajouter = false;
						}

						w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
						w.addEvent(t + ciCourante, "STOP", courante.getId());

						tabP.remove(courante);
						t += ciCourante;
						ppcm -= ciCourante;
						preempte = false;

					}
				}
			}
			else if (tabA.size() != 0) // le tableau des taches P est vide et il
			// ya des taches A
			{
				System.out.println("apériodiques");
				courante = tabA.get(0);
				// TODO Vérifier que l'on a testé le reveil d'autres taches à
				// cet instant
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);
				if (nextreveil - t >= ciCourante) // la tache peut s'executer
				// pendant son Ci
				{
					System.out.println("A t = " + t + "TacheA "
							+ courante.getId() + " sexecute :" + ciCourante
							+ " et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					w.addEvent(t + ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t + ciCourante, "STOP", courante.getId());
					tabA.remove(courante);
					tm.getTachesAperiodiques().remove(courante);

					t += ciCourante;
					ppcm -= ciCourante;

					tempAttenteTacheA.add(t - courante.getRi()
							- courante.getCi()); // mise a jour du temps
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
					if (tabP.size() != 0) // la tache courante est
					// préemptée
					{
						candidate = plusGrandePrioriteRM(tabP);
						courante.setCi(courante.getCi() - nextreveil + t);
						courante = candidate;
					}// fin de boucle
					else
					{// la tache continue à s'executer
						System.out
								.println("A t = " + t + "TacheA "
										+ courante.getId() + " sexecute :"
										+ ciCourante);
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

		}// fin while(ppcm!=0)
		tempsExecution = t;
		System.out.println("Fin");
		affichageAnalyse(tempsExecution, tempsCreux, nbPremptions,
				tempAttenteTacheA);
		w.generateFile();
	}

	public void EdfTbs(double Us)
	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		int hyperperiode = tm.PPCM(tm.getTachesPeriodiques());
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
		while (t < hyperperiode)
		{

			courante = plusGrandePrioritéTBS(inverseU, tabP, tabA);
			for (TachePeriodique tache : tabP)

				System.out.println("Tache " + tache.getId() + " Pi = "
						+ tache.getPi() + " di = " + tache.getDi() + " ci = "
						+ tache.getCi());
			for (TacheAperiodique tache : tabA)
				System.out.println("Tache " + tache.getId() + " ri = "
						+ tache.getRi() + " di = " + tache.getDi() + " ci = "
						+ tache.getCi());

			preempte = false;
			w.addEvent(t, "EXEC-B", courante.getId());
			nextReveil = tm.nextReveil(t);

			System.out.println("Nouveau réveil de la tache " + courante.getId()
					+ " à t = " + t);
			while (nextReveil < t + courante.getCi() && !preempte)
			{
				tabP.addAll(tm.getTachesP(nextReveil, w));
				tabA.addAll(tm.getTachesA(nextReveil, w));
				candidate = plusGrandePrioritéTBS(inverseU, tabP, tabA);
				if (candidate == courante)
				{
					courante.setCi(courante.getCi() - (nextReveil - t));
					t = nextReveil;
					nextReveil = tm.nextReveil(nextReveil);
					System.out.println(courante.getId()
							+ " s'execute encore à t = " + t);
				}
				else
				{
					preempte = true;
					preemptions++;
					w.addEvent(nextReveil, "EXEC-E", courante.getId());
					courante.setCi(courante.getCi() - (nextReveil - t));
					System.out.println(courante.getId() + " préemptée par "
							+ candidate.getId() + "à t = " + nextReveil);
					t = nextReveil;
					nextReveil = tm.nextReveil(nextReveil);
				}
			}
			if (!preempte)
			{
				w.addEvent(t + courante.getCi(), "EXEC-E", courante.getId());
				w.addEvent(t + courante.getCi(), "STOP", courante.getId());
				deleteAbstractTache(courante, tabP, tabA);

				t = t + courante.getCi();

				System.out.println(courante.getId() + " fini à t = " + (t));

				tabP.addAll(tm.getTachesP(t, w));
				tabA.addAll(tm.getTachesA(t, w));
				courante = plusGrandePrioritéTBS(inverseU, tabP, tabA);
				if (courante == null)
				{
					t = nextReveil;
					nextReveil = tm.nextReveil(nextReveil);
					tabP.addAll(tm.getTachesP(t, w));
					tabA.addAll(tm.getTachesA(t, w));
				}
			}

		}

		System.out.println("Fin" + tabP);
		// affichageAnalyse(tempsExecution, tempsCreux, nbPremptions,
		// tempAttenteTacheA);
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
		// System.out.println("eeee : "+tab );
		for (TacheAperiodique tache : tm.getTachesAperiodiques())
		{
			if ((tache.getRi() <= fin) && (tache.getRi() > deb))
			{

				tab.add(tache);
				System.out.println("Arrivée de tacheA : " + tache.getId()
						+ "se reveillant entre " + deb + "et" + fin + "  "
						+ tab);
				w.addEvent(tache.getRi(), "START", tache.getId());
			}
		}

	}

	public ArrayList<TachePeriodique> TacheParrivant(
			ArrayList<TachePeriodique> tab, int deb, int fin)
	{
		ArrayList<TachePeriodique> result = new ArrayList<TachePeriodique>();
		// System.out.println("eeee : "+tab );
		for (TachePeriodique tache : tm.getTachesPeriodiques())
		{
			if (((tache.getPi() % deb) <= fin % deb)
					&& ((tache.getPi() % deb) > deb % deb))
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
		moy = moy / (float) tabTacheATemps.size() + 1;
		Object min = Collections.min(tabTacheATemps);
		Object max = Collections.max(tabTacheATemps);
		// Object max = Collections.sum(tabTacheATemps);
		System.out.println("****BILAN ET ANALYSE****");
		System.out.println("Temps d'execution : " + tempqExe);
		System.out.println("Temps creux : " + tempsCreux);
		System.out.println("Utilisation du processeur :"
				+ ((tempqExe - tempsCreux) * 100) / tempqExe);
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
		// System.out.println(t);
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
	public TachePeriodique plusGrandePrioriteEDF(ArrayList<TachePeriodique> tab)
	{
		int di = tab.get(0).getDi();
		TachePeriodique result = tab.get(0);
		// System.out.println(t);
		for (TachePeriodique tache : tab)
		{
			if (tache.getDi() < di /* && (tache.getCi() !=0) */)
				result = tache;
		}
		if (result == null)
			System.out.println("error plusGrandePrioriteRM renvoit null");
		return result;
	}

	public TachePeriodique plusGrandePrioriteEDF(
			ArrayList<TachePeriodique> tab, int t)
	{
		int di = tab.get(0).getDi();
		TachePeriodique result = tab.get(0);
		// System.out.println(t);
		for (TachePeriodique tache : tab)
		{
			if (tache.getDi() < di && (tache.getDi() % t == 0))
				result = tache;
		}
		if (result == null)
			System.out.println("error plusGrandePrioriteRM renvoit null");
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
}