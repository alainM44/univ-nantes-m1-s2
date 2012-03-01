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

public class RPDemo extends PlotApplication implements ActionListener {
	static String rpPath = "/Users/ishii/Documents/base/realpaver";
	static String plotPath  = "/Users/ishii/workspace/ptplot";

	static Thread proc = null;

	JTextField precInput;
	JTextField aspectInput;
	JCheckBox  regularizeInput;
	JTextField addrInput;
	JTextField portInput;
	JTextField filenameInput;
	JCheckBox  reverseInput;
	JButton    startB;
	JButton    loadB;
	JTextField u1lInput;
	JTextField u1uInput;
	JCheckBox  u1Cyclic;
	JTextField u2lInput;
	JTextField u2uInput;
	JCheckBox  u2Cyclic;
	JTextField u3lInput;
	JTextField u3uInput;
	JCheckBox  u3Cyclic;
	JTextField x3vInput;
	JTextField xInput;
	JTextField yInput;
	JComboBox probCB;
	JComboBox opCB;

    /** Construct a plot with the specified command-line arguments
     *  and instance of plot.
     *  @param plot The instance of EditablePlot to use.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public RPDemo(PlotBox plot, String[] args)
		throws Exception {
    	
		super(plot, args);

		setupPanels();
        setTitle("RealPaver Demo");
		this.setVisible(true);
    }

	private void setupPanels() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// first row
		JPanel ctrlP = new JPanel();

		JLabel probL = new JLabel("problem:");
		ctrlP.add(probL);
		String[] probStrings = { "Simple",
								 "2-RPR", "RRR", 
								 "RR-RRR", "RR-RPR", 
								 "3-RPR", "3-RRR", "3-RRR_sc" };
		probCB = new JComboBox(probStrings);
		ctrlP.add(probCB);

		JLabel opL = new JLabel("consistency:");
		ctrlP.add(opL);
		String[] opStrings = { "2B+Box+Newton", "2B+Box", "2B+Newton", "Box+Newton", 
							   "2B", "Box", "Newton", "Exp" };
		opCB = new JComboBox(opStrings);
		opCB.setSelectedIndex(4);
		ctrlP.add(opCB);

		JLabel precL = new JLabel("precision:");
		ctrlP.add(precL);
		precInput = new JTextField("1.0e-2", 4);
		ctrlP.add(precInput);

		JLabel aspectL = new JLabel("aspect:");
		ctrlP.add(aspectL);
		aspectInput = new JTextField("-1", 2);
		ctrlP.add(aspectInput);

		regularizeInput = new JCheckBox();
		ctrlP.add(regularizeInput);

		// second row
		JPanel ctrlP1 = new JPanel();

		JLabel addrL = new JLabel("server:");
		ctrlP1.add(addrL);
		addrInput = new JTextField(defaultAddr, 10);
		ctrlP1.add(addrInput);
		JLabel portL = new JLabel("port:");
		ctrlP1.add(portL);
		portInput = new JTextField(defaultPort, 4);
		ctrlP1.add(portInput);

		JLabel fileL = new JLabel("file:");
		ctrlP1.add(fileL);
		filenameInput = new JTextField(defaultFile, 10);
		ctrlP1.add(filenameInput);

		reverseInput = new JCheckBox();
		ctrlP1.add(reverseInput);

		startB = new JButton("solve");
		startB.addActionListener(this);
		ctrlP1.add(startB);
		loadB = new JButton("load");
		loadB.addActionListener(this);
		ctrlP1.add(loadB);

		JPanel paramP = new JPanel();
		/*
		JLabel x1L = new JLabel("x1:");
		paramP.add(x1L);
		JTextField x1Input = new JTextField("0", 2);
		paramP.add(x1Input);
		JLabel x2L = new JLabel("x2:");
		paramP.add(x2L);
		JTextField x2Input = new JTextField("1", 2);
		paramP.add(x2Input);
		*/
		JLabel u1L = new JLabel("u1:");
		paramP.add(u1L);
		u1lInput = new JTextField("2", 2);
		paramP.add(u1lInput);
		u1uInput = new JTextField("6", 2);
		paramP.add(u1uInput);
		u1Cyclic = new JCheckBox();
		paramP.add(u1Cyclic);

		JLabel u2L = new JLabel("u2:");
		paramP.add(u2L);
		u2lInput = new JTextField("3", 2);
		paramP.add(u2lInput);
		u2uInput = new JTextField("9", 2);
		paramP.add(u2uInput);
		u2Cyclic = new JCheckBox();
		paramP.add(u2Cyclic);

		JLabel u3L = new JLabel("u3:");
		paramP.add(u3L);
		u3lInput = new JTextField("3", 2);
		paramP.add(u3lInput);
		u3uInput = new JTextField("5", 2);
		paramP.add(u3uInput);
		u3Cyclic = new JCheckBox();
		paramP.add(u3Cyclic);

		JLabel x3L = new JLabel("x3:");
		paramP.add(x3L);
		x3vInput = new JTextField("0", 2);
		paramP.add(x3vInput);

		JLabel xL = new JLabel("x-axis:");
		paramP.add(xL);
		xInput = new JTextField("x1", 2);
		paramP.add(xInput);
		JLabel yL = new JLabel("y-axis:");
		paramP.add(yL);
		yInput = new JTextField("x2", 2);
		paramP.add(yInput);

		panel.add(ctrlP);
		panel.add(ctrlP1);
		panel.add(paramP);
		getContentPane().add(panel, BorderLayout.NORTH);
	}


	public void actionPerformed(ActionEvent event) {
		synchronized(this) {if (proc == null) {
				try {
					if (event.getSource() == startB)
						proc = new InvokeSocket();
					else if (event.getSource() == loadB)
						proc = new LoadData();
					proc.start();
				} catch (Exception ex) {
					System.err.println(ex.toString());
				}
			} 
		}
	}


	Socket socket = null;
	static String defaultAddr;
	static String defaultPort;
	static String defaultFile;
	/*
	public Socket socket() throws IOException {
		if (socket == null)
			socket = new Socket();
		if  (!socket.isConnected()) {
			//socket.close();
			System.out.println("connect: " + host + ':' + port);
			socket.connect(new InetSocketAddress(host, port));
		}
		return socket;
	}
	*/


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
		defaultAddr = args[0];
		//port = Integer.parseInt(args[1]);
		defaultPort = args[1];
		defaultFile = args[2];

        try {
            Runnable doActions = new Runnable() {
                public void run() {
                    try {
						int ao = 3;
						String[] pArgs = new String[args.length-ao];
						for (int i = ao; i < args.length; ++i)
							pArgs[i-ao] = args[i];

                    	Plot plot = new JsonPlot();
                        new RPDemo(plot, pArgs);
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


	static int id = 0;
	class InvokeSocket extends Thread {
		public void run() {
			try {
				++id;

				//int gS = 0, gW = 2;
				//((Plot)plot).clear(gS);
				//((Plot)plot).clear(gW);
				for (int g = 0; g < 10; ++g)
					if (g != 1)
						((Plot)plot).clear(g);

				((JsonPlot)plot).setReverse(reverseInput.isSelected());

				// setup a socket
				Socket socket = new Socket();
				String addr = addrInput.getText();
				int port = Integer.parseInt(portInput.getText());
				System.out.println(id + ": connect: " + addr + ':' + port);
				socket.connect(new InetSocketAddress(addr, port));

				// detect the singularity
				String cmd = String.format("solve %s %s %s %s %s  %s %s %s  %s %s %s  %s %s %s %s",
										   precInput.getText(),
										   opCB.getSelectedIndex(),
										   probCB.getSelectedIndex(),
										   aspectInput.getText(),
										   regularizeInput.isSelected() ? 1 : 0,

										   u1lInput.getText(),
										   u1uInput.getText(),
										   u1Cyclic.isSelected() ? 1 : 0,

										   u2lInput.getText(),
										   u2uInput.getText(),
										   u2Cyclic.isSelected() ? 1 : 0,

										   u3lInput.getText(),
										   u3uInput.getText(),
										   u3Cyclic.isSelected() ? 1 : 0,
										   x3vInput.getText());
				System.out.println(id + ": " + cmd);

				// output to the socket.
				DataOutputStream out = 
					new DataOutputStream(socket.getOutputStream());
				out.writeBytes(cmd);

				InputStream is = socket.getInputStream();
				try {
					//((Plot)plot).setMarksStyle("dots", gS);
					//((Plot)plot).setConnected(true, gS);
					//((JsonPlot)plot).readData(gS, xInput.getText(), yInput.getText(), 
					//						  is);
					((JsonPlot)plot).readData(xInput.getText(), yInput.getText(), 
											  Integer.parseInt(aspectInput.getText()), 
											  is);
				} catch (SocketException ex) {
					System.err.println(ex.toString());
					ex.printStackTrace();
					System.err.println(id + ": " + "error");
					return;
				} finally {
					is.close();
					socket.close(); socket = null;
				}

				setVisible(true);
				// TODO
				//plot.resetAxes();
				repaint();

				plot.setXLabel(xInput.getText());
				plot.setYLabel(yInput.getText());

				System.out.println(id + ": " + "done");

			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			} finally {
				proc = null;
			}
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
					((JsonPlot)plot).readData(xInput.getText(), yInput.getText(), 
											  Integer.parseInt(aspectInput.getText()), 
											  is);
				} catch (SocketException ex) {
					System.err.println(ex.toString());
					ex.printStackTrace();
					System.err.println(id + ": " + "error");
					return;
				} finally {
					is.close();
				}

				setVisible(true);
				repaint();

				plot.setXLabel(xInput.getText());
				plot.setYLabel(yInput.getText());

				System.out.println(id + ": " + "done");

			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			} finally {
				proc = null;
			}
		}
	}
}
