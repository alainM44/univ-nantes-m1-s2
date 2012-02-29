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

public class Roi extends Piece
{
  // -----------------------------------------------------------------
  //     constantes de la classe
  // -----------------------------------------------------------------
  private static final Icon ROI_BLANC = new ImageIcon("roiB.gif");
  private static final Icon ROI_NOIR = new ImageIcon("roiN.gif");

  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier param�tre d�signe la couleur de la pi�ce
  //     le second param�tre d�signe la case initiale de la pi�ce
  // *****************************************************************
  public Roi(int couleur_p, Case caseInitiale_p)
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
      retour = ROI_BLANC;
    else
      retour = ROI_NOIR;
    return retour;
  }
  // *****************************************************************
  //     retourne true si la pi�ce peut se d�placer sur la case en
  //     param�tre, en respectant le d�placement de la pi�ce
  // *****************************************************************
  public boolean peutBouger(StubCase nouvelle_p)
  {
    boolean retour = false;
    if ( getPosition().caseVoisine(nouvelle_p))
      retour = true;
    return retour;
  }
  // *****************************************************************
  //     retourne l'abr�viation de la pi�ce utilis�e dans les notations
  // *****************************************************************
  public String getType() { return "R"; }
}