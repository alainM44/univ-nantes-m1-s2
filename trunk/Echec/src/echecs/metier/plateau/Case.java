package echecs.metier.plateau;

/** Packages utilis�s par ce package
 *  Aucun au sein de ce projet !
 */

import java.util.*;

/**
 * Titre : Description : Copyright : Copyright (c) 2002 Soci�t� : Fabrice
 * Tranchand
 * 
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class Case
{
	// -----------------------------------------------------------------
	// attributs de la classe
	// -----------------------------------------------------------------
	/* ligne de la case (� partir de 1) */
	private int ligne;
	/* colonne de la case (� partir de 1) */
	private int colonne;

	// -----------------------------------------------------------------
	// associations de la classe
	// -----------------------------------------------------------------
	/* jeton pouvant occup� la case */
	private Jeton jeton;

	// -----------------------------------------------------------------
	// constructeur
	// -----------------------------------------------------------------
	// *****************************************************************
	// le premier param�tre d�signe la colonne de la case (� partir de 'a')
	// le second param�tre d�signe la ligne de la case (� partir de 1)
	// *****************************************************************
	/**
	 * Erreur : Inversion entre int et char.
	 */
	public Case(char colonne_p, int ligne_p)
	{
		colonne = colonne_p - 'a' + 1;
		ligne = ligne_p;
		jeton = null;
	}

	// *****************************************************************
	// le premier param�tre d�signe la colonne de la case (� partir de 1)
	// le second param�tre d�signe la ligne de la case (� partir de 1)
	// *****************************************************************
	public Case(int colonne_p, int ligne_p)
	{
		ligne = ligne_p;
		colonne = colonne_p;
		jeton = null;
	}

	// ----------------------------------------------------------------
	// les m�thodes publiques
	// ----------------------------------------------------------------
	// *****************************************************************
	// accesseurs
	// *****************************************************************
	public int getLigne()
	{
		return ligne;
	}

	public int getColonne()
	{
		return colonne;
	}

	public Jeton getJeton()
	{
		return jeton;
	}

	// *****************************************************************
	// accesseurs des attributs calcul�s !
	// *****************************************************************
	/**
	 * Renvoie estLibre au lieu de isOccupee. Trouvé gràce à test Coup
	 */
	public boolean isOccupee()
	{
		return jeton != null;
	}

	public int getCouleur()
	{
		int retour = Couleur.BLANC;
		if (((colonne + ligne) % 2) == 0)
			retour = Couleur.NOIR;
		return retour;
	}

	// *****************************************************************
	// mutateurs
	// *****************************************************************
	public void setJeton(Jeton jeton_p)
	{
		jeton = jeton_p;
	}

	// *****************************************************************
	// retire le jeton de la case
	// *****************************************************************
	public void liberer()
	{
		jeton = null;
	}

	// *****************************************************************
	// retourne true si la case en param�tre est sur la m�me ligne
	// que la case
	// *****************************************************************
	
	public boolean memeLigne(Case autre_p)
	{
		return ligne == autre_p.ligne;
	}

	// *****************************************************************
	// retourne true si la case en param�tre est sur la m�me colonne
	// que la case
	// *****************************************************************
	public boolean memeColonne(Case autre_p)
	{
		return colonne == autre_p.colonne;
	}

	// *****************************************************************
	// retourne true si la case en param�tre est sur la m�me diagonale
	// que la case
	// *****************************************************************
	public boolean memeDiagonale(Case autre_p)
	{
		return (Math.abs(ligne - autre_p.ligne) == Math.abs(colonne
				- autre_p.colonne));
	}

	// *****************************************************************
	// retourne true si la case en param�tre est juste au-dessus
	// de la case
	// *****************************************************************
	public boolean estAuDessus(Case autre_p)
	{
		return ((ligne == autre_p.ligne + 1) && (colonne == autre_p.colonne));
	}

	// *****************************************************************
	// retourne true si la case en param�tre est voisine de la case
	// *****************************************************************
	public boolean caseVoisine(Case autre_p)
	{
		return ((Math.abs(ligne - autre_p.ligne) <= 1)
				&& (Math.abs(colonne - autre_p.colonne) <= 1) && (((ligne - autre_p.ligne) == 0) || ((colonne - autre_p.colonne) == 0)));
	}

	// *****************************************************************
	// retourne la notation de la case (exemple : "a1")
	// *****************************************************************
	public String toString()
	{
		char asc = 'a';
		asc += colonne - 1;
		String val = String.valueOf(asc) + Integer.toString(ligne);
		return val;
	}
}
