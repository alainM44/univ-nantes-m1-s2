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

public class Cavalier extends Piece
{
  // -----------------------------------------------------------------
  //     constantes de la classe
  // -----------------------------------------------------------------
  private static final Icon CAVALIER_BLANC = new ImageIcon("cavalierB.gif");
  private static final Icon CAVALIER_NOIR = new ImageIcon("cavalierN.gif");




  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier paramètre désigne la couleur de la pièce
  //     le second paramètre désigne la case initiale de la pièce
  // *****************************************************************
  public Cavalier(int couleur_p, Case caseInitiale_p)
  {
    super(couleur_p, caseInitiale_p);
  }



  // ----------------------------------------------------------------
  //     les méthodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si la case peut sauter par dessus les pièces
  // *****************************************************************
  public boolean ignoreObstacle() { return true;};
  // *****************************************************************
  //     retourne l'icône de la pièce
  // *****************************************************************
  public Icon getFigure()
  {
    Icon retour = null;
    if (getCouleur() == Couleur.BLANC)
      retour = CAVALIER_BLANC;
    else
      retour = CAVALIER_NOIR;
    return retour;
  }
  // *****************************************************************
  //     retourne true si la pièce peut se déplacer sur la case en
  //     paramètre, en respectant le déplacement de la pièce
  // *****************************************************************
  public boolean peutBouger(Case nouvelle_p)
  {
    boolean retour = false;
    if ( (!getPosition().memeColonne(nouvelle_p)) &&
         (!getPosition().memeLigne(nouvelle_p)) &&
         ( Math.abs(getPosition().getLigne()-nouvelle_p.getLigne()) +
           Math.abs(getPosition().getColonne()-nouvelle_p.getColonne()) == 3))
      retour = true;
    return retour;
  }
  // *****************************************************************
  //     retourne l'abréviation de la pièce utilisée dans les notations
  // *****************************************************************
  public String getType() { return "C"; }
}