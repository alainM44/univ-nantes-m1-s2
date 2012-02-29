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

public class Partie
{
  // -----------------------------------------------------------------
  //     constantes de la classe
  // -----------------------------------------------------------------
  // nombre de pi�ces du jeu
  private static final int NB_PIECES = 32;
  // largeur du plateau (nombre de cases)
  private static final int LARGEUR_PLATEAU = 8;
  // hauteur du plateau (nombre de cases)
  private static final int HAUTEUR_PLATEAU = 8;

  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  // �chiquier sur lequel se d�roule la partie
  private Echiquier echiquier = new Echiquier(LARGEUR_PLATEAU, HAUTEUR_PLATEAU);
  // les joueurs de la partie
  /**
   * joueurs non initialisé.
   * Trouvé grace à TestPartie
   */
  private Joueur joueurs[] = new Joueur[2];
  // le joueur qui a le trait cad celui qui doit joueur
  private Joueur trait;
  // les pi�ces du jeu
  private Piece pieces[] = new Piece[NB_PIECES];
  // les rois !
  private Piece rois[] = new Piece[2];




  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     instancie les joueurs et les pi�ces
  // *****************************************************************
  public Partie()
  {
    joueurs[0] = new Joueur(Couleur.BLANC);
    joueurs[1] = new Joueur(Couleur.NOIR);
    trait = joueurs[0];
    creerPieces();
  }





  // ----------------------------------------------------------------
  //     les m�thodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  public int getLargeur() { return LARGEUR_PLATEAU; }
  public int getHauteur() { return HAUTEUR_PLATEAU; }
  public Echiquier getEchiquier() { return echiquier; }
  public Joueur getTrait() { return trait; }
  // *****************************************************************
  //     retourne l'adversaire d'un joueur (donc le joueur blanc s'il
  //     s'agit du joueur noir et vice versa)
  // *****************************************************************
  public Joueur getAdversaire(Joueur joueur_p)
  {
    Joueur retour = joueurs[0];
    if (joueur_p == joueurs[0])
      retour = joueurs[1];
    return retour;
  }
  // *****************************************************************
  //     retourne true si le coup est correct
  //     le premier param�tre d�signe la pi�ce d�plac�e
  //     le second param�tre d�signe la case d'arriv�e de cette pi�ce
  // *****************************************************************
  public boolean jouer(Piece piece_p, StubCase caseArrivee_p)
  {
    boolean retour = true;
    Coup coupEnCours = new Coup(echiquier, trait, piece_p, caseArrivee_p);
    if (! coupEnCours.estValide())
      retour = false;
    else
    {
      coupEnCours.effectuer();
      if (echec(trait))
      {
        coupEnCours.annuler();
        retour = false;
      }
      else
        validerCoup();
    }
    return retour;
  }
  // *****************************************************************
  //     retourne la pi�ce situ�e sur la case en param�tre
  // *****************************************************************
  public Piece getPiece(StubCase case_p)
  {
    return (Piece)(case_p.getJeton());
  }
  // *****************************************************************
  //     retourne true si le joueur pass� en param�tre est en position
  //     d'�chec
  // *****************************************************************
  public boolean echec(Joueur joueur_p)
  {
    boolean retour = false;
    StubCase caseRoi = getCaseRoi(joueur_p);
    Joueur adversaire = getAdversaire(joueur_p);
    for (int i=0; i<=NB_PIECES && (!retour); i++)
    {
      if (pieces[i].estPresente() &&
          pieces[i].getCouleur() == adversaire.getCouleur())
      {
        Coup coup = new Coup(echiquier, adversaire, pieces[i], caseRoi);
        if (coup.estValide())
          retour = true;
      }
    }
    return retour;
  }
  // *****************************************************************
  //     retourne true si le joueur pass� en param�tre ne peut pas jouer
  //     sans se retrouver en �chec
  // *****************************************************************
  public boolean pat(Joueur joueur_p)
  {
    boolean retour = true;
    for (int i=0; i<NB_PIECES && retour; i++)
      if (pieces[i].estPresente() &&
          pieces[i].getCouleur() == joueur_p.getCouleur())
      {
        for (int col=1; col<9 && retour; col++)
          for (int lig=1; lig<9 && retour; lig++)
            retour = simulerCoup(joueur_p, pieces[i], echiquier.getCase(col, lig));
      }
    return retour;
  }





  // ----------------------------------------------------------------
  //     les m�thodes priv�es
  // ----------------------------------------------------------------
  // *****************************************************************
  //     retourne la case sur lequel est situ� le roi du joueur en param�tre
  // *****************************************************************
  private StubCase getCaseRoi(Joueur joueur_p)
  {
    Piece piece = rois[0];
    if (joueur_p.getCouleur() == rois[1].getCouleur())
      piece = rois[1];
    return piece.getPosition();
  }
  // *****************************************************************
  //     retourne true si le coup serait valide. Le coup n'est pas effectu�
  //     cad que les pi�ces ne sont pas r�ellement d�plac�es
  //     le premier param�tre d�signe l'auteur de ce coup
  //     le deuxi�me coup d�signe la pi�ce � d�placer
  //     le troisi�me coup d�signe la case d'arriv�e de la pi�ce
  // *****************************************************************
  private boolean simulerCoup(Joueur joueur_p, Piece piece_p, StubCase case_p)
  {
    boolean retour = true;
    Coup coup = new Coup(echiquier, joueur_p, piece_p, case_p);
    if (coup.estValide())
    {
      coup.effectuer();
      if (! echec(joueur_p))
        retour = false;
      coup.annuler();
    }
    return retour;
  }
  // *****************************************************************
  //     ent�rine le coup jou�
  // *****************************************************************
  private void validerCoup()
  {
    changerTrait();
  }
  // *****************************************************************
  //     change le joueur � qui c'est le tour
  // *****************************************************************
  private void changerTrait()
  {
    if (trait == joueurs[0])
      trait = joueurs[0];
    else
      trait = joueurs[1];
  }
  // *****************************************************************
  //     instancie les pi�ces
  // *****************************************************************
  private void creerPieces()
  {
    pieces[0] = new Tour    (Couleur.BLANC, echiquier.getCase('a', 1));
    pieces[1] = new Cavalier(Couleur.BLANC, echiquier.getCase('b', 1));
    pieces[2] = new Fou     (Couleur.BLANC, echiquier.getCase('c', 1));
    pieces[3] = new Dame    (Couleur.BLANC, echiquier.getCase('d', 1));
    pieces[4] = new Roi     (Couleur.BLANC, echiquier.getCase('e', 1));
    pieces[5] = new Fou     (Couleur.BLANC, echiquier.getCase('f', 1));
    pieces[6] = new Cavalier(Couleur.BLANC, echiquier.getCase('g', 1));
    pieces[7] = new Tour    (Couleur.BLANC, echiquier.getCase('h', 1));
    rois[0] = pieces[4];
    for (char i=0; i<8; i++)
      pieces[8+i] = new Pion(Couleur.BLANC, echiquier.getCase((char)('a'+i),2));
    pieces[16] = new Tour    (Couleur.NOIR, echiquier.getCase('a', 8));
    pieces[17] = new Cavalier(Couleur.NOIR, echiquier.getCase('b', 8));
    pieces[18] = new Fou     (Couleur.NOIR, echiquier.getCase('c', 8));
    pieces[19] = new Dame    (Couleur.NOIR, echiquier.getCase('d', 8));
    pieces[20] = new Roi     (Couleur.NOIR, echiquier.getCase('e', 8));
    pieces[21] = new Fou     (Couleur.NOIR, echiquier.getCase('f', 8));
    pieces[22] = new Cavalier(Couleur.NOIR, echiquier.getCase('g', 8));
    pieces[23] = new Tour    (Couleur.NOIR, echiquier.getCase('h', 8));
    rois[1] = pieces[20];
    for (char i=0; i<8; i++)
      pieces[24+i] = new Pion(Couleur.NOIR, echiquier.getCase((char)('a'+i),7));
  }
}
