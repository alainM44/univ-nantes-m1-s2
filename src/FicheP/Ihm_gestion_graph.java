package FicheP;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Ihm_gestion_graph extends JFrame {
	private final List<Fiche> fiches = new ArrayList<Fiche>();
	private ModeleDynamiqueObjet modele = new ModeleDynamiqueObjet(fiches);
	private JTable tableau;

	private JTextField jtf_nom = new JTextField("Entrez un nom");
	private JTextField jtf_prenom = new JTextField("Entrez un prenom");
	private JTextField jtf_mail = new JTextField("Entrez une adresse mail");
	private JTextField jtf_rue = new JTextField("Entrez une rue");
	private JTextField jtf_num = new JTextField("Entrez un numéro");
	private JTextField jtf_codep = new JTextField("Entrez un codep");
	private JTextField jtf_pays = new JTextField("Entrez un pays");
	private JTextField jtf_tel = new JTextField("Entrez un numéro");
	private JLabel lab = new JLabel("");
	private JButton but_add = new JButton(new AddAction());
	private JButton but_sup = new JButton(new RemoveAction());
	private JButton but_comp = new JButton(new CompareAction());




	public Ihm_gestion_graph() {
		super();

		Box left_box = Box.createVerticalBox();

		jtf_nom.addMouseListener(new jtfhandler());
		jtf_codep.addMouseListener(new jtfhandler());
		jtf_prenom.addMouseListener(new jtfhandler());
		jtf_mail.addMouseListener(new jtfhandler());
		jtf_num.addMouseListener(new jtfhandler());
		jtf_pays.addMouseListener(new jtfhandler());
		jtf_rue.addMouseListener(new jtfhandler());
		jtf_tel.addMouseListener(new jtfhandler());
		
		left_box.add(jtf_nom);
		left_box.add(jtf_prenom);
		left_box.add(jtf_mail);
		left_box.add(jtf_rue);
		left_box.add(jtf_num);
		left_box.add(jtf_codep);
		left_box.add(jtf_pays);
		left_box.add(jtf_tel);


		Box but_box = Box.createHorizontalBox();
		//sbut_add.addActionListener(new button_handler());

		but_box.add(but_add);
		but_box.add(but_sup);
		left_box.add(but_box);
		getContentPane().add(left_box,BorderLayout.EAST);

		setTitle("JTable avec mod�le dynamique");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tableau = new JTable(modele);



		getContentPane().add(new JScrollPane(tableau), BorderLayout.WEST);

		Box boutons = Box.createHorizontalBox();

		boutons.add(but_add);
		boutons.add(but_sup);
		boutons.add(but_comp);
		boutons.add(Box.createHorizontalGlue());
		boutons.add(lab);


		getContentPane().add(boutons, BorderLayout.SOUTH);

		pack();
		this.pack();
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Ihm_gestion_graph().setVisible(true);
	}

	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter");
		}

		public void actionPerformed(ActionEvent e) {
			modele.addFiche(new Fiche(jtf_nom.getText(), new Adresse(Integer.parseInt(jtf_num.getText()), jtf_rue.getText(), Integer.parseInt(jtf_codep.getText()), jtf_pays.getText()), jtf_mail.getText(),Integer.parseInt(jtf_num.getText())));
		}
	}

	private class RemoveAction extends AbstractAction {
		private RemoveAction() {
			super("Supprimmer");
		}

		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();

			for(int i = selection.length - 1; i >= 0; i--){
				modele.removeFiche(selection[i]);
			}
		}
	}
	private class CompareAction extends AbstractAction {
		private CompareAction() {
			super("Comparer");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			int[] selection = tableau.getSelectedRows();
			//			System.out.println(selection.length);
			//			System.out.println(fiches.get(selection[0]).m_nom);
			//			System.out.println(fiches.get(selection[1]).m_nom);
			if	(modele.compFiche(selection[0], selection[1]))
				lab.setText("EGALES");
			else
				lab.setText(" PAS EGALES");


		}
	}
	
	private class jtfhandler implements MouseListener {

	

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JTextField source =(JTextField)e.getSource();
			source.setSelectionStart(0);
			source.setSelectionEnd(source.getText().length());
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public JLabel getLab() {
		return lab;
	}

	public void setLab(JLabel lab) {
		this.lab = lab;
	}


}