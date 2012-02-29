package tests;

import static org.junit.Assert.*;

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
public class TestCoup
{
	Coup p;

	@Before
	public void creeDame()
	{
		
		E = new Echiquier(8, 8);
		p = new Coup(echiquier_p, joueur_p, piece_p, case_p)

	}

	@Test
	public void testConst()
	{
		Coup d1 = new Coup(0, new StubCase('d', 1));
		assertEquals('d'-'a'+1, d1.getPosition().getColonne());
		assertEquals(1, d1.getPosition().getLigne());
	}

	@Test(expected = Exception.class)
	public void testConstColFail()
	{
		Coup d1 = new Coup(0, new StubCase('i', 1));

	}

	@Test(expected = Exception.class)
	public void testConstLigneFail()
	{
		Coup d1 = new Coup(0, new StubCase('d', 0));
	}

	@Test
	public void testGetCouleur()
	{
		assertEquals(0, d.getCouleur());
		d = new Coup(1, new StubCase('d', 1));
		assertEquals(1, d.getCouleur());
	}

	@Test
	public void testADejaBouge()
	{
		assertEquals(false, d.aDejaBouge());
		d.bouger(new StubCase('d', 2));
		assertEquals(true, d.aDejaBouge());
	}

	@Test
	public void testGetPosition()
	{
		assertEquals('d' - 'a' + 1, d.getPosition().getColonne());
		assertEquals(1, d.getPosition().getLigne());

	}

	@Test
	public void testIgnoreObstacle()
	{
		assertEquals(false, d.ignoreObstacle());

	}

	@Test
	public void testEstPresent()
	{
		assertEquals(true, d.estPresente());
	}

	@Test
	public void testEnlever()
	{
		d.enlever();
		assertEquals(null, d.getPosition());
		assertEquals(false, d.estPresente());
	}

	@Test
	public void testBouger()
	{
		d.bouger(new StubCase('d', 2));
		assertEquals(2, d.getPosition().getLigne());
		assertEquals('d' - 'a' + 1, d.getPosition().getColonne());
		assertEquals(true, d.aDejaBouge());

	}

	@Test
	public void testReplacer()
	{
		d.replacer(new StubCase('f', 3));
		assertEquals('f' - 'a' + 1, d.getPosition().getColonne());
		assertEquals(3, d.getPosition().getLigne());
	}

	@Test
	public void testPeutBouger()
	{
		assertEquals(true, d.peutBouger(new StubCase('d', 4)));

	}

	@Test
	public void testPeutPasBouger()
	{
		assertEquals(false, d.peutBouger(new StubCase('a', 1)));

	}

	@Test
	public void testGetType()
	{
		String da = "D";
		assertEquals(true, d.getType().equals(da));
	}

	@Test
	public void testPresente()
	{
		assertNotNull(d.estPresente());
	}
}
