package test_3D_2D;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;
// classes Java 3D
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.Appearance;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;

// attention vous ne pouvez pas utiliser un m�me objet (shape3d), comme notre cube, dans plusieur Group de transformation
// il faut cr�er 2 entit� distinct comme nous l'avons fait

public class Test_Box extends JFrame implements WindowListener
{	 
	private static long Used_memory;
	private static double elapsedTimeInSec;
	private static long CpuTime; 
	private static long start;
	public static float x;
	public static float y;
	public static float z;
	private static Vector3f vector = new Vector3f();

	public Test_Box(int n,int display)
	{
		super("-Test de JAVA 3D -");
		this.addWindowListener(this);
		setLayout(new BorderLayout());
		// 1ere �tape cr�ation du Canvas3d qui vas afficher votre univers virtuel avec une config pr� etablie
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

		add("Center", canvas3D);
		// 2eme �tape on cr�e notre scene (regroupement d'objet)
		BranchGroup scene = createSceneGraph(n,display);
		// on les compile pour optimiser les calcules
		scene.compile();

		// 3eme �tape on creer l'univer qui va contenir notre scene 3d
		// utilise simpleUniverse qui simplifie le code (il cr�e un environemment minimal simple)
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		// on met le plan de projection en arriere par rapport a l'origine
		simpleU.getViewingPlatform().setNominalViewingTransform();
		// on place la scene dans l'univers simpleU
		simpleU.addBranchGraph(scene);

		JScrollPane jsp = new JScrollPane(this.getContentPane());
		setContentPane(jsp);
	}
	private float randRange(float min, float max) {
		return min + (float)Math.random() * (max - min);
	}

	//cr�e un regroupement d'objet contenant un objet cube
	public BranchGroup createSceneGraph(int n ,int display)
	{
		Random alea = new java.util.Random(System.currentTimeMillis()); // pour le choix de la couleur
		BranchGroup objRoot=new BranchGroup();
		for(int i=1;i<=n;i++)
		{
			
			x = randRange(-1.0f,1.0f);
			y = randRange(-1.0f,1.0f);
			z=  randRange(-1.0f,1.0f);

			//------------ d�but de creation du premier cube  ------------
			// on cr�e un vecteur de translation 30 cm suivant les Y
		//	Transform3D translate = new Transform3D();
			//Test.vector.set(x, y, z);
			//translate.set(vector);

			// on cr�e une matrice de tranformation pour faire tourner notre cube
			//Transform3D rotate = new Transform3D();
			//(X represente la horizontal orient� vers le droite, Y represente la vertical orient� vers le haut, Z pointe sur vous)
			//rotate.rotX(Math.PI/3.0d);//rotation d'angle Pi/3

			// on combine les deux transformations: translation puis rotation
			//translate1.mul(rotate);
			//translate.mul(rotate);
			// on cr�e un groupe de transformation rotate suivant la matrice de transformation translate1
			TransformGroup TG1 = new TransformGroup(/*translate*/);

			// on cr�e un cube qui herite de cette rotation
			Appearance ap =new Appearance();
			//Color3f color = new Color3f(x,y,z);
			ap.setColoringAttributes(new ColoringAttributes(x,y,z, ColoringAttributes.FASTEST));
			//ap.setColoringAttributes(new ColoringAttributes(Math.abs(alea.nextInt()) % 256,Math.abs(alea.nextInt()) % 256, Math.abs(alea.nextInt()) % 256,ColoringAttributes.FASTEST));
					
			Box para = new Box(x,y,z,ap);
			TG1.addChild(para);// de rayon 30 cm


			objRoot.addChild(TG1);
			//------------ fin de creation du premier cube  ------------
//			System.out.println(Math.abs(alea.nextInt()) % 256);
		
	}
	return objRoot;

}

public void windowActivated(WindowEvent e){}
public void windowClosed(WindowEvent e){}
public void windowDeactivated(WindowEvent e){}
public void windowDeiconified(WindowEvent e){}
public void windowIconified(WindowEvent e){}
public void windowOpened(WindowEvent e){}

public void windowClosing(WindowEvent e)
{
	System.exit(1);
}

public static void main(String args[])
{
	int n =Integer.parseInt(args[0]);
	int display = Integer.parseInt(args[1]);
	System.out.println(n+","+display);
	start = System.nanoTime(); 
	Test_Box myApp=new Test_Box(n,display);
	myApp.setSize(new Dimension(display,display));
	myApp.setVisible(true);

	Runtime s_runtime = Runtime.getRuntime ();
	Used_memory = s_runtime.totalMemory () - s_runtime.freeMemory ();  
	elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
	CpuTime = test_3D_2D.Test_Box.getCpuTime();

	System.out.println("elapsed time :"+ elapsedTimeInSec+"s");
	System.out.println("CPU Time:"+ CpuTime*(1.0e-9)+"s");
	System.out.println("Total memory :"+ s_runtime.totalMemory()/1048576 +"mo" ); 
	System.out.println("Free memory :"+ s_runtime.freeMemory()/1048576 +"mo"); 
	System.out.println("Used memory :"+ Used_memory/1048576 +"mo"); // a vérifier

}
/** Get CPU time in nanoseconds. */
public static long getCpuTime( ) 
{
	ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	return bean.isCurrentThreadCpuTimeSupported( ) ?
			bean.getCurrentThreadCpuTime() : 0L;
}


}