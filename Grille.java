package ricochetRobot;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * La partie graphique de la grille. Elle est composee de CaseGrille
 * 
 * @author Dorian
 *
 */
public class Grille extends JPanel implements MouseListener {
	private int taille = 10;
	private CaseGrille[][] grille;

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

	public void paintComponent(Graphics g) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

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

}