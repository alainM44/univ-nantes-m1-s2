package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import echecs.metier.materiel.Dame;
import echecs.metier.plateau.Case;
import echecs.metier.plateau.Couleur;
import echecs.metier.plateau.Jeton;
import echecs.metier.plateau.StubCase;

//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestCase {

	@Test
	public void testConstEqCol() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(c2.getColonne(), c1.getColonne());
	}

	@Test
	public void testConstEqCol2() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(1, c1.getColonne());
		assertEquals(1, c2.getColonne());
	}

	@Test
	public void testConstEqLig() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(c1.getLigne(), c2.getLigne());
	}

	@Test
	public void testConstEqLig2() {
		Case c1 = new Case('a', 1);
		Case c3 = new Case('d', 2);
		assertEquals(1, c1.getLigne());
		assertEquals(2, c3.getLigne());

	}

	@Test
	public void testgetjeton1() {
		Case c1 = new Case('a', 1);
		assertNull(c1.getJeton());
	}

	@Test
	public void testsetjeton() {
		Case c1 = new Case('a', 1);
		Dame d = new Dame(0, new StubCase(1, 1));
		c1.setJeton(d);
		assertEquals(d, c1.getJeton());
	}

	@Test
	public void testLiberer() {
		Case c1 = new Case('a', 1);
		Dame d = new Dame(0, new StubCase(1, 1));
		c1.setJeton(d);
		c1.liberer();
		assertEquals(null, c1.getJeton());
	}

	@Test
	public void testCouleurNOIR() {
		Case c1 = new Case('a', 1);
		assertEquals(1, c1.getCouleur());
	}

	@Test
	public void testCouleurBLANC() {
		Case c1 = new Case('b', 1);
		assertEquals(0, c1.getCouleur());
	}

	@Test
	public void testMemeLigneEQ() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('d', 1);
		assertTrue(c1.memeLigne(c2));
	}

	@Test
	public void testMemeLigneDIFF() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('a', 3);
		assertFalse(c1.memeLigne(c2));
	}

	@Test
	public void testMemecolEQ() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('a', 2);
		assertTrue(c1.memeColonne(c2));
	}

	@Test
	public void testMemeColDIFF() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('b', 3);
		assertFalse(c1.memeColonne(c2));
	}
	
	
	@Test
	public void testMemeDiagEQ() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('c', 3);
		assertTrue(c1.memeDiagonale(c2));
	}

	@Test
	public void testMemDiaDIFF() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('a', 3);
		assertFalse(c1.memeDiagonale(c2));
	
	}
	
	@Test
	public void testCaseVoisineEQ() {
		Case c1 = new Case('a', 2);
		Case c2;
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
		c2 = new Case('a', 1);
		assertTrue(c1.caseVoisine(c2));
	}

	@Test
	public void testMemDiaDIFF() {
		Case c1 = new Case('a', 1);
		Case c2 = new Case('a', 3);
		assertFalse(c1.memeDiagonale(c2));
	
	}
}
