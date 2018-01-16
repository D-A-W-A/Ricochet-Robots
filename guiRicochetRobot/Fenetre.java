package guiRicochetRobot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

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

		JButton solution = new JButton("Solution");
		solution.addMouseListener(this);

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
		for (int i = 5; i < 8; i++) {
			menuGauche.add(new JPanel());
		}
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

}