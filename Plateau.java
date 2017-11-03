package ricochetRobot;

/**
 * Plateau du jeu, compos� de Cases et de Robots
 * <br><br>
 * Strat�gie : La g�n�ration du Plateau cr�e des cases et attribue � chaque case les case next. Il doit �galement cr�er au moins une case de type CaseObjectif.
 * <br>Les murs sont repr�sent�s par des Cases vides mais ces cases vides n'apparaissent pas dans tabCases. 
 * <br>Graphiquement : Les murs seront retrouv�s en faisant un test � chaque case si ses next sont vides, si oui, un mur apparaitra � cot� de la case.
 * @author Dorian
 *
 */
public class Plateau {
	private int taille;
	private Case[][] tabCases;
	private Robot[] tabRobots;

	public Plateau() {
		// TODO
	}

}

