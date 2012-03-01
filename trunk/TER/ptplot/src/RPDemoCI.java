import java.io.*;
import java.net.URL;
import java.net.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.zip.GZIPInputStream;
import javax.imageio.ImageIO;

import ptolemy.plot.EditablePlot;
import ptolemy.plot.Plot;

public class RPDemoCI {

	Socket socket = null;
	static String defaultAddr;
	static String defaultPort;
	static String defaultFile;

    public static void main(final String[] args) {
		String configFile = args[0];
		String inputFile  = args[1];
		String outputFile = args[2];
		String xAxis = args[3];
		String yAxis = args[4];
		int width  = Integer.parseInt(args[5]);
		int height = Integer.parseInt(args[6]);

		// plot object
		JsonPlot plot = new JsonPlot();

		try {
			plot.read(new FileInputStream(configFile));

			for (int g = 0; g < 6; ++g)
				if (g != 1)
					((Plot)plot).clear(g);

			plot.setXLabel(xAxis);
			plot.setYLabel(yAxis);

			// plot the input data
			InputStream is = null;
			try {
				is = inputFile.endsWith(".gz") 
					? new GZIPInputStream(new FileInputStream(inputFile))
					: new FileInputStream(inputFile);
				
				((JsonPlot)plot).readData(xAxis, yAxis, is);
			} 
			finally {
				is.close();
			}

			// export to a file
			BufferedImage bi = plot.exportImage(new Rectangle(width, height));
			ImageIO.write(bi, "PNG", new File("output.png"));
		} 
		catch (Exception ex) {
			System.err.println(ex.toString());
			ex.printStackTrace();
		}
    }
}
