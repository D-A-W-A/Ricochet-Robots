package ricochetRobot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
public class Fenetre extends JFrame {

	Grille grille;
	String titre = "Ricochet - Robots";
	int tailleGrille = 10;

	public Fenetre() {
		grille = new Grille(tailleGrille);
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
		this.setSize(screenWidth / 2 + screenWidth / 3, screenHeight / 2 + screenHeight / 3);

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
		JLabel titreLabel = new JLabel(titre);
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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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
}