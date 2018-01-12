package ricochetRobot;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import guiRicochetRobot.CaseGrille;
import guiRicochetRobot.Grille;

/**
 * Partie classque avec inteface graphique<br>
 * <br>
 * 
 * Toutes les autres parties devront extends celle-ci et redefinir : <br>
 * <ul>
 * <li>Le constructeur, ou nous redefinissont les murs et objectifs</li>
 * <li>placerRobotPartie, pour definir les zones où un robot ne doit pas
 * apparaître</li>
 * <li>ActionRecommencer</li>
 * <li>mainPartie</li>
 * <li>main (String[] args)</li>
 * </ul>
 * 
 * @author Dorian
 *
 */
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
	 * Cette fonction est appelee lorsque l'utilisateur clique sur n'importe quelle
	 * case <br>
	 * <br> Si le joueur clique sur un case comprenant un robot, alors cette case est selectionnee, ce qui signifie qu'il devra deplacer ce robot-la<br><br>
	 * Si il a clique sur une CaseNext, alors le robot se deplace, sinon, il ne se
	 * passe rien. <br><br>
	 * Lorsque le robot atteint la case objectif, alors c'est la victoire.
	 */
	public void update(String ob) {
		// On reccupere la case cliquee
		if (getEtatPartie() == 1) {
			CaseGrille c = this.getGrille().getGrille()[getGrille().getCoordCaseClic()[0]][getGrille()
					.getCoordCaseClic()[1]];
			// On reccupere la case actuelle
			int[] coordCaseActuelle = { getPlateau().getTabRobots()[getRobotSelectionne()].getCaseActuelle().getPosX(),
					getPlateau().getTabRobots()[getRobotSelectionne()].getCaseActuelle().getPosY() };
			
			int hasRobot = c.getHasRobot();
			int next = c.isNext();

			// On modifie la selection du robot
			if (hasRobot != 0) {
				// On reset cette case
				supprimerNext();
				getGrille().getGrille()[coordCaseActuelle[0]][coordCaseActuelle[1]].setSelected(false);;
				
				// On init la nouvelle case
				c.setSelected(true);
				setRobotSelectionne(hasRobot-1);

				definirNext();

			}
			// On deplace le robot s'il s'agit d'une caseNext
			if (next != 0) {
				
				supprimerNext();
				supprimerRobot(this.getRobotSelectionne());
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
				getPlateau().supprimerMursRobot(coordCaseActuelle[0], coordCaseActuelle[1]);
				getPlateau().ajouterMursRobot(getGrille().getCoordCaseClic()[0], getGrille().getCoordCaseClic()[1]);
				definirNext();
				placerRobot(this.getRobotSelectionne());
			}

			// En cas de victoire
			if (getPlateau().getObjectif().reussite()) {
				victoire();
			}
		}

		// On redessine tout pour eviter les bugs visuels
		getGrille().repaint();
		this.repaint();
	}

	/**
	 * Victoire
	 */
	public void victoire() {
		this.changerTexte("VICTOIRE");
		setNbVictoire(getNbVictoire() + 1);
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
	public void supprimerRobot(int num) {
		int[] coord = { getPlateau().getTabRobots()[num].getCaseActuelle().getPosX(),
				getPlateau().getTabRobots()[num].getCaseActuelle().getPosY() };
		getGrille().getGrille()[coord[0]][coord[1]].setHasRobot(0);
		getGrille().getGrille()[coord[0]][coord[1]].setSelected(false);
	}

	/**
	 * Replace le robot sur l'interface graphique
	 */
	public void placerRobot(int num) {
		int[] coord = { getPlateau().getTabRobots()[num].getCaseActuelle().getPosX(),
				getPlateau().getTabRobots()[num].getCaseActuelle().getPosY() };
		getGrille().getGrille()[coord[0]][coord[1]].setHasRobot(getRobotSelectionne() + 1);
		getGrille().getGrille()[coord[0]][coord[1]].setSelected(true);
	}

	/**
	 * Supprime les caseNext de l'interface graphique
	 */
	public void supprimerNext() {
		Case cActuelle = getPlateau().getTabRobots()[this.getRobotSelectionne()].getCaseActuelle();
		if (!cActuelle.getCaseNextGauche().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextGauche().getPosX()][cActuelle.getCaseNextGauche().getPosY()]
					.setNext(0);
		if (!cActuelle.getCaseNextHaut().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextHaut().getPosX()][cActuelle.getCaseNextHaut().getPosY()]
					.setNext(0);
		if (!cActuelle.getCaseNextDroite().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextDroite().getPosX()][cActuelle.getCaseNextDroite().getPosY()]
					.setNext(0);
		if (!cActuelle.getCaseNextBas().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextBas().getPosX()][cActuelle.getCaseNextBas().getPosY()]
					.setNext(0);
	}

	/**
	 * Permet de definir les caseNext pour l'interface graphique
	 * 
	 */
	public void definirNext() {
		Case cActuelle = getPlateau().getTabRobots()[this.getRobotSelectionne()].getCaseActuelle();
		if (!cActuelle.getCaseNextGauche().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextGauche().getPosX()][cActuelle.getCaseNextGauche().getPosY()]
					.setNext(1);
		if (!cActuelle.getCaseNextHaut().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextHaut().getPosX()][cActuelle.getCaseNextHaut().getPosY()]
					.setNext(2);
		if (!cActuelle.getCaseNextDroite().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextDroite().getPosX()][cActuelle.getCaseNextDroite().getPosY()]
					.setNext(3);
		if (!cActuelle.getCaseNextBas().estVide())
			getGrille().getGrille()[cActuelle.getCaseNextBas().getPosX()][cActuelle.getCaseNextBas().getPosY()]
					.setNext(4);
	}

	/**
	 * Gestion des boutons du menu de gauche
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton b = (JButton) (e.getSource());
		if (b.getText().equals("Solution"))
			changerTexte(toStringSolution());
		else if (b.getText().equals("Recommencer"))
			actionRecommencer();
	}

	public void actionRecommencer() {
		dispose();
		mainPartie();
	}

	public int[][] placerRobotGui() {
		int[][] posRobots = new int[this.getPlateau().getTabRobots().length][2];
		for (int i = 0; i < this.getPlateau().getTabRobots().length; i++) {
			posRobots[i][0] = this.getPlateau().getTabRobots()[i].getCaseActuelle().getPosX();
			posRobots[i][1] = this.getPlateau().getTabRobots()[i].getCaseActuelle().getPosY();
		}
		return posRobots;
	}

	@Override
	protected void mainPartieAux() {
		creerPartie();
		int[][] posRobots = placerRobotGui();
		Grille g = new Grille(this.getPlateau().getTaille(), this.getDispositionMursPlateau(), posRobots,
				this.getPlateau().getObjectifPos());
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
