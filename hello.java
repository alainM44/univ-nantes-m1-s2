package FicheP;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private JButton but = new JButton("Ajout");
	private Fiche[] carnet_adresses = new Fiche [3];
	// Constructeur
	public hello() {

		super("Titre de la JFrame");

		// Si on appuie sur la croix, le programme s'arrete
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On récupère le conteneur de la JFrame (this est
		// une JFrame car Hello hérite de JFrame)
		Container contentPane = this.getContentPane();
		// Choix du gestionnaire de disposition
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);

		JPanel panel = new JPanel();

		JLabel label = new JLabel(
				"Bonjour, ceci est une JFrame qui contient"+
		" un JPanel qui contient un JLabel");
		panel.add(jtf_nom);
		panel.add(jtf_prenom);
		panel.add(jtf_mail);
		panel.add(jtf_rue);
		panel.add(jtf_num);
		panel.add(jtf_codep);
		
		panel.add(jtf_pays);
		panel.add(jtf_tel);
		
		but.addActionListener(new button_handler());

		panel.add(but);
		
		
		
		
		// Ici ne sert pas car le panel est seul
		contentPane.add(panel, BorderLayout.CENTER);
		

		this.pack();
		this.setVisible(true);

		
	}

	// Méthode principale : démarrage du programme
	public static void main(String[] args) {
		new hello();
	}
	public class button_handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			Fiche f1 = new Fiche(jtf_nom.getText(), new Adresse(Integer.parseInt(jtf_num.getText()), jtf_rue.getText(), Integer.parseInt(jtf_codep.getText()), jtf_pays.getText()), jtf_mail.getText(),Integer.parseInt(jtf_tel.getText()));
			carnet_adresses[pos]=f1;
			pos++;
			
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

	public JButton getBut()
	{
		return but;
	}

	public Fiche[] getCarnet_adresses()
	{
		return carnet_adresses;
	}
	
}