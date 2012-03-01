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

public abstract class Piece extends Jeton
{
  // -----------------------------------------------------------------
  //     attributs
  // -----------------------------------------------------------------
  /* couleur de la pi�ce : cf. classe Couleur                       */
  private int couleur;
  /* indique si la pi�ce a d�j� boug�                               */
  private boolean aDejaBouge;

  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  /* case sur laquelle est pos�e la pi�ce                           */
  private Case position;





  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier param�tre d�signe la couleur de la pi�ce
  //     le second param�tre d�signe la case initiale de la pi�ce
  // *****************************************************************
  public Piece(int couleur_p, Case caseInitiale_p)
  {
    couleur = couleur_p;
    aDejaBouge = false;
    bouger(caseInitiale_p);
  }




  // ----------------------------------------------------------------
  //     les m�thodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  public int getCouleur() { return couleur; }
  public boolean aDejaBouge() { return aDejaBouge; }
  public Case getPosition() { return position; }

  // *****************************************************************
  //     retourne l'ic�ne de la pi�ce
  //     A REDEFINIR
  // *****************************************************************
  public abstract Icon getFigure();
  // *****************************************************************
  //     retourne true si la pi�ce peut se d�placer sur la case en
  //     param�tre, en respectant le d�placement de la pi�ce
  //     A REDEFINIR
  // *****************************************************************
  public abstract boolean peutBouger(Case nouvelle_p);
  // *****************************************************************
  //     retourne l'abr�viation de la pi�ce utilis�e dans les notations
  //     A REDEFINIR
  // *****************************************************************
  public abstract String getType();
  // *****************************************************************
  //     retourne true si la case peut sauter par dessus les pi�ces
  // *****************************************************************
  public boolean ignoreObstacle() { return false;}
  // *****************************************************************
  //     retourne true la pi�ce est sur le plateau de jeu
  // *****************************************************************
  public boolean estPresente() { return position != null;}
  // *****************************************************************
  //     retire la pi�ce du jeu
  // *****************************************************************
  public void enlever()
  {
    position.liberer();
    position = null;
  }
  // *****************************************************************
  //     d�place la pi�ce sur une nouvelle case, pass�e en param�tre
  // *****************************************************************
  public void bouger(Case nouvelle_p)
  {
    if (position != null)
      aDejaBouge = true;
    replacer(nouvelle_p);
  }
  // *****************************************************************
  //     place le pi�ce sur la case pass�e en param�tre, sans consid�rer
  //     ceci comme un d�placement (aDejaBouge inchang�)
  // *****************************************************************
  public void replacer(Case nouvelle_p)
  {
    if (position != null)
      position.liberer();
    position = nouvelle_p;
    nouvelle_p.setJeton(this);
  }
}