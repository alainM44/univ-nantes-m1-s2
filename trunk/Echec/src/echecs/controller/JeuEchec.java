package echecs.controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import echecs.metier.jeu.*;
import echecs.metier.plateau.*;
import echecs.metier.materiel.*;
import echecs.ihm.*;
/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Société : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class JeuEchec implements ActionListener
{
  // -----------------------------------------------------------------
  //     attributs
  // -----------------------------------------------------------------
  // indique si la partie est finie
  private boolean termine;
  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  // l'ihm
  private IHMEchec plateau;
  // le modèle
  private Partie jeu;
  // la dernière entrain d'être jouée
  private Piece pieceJouee;
  // la case sur laquelle se trouvait la pièce entrain d'être déplacée
  private Case caseDepart;




  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le constructeur crée la partie représentant le métier et l'ihm
  // *****************************************************************
  private JeuEchec()
  {
    jeu = new Partie();
    plateau = new IHMEchec(jeu, this);
    pieceJouee = null;
    caseDepart = null;
    termine = false;
    afficherTour("Aux blancs de commencer");
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
  public void actionPerformed(ActionEvent event_p)
  {
    // clic sur "rejouer"
    if (rejouer(event_p))
      traiterRejouer();
    // clic sur une case
    else if ((! termine) && (pieceJouee == null))
      // premier clic = une pièce de la bonne couleur
      traiterPremierClic(event_p);
    else if (! termine)
      // second clic = une pièce adverse ou une case vide ou meme case
      traiterDeuxiemeClic(event_p);
  }
  // *****************************************************************
  //     méthode principale
  // *****************************************************************
  public static void main(String[] args)
  {
    JeuEchec listener = new JeuEchec();
  }





  // ----------------------------------------------------------------
  //     les méthodes privées
  // ----------------------------------------------------------------
  // *****************************************************************
  //     traite le premier clic du joueur... qui est validé si le joueur
  //     a désigné l'une de ses pièces
  // *****************************************************************
  private void traiterPremierClic(ActionEvent event_p)
  {
    pieceJouee = validerPiece(event_p, jeu.getTrait().getCouleur());
    if (pieceJouee != null)
      marquerPieceDeplacee();
  }
  // *****************************************************************
  //     traite le deuxième clic du joueur... qui est validé si le coup
  //     est correct
  // *****************************************************************
  private void traiterDeuxiemeClic(ActionEvent event_p)
  {
    Case caseChoisie = trouverCase(event_p);
    if (caseChoisie == pieceJouee.getPosition())
      // meme case ==> annule le coup
      annulerPieceJouee();
    else
      // autre case ==> tente de valider le coup
      if (jeu.jouer(pieceJouee, caseChoisie))
      {
        // coup valide ==> remet l'ihm à jour
        plateau.rafraichir();
        annulerPieceJouee();
        afficherEtat();
      }
      else
        // coup non valide ==> attend une nouvelle case de destination
        afficher ("Coup incorrect");
  }
  // *****************************************************************
  //     retourne la pièce désignée par le joueur si celle-ci appartient
  //     à ce joueur
  // *****************************************************************
  private Piece validerPiece(ActionEvent event_p, int couleur_p)
  {
    Piece retour = trouverPiece(event_p);
    if (retour != null)
      if (retour.getCouleur() != couleur_p)
        retour = null;
    return retour;
  }
  // *****************************************************************
  //     annule le coup en "oubliant" la pièce précédemment choisie
  // *****************************************************************
  private void annulerPieceJouee()
  {
    plateau.repererCase(caseDepart, false);
    pieceJouee = null;
    caseDepart = null;
  }
  // *****************************************************************
  //     stocke la pièce choisie et se case, et la marque sur l'échiquier
  // *****************************************************************
  private void marquerPieceDeplacee()
  {
    caseDepart = pieceJouee.getPosition();
    plateau.repererCase(pieceJouee.getPosition(), true);
  }
  // *****************************************************************
  //      affiche un message d'alerte à l'intention des joueurs
  // *****************************************************************
  private void afficher(String message_p)
  {
    plateau.afficher(null, message_p);
  }
  // *****************************************************************
  //      réinitialise une nouvelle partie
  // *****************************************************************
  private void traiterRejouer()
  {
    jeu = new Partie();
    plateau.initialiser(jeu);
    pieceJouee = null;
    caseDepart = null;
    termine = false;
    afficherTour("Aux blancs de commencer");
  }
  // *****************************************************************
  //      indique le joueur qui a le trait et l'état de la partie
  // *****************************************************************
  private void afficherEtat()
  {
    String message = "";
    if (jeu.pat(jeu.getTrait()))
      termine = true;
    if (jeu.echec(jeu.getTrait()))
      if (termine)
        message = "Echec et mat ! Victoire des " +
                  jeu.getAdversaire(jeu.getTrait()).getCouleurString();
      else
        message = "Echec ! Trait aux " + jeu.getTrait().getCouleurString();
    else
      if (termine)
        message = "Pat ! Partie nulle.";
      else
        message = "Trait aux " + jeu.getTrait().getCouleurString();

    afficherTour(message);
  }
  // *****************************************************************
  //     affiche le paramètre dans la zone de texte indiquant le tour
  // *****************************************************************
  private void afficherTour(String message_p)
  {
    plateau.afficher(message_p, "");
  }
  // *****************************************************************
  //      indique s'il s'agit d'un clic sur "rejouer"
  // *****************************************************************
  private boolean rejouer(ActionEvent event_p)
  {
    return (event_p.getSource() == plateau.getToucheRejouer());
  }
  // *****************************************************************
  //      retourne la piece située sur la case choisie par le joueur
  //      (null si incorrect)
  // *****************************************************************
  private Piece trouverPiece(ActionEvent event_p)
  {
    Piece retour = null;
    Case caseCliquee = trouverCase(event_p);
    if (caseCliquee != null)
      retour = (Piece)(caseCliquee.getJeton());
    return retour;
  }
  // *****************************************************************
  //      retourne la case sélectionnée par le joueur
  // *****************************************************************
  private Case trouverCase(ActionEvent event_p)
  {
    Case retour = null;
    // retrouver la case graphique et donc la case du modèle
    JButton[][] b = plateau.getCases();
    int largeur = jeu.getLargeur();
    int hauteur = jeu.getHauteur();
    for (int i=0; i<largeur && (retour == null); i++)
      for (int j=0; j<hauteur && (retour == null); j++)
        if (event_p.getSource() == b[j][i])
          retour = jeu.getEchiquier().getCase(i+1, j+1);
    return retour;
  }
}