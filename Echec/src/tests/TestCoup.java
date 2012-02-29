package tests;

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
		c =new Coup(e, j, d, scarr);
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
		c =new Coup(e, j, d, scarr);
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
		c =new Coup(e, j, d, scarr);
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
		c =new Coup(e, j, d, scarr);
		c.effectuer();
		assertFalse(scdep.isOccupee());
		assertTrue(scarr.isOccupee());
	}
}
