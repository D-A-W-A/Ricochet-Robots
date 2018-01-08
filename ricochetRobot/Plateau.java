package ricochetRobot;

import java.util.Random;

/**
 * Plateau du jeu, compose de Cases et de Robots <br>
 * <br>
 * Strategie : La generation du Plateau cree des cases et attribue a chaque case
 * les case next. Il doit egalement creer au moins une case de type
 * CaseObjectif. <br>
 * <br>
 * S'il y a plusieurs Robots : un Robot place sur une Case du plateau redefinira
 * les CaseNext de toutes les cases horizontales et verticales.<br>
 * <br>
 * Les murs sont representes par des Cases vides mais ces cases vides
 * n'apparaissent pas dans tabCases. <br>
 * Graphiquement : Les murs seront retrouves en faisant un test a chaque case si
 * ses next sont vides, si oui, un mur apparaitra a cote de la case.
 * 
 * @author Dorian
 *
 */
public class Plateau {

	// ////// ATTRIBUTS ///////////

	private int taille = 16;
	private Case[][] tabCases;
	private Robot[] tabRobots;
	private int[] objectifPos = new int[2];

	// ///// CONSTRUCTEURS /////////

	/**
	 * Constructeur Vide : Cree un plateau de taille 16 et alloue la memoire pour
	 * tabCases et tabRobots
	 */
	public Plateau() {
		taille = 16;
		tabCases = new Case[16][16];
		tabRobots = new Robot[4];
		for (int i = 0; i < 4; i++) {
			tabRobots[i] = new Robot();
		}
		tabRobots[0].setCouleur('B');
	}

	/**
	 * Cree un plateau de taille n et alloue la memoire pour tabCases et tabRobots
	 * 
	 * @param n
	 */
	public Plateau(int n) {
		taille = n;
		tabCases = new Case[n][n];
		tabRobots = new Robot[4];
		for (int i = 0; i < 4; i++) {
			tabRobots[i] = new Robot();
		}
		tabRobots[0].setCouleur('B');
	}

	// ///// GETTERS AND SETTERS ////////

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

	public int[] getObjectifPos() {
		return objectifPos;
	}

	public void setObjectifPos(int[] objectifPos) {
		this.objectifPos = objectifPos;
	}

	public CaseObjectif getObjectif() {
		return (CaseObjectif) tabCases[objectifPos[0]][objectifPos[1]];
	}

	public void setObjectif(CaseObjectif objectif) {
		this.tabCases[objectifPos[0]][objectifPos[1]] = objectif;
	}

	// ///// METHODES /////////

	/**
	 * Genere un plateau sans aucun murs
	 */
	public void genererPlateauSansMur() {
		Case caseNonVide = Case.creerCase();
		Case caseVide = new Case();

		Case[] tVide = new Case[4];
		for (int i = 0; i < 4; i++) {
			tVide[i] = caseVide;
		}

		Case[] tNonVide = new Case[4];
		for (int i = 0; i < 4; i++) {
			tNonVide[i] = caseNonVide;
		}

		tabCases = new Case[taille][taille];

		// Ajout du plateau global
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tabCases[i][j] = new Case();
				tabCases[i][j].setCaseNext(tNonVide);

			}
		}

		// Ajout du bord du plateau haut
		for (int j = 0; j < taille; j++) {
			tabCases[0][j].setCaseNextHaut(caseVide);
		}

		// Ajout du bord du plateau gauche
		for (int i = 0; i < taille; i++) {
			tabCases[i][0].setCaseNextGauche(caseVide);
		}

		// Ajout du bord du plateau droit
		for (int i = 0; i < taille; i++) {
			tabCases[i][taille - 1].setCaseNextDroite(caseVide);
		}

		// Ajout du bord du plateau bas
		for (int j = 0; j < taille; j++) {
			tabCases[taille - 1][j].setCaseNextBas(caseVide);
		}
		insererCoordonees();
		configureCaseNextPlateau();
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
	 * Redefinit les lignes en fonction du deplacement d'un robot <br>
	 * Fonction appellee a chaque deplacement de robot
	 * 
	 * @param x1
	 *            la ligne de depart du robot
	 * @param y1
	 *            la colonne de depart du robot
	 * @param x2
	 *            la ligne d'arrivee du robot
	 * @param y2
	 *            la colonne d'arrivee du robot
	 */
	public void redefinirLignes(int x1, int y1, int x2, int y2) {
		supprimerMursRobot(x1, y1);
		ajouterMursRobot(x2, y2);
	}

	/**
	 * Supprime les murs autours d'un robot (ancienne position du robot)
	 * 
	 * @param x1
	 *            L'ancienne posX du robot
	 * @param y1
	 *            L'ancienne PosY du robot
	 */
	public void supprimerMursRobot(int x1, int y1) {
		int k;
		// --------- On retire le robot : redefinition des caseNext

		// Cases de gauche
		if (!tabCases[x1][y1].getCaseNextGauche().estVide()) {
			k = 1;
			while (!tabCases[x1][y1 - k].getCaseNextGauche().estVide()) {
				tabCases[x1][y1 - k].setCaseNextDroite(tabCases[x1][y1]);
				k++;
			}
			tabCases[x1][y1 - k].setCaseNextDroite(tabCases[x1][y1]);
		}

		// Cases de droite
		if (!tabCases[x1][y1].getCaseNextDroite().estVide()) {
			k = 1;
			while (!tabCases[x1][y1 + k].getCaseNextDroite().estVide()) {
				tabCases[x1][y1 + k].setCaseNextGauche(tabCases[x1][y1]);
				k++;
			}
			tabCases[x1][y1 + k].setCaseNextGauche(tabCases[x1][y1]);
		}

		// Cases du haut
		if (!tabCases[x1][y1].getCaseNextHaut().estVide()) {
			k = 1;
			while (!tabCases[x1 - k][y1].getCaseNextHaut().estVide()) {
				tabCases[x1 - k][y1].setCaseNextBas(tabCases[x1][y1]);
				k++;
			}
			tabCases[x1 - k][y1].setCaseNextBas(tabCases[x1][y1]);
		}

		// Cases du bas
		if (!tabCases[x1][y1].getCaseNextBas().estVide()) {
			k = 1;
			while (!tabCases[x1 + k][y1].getCaseNextBas().estVide()) {
				tabCases[x1 + k][y1].setCaseNextHaut(tabCases[x1][y1]);
				k++;
			}
			tabCases[x1 + k][y1].setCaseNextHaut(tabCases[x1][y1]);
		}
	}

	/**
	 * Ajoute les murs autours d'un robot
	 * 
	 * @param x
	 *            la posX du robot
	 * @param y
	 *            la posY du robot
	 */
	public void ajouterMursRobot(int x, int y) {
		// -------- Definition des casesNext autours de la nouvelle position du Robot
		int k;
		// Cases de gauche
		if (!tabCases[x][y].getCaseNextGauche().estVide() && y > 0) {
			k = 2;
			tabCases[x][y - 1].setCaseNextDroite(new Case());
			if (y > 1) {
				while (!tabCases[x][y - k].getCaseNextGauche().estVide()) {
					tabCases[x][y - k].setCaseNextDroite(tabCases[x][y - 1]);
					k++;
				}
				tabCases[x][y - k].setCaseNextDroite(tabCases[x][y - 1]);
			}
		}

		// Cases de droite
		if (!tabCases[1][y].getCaseNextDroite().estVide() && y < taille - 1) {
			k = 2;
			tabCases[x][y + 1].setCaseNextGauche(new Case());
			if (y < taille - 2) {
				while (!tabCases[x][y + k].getCaseNextDroite().estVide()) {
					tabCases[x][y + k].setCaseNextGauche(tabCases[x][y + 1]);
					k++;
				}
				tabCases[x][y + k].setCaseNextGauche(tabCases[x][y + 1]);
			}
		}

		// Cases du haut
		if (!tabCases[x][y].getCaseNextHaut().estVide() && x > 0) {
			k = 2;
			tabCases[x - 1][y].setCaseNextBas(new Case());
			if (x > 1) {
				while (!tabCases[x - k][y].getCaseNextHaut().estVide()) {
					tabCases[x - k][y].setCaseNextBas(tabCases[x - 1][y]);
					k++;
				}
				tabCases[x - k][y].setCaseNextBas(tabCases[x - 1][y]);
			}
		}

		// Cases du bas
		if (!tabCases[x][y].getCaseNextBas().estVide() && x < taille - 1) {
			k = 2;
			tabCases[x + 1][y].setCaseNextHaut(new Case());
			if (x < taille - 2) {
				while (!tabCases[x + k][y].getCaseNextBas().estVide()) {
					tabCases[x + k][y].setCaseNextHaut(tabCases[x + 1][y]);
					k++;
				}
				tabCases[x + k][y].setCaseNextHaut(tabCases[x + 1][y]);
			}
		}
	}

	/**
	 * Place le robot numero num de facon random et retourne ses coordonees
	 * 
	 * @param num
	 *            le numero du Robots
	 * @return le tableau de Robots
	 */
	public int[] placerRobotRandom(int num) {
		Random r = new Random();
		int i = r.nextInt(taille);
		int j = r.nextInt(taille);
		this.tabRobots[num].setCaseActuelle(tabCases[i][j]);
		this.tabCases[i][j].affecterRobot(tabRobots[num]);
		int[] tab = { i, j };
		return tab;
	}

	public void supprimerRobot(int num) {
		tabRobots[num].getCaseActuelle().supprimerRobot();
	}

	/**
	 * Insere les coordonees de chaque Case dans Case.posX et Case.posY
	 */
	public void insererCoordonees() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				this.tabCases[i][j].setPos(i, j);
			}
		}
	}

	/**
	 * Ajoute un mur sur le plateau aux coordonnes (i,j) et sur le haut de la case
	 * et redefinie toutes les autres cases pour pouvoir interagir avec ce nouveau
	 * mur <br>
	 * 
	 * @param i
	 *            la ligne
	 * @param j
	 *            la colone
	 */
	public void ajouterMurHaut(int i, int j) {
		tabCases[i][j].setCaseNextHaut(new Case());
		if (i > 0)
			tabCases[i - 1][j].setCaseNextBas(new Case());

		if (i > 1) {
			if (!tabCases[i - 1][j].getCaseNextHaut().estVide()) {
				int k = 2;
				while (!tabCases[i - k][j].getCaseNextHaut().estVide()) {
					tabCases[i - k][j].setCaseNextBas(tabCases[i - 1][j]);
					k++;
				}
				tabCases[i - k][j].setCaseNextBas(tabCases[i - 1][j]);
			}
		}

		if (i < taille - 1) {
			if (!tabCases[i][j].getCaseNextBas().estVide()) {
				int k = 1;
				while (!tabCases[i + k][j].getCaseNextBas().estVide()) {
					tabCases[i + k][j].setCaseNextHaut(tabCases[i][j]);
					k++;
				}
				tabCases[i + k][j].setCaseNextHaut(tabCases[i][j]);
			}
		}
	}

	/**
	 * Ajoute un mur sur le plateau aux coordonnees (i,j) et sur le bas de la case
	 * et redefinie toutes les autres cases pour pouvoir interagir avec ce nouveau
	 * mur <br>
	 * 
	 * @param i
	 *            la ligne
	 * @param j
	 *            la colone
	 */
	public void ajouterMurBas(int i, int j) {
		tabCases[i][j].setCaseNextBas(new Case());
		if (i < taille - 1)
			tabCases[i + 1][j].setCaseNextHaut(new Case());

		if (i > 0) {
			if (!tabCases[i][j].getCaseNextHaut().estVide()) {
				int k = 1;
				while (!tabCases[i - k][j].getCaseNextHaut().estVide()) {
					tabCases[i - k][j].setCaseNextBas(tabCases[i][j]);
					k++;
				}
				tabCases[i - k][j].setCaseNextBas(tabCases[i][j]);
			}
		}

		if (i < taille - 2) {
			if (!tabCases[i + 1][j].getCaseNextBas().estVide()) {
				int k = 2;
				while (!tabCases[i + k][j].getCaseNextBas().estVide()) {
					tabCases[i + k][j].setCaseNextHaut(tabCases[i + 1][j]);
					k++;
				}
				tabCases[i + k][j].setCaseNextHaut(tabCases[i + 1][j]);
			}
		}
	}

	/**
	 * Ajoute un mur sur le plateau aux coordonnees (i,j) et sur la gauche de la
	 * case et redefinie toutes les autres cases pour pouvoir interagir avec ce
	 * nouveau mur
	 * 
	 * TODO DEBBUGGING : Traversable par la droite
	 * 
	 * @param i
	 *            la ligne
	 * @param j
	 *            la colone
	 * 
	 * 
	 */
	public void ajouterMurGauche(int i, int j) {
		tabCases[i][j].setCaseNextGauche(new Case());
		if (j > 0)
			tabCases[i][j - 1].setCaseNextDroite(new Case());

		if (j > 1) {
			if (!tabCases[i][j - 1].getCaseNextGauche().estVide()) {
				int k = 2;
				while (!tabCases[i][j - k].getCaseNextGauche().estVide()) {
					tabCases[i][j - k].setCaseNextDroite(tabCases[i][j - 1]);
					k++;
				}
				tabCases[i][j - k].setCaseNextDroite(tabCases[i][j - 1]);
			}
		}

		if (j < taille - 1) {
			if (!tabCases[i][j].getCaseNextDroite().estVide()) {
				int k = 1;
				while (!tabCases[i][j + k].getCaseNextDroite().estVide()) {
					tabCases[i][j + k].setCaseNextGauche(tabCases[i][j]);
					k++;
				}
				tabCases[i][j + k].setCaseNextGauche(tabCases[i][j]);
			}
		}
	}

	/**
	 * Ajoute un mur sur le plateau aux coordonnees (i,j) et sur la droite de la
	 * case et redefinie toutes les autres cases pour pouvoir interagir avec ce
	 * nouveau mur TODO : DEBUGGING !!!
	 * 
	 * @param i
	 *            la ligne
	 * @param j
	 *            la colone
	 * 
	 * 
	 */
	public void ajouterMurDroite(int i, int j) {
		tabCases[i][j].setCaseNextDroite(new Case());
		if (j < taille - 1)
			tabCases[i][j + 1].setCaseNextGauche(new Case());

		if (j > 1) {
			if (!tabCases[i][j].getCaseNextGauche().estVide()) {
				int k = 1;
				while (!tabCases[i][j - k].getCaseNextGauche().estVide()) {
					tabCases[i][j - k].setCaseNextDroite(tabCases[i][j]);
					k++;
				}
				tabCases[i][j - k].setCaseNextDroite(tabCases[i][j]);
			}
		}

		if (j < taille - 2) {
			// if (!tabCases[i][j].getCaseNextDroite().estVide()) {
			// int k = 1;
			// while (!tabCases[i][j + k].getCaseNextDroite().estVide()) {
			// tabCases[i][j + k].setCaseNextGauche(tabCases[i][j + 1]);
			// k++;
			// }
			// tabCases[i][j + k].setCaseNextGauche(tabCases[i][j + 1]);
			// }
			ajouterMurGauche(i, j + 1);
		}

	}

	/**
	 * Configure les casesNext du plateau <br>
	 * Prereq : Le plateau doit contenir des cases qui ont comme caseNext soit une
	 * case Vide, soit une Case valide
	 */
	public void configureCaseNextPlateau() {
		int k;
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (!tabCases[i][j].getCaseNextHaut().estVide()) {
					// Ajout des CasesNext vers le haut
					k = 0;
					while (!tabCases[i - k][j].getCaseNextHaut().estVide()) {
						k++;
					}
					tabCases[i][j].setCaseNextHaut(tabCases[i - k][j]);
				}

				if (!tabCases[i][j].getCaseNextBas().estVide()) {
					// Ajout des CasesNext vers le bas
					k = 0;
					while (!tabCases[i + k][j].getCaseNextBas().estVide()) {
						k++;
					}
					tabCases[i][j].setCaseNextBas(tabCases[i + k][j]);
				}

				if (!tabCases[i][j].getCaseNextGauche().estVide()) {
					// Ajout des CasesNext vers la gauche
					k = 0;
					while (!tabCases[i][j - k].getCaseNextGauche().estVide()) {
						k++;
					}
					tabCases[i][j].setCaseNextGauche(tabCases[i][j - k]);
				}

				if (!tabCases[i][j].getCaseNextDroite().estVide()) {
					// Ajout des CasesNext vers la Droite
					k = 0;
					while (!tabCases[i][j + k].getCaseNextDroite().estVide()) {
						k++;
					}
					tabCases[i][j].setCaseNextDroite(tabCases[i][j + k]);
				}
			}
		}
	}

	/**
	 * Ajoute un mur a GAUCHE et en HAUT de la case definie en (i,j)
	 * 
	 * @param i
	 *            la ligne de la case
	 * @param j
	 *            la colonne de la case
	 */
	public void ajouterCoinGH(int i, int j) {
		ajouterMurHaut(i, j);
		ajouterMurGauche(i, j);
	}

	/**
	 * Ajoute un mur en HAUT et a DROITE de la case definie en (i,j)
	 *
	 * @param i
	 *            la ligne de la case
	 * @param j
	 *            la colonne de la case
	 */
	public void ajouterCoinHD(int i, int j) {
		ajouterMurHaut(i, j);
		ajouterMurDroite(i, j);
	}

	/**
	 * Ajoute un mur a DROITE et en BAS de la case definie en (i,j)
	 * 
	 * @param i
	 *            la ligne de la case
	 * @param j
	 *            la colonne de la case
	 */
	public void ajouterCoinDB(int i, int j) {
		ajouterMurDroite(i, j);
		ajouterMurBas(i, j);
	}

	/**
	 * Ajoute un mur en BAS et a DROITE de la case definie en (i,j)
	 * 
	 * @param i
	 *            la ligne de la case
	 * @param j
	 *            la colonne de la case
	 */
	public void ajouterCoinBG(int i, int j) {
		ajouterMurBas(i, j);
		ajouterMurGauche(i, j);
	}

	/**
	 * Genere un plateau grace a un tableau d'int qui definie les murs :<br>
	 * - 1 : Un angle Gauche-Haut<br>
	 * - 2 : Un angle Haut-Droite<br>
	 * - 3 : Un angle Droite-Bas<br>
	 * - 4 : Un angle Bas-Gauche<br>
	 * - Autre : Une case normale, sans aucuns murs<br>
	 * <br>
	 * Prereq : Le tableau doit etre de la meme taille que le plateau
	 * 
	 * @param murs
	 *            le tableau des murs
	 * @param posXObjectif
	 *            : La position x de l'objectif
	 * @param posYObjectif
	 *            : La position y de l'objectif
	 * 
	 * 
	 */
	public void genererPlateau(int[][] murs, int posXObjectif, int posYObjectif) {
		genererPlateauSansMur();
		ajouterObjectif(posXObjectif, posYObjectif);
		for (int i = 0; i < murs.length; i++) {
			for (int j = 0; j < murs.length; j++) {
				if (murs[i][j] == 1)
					ajouterCoinGH(i, j);
				if (murs[i][j] == 2)
					ajouterCoinHD(i, j);
				if (murs[i][j] == 3)
					ajouterCoinDB(i, j);
				if (murs[i][j] == 4)
					ajouterCoinBG(i, j);
			}
		}
		// System.out.println(tabCases[objectifPos[0]][objectifPos[1]].getCaseNextDroite().getCaseNextGauche().getPosX());
	}

	/**
	 * Ajoute l'objectif
	 * 
	 * @param i
	 *            la ligne
	 * @param j
	 *            la colone
	 */
	public void ajouterObjectif(int i, int j) {
		CaseObjectif c = new CaseObjectif();
		c.setCaseNextGauche(tabCases[i][j].getCaseNextGauche());
		c.setCaseNextHaut(tabCases[i][j].getCaseNextHaut());
		c.setCaseNextDroite(tabCases[i][j].getCaseNextDroite());
		c.setCaseNextBas(tabCases[i][j].getCaseNextBas());
		c.setPos(i, j);
		tabCases[i][j] = c;
		objectifPos[0] = i;
		objectifPos[1] = j;
	}

	// ////// METHODES POUR LA GENERATION D'UN PLATEAU RANDOM /////////

	/**
	 * Genere un plateau de taille n de maniere Aleatoire : 12% de chances qu'une
	 * case ait au moins un mur (donc 3 % par cote)<br>
	 * Utilise :<br>
	 * 
	 * 
	 * @param n
	 * @return le plateau genere
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

		// corrige les tableaux de booleens pour appliquer les murs des deux
		// cotes
		murDesDeuxCotes(murHaut, murBas, murGauche, murDroite);

		// Creation de cases next temporaire pour toutes les autres cases
		Case caseNonVide = Case.creerCase();
		Case caseVide = new Case();

		// Place les murs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Case c = new Case();

				// Met soit un mur en HAUT de la case courante, soit une case
				// non vide
				if (murHaut[i][j])
					c.setCaseNextHaut(caseVide);
				else
					c.setCaseNextHaut(caseNonVide);

				// Met soit un mur a GAUCHE de la case courante, soit une case
				// non vide
				if (murGauche[i][j])
					c.setCaseNextGauche(caseVide);
				else
					c.setCaseNextGauche(caseNonVide);

				// Met soit un mur a DROITE de la case courante, soit une case
				// non vide
				if (murDroite[i][j])
					c.setCaseNextDroite(caseVide);
				else
					c.setCaseNextDroite(caseNonVide);

				// Met soit un mur en BAS de la case courante, soit une case non
				// vide
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
	 * Reevalue les tableaux de murs pour qu'un mur soit present sur les deux cotes
	 * : <br>
	 * ex : Un murDroite sur une Case est un murGauche sur la case de Droite
	 * 
	 * @param murHaut
	 * @param murBas
	 * @param murGauche
	 * @param murDroite
	 *            <br>
	 *            prereq : tous les tableaux doivent �tre de m�me taille
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
	 * Configure les CaseNext en fontion d'un tableau o� les murs sont deja
	 * initialises : Les caseNext actuelles sont soit null, soit une case Vide) <br>
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
	 * Determine de facon aleatoire quelle case aura un mur. Une case a 5 % de
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
	 * Ajoute de facon aleatoire un objectif sur le plateau Note : L'objectif doit
	 * se trouver a cote d'un mur sinon la partie est impossible
	 */
	public void ajouterObjectifRandom() {
		Random r = new Random();
		int i = r.nextInt(taille);
		int j = r.nextInt(taille);

		CaseObjectif c = new CaseObjectif();
		c.setCaseNext(tabCases[i][j].getCaseNext());

		tabCases[i][j] = c;
		objectifPos[0] = i;
		objectifPos[1] = j;
		ajouterMursRandomObjectif();

	}

	/**
	 * Ajoute un mur formant un angle sur l'objectif
	 */
	public void ajouterMursRandomObjectif() {
		int i = objectifPos[0];
		int j = objectifPos[1];
		Random r = new Random();
		int random = r.nextInt(4);

		if (random == 0) {
			ajouterMurGauche(i, j);
			ajouterMurHaut(i, j);
		} else if (random == 1) {
			ajouterMurHaut(i, j);
			ajouterMurDroite(i, j);
		} else if (random == 2) {
			ajouterMurDroite(i, j);
			ajouterMurBas(i, j);
		} else if (random == 3) {
			ajouterMurBas(i, j);
			ajouterMurGauche(i, j);
		}
	}

}
