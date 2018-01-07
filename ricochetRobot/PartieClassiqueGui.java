package ricochetRobot;

import java.awt.BorderLayout;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import guiRicochetRobot.Grille;

/**
 * Partie classque avec inteface graphique<br><br>
 * 
 * Toutes les autres parties devront extends celle-ci et redefinir :
 * <br><ul><li>Le constructeur, ou nous redefinissont les murs et objectifs</li>
 * <li>placerRobotPartie, pour definir les zones où un robot ne doit pas apparaître</li>
 * <li>mainPartie</li>
 * <li>main (String[] args)</li></ul>
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
	 * Si il a clique sur une CaseNext, alors le robot se deplace, sinon, il ne se
	 * passe rien. <br>
	 * Lorsque le robot atteint la case objectif, alors c'est la victoire.
	 */
	public void update(String ob) {
		// On reccupere la case cliquee
		if (getEtatPartie() == 1) {
			int next = this.getGrille().getGrille()[getGrille().getCoordCaseClic()[0]][getGrille()
					.getCoordCaseClic()[1]].isNext();

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
	 * TODO : DEBUG : getCaseNext : Vérifier les murs
	 */
	public void definirNext() {
		Case cActuelle = getPlateau().getTabRobots()[0].getCaseActuelle();
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



	@Override
	protected void mainPartieAux() {
		creerPartie();
		int[] posRobots = { this.getPlateau().getTabRobots()[0].getCaseActuelle().getPosX(),
				this.getPlateau().getTabRobots()[0].getCaseActuelle().getPosY() };
		Grille g = new Grille(this.getPlateau().getTaille(), this.getDispositionMursPlateau(), posRobots, this.getPlateau().getObjectifPos());
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
