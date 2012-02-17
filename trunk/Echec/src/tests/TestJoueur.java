package tests;
import static org.junit.Assert.*;
import org.junit.Test;

import echecs.metier.jeu.Joueur;


public class TestJoueur
{

		@Test
		public void testConst(){
			Joueur j = new Joueur(1);
			assertEquals(1, j.getCouleur());
			assertEquals("Noirs", j.getCouleurString());
			
		}
		
		@Test(expected=Exception.class)
		public void testConstFail(){
			Joueur j = new Joueur(-1);
			
		}
		
		@Test
		public void testGetCouleur()
		{
			Joueur j = new Joueur(0);
			assertEquals(0, j.getCouleur());
			Joueur j2 = new Joueur(1);
			assertEquals(1, j2.getCouleur());
		}
		
		@Test
		public void testGetCouleurString()
		{
			Joueur j = new Joueur(0);
			assertEquals("Blancs", j.getCouleurString());
			Joueur j2 = new Joueur(1);
			assertEquals("Noirs", j2.getCouleurString());
		}
		
	
}
