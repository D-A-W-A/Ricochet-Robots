package ricochetRobot;

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
public class Partie {
	private int idPartie;
	private Plateau plateau;
	private Chrono chrono;
	private int nbCoups;

}
