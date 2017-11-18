package ricochetRobot;

import java.awt.event.KeyEvent;

public class PartieRandomTerminal extends Partie {

	//////// CONSTRUCTEURS ///////
	public PartieRandomTerminal() {
		super();
	}
	
	/**
	 * Cree une partie random avec un plateau genere aleatoirement de taille 16
	 */
	public void creerPartie() {
		this.setPlateau(Plateau.genererPlateauRandom(16));
		setEtatPartie(0);
		setNbCoups(0);
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
		stop();
		System.out.println("GAME OVER !");
		System.exit(0);
	}
	
	
	
	/**
	 * Permet de lancer une partie avec toute les fonctionnalites necessaires.
	 */
	protected void mainPartieAux() {
		System.out.println("RICOCHET - ROBOTS\nVersion sur Terminal - Plateau Random\n\n");
		System.out.println(
				"Le jeu se joue avec les feches directionnelles.\nPour mettre en pause, appuyez sur P. Une fois le jeu en pause, vous pouvez appuyer sur R pour reccomencer sur un autre Plateau\nPour arreter la partie, appuyez sur ECHAP\n");
		creerPartie();
		getPlateau().placerRobotRandom(0);
		getPlateau().ajouterObjectifRandom();
		lancerPartie();
		System.out.println(toString());
	}
	
	
	public static void mainPartie() {
		PartieRandomTerminal p = new PartieRandomTerminal();
		p.mainPartieAux();
	}
	
	/**
	 * Met a jour le terminal pour montrer la nouvelle disposition du plateau
	 */
	public void update() {
		System.out
				.println("\n\n=====================================================================\nNombre de Coups : "
						+ getNbCoups() + "\n" + toString());
	}
	
	
	public static void main(String[] args) {
		PartieRandomTerminal.mainPartie();
	}

}
