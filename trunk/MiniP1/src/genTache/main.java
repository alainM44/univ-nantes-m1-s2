package genTache;

import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class main {

	static int temps = 1000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.util.Random r = new java.util.Random();
		XStream xstream = new XStream();
		int nbTachesP = 0;
		int nbTachesAP = 0;
		int Ci;
		int Pi;
		int Di;
		int U = temps;
		int reste;
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
			for (int i = 1; i <= nbTachesP; i++) {
				do {
					Pi = r.nextInt(temps)+1;
					Di = r.nextInt(Pi)+1;
					Ci = r.nextInt(Di)+1;
					reste = ((int)((((double)Ci)/((double)Pi))*temps))+1;
				} while (U - reste  < nbTachesP + nbTachesAP - i);
				U = U - reste;
				tab[i - 1] = new TachePeriodique(i, Ci, Di, Pi);
				System.out.println(Pi+" "+Ci+" "+Di+" "+U);
			}

		} else {
			for (int i = 1; i < nbTachesP + 1; i++) {
				System.out.println("Vous avez "+U+" unités de temps disponibles");
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
				reste = ((int)((((double)Ci)/((double)Pi))*temps))+1;
				U-=reste;
			}
		}
		System.out
				.println("Souhaitez vous une génération automatique pour les tâches apériodiques ? (y/n)");
		genauto = in.next().equals("y");
		if (genauto) {
			for (int i = nbTachesP + 1; i <= nbTachesP + nbTachesAP; i++) {
				Di = r.nextInt(U - nbTachesAP + i - nbTachesP )+1;
				Ci = r.nextInt(Di)+1;
				U = U - Di;
				tab[i - 1] = new TacheAperiodique(i, Ci, Di);
				System.out.println(" "+Ci+" "+Di+" "+U);			}

		} else {
			for (int i = nbTachesP + 1; i < nbTachesP + nbTachesAP + 1; i++) {
				System.out.println("Attention il ne vous reste que "+U+" unités de temps disponibles");
				System.out
						.println("Merci d'entrer les valeurs pour les paramètres de la tache "
								+ i);
				System.out.println("Valeur pour Ci :");
				Ci = in.nextInt();
				System.out.println("Valeur pour Di :");
				Di = in.nextInt();
				tab[i - 1] = new TacheAperiodique(i, Ci, Di);
				U = U - Di;
			}
		}
		System.out.println(xstream.toXML(tab));

	}

}
