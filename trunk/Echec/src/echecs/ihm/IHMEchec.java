package echecs.ihm;

/** Packages utilis�s par ce package
 *  Au sein de ce projet, seul metier.plateau.*
 *                             metier.jeu.*
 *                             metier.materiel.*  sont autoris�s
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
 * Soci�t� : Fabrice Tranchand
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
  // les cases de l'�chiquier sont repr�sent�es par des "boutons"
  private JButton[][] b;
  // �l�ment de menu permettant de rejouer
  private JMenuItem rejouer;
  // panel repr�sentant l'�chiquier
  private JPanel plateau;
  // zone de texte identiquant le joueur qui a le trait
  private JTextField tourJoueur;
  // zone de message d'alerte � l'intention des joueurs
  private JTextField message;
  // �l�ments graphiques de base
  private GridBagLayout trace;
  private GridBagConstraints traceContrainte;
  private Container conteneur;
  // classe principale du "m�tier"
  private Partie jeu;





  // -----------------------------------------------------------------
  //     constructeur
  // -----------------------------------------------------------------
  // *****************************************************************
  //     le premier param�tre d�signe le m�tier
  //     le second param�tre d�signe le contr�leur
  //     le constructeur cr�e l'ihm : plateau, menu, zone de texte
  // *****************************************************************
  public IHMEchec(Partie jeu_p, ActionListener ech_p)
  {
    super ("Jeu d'�checs");
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
  //     les m�thodes publiques
  // ----------------------------------------------------------------
  // *****************************************************************
  //     accesseurs
  // *****************************************************************
  public JMenuItem getToucheRejouer() { return rejouer; }
  public JButton[][] getCases() { return b; }
  // *****************************************************************
  //     affiche le premier param�tre dans la zone indiquant le joueur
  //     qui a le trait, et le second param�tre dans la zone d'alerte
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
  //     si le deuxi�me param�tre vaut true, affiche la case pass�e en
  //     premier param�tre sur fond rouge. Sinon, affiche le fond ad�quat
  //     (blanc ou noir) conform�ment � une plateau d'�chec
  // *****************************************************************
  public void repererCase(Case case_p, boolean marquer_p)
  {
    if (marquer_p)
      b[case_p.getLigne()-1][case_p.getColonne()-1].setBackground(Color.red);
    else
      colorerCase(case_p.getLigne()-1,case_p.getColonne()-1);
  }
  // *****************************************************************
  //     affiche les pi�ces sur l'�chiquier
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
  //     les m�thodes priv�es
  // ----------------------------------------------------------------
  // *****************************************************************
  //     construit le plateau d'�checs graphique
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
  //     permet d'ajouter des �l�ments graphiques dans l'ihm
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
  //     cr�e un bouton repr�sentant la case dont les coordonn�es sont
  //     pass�es en param�tre (elles commencent � 0)
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
  //     traite le clic sur la croix de la fen�tre graphique.
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
  //     colore le fond de la case dont les coordonn�es sont pass�es
  //     en param�tre (elles commencent � 0)
  // *****************************************************************
  private void colorerCase(int col_p, int lig_p)
  {
    if (jeu.getEchiquier().getCase(col_p+1, lig_p+1).getCouleur() == Couleur.BLANC)
      b[col_p][lig_p].setBackground(Color.white);
    else
      b[col_p][lig_p].setBackground(Color.darkGray);
  }
  // *****************************************************************
  //     retourne la pi�ce situ�e sur la case pass�e en param�tre
  // *****************************************************************
  private Piece trouverPiece(Case case_p)
  {
    return jeu.getPiece(case_p);
  }
  // *****************************************************************
  //     retourne la couleur de la pi�ce pos�e sur la case dont les
  //     coordonn�es sont pass�es en param�tre  (elles commencent � 0)
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