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

public abstract class Piece extends Jeton
{
  // -----------------------------------------------------------------
  //     attributs
  // -----------------------------------------------------------------
  /* couleur de la pièce : cf. classe Couleur                       */
  private int couleur;
  /* indique si la pièce a déjà bougé                               */
  private boolean aDejaBouge;

  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  /* case sur laquelle est posée la pièce                           */
  private Case position;





  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier paramètre désigne la couleur de la pièce
  //     le second paramètre désigne la case initiale de la pièce
  // *****************************************************************
  public Piece(int couleur_p, Case caseInitiale_p)
  {
    couleur = couleur_p;
    aDejaBouge = false;
    bouger(caseInitiale_p);
  }




  // ----------------------------------------------------------------
  //     les méthodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  public int getCouleur() { return couleur; }
  public boolean aDejaBouge() { return aDejaBouge; }
  public Case getPosition() { return position; }

  // *****************************************************************
  //     retourne l'icône de la pièce
  //     A REDEFINIR
  // *****************************************************************
  public abstract Icon getFigure();
  // *****************************************************************
  //     retourne true si la pièce peut se déplacer sur la case en
  //     paramètre, en respectant le déplacement de la pièce
  //     A REDEFINIR
  // *****************************************************************
  public abstract boolean peutBouger(Case nouvelle_p);
  // *****************************************************************
  //     retourne l'abréviation de la pièce utilisée dans les notations
  //     A REDEFINIR
  // *****************************************************************
  public abstract String getType();
  // *****************************************************************
  //     retourne true si la case peut sauter par dessus les pièces
  // *****************************************************************
  public boolean ignoreObstacle() { return false;}
  // *****************************************************************
  //     retourne true la pièce est sur le plateau de jeu
  // *****************************************************************
  public boolean estPresente() { return position != null;}
  // *****************************************************************
  //     retire la pièce du jeu
  // *****************************************************************
  public void enlever()
  {
    position.liberer();
    position = null;
  }
  // *****************************************************************
  //     déplace la pièce sur une nouvelle case, passée en paramètre
  // *****************************************************************
  public void bouger(Case nouvelle_p)
  {
    if (position != null)
      aDejaBouge = true;
    replacer(nouvelle_p);
  }
  // *****************************************************************
  //     place le pièce sur la case passée en paramètre, sans considérer
  //     ceci comme un déplacement (aDejaBouge inchangé)
  // *****************************************************************
  public void replacer(Case nouvelle_p)
  {
    if (position != null)
      position.liberer();
    position = nouvelle_p;
    nouvelle_p.setJeton(this);
  }
}