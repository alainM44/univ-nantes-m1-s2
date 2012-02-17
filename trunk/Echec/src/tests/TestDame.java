package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import echecs.metier.materiel.Dame;
import echecs.metier.plateau.Case;
import echecs.metier.plateau.StubCase;
//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestDame
{
	Dame d;

	@Before
	public void creeDame()
	{
		d = new Dame(0, new StubCase('d', 1));
	}

	@Test
	public void testConst()
	{
		Dame d1 = new Dame(0, new StubCase('d', 1));
		assertEquals('d', d1.getPosition().getColonne());
		assertEquals(1, d1.getPosition().getLigne());
	}

	@Test(expected = Exception.class)
	public void testConstColFail()
	{
		Dame d1 = new Dame(0, new StubCase('i', 1));

	}

	@Test(expected = Exception.class)
	public void testConstLigneFail()
	{
		Dame d1 = new Dame(0, new StubCase('d', 0));
	}

	@Test
	public void testGetCouleur()
	{
		assertEquals(0, d.getCouleur());
		d = new Dame(1, new StubCase('d', 1));
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
		assertEquals('d'-'a'+1, d.getPosition().getColonne());
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
		assertEquals('d' - 'a' +1, d.getPosition().getColonne());
		assertEquals(true, d.aDejaBouge());

	}
	
	
	@Test
	public void testReplacer()
	{
		d.replacer(new StubCase('f', 3));
		assertEquals('f'- 'a' +1, d.getPosition().getColonne());
		assertEquals(3, d.getPosition().getLigne());
	}
	
	
	@Test
	public void testPeutBouger()
	{
		assertEquals(false,d.peutBouger(new StubCase('f', 4)));
	}
}
