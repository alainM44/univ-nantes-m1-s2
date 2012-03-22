import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class mainTest {

	private static long Used_memory;
	private static double elapsedTimeInSec;
	private static long CpuTime;
	private static long start;
	private static int n; // nombre de boîtes à tester

	
	public void fulling_box(Box b ,int nb_coord,int nb_cara){
		b.setList_cara_interval(l)
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		n = Integer.valueOf(args[0]);
		 start = System.nanoTime();
		Box boxs[]= new Box[n];
		
		//Box b;
		//String name="box";
		for(int i=0;i<n;i++)
			boxs[i]=new Box(i);
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
