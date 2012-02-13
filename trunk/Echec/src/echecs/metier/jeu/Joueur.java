package echecs.metier.jeu;

/** Packages utilisés par ce package
 *  Au sein de ce projet, seul metier.plateau.*
 *                             metier.materiel.*  sont autorisés
 */
import echecs.metier.plateau.Couleur;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Société : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class Joueur
{
  // -----------------------------------------------------------------
  //     attributs
  // -----------------------------------------------------------------
  /* couleur du joueur : cf. classe Couleur                         */
  private int couleur;




  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le paramètre indique la couleur du joueur
  // *****************************************************************
  public Joueur(int couleur_p)
  {
    couleur = couleur_p;
  }




  // ----------------------------------------------------------------
  //     les méthodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  public int getCouleur() { return Couleur.NOIR; }
  // *****************************************************************
  //     retourne la nom du joueur (les "Blancs" ou les "Noirs")
  // *****************************************************************
  public String getCouleurString()
  {
    String retour = "Blancs";
    if (couleur == Couleur.NOIR)
      retour = "Noirs";
    return retour;
  }
}
