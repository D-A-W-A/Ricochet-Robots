package ricochetRobot;

/**
 * Une Case simple du Plateau<br>
 * Elle poss�de une id unique, un attribut occupe et un tableau de 4 Case
 * next<br>
 * <br>
 * Strat�gie : C'est une structure r�ccursive sous forme de graphe orient�.
 * Chaque Case poss�de 4 Cases next qui ne sont pas null. Si une Case poss�de au
 * moins une caseNext = Null, alors cette case est une Case vide.<br>
 * Une case vide n'est pas atteignable par un Robot : On peut donc consid�rer
 * une Case vide comme un Mur
 * 
 * @author Dorian
 *
 */
public class Case {

	// 0 => Gauche --- 1 => Haut --- 2 => Droite --- 3 => Bas
	private Case[] caseNext = new Case[4];
	private boolean occupe;

	/**
	 * Constructeur d'une Case Vide
	 */
	public Case() {
		for (int i = 0; i<4; i++)
			this.caseNext[i] = null;
		occupe = false;
	}

	public Case[] getCaseNext() {
		return caseNext;
	}

	public void setCaseNext(Case[] caseNext) {
		this.caseNext = caseNext;
	}

	public boolean isOccupe() {
		return occupe;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}


}
