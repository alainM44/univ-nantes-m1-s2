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

	
}
