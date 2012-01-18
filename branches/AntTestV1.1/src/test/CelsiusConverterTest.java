Save This Page
Home » apache-ivy-2.1.0-bin-with-deps » example » [javadoc | source]

    1   package example;
    2   
    3   import java.awt.Component;
    4   
    5   import javax.swing.JButton;
    6   import javax.swing.JFrame;
    7   import javax.swing.JLabel;
    8   import javax.swing.JMenuItem;
    9   import javax.swing.JTextField;
   10   
   11   import junit.extensions.abbot.ComponentTestFixture;
   12   import junit.extensions.abbot.TestHelper;
   13   import abbot.finder.matchers.ClassMatcher;
   14   import abbot.finder.matchers.JMenuItemMatcher;
   15   import abbot.tester.JButtonTester;
   16   import abbot.tester.JTextComponentTester;

   26   public class CelsiusConverterTest extends ComponentTestFixture {
   27   
   28       // for precision testing
   29       final static int DEFAULT_PRECISION = 0;
   30       final static int PRECISION2 = 2;
   31       final static int PRECISION3 = 3;
   32   
   33       private CelsiusConverter cc;
   34       private JTextField tempCelsius;
   35       private JButton convertButton;
   36       private JLabel outputLabel;
   37   
   38       private JTextComponentTester tt;
   39       private JButtonTester bt;
   40   
   41       /** For older versions of JUnit. */
   42       public CelsiusConverterTest(String name) {
   43           super(name);
   44       }
   45   
   46       protected void setUp() throws Exception {
   47           cc = new CelsiusConverter();
   48           JFrame frame = new JFrame();
   49           cc.enframe(frame);
   50           // Display at the current frame's desired size (avoids packing)
   51           showWindow(frame, null, false);
   52   
   53           // only one JTextField in our UI, so we can just class-match
   54           tempCelsius = (JTextField) getFinder().
   55               find(new ClassMatcher(JTextField.class));
   56           // ditto for the JButton
   57           convertButton = (JButton) getFinder().
   58               find(new ClassMatcher(JButton.class));
   59           // But there's 2 JLabel's in our UI, so we need to add more
   60           // information
   61           outputLabel = (JLabel)getFinder().
   62               find(new ClassMatcher(JLabel.class) {
   63                   public boolean matches(Component c) {
   64                       String text =
   65                           CelsiusConverter.lookupString("output.label.text");
   66                       return super.matches(c)
   67                           && ((JLabel)c).getText().equals(text);
   68                   }
   69               });
   70   
   71           tt = new JTextComponentTester();
   72           bt = new JButtonTester();
   73       }
   74   
   75       public void testNegativeNumber() throws Exception {
   76   
   77           tt.actionEnterText(tempCelsius, "-45"); //$NON-NLS-1$
   78           bt.actionClick(convertButton);
   79           assertEquals(CelsiusConverter.fahrenheitOutput(-49, DEFAULT_PRECISION),
   80                        outputLabel.getText());
   81       }
   82   
   83       public void testBadInput() throws Exception {
   84           // get default text
   85           String originalText = outputLabel.getText();
   86           // nothing should change if the input is not parseable as a double
   87           tt.actionEnterText(tempCelsius, " HELLO "); //$NON-NLS-1$
   88           bt.actionClick(convertButton);
   89           assertTrue("Output changed for bad input",
   90                      outputLabel.getText().equals(originalText)); //$NON-NLS-1$
   91       }
   92   
   93       public void testChangePrecision() throws Exception {
   94           JMenuItem item2 = (JMenuItem) getFinder().
   95               find(new JMenuItemMatcher(String.valueOf(PRECISION2))); //$NON-NLS-1$
   96   
   97           // the output should update to reflect a higher precision after making
   98           // a change, even with no new input
   99   
  100           // initial precision is 0
  101           tt.actionEnterText(tempCelsius,"25.23"); //$NON-NLS-1$
  102           bt.actionClick(convertButton);
  103   
  104           // now update precision and make sure output fields update too
  105           tt.actionSelectMenuItem(item2);
  106   
  107           assertEquals("Failed to reflect change in precision", //$NON-NLS-1$
  108                        CelsiusConverter.fahrenheitOutput(77.41, PRECISION2),
  109                        outputLabel.getText());
  110       }
  111   
  112       public void testHighPrecision() throws Exception {
  113           JMenuItem item3 = (JMenuItem) getFinder().
  114               find(new JMenuItemMatcher(String.valueOf(PRECISION3))); //$NON-NLS-1$
  115           tt.actionSelectMenuItem(item3);
  116           tt.actionEnterText(tempCelsius,"-45.543"); //$NON-NLS-1$
  117           bt.actionClick(convertButton);
  118   				
  119           assertEquals("Failed to show answer with proper precision", //$NON-NLS-1$
  120                        CelsiusConverter.fahrenheitOutput(-49.977, PRECISION3),
  121                        outputLabel.getText());
  122       }
  123   	
  124       public static void main(String[] args) {
  125           /*
  126           junit.textui.TestRunner.main(new String[] {
  127               CelsiusConverterTest.class.getName()
  128           });
  129           */
  130           TestHelper.runTests(args, CelsiusConverterTest.class);
  131       }
  132   
  133   }


Save This Page
Home » apache-ivy-2.1.0-bin-with-deps » example » [javadoc | source]
