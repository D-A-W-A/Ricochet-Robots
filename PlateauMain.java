package ricochetRobot;

public class PlateauMain {

	public static void main (String [] args) {
		Plateau p = Plateau.genererPlateauRandom(16);
		Case[][] tab = p.getTabCases();
		System.out.println(tab[0][0].getCaseNextHaut().estVide());
		
		// S'il y a un mur à gauche, il doit y avoir un mur sur la case avant à droite
		System.out.println(tab[12][12].getCaseNextGauche().estVide());
		System.out.println(tab[12][11].getCaseNextDroite().estVide());
		System.out.println(p.toString());
		
	}
}
