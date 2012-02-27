package tests;

import static org.junit.Assert.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import echecs.metier.materiel.Dame;
import echecs.metier.materiel.Roi;
import echecs.metier.plateau.Case;
import echecs.metier.plateau.Couleur;
import echecs.metier.plateau.StubCase;

//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestRoi
{
	Roi r;

	@Before
	public void creeRoi()
	{
		r = new Roi(0, new StubCase('e', 1));

	}

	@Test
	public void testConst()
	{
		Roi d1 = new Roi(0, new StubCase('e', 1));
		assertEquals('d', d1.getPosition().getColonne());
		assertEquals(1, d1.getPosition().getLigne());
	}

	@Test(expected = Exception.class)
	public void testConstColFail()
	{
		Roi d1 = new Roi(0, new StubCase('i', 1));

	}

	@Test(expected = Exception.class)
	public void testConstLigneFail()
	{
		Roi d1 = new Roi(0, new StubCase('e', 0));
	}

	@Test
	public void testGetCouleur()
	{
		assertEquals(0, r.getCouleur());
		r = new Roi(1, new StubCase('e', 1));
		assertEquals(1, r.getCouleur());
	}

	@Test
	public void testADejaBouge()
	{
		assertEquals(false, r.aDejaBouge());
		r.bouger(new StubCase('d', 2));
		assertEquals(true, r.aDejaBouge());
	}

	@Test
	public void testGetPosition()
	{
		assertEquals('d' - 'a' + 1, r.getPosition().getColonne());
		assertEquals(1, r.getPosition().getLigne());

	}

	@Test
	public void testIgnoreObstacle()
	{
		assertEquals(false, r.ignoreObstacle());

	}

	@Test
	public void testEstPresent()
	{
		assertEquals(true, r.estPresente());
	}

	@Test
	public void testEnlever()
	{
		r.enlever();
		assertEquals(null, r.getPosition());
		assertEquals(false, r.estPresente());
	}

	@Test
	public void testBouger()
	{
		r.bouger(new StubCase('d', 2));
		assertEquals(2, r.getPosition().getLigne());
		assertEquals('d' - 'a' + 1, r.getPosition().getColonne());
		assertEquals(true, r.aDejaBouge());

	}

	@Test
	public void testReplacer()
	{
		r.replacer(new StubCase('f', 3));
		assertEquals('f' - 'a' + 1, r.getPosition().getColonne());
		assertEquals(3, r.getPosition().getLigne());
	}

	@Test
	public void testPeutBouger()
	{
		assertEquals(true, r.peutBouger(new StubCase('d', 4)));

	}

	@Test
	public void testPeutPasBouger()
	{
		assertEquals(false, r.peutBouger(new StubCase('a', 1)));

	}

	@Test
	public void testGetType()
	{
		String da = "D";
		assertEquals(true, r.getType().equals(da));
	}

	@Test
	public void testPresente()
	{
		assertNotNull(r.estPresente());
	}
}
