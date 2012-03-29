package boxTest;
import interfaces.IBox;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;


public class mainTest {

	private static long Used_memory;
	private static double elapsedTimeInSec;
	private static long CpuTime;
	private static long start;
	private static int n; // nombre de boîtes à tester

	
	public static void fulling_box_number(IBox b ,int nb_coord,int nb_cara){
		int i =0;
		for(i=0;i<nb_coord;i++)
			b.addCoord(i, new Interval(1.2, 1.3));
		for(i=0;i<nb_cara;i++)
			b.addCara(i, 1.3253564564);

	}
	
	public static void fulling_box_string(IBox b ,int nb_coord,int nb_cara){
		int i =0;
		for(i=0;i<nb_coord;i++)
			b.addCoord(i, new Interval(1.2, 1.3));
		for(i=0;i<nb_cara;i++)
			b.addCara(i, "Une chaine de caractères");

	}
	
	public static void fulling_box_interval(IBox b ,int nb_coord,int nb_cara){
		int i =0;
		for(i=0;i<nb_coord;i++)
			b.addCoord(i, new Interval(1.2, 1.3));
		for(i=0;i<nb_cara;i++)
			b.addCara(i, new Interval(1.2, 1.3));

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		n = 1000000;
		 start = System.nanoTime();
		 IBox boxs[]= new IBox[n];
		 IBox b;
		
		//Box b;
		//String name="box";
		for(int i=0;i<n;i++)
		{
			b = new BoxArrayList(i);
			fulling_box_interval(b, 100, 20);
			boxs[i]=b;
		}
		Runtime s_runtime = Runtime.getRuntime();
		Used_memory = s_runtime.totalMemory() - s_runtime.freeMemory(); // bytes
		elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
		CpuTime = getCpuTime();
		System.out.println("Nombre de boîtes :" + n);
	//	System.out.println("Time start :" + start);
		System.out.println("elapsed time :" + elapsedTimeInSec + "s");
		System.out.println("CPU Time:" + CpuTime * (1.0e-9) + "s"); // a
		// vérifier
		System.out.println("Total memory :" + s_runtime.totalMemory() / 1048576
				+ "mo"); // a vérifier
		System.out.println("Free memory :" + s_runtime.freeMemory() / 1048576
				+ "mo"); // a vérifier
		System.out.println("Used memory :" + Used_memory / 1048576 + "mo"); // a
		// vérifier

	}
	
	 /** Get CPU time in nanoseconds. */
	   public static long getCpuTime( )
	   {
	      ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
	      return bean.isCurrentThreadCpuTimeSupported( ) ?
	            bean.getCurrentThreadCpuTime() : 0L;
	   }
}
