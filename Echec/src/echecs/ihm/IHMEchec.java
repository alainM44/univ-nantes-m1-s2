package echecs.ihm;

/** Packages utilisés par ce package
 *  Au sein de ce projet, seul metier.plateau.*
 *                             metier.jeu.*
 *                             metier.materiel.*  sont autorisés
 */
import echecs.metier.jeu.*;
import echecs.metier.materiel.*;
import echecs.metier.plateau.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Titre :
 * Description :
 * Copyright :    Copyright (c) 2002
 * Société : Fabrice Tranchand
 * @author Fabrice Tranchand
 * @version 1.0
 */

public class IHMEchec extends JFrame
{
  // -----------------------------------------------------------------
  //     constantes
  // -----------------------------------------------------------------
  // indique une case vide
  private final int VIDE = -1;
  // -----------------------------------------------------------------
  //     associations
  // -----------------------------------------------------------------
  // les cases de l'échiquier sont représentées par des "boutons"
  private JButton[][] b;
  // élément de menu permettant de rejouer
  private JMenuItem rejouer;
  // panel représentant l'échiquier
  private JPanel plateau;
  // zone de texte identiquant le joueur qui a le trait
  private JTextField tourJoueur;
  // zone de message d'alerte à l'intention des joueurs
  private JTextField message;
  // éléments graphiques de base
  private GridBagLayout trace;
  private GridBagConstraints traceContrainte;
  private Container conteneur;
  // classe principale du "métier"
  private Partie jeu;





  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier paramètre désigne le métier
  //     le second paramètre désigne le contrôleur
  //     le constructeur crée l'ihm : plateau, menu, zone de texte
  // *****************************************************************
  public IHMEchec(Partie jeu_p, ActionListener ech_p)
  {
    super ("Jeu d'échecs");
    jeu = jeu_p;

    conteneur = getContentPane();
    trace = new GridBagLayout();
    traceContrainte = new GridBagConstraints();
    conteneur.setLayout(trace);

    initPlateau(ech_p);
    initMessage();
    initMenu(ech_p);

    setSize(350, 500);
    show();
    rafraichir();
    traiterFin();
  }





  // ----------------------------------------------------------------
  //     les méthodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  public JMenuItem getToucheRejouer() { return rejouer; }
  public JButton[][] getCases() { return b; }
  // *****************************************************************
  //     affiche le premier paramètre dans la zone indiquant le joueur
  //     qui a le trait, et le second paramètre dans la zone d'alerte
  // *****************************************************************
  public void afficher(String tour_p, String message_p)
  {
    if (tour_p != null)
      tourJoueur.setText(tour_p);
    if (message_p != null)
      message.setText(message_p);
  }
  // *****************************************************************
  //     affiche le plateau et vide les zones de messages
  // *****************************************************************
  public void initialiser(Partie jeu_p)
  {
    jeu = jeu_p;
    ImageIcon vide = new ImageIcon();
    for (int i=0; i<jeu.getLargeur(); i++)
      for (int j=0; j<jeu.getHauteur(); j++)
        b[i][j].setIcon(null);
    rafraichir();
    afficher(null, null);
  }
  // *****************************************************************
  //     si le deuxième paramètre vaut true, affiche la case passée en
  //     premier paramètre sur fond rouge. Sinon, affiche le fond adéquat
  //     (blanc ou noir) conformément à une plateau d'échec
  // *****************************************************************
  public void repererCase(Case case_p, boolean marquer_p)
  {
    if (marquer_p)
      b[case_p.getLigne()-1][case_p.getColonne()-1].setBackground(Color.red);
    else
      colorerCase(case_p.getLigne()-1,case_p.getColonne()-1);
  }
  // *****************************************************************
  //     affiche les pièces sur l'échiquier
  // *****************************************************************
  public void rafraichir()
  {
    for (int i=0; i<jeu.getLargeur(); i++)
      for (int j=0; j<jeu.getHauteur(); j++)
      {
        Piece piece = trouverPiece(jeu.getEchiquier().getCase(i+1, j+1));
        if (piece != null)
          b[j][i].setIcon(piece.getFigure());
        else
          b[j][i].setIcon(null);
      }
  }






  // ----------------------------------------------------------------
  //     les méthodes privées
  // ----------------------------------------------------------------
  // *****************************************************************
  //     construit le plateau d'échecs graphique
  // *****************************************************************
  private void initPlateau(ActionListener ech_p)
  {
    plateau = new JPanel();
    int largeur = jeu.getLargeur();
    int hauteur = jeu.getHauteur();
    plateau.setLayout(new GridLayout(largeur, hauteur));
    b = new JButton[largeur][hauteur];
    for (int i=0; i<largeur; i++)
      for (int j=0; j<hauteur; j++)
        initCase(largeur - 1 - i, j, ech_p);
    traceContrainte.fill = GridBagConstraints.NONE;
    traceContrainte.gridheight = 200;
    traceContrainte.gridwidth = 200;

    traceContrainte.weightx = 1;
    traceContrainte.weighty = 1;
    plateau.setSize(200, 200);
    ajouterComposant(plateau, 0, 0, 1, 1);
  }
  // *****************************************************************
  //     construit les menus
  // *****************************************************************
  private void initMenu(ActionListener ech_p)
  {
    JMenuBar barre = new JMenuBar();
    setJMenuBar(barre);

    JMenu menuJeu = new JMenu("Jeu");
    rejouer = new JMenuItem("Rejouer !");
    rejouer.setMnemonic('N');
    rejouer.addActionListener(ech_p);
    menuJeu.add(rejouer);

    barre.add(menuJeu);
  }
  // *****************************************************************
  //     construit les zones de texte
  // *****************************************************************
  private void initMessage()
  {
    tourJoueur = new JTextField("",20);
    tourJoueur.setEditable(false);
    message = new JTextField("",20);
    message.setEditable(false);
    message.setForeground(Color.red);
    ajouterComposant(tourJoueur, 1, 0, 2, 1);
    ajouterComposant(message, 2, 0, 2, 1);
  }
  // *****************************************************************
  //     permet d'ajouter des éléments graphiques dans l'ihm
  // *****************************************************************
  private void ajouterComposant(Component c, int ligne, int colonne, int larg, int haut)
  {
    traceContrainte.fill = GridBagConstraints.BOTH;
    traceContrainte.gridx = colonne;
    traceContrainte.gridy = ligne;
    traceContrainte.gridheight = haut;
    traceContrainte.gridwidth = larg;
    trace.setConstraints(c, traceContrainte);
    conteneur.add(c);
  }
  // *****************************************************************
  //     crée un bouton représentant la case dont les coordonnées sont
  //     passées en paramètre (elles commencent à 0)
  // *****************************************************************
  private void initCase(int col_p, int lig_p, ActionListener oth_p)
  {
    b[col_p][lig_p] = new JButton();
    b[col_p][lig_p].setFocusPainted(false);
    b[col_p][lig_p].addActionListener(oth_p);
    colorerCase(col_p, lig_p);
    plateau.add(b[col_p][lig_p]);
  }
  // *****************************************************************
  //     traite le clic sur la croix de la fenêtre graphique.
  //     termine l'application
  // *****************************************************************
  private void traiterFin()
  {
    addWindowListener( new WindowAdapter()
      { public void windowClosing( WindowEvent e)
        {
          System.exit(0);
        }
      } );
  }
  // *****************************************************************
  //     colore le fond de la case dont les coordonnées sont passées
  //     en paramètre (elles commencent à 0)
  // *****************************************************************
  private void colorerCase(int col_p, int lig_p)
  {
    if (jeu.getEchiquier().getCase(col_p+1, lig_p+1).getCouleur() == Couleur.BLANC)
      b[col_p][lig_p].setBackground(Color.white);
    else
      b[col_p][lig_p].setBackground(Color.darkGray);
  }
  // *****************************************************************
  //     retourne la pièce située sur la case passée en paramètre
  // *****************************************************************
  private Piece trouverPiece(Case case_p)
  {
    return jeu.getPiece(case_p);
  }
  // *****************************************************************
  //     retourne la couleur de la pièce posée sur la case dont les
  //     coordonnées sont passées en paramètre  (elles commencent à 0)
  //     retourne VIDE si la case est vide.
  // *****************************************************************
  private int trouverCase(int i, int j)
  {
    int retour = VIDE;
    Piece piece = jeu.getPiece(jeu.getEchiquier().getCase(i,j));
    if (piece != null)
      retour = piece.getCouleur();
    return retour;
  }
}