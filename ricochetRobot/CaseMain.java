package ricochetRobot;

public class CaseMain {

	public static void main(String[] args) {
//
//		// Test de estVide()
//		Case c = new Case();
//		System.out.println("c est vide ? " + c.estVide());
//		
////		Case c1 = new Case();
////		c.setCaseNextGauche(c1);
////		System.out.println("Ajout d'un CaseNextGauche, c est vide ? " + c.estVide());
//
//		Case[] tabCase = new Case[4];
//		for (int i = 0; i < 4; i++) {
//			tabCase[i] = new Case();
//		}
//		c.setCaseNext(tabCase);
//		System.out.println("Ajout d'un tableau de CaseNext, c est vide ? " + c.estVide());
//		System.out.println("La case de gauche est vide ? " + c.getCaseNextGauche().estVide());
//		
//		
////		// TEST DE ToString()
////		System.out.println(c.toString());
////		System.out.println(c.toStringMur());
////		c.setCaseNextGauche(c);
////		System.out.println(c.toStringMur());
//
//		Case caseGen = Case.creerCase();
//		System.out.println("Case generee vide ? " + caseGen.estVide());
		
		Case c = new Case();
		c.setDispoMurs(3);
		System.out.println(c.murDroite());


	}

}
