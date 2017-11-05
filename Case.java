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

	///////// ATTRIBUTS///////////
	// 0 => Gauche --- 1 => Haut --- 2 => Droite --- 3 => Bas
	private Case[] caseNext = new Case[4];
	private boolean occupe;

	//////// CONSTRUCTEURS ////////
	/**
	 * 
	 * Constructeur d'une Case Vide
	 */
	public Case() {
		for (int i = 0; i < 4; i++)
			this.caseNext[i] = null;
		occupe = false;
	}

	//////// GETTERS AND SETTERS ////////

	/**
	 * Getter de toutes les caseNext
	 * @return un tableau de Case
	 */
	public Case[] getCaseNext() {
		return caseNext;
	}

	/**
	 * Getter de la caseNext de gauche
	 * @return la prochaine case à gauche
	 */
	public Case getCaseNextGauche() {
		return caseNext[0];
	}

	/**
	 * Getter de la caseNext du haut
	 * @return la prochaine case en haut
	 */
	public Case getCaseNextHaut() {
		return caseNext[1];
	}

	/**
	 * Getter de la caseNext de droite
	 * @return la prochaine case à droite
	 */
	public Case getCaseNextDroite() {
		return caseNext[2];
	}

	/**
	 * Getter de la caseNext du bas
	 * @return la prochaine case en bas
	 */
	public Case getCaseNextBas() {
		return caseNext[3];
	}
	

	/**
	 * Setter de toutes les CaseNext
	 * @param caseNext un tableau de Case
	 */
	public void setCaseNext(Case[] caseNext) {
		this.caseNext = caseNext;
	}

	/**
	 * Setter de la prochaine case à gauche
	 * @param c une Case
	 */
	public void setCaseNextGauche(Case c) {
		this.caseNext[0] = c;
	}

	/**
	 * Setter de la prochaine case en haut
	 * @param c une Case
	 */
	public void setCaseNextHaut(Case c) {
		this.caseNext[1] = c;
	}

	/**
	 * Setter de la prochaine case à droite
	 * @param c une Case
	 */
	public void setCaseNextDroite(Case c) {
		this.caseNext[2] = c;
	}

	/**
	 * Setter de la prochaine case en bas
	 * @param c une Case
	 */
	public void setCaseNextBas(Case c) {
		this.caseNext[3] = c;
	}

	
	
	/**
	 * Vérifie si un Robot se trouve sur cette Case
	 * @return true si la case est occupee
	 */
	public boolean isOccupe() {
		return occupe;
	}

	/**
	 * Permet de changer l'état de la case (La rendre occupée ou non)
	 * @param occupe 
	 */
	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

}
