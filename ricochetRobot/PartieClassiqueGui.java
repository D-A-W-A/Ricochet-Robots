package ricochetRobot;

import java.awt.Color;

import javax.swing.JLabel;

import guiRicochetRobot.Grille;

public class PartieClassiqueGui extends PartieClassique {

	public PartieClassiqueGui() {
		super();
	}

	/**
	 * Met en pause la Partie
	 */
	public void pausePartie() {
		setEtatPartie(2);
	}

	/**
	 * Relance la partie
	 */
	public void reprendrePartie() {
		setEtatPartie(1);
	}

	/**
	 * Arrete completement la partie. Rien n'est stocke
	 */
	public void arreterPartie() {
		System.exit(0);
		stop();
	}

	/**
	 * Cette fonction est appelee lorsque l'utilisateur clique sur n'importe quelle case <br>
	 * Si il a clique sur une CaseNext, alors le robot se deplace, sinon, il ne se passe rien.
	 * <br> Lorsque le robot atteint la case objectif, alors c'est la victoire.
	 */
	public void update(String ob) {
		// On reccupere la case cliquee
		int next = this.getGrille().getGrille()[getGrille().getCoordCaseClic()[0]][getGrille().getCoordCaseClic()[1]]
				.isNext();
		
		// On deplace le robot s'il s'agit d'une caseNext
		if (next != 0) {
			supprimerNext();
			supprimerRobot();
			int deplacement;
			if (next == 1) {
				deplacement = getPlateau().getTabRobots()[getRobotSelectionne()].deplacerRobotGauche();
			} else if (next == 2) {
				deplacement = getPlateau().getTabRobots()[getRobotSelectionne()].deplacerRobotHaut();
			} else if (next == 3) {
				deplacement = getPlateau().getTabRobots()[getRobotSelectionne()].deplacerRobotDroite();
			} else if (next == 4) {
				deplacement = getPlateau().getTabRobots()[getRobotSelectionne()].deplacerRobotBas();
			}
			definirNext();
			placerRobot();
		}
		
		// En cas de victoire
		if (getPlateau().getObjectif().reussite()) {
			System.out.println("YAY");
			JLabel j = new JLabel ("Gagné !!!");
			j.setForeground(Color.red);
			setTitreLabel(j);
			victoire();
		}
		
		// On redessine tout pour eviter les bugs visuels
		getGrille().repaint();
		this.repaint();
	}
	
	/**
	 * Victoire
	 */
	public void victoire() {
		setNbVictoire(getNbVictoire()+1);
		setEtatPartie(0);
		
	}

	/**
	 * Fonction qui lance la partie
	 */
	public void lancerPartie() {
		setEtatPartie(1);
	}

	/**
	 * Supprime le robot de l'interface graphique
	 */
	public void supprimerRobot() {
		int[] coord = { getPlateau().getTabRobots()[0].getCaseActuelle().getPosX(),
				getPlateau().getTabRobots()[0].getCaseActuelle().getPosY() };
		getGrille().getGrille()[coord[0]][coord[1]].setHasRobot(false);
	}

	/** 
	 * Replace le robot sur l'interface graphique
	 */
	public void placerRobot() {
		int[] coord = { getPlateau().getTabRobots()[0].getCaseActuelle().getPosX(),
				getPlateau().getTabRobots()[0].getCaseActuelle().getPosY() };
		getGrille().getGrille()[coord[0]][coord[1]].setHasRobot(true);
	}

	/**
	 * Supprime les caseNext de l'interface graphique
	 */
	public void supprimerNext() {
		Case cActuelle = getPlateau().getTabRobots()[0].getCaseActuelle();
		getGrille().getGrille()[cActuelle.getCaseNextGauche().getPosX()][cActuelle.getCaseNextGauche().getPosY()]
				.setNext(0);
		getGrille().getGrille()[cActuelle.getCaseNextHaut().getPosX()][cActuelle.getCaseNextHaut().getPosY()]
				.setNext(0);
		getGrille().getGrille()[cActuelle.getCaseNextDroite().getPosX()][cActuelle.getCaseNextDroite().getPosY()]
				.setNext(0);
		getGrille().getGrille()[cActuelle.getCaseNextBas().getPosX()][cActuelle.getCaseNextBas().getPosY()].setNext(0);
	}

	/**
	 * Permet de definir les caseNext pour l'interface graphique
	 */
	public void definirNext() {
		Case cActuelle = getPlateau().getTabRobots()[0].getCaseActuelle();
		getGrille().getGrille()[cActuelle.getCaseNextGauche().getPosX()][cActuelle.getCaseNextGauche().getPosY()]
				.setNext(1);
		getGrille().getGrille()[cActuelle.getCaseNextHaut().getPosX()][cActuelle.getCaseNextHaut().getPosY()]
				.setNext(2);
		getGrille().getGrille()[cActuelle.getCaseNextDroite().getPosX()][cActuelle.getCaseNextDroite().getPosY()]
				.setNext(3);
		getGrille().getGrille()[cActuelle.getCaseNextBas().getPosX()][cActuelle.getCaseNextBas().getPosY()].setNext(4);
	}

	@Override
	protected void mainPartieAux() {
		creerPartie();
		int[] posRobots = { this.getPlateau().getTabRobots()[0].getCaseActuelle().getPosX(),
				this.getPlateau().getTabRobots()[0].getCaseActuelle().getPosY() };
		Grille g = new Grille(16, this.getDispositionMursPlateau(), posRobots, this.getPlateau().getObjectifPos());
		this.setGrille(g);
		g.addObservateur(this);
		definirNext();
		lancerFenetre();
		lancerPartie();
	}

	public static void mainPartie() {
		PartieClassiqueGui p = new PartieClassiqueGui();
		p.mainPartieAux();
	}

	public static void main(String[] args) {
		PartieClassiqueGui.mainPartie();
	}
}
