package echecs.metier.plateau;

/** Packages utilisés par ce package
 *  Aucun au sein de ce projet !
 */

import java.util.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Société : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class Echiquier
{
  // -----------------------------------------------------------------
  //     attributs de la classe
  // -----------------------------------------------------------------
  /* nombre de colonnes de l'échiquier                              */
  private int nombreColonnes;
  /* nombre de lignes de l'échiquier                                */
  private int nombreLignes;

  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  /* les cases de l'échiquier                                       */
  private Case cases[][];


  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  public Echiquier(int nbColonnes_p, int nbLignes_p)
  {
    nombreColonnes = nbColonnes_p;
    nombreLignes = nbLignes_p;
    cases = new Case[nombreColonnes][nombreLignes];
    for (char i=0; i<nombreColonnes; i++)
      for (int j=0; j<nombreLignes; j++)
        cases[i][j] = new Case(i+1, j+1);
  }

  // -----------------------------------------------------------------
  //     les méthodes publiques
  // -----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si les cases comprises entre les 2 cases en
  //     paramètres sont inoccupées (ces 2 cases exclues)
  //     si ces cases ne sont pas alignées (ni en ligne, ni en colonne
  //     ni en diagonale, la méthode retourne true
  // *****************************************************************
  public boolean intervalleLibre(Case debut_p, Case fin_p)
  {
    boolean retour = true;
    Vector intervalle = getIntervalle(debut_p, fin_p);
    for (int i=0; i<intervalle.size() && retour; i++)
    {
      Case uneCase = (Case)(intervalle.elementAt(1));
      if (uneCase.isOccupee())
        retour = false;
    }

    return retour;
  }
  // *****************************************************************
  //     retourne la case repérée par la colonne et la ligne passées
  //     en paramètres.
  //     la colonne et la ligne passées en paramètres sont supposées
  //     commencées à 1 (et non à 0)
  // *****************************************************************
  public Case getCase(int colonne_p, int ligne_p)
  {
    Case retour = null;
    if (colonne_p > 0 && colonne_p <= nombreColonnes &&
        ligne_p > 0 && ligne_p <= nombreLignes)
      retour = cases[colonne_p-1][ligne_p-1];

    return retour;
  }
  // *****************************************************************
  //     retourne la case repérée par la colonne et la ligne passées
  //     en paramètres.
  //     la colonne passée en paramètre est censée commencée à 'a'
  //     la ligne passée en paramètre est censée commencée à 1 (et non à 0)
  // *****************************************************************
  public Case getCase(char colonne_p, int ligne_p)
  {
    return getCase(colonne_p-'a'+1, ligne_p);
  }



  // -----------------------------------------------------------------
  //     les méthodes privées
  // -----------------------------------------------------------------
  // *****************************************************************
  //     retourne la liste des cases comprises entre les 2 cases en
  //     paramètres, celles-ci étant exclues
  //     les 2 cases en paramètres sont censées être sur une même ligne
  // *****************************************************************
  private Vector getLigne(Case debut_p, Case fin_p)
  {
    Vector retour = new Vector();
    for (int i=debut_p.getLigne(); i<fin_p.getLigne(); i++)
      retour.add(getCase(debut_p.getColonne(), i));
    for (int i=fin_p.getLigne(); i<debut_p.getLigne(); i++)
      retour.add(getCase(debut_p.getColonne(), i));

    return retour;
  }
  // *****************************************************************
  //     retourne la liste des cases comprises entre les 2 cases en
  //     paramètres, celles-ci étant exclues
  //     les 2 cases en paramètres sont censées être sur une même colonne
  // *****************************************************************
  private Vector getColonne(Case debut_p, Case fin_p)
  {
    Vector retour = new Vector();
    for (int i=debut_p.getColonne() + 1; i<fin_p.getColonne(); i++)
      retour.add(getCase(i, debut_p.getLigne()));
    for (int i=fin_p.getColonne() + 1; i<debut_p.getColonne(); i++)
      retour.add(getCase(i, debut_p.getLigne()));

    return retour;
  }
  // *****************************************************************
  //     retourne la liste des cases comprises entre les 2 cases en
  //     paramètres, celles-ci étant exclues
  //     les 2 cases en paramètres sont censées être sur une même diagonale
  // *****************************************************************
  private Vector getDiagonale(Case debut_p, Case fin_p)
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
  //     retourne la liste des cases comprises entre les 2 cases en
  //     paramètres, celles-ci étant exclues
  // *****************************************************************
  private Vector getIntervalle(Case debut_p, Case fin_p)
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
