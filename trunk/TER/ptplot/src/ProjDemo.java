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

public class ProjDemo extends PlotApplication implements ActionListener {
	static Thread proc = null;

	Problem[] probs;

	JTextField precInput;
	JTextField psrInput;
	JTextField aspectInput;
	JCheckBox  regularizeInput;
	JTextField addrInput;
	JTextField portInput;
	JTextField filenameInput;
	JCheckBox  reverseInput;
	JButton    startB;
	JButton    loadB;
	JTextField x1lInput;
	JTextField x1uInput;
	JTextField x2lInput;
	JTextField x2uInput;
	JTextField x3lInput;
	JTextField x3uInput;
	JTextField y1lInput;
	JTextField y1uInput;
	JCheckBox  y1Cyclic;
	JTextField y2lInput;
	JTextField y2uInput;
	JCheckBox  y2Cyclic;
	JTextField y3lInput;
	JTextField y3uInput;
	JCheckBox  y3Cyclic;
	JTextField y4lInput;
	JTextField y4uInput;
	JCheckBox  y4Cyclic;
	JTextField xInput;
	JTextField yInput;
	JComboBox probCB;
	JComboBox opCB;
	JComboBox selectCB;
	JComboBox testCB;

    /** Construct a plot with the specified command-line arguments
     *  and instance of plot.
     *  @param plot The instance of EditablePlot to use.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public ProjDemo(PlotBox plot, String[] args)
		throws Exception {
    	
		super(plot, args);

		setupProblems();
		setupPanels();
        setTitle("Projection Demo");
		this.setVisible(true);
    }

	private void setupProblems() {
		probs = new Problem[10];
		probs[0] = new Problem();
		probs[0].name = "Simple (1x1)";
		probs[0].x1l = "-10"; probs[0].x1u = "10";
		probs[0].y1l = "-10"; probs[0].y1u = "10";
		probs[0].x = "x1"; probs[0].u = "y1";

		probs[1] = new Problem();
		probs[1].name = "Sphere (1x1)";
		probs[1].x1l = "-1"; probs[1].x1u = "1";
		probs[1].y1l = "-1"; probs[1].y1u = "1";
		probs[1].x = "x1"; probs[1].u = "y1";

		probs[2] = new Problem();
		probs[2].name = "Sphere (2x2)";
		probs[2].x1l = "-1"; probs[2].x1u = "1";
		probs[2].x2l = "-1"; probs[2].x2u = "1";
		probs[2].y1l = "-1"; probs[2].y1u = "1";
		probs[2].y2l = "-1"; probs[2].y2u = "1";
		probs[2].x = "x1"; probs[2].u = "x2";

		probs[3] = new Problem();
		probs[3].name = "Sphere (2x3)";
		probs[3].x1l = "-1"; probs[3].x1u = "1";
		probs[3].x2l = "-1"; probs[3].x2u = "1";
		probs[3].y1l = "-1"; probs[3].y1u = "1";
		probs[3].y2l = "-1"; probs[3].y2u = "1";
		probs[3].y3l = "-1"; probs[3].y3u = "1";
		probs[3].x = "x1"; probs[3].u = "x2";

		probs[4] = new Problem();
		probs[4].name = "Sphere (2x4)";
		probs[4].x1l = "-1"; probs[4].x1u = "1";
		probs[4].x2l = "-1"; probs[4].x2u = "1";
		probs[4].y1l = "-1"; probs[4].y1u = "1";
		probs[4].y2l = "-1"; probs[4].y2u = "1";
		probs[4].y3l = "-1"; probs[4].y3u = "1";
		probs[4].y4l = "-1"; probs[4].y4u = "1";
		probs[4].x = "x1"; probs[4].u = "x2";

		probs[5] = new Problem();
		probs[5].name = "SailorBoat (2x2)";
		probs[5].x1l =   "0"; probs[5].x1u = "10";
		probs[5].x2l = "-pi"; probs[5].x2u = "pi";
		probs[5].y1l = "-pi/2"; probs[5].y1u = "pi/2";
		probs[5].y2l = "-pi/2"; probs[5].y2u = "pi/2";
		probs[5].x = "x1"; probs[5].u = "x2";

		probs[6] = new Problem();
		probs[6].name = "Robot (2x2)";
		probs[6].x1l = "-10"; probs[6].x1u = "10";
		probs[6].x2l = "-10"; probs[6].x2u = "10";
		probs[6].y1l =   "0"; probs[6].y1u =  "4";
		probs[6].y2l =  "-2"; probs[6].y2u =  "2";
		probs[6].x = "x1"; probs[6].u = "x2";

		probs[7] = new Problem();
		probs[7].name = "RR-RRR Robot (2x2)";
		probs[7].x1l = "-20"; probs[7].x1u = "20";
		probs[7].x2l = "-20"; probs[7].x2u = "20";
		probs[7].y1l = "-pi"; probs[7].y1u = "pi";
		probs[7].y2l = "-pi"; probs[7].y2u = "pi";
		probs[7].x = "x1"; probs[7].u = "x2";

		probs[8] = new Problem();
		probs[8].name = "3-RPR Robot (2x4)";
		probs[8].x1l = "-50"; probs[8].x1u = "50";
		probs[8].x2l = "-50"; probs[8].x2u = "50";
		probs[8].y1l =  "10"; probs[8].y1u = "32";
		probs[8].y2l =  "10"; probs[8].y2u = "32";
		probs[8].y3l =  "10"; probs[8].y3u = "32";
		probs[8].y4l =   "0"; probs[8].y4u = "0";
		probs[8].x = "x1"; probs[8].u = "x2";

		probs[9] = new Problem();
		probs[9].name = "3-RRR Robot (2x4)";
		probs[9].x1l = "-50"; probs[9].x1u = "50";
		probs[9].x2l = "-50"; probs[9].x2u = "50";
		probs[9].y1l = "-pi"; probs[9].y1u = "pi";
		probs[9].y2l = "-pi"; probs[9].y2u = "pi";
		probs[9].y3l = "-pi"; probs[9].y3u = "pi";
		probs[9].y4l =   "0"; probs[9].y4u = "0";
		probs[9].x = "x1"; probs[9].u = "x2";
	}

	private void setupPanels() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// first row
		JPanel ctrlP = new JPanel();

		JLabel probL = new JLabel("problem:");
		ctrlP.add(probL);

		probCB = new JComboBox(probs);
		probCB.addActionListener(this);
		probCB.setSelectedIndex(0);
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

		//
		JPanel strategyP = new JPanel();

		JLabel selectL = new JLabel("selecter:");
		strategyP.add(selectL);
		String[] selectStrings = { "RoundRobin", "ProbRR", "TwoTierRR" };
		selectCB = new JComboBox(selectStrings);
		selectCB.setSelectedIndex(2);
		strategyP.add(selectCB);

		JLabel testL = new JLabel("tester:");
		strategyP.add(testL);
		String[] testStrings = { "Local", "RegularJu", "ContractingU", "RJu+CU" };
		testCB = new JComboBox(testStrings);
		testCB.setSelectedIndex(1);
		strategyP.add(testCB);

		JLabel psrL = new JLabel("PSR:");
		strategyP.add(psrL);
		psrInput = new JTextField("0.5", 3);
		strategyP.add(psrInput);


		// 
		JPanel domP = new JPanel();

		JLabel x1L = new JLabel("x1:");
		domP.add(x1L);
		x1lInput = new JTextField("-10", 4);
		domP.add(x1lInput);
		x1uInput = new JTextField("10", 4);
		domP.add(x1uInput);

		JLabel x2L = new JLabel("x2:");
		domP.add(x2L);
		x2lInput = new JTextField("-10", 4);
		domP.add(x2lInput);
		x2uInput = new JTextField("10", 4);
		domP.add(x2uInput);

		JLabel x3L = new JLabel("x3:");
		domP.add(x3L);
		x3lInput = new JTextField("-10", 4);
		domP.add(x3lInput);
		x3uInput = new JTextField("10", 4);
		domP.add(x3uInput);

		JLabel xL = new JLabel("x:");
		domP.add(xL);
		xInput = new JTextField("x1", 2);
		domP.add(xInput);
		JLabel yL = new JLabel("y:");
		domP.add(yL);
		yInput = new JTextField("x2", 2);
		domP.add(yInput);


		// 
		JPanel paramP = new JPanel();

		JLabel y1L = new JLabel("y1:");
		paramP.add(y1L);
		y1lInput = new JTextField("2", 4);
		paramP.add(y1lInput);
		y1uInput = new JTextField("6", 4);
		paramP.add(y1uInput);
		y1Cyclic = new JCheckBox();
		paramP.add(y1Cyclic);

		JLabel y2L = new JLabel("y2:");
		paramP.add(y2L);
		y2lInput = new JTextField("3", 4);
		paramP.add(y2lInput);
		y2uInput = new JTextField("9", 4);
		paramP.add(y2uInput);
		y2Cyclic = new JCheckBox();
		paramP.add(y2Cyclic);

		JLabel y3L = new JLabel("y3:");
		paramP.add(y3L);
		y3lInput = new JTextField("3", 4);
		paramP.add(y3lInput);
		y3uInput = new JTextField("5", 4);
		paramP.add(y3uInput);
		y3Cyclic = new JCheckBox();
		paramP.add(y3Cyclic);

		JLabel y4L = new JLabel("y4:");
		paramP.add(y4L);
		y4lInput = new JTextField("3", 4);
		paramP.add(y4lInput);
		y4uInput = new JTextField("5", 4);
		paramP.add(y4uInput);
		y4Cyclic = new JCheckBox();
		paramP.add(y4Cyclic);


		//
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
		reverseInput.setSelected(true);
		ctrlP1.add(reverseInput);

		startB = new JButton("solve");
		startB.addActionListener(this);
		ctrlP1.add(startB);
		loadB = new JButton("load");
		loadB.addActionListener(this);
		ctrlP1.add(loadB);

		panel.add(ctrlP);
		panel.add(strategyP);
		panel.add(domP);
		panel.add(paramP);
		panel.add(ctrlP1);
		getContentPane().add(panel, BorderLayout.NORTH);
	}


	public void actionPerformed(ActionEvent event) {
		synchronized(this) {if (proc == null) {
				try {
					if (event.getSource() == startB) {
						proc = new InvokeSocket();
						proc.start();
					}
					else if (event.getSource() == loadB) {
						proc = new LoadData();
						proc.start();
					}
					else if (event.getSource() == probCB) {
						Problem p = (Problem)((JComboBox)event.getSource()).getSelectedItem();
						x1lInput.setText(p.x1l);
						x1uInput.setText(p.x1u);
						x2lInput.setText(p.x2l);
						x2uInput.setText(p.x2u);
						y1lInput.setText(p.y1l);
						y1uInput.setText(p.y1u);
						y2lInput.setText(p.y2l);
						y2uInput.setText(p.y2u);
						y3lInput.setText(p.y3l);
						y3uInput.setText(p.y3u);
						y4lInput.setText(p.y4l);
						y4uInput.setText(p.y4u);
						xInput.setText(p.x);
						yInput.setText(p.u);
					}
				} catch (Exception ex) {
					System.err.println(ex.toString());
					ex.printStackTrace();
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
                        new ProjDemo(plot, pArgs);
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

				// generate a command
				//String cmd = String.format("solve %s %s %s %s  %s %s %s  %s %s %s  %s %s %s  %s %s  %s %s  %s %s",
				String cmd = String.format("{\"command\" : \"solve\", " +
										   "\"problem\" : %s, " +
										   "\"precision\" : %s, " +
										   "\"consistency\" : %s, " +

										   "\"noinnertest\" : false, " +
										   "\"splitselect\" : %s, " +
										   "\"splittest\" : %s, " +
										   "\"PSR\" : %s, " +

										   "\"x1\" : [\"%s\", \"%s\"], " +
										   "\"x2\" : [\"%s\", \"%s\"], " +
										   "\"x3\" : [\"%s\", \"%s\"], " +
										   "\"y1\" : [\"%s\", \"%s\"], \"y1c\" : %s, " +
										   "\"y2\" : [\"%s\", \"%s\"], \"y2c\" : %s, " +
										   "\"y3\" : [\"%s\", \"%s\"], \"y3c\" : %s, " +
										   "\"y4\" : [\"%s\", \"%s\"], \"y4c\" : %s" +
										   "}",

										   probCB.getSelectedIndex(),
										   precInput.getText(),
										   opCB.getSelectedIndex(),
										   //aspectInput.getText(),
										   //regularizeInput.isSelected() ? 1 : 0,

										   selectCB.getSelectedIndex(),
										   testCB.getSelectedIndex(),
										   psrInput.getText(),

										   x1lInput.getText(),
										   x1uInput.getText(),

										   x2lInput.getText(),
										   x2uInput.getText(),

										   x3lInput.getText(),
										   x3uInput.getText(),

										   y1lInput.getText(),
										   y1uInput.getText(),
										   y1Cyclic.isSelected() ? "true" : "false",

										   y2lInput.getText(),
										   y2uInput.getText(),
										   y2Cyclic.isSelected() ? "true" : "false",

										   y3lInput.getText(),
										   y3uInput.getText(),
										   y3Cyclic.isSelected() ? "true" : "false",

										   y4lInput.getText(),
										   y4uInput.getText(),
										   y4Cyclic.isSelected() ? "true" : "false");

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
											  //Integer.parseInt(aspectInput.getText()), 
											  -1, 
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
					((JsonPlot)plot).readData(xInput.getText(), yInput.getText(), -1, is);
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

	class Problem {
		public String name = "Name";
		public String toString() {
			return name;
		}

		public String x1l = "0";
		public String x1u = "0";
		public String x2l = "0";
		public String x2u = "0";

		public String y1l = "0";
		public String y1u = "0";
		public boolean y1c = false;
		public String y2l = "0";
		public String y2u = "0";
		public boolean y2c = false;
		public String y3l = "0";
		public String y3u = "0";
		public boolean y3c = false;
		public String y4l = "0";
		public String y4u = "0";
		public boolean y4c = false;

		public String x = "x1";
		public String u = "x2";
	}
}
