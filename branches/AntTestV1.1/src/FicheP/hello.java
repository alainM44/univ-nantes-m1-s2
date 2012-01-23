package FicheP;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListDataListener;


import com.thoughtworks.xstream.XStream;

public class hello extends JFrame {

	private JTextField jtf_nom = new JTextField("Entrez un nom");
	private JTextField jtf_prenom = new JTextField("Entrez un prenom");
	private JTextField jtf_mail = new JTextField("Entrez une adresse mail");
	private JTextField jtf_rue = new JTextField("Entrez une rue");
	private JTextField jtf_num = new JTextField("Entrez un numéro");
	private JTextField jtf_codep = new JTextField("Entrez un codep");
	private JTextField jtf_pays = new JTextField("Entrez un pays");
	private JTextField jtf_tel = new JTextField("Entrez un numéro");
	private int pos =0;
	private JButton but_add = new JButton("Ajout");
	private JButton but_sup = new JButton("Supprimer");
	private JComboBox combo1fiche = new JComboBox(); 
	private Fiche[] carnet_adresses = new Fiche [3];
	// Constructeur
	public hello() {

		super("Gestion de Fiches d'adresse");
		// Si on appuie sur la croix, le programme s'arrete
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On récupère le conteneur de la JFrame (this est
		// une JFrame car Hello hérite de JFrame)
		Container contentPane = this.getContentPane();
		// Choix du gestionnaire de disposition
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);


		Box left_box = Box.createVerticalBox();

		left_box.add(jtf_nom);
		left_box.add(jtf_prenom);
		left_box.add(jtf_mail);
		left_box.add(jtf_rue);
		left_box.add(jtf_num);
		left_box.add(jtf_codep);
		left_box.add(jtf_pays);
		left_box.add(jtf_tel);


		Box but_box = Box.createHorizontalBox();
		but_add.addActionListener(new button_handler());

		but_box.add(but_add);
		but_box.add(but_sup);
		left_box.add(but_box);
		contentPane.add(left_box,BorderLayout.EAST);

		// Ici ne sert pas car le panel est seul


		//PARTIE CENTRALE
		Box center_box = Box.createVerticalBox();


		//combo1fiche.setEditable(true);
		//center_box.add();

		contentPane.add(center_box, BorderLayout.CENTER);
		//	combo1fiche.addActionListener(this);


		this.pack();
		this.setVisible(true);


	}

	// Méthode principale : démarrage du programme
	public static void main(String[] args) {
		//new hello();
		new Ihm_gestion_graph();
	}
	public class button_handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			Fiche f1 = new Fiche(jtf_nom.getText(), new Adresse(Integer.parseInt(jtf_num.getText()), jtf_rue.getText(), Integer.parseInt(jtf_codep.getText()), jtf_pays.getText()), jtf_mail.getText(),Integer.parseInt(jtf_tel.getText()));
			carnet_adresses[pos]=f1;
			pos++;
			combo1fiche.addItem(f1.m_nom.toString());
			

		}

	}
	public JTextField getJtf_nom()
	{
		return jtf_nom;
	}

	public JTextField getJtf_prenom()
	{
		return jtf_prenom;
	}

	public JTextField getJtf_mail()
	{
		return jtf_mail;
	}

	public JTextField getJtf_rue()
	{
		return jtf_rue;
	}

	public JTextField getJtf_num()
	{
		return jtf_num;
	}

	public JTextField getJtf_codep()
	{
		return jtf_codep;
	}

	public JTextField getJtf_pays()
	{
		return jtf_pays;
	}

	public JTextField getJtf_tel()
	{
		return jtf_tel;
	}

	public int getPos()
	{
		return pos;
	}



	public Fiche[] getCarnet_adresses()
	{
		return carnet_adresses;
	}

}