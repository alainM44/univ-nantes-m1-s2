package sstr;

import genTache.AbstractTache;
import genTache.TacheAperiodique;
import genTache.TachePeriodique;
import java.util.ArrayList;

public class Algorithms
{
	TasksManager tm;
	Writer w;

	public Algorithms(TasksManager tm)
	{
		super();
		this.tm = tm;
		this.w = new Writer("demo1.ktr",tm,tm.PPCM(tm.getTachesPeriodiques()));
	}

	public void RmBg() throws Exception
	{
		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		int t = 0;
		tabP.addAll(tm.getTachesP(t, w));
		tabA.addAll(tm.getTachesA(t, w));

		/* Tests affichages des test */
		int ppcm = tm.PPCM(tm.getTachesPeriodiques());
		System.out.println("PPCM : " + ppcm);
		System.out.println("RM cond necessaire" + tm.getRmCondNeccessaire());
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
			System.out.println("debut de while t = " + t + " next reveil : " + nextreveil);
			//			System.out.println("tabP :" + tabP);
			//			System.out.println("tabA :" + tabA);

			if (nextreveil == t && t != 0)
			{
				tabP.addAll(tm.getTachesP(t, w));
				System.out.println("tabP apres rajout:" + tabP);
			}
			if (tabP.size() != 0) // il y a des taches périodique en attente
			{
				courante = plusGrandePrioriteRM(tabP);
				ciCourante = courante.getCi();
				nextreveil = tm.nextReveil(t);

				if (nextreveil - t >= ciCourante || (nextreveil == 0)) // la  tache peut l'executer pendant son Ci
				{
					System.out.println("A t = " + t + "Tache " + courante.getId() + " sexecute :" + ciCourante + "et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					ajoutTacheASinecessaire(tabA, t, t + ciCourante);
					w.addEvent(t+ciCourante, "EXEC-E", courante.getId());

					w.addEvent(t+ciCourante, "STOP", courante.getId());

					tabP.remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
					//					System.out.println("tabp apres suppr" + tabP);

				}

				else
				// on récupère les nouvelles taches se reveillant.
				{
					//tabP.addAll(tm.getTachesP(nextreveil));
					//tabA.addAll(tm.getTachesA(nextreveil));
					candidate = plusGrandePrioriteRM(tabP);
					if (!candidate.equals(courante)) // la tache courante est
					// préemptée
					{
						w.addEvent(t, "EXEC-B", courante.getId());
						System.out.println("A t = " + t + "TacheP " + courante.getId() + " sexecute :" + (courante.getCi() - nextreveil + t));
						ajoutTacheASinecessaire(tabA, t, t + (courante.getCi() - nextreveil + t));
						w.addEvent(t+(courante.getCi() - nextreveil + t), "STOP", courante.getId());
						ppcm -= (courante.getCi() - nextreveil + t);
						//System.out.println("avant modif : " + tabP.get(tabP.indexOf(courante)).getCi());

						TachePeriodique maj = new TachePeriodique(tabP.get(tabP.indexOf(courante)).getId(), tabP.get(tabP.indexOf(courante)).getCi() - (courante.getCi() - nextreveil + t), tabP.get(tabP.indexOf(courante)).getDi(), tabP.get(tabP.indexOf(courante)).getPi());
						tabP.set((tabP.indexOf(courante)), maj);
						//tabP.get(tabP.indexOf(courante)).setCi(courante.getCi() - nextreveil + t); // modification le ci restait toujour le meme .
					//	System.out.println("apres modif : " + tabP.get(tabP.indexOf(maj)).getCi());
						courante = candidate;
						t += (courante.getCi() - nextreveil + t);
						ppcm -= (courante.getCi() - nextreveil + t);
					}

					else
					{// la tache continue à s'executer

						System.out.println("A t = " + t + "TacheP " + courante.getId() + " sexecute :" + (courante.getCi() - nextreveil + t));
						w.addEvent(t, "EXEC-B", courante.getId());
						ajoutTacheASinecessaire(tabA, t, t + (courante.getCi() - nextreveil + t));
						w.addEvent(t+(courante.getCi() - nextreveil + t), "EXEC-E", courante.getId());
						ppcm -= (courante.getCi() - nextreveil + t);

						TachePeriodique maj = new TachePeriodique(tabP.get(tabP.indexOf(courante)).getId(), tabP.get(tabP.indexOf(courante)).getCi() - (courante.getCi() - nextreveil + t), tabP.get(tabP.indexOf(courante)).getDi(), tabP.get(tabP.indexOf(courante)).getPi());
						tabP.set((tabP.indexOf(courante)), maj);

						t += (courante.getCi() - nextreveil + t);
						ppcm -= (courante.getCi() - nextreveil + t);

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
					System.out.println("A t = " + t + "TacheA " + courante.getId() + " sexecute :" + ciCourante + " et se termine");
					w.addEvent(t, "EXEC-B", courante.getId());
					w.addEvent(t+ciCourante, "EXEC-E", courante.getId());
					w.addEvent(t+ciCourante, "STOP", courante.getId());
					tabA.remove(courante);
					tm.getTachesAperiodiques().remove(courante);

					t += ciCourante;				
					ppcm -= ciCourante;
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
						System.out.println("A t = " + t + "TacheA " + courante.getId() + " sexecute :" + ciCourante);
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
			}

		}// fin while(ppcm!=0)
		System.out.println("Fin");
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
		//System.out.println("eeee : "+tab );
		for (TacheAperiodique tache : tm.getTachesAperiodiques())
		{
			if ((tache.getRi() <= fin) && (tache.getRi() >= deb))
			{

				tab.add(tache);
				System.out.println("Arrivée de tacheA : " + tache.getId() + "se reveillant entre " + deb + "et" + fin + "  " + tab);
				w.addEvent(tache.getRi(), "START", tache.getId());
			}
		}

	}

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
}