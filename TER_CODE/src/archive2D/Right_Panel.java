package archive2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;

@SuppressWarnings("serial")

public class Right_Panel extends JPanel{
	
	// cadre de texte ou seront affichés les solutions avec leurs données
	private Font font;
	private Border main_border;
	private Border second_border;

	private JLabel l_nb_sol;
	private JLabel value_nb_sol;
	private JLabel l_time;
	private JLabel value_time;
	
	private JTextArea jtxtarea;
	private JLabel l_jtxtarea;
		
	private JComboBox combo1;
	private JLabel l_combo1;
	private JComboBox combo2;
	private JLabel l_combo2;
	private JRadioButton radio1;
	private JLabel l_radio1;
	private JRadioButton radio2;
	private JLabel l_radio2;
	private JRadioButton radio3;
	private JLabel l_radio3;
	private JRadioButton radio4;
	private JLabel l_radio4;
	
	

	public Right_Panel(){
		super();
		//	this.font = new Font("Helvetica.Italic", Font.BOLD,20); a debugger
		setFont(this.font);
		this.setBackground(Color.gray);
		this.main_border=BorderFactory.createLineBorder(Color.black, 5);
		this.second_border=BorderFactory.createLineBorder(Color.red, 2);
		this.setBorder(main_border);


		Box global_vertical_box = Box.createVerticalBox(); // Box dans laquelle on va empiler les différentse "rubriques" info générales, Solution, Filtres..

		///INFO GENERALES
		Box infog_vertical_box = Box.createVerticalBox();
		//nb solutions
		Box nb_sol_box =Box.createHorizontalBox();
		l_nb_sol= new JLabel("Number of solutions: ");

		value_nb_sol= new JLabel("0");
		nb_sol_box.add(l_nb_sol);
		nb_sol_box.add(value_nb_sol);

		infog_vertical_box.add(nb_sol_box);
		infog_vertical_box.add(Box.createRigidArea(new Dimension(0,10)));
		infog_vertical_box.add(Box.createVerticalGlue());

		//time
		Box time_box =Box.createHorizontalBox();
		l_time = new JLabel("Time (s) : ");
		value_time = new JLabel("0");
		time_box.add(l_time);
		time_box.add(value_time);


		infog_vertical_box.add(nb_sol_box);
		infog_vertical_box.add(time_box);
		infog_vertical_box.setBorder(second_border);
		global_vertical_box.add(infog_vertical_box);
		global_vertical_box.add(Box.createRigidArea(new Dimension(0,20)));
/////////////////////////////////////

		////Cadre de solutions
		Box solution_vertical_box = Box.createVerticalBox();
		jtxtarea = new JTextArea("No solution yet", 8, 8);
		l_jtxtarea = new JLabel("Solutions : ");
		//	l_jtxtarea.setAlignmentX(Component.LEFT_ALIGNMENT);
		solution_vertical_box.add(l_jtxtarea);
		solution_vertical_box.add(Box.createRigidArea(new Dimension(0,10)));
		solution_vertical_box.add(jtxtarea);
		solution_vertical_box.setBorder(second_border);
		global_vertical_box.add(solution_vertical_box);
		global_vertical_box.add(Box.createRigidArea(new Dimension(0,20)));
/////////////////////////////////////
		
		
		//////Filtres et conditions;
		Box filtres_vertical_box = Box.createVerticalBox();
		
		Box combo1_box = Box.createHorizontalBox();
		this.combo1=new JComboBox();
		this.l_combo1 =new JLabel("Condition 1 : ");
		combo1.addItem("Option 1");
		combo1.addItem("Option 2");
		combo1.addItem("Option 3");
		combo1.addItem("Option 4");
		combo1_box.add(l_combo1);
		combo1_box.add(combo1);
		
		filtres_vertical_box.add(combo1_box);
		
		Box combo2_box = Box.createHorizontalBox();
		this.combo2=new JComboBox();
		this.l_combo2 =new JLabel("Condition 2 : ");
		combo2.addItem("Option A");
		combo2.addItem("Option B");
		combo2.addItem("Option C");
		combo2.addItem("Option D");
		combo2_box.add(l_combo2);
		combo2_box.add(combo2);
		
		filtres_vertical_box.add(combo2_box);
		solution_vertical_box.add(Box.createRigidArea(new Dimension(0,10)));
		
		//RADIO
		Box radioA_box = Box.createHorizontalBox();
		l_radio1 = new JLabel(" Filter 1 ");
		radio1 = new JRadioButton();
		radioA_box.add(l_radio1);
		radioA_box.add(radio1);
		filtres_vertical_box.add(radioA_box);
		l_radio2 = new JLabel(" Filter 2 ");
		radio2 = new JRadioButton();
		radioA_box.add(l_radio2);
		radioA_box.add(radio2);

		Box radioB_box = Box.createHorizontalBox();
		
		l_radio3 = new JLabel(" Filter 3 ");
		radio3 = new JRadioButton();
		radioB_box.add(l_radio3);
		radioB_box.add(radio3);
		filtres_vertical_box.add(radioB_box);
		l_radio4 = new JLabel(" Filter 4 ");
		radio4 = new JRadioButton();
		radioB_box.add(l_radio4);
		radioB_box.add(radio4);
		
		filtres_vertical_box.add(radioB_box);
		
		filtres_vertical_box.setBorder(second_border);
		global_vertical_box.add(filtres_vertical_box);
		
		
		
		this.add(global_vertical_box);
		this.setOpaque(true);
	}

	//	public void setFont( Font font){
	//		this.l_nb_sol.setFont(font);
	//		this.value_nb_sol.setFont(font);
	//		this.l_time.setFont(font);
	//		this.value_time.setFont(font);
	//		this.jtxtarea.setFont(font);
	//		this.l_jtxtarea.setFont(font);
	//	}
	//	

	public static void main(String [] args){
		JFrame my_jf =new  JFrame ("test de right Panel");
		my_jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		my_jf.add(new Right_Panel());

		my_jf.setVisible(true);
		my_jf.setPreferredSize(new Dimension(300,500));
		my_jf.pack();

	}



}
