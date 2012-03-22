package archive2D;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Point;
import java.lang.management.ManagementFactory;
import java.util.Random;
import java.lang.management.*;




public class Main {
   private static long Used_memory;
   private static double elapsedTimeInSec;
   private long CpuTime;
   private long start;
   private Color color;
   private Dimension panel_size;
   private Dimension pave_size;
   private Point position;
   private Double n;
   private JCanvas jc;

   public static int x1;
   public static int x2;
   public static int y1;
   public static int y2;



   public void swapx1x2() {
      int tmp =Main.x1 ;
      Main.x1=Main.x2;
      Main.x2=tmp;

   }
   public void swapy1y2() {
      int tmp =Main.y1 ;
      Main.y1=Main.y2;
      Main.y2=tmp;

   }


   public Main(Double nb_pave, int display ){
      super();
      this.n=nb_pave;

      start = System.nanoTime(); // requires java 1.5
      Random alea = new java.util.Random(System.currentTimeMillis()); // pour le choix de la couleur

      jc = new JCanvas();
      jc.setBackground(Color.WHITE);
      jc.setFocusable(true);



      this.panel_size= new Dimension(display,display);
      jc.setPreferredSize(panel_size);


      for (int i =0; i<n;i++){

         x1 = Math.abs(alea.nextInt(display) );
         x2 = Math.abs(alea.nextInt(display) );
         y1 = Math.abs(alea.nextInt(display) );
         y2 = Math.abs(alea.nextInt(display) );
         if ((x1>x2))
            swapx1x2();
         if ((y1>y2))
            swapy1y2();

         this.position = new Point(x1,y1);
         this.pave_size = new Dimension(x2-x1,y2-y1);
//         System.out.println("Position="+this.position+" Dimension="+this.pave_size);

         this.color = new Color(Math.abs(alea.nextInt()) % 256,Math.abs(alea.nextInt()) % 256, Math.abs(alea.nextInt()) % 256);
         IDrawable rect = new RectangleDrawable(color,position,pave_size);
         jc.addDrawable(rect);
//       System.out.println("carre :"+i+" dessiné à la position ("+position.getX()+","+position.getY()+ ") de taille : "+ pave_size.getHeight()+","+pave_size.getWidth()+")");
      }

      GUIHelper.showOnFrame(jc.JCanvastoSrcoll(jc),"Test de performances",display);

      Runtime s_runtime = Runtime.getRuntime ();
      Used_memory = s_runtime.totalMemory () - s_runtime.freeMemory (); // bytes
      elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
      CpuTime = archive2D.Main.getCpuTime();
      System.out.println("Nombre de solutions :"+ n);
            System.out.println("Time start :"+ start);
      System.out.println("elapsed time :"+ elapsedTimeInSec+"s");
      System.out.println("CPU Time:"+ CpuTime*(1.0e-9)+"s"); // a vérifier
      System.out.println("Total memory :"+ s_runtime.totalMemory()/1048576 +"mo" ); // a vérifier
      System.out.println("Free memory :"+ s_runtime.freeMemory()/1048576 +"mo"); // a vérifier
      System.out.println("Used memory :"+ Used_memory/1048576 +"mo"); // a vérifier
   }

   /** Get CPU time in nanoseconds. */
   public static long getCpuTime( )
   {
      ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
      return bean.isCurrentThreadCpuTimeSupported( ) ?
            bean.getCurrentThreadCpuTime() : 0L;
   }

   public static void main(String [] args){
      Double n =Double.parseDouble(args[0]);
      int display = Integer.parseInt(args[1]);
      Main main = new Main(n,display);
   }
}
