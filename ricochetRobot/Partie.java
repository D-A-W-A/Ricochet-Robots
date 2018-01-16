package ricochetRobot;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;

/**
 * Une Partie de Ricochet-Robots.<br>
 * Le jeu se deroule sur un plateau dun nombre n*n de cases et de murs. Sur les
 * cases sont places des pions appeles robots et des objectifs. <br>
 * Le jeu se deroule en tours. Au debut du tour, on indique un objectif. Le but
 * est de deplacer le robot de la couleur de l'objectif sur celui-ci en un
 * minimum de mouvements (Deplacer les autres robots compte egalement pour 1
 * coup). <br>
 * Un robot ne peut se deplacer que tout droit jusqu a atteindre un obstacle (un
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
	private Chrono chrono = new Chrono();
	private int nbCoups;
	private static int nbVictoire = 0;
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
	 * Sauvegarde les cases du plateau de la partie.
	 */
	public Plateau sauvCases() {
		return new Plateau(this.plateau);
	}

	/**
	 * Restaure les cases du plateau passé en paramètre
	 */
	public void restaureCases(Plateau p) {
		this.plateau.setTabCases(p.getTabCases());
	}

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
	 * == Si le joueur appuie sur une fleche directionelle, Le Robot est deplacee
	 * dans la direction indiquee <br>
	 * == Si le joueur Appuie sur P : la partie est mise en pause <br>
	 * <br>
	 * Lors d'une Pause : <br>
	 * == Si le joueur appuie sur P, la partie reprend <br>
	 * == Si le joueur appuie sur R, une nouvelle manche est lancee<br>
	 * <br>
	 * Lorsque la partie est arrettee :<br>
	 * == Si le joueur appuie sur O, une nouvelle manche est lancee == Si le joueur
	 * appuie sur N, La partie se termine et se ferme.
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

	/**
	 * Gestion des boutons du menu de gauche
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton b = (JButton) (e.getSource());
		System.out.println("Clic de : " + b.getText());
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
	 * Prend la solution sous forme de LinkedList et la retourne sous forme de
	 * tableau. ATTENTION : La methode est non destructrice de la liste en parametre
	 * mais pourra etre lourde pour les longues solutions.
	 * 
	 * @return La solution sous forme de tableau
	 */
	protected int[] linkedListToTab(LinkedList<Integer> solution) {
		if (solution.isEmpty()) {
			return new int[0];
		} else {
			int[] pathArray = new int[solution.size()];
			for (int i = 0; i < solution.size(); i++) {
				pathArray[i] = solution.get(i);
			}
			return pathArray;
		}
	}

	/**
	 * Affiche la solution textuellement.
	 */
	protected void displaySolution() {
		System.out.println(toStringSolution1());
	}

	/**
	 * Appelle solve1 et affiche renvoie une chaine de caractere de la solution
	 */
	public String toStringSolution1() {
		LinkedList<Integer> solution = new LinkedList<>(solve1());
		if (solution.size() == 0) {
			return ("L'objectif n'est pas atteignable");
		} else {
			StringBuilder s = new StringBuilder("La solution est : ");
			s.append(toStringChemin(solution));
			return s.toString();
		}
	}

	public String numberToLetters(int i) {
		String s = "";
		switch (i) {
		case 0:
			s = "Bleu gauche";
			break;
		case 1:
			s = "Bleu haut";
			break;
		case 2:
			s = "Bleu droite";
			break;
		case 3:
			s = "Bleu bas";
			break;
		case 4:
			s = " gauche";
			break;
		case 5:
			s = " haut";
			break;
		case 6:
			s = " droite";
			break;
		case 7:
			s = " bas";
			break;
		}
		return s;
	}

	public String toStringSolution2() {
		LinkedList<Integer> solution = new LinkedList<Integer>(solve2());
		if (solution.size() == 0) {
			return ("L'objectif n'est pas atteignable");
		} else {
			StringBuilder s = new StringBuilder("La solution est :");
			while(!(solution.size()>1)) {
				s.append(numberToLetters(solution.removeFirst()));
			}
			s.append(numberToLetters(solution.removeFirst()));
			s.append(".");
			return s.toString();
		}
	}

	public String toStringChemin(LinkedList<Integer> list) {
		int[] solution = linkedListToTab(list);
		if (solution.length == 0) {
			return ("");
		} else {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < solution.length - 1; i++) {
				switch (solution[i]) {
				case 0:
					s.append("gauche, ");
					break;
				case 1:
					s.append("haut, ");
					break;
				case 2:
					s.append("droite, ");
					break;
				case 3:
					s.append("bas, ");
					break;
				}
			}
			switch (solution[solution.length - 1]) { // Pour le dernier coup, on finit la phrase par un point.
			case 0:
				s.append("gauche. ");
				break;
			case 1:
				s.append("haut. ");
				break;
			case 2:
				s.append("droite. ");
				break;
			case 3:
				s.append("bas. ");
				break;
			}
			return s.toString();
		}
	}

	/**
	 * Les coups retournes sont des entiers sous la forme : 0 = gauche, 1 = haut, 2
	 * = droite et 3 = bas Si la liste est vide, l'objectif n'est pas atteignable
	 * 
	 * @return La liste des coups
	 */
	protected LinkedList<Integer> solve1() {
		LinkedList<Case> marked = new LinkedList<Case>();
		LinkedList<Case> checked = new LinkedList<Case>();
		LinkedList<LinkedList<Integer>> path = new LinkedList<LinkedList<Integer>>();

		marked.add(plateau.getTabRobots()[0].getCaseActuelle());
		path.add(new LinkedList<Integer>());

		Case objectif = plateau.getObjectif();
		LinkedList<Integer> finalPath = new LinkedList<Integer>();

		boolean found = false;

		while (!found && !marked.isEmpty()) { // Tant qu'on a pas trouve l'objectif et qu'il y a des cases marquees
			Case current = marked.getFirst(); // On s'interesse a la premiere case de la FIFO marquee
			if (current.equals(objectif)) { // Si c'est l'objectif
				found = true; // On l'a trouvÃ©
				finalPath.addAll(path.peek()); // On recupere le chemin jusqu'a  lui
			} else { // Si c'est pas l'objectif et qu'elle est pas vide
				for (int i = 0; i < 4; i++) { // Pour chacune de ses voisines
					Case nextI = current.getCaseNext(i);
					if (!nextI.estVide() && !checked.contains(nextI) && !marked.contains(nextI)) {
						marked.add(nextI); // On la marque
						LinkedList<Integer> nextPath = new LinkedList<Integer>(path.peek()); // On initialise le chemin
																								// jusqu'a cette case
																								// suivante en utilisant
																								// le chemin jusqu'a
																								// courant
						nextPath.add(i); // On ajoute a ce chemin le coup pour passer de courant a cette case suivante
						path.add(nextPath); // On place ce chemin a la fin de la liste des chemins

					}
				}
			}

			checked.add(current);
			marked.remove();
			path.remove();
		}
		return finalPath;
	}

	protected void deplacements(LinkedList<Integer> liste, Robot robot1, Robot robot2) {
		while (!liste.isEmpty()) {
			if(liste.getFirst()<4) {
				robot1.deplacerRobot(liste.removeFirst());
			}
			else {
				robot2.deplacerRobot(liste.removeFirst()-4);
			}
		}
	}
	
	protected LinkedList<Integer> solve2() {
		Plateau sauvegarde = this.sauvCases(); // C'est la sauvegarde des cases avant de commencer à utiliser
												// l'algorithme.
		Robot robot1 = this.plateau.getTabRobots()[0];
		Robot robot2 = this.plateau.getTabRobots()[1];
		Case objectif = plateau.getObjectif();

		boolean found = false;

		LinkedList<Case[]> marked = new LinkedList<Case[]>();
		LinkedList<Case[]> checked = new LinkedList<Case[]>();
		LinkedList<LinkedList<Integer>> path = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> finalPath = new LinkedList<Integer>();
		//Marquage du premier element
		Case[] casesOrigine = {robot1.getCaseActuelle(), robot2.getCaseActuelle()};
		path.add(new LinkedList<Integer>());
		
		marked.add(casesOrigine);

		while (!found && !marked.isEmpty()) {
			this.restaureCases(sauvegarde);
			this.deplacements(path.getFirst(), robot1, robot2);
			Case[] current = { robot1.getCaseActuelle(), robot2.getCaseActuelle() };
			if (current[0].equals(objectif)) {
				found = true;
				finalPath.addAll(path.peek());
			} else {
				for (int i = 0; i < 8; i++) {
					Case nextI;
					Case[] nextCoupleCases = new Case[2];

					// Construction du next couple de cases
					if (i < 4) { // Si c'est le robot1 qui bouge
						nextI = current[0].getCaseNext(i);
						nextCoupleCases[0] = nextI;
						nextCoupleCases[1] = current[1];
					}

					else { // Si c'est le robot2 qui bouge
						nextI = current[1].getCaseNext(i - 4);
						nextCoupleCases[0] = current[0];
						nextCoupleCases[1] = nextI;
					}

					if (!nextI.estVide() && !checked.contains(nextCoupleCases) && !marked.contains(nextCoupleCases)) {
						marked.add(nextCoupleCases);
						LinkedList<Integer> nextPath = new LinkedList<Integer>(path.peek());
						nextPath.add(i);
						path.add(nextPath);

					}
				}
			}
			checked.add(current);
			marked.remove();
			path.remove();
		}
		this.restaureCases(sauvegarde);
		return finalPath;
	}

	/**
	 * 
	 */
//	protected LinkedList<LinkedList<Integer>> solveFalse() {
//		Plateau sauvegarde = this.sauvCases(); // C'est la sauvegarde des cases avant de commencer à utiliser
//												// l'algorithme.
//		Robot robot2 = this.plateau.getTabRobots()[1];
//
//		LinkedList<Integer> coupsRobot2 = new LinkedList<Integer>(); // Correspond aux déplacements du robot 2 pour
//																		// résoudre la partie en minCoups coups
//		LinkedList<Integer> coupsRobot1 = new LinkedList<Integer>(solve1()); // Correspond aux déplacements du robot 1
//																				// pour résoudre la partie en minCoups
//																				// coups
//		int minCoups = coupsRobot1.size();
//
//		LinkedList<Case> marked = new LinkedList<Case>();
//		LinkedList<Case> checked = new LinkedList<Case>();
//		LinkedList<LinkedList<Integer>> path = new LinkedList<LinkedList<Integer>>();
//
//		marked.add(robot2.getCaseActuelle());
//		path.add(new LinkedList<Integer>());
//
//		while (!marked.isEmpty()) {
//			Case current = marked.getFirst();
//			this.restaureCases(sauvegarde);
//
//			LinkedList<Integer> deplacements = new LinkedList<Integer>(path.getFirst());
//			while (deplacements.size() > 0) {
//				robot2.deplacerRobot(deplacements.pop());
//			}
//
//			LinkedList<Integer> sol1 = new LinkedList<Integer>(solve1());
//			if (sol1.size() == 0) {
//			} else if (sol1.size() + path.getFirst().size() < minCoups) {
//				coupsRobot1.clear();
//				coupsRobot2.clear();
//				coupsRobot1.addAll(sol1);
//				coupsRobot2.addAll(path.getFirst());
//				minCoups = coupsRobot1.size() + coupsRobot2.size();
//			}
//
//			for (int i = 0; i < 4; i++) {
//				Case nextI = current.getCaseNext(i);
//				if (!nextI.estVide() && !checked.contains(nextI) && !marked.contains(nextI)) {
//					marked.add(nextI);
//					LinkedList<Integer> nextPath = new LinkedList<Integer>(path.peek());
//					nextPath.add(i);
//					path.add(nextPath);
//				}
//			}
//
//			checked.add(current);
//			marked.remove();
//			path.remove();
//		}
//
//		this.restaureCases(sauvegarde);
//		LinkedList<LinkedList<Integer>> finalPath = new LinkedList<LinkedList<Integer>>();
//		finalPath.add(coupsRobot1);
//		finalPath.add(coupsRobot2);
//		return finalPath;
//	}

}
