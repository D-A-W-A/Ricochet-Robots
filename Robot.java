package ricochetRobot;

/**
 * Un pion deplacable par le joueur. Un robot ne peut se deplacer que tout droit
 * jusqu'a atteindre un obstacle (un bord du plateau, un mur ou un autre Robot).
 * <br>
 * <br>
 * Strategie : Le Robot se trouve sur une Case Aleatoire du plateau, et peut se
 * deplacer sur l'une des CaseNext de cette case, a condition qu'elle ne soit
 * pas vide (mur) Le but du Robot 1 est d'atteindre la CaseObjectif en un
 * minimum de coup. <br>
 * <br>
 * Note : La premiere version du programme se fait avec 1 unique Robot, nous
 * n'avons pas besoin gerer les colisions entre Robots ou les modifications des
 * CaseNext
 * 
 * @author Dorian
 *
 */
public class Robot {

	/////////// ATTRIBUTS ///////////
	private char couleur = 'R';
	private boolean selectione;
	private Case caseActuelle;

	/////////// CONSTRUCTEURS /////////////
	public Robot() {
		selectione = false;
	}

	public Robot(Case c) {
		selectione = false;
		caseActuelle = c;
	}

	////////// GETTERS AND SETTERS ////////
	public boolean isSelectione() {
		return selectione;
	}

	public void setSelectione(boolean selectione) {
		this.selectione = selectione;
	}

	public Case getCaseActuelle() {
		return caseActuelle;
	}

	public void setCaseActuelle(Case caseActuelle) {
		this.caseActuelle = caseActuelle;
	}

	public char getCouleur() {
		return couleur;
	}

	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}

	////////// METHODES ////////////

	/**
	 * Deplace le robot vers le haut Ne fait rien si le robot se deplace vers un mur
	 * 
	 * @param direction
	 */
	public void deplacerRobotHaut() {
		if (!caseActuelle.getCaseNextHaut().estVide()) {
			Case aux = caseActuelle.getCaseNextHaut();
			caseActuelle.supprimerRobot();
			aux.affecterRobot(this);
			this.setCaseActuelle(aux);
		}
	}

	/**
	 * Deplace le robot vers le bas Ne fait rien si le robot se deplace vers un mur
	 * 
	 * @param direction
	 */
	public void deplacerRobotBas() {
		if (!caseActuelle.getCaseNextBas().estVide()) {
			Case aux = caseActuelle.getCaseNextBas();
			caseActuelle.supprimerRobot();
			aux.affecterRobot(this);
			this.setCaseActuelle(aux);
		}
	}

	/**
	 * Deplace le robot vers la gauche Ne fait rien si le robot se deplace vers un
	 * mur
	 * 
	 * @param direction
	 */
	public void deplacerRobotGauche() {
		if (!caseActuelle.getCaseNextGauche().estVide()) {
			Case aux = caseActuelle.getCaseNextGauche();
			caseActuelle.supprimerRobot();
			aux.affecterRobot(this);
			this.setCaseActuelle(aux);
		}
	}

	/**
	 * Deplace le robot vers la droite Ne fait rien si le robot se deplace vers un
	 * mur
	 * 
	 * @param direction
	 */
	public void deplacerRobotDroite() {
		if (!caseActuelle.getCaseNextDroite().estVide()) {
			Case aux = caseActuelle.getCaseNextDroite();
			caseActuelle.supprimerRobot();
			aux.affecterRobot(this);
			this.setCaseActuelle(aux);
		}
	}

}
