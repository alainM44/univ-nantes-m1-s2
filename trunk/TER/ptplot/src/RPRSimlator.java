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

public class RPRSimlator extends JFrame implements ActionListener {

	JComboBox modelCB;

	JTextField x1Input;
	JTextField x2Input;
	JTextField x3Input;
	JTextField u1Input;
	JTextField u2Input;
	JTextField u3Input;

	JTextField addrInput;
	JTextField portInput;

    /** Construct a plot with the specified command-line arguments
     *  and instance of plot.
     *  @param plot The instance of EditablePlot to use.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public RPRSimulator() {
    	
		setupPanels();
        setTitle("Robot Simulator");
		this.setVisible(true);
    }


	private void setupPanels() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// first row
		JPanel modelP = new JPanel();

		JLabel modelL = new JLabel("model:");
		modelP.add(modelL);
		String[] probStrings = { "2-RPR",
								 "RR-RRR", 
								 "3-RPR" };
		modelCB = new JComboBox(modelStrings);
		modelP.add(modelCB);

		// 
		JPanel paramP = new JPanel();

		JLabel u1L = new JLabel("u1:");
		paramP.add(u1L);
		u1Input = new JTextField("2", 4);
		paramP.add(u1Input);

		JLabel u2L = new JLabel("u2:");
		paramP.add(u2L);
		u2Input = new JTextField("3", 4);
		paramP.add(u2Input);

		JLabel u3L = new JLabel("u3:");
		paramP.add(u3L);
		u3Input = new JTextField("3", 4);
		paramP.add(u3Input);


		JLabel x1L = new JLabel("x1:");
		paramP.add(x1L);
		x1Input = new JTextField("2", 4);
		paramP.add(x1Input);

		JLabel x2L = new JLabel("x2:");
		paramP.add(x2L);
		x2Input = new JTextField("3", 4);
		paramP.add(x2Input);

		JLabel x3L = new JLabel("x3:");
		paramP.add(x3L);
		x3Input = new JTextField("3", 4);
		paramP.add(x3Input);

		//
		JPanel ctrlP = new JPanel();

		JLabel addrL = new JLabel("server:");
		ctrlP.add(addrL);
		addrInput = new JTextField(defaultAddr, 10);
		ctrlP.add(addrInput);
		JLabel portL = new JLabel("port:");
		ctrlP.add(portL);
		portInput = new JTextField(defaultPort, 4);
		ctrlP.add(portInput);

		panel.add(modelP);
		panel.add(paramP);
		panel.add(ctrlP);
		//getContentPane().add(panel, BorderLayout.NORTH);
		return panel;
	}


	public void actionPerformed(ActionEvent event) {
		synchronized(this) {
			if (proc == null) {
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


    /** 
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
                        new RPRSimulator();
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
										   "\"splitselect\" : 2, " +
										   "\"splittest\" : 3, " +
										   "\"PSR\" : %s, " +

										   "\"u1\" : [\"%s\", \"%s\"], \"u1c\" : %s, " +
										   "\"u2\" : [\"%s\", \"%s\"], \"u2c\" : %s, " +
										   "\"u3\" : [\"%s\", \"%s\"], \"u3c\" : %s, " +
										   "\"x1\" : [\"%s\", \"%s\"], " +
										   "\"x2\" : [\"%s\", \"%s\"], " +
										   "\"x3\" : [\"%s\", \"%s\"]" +
										   "}",

										   probCB.getSelectedIndex(),
										   precInput.getText(),
										   opCB.getSelectedIndex(),
										   //aspectInput.getText(),
										   //regularizeInput.isSelected() ? 1 : 0,
										   psrInput.getText(),

										   u1lInput.getText(),
										   u1uInput.getText(),
										   u1Cyclic.isSelected() ? "true" : "false",

										   u2lInput.getText(),
										   u2uInput.getText(),
										   u2Cyclic.isSelected() ? "true" : "false",

										   u3lInput.getText(),
										   u3uInput.getText(),
										   u3Cyclic.isSelected() ? "true" : "false",

										   x1lInput.getText(),
										   x1uInput.getText(),

										   x2lInput.getText(),
										   x2uInput.getText(),

										   x3lInput.getText(),
										   x3uInput.getText() );
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
}
