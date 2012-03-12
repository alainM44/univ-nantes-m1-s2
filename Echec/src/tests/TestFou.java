package tests;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;
import echecs.metier.materiel.Fou;
import echecs.metier.plateau.Case;

//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestFou
{
	Fou f;

	@Before
	public void creeFou()
	{
		f = new Fou(0, new Case('c', 1));

	}

	@Test
	public void testConst()
	{
		Fou d1 = new Fou(0, new Case('c', 1));
		assertEquals('c'-'a'+1, d1.getPosition().getColonne());
		assertEquals(1, d1.getPosition().getLigne());
	}



	@Test
	public void testGetCouleur()
	{
		assertEquals(0, f.getCouleur());
		f = new Fou(1, new Case('d', 1));
		assertEquals(1, f.getCouleur());
	}

	@Test
	public void testADejaBouge()
	{
		assertEquals(false, f.aDejaBouge());
		f.bouger(new Case('e', 2));
		assertEquals(true, f.aDejaBouge());
	}

	@Test
	public void testGetPosition()
	{
		assertEquals('c' - 'a' + 1, f.getPosition().getColonne());
		assertEquals(1, f.getPosition().getLigne());

	}

	@Test
	public void testIgnoreObstacle()
	{
		assertEquals(false, f.ignoreObstacle());

	}

	@Test
	public void testEstPresent()
	{
		assertEquals(true, f.estPresente());
	}

	@Test
	public void testEnlever()
	{
		f.enlever();
		assertEquals(null, f.getPosition());
		assertEquals(false, f.estPresente());
	}

	@Test
	public void testBouger()
	{
		f.bouger(new Case('e', 3));
		assertEquals(3, f.getPosition().getLigne());
		assertEquals('e' - 'a' + 1, f.getPosition().getColonne());
		assertEquals(true, f.aDejaBouge());

	}

	@Test
	public void testReplacer()
	{
		f.replacer(new Case('f', 3));
		assertEquals('f' - 'a' + 1, f.getPosition().getColonne());
		assertEquals(3, f.getPosition().getLigne());
	}

	@Test
	public void testPeutBouger()
	{
		assertEquals(true, f.peutBouger(new Case('e', 3)));

	}

	@Test
	public void testPeutPasBouger()
	{
		assertEquals(false, f.peutBouger(new Case('a', 1)));
	}


	@Test
	public void testGetType()
	{
		String da = "F";
		assertEquals(true, f.getType().equals(da));
	}

	@Test
	public void testPresente()
	{
		assertNotNull(f.estPresente());
	}
}