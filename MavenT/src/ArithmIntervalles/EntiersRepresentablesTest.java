package ArithmIntervalles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import junit.framework.*;




public class EntiersRepresentablesTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddSimple(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 5);
		ExtendedInteger e2= new ExtendedInteger((short) 2);
		ExtendedInteger e3= e1.add(e2);
		assertTrue(e3.entierR == 7);
		assertTrue(e3.representable);
	}
	
	@Test
	public void testSimple(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 5);
		ExtendedInteger e2= new ExtendedInteger((short) 2);
		ExtendedInteger e3= e1.sub(e2);
		assertTrue(e3.entierR == 3);
		assertTrue(e3.representable);
	}
	


	@Test
	public void testMultSimple(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 5);
		ExtendedInteger e2= new ExtendedInteger((short) 2);
		ExtendedInteger e3= e1.mult(e2);
		assertTrue(e3.entierR == 10);
		assertTrue(e3.representable);
	}
	
	@Test
	public void testDivSimple(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 6);
		ExtendedInteger e2= new ExtendedInteger((short) 2);
		ExtendedInteger e3= e1.div(e2);
		assertTrue("val e3 : "+e3.entierR,e3.entierR == 3);
		assertTrue(e3.representable);
	}
	
	@Test
	public void testAddFail(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 32767);
		ExtendedInteger e2= new ExtendedInteger((short) 2);
		ExtendedInteger e3= e1.add(e2);
		assertFalse(e3.representable);
	}
	
	@Test
	public void testSubFail(){
		
		ExtendedInteger e1= new ExtendedInteger((short) -32768);
		ExtendedInteger e2= new ExtendedInteger((short) 2);
		ExtendedInteger e3= e1.sub(e2);
		assertFalse(e3.representable);
	}
	
	@Test
	public void testMultFail(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 3000);
		ExtendedInteger e2= new ExtendedInteger((short) 1000);
		ExtendedInteger e3= e1.mult(e2);
		assertFalse(e3.representable);
	}
	
	@Test
	public void testDivFailTest(){
		
		ExtendedInteger e1= new ExtendedInteger((short) 6);
		ExtendedInteger e2= new ExtendedInteger((short) 0);
		ExtendedInteger e3= e1.div(e2);
		assertFalse(e3.representable);
	}
	
	@After
	public void tearDown() throws Exception {

	}

}
