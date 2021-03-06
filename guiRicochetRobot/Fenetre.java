package guiRicochetRobot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import observer.Observateur;

/**
 * 
 * La fenetre du jeu <br>
 * Elle contient tous les autres elements de l'interface graphique : <br>
 * <ul>
 * <li>Une Grille</li>
 * <li>Des Boutons</li>
 * <li>Un titre et nombre de coups (Plus tard)</li>
 * <li>Le chrono plus tard</li>
 * </ul>
 * <br>
 * La classe partie heritera de cette classe
 *
 * @author Dorian
 *
 * 
 */
public class Fenetre extends JFrame implements Observateur, MouseListener {

	Grille grille;
	int tailleGrille = 10;

	public Fenetre() {
		grille = new Grille(tailleGrille);
		grille.addObservateur(this);
	}

	public Fenetre(int taille, int[][] murs, int[] posRobot, int[] posObjectif) {
		tailleGrille = taille;
		grille = new Grille(taille);
		grille.addObservateur(this);
	}

	/**
	 * Fonction a lancer pour initialiter l'interface graphique
	 */
	public void lancerFenetre() {
		// Obtiens et configure les dimentions de la fenetre
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setSize(screenWidth / 2, screenHeight / 2 + screenHeight / 3);
		Font police = new Font("Tahoma", Font.BOLD, 24);
		Font policeGauche = new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 18);

		// Definis le titre et les parametres de la fenetre
		this.setTitle("Ricochet-Robots");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Met la grille au milieu
		this.getContentPane().add(grille, BorderLayout.CENTER);

		// Creation des boutons

		JButton recommencer = new JButton("Recommencer");
		recommencer.addMouseListener(this);
		recommencer.setFont(policeGauche);

		JButton solution = new JButton("Solution");
		solution.addMouseListener(this);
		solution.setFont(policeGauche);
		
		JButton pause = new JButton("Pause");
		pause.setFont(policeGauche);
		pause.addMouseListener(this);

		// Creation des JLabels de gauche
		JLabel sNbCoups = new JLabel("Coups : 0  ");
		sNbCoups.setFont(policeGauche);
		sNbCoups.setHorizontalAlignment(JLabel.CENTER);

		JLabel nbVictoire = new JLabel("Victoires : 0");
		nbVictoire.setFont(policeGauche);
		nbVictoire.setHorizontalAlignment(JLabel.CENTER);

		JLabel chrono = new JLabel("00 : 00");
		chrono.setFont(police);
		chrono.setHorizontalAlignment(JLabel.CENTER);

		// Creation du menu de gauche (Compose de JLabel des boutons crees precedemment)
		JPanel menuGauche = new JPanel();

		menuGauche.add(sNbCoups);
		menuGauche.add(nbVictoire);
		menuGauche.add(new JPanel());
		menuGauche.add(recommencer);
		menuGauche.add(solution);
		for (int i = 5; i < 7; i++) {
			menuGauche.add(new JPanel());
		}
		menuGauche.add(pause);
		menuGauche.add(chrono);

		GridLayout menulayout = new GridLayout(9, 1);
		menuGauche.setLayout(menulayout);

		// Ajout du menuGauche a gauche
		this.getContentPane().add(menuGauche, BorderLayout.WEST);

		// Creation du Titre
		JLabel titreLabel = new JLabel("Ricochet-Robot");
		titreLabel.setFont(police);
		titreLabel.setHorizontalAlignment(JLabel.CENTER);

		this.getContentPane().add(titreLabel, BorderLayout.NORTH);

		// Affiche la fenetre (Pas de pack : pas besoin + fait bugger)
		this.setVisible(true);
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public int getTailleGrille() {
		return tailleGrille;
	}

	public void setTailleGrille(int tailleGrille) {
		this.tailleGrille = tailleGrille;
	}

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
		f.lancerFenetre();
	}

	/**
	 * Fonction appelee par grille lorsqu'une case est cliquee
	 */
	@Override
	public void update(String ob) {
		System.out.println("Clic de la souris aux coordonnées : " + grille.getCoordCaseClic()[0] + " "
				+ grille.getCoordCaseClic()[1]);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton b = (JButton) (e.getSource());
		System.out.println("Clic de : " + b.getText());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// ------- Changement des elements graphiques ------

	/**
	 * Change le texte en haut de la fenetre
	 * 
	 * @param s
	 *            la chaine de caractere qui remplacera
	 */
	public void changerTexte(String s) {
		JLabel l = (JLabel) this.getContentPane().getComponents()[2];
		l.setText(s);
	}
	public void changerBackgroundRec() {
		JButton b1 = (JButton)((JPanel) getContentPane().getComponents()[1]).getComponent(4);
		b1.setVisible(false);
		JButton b2 = (JButton)((JPanel) getContentPane().getComponents()[1]).getComponent(7);
		b2.setVisible(false);
		JLabel l = (JLabel) this.getContentPane().getComponents()[2];
		l.setFont(new Font("Tahoma", Font.BOLD, 42));
		getContentPane().remove(0);
		getContentPane().add(getContentPane().getComponent(1), BorderLayout.CENTER);
	}

	/**
	 * Change le texte du nombre de coups
	 * 
	 * @param s
	 *            l'entier qui remplacera l'ancienne valeur
	 */
	public void changerNbCoups(int s) {
		JLabel l = (JLabel) ((JPanel) getContentPane().getComponents()[1]).getComponent(0);
		l.setText("Coups : " + s);
	}

	public void changerNbVictoire(int s) {
		JLabel l = (JLabel) ((JPanel) getContentPane().getComponents()[1]).getComponent(1);
		l.setText("Victoires : " + s);
	}
	
	public void changerChrono (String s) {
		JLabel l = (JLabel) ((JPanel) getContentPane().getComponents()[1]).getComponent(8);
		l.setText(s);
		
	}
	
	public void changerPlay(String s) {
		JButton b = (JButton)((JPanel) getContentPane().getComponents()[1]).getComponent(7);
		b.setText(s);
		if (s.equals ("Pause")) {
			getContentPane().getComponents()[0].setVisible(true);
		}
		else if (s.equals("Play")) {
			getContentPane().getComponents()[0].setVisible(false);
		}
		
		this.repaint();
			
	}

}