package ricochetRobot;

/**
 * Un pion deplacable par le joueur. Un robot ne peut se deplacer que tout droit
 * jusqu'a atteindre un obstacle (un bord du plateau, un mur ou un autre Robot).
 * <br>
 * <br>
 * Strategie : Le Robot se trouve sur une Case Aleatoire du plateau, et peut se
 * deplacer sur l'une des CaseNext de cette case, a condition qu'elle ne soit
 * pas vide (mur) Le but du Robot 1 est d'atteindre la CaseObjectif en un
 * minimum de coup. <br>
 * <br>
 * Note : La premiere version du programme se fait avec 1 unique Robot, nous
 * n'avons pas besoin gerer les colisions entre Robots ou les modifications des
 * CaseNext
 * 
 * @author Dorian
 *
 */
public class Robot {
	private int idRobot;
	private boolean selectione;
	private int[] position = new int[2];
	private static int nbRobot = 0;

	public Robot() {
		nbRobot++;
		this.idRobot = nbRobot;
		// TODO
	}

	public boolean isSelectione() {
		return selectione;
	}

	public void setSelectione(boolean selectione) {
		this.selectione = selectione;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public int getIdRobot() {
		return idRobot;
	}

}
