package tests.testStubber;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import echecs.metier.plateau.Couleur;


public class TestCouleur
{
	Couleur couleur;
	
	@Before
	public void init() 
	{
		couleur = new Couleur();
	}
	@Test
	public void testAtteq(){
		assertEquals(couleur.BLANC,0);
		assertEquals(couleur.NOIR,1);
	}

	
}
