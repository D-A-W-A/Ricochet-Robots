package ricochetRobot;

import java.awt.event.KeyEvent;

/**
 * Une Partie de Ricochet-Robots.<br>
 * Le jeu se d�roule sur un plateau d�un nombre n*n de cases et de murs. Sur les
 * cases sont plac�s des pions appel�s �robots� et des objectifs. <br>
 * Le jeu se d�roule en tours. Au d�but du tour, on indique un objectif. Le but
 * est de d�placer le robot de la couleur de l�objectif sur celui-ci en un
 * minimum de mouvements (D�placer les autres robots compte �galement pour 1
 * coup). <br>
 * Un robot ne peut se d�placer que tout droit jusqu�� atteindre un obstacle (un
 * bord du plateau, un mur ou un autre robot). <br>
 * Le joueur ayant trouv� le plus rapidement la solution annonce son nombre de
 * mouvements et retourne le sablier. Si les autres joueurs ne trouvent pas de
 * meilleures solutions avant la fin du temps imparti, il remporte la manche.
 * <br>
 * <br>
 * Strat�gie : La partie g�n�re un plateau et lance les diff�rentes actions pour
 * permettre � 1 ou plusieurs joueurs de jouer au jeu.
 * 
 * @author Dorian
 *
 */
public class Partie extends FlecheClavierListener {
	private int idPartie;
	private Plateau plateau;
	private Chrono chrono;
	private int nbCoups;
	private int nbVictoire = 0;

	public Partie() {
		super();
		idPartie = 1;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Chrono getChrono() {
		return chrono;
	}

	public void setChrono(Chrono chrono) {
		this.chrono = chrono;
	}

	public int getNbCoups() {
		return nbCoups;
	}

	public void setNbCoups(int nbCoups) {
		this.nbCoups = nbCoups;
	}

	public int getNbVictoire() {
		return nbVictoire;
	}

	public void setNbVictoire(int nbVictoire) {
		this.nbVictoire = nbVictoire;
	}

	public int getIdPartie() {
		return idPartie;
	}

	// MODIFICATION DU LISTENER DES FLECHES
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP || code == KeyEvent.VK_LEFT
				|| code == KeyEvent.VK_RIGHT) {
			setNbCoups(getNbCoups()+1);
			System.out.println("Nombre de coups : "+ getNbCoups());
		}
	}

	public static void main(String[] args) {
		Partie p = new Partie();
	}

}
