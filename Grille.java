package ricochetRobot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Grille extends JPanel{
	private int taille = 10;
	private CaseGrille[][] grille = new CaseGrille[taille][taille];

	public Grille() {

		// On definit le layout qu'on adoptera
		this.setLayout(new GridLayout(taille, taille));

		// On crée nos differentes cases
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				grille[i][j] = new CaseGrille();
				this.add(grille[i][j]);
			}
		}

	}

	public void paintComponent(Graphics g) {

	}

}