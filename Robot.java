package ricochetRobot;

/**
 * Un pion déplaçable par le joueur. Un robot ne peut se déplacer que tout droit
 * jusqu’à atteindre un obstacle (un bord du plateau, un mur ou un autre Robot).
 * <br>
 * <br>
 * Stratégie : Le Robot se trouve sur une Case Aléatoire du plateau, et peut se
 * déplacer sur l'une des CaseNext de cette case, à condition qu'elle ne soit
 * pas vide (mur) Le but du Robot 1 est d'atteindre la CaseObjectif en un
 * minimum de coup. <br>
 * <br>
 * Note : La première version du programme se fait avec 1 unique Robot, nous
 * n'avons pas besoin gérer les colisions entre Robots ou les modifications des
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
