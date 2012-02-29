package echecs.metier.plateau;

/** Packages utilis�s par ce package
 *  Aucun au sein de ce projet !
 */

import java.util.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Soci�t� : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class Echiquier
{
  // -----------------------------------------------------------------
  //     attributs de la classe
  // -----------------------------------------------------------------
  /* nombre de colonnes de l'�chiquier                              */
  private int nombreColonnes;
  /* nombre de lignes de l'�chiquier                                */
  private int nombreLignes;

  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  /* les cases de l'�chiquier                                       */
  private StubCase StubCases[][];


  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  public Echiquier(int nbColonnes_p, int nbLignes_p)
  {
    nombreColonnes = nbColonnes_p;
    nombreLignes = nbLignes_p;
    StubCases = new StubCase[nombreColonnes][nombreLignes];
    for (char i=0; i<nombreColonnes; i++)
      for (int j=0; j<nombreLignes; j++)
        StubCases[i][j] = new StubCase(i+1, j+1);
  }

  // -----------------------------------------------------------------
  //     les m�thodes publiques
  // -----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si les StubCases comprises entre les 2 StubCases en
  //     param�tres sont inoccup�es (ces 2 StubCases exclues)
  //     si ces StubCases ne sont pas align�es (ni en ligne, ni en colonne
  //     ni en diagonale, la m�thode retourne true
  // *****************************************************************
  
  /**
   * Erreur détectée : elementAt(1) ou lieu de i
   */
  public boolean intervalleLibre(StubCase debut_p, StubCase fin_p)
  {
    boolean retour = true;
    Vector intervalle = getIntervalle(debut_p, fin_p);
    for (int i=0; i<intervalle.size() && retour; i++)
    {
      StubCase uneStubCase = (StubCase)(intervalle.elementAt(i));
      if (uneStubCase.isOccupee())
        retour = false;
    }

    return retour;
  }
  // *****************************************************************
  //     retourne la StubCase rep�r�e par la colonne et la ligne pass�es
  //     en param�tres.
  //     la colonne et la ligne pass�es en param�tres sont suppos�es
  //     commenc�es � 1 (et non � 0)
  // *****************************************************************
  public StubCase getCase(int colonne_p, int ligne_p)
  {
    StubCase retour = null;
    if (colonne_p > 0 && colonne_p <= nombreColonnes &&
        ligne_p > 0 && ligne_p <= nombreLignes)
      retour = StubCases[colonne_p-1][ligne_p-1];

    return retour;
  }
  // *****************************************************************
  //     retourne la StubCase rep�r�e par la colonne et la ligne pass�es
  //     en param�tres.
  //     la colonne pass�e en param�tre est cens�e commenc�e � 'a'
  //     la ligne pass�e en param�tre est cens�e commenc�e � 1 (et non � 0)
  // *****************************************************************
  public StubCase getCase(char colonne_p, int ligne_p)
  {
    return getCase(colonne_p-'a'+1, ligne_p);
  }



  // -----------------------------------------------------------------
  //     les m�thodes priv�es
  // -----------------------------------------------------------------
  // *****************************************************************
  //     retourne la liste des StubCases comprises entre les 2 StubCases en
  //     param�tres, celles-ci �tant exclues
  //     les 2 StubCases en param�tres sont cens�es �tre sur une m�me ligne
  // *****************************************************************
  private Vector getLigne(StubCase debut_p, StubCase fin_p)
  {
    Vector retour = new Vector();
    for (int i=debut_p.getLigne(); i<fin_p.getLigne(); i++)
      retour.add(getCase(debut_p.getColonne(), i));
    for (int i=fin_p.getLigne(); i<debut_p.getLigne(); i++)
      retour.add(getCase(debut_p.getColonne(), i));

    return retour;
  }
  // *****************************************************************
  //     retourne la liste des StubCases comprises entre les 2 StubCases en
  //     param�tres, celles-ci �tant exclues
  //     les 2 StubCases en param�tres sont cens�es �tre sur une m�me colonne
  // *****************************************************************
  private Vector getColonne(StubCase debut_p, StubCase fin_p)
  {
    Vector retour = new Vector();
    for (int i=debut_p.getColonne() + 1; i<fin_p.getColonne(); i++)
      retour.add(getCase(i, debut_p.getLigne()));
    for (int i=fin_p.getColonne() + 1; i<debut_p.getColonne(); i++)
      retour.add(getCase(i, debut_p.getLigne()));

    return retour;
  }
  // *****************************************************************
  //     retourne la liste des StubCases comprises entre les 2 StubCases en
  //     param�tres, celles-ci �tant exclues
  //     les 2 StubCases en param�tres sont cens�es �tre sur une m�me diagonale
  // *****************************************************************
  private Vector getDiagonale(StubCase debut_p, StubCase fin_p)
  {
    Vector retour = new Vector();
    if (debut_p.getColonne() < fin_p.getColonne())
      if (debut_p.getLigne() < fin_p.getLigne())
        for (int i = 1; i < (fin_p.getLigne() - debut_p.getLigne()); i++)
          retour.add(getCase(debut_p.getColonne()+i, debut_p.getLigne()+i));
      else
        for (int i = 1; i < (debut_p.getLigne() - fin_p.getLigne()); i++)
          retour.add(getCase(debut_p.getColonne()+i, debut_p.getLigne()-i));
    else
      if (debut_p.getLigne() < fin_p.getLigne())
        for (int i = 1; i < (fin_p.getLigne() - debut_p.getLigne()); i++)
          retour.add(getCase(debut_p.getColonne()-i, debut_p.getLigne()+i));
      else
        for (int i = 1; i < (debut_p.getLigne() - fin_p.getLigne()); i++)
          retour.add(getCase(debut_p.getColonne()-i, debut_p.getLigne()-i));

    return retour;
  }
  // *****************************************************************
  //     retourne la liste des StubCases comprises entre les 2 StubCases en
  //     param�tres, celles-ci �tant exclues
  // *****************************************************************
  
  /** 
   * Erreur détectée : inversion entre l'action du if et du 1er elsif Test echiquer
   * nom mal choisi
   */
  private Vector getIntervalle(StubCase debut_p, StubCase fin_p)
  {
    Vector retour = new Vector();
    if (debut_p.memeColonne(fin_p))
    	  retour = getLigne(debut_p, fin_p);  
    else if (debut_p.memeLigne(fin_p))
    	   retour = getColonne(debut_p, fin_p);
    else if (debut_p.memeDiagonale(fin_p))
      retour = getDiagonale(debut_p,fin_p);

    return retour;
  }
}
