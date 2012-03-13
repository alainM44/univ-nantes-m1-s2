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
import echecs.metier.plateau.Case;

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
		Case c1 = new Case('a', 4);
		Case c2 = new Case('b', 4);
		assertEquals(true, e.intervalleLibre(c1, c2));

	}

	@Test
	public void testIntervalleLibre2()
	{
		Case c1 = new Case('a', 3);
		Case c2 = new Case('a', 1);
		assertEquals(true, e.intervalleLibre(c1, c2));

	}

	// On detecte une erreur
	@Test
	public void testIntervallePasLibre()
	{
		Case c1 = new Case('a', 4);
		e.getCase('a', 3).setJeton(new Dame(0, e.getCase('a', 3)));
		Case c3 = new Case('a', 1);
		assertEquals(false, e.intervalleLibre(c1, c3));

	}

	@Test
	public void testGetCase()
	{
		Case c1 = e.getCase(1, 1);
		Case c2 = new Case(1, 1);
		assertEquals(c1.getLigne(), c2.getLigne());
		assertEquals(c1.getColonne(), c2.getColonne());

	}

	@Test
	public void testGetCasePiece()
	{
		Case c1 = e.getCase(1, 1);
		Case c2 = new Case(1, 1);
		assertEquals(c1.getJeton(), c2.getJeton());
	}

	@Test
	public void testGetCaseLettre()
	{
		Case c1 = e.getCase('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(c1.getLigne(), c2.getLigne());
		assertEquals(c1.getColonne(), c2.getColonne());

	}

}