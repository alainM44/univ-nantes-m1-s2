import java.io.*;
import java.net.URL;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

import ptolemy.plot.EditablePlot;
import ptolemy.plot.Plot;
import ptolemy.plot.PlotApplication;
import ptolemy.plot.PlotBox;

public class PolyhedraViewer extends PlotApplication implements ActionListener {
    /** Construct a plot with no command-line arguments.
     *  It initially displays a sample plot.
     *  @exception Exception If command line arguments have problems.
     */
    public PolyhedraViewer() throws Exception {
        this(null);
    }

    /** Construct a plot with the specified command-line arguments.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public PolyhedraViewer(String[] args) throws Exception {
        this(new EditablePlot(), args);
    }

    /** Construct a plot with the specified command-line arguments
     *  and instance of plot.
     *  @param plot The instance of EditablePlot to use.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public PolyhedraViewer(PlotBox plot, String[] args)
            throws Exception {

    	super(plot, args);

		JPanel ctrlP = new JPanel();

		JButton rlB = new JButton("reload");
		rlB.addActionListener(this);
		ctrlP.add(rlB);

		getContentPane().add(ctrlP, BorderLayout.NORTH);

		this.setVisible(true);
    }

	public void actionPerformed(ActionEvent e) {
		((Plot)plot).clear(false);
		plot.clearLegends();

		try {
			FileInputStream fin = new FileInputStream(_file);
			URL base = new URL("file", null, _directory.getAbsolutePath());
			_read(base, fin);
		} catch (Exception evt) {
			System.err.println("error occured: " + evt.getMessage());
		}

		plot.resetAxes();
		this.setVisible(true);
	} 

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
                    	Plot plot = new PolyhedraPlot();
                        new PolyhedraViewer(plot, args);
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
}
