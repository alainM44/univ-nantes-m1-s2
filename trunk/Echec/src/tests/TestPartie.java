package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import echecs.metier.jeu.Joueur;
import echecs.metier.jeu.Partie;
import echecs.metier.materiel.Piece;
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
		assertNotNull( p.getEchiquier());
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
StubCase CaseCavalier = 
p.jouer(piece, c);
	}

}
