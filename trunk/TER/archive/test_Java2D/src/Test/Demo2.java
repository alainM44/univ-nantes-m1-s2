package Test;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.management.*;



import javax.management.MBeanServerConnection;





public class Demo2 {
	private static long Used_memory;
	private static double elapsedTimeInSec;
	private long CpuTime; 
	private long stopTime;
	private long startTime ;
	private long start;
	
	private Double n;

	public Demo2(Double arg ){
		super();
		this.n=arg;
		start = System.nanoTime(); // requires java 1.5
		JCanvas jc = new JCanvas();
		jc.setBackground(Color.WHITE);
		jc.setPreferredSize(new Dimension(400,200));
		Dimension dim  =new Dimension(20,20);

		int x =20;
		int y =10;

		this.startTime = System.currentTimeMillis();
		for (int i =0; i<Math.sqrt(n);i++){
			for (int j = 0; j<Math.sqrt(n);j++){

				IDrawable rect = new RectangleDrawable(Color.RED,new Point(x,y),dim);	
				jc.addDrawable(rect);	
				//System.out.println("carre :"+"("+i+","+j+")"+"dessiné a la pos "+x+","+y);
				x+=25;
			}
			x=20;
			y+=25;
		}
		stopTime = System.currentTimeMillis();

		GUIHelper.showOnFrame(jc,"Test de performances");

		Runtime s_runtime = Runtime.getRuntime ();
		Used_memory = s_runtime.totalMemory () - s_runtime.freeMemory (); // bytes 
		elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
		CpuTime = Test.Demo2.getCpuTime();
		System.out.println("Time start :"+ start);
		System.out.println("elapsed time :"+ elapsedTimeInSec+"s");
		//System.out.println("elapsed time2 :" + (stopTime - startTime)*1.0e-3 +"s");
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

		Demo2 main = new Demo2(n);
		
	
	}

	




	//	private double getAverageValueByLinux() throws InterruptedException {
	//		try {
	//
	//			long delay = 50;
	//			List<Double> listValues = new ArrayList<Double>();
	//			for (int i = 0; i < 100; i++) {
	//				long cput1 = getCpuT(pattern);
	//				Thread.sleep(delay);
	//				long cput2 = getCpuT(pattern);
	//				double cpuproc = (1000d * (cput2 - cput1)) / (double) delay;
	//				listValues.add(cpuproc);
	//			}
	//			listValues.remove(0);
	//			listValues.remove(listValues.size() - 1);
	//			double sum = 0.0;
	//			for (Double double1 : listValues) {
	//				sum += double1;
	//			}
	//			return sum / listValues.size();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return 0;
	//		}
	//
	//	}
	//
	//	private long getCpuT(Pattern pattern) throws FileNotFoundException, IOException {
	//		BufferedReader reader = new BufferedReader(new FileReader("/proc/stat"));
	//		String line = reader.readLine();
	//		Matcher m = pattern.matcher(line);
	//
	//		long cpuUser = 0;
	//		long cpuSystem = 0;
	//		if (m.find()) {
	//			cpuUser = Long.parseLong(m.group(1));
	//			cpuSystem = Long.parseLong(m.group(3));
	//		}
	//		return cpuUser + cpuSystem;
	//	}
	//
}
