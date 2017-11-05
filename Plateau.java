package ricochetRobot;

/**
 * Plateau du jeu, composé de Cases et de Robots <br>
 * <br>
 * Stratégie : La génération du Plateau crée des cases et attribue à chaque case
 * les case next. Il doit également créer au moins une case de type
 * CaseObjectif. <br>
 * <br>
 * S'il y a plusieurs Robots : un Robot placé sur une Case du plateau redéfinira
 * les CaseNext de toutes les cases horizontales et verticales.<br>
 * <br>
 * Les murs sont représentés par des Cases vides mais ces cases vides
 * n'apparaissent pas dans tabCases. <br>
 * Graphiquement : Les murs seront retrouvés en faisant un test à chaque case si
 * ses next sont vides, si oui, un mur apparaitra à coté de la case.
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
	 * Constructeur Vide : Cree un plateau de taille 16 et alloue la mémoire pour tabCases et tabRobots
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
	 * Initialise la ligne i du plateau avec des cases et des murs de tous les cotés
	 */
	public void initLigne(int i) {
		Case[] tCases = new Case[this.getTaille()];
		for (int j = 0; i<this.getTaille(); i++) {
			tCases[j] = Case.creerCase();
		}
		this.setLigne(i, tCases);
		
	}
	/**
	 * Genere un plateau classique : 16*16 cases, murs prédéfinis
	 * @return le plateau genere
	 */
	public Plateau genererPlateauClassique() {
		return null;
	}
	
	/**
	 * Genere un plateau de taille n de manière Aléatoire : 10% de chances qu'une case ait au moins un mur
	 * 
	 * @param n
	 * @return
	 */
	public Plateau genererPlateauRandom(int n) {
		// TODO
		return null;
	}

}
