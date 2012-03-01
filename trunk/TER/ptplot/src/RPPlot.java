import java.io.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.zip.GZIPInputStream;
import javax.imageio.ImageIO;

public class RPPlot {

    public static void main(final String[] args) {
		String configFile = args[0];
		int aspectId = Integer.parseInt(args[1]);
		String inputFile  = args[2];
		String outputFile = args[3];
		String xAxis = args[4];
		String yAxis = args[5];
		int width  = Integer.parseInt(args[6]);
		int height = Integer.parseInt(args[7]);

		// plot object
		JsonPlot plot = new JsonPlot();

		try {
			plot.read(new FileInputStream(configFile));

			for (int g = 0; g < 10; ++g)
				if (g != 1)
					plot.clear(g);

			plot.setReverse(false);
			
			plot.setXLabel(xAxis);
			plot.setYLabel(yAxis);

			// plot the input data
			InputStream is = null;
			try {
				is = inputFile.endsWith(".gz") 
					? new GZIPInputStream(new FileInputStream(inputFile))
					: new FileInputStream(inputFile);
				
				((JsonPlot)plot).readData(xAxis, yAxis, aspectId, is);
			} 
			finally {
				if (is != null) try { is.close(); } catch (IOException ex) {}
			}

			// export to a file
			BufferedImage bi = plot.exportImage(new Rectangle(width, height));
			//ImageIO.write(bi, "PNG", new File(outputFile));
			ImageIO.write(bi, "GIF", new File(outputFile));

			//plot.export(new FileOutputStream(outputFile));
			//plot.exportEps(new FileOutputStream(outputFile));
			//plot.exportPdf(new FileOutputStream(outputFile));
		} 
		catch (Exception ex) {
			System.err.println(ex.toString());
			ex.printStackTrace();
		}
    }
}
