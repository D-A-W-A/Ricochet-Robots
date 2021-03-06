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
 * est de deplacer le robot de la couleur de l’objectif sur celui-ci en un
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
	 * tableau. ATTENTION : La méthode est non destructrice de la liste en parametre
	 * mais pourra être lourde pour les longues solutions.
	 * 
	 * @return La solution sous forme de tableau
	 */
	protected int[] solveToTab(LinkedList<Integer> solution) {
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
		System.out.println(toStringSolution());

	}

	/**
	 * Appelle solve et affiche renvoie une chaine de caractere de la solution
	 */
	public String toStringSolution() {
		int[] solution = solveToTab(solve1());
		if (solution.length == 0) {
			return ("L'objectif n'est pas atteignable");
		} else {
			StringBuilder s = new StringBuilder("La solution est : ");
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
	 * Les coups retournés sont des entiers sous la forme : 0 = gauche, 1 = haut, 2
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


		while(!found && !marked.isEmpty()) { //Tant qu'on a pas trouvé l'objectif et qu'il y a des cases marquées
			Case current = marked.getFirst(); // On s'intéresse à la première case de la FIFO marquée
			if (current.equals(objectif)){ //Si c'est l'objectif
				found = true; //On l'a trouvé
				finalPath.addAll(path.peek()); //On récupère le chemin jusqu'à lui
			}
			else { //Si c'est pas l'objectif et qu'elle est pas vide
				for (int i=0; i<4; i++) { //Pour chacune de ses voisines
					Case nextI = current.getCaseNext(i);
					if(!nextI.estVide()&&!checked.contains(nextI)&&!marked.contains(nextI)) {
						marked.add(nextI); //On la marque
						LinkedList<Integer> nextPath = new LinkedList<Integer>(path.peek()); //On initialise le chemin jusqu'à cette case suivante en utilisant le chemin jusqu'à courant
						nextPath.add(i); // On ajoute à ce chemin le coup pour passer de courant à cette case suivante 
						path.add(nextPath); // On place ce chemin à la fin de la liste des chemins

					}
				}
			}
			
			checked.add(current);
			marked.remove();
			path.remove();
		}
		return finalPath;
	}
}
