package archive3d;

/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Nous allons maintenant voir comment, � l'aide des TransformGroup, 
on peut positionner deux objets l'un par rapport � l'autre. Nous d�taillons 
ici les 3 utilisations principale de la classe Transform3D qui permet de faire 
des translations, des rotations ( voir le sch�ma rep�re ), et 
de retailler vos objet. Faites attention, une instance d'un objet ( Shape3D ) 
ne peut pas �tre ref�renc�e par deux TransformGroup 
ou BranchGroup. Par contre plusieurs Shape3D peuvent tres bien avoir le m�me 
BG ou TG. Il faut cr�er une instance de la Shape3D pour chaque TransformGroup 
ou BranchGroup.
 */

// classes Java standart
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
// classes Java 3D
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.ColorCube;

// attention vous ne pouvez pas utiliser un m�me objet (shape3d), comme notre cube, dans plusieur Group de transformation
// il faut cr�er 2 entit� distinct comme nous l'avons fait

@SuppressWarnings("serial")
public class MyPanel extends JPanel
{	    
	public MyPanel()
	{
		//      super("- 2 TG pour placer 2 cubes -");

		//  setLayout(new BorderLayout());
		// 1ere �tape cr�ation du Canvas3d qui vas afficher votre univers virtuel avec une config pr� etablie
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add(canvas3D);
		// 2eme �tape on cr�e notre scene (regroupement d'objet)
		BranchGroup scene = createSceneGraph();
		// on les compile pour optimiser les calcules
		//scene.compile();

		// 3eme �tape on creer l'univer qui va contenir notre scene 3d
		// utilise simpleUniverse qui simplifie le code (il cr�e un environemment minimal simple)
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		// on met le plan de projection en arriere par rapport a l'origine
		simpleU.getViewingPlatform().setNominalViewingTransform();
		// on place la scene dans l'univers simpleU
		simpleU.addBranchGraph(scene);
	}	

	//cr�e un regroupement d'objet contenant un objet cube
	public BranchGroup createSceneGraph()
	{
		//on cr�e le Bg principal
		BranchGroup objRoot=new BranchGroup();

		//------------ d�but de creation du premier cube  ------------
		// on cr�e un vecteur de translation 30 cm suivant les Y
		Transform3D translate1 = new Transform3D();
		translate1.set(new Vector3f(0.4f, 0.4f, 0.0f));

		// on cr�e une matrice de tranformation pour faire tourner notre cube
		Transform3D rotate = new Transform3D();
		//(X represente la horizontal orient� vers le droite, Y represente la vertical orient� vers le haut, Z pointe sur vous)
		rotate.rotX(Math.PI/3.0d);//rotation d'angle Pi/3

		// on combine les deux transformations: translation puis rotation
		//translate1.mul(rotate);
		translate1.mul(rotate);
		// on cr�e un groupe de transformation rotate suivant la matrice de transformation translate1
		TransformGroup TG1 = new TransformGroup(translate1);

		// on cr�e un cube qui herite de cette rotation
		TG1.addChild(new ColorCube(0.3));// de rayon 30 cm

		objRoot.addChild(TG1);
		//------------ fin de creation du premier cube  ------------


		//------------ d�but de creation du deuxieme cube  ------------
		// on cr�e un vecteur de translation 30 cm suivant les Y (dans l'autre sens)
		Transform3D translate2 = new Transform3D();
		translate2.set(new Vector3f(-0.4f, -0.4f, 0.0f));

		// on cr�e une matrice de tranformation pour faire tourner notre cube
		Transform3D rotate2 = new Transform3D();
		rotate2.rotZ(Math.PI/3.0d);//rotation d'angle Pi/3

		// on combine les deux transformations: translation puis rotation
		translate2.mul(rotate2);

		// on r�duit la taille du cube par 2 (on la multiplie par 0.5)
		translate2.setScale(0.5f);

		// on cr�e un groupe de transformation rotate suivant la matrice de transformation translate1
		TransformGroup TG2 = new TransformGroup(translate2);

		// on cr�e un cube qui herite de cette rotation
		TG2.addChild(new ColorCube(0.3));// de rayon 20 cm

		objRoot.addChild(TG2);
		//------------ fin de creation du deuxieme cube  ------------		
		setBorder(BorderFactory.createLineBorder(Color.RED));
//		setOpaque(true);
		return objRoot;
	}

	//	public void windowActivated(WindowEvent e){}
	//	public void windowClosed(WindowEvent e){}
	//	public void windowDeactivated(WindowEvent e){}
	//	public void windowDeiconified(WindowEvent e){}
	//	public void windowIconified(WindowEvent e){}
	//	public void windowOpened(WindowEvent e){}
	//	
	public void windowClosing(WindowEvent e)
	{
		System.exit(1);
	}

	public static void main(String args[])
	{	
		JFrame my_jf =new  JFrame ("test de right Panel");
		my_jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		my_jf.add(new MyPanel());

		my_jf.setVisible(true);
	//	my_jf.setPreferredSize(new Dimension(300,500));
		my_jf.pack();
		
//		cube3d2 myApp=new cube3d2();
//		myApp.setSize(300,300);
//		myApp.setVisible(true);
	}

}