package tests;

import static org.junit.Assert.*;

import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import echecs.metier.jeu.Coup;
import echecs.metier.materiel.Dame;
import echecs.metier.plateau.Case;
import echecs.metier.plateau.Couleur;
import echecs.metier.plateau.Echiquier;
import echecs.metier.plateau.StubCase;

//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestEchiquier
{
	Echiquier e;

	@Before
	public void creeEchiquier()
	{

		e = new Echiquier(8, 8);

	}

	@Test
	public void testIntervalleLibre()
	{
		StubCase c1 = new StubCase('a', 4);
		StubCase c2 = new StubCase('b', 4);
		assertEquals(true, e.intervalleLibre(c1, c2));

	}

	@Test
	public void testIntervalleLibre2()
	{
		StubCase c1 = new StubCase('a', 3);
		StubCase c2 = new StubCase('a', 1);
		assertEquals(true, e.intervalleLibre(c1, c2));

	}

	// On detecte une erreur
	@Test
	public void testIntervallePasLibre()
	{
		StubCase c1 = new StubCase('a', 4);
		e.getCase('a', 3).setJeton(new Dame(0, e.getCase('a', 3)));
		System.out.println(e.getCase('a', 3).isOccupee());
		StubCase c3 = new StubCase('a', 1);
		assertEquals(false, e.intervalleLibre(c1, c3));

	}

	/**
	 * Comment tester testIntervallePasLibr
	 */

	@Test
	public void testGetCase()
	{
		StubCase c1 = e.getCase(1, 1);
		StubCase c2 = new StubCase(1, 1);
		assertEquals(c1.getLigne(), c2.getLigne());
		assertEquals(c1.getColonne(), c2.getColonne());

	}

	@Test
	public void testGetCasePiece()
	{
		StubCase c1 = e.getCase(1, 1);
		StubCase c2 = new StubCase(1, 1);
		assertEquals(c1.getJeton(), c2.getJeton());
	}

	@Test
	public void testGetCaseLettre()
	{
		StubCase c1 = e.getCase('a', 1);
		StubCase c2 = new StubCase(1, 1);
		assertEquals(c1.getLigne(), c2.getLigne());
		assertEquals(c1.getColonne(), c2.getColonne());

	}

}
