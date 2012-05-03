package boxTest;

import interfaces.IBox;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class mainTest {

	private static long Used_memory;
	private static double elapsedTimeInSec;
	private static long CpuTime;
	private static long start;
	private static int n; // nombre de boîtes à tester
	private static int nbAcces;

	/**
	 * Remplit une boîte avec uniquement des Number
	 * 
	 * @param b
	 *            La boîte à replir
	 * @param nb_coord
	 *            Le nombre de coordonnées dans la boîte
	 * @param nb_cara
	 *            Le nombre de caractéristiques de la boîte
	 */
	public static void fulling_box_number(IBox b, int nb_coord, int nb_cara) {
		int i = 0;
		for (i = 0; i < nb_coord; i++)
			b.addCoord(i, new Interval(Math.random(), Math.random()));
		for (i = 0; i < nb_cara; i++)
			b.addCara(i, Math.random());

	}

	/**
	 * Remplit une boîte avec uniquement des String
	 * 
	 * @param b
	 *            La boîte à replir
	 * @param nb_coord
	 *            Le nombre de coordonnées dans la boîte
	 * @param nb_cara
	 *            Le nombre de caractéristiques de la boîte
	 */
	public static void fulling_box_string(IBox b, int nb_coord, int nb_cara) {
		int i = 0;
		for (i = 0; i < nb_coord; i++)
			b.addCoord(i, new Interval(Math.random(), Math.random()));
		for (i = 0; i < nb_cara; i++)
			b.addCara(i, "Une chaine de caracteres");

	}

	/**
	 * Remplit une boîte avec uniquement des Interval
	 * 
	 * @param b
	 *            La boîte à replir
	 * @param nb_coord
	 *            Le nombre de coordonnées dans la boîte
	 * @param nb_cara
	 *            Le nombre de caractéristiques de la boîte
	 */
	public static void fulling_box_interval(IBox b, int nb_coord, int nb_cara) {
		int i = 0;
		for (i = 0; i < nb_coord; i++)
			b.addCoord(i, new Interval(Math.random(), Math.random()));
		for (i = 0; i < nb_cara; i++)
			b.addCara(i, new Interval(Math.random(), Math.random()));

	}

	/**
	 * Remplit une boîte avec chaque type (Number, Interval, String) avec la
	 * même proportion 1/3 pour chacune à un arrondie près
	 * 
	 * @param b
	 *            La boîte à replir
	 * @param nb_coord
	 *            Le nombre de coordonnées dans la boîte
	 * @param nb_cara
	 *            Le nombre de caractéristiques de la boîte
	 */
	public static void each_cara_in_box(IBox b, int nb_coord, int nb_cara) {
		int i = 0;
		for (i = 0; i < nb_coord; i++)
			b.addCoord(i, new Interval(Math.random(), Math.random()));
		for (i = 0; i < nb_cara / 3; i++)
			b.addCara(i, new Interval(Math.random(), Math.random()));
		for (i = nb_cara / 3; i < 2 * nb_cara / 3; i++)
			b.addCara(i, "Une chaine de caracteres");
		for (i = 2 * nb_cara / 3; i < nb_cara; i++)
			b.addCara(i, Math.random());

	}

	public static void constructGlobalMap(Map<Integer, Integer> a, int nb_cara) {
		int i = 0;
		for (i = 0; i < nb_cara / 3; i++)
			a.put(i, i);
		for (i = nb_cara / 3; i < 2 * nb_cara / 3; i++)
			a.put(i, i - nb_cara / 3);
		for (i = 2 * nb_cara / 3; i < nb_cara; i++)
			a.put(i, i - 2 * nb_cara / 3);

	}

	public static void constructGlobalArrayType(ArrayList<String> a, int nb_cara) {
		int i = 0;
		for (i = 0; i < nb_cara / 3; i++)
			a.add("Interval");
		for (i = nb_cara / 3; i < 2 * nb_cara / 3; i++)
			a.add("String");
		for (i = 2 * nb_cara / 3; i < nb_cara; i++)
			a.add("Number");

	}

	public static void each_cara_in_box_with_global_array(IBox b, int nb_coord,
			int nb_cara, Map<Integer, Integer> globale) {
		int i = 0;
		for (i = 0; i < nb_coord; i++)
			b.addCoord(i, new Interval(Math.random(), Math.random()));
		for (i = 0; i < nb_cara / 3; i++)
			b.addCara(globale.get(i),
					new Interval(Math.random(), Math.random()));
		for (i = nb_cara / 3; i < 2 * nb_cara / 3; i++)
			b.addCara(globale.get(i), "Une chaine de caracteres");
		for (i = 2 * nb_cara / 3; i < nb_cara; i++)
			b.addCara(globale.get(i), Math.random());

	}

	public static void nRandomAccesCarac(IBox[] boxs, int nb_box, int nb_Acces,
			int nb_Carac, Map<Integer, Integer> globaleid,
			ArrayList<String> globaleType) {
		int rand;
		int randBox;
		IBox b;
		// System.out.println(globaleid);
		// System.out.println(globaleType);
		for (int i = 0; i < nbAcces; i++) {
			randBox = (int) (Math.random() * (nb_box));
			if (randBox == nb_box)
				randBox = 0;

			b = boxs[randBox];

			rand = (int) (Math.random() * (nb_Carac));
			if (rand == nb_Carac)
				rand = 0;
			if (globaleType.get(rand) == "Number") {
				b.getNumber(globaleid.get(rand));
			} else if (globaleType.get(rand) == "String") {
				b.getString(globaleid.get(rand));
			} else {
				b.getInterval(globaleid.get(rand));
			}
		}

	}

	public static void nRandomAccesCaracHashMap(IBox[] boxs, int nb_box,
			int nb_Acces, int nb_Carac, ArrayList<String> globaleType) {
		int rand;
		int randBox;
		IBox b;
		// System.out.println(globaleid);
		// System.out.println(globaleType);
		for (int i = 0; i < nbAcces; i++) {

			randBox = (int) (Math.random() * (nb_box));
			if (randBox == nb_box)
				randBox = 0;

			b = boxs[randBox];

			rand = (int) (Math.random() * (nb_Carac));
			// System.out.println(rand);
			if (rand == nb_Carac)
				rand = 0;
			if (globaleType.get(rand) == "Number") {
				b.getNumber(rand);
			} else if (globaleType.get(rand) == "String") {
				b.getString(rand);
			} else {
				b.getInterval(rand);
			}
		}

	}

	public static void nRandomAccesCoord(IBox[] boxs, int nb_box, int nb_Acces,
			int nb_Coord) {
		int rand;
		int randBox;
		IBox b;
		// System.out.println(globaleid);
		// System.out.println(globaleType);
		for (int i = 0; i < nbAcces; i++) {
			randBox = (int) (Math.random() * (nb_box));
			if (randBox == nb_box)
				randBox = 0;

			b = boxs[randBox];

			rand = (int) (Math.random() * (nb_Coord));
			if (rand == nb_Coord)
				rand = 0;
			b.getCoord(rand);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		n = 10000;
		nbAcces = 1000000 * 10;
		int nbCarac = 20;
		int nbCoord = 100;
		IBox boxs[] = new IBox[n];
		IBox b = null;
//		ArrayList<String> globaletype = new ArrayList<String>();
//		TreeMap<Integer, Integer> globaleid = new TreeMap<Integer, Integer>();
		// Box b;
		// String name="box";
//		constructGlobalMap(globaleid, nbCarac);
//		constructGlobalArrayType(globaletype, nbCarac);
		for (int i = 0; i < n; i++) {
			b = new BoxTreeMap(i);
			each_cara_in_box(b, nbCoord, nbCarac);
			boxs[i] = b;
		}
		start = System.nanoTime();
		CpuTime = getCpuTime();
		nRandomAccesCoord(boxs, n, nbAcces, nbCoord);

		Runtime s_runtime = Runtime.getRuntime();
		Used_memory = s_runtime.totalMemory() - s_runtime.freeMemory(); // bytes
		elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
		CpuTime = getCpuTime() - CpuTime;
		System.out.println("Nombre de boîtes :" + n);
		// System.out.println("Time start :" + start);
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
	public static long getCpuTime() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		return bean.isCurrentThreadCpuTimeSupported() ? bean
				.getCurrentThreadCpuTime() : 0L;
	}
}
