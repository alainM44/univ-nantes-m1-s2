package test;


import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests extends TestSuite  {
	public static Test suite() {

		TestSuite suite = new TestSuite("Test sur les fiches");

		//$JUnit-BEGIN$

		suite.addTest(new TestSuite(FicheTest.class));


		//$JUnit-END$

		return  suite;

}
}
