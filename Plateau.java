package ricochetRobot;

import java.util.Random;

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
	 * Constructeur Vide : Cree un plateau de taille 16 et alloue la mémoire pour
	 * tabCases et tabRobots
	 */
	public Plateau() {
		taille = 16;
		tabCases = new Case[16][16];
		tabRobots = new Robot[1];
		tabRobots[0] = new Robot();
	}

	/**
	 * Cree un plateau de taille n et alloue la mémoire pour tabCases et tabRobots
	 */
	public Plateau(int n) {
		taille = n;
		tabCases = new Case[n][n];
		tabRobots = new Robot[1];
		tabRobots[0] = new Robot();
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

	/**
	 * Insert la case aux coordonnées i,j
	 * 
	 * @param c
	 *            La case a inserer
	 * @param i
	 *            La ligne ou inserer la case
	 * @param j
	 *            La colone ou inserer la case
	 */
	public void setCaseCoord(Case c, int i, int j) {

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
	 * Initialise la ligne i du plateau avec des cases Vides
	 */
	public void initLigne(int i) {
		Case[] tCases = new Case[this.getTaille()];
		for (int j = 0; i < this.getTaille(); i++) {
			tCases[j] = new Case();
		}
		this.setLigne(i, tCases);

	}

	/**
	 * Genere un plateau classique : 16*16 cases, murs prédéfinis
	 * 
	 * @return le plateau genere
	 */
	public static Plateau genererPlateauClassique() {
		return null;
	}

	/**
	 * Genere un plateau de taille n de manière Aleatoire : 12% de chances qu'une
	 * case ait au moins un mur (donc 3 % par coté)<br>
	 * Utilise :<br>
	 * 
	 * @see initLigne(int i)<br>
	 * @see determinerMurRandom(int n)
	 * 
	 * @param n
	 * @return
	 */
	public static Plateau genererPlateauRandom(int n) {
		// Generation d'un Plateau de taille n
		Plateau p = new Plateau(n);

		// Cree le tableau de case
		Case[][] tCases = new Case[n][n];

		// Determine quelles cases ont un mur
		boolean[][] murHaut = determinerMurRandom(n);
		for (int i = 0; i < n; i++)
			murHaut[0][i] = true;

		boolean[][] murGauche = determinerMurRandom(n);
		for (int i = 0; i < n; i++)
			murGauche[i][0] = true;

		boolean[][] murBas = determinerMurRandom(n);
		for (int i = 0; i < n; i++)
			murBas[n - 1][i] = true;

		boolean[][] murDroite = determinerMurRandom(n);
		for (int i = 0; i < n; i++)
			murDroite[i][n - 1] = true;

		// Creation d'une case next temporaire pour toutes les autres cases
		Case caseNonVide = Case.creerCase();

		// Place les murs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Case c = new Case();

				// Met soit un mur en HAUT de la case courante, soit une case non vide
				if (murHaut[i][j])
					c.setCaseNextHaut(new Case());
				else
					c.setCaseNextHaut(caseNonVide);

				// Met soit un mur a GAUCHE de la case courante, soit une case non vide
				if (murGauche[i][j])
					c.setCaseNextGauche(new Case());
				else
					c.setCaseNextGauche(caseNonVide);

				// Met soit un mur a DROITE de la case courante, soit une case non vide
				if (murDroite[i][j])
					c.setCaseNextDroite(new Case());
				else
					c.setCaseNextDroite(caseNonVide);

				// Met soit un mur en BAS de la case courante, soit une case non vide
				if (murBas[i][j])
					c.setCaseNextBas(new Case());
				else
					c.setCaseNextBas(caseNonVide);

				tCases[i][j] = c;
			}
		}

		// Attribue les CaseNext pour chaque case du plateau
		configureCaseNext(tCases);

		// Attribue le tableau de case dans le Plateau
		p.setTabCases(tCases);

		return p;
	}

	/**
	 * Configure les CaseNext en fontion d'un tableau où les murs sont déjà
	 * initialisés : Les caseNext actuels sont soit null, soit une case Vide)
	 * 
	 * @param tCases
	 */
	private static void configureCaseNext(Case[][] tCases) {
		int k;
		for (int i = 0; i < tCases.length; i++) {
			for (int j = 0; j < tCases.length; j++) {
				if (!tCases[i][j].getCaseNextHaut().estVide()) {
					// Ajout des CasesNext vers le haut
					k = 0;
					while (!tCases[i - k][j].getCaseNextHaut().estVide()) {
						k++;
					}
					tCases[i][j].setCaseNextHaut(tCases[i - k][j]);
				}

				if (!tCases[i][j].getCaseNextBas().estVide()) {
					// Ajout des CasesNext vers le bas
					k = 0;
					while (!tCases[i + k][j].getCaseNextBas().estVide()) {
						k++;
					}
					tCases[i][j].setCaseNextBas(tCases[i + k][j]);
				}

				if (!tCases[i][j].getCaseNextGauche().estVide()) {
					// Ajout des CasesNext vers la gauche
					k = 0;
					while (!tCases[i][j - k].getCaseNextGauche().estVide()) {
						k++;
					}
					tCases[i][j].setCaseNextGauche(tCases[i][j - k]);
				}

				if (!tCases[i][j].getCaseNextDroite().estVide()) {
					// Ajout des CasesNext vers la Droite
					k = 0;
					while (!tCases[i][j + k].getCaseNextDroite().estVide()) {
						k++;
					}
					tCases[i][j].setCaseNextDroite(tCases[i][j + k]);
				}
			}
		}
	}

	/**
	 * Determine de façon aleatoire quelle case aura un mur. Une case a 3 % de
	 * chance d'avoir un mur
	 * 
	 * @param n
	 *            la taille du plateau
	 */
	private static boolean[][] determinerMurRandom(int n) {
		Random r = new Random();
		int tirage;
		boolean[][] t = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tirage = r.nextInt(100);
				if (tirage < 3)
					t[i][j] = true;
				else
					t[i][j] = false;
			}
		}
		return t;
	}

}
