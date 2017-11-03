package ricochetRobot;

/**
 * Un pion d�pla�able par le joueur. Un robot ne peut se d�placer que tout droit
 * jusqu�� atteindre un obstacle (un bord du plateau, un mur ou un autre Robot).
 * <br>
 * <br>
 * Strat�gie : Le Robot se trouve sur une Case Al�atoire du plateau, et peut se
 * d�placer sur l'une des CaseNext de cette case, � condition qu'elle ne soit
 * pas vide (mur) Le but du Robot 1 est d'atteindre la CaseObjectif en un
 * minimum de coup. <br>
 * <br>
 * Note : La premi�re version du programme se fait avec 1 unique Robot, nous
 * n'avons pas besoin g�rer les colisions entre Robots ou les modifications des
 * CaseNext
 * 
 * @author Dorian
 *
 */
public class Robot {
	private int idRobot;
	private boolean selection;
	private int[] position = new int[2];

	public Robot() {
		// TODO
	}

}
