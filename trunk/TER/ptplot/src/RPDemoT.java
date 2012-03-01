import java.io.*;
import java.net.URL;
import java.net.*;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.math.BigDecimal;

import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;

import ptolemy.plot.EditablePlot;
import ptolemy.plot.Plot;
import ptolemy.plot.PlotApplication;
import ptolemy.plot.PlotBox;

import net.arnx.jsonic.JSON;

public class RPDemoT extends PlotApplication implements ActionListener {

	private final String inputFilename  = "proj_input.txt";
	private final String schemeFilename = "proj_scheme.txt";

	static Thread proc = null;

	JPanel  ctrlP;
	JPanel  btnP;
	JButton startB;
	JButton loadB;

	RPTModel tableModel;
	String servAddress;
	int servPort;
	boolean ifReverse = false;
	String xData, yData;
	String dataFilename;

    /** Construct a plot with the specified command-line arguments
     *  and instance of plot.
     *  @param plot The instance of EditablePlot to use.
     *  @param args The command-line arguments.
     *  @exception Exception If command line arguments have problems.
     */
    public RPDemoT(PlotBox plot, String[] args)
		throws Exception {
    	
		super(plot, args);

		this.readScheme();

		this.tableModel = new RPTModel();
		setupPanels();
        setTitle("RealPaver Demo");
		this.setVisible(true);
    }

	private void setupPanels() {

		JPanel ctrlP = new JPanel(new BorderLayout());

		JPanel btnP = new JPanel();
		btnP.setLayout(new BoxLayout(btnP, BoxLayout.Y_AXIS));

		startB = new JButton("solve");
		startB.addActionListener(this);
		btnP.add(startB);
		loadB = new JButton("load");
		loadB.addActionListener(this);
		btnP.add(loadB);

		ctrlP.add(btnP, BorderLayout.NORTH);

		JTable table = new JTable(tableModel);
		TableColumn col = table.getColumnModel().getColumn(1);
		//CellEditorImpl editor = new CellEditorImpl();
		//table.setCellEditor(editor);
		//col.setCellRenderer(editor);
		//col.setCellEditor(editor);
		ctrlP.add(table, BorderLayout.CENTER);

		getContentPane().add(ctrlP, BorderLayout.WEST);
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
                        new RPDemoT(plot, pArgs);
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

				((JsonPlot)plot).setReverse(ifReverse);

				// setup a socket
				Socket socket = new Socket();
				//String addr = addrInput.getText();
				//int port = Integer.parseInt(portInput.getText());
				System.out.println(id + ": connect: " + servAddress + ':' + servPort);
				socket.connect(new InetSocketAddress(servAddress, servPort));

				// generate a command
				/*String cmd = String.format("solve %s %s %s %s %s  %s %s %s  %s %s %s  %s %s %s %s",
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
				*/
				String cmd = tableModel.toString();
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
					((JsonPlot)plot).readData(xData, yData, -1, 
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

				plot.setXLabel(xData);
				plot.setYLabel(yData);

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

				((JsonPlot)plot).setReverse(ifReverse);

				InputStream is = dataFilename.endsWith(".gz") 
					? new GZIPInputStream(new FileInputStream(dataFilename))
					: new FileInputStream(dataFilename);
				try {
					//((Plot)plot).setMarksStyle("dots", gS);
					//((Plot)plot).setConnected(true, gS);
					//((JsonPlot)plot).readData(gS, xInput.getText(), yInput.getText(), 
					//						  is);
					((JsonPlot)plot).readData(xData, yData, -1,
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

				plot.setXLabel(xData);
				plot.setYLabel(yData);

				System.out.println(id + ": " + "done");

			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			} finally {
				proc = null;
			}
		}
	}


	private Map scheme;

	public void readScheme() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(schemeFilename));
			try {
				StringBuilder buffer = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}

				scheme = JSON.decode(buffer.toString(), Map.class);

			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			} finally {
				reader.close();
			}
		} catch (Exception ex) {
			System.err.println(ex.toString());
			ex.printStackTrace();
		}
	}


	class RPTModel extends AbstractTableModel {
		class Pair {
			public String tag;
			public Object value;
			public Pair(String tag, Object value) {
				this.tag = tag;
				this.value = value;
			}
		}

		List<Pair> data;
		private Map<String,Boolean> intervals;
		
		public RPTModel() {
			this.data = new ArrayList<Pair>();
			this.intervals = new HashMap<String,Boolean>();
			this.setup();
		}

		public int getRowCount() {
			return data.size();
		}
		public int getColumnCount() {
			return 2;
		}
		public Object getValueAt(int row, int column) {
			Pair p = data.get(row);
			if (column == 0)
				return p.tag;
			else // if (column == 1)
				return p.value;
		}
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Pair p = data.get(rowIndex);
			p.value = aValue;
			setParam(p.tag, aValue);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 1)
				return true;
			else
				return false;
		}

		/*public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
		*/

		void setParam(String tag, Object value) {
			if (tag.equals("server")) {
				servAddress = value.toString();
			}
			else if (tag.equals("port")) {
				servPort = Integer.parseInt(value.toString());
			}
			else if (tag.equals("ifreverse")) {
				ifReverse = ((Boolean)value).booleanValue();
			}
			else if (tag.equals("x")) {
				xData = value.toString();
			}
			else if (tag.equals("y")) {
				yData = value.toString();
			}
			else if (tag.equals("file")) {
				dataFilename = value.toString();
			}
		}

		public void setup() {
			try {
				InputStream is = new FileInputStream(inputFilename);
				readInput(is);

			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			}
		}

		public void readInput(InputStream is) throws IOException {
			try {
				StringBuilder buffer = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = reader.readLine()) != null) {
					//System.out.println(line);
					buffer.append(line);
				}

				Map map = JSON.decode(buffer.toString(), Map.class);
				List<String> tags = (List<String>)scheme.get("tags");
				for (Iterator<String> i = tags.iterator(); i.hasNext();) {
					String tag = i.next();
					Object v = map.get(tag);

					if (v == null)
						continue;

					setParam(tag, v);

					if (v instanceof List) {
						intervals.put(tag,Boolean.TRUE);
						List l = (List)v;
						v = '['+l.get(0).toString()+','+l.get(1).toString()+']';
					}
					Pair p = new Pair(tag, v);
					data.add(p);
				}
				Pair p = new Pair("hoge", new Boolean(true));
				data.add(p);
			} catch (Exception ex) {
				System.err.println(ex.toString());
				ex.printStackTrace();
			} finally {
				is.close();
			}
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("{ \"command\" : \"solve\", ");
			boolean first = true;
			for (Iterator<Pair> i = data.iterator(); i.hasNext();) {
				if (first)
					first = false;
				else
					sb.append(',');

				Pair p = i.next();
				String t = p.tag;
				boolean isString = p.value instanceof String;
				String v = p.value.toString();
				if (intervals.containsKey(t)) {
					String[] res = v.split("[\\[,\\],\\,]",-1);
					v = "[\""+res[1]+"\",\""+res[2]+"\"]";
					isString = false;
				}
				sb.append('"');
				sb.append(t);
				sb.append("\" : ");
				if (isString) sb.append('"');
				sb.append(v);
				if (isString) sb.append('"');
			}
			sb.append('}');
			return sb.toString();
		}
	}

	class CellEditorImpl extends DefaultCellEditor implements TableCellRenderer {

		public CellEditorImpl() {
			super(new JComboBox());

			JComboBox comboBox = (JComboBox)getComponent();
			comboBox.addItem("Snowboarding");
			comboBox.addItem("Rowing");
			comboBox.addItem("Chasing toddlers");
			comboBox.addItem("Speed reading");
			comboBox.addItem("Teaching high school");
			comboBox.addItem("None");
		}

		public Component getTableCellEditorComponent(JTable table,
													Object value,
													boolean isSelected,
													int row,
													int column) {
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		}

		public Component getTableCellRendererComponent(JTable table,
													   Object value,
													   boolean isSelected,
													   boolean hasFocus,
													   int row,
													   int column) {
			return getComponent();
		}
	}
}
