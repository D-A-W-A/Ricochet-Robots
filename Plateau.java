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

		// corrige les tableaux de booléens pour appliquer les murs des deux cotés
		murDesDeuxCotes(murHaut, murBas, murGauche, murDroite);

		// Creation de cases next temporaire pour toutes les autres cases
		Case caseNonVide = Case.creerCase();
		Case caseVide = new Case();

		// Place les murs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Case c = new Case();

				// Met soit un mur en HAUT de la case courante, soit une case non vide
				if (murHaut[i][j])
					c.setCaseNextHaut(caseVide);
				else
					c.setCaseNextHaut(caseNonVide);

				// Met soit un mur a GAUCHE de la case courante, soit une case non vide
				if (murGauche[i][j])
					c.setCaseNextGauche(caseVide);
				else
					c.setCaseNextGauche(caseNonVide);

				// Met soit un mur a DROITE de la case courante, soit une case non vide
				if (murDroite[i][j])
					c.setCaseNextDroite(caseVide);
				else
					c.setCaseNextDroite(caseNonVide);

				// Met soit un mur en BAS de la case courante, soit une case non vide
				if (murBas[i][j])
					c.setCaseNextBas(caseVide);
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
	 * Réévalue les tableaux de murs pour qu'un mur soit présent sur les deux cotés
	 * : <br>
	 * ex : Un murDroite sur une Case est un murGauche sur la case de Droite
	 * 
	 * @param murHaut
	 * @param murBas
	 * @param murGauche
	 * @param murDroite
	 *            <br>
	 *            préreq : tous les tableaux doivent être de même taille
	 */
	private static void murDesDeuxCotes(boolean[][] murHaut, boolean[][] murBas, boolean[][] murGauche,
			boolean[][] murDroite) {
		for (int i = 0; i < murHaut[0].length; i++) {
			for (int j = 0; j < murHaut[0].length; j++) {
				if (i > 0 && murHaut[i][j])
					murBas[i - 1][j] = true;
				if (i < murHaut[0].length - 1 && murBas[i][j])
					murHaut[i + 1][j] = true;
				if (j > 0 && murGauche[i][j])
					murDroite[i][j - 1] = true;
				if (j < murHaut[0].length - 1 && murDroite[i][j])
					murGauche[i][j + 1] = true;
			}
		}
	}

	/**
	 * Configure les CaseNext en fontion d'un tableau où les murs sont déjà
	 * initialisés : Les caseNext actuels sont soit null, soit une case Vide) <br>
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
	 * Determine de façon aleatoire quelle case aura un mur. Une case a 5 % de
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
				if (tirage < 5)
					t[i][j] = true;
				else
					t[i][j] = false;
			}
		}
		return t;
	}

	/**
	 * Genere une String du Plateau, * Pour une case, _ Pour mur du haut ou bas, |
	 * pour mur de gauche ou droite
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < taille; i++) {
			if (tabCases[0][i].getCaseNextHaut().estVide())
				s.append("  _ ");
			else
				s.append("    ");
		}
		for (int i = 0; i < taille; i++) {
			s.append("\n|");
			for (int j = 0; j < taille; j++) {
				if (tabCases[i][j].getCaseNextDroite().estVide())
					s.append(" " + tabCases[i][j].toString() + " |");
				else
					s.append(" " + tabCases[i][j].toString() + "  ");

			}
			s.append("\n ");
			for (int j = 0; j < taille; j++) {
				if (tabCases[i][j].getCaseNextBas().estVide())
					s.append(" _  ");
				else
					s.append("    ");
			}
		}
		return s.toString();
	}

	/**
	 * Redefinis les lignes lors d'une modification de case
	 * 
	 * @param i
	 * @param j
	 */
	public void redefinirLignes(int ligne, int colone) {
		int k;
		for (int m = 0; m < colone; m++) {
			if (!tabCases[ligne][m].getCaseNextGauche().estVide()) {
				// Ajout des CasesNext vers la gauche
				k = 0;
				while (!tabCases[ligne][m - k].getCaseNextGauche().estVide()) {
					k++;
				}
				tabCases[ligne][m].setCaseNextGauche(tabCases[ligne][m - k]);
			}

			if (!tabCases[ligne][m].getCaseNextDroite().estVide()) {
				// Ajout des CasesNext vers la Droite
				k = 0;
				while (!tabCases[ligne][m + k].getCaseNextDroite().estVide()) {
					k++;
				}
				tabCases[ligne][m].setCaseNextDroite(tabCases[ligne][m + k]);
			}
		}

		for (int m = 0; m < ligne; m++) {
			if (!tabCases[m][colone].getCaseNextHaut().estVide()) {
				// Ajout des CasesNext vers le haut
				k = 0;
				while (!tabCases[m - k][colone].getCaseNextHaut().estVide()) {
					k++;
				}
				tabCases[m][colone].setCaseNextHaut(tabCases[m - k][colone]);
			}

			if (!tabCases[m][colone].getCaseNextBas().estVide()) {
				// Ajout des CasesNext vers le bas
				k = 0;
				while (!tabCases[m + k][colone].getCaseNextBas().estVide()) {
					k++;
				}
				tabCases[m][colone].setCaseNextBas(tabCases[m + k][colone]);
			}
		}
	}

	/**
	 * Ajoute de façon aleatoire un objectif sur le plateau
	 * Note : L'objectif doit se trouver à coté d'un mur sinon la partie est impossible
	 */
	public void ajouterObjectifRandom() {
		Random r = new Random();
		int i = r.nextInt(taille);
		int j = r.nextInt(taille);
		CaseObjectif c = new CaseObjectif();
		c.setCaseNext(tabCases[i][j].getCaseNext());
		tabCases[i][j] = c;
		redefinirLignes(i, j);

	}
	
	/**
	 * Place le robot n° num de façon random
	 * @param num
	 */
	public void placerRobotRandom(int num) {
		Random r = new Random();
		int i = r.nextInt(taille);
		int j = r.nextInt(taille);
		int[] t = {i,j};
		this.tabRobots[num].setPosition(t);
		this.tabCases[i][j].setOccupe(true);
	}
	
	/**
	 * Insere les coordonees de chaque Case dans Case.posX et Case.posY
	 */
	public void insererCoordonees() {
		for (int i=0; i <taille; i++) {
			for (int j=0; j<taille; j++) {
				this.tabCases[i][j].setPos(i, j);
			}
		}
	}

}
