package echecs.metier.materiel;

/** Packages utilisés par ce package
 *  Au sein de ce projet, seul metier.plateau.* est autorisé
 */
import echecs.metier.plateau.*;

import javax.swing.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Société : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class Tour extends Piece
{
  // -----------------------------------------------------------------
  //     constantes de la classe
  // -----------------------------------------------------------------
  private static final Icon TOUR_BLANC = new ImageIcon("tourB.gif");
  private static final Icon TOUR_NOIR = new ImageIcon("tourN.gif");

  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier paramètre désigne la couleur de la pièce
  //     le second paramètre désigne la case initiale de la pièce
  // *****************************************************************
  public Tour(int couleur_p, Case caseInitiale_p)
  {
    super(couleur_p, caseInitiale_p);
  }



  // ----------------------------------------------------------------
  //     les méthodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne l'icône de la pièce
  // *****************************************************************
  public Icon getFigure()
  {
    Icon retour = null;
    if (getCouleur() == Couleur.BLANC)
      retour = TOUR_BLANC;
    else
      retour = TOUR_NOIR;
    return retour;
  }
  // *****************************************************************
  //     retourne true si la pièce peut se déplacer sur la case en
  //     paramètre, en respectant le déplacement de la pièce
  // *****************************************************************
  public boolean peutBouger(Case nouvelle_p)
  {
    boolean retour = false;
    if ( getPosition().memeColonne(nouvelle_p) ||
         getPosition().memeLigne(nouvelle_p))
      retour = true;
    return retour;
  }
  // *****************************************************************
  //     retourne l'abréviation de la pièce utilisée dans les notations
  // *****************************************************************
  public String getType() { return "T"; }
}