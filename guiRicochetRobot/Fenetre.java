package guiRicochetRobot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import observer.Observateur;


/**
 * 
 * La fenetre du jeu <br>
 *Elle contient tous les autres elements de l'interface graphique : <br>
 *<ul><li>Une Grille</li>
 *<li>Des Boutons</li>
 *<li>Un titre et nombre de coups (Plus tard)</li>
 *<li>Le chrono plus tard</li></ul><br>
 *La classe partie heritera de cette classe
 *
 * @author Dorian
 *
 *	
 */
public class Fenetre extends JFrame implements Observateur, KeyListener{

	Grille grille;
	JLabel titreLabel = new JLabel ("Ricochet-Robot");
	int tailleGrille = 10;
	

	public Fenetre() {
		grille = new Grille(tailleGrille);
		grille.addObservateur(this);
	}
	
	public Fenetre(int taille, int [][] murs, int [] posRobot, int [] posObjectif) {
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

		// Definis le titre et les parametres de la fenetre
		this.setTitle("Ricochet-Robots");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Met la grille au milieu
		this.getContentPane().add(grille, BorderLayout.CENTER);

		// Creation des boutons
		JButton pause = new JButton("Pause");
		JButton reprendre = new JButton("Reprendre");
		JButton restart = new JButton("Recommencer");

		// Creation du menu de gauche (Compose des boutons crees precedemment)
		JPanel menuGauche = new JPanel();
		for (int i = 0; i < 2; i++)
			menuGauche.add(new JPanel());
		menuGauche.add(pause);
		menuGauche.add(reprendre);
		menuGauche.add(restart);
		GridLayout menulayout = new GridLayout(9, 1);
		menuGauche.setLayout(menulayout);

		// Ajout du menuGauche a gauche
		this.getContentPane().add(menuGauche, BorderLayout.WEST);

		// Creation du Titre + Nombre de coups
		Font police = new Font("Tahoma", Font.BOLD, 24);
		titreLabel.setFont(police);
		titreLabel.setHorizontalAlignment(JLabel.CENTER);

		// Place les labels en haut de la fenetre
		JPanel hautFenetre = new JPanel();
		hautFenetre.add(titreLabel);
		this.getContentPane().add(hautFenetre, BorderLayout.NORTH);

		// Affiche la fenetre (Pas de pack : pas besoin + fait bugger)
		this.setVisible(true);
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public JLabel getTitreLabel() {
		return titreLabel;
	}

	public void setTitreLabel(JLabel j) {
		this.titreLabel = j;
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
		System.out.println("Clic de la souris aux coordonnées : " + grille.getCoordCaseClic()[0] + " " + grille.getCoordCaseClic()[1]);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}