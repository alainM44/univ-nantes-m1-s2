package test;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import FicheP.Adresse;
import FicheP.Fiche;


public class FicheTest extends TestCase {



	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEgalite(){
		Fiche f1 = new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f1.setM_prenoms("Jacque", 0);
		f1.setM_prenoms("Jean", 1);
		f1.setM_prenoms("Joseph", 2);
		Fiche f2= new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f2.setM_prenoms("Jacque", 0);
		f2.setM_prenoms("Jean", 1);
		f2.setM_prenoms("Joseph", 2);
		assertTrue("Coucou", f1.compare(f2));

	}
	
	@Test
	public void testDifference(){
		Fiche f1 = new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f1.setM_prenoms("Jacque", 0);
		f1.setM_prenoms("Jean", 1);
		f1.setM_prenoms("Joseph", 2);
	Fiche f2= new Fiche("Dupont", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f2.setM_prenoms("Jacque", 0);
		f2.setM_prenoms("Jean", 1);
		f2.setM_prenoms("Joseph", 2);
		assertFalse("Coucou", f1.compare(f2));

	}
	
	@After
	public void tearDown() throws Exception {

	}

}
