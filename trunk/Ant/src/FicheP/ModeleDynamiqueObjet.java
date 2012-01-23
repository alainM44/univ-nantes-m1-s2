package FicheP;

import javax.swing.table.AbstractTableModel;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/*
 * Table pour la gestion dynamique des fiches d'adresses. Elle se trouve à l'ouest dans l'IHM
 */
public class ModeleDynamiqueObjet extends AbstractTableModel {

	/*
	 * Si l'on voulait afficher plus d'information il faudrait les ajouter dans ce tableau et rajouter les getteurs et les setteurs.
	 */
	private final String[] entetes = {"Nom", "mail","Tel"};
	private  List<Fiche> fiches;

	public ModeleDynamiqueObjet(List<Fiche> fiches) {
		super();
		this.fiches=fiches;
		/*
		 * A décommenter, s'il on veut la présence dans le carnet d'adresse au lancement de l'application
		 */
		//		Fiche f1 = new Fiche("Dupond", "jacque",new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		//		Fiche f2= new Fiche("Dupont","George", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		//		Fiche f3= new Fiche("Durand", "Michel", new Adresse(8, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		//		Fiche f4 = new Fiche("Dupond","jacque", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		//		fiches.add(f1);
		//		fiches.add(f2);
		//		fiches.add(f3);
		//		fiches.add(f4);
	}


	public int getRowCount() {
		return fiches.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	/*
	 * Methode permettant de récupérer les informations dans les colonnes de la table
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return fiches.get(rowIndex).getM_nom();
		case 1:
			return fiches.get(rowIndex).getM_mails();
		case 2:
			return fiches.get(rowIndex).getM_tel();

		default:
			return null; //Ne devrait jamais arriver
		}
	}

	public void addFiche(Fiche Fiche) {
		fiches.add(Fiche);

		fireTableRowsInserted(fiches.size() -1, fiches.size() -1);
	}

	public void removeFiche(int rowIndex) {
		fiches.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	public boolean compFiche(int rowIndex,int rowIndex2) {
		/*
		 *  Pour le deboggage
		 * System.out.println(fiches.get(rowIndex).getM_nom());
		 * System.out.println(fiches.get(rowIndex2).getM_nom());
		 * 	System.out.println(fiches.get(rowIndex).compare(fiches.get(rowIndex2)));
		 * 
		 */

		return(fiches.get(rowIndex).compare(fiches.get(rowIndex2)));
		//			System.out.println("Egales");
		//		
		//		else
		//			System.out.println("Pas Egales");
		//	
	}
}