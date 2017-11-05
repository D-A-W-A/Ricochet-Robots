package ricochetRobot;

/**
 * Plateau du jeu, compos� de Cases et de Robots <br>
 * <br>
 * Strat�gie : La g�n�ration du Plateau cr�e des cases et attribue � chaque case
 * les case next. Il doit �galement cr�er au moins une case de type
 * CaseObjectif. <br>
 * <br>
 * S'il y a plusieurs Robots : un Robot plac� sur une Case du plateau red�finira
 * les CaseNext de toutes les cases horizontales et verticales.<br>
 * <br>
 * Les murs sont repr�sent�s par des Cases vides mais ces cases vides
 * n'apparaissent pas dans tabCases. <br>
 * Graphiquement : Les murs seront retrouv�s en faisant un test � chaque case si
 * ses next sont vides, si oui, un mur apparaitra � cot� de la case.
 * 
 * @author Dorian
 *
 */
public class Plateau {
	
	//////// ATTRIBUTS ///////////
	
	
	private int taille;
	private Case[][] tabCases;
	private Robot[] tabRobots;

	
	/////// CONSTRUCTEURS /////////
	
	/**
	 * Constructeur Vide : Cree un plateau de taille 16 et alloue la m�moire pour tabCases et tabRobots
	 */
	public Plateau() {
		taille = 16;
		tabCases = new Case[16][16];
		tabRobots = new Robot[1];
	}
		
	
	/////// GETTERS AND SETTERS ////////

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Case[][] getTabCases() {
		return tabCases;
	}

	public void setTabCases(Case[][] tabCases) {
		this.tabCases = tabCases;
	}
	
	public void setLigne (int i, Case[] t) {
		tabCases[i] = t;
	}

	public Robot[] getTabRobots() {
		return tabRobots;
	}

	public void setTabRobots(Robot[] tabRobots) {
		this.tabRobots = tabRobots;
	}
	
	
	/////// METHODES /////////
	
	
	/**
	 * Initialise la ligne i du plateau avec des cases et des murs de tous les cot�s
	 */
	public void initLigne(int i) {
		Case[] tCases = new Case[this.getTaille()];
		for (int j = 0; i<this.getTaille(); i++) {
			tCases[j] = Case.creerCase();
		}
		this.setLigne(i, tCases);
		
	}
	/**
	 * Genere un plateau classique : 16*16 cases, murs pr�d�finis
	 * @return le plateau genere
	 */
	public Plateau genererPlateauClassique() {
		return null;
	}
	
	/**
	 * Genere un plateau de taille n de mani�re Al�atoire : 10% de chances qu'une case ait au moins un mur
	 * 
	 * @param n
	 * @return
	 */
	public Plateau genererPlateauRandom(int n) {
		// TODO
		return null;
	}

}
