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
	private int posX = -1;
	private int posY = -1;
	private Robot robot;
	// 1 -> GH, 2-> HD, 3 ->DB, 4 -> BG 
	private int dispoMurs = 0;

	//////// CONSTRUCTEURS ////////

	/**
	 * Constructeur d'une Case Vide
	 */
	public Case() {
		for (int i = 0; i < 4; i++)
			this.caseNext[i] = null;
	}

	/**
	 * Constructeur par recopie
	 * @param c la case a copier
	 */
	public Case(Case c) {
		if (c.estVide()) {
			for (int i = 0; i < 4; i++)
				this.caseNext[i] = null;
		} else {
			posX = c.posX;
			posY = c.posY;
			dispoMurs = c.dispoMurs;
			caseNext[0] = new Case(c.getCaseNextGauche());
			caseNext[1] = new Case(c.getCaseNextHaut());
			caseNext[2] = new Case(c.getCaseNextDroite());
			caseNext[3] = new Case(c.getCaseNextBas());
		}
	}

	/**
	 * Constructeur a partir des positions
	 * @param x la coordonnee x
	 * @param y la coord y
	 */
	public Case(int x, int y) {
		Case caseVide = new Case();
		for (int i = 0; i < 4; i++)
			this.caseNext[i] = caseVide;
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

	public int getDispoMurs() {
		return dispoMurs;
	}

	public void setDispoMurs(int dispoMurs) {
		this.dispoMurs = dispoMurs;
	}

	/**
	 * Getter de la caseNext en fonction de son numero
	 * @param i l'indice de la caseNext (de 0 a 3)
	 * @return la prochaine caseNext[i]
	 */
	public Case getCaseNext(int i) {
		return caseNext[i];
	}

	/**
	 * Getter de la caseNext de gauche
	 * 
	 * @return la prochaine case a gauche
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
	 * @return la prochaine case a droite
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
		for (int i = 0 ; i<4; i++) {
			this.caseNext[i] = caseNext[i];
		}
	}
	

	/**
	 * Setter de toutes les caseNext
	 * 
	 * @param gauche la CaseNext de gauche
	 * @param haut la CaseNext du haut
	 * @param droite la CaseNext de droite
	 * @param bas la CaseNext du bas
	 */
	public void setCaseNext(Case gauche, Case haut, Case droite, Case bas) {
		caseNext[0] = gauche;
		caseNext[1] = haut;
		caseNext[2] = droite;
		caseNext[3] = bas;
	}

	/**
	 * Setter de la prochaine case a gauche
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
	 * Setter de la prochaine case a droite
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
	public boolean estOccupe() {
		return robot != null;
	}


	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
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
	
	public boolean murDroite() {
		return dispoMurs == 2 || dispoMurs == 3;
	}
	
	public boolean murGauche() {
		return dispoMurs == 1 || dispoMurs == 4;
	}
	
	public boolean murHaut() {
		return dispoMurs == 1 || dispoMurs == 2;
	}
	
	public boolean murBas() {
		return dispoMurs == 3 || dispoMurs == 4;
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

	/**
	 * Compare les coordonnées de deux cases.
	 * @param case2 : Case comparee.
	 * @return True si les positions des deux cases sont egales.
	 */
	public boolean equals(Case case2) {
		return ((case2.getPosX()==this.getPosX()) && (case2.getPosY()==this.getPosY()));
	}

	public String toString() {
		if (!this.estVide()) {
			if (estOccupe())
				return robot.toString();
			else
				return (".");
		} else {
			return "";
		}

	}

	/**
	 * Cree une String de la case sous la forme : <br>
	 * <ul>
	 * <li>* : S'il n'y a pas de mur a proximite</li>
	 * <li>|* : si un mur est a gauche</li>
	 * <li>*<br>
	 * _ : Si le mur est en bas</li>
	 * <li>_<br>
	 * |*|<br>
	 * _ : Si la case est entouree de murs</li>
	 * <li>etc.</li>
	 * </ul>
	 * @return la Chaine de caracteres
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
	 * Genere une Case entouree de Cases Vides (donc une case non vide)
	 * 
	 * @return la case
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

	public void affecterRobot (Robot r) {
		this.robot = r;
		r.setCaseActuelle(this);
	}

	public void supprimerRobot () {
		robot.setCaseActuelle(null);
		this.robot = null;
	}

}
