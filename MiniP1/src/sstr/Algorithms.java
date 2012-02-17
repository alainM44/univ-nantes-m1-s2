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

	public void RmBg()
	{
		int t = 0;
		AbstractTache courante = null;
		TachePeriodique candidate = null;
		int ciCourante;
		int nextreveil;

		ArrayList<TachePeriodique> tabP = new ArrayList<TachePeriodique>();
		ArrayList<TacheAperiodique> tabA = new ArrayList<TacheAperiodique>();
		System.out.println("Début");
		tabP.addAll(tm.getTachesP(t));
		tabA.addAll(tm.getTachesA(t));
		courante = plusGrandePrioriteRM(tabP);

		if (tabP.size() != 0) // il y a des taches périodique en attente
		{
			ciCourante = courante.getCi();
			nextreveil = tm.nextReveil(t);
			if (nextreveil - t >= ciCourante) // la tache peut s'executer
			// pendant son Ci
			{
				System.out.println("Tache " + courante.getId() + " sexecute :"
						+ ciCourante);
				tabP.remove(courante);
				t += ciCourante;
			}
			else
			// on récupère les nouvelles taches se reveillant.
			{
				// t=nextreveil;
				tabP.addAll(tm.getTachesP(nextreveil));
				tabA.addAll(tm.getTachesA(nextreveil));
				candidate = plusGrandePrioriteRM(tabP);
				if (!candidate.equals(courante)) // la tache courante est
				// préemptée
				{
					courante.setCi(courante.getCi() - nextreveil + t);
					courante = candidate;

				}// fin de boucle
				else
				{// la tache continue à s'executer
				}
			}

		}
		else if (tabA.size() != 0) // le tableau des taches P est vide et il ya
									// des taches A
		{
			courante = tabA.get(0);
			// TODO Vérifier que l'on a testé le reveil d'autres taches à cet instant
			ciCourante = courante.getCi();
			nextreveil = tm.nextReveil(t);
			if (nextreveil - t >= ciCourante) // la tache peut s'executer
			// pendant son Ci
			{
				System.out.println("Tache " + courante.getId() + " sexecute :"
						+ ciCourante);
				tabA.remove(courante);
				t += ciCourante;
			}
			else
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

		System.out.println("Fin");
	}

	public TachePeriodique plusGrandePrioriteRM(ArrayList<TachePeriodique> t)
	{
		int pi = t.get(0).getPi();
		TachePeriodique result = null;
		for (TachePeriodique tache : t)
		{
			if (tache.getPi() < pi)
				result = tache;
		}
		return result;

	}
}