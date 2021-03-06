package guiRicochetRobot;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import observer.Observable;
import observer.Observateur;

/**
 * La partie graphique de la grille. Elle est composee de CaseGrille
 * 
 * @author Dorian
 *
 */
public class Grille extends JPanel implements ActionListener, Observable {
	private int taille = 10;
	private CaseGrille[][] grille;
	private int[] coordCaseClic = new int[2];

	// Notre collection d'observateurs
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

	public Grille(int t) {
		// On alloue la memoire pour une grille de taille : (taille*taille)
		grille = new CaseGrille[taille][taille];

		// On definit le layout qu'on adoptera (Grid)
		this.setLayout(new GridLayout(taille, taille));

		// On cr�e nos differentes cases et on les ajoute au panel this
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				grille[i][j] = new CaseGrille(i, j, 0);
				this.add(grille[i][j]);
				grille[i][j].addActionListener(this);
			}
		}

	}

	public Grille(int taille, int[][] murs, int[][] posRobot, int[] posObjectif) {
		this.taille = taille;
		grille = new CaseGrille[taille][taille];
		// On definit le layout qu'on adoptera
		this.setLayout(new GridLayout(taille, taille));

		// On cr�e nos differentes cases
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				grille[i][j] = new CaseGrille(i, j, murs[i][j]);

				// On y definit les murs
				grille[i][j].setMurs(murs[i][j]);

				// On place le robot s'il y en a un
				for (int x = 0; x < posRobot.length; x++) {
					if (posRobot[x][0] == i && posRobot[x][1] == j) {
							grille[i][j].setHasRobot(x+1);
							if (x == 0)
								grille[i][j].setSelected(true);
					}
				}

				// On place l'objectif
				if (posObjectif[0] == i && posObjectif[1] == j)
					grille[i][j].setObjective(true);

				// La grille ecoute chacune des cases
				grille[i][j].addActionListener(this);

				// On ajoute la nouvelle case au tableau
				this.add(grille[i][j]);
			}
		}

	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public CaseGrille[][] getGrille() {
		return grille;
	}

	public void setGrille(CaseGrille[][] grille) {
		this.grille = grille;
	}

	public int[] getCoordCaseClic() {
		return coordCaseClic;
	}

	public void setCoordCaseClic(int[] coordCaseClic) {
		this.coordCaseClic = coordCaseClic;
	}

	/**
	 * Fonction declenchee lorsque l'utilisateur clique sur une case
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CaseGrille c = (CaseGrille) (e.getSource());
		coordCaseClic[0] = c.getPosX();
		coordCaseClic[1] = c.getPosY();
		this.updateObservateur();
	}

	/**
	 * Ajoute un observateur a la liste
	 */
	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
	}

	/**
	 * Retire tous les observateurs de la liste
	 */
	public void delObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}

	/**
	 * Avertit les observateurs que l'objet observable a change et invoque la
	 * methode update() de chaque observateur
	 */
	public void updateObservateur() {
		for (Observateur obs : this.listObservateur)
			obs.update("clic");
	}

}