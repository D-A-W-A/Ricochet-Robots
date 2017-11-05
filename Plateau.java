package ricochetRobot;

import java.util.Random;

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
	 * Constructeur Vide : Cree un plateau de taille 16 et alloue la m�moire pour
	 * tabCases et tabRobots
	 */
	public Plateau() {
		taille = 16;
		tabCases = new Case[16][16];
		tabRobots = new Robot[1];
	}

	/**
	 * Cree un plateau de taille n et alloue la m�moire pour tabCases et tabRobots
	 */
	public Plateau(int n) {
		taille = n;
		tabCases = new Case[n][n];
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

	public void setLigne(int i, Case[] t) {
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
		for (int j = 0; i < this.getTaille(); i++) {
			tCases[j] = Case.creerCase();
		}
		this.setLigne(i, tCases);

	}

	/**
	 * Genere un plateau classique : 16*16 cases, murs pr�d�finis
	 * 
	 * @return le plateau genere
	 */
	public static Plateau genererPlateauClassique() {
		return null;
	}

	/**
	 * Genere un plateau de taille n de mani�re Aleatoire : 12% de chances qu'une
	 * case ait au moins un mur (donc 3 % par cot�)<br>
	 * Utilise :<br> 
	 * @see initLigne(int i)<br>
	 * @see determinerMurRandom(int n)
	 * 
	 * @param n
	 * @return
	 */
	public static Plateau genererPlateauRandom(int n) {
		Plateau p = new Plateau(n);
		for (int i = 0; i < n; i++) {
			p.initLigne(i);
		}
		
	}
	
	/**
	 * Determine de fa�on aleatoire quelle case aura un mur. Une case a 3 % de chance d'avoir un mur
	 * @param n la taille du plateau
	 */
	private static boolean[][] determinerMurRandom(int n) {
		Random r = new Random();
		int tirage;
		boolean[][] t = new boolean [n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				tirage = r.nextInt(100);
				if (tirage <3)
					t[i][j] = true;
				else 
					t[i][j] = false;
			}
		}
		return t;
	}

}
