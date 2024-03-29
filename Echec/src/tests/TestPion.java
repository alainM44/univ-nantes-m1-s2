package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import echecs.metier.materiel.Pion;
import echecs.metier.plateau.Case;

public class TestPion
{
	Pion p;

	@Before
	public void creeDame()
	{
		p = new Pion(0, new Case('d', 2));

	}

	@Test
	public void testConst()
	{
		Pion d1 = new Pion(0, new Case('d', 2));
		assertEquals('d'-'a'+1, d1.getPosition().getColonne());
		assertEquals(2, d1.getPosition().getLigne());
	}


	@Test
	public void testGetCouleur()
	{
		assertEquals(0, p.getCouleur());
		p = new Pion(1, new Case('d', 2));
		assertEquals(1, p.getCouleur());
	}

	@Test
	public void testADejaBouge()
	{
		assertEquals(false, p.aDejaBouge());
		p.bouger(new Case('d', 2));
		assertEquals(true, p.aDejaBouge());
	}

	@Test
	public void testGetPosition()
	{
		assertEquals('d' - 'a' + 1, p.getPosition().getColonne());
		assertEquals(2, p.getPosition().getLigne());

	}

	@Test
	public void testIgnoreObstacle()
	{
		assertEquals(false, p.ignoreObstacle());

	}

	@Test
	public void testEstPresent()
	{
		assertEquals(true, p.estPresente());
	}

	@Test
	public void testEnlever()
	{
		p.enlever();
		assertEquals(null, p.getPosition());
		assertEquals(false, p.estPresente());
	}

	@Test
	public void testBouger()
	{
		p.bouger(new Case('d', 2));
		assertEquals(2, p.getPosition().getLigne());
		assertEquals('d' - 'a' + 1, p.getPosition().getColonne());
		assertEquals(true, p.aDejaBouge());

	}

	@Test
	public void testReplacer()
	{
		p.replacer(new Case('f', 3));
		assertEquals('f' - 'a' + 1, p.getPosition().getColonne());
		assertEquals(3, p.getPosition().getLigne());
	}

	@Test
	public void testPeutBouger()
	{
		assertEquals(true, p.peutBouger(new Case('d', 3)));

	}

	@Test
	public void testPeutPasBouger()
	{
		assertEquals(false, p.peutBouger(new Case('a', 1)));

	}

	@Test
	public void testGetType()
	{
		String da = "P";
		assertEquals(true, p.getType().equals(da));
	}

	@Test
	public void testPresente()
	{
		assertNotNull(p.estPresente());
	}
}
