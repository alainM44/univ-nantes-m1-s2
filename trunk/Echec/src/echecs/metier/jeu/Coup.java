package echecs.metier.jeu;

/** Packages utilis�s par ce package
 *  Au sein de ce projet, seul metier.plateau.*
 *                             metier.materiel.*  sont autoris�s
 */
import echecs.metier.plateau.*;
import echecs.metier.materiel.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Soci�t� : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class Coup
{
  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  /* joueur auteur du coup                                          */
  private Joueur auteur;
  /* case d'arriv�e de la pi�ce d�plac�e                            */
  private Case caseArrivee;
  /* case de d�part de la pi�ce d�plac�e                            */
  private Case caseDepart;
  /* pi�ce d�plac�e                                                 */
  private Piece piece;
  /* pi�ce prise par la pi�ce d�plac�e                              */
  private Piece piecePrise;
  /* l'�chiquier                                                    */
  private Echiquier echiquier;




  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier param�tre d�signe l'auteur du coup
  //     le second param�tre d�signe la pi�ce qui est d�plac�e
  //     le troisi�me param�tre d�signe la case d'arriv�e de cette pi�ce
  //     le constructeur ne d�place pas les pi�ces
  // *****************************************************************
  public Coup(Echiquier echiquier_p,
              Joueur joueur_p,
              Piece piece_p,
              Case case_p)
  {
    echiquier = echiquier_p;
    auteur = joueur_p;
    piece = piece_p;
    piecePrise = (Piece)(case_p.getJeton());
    caseArrivee = case_p;
    caseDepart = piece.getPosition();
  }





  // ----------------------------------------------------------------
  //     les m�thodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si le coup est conforme au r�gles des �checs selon
  //     les r�gles suivantes :
  //        - le joueur d�place l'une de ses pi�ces
  //        - la case d'arriv�e est libre ou occup�e par une pi�ce adverse
  //        - la pi�ce se d�place conform�ment � son type
  // *****************************************************************
  /**
   * PeutBouger interrogait la pièce mangée au lieu de la pièce se dépalaçant.
   * Trouvé grace à TestCoup testEstValide
   */
  public boolean estValide()
  {
    boolean retour = true;
    if (! pieceCorrecte()) retour = false;
    else if (! caseArriveeCorrecte()) retour = false;
    else if (! piece.peutBouger(caseArrivee)) retour = false;
    else retour = piece.ignoreObstacle() ||
              echiquier.intervalleLibre(piece.getPosition(), caseArrivee);

    return retour;
  }
  // *****************************************************************
  //     met � jour l'�chiquier en d�pla�ant la pi�ce
  // *****************************************************************
  public void effectuer()
  {
    if (piecePrise != null) piecePrise.enlever();
    piece.bouger(caseArrivee);
  }
  // *****************************************************************
  //     annule le coup en repla�ant les pi�ces comme si le coup n'avait
  //     pas eu lieu
  // *****************************************************************
  /**
   * piecePrise peut être null et l'on obtient un null pointer exception.
   * trouvé avec TestCoup testAnnuler
   */
  public void annuler()
  {
    piece.replacer(caseDepart);
    if (piecePrise != null)
    	piecePrise.replacer(caseArrivee);
  }






  // ----------------------------------------------------------------
  //     les m�thodes priv�es
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si la case d'arriv�e est libre ou occup�e par
  //     une pi�ce adverse
  // *****************************************************************
  private boolean caseArriveeCorrecte()
  {
    return ( pieceBouge() && (! pieceDuJoueur()));
  }
  // *****************************************************************
  //     retourne true si la case d'arriv�e est diff�rente de la case
  //     de d�part
  // *****************************************************************
  private boolean pieceBouge()
  {
    return caseArrivee != caseDepart;
  }
  // *****************************************************************
  //     retourne true si la case d'arriv�e est occup�e par une pi�ce
  //     de l'auteur du coup
  // *****************************************************************
  private boolean pieceDuJoueur()
  {
    boolean retour = false;
    Piece pieceArrivee = (Piece)(caseArrivee.getJeton());
    if (pieceArrivee != null)
      retour = (pieceArrivee.getCouleur() == auteur.getCouleur());
    return retour;
  }
  // *****************************************************************
  //     retourne true si la pi�ce jou�e est sur l'�chiquier et
  //     appartient � l'auteur du coup
  // *****************************************************************
  private boolean pieceCorrecte()
  {
    return ((piece != null) &&
            (piece.getCouleur() == auteur.getCouleur()));
  }
}
