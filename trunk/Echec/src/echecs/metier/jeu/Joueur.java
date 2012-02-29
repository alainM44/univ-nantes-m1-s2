package echecs.metier.jeu;

/** Packages utilis�s par ce package
 *  Au sein de ce projet, seul metier.plateau.*
 *                             metier.materiel.*  sont autoris�s
 */
import echecs.metier.plateau.Couleur;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Soci�t� : Fabrice Tranchand
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
  //     le param�tre indique la couleur du joueur
  // *****************************************************************
  public Joueur(int couleur_p)
  {
    couleur = couleur_p;
  }




  // ----------------------------------------------------------------
  //     les m�thodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  /**
   * getCouleur retourne toujours noir. Trouvé grace à TestJoueur.
   */
  public int getCouleur() { return couleur; }
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
