package sstr;

import genTache.AbstractTache;
import genTache.TacheAperiodique;
import genTache.TachePeriodique;
import java.util.ArrayList;

public class Algorithms
{
	TasksManager tm;

	public Algorithms(TasksManager tm)
	{
		super();
		this.tm = tm;
	}

	public void RmBg() throws Exception
	{

		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		int t = 0;
		tabP.addAll(tm.getTachesP(t));
		tabA.addAll(tm.getTachesA(t));

		/* Tests affichages des test */
		int ppcm = tm.PPCM(tabP);
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
			if (tabP.size() == 0) // on reremplit

				// System.out.println("taches en attentes : "+tabP);
				courante = plusGrandePrioriteRM(tabP);
			ciCourante = courante.getCi();
			nextreveil = tm.nextReveil(t);
			if (tabP.size() != 0) // il y a des taches périodique en attente
			{

				if (nextreveil - t >= ciCourante) // la tache peut s'executer
				// pendant son Ci
				{
					System.out.println("Tache " + courante.getId()
							+ " sexecute :" + ciCourante);
					tabP.remove(courante);
					t += ciCourante;
					ppcm -= ciCourante;
				} else
				// on récupère les nouvelles taches se reveillant.
				{
					// t=nextreveil;
					System.out.println("a t = " + t + tm.getTachesP(nextreveil)
							+ "next reveil = " + nextreveil);
					tabP.addAll(tm.getTachesP(nextreveil));
					tabA.addAll(tm.getTachesA(nextreveil));
					candidate = plusGrandePrioriteRM(tabP);
					if (!candidate.equals(courante)) // la tache courante est
					// préemptée
					{
						System.out.println("Tache " + courante.getId()
								+ " sexecute :"
								+ (courante.getCi() - nextreveil + t));
						t += (courante.getCi() - nextreveil + t);
						ppcm -= (courante.getCi() - nextreveil + t);
						tabP.get(tabP.indexOf(courante)).setCi( // modification
								// le ci restait
								// toujour le
								// meme .
								courante.getCi() - nextreveil + t);

						courante = candidate;

					}// fin de boucle
					else
					{// la tache continue à s'executer
						System.out.println("else");
					}
				}

			} else if (tabA.size() != 0) // le tableau des taches P est vide et
			// il ya
			// des taches A
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
					System.out.println("Tache " + courante.getId()
							+ " sexecute :" + ciCourante);
					tabA.remove(courante);
					t += ciCourante;

				} else
				// on récupère les nouvelles taches se reveillant.
				{
					// t=nextreveil;
					tabP.addAll(tm.getTachesP(nextreveil));
					tabA.addAll(tm.getTachesA(nextreveil));
					if (tabP.size() != 0) // la tache courante est
					// préemptée
					{
						candidate = plusGrandePrioriteRM(tabP);
						courante.setCi(courante.getCi() - nextreveil + t);
						courante = candidate;
					}// fin de boucle
					else
					{// la tache continue à s'executer
					}
				}

			}
		}// fin while(ppcm!=0)
		System.out.println("Fin");
	}

	public TachePeriodique plusGrandePrioriteRM(ArrayList<TachePeriodique> t)
	{
		int pi = t.get(0).getPi();he
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