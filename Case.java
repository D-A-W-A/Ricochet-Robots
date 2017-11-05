package ricochetRobot;

/**
 * Une Case simple du Plateau<br>
 * Elle possede une id unique, un attribut occupe et un tableau de 4 Case
 * next<br>
 * <br>
 * Strategie : C'est une structure reccursive sous forme de graphe oriente.
 * Chaque Case possede 4 Cases next qui ne sont pas null. Si une Case possede au
 * moins une caseNext = Null, alors cette case est une Case vide.<br>
 * Une case vide n'est pas atteignable par un Robot : On peut donc considerer
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
	private int posX;
	private int posY;

	//////// CONSTRUCTEURS ////////

	/**
	 * Constructeur d'une Case Vide
	 */
	public Case() {
		for (int i = 0; i < 4; i++)
			this.caseNext[i] = null;
		occupe = false;
	}

	/**
	 * Constructeur par recopie
	 */
	public Case(Case c) {
		if (c.estVide()) {
			for (int i = 0; i < 4; i++)
				this.caseNext[i] = null;
		} else {
			caseNext[0] = new Case(c.getCaseNextGauche());
			caseNext[1] = new Case(c.getCaseNextHaut());
			caseNext[2] = new Case(c.getCaseNextDroite());
			caseNext[3] = new Case(c.getCaseNextBas());
		}
		occupe = false;
	}
	
	/**
	 * Constructeur à partir des positions
	 * @param x
	 * @param y
	 */
	public Case(int x, int y) {
		Case caseVide = new Case();
		for (int i = 0; i < 4; i++)
			this.caseNext[i] = caseVide;
		occupe = false;
		posX = x;
		posY = y;
	}

	//////// GETTERS AND SETTERS ////////

	/**
	 * Getter de toutes les caseNext
	 * 
	 * @return un tableau de Case
	 */
	public Case[] getCaseNext() {
		return caseNext;
	}

	/**
	 * Getter de la caseNext de gauche
	 * 
	 * @return la prochaine case à gauche
	 */
	public Case getCaseNextGauche() {
		return caseNext[0];
	}

	/**
	 * Getter de la caseNext du haut
	 * 
	 * @return la prochaine case en haut
	 */
	public Case getCaseNextHaut() {
		return caseNext[1];
	}

	/**
	 * Getter de la caseNext de droite
	 * 
	 * @return la prochaine case à droite
	 */
	public Case getCaseNextDroite() {
		return caseNext[2];
	}

	/**
	 * Getter de la caseNext du bas
	 * 
	 * @return la prochaine case en bas
	 */
	public Case getCaseNextBas() {
		return caseNext[3];
	}

	/**
	 * Setter de toutes les CaseNext
	 * 
	 * @param caseNext
	 *            un tableau de Case
	 */
	public void setCaseNext(Case[] caseNext) {
		this.caseNext = caseNext;
	}

	/**
	 * Setter de toutes les caseNext
	 * 
	 * @param gauche
	 * @param haut
	 * @param droite
	 * @param bas
	 */
	public void setCaseNext(Case gauche, Case haut, Case droite, Case bas) {
		caseNext[0] = gauche;
		caseNext[1] = haut;
		caseNext[2] = droite;
		caseNext[3] = bas;
	}

	/**
	 * Setter de la prochaine case à gauche
	 * 
	 * @param c
	 *            une Case
	 */
	public void setCaseNextGauche(Case c) {
		this.caseNext[0] = c;
	}

	/**
	 * Setter de la prochaine case en haut
	 * 
	 * @param c
	 *            une Case
	 */
	public void setCaseNextHaut(Case c) {
		this.caseNext[1] = c;
	}

	/**
	 * Setter de la prochaine case à droite
	 * 
	 * @param c
	 *            une Case
	 */
	public void setCaseNextDroite(Case c) {
		this.caseNext[2] = c;
	}

	/**
	 * Setter de la prochaine case en bas
	 * 
	 * @param c
	 *            une Case
	 */
	public void setCaseNextBas(Case c) {
		this.caseNext[3] = c;
	}

	/**
	 * Verifie si un Robot se trouve sur cette Case
	 * 
	 * @return true si la case est occupee
	 */
	public boolean isOccupe() {
		return occupe;
	}

	/**
	 * Permet de changer l'état de la case (La rendre occupée ou non)
	 * 
	 * @param occupe
	 */
	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setPos(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	

	/////// METHODES //////////


	/**
	 * Verifie si la case est Vide
	 * 
	 * @return true si elle est vide, false sinon.
	 */
	public boolean estVide() {
		return caseNext[0] == null || caseNext[1] == null || caseNext[2] == null || caseNext[3] == null;
	}

	public String toString() {
		if (!this.estVide()) {
			if (occupe)
				return "R";
			else
				return (".");
		} else {
			return "";
		}

	}

	/**
	 * Cree une String de la case sous la forme : <br>
	 * <ul>
	 * <li>* : S'il n'y a pas de mur à proximité</li>
	 * <li>|* : si un mur est à gauche</li>
	 * <li>*<br>
	 * _ : Si le mur est en bas</li>
	 * <li>_<br>
	 * |*|<br>
	 * _ : Si la case est entourée de murs</li>
	 * <li>etc.</li>
	 * 
	 * @return
	 */
	public String toStringMur() {
		if (!this.estVide()) {
			StringBuilder s = new StringBuilder(" ");
			if (this.getCaseNextHaut().estVide())
				s.append("_ \n");
			else
				s.append("  \n");

			if (this.getCaseNextGauche().estVide())
				s.append("|*");
			else
				s.append(" *");

			if (this.getCaseNextDroite().estVide())
				s.append("|\n ");
			else
				s.append(" \n ");

			if (this.getCaseNextBas().estVide())
				s.append("_ ");
			else
				s.append("  ");

			return s.toString();
		} else {
			return "";
		}
	}

	/**
	 * Genere une Case entourée de Cases Vides (donc une case non vide)
	 * 
	 * @return
	 */
	public static Case creerCase() {
		Case c = new Case();
		Case[] tabCase = new Case[4];
		for (int i = 0; i < 4; i++) {
			tabCase[i] = new Case();
		}
		c.setCaseNext(tabCase);
		return c;
	}

}
