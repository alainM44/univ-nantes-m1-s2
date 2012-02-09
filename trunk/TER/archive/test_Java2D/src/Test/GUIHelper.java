package Test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class GUIHelper {

   private static Right_Panel r_panel= new Right_Panel();

   private static JMenuBar menuBar = new JMenuBar();

   private static JMenu file = new JMenu("File"),display = new JMenu("Display"),about = new JMenu("About");

   private static JMenuItem save = new JMenuItem("Save"),
   open = new JMenuItem("Open"),
   save_as = new JMenuItem("Save_as"),
   quitter = new JMenuItem("Quitter"),
   about_item = new JMenuItem("About");


   private static JRadioButtonMenuItem twoD = new JRadioButtonMenuItem("2D"),
   threeD = new JRadioButtonMenuItem("3D");



   private static void initMenu(JFrame frame){
      //Menu file
      file.add(open);
      file.add(save);
      file.add(save_as);
      //Pour quitter l'application
      quitter.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            System.exit(0);
         }
      });
      file.add(quitter);

      //Menu Display
      display.add(twoD);
      display.add(threeD);

      twoD.setSelected(true);

      //menu à propos
      about.add(about_item);

      //Ajout des menus dans la barre de menus
      menuBar.add(file);
      menuBar.add(display);
      menuBar.add(about);

      //Ajout de la barre de menus sur la fenêtre
      frame.setJMenuBar(menuBar);
   }

   public static void showOnFrame(JComponent component, String frameName, int display) {

      JFrame frame = new JFrame(frameName);
      GUIHelper.initMenu(frame);
      WindowAdapter wa = new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      };
      frame.addWindowListener(wa);
      frame.getContentPane().setLayout(new GridLayout(1, 1));


      frame.getContentPane().add(component);
      frame.getContentPane().add(r_panel);
      frame.setPreferredSize(new Dimension(1000,500));
      frame.setVisible(true);
      frame.pack();

   }
   public static class  ScrollPane_Listener implements AdjustmentListener {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {


      }

   }




}
