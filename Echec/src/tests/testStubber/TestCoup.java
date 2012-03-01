package tests.testStubber;

import static org.junit.Assert.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import echecs.metier.jeu.Coup;
import echecs.metier.jeu.Joueur;
import echecs.metier.materiel.Cavalier;
import echecs.metier.materiel.Dame;
import echecs.metier.materiel.Piece;
import echecs.metier.plateau.Case;
import echecs.metier.plateau.Couleur;
import echecs.metier.plateau.Echiquier;
import echecs.metier.plateau.StubCase;

//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestCoup
{
	Coup c;

	@Before
	public void creeCoup()
	{

	}

	@Test
	public void testEstValide()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase scarr = new StubCase(2, 4);
		Dame d = new Dame(0, scdep);
		c = new Coup(e, j, d, scarr);
		assertTrue(c.estValide());
	}

	@Test
	public void testEstValideFailDeplacement()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase scarr = new StubCase(5, 7);
		Dame d = new Dame(0, scdep);
		c = new Coup(e, j, d, scarr);
		assertFalse(c.estValide());
	}

	@Test
	public void testEstValideFailSaut()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase obstacle = new StubCase(3, 4);
		StubCase scarr = new StubCase(2, 7);
		Dame d = new Dame(0, scdep);
		Dame obsDame = new Dame(1, obstacle);
		c = new Coup(e, j, d, scarr);
		assertFalse(c.estValide());
	}
	
	@Test
	public void testEstValidePrise()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase scarr = new StubCase(5, 5);
		Dame d = new Dame(0, scdep);
		Dame obsDame = new Dame(1, scarr);
		c = new Coup(e, j, d, scarr);
		assertTrue(c.estValide());
	}
	
	@Test
	public void testEstValidePriseFail()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase scarr = new StubCase(5, 5);
		Dame d = new Dame(0, scdep);
		Dame obsDame = new Dame(0, scarr);
		c = new Coup(e, j, d, scarr);
		assertFalse(c.estValide());
	}

	@Test
	public void testEffectuer()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase scarr = new StubCase(4, 3);
		Dame d = new Dame(0, scdep);
		c = new Coup(e, j, d, scarr);
		c.effectuer();
		assertFalse(scdep.isOccupee());
		assertTrue(scarr.isOccupee());
	}

	@Test
	public void testEffectuerPrise()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(4, 4);
		StubCase scarr = new StubCase(4, 3);
		Dame d = new Dame(0, scdep);		
		Dame obsDame = new Dame(1, scarr);
		c = new Coup(e, j, d, scarr);
		c.effectuer();
		assertFalse(scdep.isOccupee());
		assertTrue(scarr.isOccupee());
		assertNull(obsDame.getPosition());
	}
	
	@Test
	public void testAnnuler()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(3, 2);
		StubCase scarr = new StubCase(4, 3);
		Dame d = new Dame(0, scdep);		
		c = new Coup(e, j, d, scarr);
		c.effectuer();
		c.annuler();
		assertTrue(scdep.isOccupee());
		assertFalse(scarr.isOccupee());
		assertEquals(3, d.getPosition().getColonne());
		assertEquals(2, d.getPosition().getLigne());
	}
	
	@Test
	public void testAnnulerArriveeOccupee()
	{
		Echiquier e = new Echiquier(8, 8);
		Joueur j = new Joueur(0);
		StubCase scdep = new StubCase(3, 2);
		StubCase scarr = new StubCase(4, 3);
		Dame d = new Dame(0, scdep);	
		Dame obsDame = new Dame(1, scarr);
		c = new Coup(e, j, d, scarr);
		c.effectuer();
		c.annuler();
		assertTrue(scdep.isOccupee());
		assertTrue(scarr.isOccupee());
		assertEquals(3, d.getPosition().getColonne());
		assertEquals(2, d.getPosition().getLigne());
		assertEquals(4, obsDame.getPosition().getColonne());
		assertEquals(3, obsDame.getPosition().getLigne());
	}
	

}
