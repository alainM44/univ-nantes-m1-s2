package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import echecs.metier.jeu.Joueur;
import echecs.metier.jeu.Partie;
import echecs.metier.plateau.Couleur;
import echecs.metier.plateau.Echiquier;
import echecs.metier.plateau.StubCase;

public class TestPartie
{
	Couleur couleur;

	@Test
	public void testGetLargeur()
	{
		Partie p = new Partie();
		assertEquals(8, p.getLargeur());
	}

	@Test
	public void testGetHauteur()
	{
		Partie p = new Partie();
		assertEquals(8, p.getHauteur());
	}

	@Test
	public void testGetEchiquier()
	{
		Partie p = new Partie();
		assertNotNull(p.getEchiquier());
	}

	@Test
	public void testGetTrait()
	{
		Partie p = new Partie();
		assertEquals(0, p.getTrait().getCouleur());
	}

	@Test
	public void testGetAdversaire()
	{
		Partie p = new Partie();
		Joueur j1 = p.getTrait();
		Joueur j2 = p.getAdversaire(j1);
		assertEquals(1, j2.getCouleur());
		assertEquals(0, p.getAdversaire(j2).getCouleur());
	}

	@Test
	public void testJouer()
	{
		Partie p = new Partie();
		Echiquier e = p.getEchiquier();
		StubCase CaseCavalier = e.getCase(2, 8);
		assertFalse(p.jouer(p.getPiece(CaseCavalier), e.getCase(1, 6)));
		CaseCavalier = e.getCase(2, 1);
		assertTrue(p.jouer(p.getPiece(CaseCavalier), e.getCase(1, 3)));		
		CaseCavalier = e.getCase(2, 8);
		assertTrue(p.jouer(p.getPiece(CaseCavalier), e.getCase(1, 6)));
		

	}
	
	@Test
	public void testJouerEchec()
	{
		Partie p = new Partie();
		Echiquier e = p.getEchiquier();
		p.getPiece(e.getCase(1, 1)).enlever();
		p.getPiece(e.getCase(2, 1)).enlever();
		p.getPiece(e.getCase(3, 1)).enlever();
		p.getPiece(e.getCase(6, 1)).enlever();
		p.getPiece(e.getCase(7, 1)).enlever();
		p.getPiece(e.getCase(8, 1)).enlever();
		p.getPiece(e.getCase(1, 2)).enlever();
		p.getPiece(e.getCase(2, 2)).enlever();
		p.getPiece(e.getCase(3, 2)).enlever();
		p.getPiece(e.getCase(4, 2)).enlever();
		p.getPiece(e.getCase(5, 2)).enlever();
		p.getPiece(e.getCase(6, 2)).enlever();
		p.getPiece(e.getCase(7, 2)).enlever();
		p.getPiece(e.getCase(8, 2)).enlever();
		p.getPiece(e.getCase(1, 8)).enlever();
		p.getPiece(e.getCase(2, 8)).enlever();
		p.getPiece(e.getCase(3, 8)).enlever();
		p.getPiece(e.getCase(6, 8)).enlever();
		p.getPiece(e.getCase(7, 8)).enlever();
		p.getPiece(e.getCase(8, 8)).enlever();
		p.getPiece(e.getCase(1, 7)).enlever();
		p.getPiece(e.getCase(2, 7)).enlever();
		p.getPiece(e.getCase(3, 7)).enlever();
		p.getPiece(e.getCase(4, 7)).enlever();
		p.getPiece(e.getCase(5, 7)).enlever();
		p.getPiece(e.getCase(6, 7)).enlever();
		p.getPiece(e.getCase(7, 7)).enlever();
		p.getPiece(e.getCase(8, 7)).enlever();
		assertFalse(p.jouer(p.getPiece(e.getCase(5, 1)), e.getCase(4, 2)));
		assertTrue(p.jouer(p.getPiece(e.getCase(4, 1)), e.getCase(5, 2)));
		assertFalse(p.jouer(p.getPiece(e.getCase(4, 8)), e.getCase(4, 1)));
		assertTrue(p.echec(p.getTrait()));
		assertFalse(p.pat(p.getTrait()));

		

	}
	
	@Test//TODO
	public void testJouerPat()
	{
		Partie p = new Partie();
		Echiquier e = p.getEchiquier();
		p.getPiece(e.getCase(1, 1)).enlever();
		p.getPiece(e.getCase(2, 1)).enlever();
		p.getPiece(e.getCase(3, 1)).enlever();
		p.getPiece(e.getCase(6, 1)).enlever();
		p.getPiece(e.getCase(7, 1)).enlever();
		p.getPiece(e.getCase(8, 1)).enlever();
		p.getPiece(e.getCase(1, 2)).enlever();
		p.getPiece(e.getCase(2, 2)).enlever();
		p.getPiece(e.getCase(3, 2)).enlever();
		p.getPiece(e.getCase(4, 2)).enlever();
		p.getPiece(e.getCase(5, 2)).enlever();
		p.getPiece(e.getCase(6, 2)).enlever();
		p.getPiece(e.getCase(7, 2)).enlever();
		p.getPiece(e.getCase(8, 2)).enlever();
		p.getPiece(e.getCase(1, 8)).enlever();
		p.getPiece(e.getCase(2, 8)).enlever();
		p.getPiece(e.getCase(3, 8)).enlever();
		p.getPiece(e.getCase(4, 8)).enlever();
		p.getPiece(e.getCase(6, 8)).enlever();
		p.getPiece(e.getCase(7, 8)).enlever();
		p.getPiece(e.getCase(8, 8)).enlever();
		p.getPiece(e.getCase(1, 7)).enlever();
		p.getPiece(e.getCase(2, 7)).enlever();
		p.getPiece(e.getCase(3, 7)).enlever();
		p.getPiece(e.getCase(4, 7)).enlever();
		p.getPiece(e.getCase(5, 7)).enlever();
		p.getPiece(e.getCase(6, 7)).enlever();
		p.getPiece(e.getCase(7, 7)).enlever();
		p.getPiece(e.getCase(8, 7)).enlever();
		p.jouer(p.getPiece(e.getCase(4, 8)), e.getCase(4, 1));


		

	}

}
