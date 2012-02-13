package echecs.metier.jeu;

/** Packages utilisés par ce package
 *  Au sein de ce projet, seul metier.plateau.*
 *                             metier.materiel.*  sont autorisés
 */
import echecs.metier.plateau.*;
import echecs.metier.materiel.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Société : Fabrice Tranchand
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
  /* case d'arrivée de la pièce déplacée                            */
  private Case caseArrivee;
  /* case de départ de la pièce déplacée                            */
  private Case caseDepart;
  /* pièce déplacée                                                 */
  private Piece piece;
  /* pièce prise par la pièce déplacée                              */
  private Piece piecePrise;
  /* l'échiquier                                                    */
  private Echiquier echiquier;




  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier paramètre désigne l'auteur du coup
  //     le second paramètre désigne la pièce qui est déplacée
  //     le troisième paramètre désigne la case d'arrivée de cette pièce
  //     le constructeur ne déplace pas les pièces
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
  //     les méthodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si le coup est conforme au règles des échecs selon
  //     les règles suivantes :
  //        - le joueur déplace l'une de ses pièces
  //        - la case d'arrivée est libre ou occupée par une pièce adverse
  //        - la pièce se déplace conformément à son type
  // *****************************************************************
  public boolean estValide()
  {
    boolean retour = true;
    if (! pieceCorrecte()) retour = false;
    else if (! caseArriveeCorrecte()) retour = false;
    else if (! piecePrise.peutBouger(caseArrivee)) retour = false;
    else retour = piece.ignoreObstacle() ||
              echiquier.intervalleLibre(piece.getPosition(), caseArrivee);

    return retour;
  }
  // *****************************************************************
  //     met à jour l'échiquier en déplaçant la pièce
  // *****************************************************************
  public void effectuer()
  {
    if (piecePrise != null) piecePrise.enlever();
    piece.bouger(caseArrivee);
  }
  // *****************************************************************
  //     annule le coup en replaçant les pièces comme si le coup n'avait
  //     pas eu lieu
  // *****************************************************************
  public void annuler()
  {
    piece.replacer(caseDepart);
    piecePrise.replacer(caseArrivee);
  }






  // ----------------------------------------------------------------
  //     les méthodes privées
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne true si la case d'arrivée est libre ou occupée par
  //     une pièce adverse
  // *****************************************************************
  private boolean caseArriveeCorrecte()
  {
    return ( pieceBouge() && (! pieceDuJoueur()));
  }
  // *****************************************************************
  //     retourne true si la case d'arrivée est différente de la case
  //     de départ
  // *****************************************************************
  private boolean pieceBouge()
  {
    return caseArrivee != caseDepart;
  }
  // *****************************************************************
  //     retourne true si la case d'arrivée est occupée par une pièce
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
  //     retourne true si la pièce jouée est sur l'échiquier et
  //     appartient à l'auteur du coup
  // *****************************************************************
  private boolean pieceCorrecte()
  {
    return ((piece != null) &&
            (piece.getCouleur() == auteur.getCouleur()));
  }
}
