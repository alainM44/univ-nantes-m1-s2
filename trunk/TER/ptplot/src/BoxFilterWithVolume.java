import java.io.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.arnx.jsonic.JSON;

public class BoxFilterWithVolume {

    public static void main(final String[] args) {
		int threshold  = Integer.parseInt(args[0]);
		String inputFile  = args[1];
		String outputFile = args[2];

		int dim = (args.length -3) /2;
		String[] axes = new String[dim];
		double[] scales = new double[dim];
		for (int i = 0; i < dim; ++i) {
			axes[i] = args[2*i+3];
			scales[i] = Double.parseDouble(args[2*i+4]);
		}

		// plot object
		JsonPlot plot = new JsonPlot();

		// plot the input data
		InputStream  is = null;
		OutputStream os = null;
		try {
			is = inputFile.endsWith(".gz") 
				? new GZIPInputStream(new FileInputStream(inputFile))
				: new FileInputStream(inputFile);
			os = inputFile.endsWith(".gz") 
				? new GZIPOutputStream(new FileOutputStream(outputFile))
				: new FileOutputStream(outputFile);

			StringBuilder buffer = null;
			StringBuilder buffer1 = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			double volume = 0;
			int currentAid = -1;
			int filteredAid = -1;

			line = reader.readLine();
			while (line != null) {
				//System.out.println(line);

				if (line.startsWith("ok")) {
					os.write("\nok\n".getBytes());
					return;
				} else if (line.equals("")) {
					if (buffer != null) {
						Map poly = JSON.decode(buffer.toString(), Map.class);

						Object aid = poly.get("group");
						if (aid != null &&
							currentAid != ((BigDecimal)aid).intValue()) {

							currentAid = ((BigDecimal)aid).intValue();

							if (c >= threshold) {
								os.write(buffer1.toString().getBytes());
								++filteredAid;
							}
							c = 0;
							buffer1 = null;
						}

						if (buffer1 == null) buffer1 = new StringBuilder();
						//buffer1.append(buffer.toString());
						poly.put("group", filteredAid);
						buffer1.append(JSON.encode(poly));
						buffer1.append('\n');
						buffer1.append('\n');
						++c;

						if (c % 400000 == 0) { // TODO
							os.write(buffer1.toString().getBytes());
							buffer1 = new StringBuilder();
						}

						buffer = null;
					}
				}
				
				if (buffer == null)  buffer  = new StringBuilder();
				buffer.append(line);
				buffer.append('\n');

				line = reader.readLine();
			}
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
}
