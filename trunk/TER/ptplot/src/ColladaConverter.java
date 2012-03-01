import java.io.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.arnx.jsonic.JSON;

public class ColladaConverter {

    public static void main(final String[] args) {
		int i = 0;
		int maxN      = Integer.parseInt(args[i++]);
		int plotS     = Integer.parseInt(args[i++]);
		int aspectS   = Integer.parseInt(args[i++]);
		String nameX  = args[i++];
		String nameY  = args[i++];
		String nameZ  = args[i++];
		String inputFile  = args[i++];
		String outputFile = args[i++];

		// plot the input data
		InputStream  is = null;
		OutputStream os = null;
		try {
			is = inputFile.endsWith(".gz") 
				? new GZIPInputStream(new FileInputStream(inputFile))
				: new FileInputStream(inputFile);
			os = new FileOutputStream(outputFile);

			printProlog(os, maxN);

			StringBuilder buffer = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			int c = 0;
			int currentAid = -1;
			int filteredAid = -1;

			line = reader.readLine();
			while ((maxN < 0 || c < maxN) && line != null) {
				//System.out.println(line);

				if (line.startsWith("ok")) {
					//os.write("\nok\n".getBytes());
				} else if (line.equals("")) {
					if (buffer != null) {
						Map poly = JSON.decode(buffer.toString(), Map.class);
						Object plot = poly.get("plot");
						Object aspect = poly.get("group");
						if (plot != null 
							&& 
							(plotS < 0 || ((BigDecimal)plot).intValue() == plotS)
							
							&& (aspectS < 0 || 
								(aspect != null 
								 && ((BigDecimal)aspect).intValue() == aspectS))
						) {

							List<BigDecimal> intvX = (List<BigDecimal>)poly.get(nameX);
							double xl = intvX.get(0).doubleValue(); 
							double xu = intvX.get(1).doubleValue();
							List<BigDecimal> intvY = (List<BigDecimal>)poly.get(nameY);
							double yl = intvY.get(0).doubleValue(); 
							double yu = intvY.get(1).doubleValue();
							List<BigDecimal> intvZ = (List<BigDecimal>)poly.get(nameZ);
							double zl = intvZ.get(0).doubleValue(); 
							double zu = intvZ.get(1).doubleValue();

							printBox(os, c, 
									 ((BigDecimal)plot).intValue(),
									 xl, xu, yl, yu, zl, zu);
							++c;
						}

						buffer = null;
					}
				}
				
				if (buffer == null)  buffer  = new StringBuilder();
				buffer.append(line);
				buffer.append('\n');

				line = reader.readLine();
			}

			printEpilog(os);
		} 
		catch (Exception ex) {
			System.err.println(ex.toString());
			ex.printStackTrace();
		}
		finally {
			if (is != null) try { is.close(); } catch (IOException ex) {}
			if (os != null) try { os.close(); } catch (IOException ex) {}
		}
    }


	static void printProlog(OutputStream os, int maxN) throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<COLLADA xmlns=\"http://www.collada.org/2008/03/COLLADASchema\" version=\"1.5.0\">\n");
		sb.append("  <asset><revision>1.0</revision></asset>\n");
		sb.append("  <library_effects>\n");
		sb.append("  <effect id=\"whitePhong\"><profile_COMMON>\n");
		sb.append("	<technique sid=\"phong1\"><phong>\n");
		sb.append("	  <emission>  <color>0.0 0.0 0.0 0.0</color></emission>\n");
		//sb.append("	  <ambient>   <color>0.5 0.5 0.5 1.0</color></ambient>\n");
		//sb.append("	  <diffuse>   <color>0.5 0.5 0.5 1.0</color></diffuse>\n");
		//sb.append("	  <specular>  <color>0.5 0.5 0.5 1.0</color></specular>\n");
		//sb.append("	  <shininess> <float>20.0</float></shininess>\n");
		//sb.append("	  <reflective><color>1.0 1.0 1.0 1.0</color></reflective>\n");
		//sb.append("	  <reflectivity><float>0.5</float></reflectivity>\n");
		//sb.append("	  <transparent><color>1.0 1.0 1.0 1.0</color></transparent>\n");
		//sb.append("	  <transparency><float>1.0</float></transparency>\n");
		sb.append("	</phong></technique>\n");
		sb.append("  </profile_COMMON></effect>\n");

		sb.append("	<effect id=\"redPhong\"><profile_COMMON>\n");
		sb.append("	  <technique sid=\"phong2\"><phong>\n");
		sb.append("		<emission>  <color>1.0 0.0 0.0 1.0</color></emission>\n");
		sb.append("		<diffuse>   <color>1.0 0.5 0.5 1.0</color></diffuse>\n");
		sb.append("		<shininess> <float>50.0</float></shininess>\n");
		sb.append("	  </phong></technique>\n");
		sb.append("	</profile_COMMON></effect>\n");
		sb.append("	<effect id=\"bluePhong\"><profile_COMMON>\n");
		sb.append("	  <technique sid=\"phong3\"><phong>\n");
		sb.append("		<emission>  <color>0.0 0.0 1.0 1.0</color></emission>\n");
		sb.append("		<diffuse>   <color>0.5 0.5 1.0 1.0</color></diffuse>\n");
		sb.append("		<shininess> <float>50.0</float></shininess>\n");
		sb.append("	  </phong></technique>\n");
		sb.append("	</profile_COMMON></effect>\n");

		sb.append("  </library_effects><library_materials>\n");
		sb.append("	<material id=\"whiteMaterial\"><instance_effect url=\"#whitePhong\"/></material>\n");

		sb.append("	<material id=\"redMaterial\"><instance_effect url=\"#redPhong\"/></material>\n");
		sb.append("	<material id=\"blueMaterial\"><instance_effect url=\"#bluePhong\"/></material>\n");

		sb.append("  </library_materials>\n");
		sb.append("  <library_geometries>\n");
		sb.append("	<geometry id=\"box\" name=\"box\">\n");
		sb.append("	  <mesh>\n");
		sb.append("		<source id=\"box-Pos\">\n");
		sb.append("		  <float_array id=\"box-Pos-array\" count=\"24\">\n");
		// TODO
		sb.append("			-0.5 0.5 0.5\n");
		sb.append("			0.5 0.5 0.5\n");
		sb.append("			-0.5 -0.5 0.5\n");
		sb.append("			0.5 -0.5 0.5\n");
		sb.append("			-0.5 0.5 -0.5\n");
		sb.append("			0.5 0.5 -0.5\n");
		sb.append("			-0.5 -0.5 -0.5\n");
		sb.append("			0.5 -0.5 -0.5\n");
		//sb.append("			-0.7 0.7 0.7\n");
		//sb.append("			0.7 0.7 0.7\n");
		//sb.append("			-0.7 -0.7 0.7\n");
		//sb.append("			0.7 -0.7 0.7\n");
		//sb.append("			-0.7 0.7 -0.7\n");
		//sb.append("			0.7 0.7 -0.7\n");
		//sb.append("			-0.7 -0.7 -0.7\n");
		//sb.append("			0.7 -0.7 -0.7\n");
		sb.append("		  </float_array>\n");
		sb.append("		  <technique_common>\n");
		sb.append("			<accessor source=\"#box-Pos-array\" count=\"8\" stride=\"3\">\n");
		sb.append("			  <param name=\"X\" type=\"float\" /><param name=\"Y\" type=\"float\" /><param name=\"Z\" type=\"float\" />\n");
		sb.append("			</accessor>\n");
		sb.append("		  </technique_common>\n");
		sb.append("		</source>\n");
		sb.append("		<source id=\"box-0-Normal\">\n");
		sb.append("		  <float_array id=\"box-0-Normal-array\" count=\"18\">\n");
		sb.append("			1.0 0.0 0.0\n");
		sb.append("			-1.0 0.0 0.0\n");
		sb.append("			0.0 1.0 0.0\n");
		sb.append("			0.0 -1.0 0.0\n");
		sb.append("			0.0 0.0 1.0\n");
		sb.append("			0.0 0.0 -1.0\n");
		sb.append("		  </float_array>\n");
		sb.append("		  <technique_common>\n");
		sb.append("			<accessor source=\"#box-0-Normal-array\" count=\"6\" stride=\"3\">\n");
		sb.append("			  <param name=\"X\" type=\"float\"/><param name=\"Y\" type=\"float\"/><param name=\"Z\" type=\"float\"/>\n");
		sb.append("			</accessor>\n");
		sb.append("		  </technique_common>\n");
		sb.append("		</source>\n");
		sb.append("		<vertices id=\"box-Vtx\">\n");
		sb.append("		  <input semantic=\"POSITION\" source=\"#box-Pos\"/>\n");
		sb.append("		</vertices>\n");
		sb.append("		<polygons count=\"6\" material=\"WHITE\">\n");
		sb.append("		  <input semantic=\"VERTEX\" source=\"#box-Vtx\" offset=\"0\"/>\n");
		sb.append("		  <input semantic=\"NORMAL\" source=\"#box-0-Normal\" offset=\"1\"/>\n");
		sb.append("		  <p>0 4 2 4 3 4 1 4</p>\n");
		sb.append("		  <p>0 2 1 2 5 2 4 2</p>\n");
		sb.append("		  <p>6 3 7 3 3 3 2 3</p>\n");
		sb.append("		  <p>0 1 4 1 6 1 2 1</p>\n");
		sb.append("		  <p>3 0 7 0 5 0 1 0</p>\n");
		sb.append("		  <p>5 5 7 5 6 5 4 5</p>\n");
		sb.append("		</polygons>\n");
		sb.append("	  </mesh>\n");
		sb.append("	</geometry>\n");

		sb.append("	<geometry id=\"box-Red\" name=\"red box\">\n");
		sb.append("	  <mesh>\n");
		sb.append("		<polygons count=\"6\" material=\"RED\">\n");
		sb.append("		  <input semantic=\"VERTEX\" source=\"#box-Vtx\" offset=\"0\"/>\n");
		sb.append("		  <input semantic=\"NORMAL\" source=\"#box-0-Normal\" offset=\"1\"/>\n");
		sb.append("		  <p>0 4 2 4 3 4 1 4</p>\n");
		sb.append("		  <p>0 2 1 2 5 2 4 2</p>\n");
		sb.append("		  <p>6 3 7 3 3 3 2 3</p>\n");
		sb.append("		  <p>0 1 4 1 6 1 2 1</p>\n");
		sb.append("		  <p>3 0 7 0 5 0 1 0</p>\n");
		sb.append("		  <p>5 5 7 5 6 5 4 5</p>\n");
		sb.append("		</polygons>\n");
		sb.append("	  </mesh>\n");
		sb.append("	</geometry>\n");
		sb.append("	<geometry id=\"box-Blue\" name=\"blue box\">\n");
		sb.append("	  <mesh>\n");
		sb.append("		<polygons count=\"6\" material=\"BLUE\">\n");
		sb.append("		  <input semantic=\"VERTEX\" source=\"#box-Vtx\" offset=\"0\"/>\n");
		sb.append("		  <input semantic=\"NORMAL\" source=\"#box-0-Normal\" offset=\"1\"/>\n");
		sb.append("		  <p>0 4 2 4 3 4 1 4</p>\n");
		sb.append("		  <p>0 2 1 2 5 2 4 2</p>\n");
		sb.append("		  <p>6 3 7 3 3 3 2 3</p>\n");
		sb.append("		  <p>0 1 4 1 6 1 2 1</p>\n");
		sb.append("		  <p>3 0 7 0 5 0 1 0</p>\n");
		sb.append("		  <p>5 5 7 5 6 5 4 5</p>\n");
		sb.append("		</polygons>\n");
		sb.append("	  </mesh>\n");
		sb.append("	</geometry>\n");

		sb.append("  </library_geometries>\n");
		sb.append("  <library_visual_scenes><visual_scene id=\"DefaultScene\">\n");
		/*
		sb.append("	  <node id=\"Box\" name=\"Box\">\n");
		sb.append("		<translate> 0 0 0</translate>\n");
		sb.append("		<rotate> 0 0 1 0</rotate><rotate> 0 1 0 0</rotate><rotate> 1 0 0 0</rotate>\n");
		sb.append("		<scale> 1 1 1</scale>\n");
		sb.append("		<instance_geometry url=\"#box\">\n");
		sb.append("		  <!--<bind_material><technique_common>\n");
		sb.append("			<instance_material symbol=\"WHITE\" target=\"#whiteMaterial\"/>\n");
		sb.append("		  </technique_common></bind_material>-->\n");
		sb.append("		</instance_geometry>\n");
		sb.append("	  </node>\n");
		*/
		sb.append("\n");

		os.write(sb.toString().getBytes());
	}

	static void printBox(OutputStream os, int c, int grp,
						 double xl, double xu, double yl, double yu, 
						 double zl, double zu) 
		throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("	  <node id=\"Box"+new Integer(c)+"\" name=\"Box "
				  +new Integer(c)+"\">\n");
		printBoxLoc(sb, xl, xu, yl, yu, zl, zu);
		switch (grp) {
		case 2:
			sb.append("		<instance_geometry url=\"#box-Red\">\n");
			break;
		case 3:
			sb.append("		<instance_geometry url=\"#box-Blue\">\n");
			break;
		default:
			sb.append("		<instance_geometry url=\"#box\">\n");
		}
		sb.append("		  <bind_material><technique_common>\n");
		switch (grp) {
		case 2:
			sb.append("			<instance_material symbol=\"RED\" target=\"#redMaterial\"/>\n");
			break;
		case 3:
			sb.append("			<instance_material symbol=\"BLUE\" target=\"#blueMaterial\"/>\n");
			break;
		default:
			sb.append("			<instance_material symbol=\"WHITE\" target=\"#whiteMaterial\"/>\n");
		}
		sb.append("		  </technique_common></bind_material>\n");
		sb.append("		</instance_geometry>\n");
		sb.append("	  </node>\n");

		os.write(sb.toString().getBytes());
	}

	static void printBoxLoc(StringBuilder sb,
							double xl, double xu, double yl, double yu, 
							double zl, double zu) 
		throws IOException {

		double ratio  = 100;
		double ratioZ = 1000;

		sb.append("		<translate>"
				  +new Double((xl+xu)/2*ratio)+" "
				  +new Double((yl+yu)/2*ratio)+" "
				  +new Double((zl+zu)/2*ratioZ)
				  +"</translate>\n");
		sb.append("		<scale>"
				  +new Double(Math.abs(xu-xl)*ratio)+" "
				  +new Double(Math.abs(yu-yl)*ratio)+" "
				  +new Double(Math.abs(zu-zl)*ratioZ)+"</scale>\n");
	}

	static void printEpilog(OutputStream os) throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("  </visual_scene></library_visual_scenes>\n");
		sb.append("  <scene><instance_visual_scene url=\"#DefaultScene\"/></scene>\n");
		sb.append("</COLLADA>\n");

		os.write(sb.toString().getBytes());
	}
}
