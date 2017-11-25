package ricochetRobot;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Une Partie de Ricochet-Robots.<br>
 * Le jeu se deroule sur un plateau d’un nombre n*n de cases et de murs. Sur les
 * cases sont places des pions appeles “robots” et des objectifs. <br>
 * Le jeu se deroule en tours. Au debut du tour, on indique un objectif. Le but
 * est de deplacer le robot de la couleur de l’objectif sur celui-ci en un
 * minimum de mouvements (Deplacer les autres robots compte egalement pour 1
 * coup). <br>
 * Un robot ne peut se deplacer que tout droit jusqu’a atteindre un obstacle (un
 * bord du plateau, un mur ou un autre robot). <br>
 * Le joueur ayant trouve le plus rapidement la solution annonce son nombre de
 * mouvements et retourne le sablier. Si les autres joueurs ne trouvent pas de
 * meilleures solutions avant la fin du temps imparti, il remporte la manche.
 * <br>
 * <br>
 * Strategie : La partie genere un plateau et lance les differentes actions pour
 * permettre a 1 ou plusieurs joueurs de jouer au jeu.
 * 
 * @author Dorian
 *
 *
 *
 *
 */
public abstract class Partie extends FlecheClavierListener {

	/////// ATTRIBUTS //////////

	private int idPartie;
	private Plateau plateau;
	private Chrono chrono;
	private int nbCoups;
	private int nbVictoire = 0;
	private int robotSelectionne = 0;

	/**
	 * 0 : Partie arretee 1 : Partie en cours 2 : Pause
	 */
	private int etatPartie = 0;

	//////// CONSTRUCTEURS ///////
	public Partie() {
		super();
		idPartie = 1;
	}

	//////// GETTERS AND SETTERS ///////

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Chrono getChrono() {
		return chrono;
	}

	public void setChrono(Chrono chrono) {
		this.chrono = chrono;
	}

	public int getNbCoups() {
		return nbCoups;
	}

	public void setNbCoups(int nbCoups) {
		this.nbCoups = nbCoups;
	}

	public int getNbVictoire() {
		return nbVictoire;
	}

	public void setNbVictoire(int nbVictoire) {
		this.nbVictoire = nbVictoire;
	}

	public int getIdPartie() {
		return idPartie;
	}

	public int getEtatPartie() {
		return etatPartie;
	}

	public void setEtatPartie(int etatPartie) {
		this.etatPartie = etatPartie;
	}

	public int getRobotSelectionne() {
		return robotSelectionne;
	}

	public void setRobotSelectionne(int robotSelectionne) {
		this.robotSelectionne = robotSelectionne;
	}

	////////// METHODES /////////

	public String toString() {
		return plateau.toString();
	}

	///////// GESTION DE LA PARTIE ////////
	/**
	 * Lance la partie
	 */
	public void lancerPartie() {
		etatPartie = 1;
		lancer();
	}

	/**
	 * Met en pause la Partie
	 */
	public void pausePartie() {
		etatPartie = 2;
	}

	/**
	 * Relance la partie
	 */
	public void reprendrePartie() {
		etatPartie = 1;
	}

	/**
	 * Arrete completement la partie. Rien n'est stocke
	 */
	public void arreterPartie() {
		stop();
		System.exit(0);
	}

	/**
	 * En cas de victoire, cette fonction est appelee
	 */
	public void victoire() {
		nbVictoire++;
		etatPartie = 0;
		System.out.println(
				"Bien joue !\nNombre de victoires : " + nbVictoire + "\nVoulez vous refaire une manche ? (O / N)\n");
	}

	/**
	 * LISTENER : <br>
	 * Ce listener se comporte de 3 facons differentes : lors d'une partie arretee,
	 * lors d'une partie lancee, lors d'une pause.<br>
	 * Si le joueur appuie sur ECHAP, la partie s'arrete immediatement. <br>
	 * <br>
	 * Lors d'une Partie : <br>
	 * ==> Si le joueur appuie sur une fleche directionelle, Le Robot est deplacee
	 * dans la direction indiquee <br>
	 * ==> Si le joueur Appuie sur P : la partie est mise en pause <br>
	 * <br>
	 * Lors d'une Pause : <br>
	 * ==> Si le joueur appuie sur P, la partie reprend <br>
	 * ==> Si le joueur appuie sur R, une nouvelle manche est lancee<br>
	 * <br>
	 * Lorsque la partie est arrettee :<br>
	 * ==> Si le joueur appuie sur O, une nouvelle manche est lancee ==> Si le
	 * joueur appuie sur N, La partie se termine et se ferme.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ESCAPE) {
			arreterPartie();
		}
		if (etatPartie == 1) {
			if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p')
				pausePartie();

			int deplacement = 0;
			if (code == KeyEvent.VK_DOWN) {
				deplacement = plateau.getTabRobots()[robotSelectionne].deplacerRobotBas();
			} else if (code == KeyEvent.VK_UP) {
				deplacement = plateau.getTabRobots()[robotSelectionne].deplacerRobotHaut();
			} else if (code == KeyEvent.VK_LEFT) {
				deplacement = plateau.getTabRobots()[robotSelectionne].deplacerRobotGauche();
			} else if (code == KeyEvent.VK_RIGHT) {
				deplacement = plateau.getTabRobots()[robotSelectionne].deplacerRobotDroite();
			}
			if (deplacement == 1) {
				nbCoups++;
				update();
			}
			if (plateau.getObjectif().reussite()) {
				victoire();
			}
		} else if (etatPartie == 2) {
			if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p')
				reprendrePartie();
			else if (e.getKeyChar() == 'r' || e.getKeyChar() == 'r')
				mainPartieAux();
		} else if (etatPartie == 0) {
			if (e.getKeyChar() == 'O' || e.getKeyChar() == 'o') {
				etatPartie = 1;
				mainPartieAux();
			}
			if (e.getKeyChar() == 'N' || e.getKeyChar() == 'n')
				arreterPartie();
		}

	}

	//////////// CREATION ET MAJ DE LA PARTIE ///////////

	/**
	 * Cree une partie en generant un plateau, met le compteur de coups et l'etat de
	 * la partie a 0 Place les robots; Place un objectif; Appelle lancerPartie();
	 */
	public abstract void creerPartie();

	/**
	 * Met a jour l'interface graphique / terminal pour montrer la nouvelle
	 * disposition du plateau
	 */
	public abstract void update();

	/**
	 * 
	 * Permet de lancer une partie avec toute les fonctionnalites necessaires:
	 * Appelle creerPartie(); Appelle lancerPartie();
	 *
	 */
	protected abstract void mainPartieAux();

	/**
	 * Fonction appelee dans le main(String[] args) Instancie une nouvelle partie
	 * puis appelle mainPartieAux():
	 */
	public static void mainPartie() {

	}

	/////// RESOLUTION DU JEU ///////

	/**
	 * @param regardee
	 *            : Case
	 * @return True si l'une des voisine de regardee est de type CaseObjectif
	 */
	protected static boolean objectifVoisin(Case regardee) {
		boolean check = false;
		for (int i = 0; i < 4; i++) {
			if (regardee.getCaseNext(i) instanceof CaseObjectif) {
				check = true;
			}
		}
		return check;
	}

	// SERT A RIEN JE PENSE. Et surtout, très complexe.
	// /**
	// * Donne la matrice correspondant au graphe du plateau avec 1 si la case est
	// * voisine, 0 sinon.
	// */
	// protected int[][] mat() {
	// int taillePlateau = plateau.getTaille();
	// Case[][] tabCases = plateau.getTabCases();
	// int tailleMat = taillePlateau * taillePlateau;
	// int[][] mat = new int[tailleMat][tailleMat];
	// for (int i = 0; i < taillePlateau; i++) {
	// for (int j = 0; j < taillePlateau; j++) {
	// mat[i][j] = 0;
	// }
	// }
	// for (int i = 0; i < taillePlateau; i++) {
	// for (int j = 0; j < taillePlateau; j++) {
	// for (int k = 0; k < 4; k++) {
	// mat[tabCases[i][j].getPosX() * (taillePlateau - 1)
	// + tabCases[i][j].getPosY()][tabCases[i][j].getCaseNext(k).getPosX() *
	// (taillePlateau - 1)
	// + tabCases[i][j].getCaseNext(k).getPosY()] = 1;
	// }
	// }
	// }
	// return mat;
	// }

	/**
	 * Avec un seul robot
	 */
	protected void solve1() {
		LinkedList<Case> marked = new LinkedList<Case>(); // Création de la liste des cases "marquées en vert"
		LinkedList<Case> checked = new LinkedList<Case>(); // Création de la liste des cases "marquées en rouge"
		LinkedList<Integer> counter = new LinkedList<Integer>(); // Compte les coups pour atteindre les cases de marked.
		// marked et counter sont "liées" par l'index, c'est à dire qu'il faut
		// counter[i] coups pour atteindre marked[i] depuis la case d'origine du robot
		LinkedList<LinkedList<Integer>> path = new LinkedList<LinkedList<Integer>>(); // Contient les coups pour atteindre les cases de marked.
		// Les coups sont des entiers : 0 = gauche, 1 = haut, 2 = droite et 3 = bas
		// marked et path sont "liées" par l'index, c'est à dire qu'il faut réaliser les coups de
		// path[i] coups pour atteindre marked[i] depuis la case d'origine du robot
		marked.add(plateau.getTabRobots()[0].getCaseActuelle()); // La 1ere case marquée est celle où se trouve le robot
		counter.add(0); // Et il faut 0 coup pour l'atteindre
		//A ESSAYER
		//path.add(new LinkedList<Integer>());
		path.add(counter); // Le premier coup de tous les chemins est faut et devra être supprimé. On ne le fera qu'à la fin si on trouve l'objectif
		Case objectif = plateau.getObjectif();
		boolean found = false;
		int nbSteps; // Contiendra le nombre de coups pour aller de la case du robot à l'objectif
		while (!found && !marked.isEmpty()) { // Tant qu'on a des cases marquées et qu'on n'a pas trouvé l'objectif...
			Case current = marked.getFirst(); // On s'intéresse à la première case marquée
			for (int i = 0; i < 4; i++) { // Et à ses cases suivantes
				Case nextI = current.getCaseNext(i);
				if (nextI.equals(objectif)) { // Si on l'objectif est une case suivante de la case courrante...
					found = true; // On l'a trouvé
					LinkedList<Integer> nextPath = new LinkedList<Integer>(path.peek()); //On initialise le chemin jusqu'à l'objectif en utilisant le chemin jusqu'à courant
					nextPath.add(i); // On ajoute à ce chemin le coup pour passer de courant à l'objectif 
					path.add(nextPath); // On place ce chemin à la fin de la liste des chemins
					nbSteps = counter.get(marked.indexOf(current)) + 1; // On peut l'atteindre en autant de coup que
					// pour atteindre la case courrante +1
				} else {
					if (!marked.contains(nextI) && !checked.contains(nextI)) { // Sinon, si la case suivante regarnée
						// n'est ni "verte" ni "rouge"
						marked.add(nextI); // On l'ajoute à la liste des cases marquées
						counter.add(counter.peek() + 1); // Et on lui attribue le nombre de coup pour atteindre la
						// case courante +1
						LinkedList<Integer> nextPath = new LinkedList<Integer>(path.peek()); //On initialise le chemin jusqu'à cette case suivante en utilisant le chemin jusqu'à courant
						nextPath.add(i); // On ajoute à ce chemin le coup pour passer de courant à cette case suivante 
						path.add(nextPath); // On place ce chemin à la fin de la liste des chemins
					}

				}
			}
			checked.add(marked.remove()); // La case courante n'est plus verte, mais rouge
			counter.remove(); // On retire le nombre de coup pour la case courante
			path.remove(); // On retire le chemin jusqu'à la case courante de la liste des chemins.
		}
		// end while
	}

}
