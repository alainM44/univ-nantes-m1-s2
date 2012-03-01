import java.io.*;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;

import ptolemy.plot.EditablePlot;
import ptolemy.plot.Plot;

import net.arnx.jsonic.JSON;

public class JsonPlot extends EditablePlot {
	static final int DefaultGrp = 1;
	static final int RefreshRate = 64;

	JsonParser parser = null;
	String nameX = "x";
	String nameY = "y";
	int aspectId = -1;

	boolean isReversed = false;

	JsonParser parser() {
		if (parser == null) parser = new JsonParser(this);
		return parser;
	}
	
    public synchronized void read(InputStream inputStream) throws IOException {
        super.read(inputStream);
        parser = null;
    }

	//public void readData(int g, String nameX, String nameY, InputStream is) 
	public void readData(String nameX, String nameY, InputStream is)  
		throws IOException {
		readData(nameX, nameY, -1, is);
	}

	public void readData(String nameX, String nameY, int aspectId,
						 InputStream is) 
		throws IOException {

		this.nameX = nameX;
		this.nameY = nameY;
		this.aspectId = aspectId;
		parser().parse(is);
	}

    protected boolean _parseLine(String line) {
        // parse only if the super class does not recognize the line.
        if (super._parseLine(line)) {
            return true;
        } else {
            // We convert the line to lower case so that the command
            // names are case insensitive
			StringTokenizer st = new StringTokenizer(line);
			if (!st.hasMoreTokens())
				return false;
			String command = st.nextToken().toLowerCase();

            if (command.equals("pdataset:") ||
               	command.equals("idataset:")) {

				String dataset  = st.nextToken();
				String filename = st.nextToken();
					
				super._parseLine("dataset: " + dataset);

				//if (command.equals("idataset:"))
	            //	this.setMarksStyle("none", _currentdataset);
				//parser().parse(_currentdataset, filename);
				parser().parse(filename);

	           	return true;
	        } 
			else if (command.equals("xname:")) {
				nameX = st.nextToken();
	           	return true;
			} 
			else if (command.equals("yname:")) {
				nameY = st.nextToken();
	           	return true;
			}
			else {
				System.err.println("unknown command: " + command);
	           	return false;
			}
	    }
	}

    public synchronized void addBox(final int dataset, 
									final double xl,
									final double xu,
									final double yl,
									final double yu) {

		addPoint(dataset, xl, yl, false);
		addPoint(dataset, xu, yl, true);
		addPoint(dataset, xu, yu, true);
		addPoint(dataset, xl, yu, true);
		addPoint(dataset, xl, yl, true);

		/*
        Runnable doAddPoint = new Runnable() {
            public void run() {
				_plotImage = null;
				_checkDatasetIndex(dataset);

				setDoubleBuffered(false);

				Component parent = getParent();
				while (parent != null) {
					if (parent instanceof JComponent) {
						((JComponent) parent).setDoubleBuffered(false);
					}
					parent = parent.getParent();
				}

				Graphics graphics = getGraphics();
                if (_background == null) {
                    graphics.setXORMode(getBackground());
                } else {
                    graphics.setXORMode(_background);
                }
                graphics.setPaintMode();

				graphics.setColor(Color.red);
				long xlpos = _lrx - (long) ((xl - _xMin) * _xscale);
				long xupos = _lrx - (long) ((xu - _xMin) * _xscale);
				long ylpos = _lry - (long) ((yl - _yMin) * _yscale);
				long yupos = _lry - (long) ((yu - _yMin) * _yscale);
				graphics.fillRect((int)xlpos, (int)ylpos, 
								  (int)(xupos-xlpos), (int)(yupos-ylpos));
				graphics.fillRect((int)xlpos, (int)ylpos, 10, 10);
				graphics.fillRect(10, 10, 10, 10);

				graphics.setColor(_foreground);
                graphics.setPaintMode();
            }
        };
        deferIfNecessary(doAddPoint);
		*/
	}

	void setReverse(boolean isReversed) {
		this.isReversed = isReversed;
	}


	class JsonParser {
		private Plot plot;
	
		public JsonParser(Plot plot) {
			this.plot = plot;
		}
	
		public void parse(String filename) {
			try {
				FileInputStream is = new FileInputStream(filename);
				parse(is);
			} catch (Exception e) {
				System.err.println("an error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}
		//public void parse(int g, InputStream is) throws IOException {
		public void parse(InputStream is) throws IOException {
			StringBuilder buffer = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			int c = 0;
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);

				if (line.startsWith("ok")) {
					return;
				} else if (line.equals("")) {
					if (buffer != null) {
						Map poly = JSON.decode(buffer.toString(), Map.class);

						Object aid = poly.get("group");
						if (aid != null && 

							// TODO
							((BigDecimal)aid).intValue() != -1 &&

							((JsonPlot)plot).aspectId != -1 &&

							((JsonPlot)plot).aspectId != ((BigDecimal)aid).intValue()) {

							buffer = null;
							continue;
						}

						int grp = DefaultGrp;
						Object g = poly.get("plot");
						if (g != null)
							grp = ((BigDecimal)g).intValue();

						//System.err.println(isReversed);
						if (isReversed) {
							if (grp == 0)
								grp = 2;
								//grp = 5;
							else if (grp == 2)
								grp = 0;
							else if (grp == 5)
								grp = 0;
						}
						
						plot.setMarksStyle("none", grp);
						//plot.setMarksStyle("dots", grp);
						plot.setConnected(true, grp);

						Object objX = poly.get(nameX);
						Object objY = poly.get(nameY);
						if (objX != null && objY != null) {
							List<BigDecimal> intvX = (List<BigDecimal>)objX;
							double xl = intvX.get(0).doubleValue(); 
							double xu = intvX.get(1).doubleValue();

							List<BigDecimal> intvY = (List<BigDecimal>)objY;
							double yl = intvY.get(0).doubleValue(); 
							double yu = intvY.get(1).doubleValue();

							//if (grp == 0)
							((JsonPlot)plot).addBox(grp, xl, xu, yl, yu);
						}

						buffer = null;

						if (++c == RefreshRate) {
							plot.repaint();
							c = 0;
						}
					}
				}
				else {
					if (buffer == null)
						buffer = new StringBuilder();

					buffer.append(line);
				}
			}
		}
	}
}
