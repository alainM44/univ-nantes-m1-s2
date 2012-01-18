package FicheP;

import javax.swing.table.AbstractTableModel;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleDynamiqueObjet extends AbstractTableModel {
//	private final List<Fiche> fiches = new ArrayList<Fiche>();

	private final String[] entetes = {"Nom", "mail","Tel"};
	private  List<Fiche> fiches;
	public ModeleDynamiqueObjet(List<Fiche> fiches) {
		super();

		this.fiches=fiches;
		

		Fiche f1 = new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
//		f1.setM_prenoms("Jacque", 0);
//		f1.setM_prenoms("Jean", 1);
//		f1.setM_prenoms("Joseph", 2);

		Fiche f2= new Fiche("Dupont", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
//		f2.setM_prenoms("Jacque", 0);
//		f2.setM_prenoms("Jean", 1);
//		f2.setM_prenoms("Joseph", 2);
		Fiche f3= new Fiche("Durand", new Adresse(8, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
//		f3.setM_prenoms("George", 0);
//		f3.setM_prenoms("Ramir", 1);
		Fiche f4 = new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
	
		fiches.add(f1);
		fiches.add(f2);
		fiches.add(f3);
		fiches.add(f4);
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
//
//		System.out.println(fiches.get(rowIndex).getM_nom());
//		System.out.println(fiches.get(rowIndex2).getM_nom());
		return(fiches.get(rowIndex).compare(fiches.get(rowIndex2)));
//			System.out.println("Egales");
//		
//		else
//			System.out.println("Pas Egales");
//	
		}
}