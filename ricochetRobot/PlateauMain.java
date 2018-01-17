package ricochetRobot;

import java.util.Random;

public class PlateauMain {

	public static void main(String[] args) {
		// Plateau p = Plateau.genererPlateauRandom(16);
		// Case[][] tab = p.getTabCases();
		//// System.out.println(tab[0][0].getCaseNextHaut().estVide());
		////
		//// // S'il y a un mur a gauche, il doit y avoir un mur sur la case avant a
		// droite
		//// System.out.println(tab[12][12].getCaseNextGauche().estVide());
		//// System.out.println(tab[12][11].getCaseNextDroite().estVide());
		// System.out.println(p.toString());
		// p.ajouterObjectifRandom();
		// p.placerRobotRandom(0);
		// System.out.println("\n\n==================================\n
		// \n"+p.toString());
		//
		//// p.ajouterMurHaut(1, 1);
		//// System.out.println("\n\n==================================\n
		// \n"+p.toString());
		//// System.out.println(p.getTabCases()[0][1].getCaseNextHaut().estVide());
		//// System.out.println(p.getTabCases()[0][0].getCaseNextHaut().estVide());
		//// System.out.println("\n\n==================================\n
		// \n"+p.toString());
		//
		//// p.ajouterMurBas(1, 1);
		//// System.out.println("\n\n==================================\n
		// \n"+p.toString());
		//
		// p.ajouterMurGauche(2, 3);
		// System.out.println("\n\n==================================\n
		// \n"+p.toString());
		//
		//// p.ajouterMurDroite(0,0);
		//// System.out.println("\n\n==================================\n
		// \n"+p.toString());

		Plateau p = new Plateau();
		// p.genererPlateauSansMur();
		// p.ajouterCoinBG(12, 12);
		// p.ajouterCoinGH(1, 1);
		// p.ajouterCoinHD(5, 5);
		// p.ajouterCoinDB(7, 7);
		// System.out.println(p.toString());
		// p.getTabCases()[0][0].setPos(0, 0);
		// p.getTabCases()[0][p.getTaille()-1].setPos(15, 15);
		// System.out.println(p.getTabCases()[0][0].getCaseNextDroite().getPosX());
		Random r = new Random();
		int tirage;
		int[][] t = new int[p.getTaille()][p.getTaille()];
		for (int i = 0; i < p.getTaille(); i++) {
			for (int j = 0; j < p.getTaille(); j++) {
				tirage = r.nextInt(100);
				if (tirage > 80 && tirage <= 85)
					t[i][j] = 1;
				if (tirage > 85 && tirage <= 90)
					t[i][j] = 2;
				if (tirage > 90 && tirage <= 95)
					t[i][j] = 3;
				if (tirage > 95 && tirage <= 100)
					t[i][j] = 4;
			}
		}
		p.genererPlateau(t, 12, 12);
		Robot[] tr = new Robot[1];
		tr[0] = new Robot();
		p.setTabRobots(tr);
		p.placerRobotRandom(0);
		System.out.println("PLATEAU P\n");
		System.out.println(p.toString());
		System.out.println(p.getTabRobots()[0].getCaseActuelle().getPosX());
		Plateau p2 = new Plateau(p);
		System.out.println("\n\n\n\n\nPLATEAU P2\n");
		System.out.println(p2.toString());

		System.out.println(p.getTabCases()[0][0].getCaseNextDroite().hashCode() + "   "
				+ p.getTabCases()[1][2].getCaseNextDroite().getPosX() + "   "
				+ p.getTabCases()[1][2].getCaseNextDroite().getPosY());
		System.out.println(p2.getTabCases()[0][0].getCaseNextDroite().hashCode() + "   "
				+ p2.getTabCases()[1][2].getCaseNextDroite().getPosX() + "   "
				+ p2.getTabCases()[1][2].getCaseNextDroite().getPosY());

	}
}
