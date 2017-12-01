package ricochetRobot;

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
	//Notre collection d'observateurs
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

	public Grille() {
		// On alloue la memoire pour une grille de taille : (taille*taille)
		grille = new CaseGrille[taille][taille];

		// On definit le layout qu'on adoptera (Grid)
		this.setLayout(new GridLayout(taille, taille));

		// On crée nos differentes cases et on les ajoute au panel this
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				grille[i][j] = new CaseGrille();
				this.add(grille[i][j]);
				grille[i][j].addActionListener(this);
			}
		}

	}

	public Grille(int t) {
		taille = t;
		grille = new CaseGrille[taille][taille];
		// On definit le layout qu'on adoptera
		this.setLayout(new GridLayout(taille, taille));

		// On crée nos differentes cases
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				grille[i][j] = new CaseGrille();
				grille[i][j].setPosX(i);
				grille[i][j].setPosY(j);
				grille[i][j].addActionListener(this);
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

	public void paintComponent(Graphics g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CaseGrille c = (CaseGrille) (e.getSource());
		System.out.println("X : " + c.getPosX() + "\tY : " + c.getPosY());
		coordCaseClic[0] = c.getPosX();
		coordCaseClic[1] = c.getPosY();
		this.updateObservateur();
	}

	// Ajoute un observateur à la liste
	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
	}

	// Retire tous les observateurs de la liste
	public void delObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}

	// Avertit les observateurs que l'objet observable a changé
	// et invoque la méthode update() de chaque observateur
	public void updateObservateur() {
		for (Observateur obs : this.listObservateur)
			obs.update("clic");
	}

}