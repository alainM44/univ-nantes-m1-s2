package genTache;

import java.util.Scanner;

import com.thoughtworks.xstream.XStream;



public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream();
		int nbTachesP = 0;
		int nbTachesAP = 0;
		int Ci;
		int Pi;
		int Di;
		Tache[] tab;
		boolean genauto = true;
		Scanner in = new Scanner(System.in);

		System.out.println("Saisissez le nombre de tâches périodiques : ");
		nbTachesP = in.nextInt();
		System.out.println("Saisissez le nombre de tâches apériodiques : ");
		nbTachesAP = in.nextInt();
		tab = new Tache[nbTachesP + nbTachesAP];
		System.out
				.println("Souhaitez vous une génération automatique pour les tâches périodiques ? (y/n)");
		genauto = in.next().equals("y");
		if (genauto) {

		} else {
			for (int i = 1; i < nbTachesP + 1; i++) {
				System.out
						.println("Merci d'entrer les valeurs pour les paramètres de la tache "
								+ i);
				System.out.println("Valeur pour Ci :");
				Ci = in.nextInt();
				System.out.println("Valeur pour Di :");
				Di = in.nextInt();
				System.out.println("Valeur pour Pi :");
				Pi = in.nextInt();
				tab[i - 1] = new TachePeriodique(Ci, Di, i, Pi);
			}
		}
		System.out
				.println("Souhaitez vous une génération automatique pour les tâches apériodiques ? (y/n)");
		genauto = in.next().equals("y");
		if (genauto) {

		} else {
			for (int i = nbTachesP + 1; i < nbTachesP + nbTachesAP + 1; i++) {
				System.out
						.println("Merci d'entrer les valeurs pour les paramètres de la tache "
								+ i);
				System.out.println("Valeur pour Ci :");
				Ci = in.nextInt();
				System.out.println("Valeur pour Di :");
				Di = in.nextInt();
				tab[i -1] = new TacheAperiodique(Ci, Di, i - nbTachesP);
			}
		}

		 System.out.println(xstream.toXML(tab));

	}


}
