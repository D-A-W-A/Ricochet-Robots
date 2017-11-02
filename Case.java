package ricochetRobot;

public class Case {
	private static int nbCases = 0;
	private int idCase;
	private Case[] caseVoisines = new Case[4]; // Taille min : 1 et max : 4

	public Case() {
		nbCases++;
		this.idCase = nbCases;
	}

}
