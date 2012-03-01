package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import echecs.metier.plateau.Case;


//Coup Joueur partie Dame Fou Pion Case Echiquier
public class TestCase
{
	Case c1;
	Case c2;
	Case c3;

	@Before
	public void creeCase()
	{
//		Case c1 = new Case('a', 1);
//		Case c2 = new Case(1, 1);
//		Case c3 = new Case('d', 2);

	}

	@Test
	public void testConstEqCol()
	{
		Case c1 = new Case('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(c2.getColonne(), c1.getColonne());
	}
	
	@Test
	public void testConstEqCol2()
	{
		Case c1 = new Case('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(1,  c1.getColonne());
		assertEquals(1, c2.getColonne());
	}
	


	@Test
	public void testConstEqLig()
	{
		Case c1 = new Case('a', 1);
		Case c2 = new Case(1, 1);
		assertEquals(c1.getLigne(), c2.getLigne());
	}
	@Test
	public void testConstEqLig2()
	{
		Case c1 = new Case('a', 1);
		Case c3 = new Case('d', 2);
		assertEquals(1, c1.getLigne());
		assertEquals(2, c3.getLigne());

	}


}
