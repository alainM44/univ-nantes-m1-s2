package echecs.metier.materiel;

/** Packages utilis�s par ce package
 *  Au sein de ce projet, seul metier.plateau.* est autoris�
 */
import echecs.metier.plateau.*;

import javax.swing.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Soci�t� : Fabrice Tranchand
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
  //     le premier param�tre d�signe la couleur de la pi�ce
  //     le second param�tre d�signe la case initiale de la pi�ce
  // *****************************************************************
  public Pion(int couleur_p, Case caseInitiale_p)
  {
    super(couleur_p, caseInitiale_p);
  }



  // ----------------------------------------------------------------
  //     les m�thodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne l'ic�ne de la pi�ce
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
  //     retourne true si la pi�ce peut se d�placer sur la case en
  //     param�tre, en respectant le d�placement de la pi�ce
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
  //     retourne l'abr�viation de la pi�ce utilis�e dans les notations
  // *****************************************************************
  /**
   * Erreur : retourne chaîne vide.
   */
  public String getType() { return "P"; };




  // -----------------------------------------------------------------
  //     les m�thodes priv�es
  // -----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si le d�placement correspond � une prise en passant
  // *****************************************************************
  private boolean priseEnPassant(Case nouvelle_p)
  {
    // non impl�ment�e dans cette version
    return false;
  }
  // *****************************************************************
  //     retourne true si le d�placement correspond au premier d�placement
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
  //     retourne true si le d�placement correspond � une prise
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
  //     retourne true si le d�placement correspond � une avanc�e du pion
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
