package ricochetRobot;

import java.util.Random;

import guiRicochetRobot.Grille;

public class PartieClassiqueGui extends PartieClassique {

	
	public PartieClassiqueGui() {
		super();
	}

	/**
	 * Met en pause la Partie
	 */
	public void pausePartie() {
		System.out.println("PAUSE");
		setEtatPartie(2);
	}

	/**
	 * Relance la partie
	 */
	public void reprendrePartie() {
		System.out.println("REPRISE");
		setEtatPartie(1);
	}

	/**
	 * Arrete completement la partie. Rien n'est stocke
	 */
	public void arreterPartie() {
		System.out.println("GAME OVER !");
		System.exit(0);
		stop();
	}

	@Override
	public void update() {

	}

	@Override
	protected void mainPartieAux() {
		creerPartie();
		int [] posRobots = {this.getPlateau().getTabRobots()[0].getCaseActuelle().getPosX(), this.getPlateau().getTabRobots()[0].getCaseActuelle().getPosY()};
		Grille g = new Grille(16, this.getDispositionMursPlateau(), posRobots, this.getPlateau().getObjectifPos());
		this.setGrille(g);
		lancerFenetre();
		lancerPartie();
		System.out.println(toString());
	}
	
	public static void mainPartie() {
		PartieClassiqueGui p = new PartieClassiqueGui();
		p.mainPartieAux();
	}

	public static void main(String[] args) {
		PartieClassiqueGui.mainPartie();
	}
}
