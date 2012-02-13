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

public class Pion extends Piece
{
  // -----------------------------------------------------------------
  //     constantes de la classe
  // -----------------------------------------------------------------
  private static final Icon PION_BLANC = new ImageIcon("pionB.gif");
  private static final Icon PION_NOIR = new ImageIcon("pionN.gif");

  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier paramètre désigne la couleur de la pièce
  //     le second paramètre désigne la case initiale de la pièce
  // *****************************************************************
  public Pion(int couleur_p, Case caseInitiale_p)
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
      retour = PION_BLANC;
    else
      retour = PION_NOIR;
    return retour;
  }
  // *****************************************************************
  //     retourne true si la pièce peut se déplacer sur la case en
  //     paramètre, en respectant le déplacement de la pièce
  // *****************************************************************
  public boolean peutBouger(Case nouvelle_p)
  {
    boolean retour = false;
    if (getPosition() != null)
    {
      if (avanceeClassique(nouvelle_p)) retour = true;
      if (avanceeInitiale(nouvelle_p)) retour = true;
      if (priseClassique(nouvelle_p)) retour = true;
      if (priseEnPassant(nouvelle_p)) retour = true;
    }
    return retour;
  }
  // *****************************************************************
  //     retourne l'abréviation de la pièce utilisée dans les notations
  // *****************************************************************
  public String getType() { return ""; };




  // -----------------------------------------------------------------
  //     les méthodes privées
  // -----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si le déplacement correspond à une prise en passant
  // *****************************************************************
  private boolean priseEnPassant(Case nouvelle_p)
  {
    // non implémentée dans cette version
    return false;
  }
  // *****************************************************************
  //     retourne true si le déplacement correspond au premier déplacement
  //     d'un pion
  // *****************************************************************
  private boolean avanceeInitiale(Case nouvelle_p)
  {
    boolean retour = false;
    if ( getPosition().memeColonne(nouvelle_p) &&
         (! nouvelle_p.isOccupee()) &&
         ( ((getCouleur() == Couleur.BLANC) &&
            (getPosition().getLigne() == 2) &&
            (nouvelle_p.getLigne() == 4))  ||
           ((getCouleur() == Couleur.NOIR) &&
            (getPosition().getLigne() == 7) &&
            (nouvelle_p.getLigne() == 5)) ))
      retour = true;
    return retour;
  }
  // *****************************************************************
  //     retourne true si le déplacement correspond à une prise
  // *****************************************************************
  private boolean priseClassique(Case nouvelle_p)
  {
    boolean retour = false;
    if ( nouvelle_p.isOccupee() &&
         (Math.abs(getPosition().getColonne()-nouvelle_p.getColonne()) == 1) &&
         ( ((getCouleur() == Couleur.BLANC) &&
            (nouvelle_p.getLigne() == getPosition().getLigne() + 1)) ||
           ((getCouleur() == Couleur.NOIR) &&
            (nouvelle_p.getLigne() == getPosition().getLigne() - 1)) ) )
      retour = true;
    return retour;
  }
  // *****************************************************************
  //     retourne true si le déplacement correspond à une avancée du pion
  // *****************************************************************
  private boolean avanceeClassique(Case nouvelle_p)
  {
    boolean retour = false;
    if ( (! nouvelle_p.isOccupee()) &&
         (((getCouleur() == Couleur.BLANC) &&
          (nouvelle_p.estAuDessus(getPosition()))) ||
         ((getCouleur() == Couleur.NOIR) &&
	  (nouvelle_p.estAuDessus(getPosition())))) )
      retour = true;
    return retour;
  }
}
