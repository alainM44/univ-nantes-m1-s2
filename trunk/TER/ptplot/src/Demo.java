import java.io.*;
import java.net.URL;
import java.net.*;
import java.util.zip.GZIPInputStream;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

import ptolemy.plot.EditablePlot;
import ptolemy.plot.Plot;
import ptolemy.plot.PlotApplication;
import ptolemy.plot.PlotBox;

public class Demo extends PlotApplication implements ActionListener {
	static Thread proc = null;

	JTextField filenameInput;
	JCheckBox  reverseInput;
	JButton    loadB;
	JTextField xInput;
	JTextField yInput;

    /** Construct a plot with the specified command-line arguments
     *  and instance of plot.
     *  @param plot The instance of EditablePlot to use.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public Demo(PlotBox plot, String[] args)
		throws Exception {
    	
		super(plot, args);

		setupPanels();
        setTitle("Demo");
		this.setVisible(true);
    }

	private void setupPanels() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// first row
		JPanel ctrlP = new JPanel();

		JLabel fileL = new JLabel("file:");
		ctrlP.add(fileL);
		filenameInput = new JTextField(defaultFile, 10);
		ctrlP.add(filenameInput);

		reverseInput = new JCheckBox();
		reverseInput.setSelected(true);
		ctrlP.add(reverseInput);

		JLabel xL = new JLabel("x:");
		ctrlP.add(xL);
		xInput = new JTextField("x1", 2);
		ctrlP.add(xInput);
		JLabel yL = new JLabel("y:");
		ctrlP.add(yL);
		yInput = new JTextField("x2", 2);
		ctrlP.add(yInput);

		loadB = new JButton("load");
		loadB.addActionListener(this);
		ctrlP.add(loadB);

		panel.add(ctrlP);
		getContentPane().add(panel, BorderLayout.NORTH);
	}


	public void actionPerformed(ActionEvent event) {
		synchronized(this) {if (proc == null) {
				try {
					if (event.getSource() == loadB) {
						proc = new LoadData();
						proc.start();
					}
				} catch (Exception ex) {
					System.err.println(ex.toString());
					ex.printStackTrace();
				}
			} 
		}
	}


	static String defaultFile = "input.dat.gz";

    /** 
     *  Create a new plot window and map it to the screen.
     *  The command to run would be:
     *  <pre>
     *  java -classpath $PTII ptolemy.plot.plotml.EditablePlotMLApplication
     *  </pre>
     *  @param args Arguments suitable for the
     *  {@link ptolemy.plot.EditablePlot} class.
     */
    public static void main(final String[] args) {

        try {
            Runnable doActions = new Runnable() {
                public void run() {
                    try {
						int ao = 0;
						String[] pArgs = new String[args.length-ao];
						for (int i = ao; i < args.length; ++i)
							pArgs[i-ao] = args[i];

                    	Plot plot = new JsonPlot();
                        new Demo(plot, pArgs);
                    } catch (Exception ex) {
                        System.err.println(ex.toString());
                        ex.printStackTrace();
                    }
                }
            };

            // NOTE: Using invokeAndWait() here risks causing
            // deadlock.  However, the Sun Tutorial recommends calling
            // invokeAndWait so that the work finishes before returning.
            // if we call invokeLater() then demo/PlotFourierSeries.java
            // has problems.
            SwingUtilities.invokeAndWait(doActions);
        } catch (Exception ex) {
            System.err.println(ex.toString());
            ex.printStackTrace();
        }

        // If the -test arg was set, then exit after 2 seconds.
        if (_test) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.exit(0);
        }
    }


	class LoadData extends Thread {
		public void run() {
			try {
				for (int g = 0; g < 6; ++g)
					if (g != 1)
						((Plot)plot).clear(g);

				((JsonPlot)plot).setReverse(reverseInput.isSelected());

				String filename = filenameInput.getText();
				InputStream is = filename.endsWith(".gz") 
					? new GZIPInputStream(new FileInputStream(filename))
					: new FileInputStream(filename);
				try {
					//((Plot)plot).setMarksStyle("dots", gS);
					//((Plot)plot).setConnected(true, gS);
					//((JsonPlot)plot).readData(gS, xInput.getText(), yInput.getText(), 
					//						  is);
					((JsonPlot)plot).readData(xInput.getText(), yInput.getText(), -1, is);
				} catch (SocketException ex) {
					System.err.println(ex.toString());
					ex.printStackTrace();
					System.err.println("error");
					return;
				} finally {
					is.close();
				}

				setVisible(true);
				repaint();

				plot.setXLabel(xInput.getText());
				plot.setYLabel(yInput.getText());

				System.out.println("done");

			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			} finally {
				proc = null;
			}
		}
	}
}
