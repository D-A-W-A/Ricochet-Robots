package ricochetRobot;

public class PlateauMain {

	public static void main (String [] args) {
		Plateau p = Plateau.genererPlateauRandom(16);
		Case[][] tab = p.getTabCases();
//		System.out.println(tab[0][0].getCaseNextHaut().estVide());
//		
//		// S'il y a un mur � gauche, il doit y avoir un mur sur la case avant � droite
//		System.out.println(tab[12][12].getCaseNextGauche().estVide());
//		System.out.println(tab[12][11].getCaseNextDroite().estVide());
		System.out.println(p.toString());
		p.ajouterObjectifRandom();
		p.placerRobotRandom(0);
		System.out.println("\n\n==================================\n \n"+p.toString());

//		p.ajouterMurHaut(1, 1);
//		System.out.println("\n\n==================================\n \n"+p.toString());
//		System.out.println(p.getTabCases()[0][1].getCaseNextHaut().estVide());
//		System.out.println(p.getTabCases()[0][0].getCaseNextHaut().estVide());
//		System.out.println("\n\n==================================\n \n"+p.toString());
		
//		p.ajouterMurBas(1, 1);
//		System.out.println("\n\n==================================\n \n"+p.toString());
		
		p.ajouterMurGauche(2, 3);
		System.out.println("\n\n==================================\n \n"+p.toString());
		
//		p.ajouterMurDroite(0,0);
//		System.out.println("\n\n==================================\n \n"+p.toString());
	}
}
