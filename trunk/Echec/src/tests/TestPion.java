package tests;

import static org.junit.Assert.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.junit.Before;
import org.junit.Test;
import echecs.metier.materiel.Dame;
import echecs.metier.materiel.Pion;
import echecs.metier.plateau.Case;
import echecs.metier.plateau.Couleur;
import echecs.metier.plateau.StubCase;

//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestPion
{
	Pion p;

	@Before
	public void creeDame()
	{
		p = new Pion(0, new StubCase('d', 2));

	}

	@Test
	public void testConst()
	{
		Pion d1 = new Pion(0, new StubCase('d', 2));
		assertEquals('d'-'a'+1, d1.getPosition().getColonne());
		assertEquals(2, d1.getPosition().getLigne());
	}

	@Test(expected = Exception.class)
	public void testConstColFail()
	{
		Pion p1 = new Pion(0, new StubCase('i', 1));

	}

	@Test(expected = Exception.class)
	public void testConstLigneFail()
	{
		Pion p1 = new Pion(0, new StubCase('d', 0));
	}

	@Test
	public void testGetCouleur()
	{
		assertEquals(0, p.getCouleur());
		p = new Pion(1, new StubCase('d', 2));
		assertEquals(1, p.getCouleur());
	}

	@Test
	public void testADejaBouge()
	{
		assertEquals(false, p.aDejaBouge());
		p.bouger(new StubCase('d', 2));
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
		p.bouger(new StubCase('d', 2));
		assertEquals(2, p.getPosition().getLigne());
		assertEquals('d' - 'a' + 1, p.getPosition().getColonne());
		assertEquals(true, p.aDejaBouge());

	}

	@Test
	public void testReplacer()
	{
		p.replacer(new StubCase('f', 3));
		assertEquals('f' - 'a' + 1, p.getPosition().getColonne());
		assertEquals(3, p.getPosition().getLigne());
	}

	@Test
	public void testPeutBouger()
	{
		assertEquals(true, p.peutBouger(new StubCase('d', 3)));

	}

	@Test
	public void testPeutPasBouger()
	{
		assertEquals(false, p.peutBouger(new StubCase('a', 1)));

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
