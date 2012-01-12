package ArithmIntervalles;



import junit.framework.Test;
import junit.framework.TestSuite;

public class suiteTest extends TestSuite {

	public static Test suite() {

		TestSuite suite = new TestSuite("Test sur les entiers");

		// $JUnit-BEGIN$

		suite.addTest(new TestSuite(EntiersRepresentablesTest.class));

		// $JUnit-END$

		return suite;

	}
}
