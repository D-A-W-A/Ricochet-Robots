package ricochetRobot;

/**
 * Une Case simple du Plateau<br>
 * Elle possède une id unique, un attribut occupe et un tableau de 4 Case
 * next<br>
 * <br>
 * Stratégie : C'est une structure réccursive sous forme de graphe orienté.
 * Chaque Case possède 4 Cases next qui ne sont pas null. Si une Case possède au
 * moins une caseNext = Null, alors cette case est une Case vide.<br>
 * Une case vide n'est pas atteignable par un Robot : On peut donc considérer
 * une Case vide comme un Mur
 * 
 * @author Dorian
 *
 */
public class Case {
	private static int nbCases = 0;
	private int idCase;
	
	// 0 => Gauche --- 1 => Haut --- 2 => Droite --- 3 => Bas
	private Case[] caseNext = new Case[4];
	private boolean occupe;

	public Case() {
		nbCases++;
		this.idCase = nbCases;
	}

	
}
