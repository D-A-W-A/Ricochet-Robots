package ricochetRobot;

/**
 * Une Partie de Ricochet-Robots Classique<br>
 *
 * 
 * @author Dorian
 *
 */
public class PartieClassique extends Partie {

	private int[][] dispositionMursPlateau = new int [16][16]; // Sera utilisee par Plateau.genererPlateau
	private int [] posXObjectifs = new int [17]; // Case 0 : Objectif 1 / Case 1 : Objectif 2 ...
	private int [] posYObjectifs = new int [17];
	
	public PartieClassique() {
		super();
		
		////// Tableau de generation des murs
		// Ligne 1
		dispositionMursPlateau [0][2] = 1;
		dispositionMursPlateau [0][12] = 1;
		
		// Ligne 2
		dispositionMursPlateau [1][3] = 1;
		dispositionMursPlateau [1][9] = 3;
		
		// Ligne 3
		dispositionMursPlateau [2][15] = 3;
		
		// Ligne 4
		dispositionMursPlateau [3][6] = 3;
		dispositionMursPlateau [3][11] = 4;
		
		// Ligne 5
		dispositionMursPlateau [4][1] = 4;
		
		// Ligne 6
		dispositionMursPlateau [5][14] = 1;
		
		// Ligne 7
		dispositionMursPlateau [6][0] = 4;
		dispositionMursPlateau [6][4] = 2;
		dispositionMursPlateau [6][10] = 2;
		
		// Ligne 8
		dispositionMursPlateau [7][7] = 1;
		dispositionMursPlateau [7][8] = 2;
		dispositionMursPlateau [7][13] = 4;
		
		// Ligne 9
		dispositionMursPlateau [8][7] = 4;
		dispositionMursPlateau [8][8] = 3;
		
		// Ligne 10 : Vide
		
		// Ligne 11
		dispositionMursPlateau [10][5] = 4;
		dispositionMursPlateau [10][11] = 1;
		
		// Ligne 12
		dispositionMursPlateau [11][1] = 1;
		dispositionMursPlateau [11][13] = 4;
		dispositionMursPlateau [11][15] = 3;
		
		// Ligne 13
		dispositionMursPlateau [12][6] = 2;
		dispositionMursPlateau [12][10] = 2;
		
		// Ligne 14 
		dispositionMursPlateau [13][0] = 4;
		dispositionMursPlateau [13][12] = 3;
		
		// Ligne 15
		dispositionMursPlateau [14][3] = 3;
		
		// Ligne 16
		dispositionMursPlateau [15][5] = 3;
		dispositionMursPlateau [15][9] = 3;
		
		
		////// Tableau de generation des objectifs
		// Objectif 1
		posXObjectifs[0] = 1;
		posYObjectifs[0] = 3;
		
		// Objectif 2
		posXObjectifs[1] = 1;
		posYObjectifs[1] = 9;
		
		// Objectif 3
		posXObjectifs[2] = 3;
		posYObjectifs[2] = 6;
		
		// Objectif 4
		posXObjectifs[3] = 3;
		posYObjectifs[3] = 11;
		
		// Objectif 5
		posXObjectifs[4] = 4;
		posYObjectifs[4] = 1;
		
		// Objectif 6
		posXObjectifs[5] = 5;
		posYObjectifs[5] = 14;
		
		// Objectif 7
		posXObjectifs[6] = 6;
		posYObjectifs[6] = 4;
		
		// Objectif 8
		posXObjectifs[7] = 6;
		posYObjectifs[7] = 10;
		
		// Objectif 9
		posXObjectifs[8] = 7;
		posYObjectifs[8] = 13;
		
		// Objectif 10
		posXObjectifs[9] = 10;
		posYObjectifs[9] = 5;
		
		// Objectif 11
		posXObjectifs[10] = 10;
		posYObjectifs[10] = 11;
		
		// Objectif 12
		posXObjectifs[11] = 11;
		posYObjectifs[11] = 1;
		
		// Objectif 13
		posXObjectifs[12] = 11;
		posYObjectifs[12] = 13;
		
		// Objectif 14
		posXObjectifs[13] = 12;
		posYObjectifs[13] = 6;
		
		// Objectif 15
		posXObjectifs[14] = 12;
		posYObjectifs[14] = 10;
		
		// Objectif 16
		posXObjectifs[15] = 13;
		posYObjectifs[15] = 12;
		
		// Objectif 17
		posXObjectifs[16] = 14;
		posYObjectifs[16] = 3;
		
	}
	
	public static void mainPartie() {
		PartieClassique p = new PartieClassique();
		p.mainPartieAux();
	}

	
	public int[][] getDispositionMursPlateau() {
		return dispositionMursPlateau;
	}

	public void setDispositionMursPlateau(int[][] dispositionMursPlateau) {
		this.dispositionMursPlateau = dispositionMursPlateau;
	}

	public int[] getPosXobjectifs() {
		return posXobjectifs;
	}

	public void setPosXobjectifs(int[] posXobjectifs) {
		this.posXobjectifs = posXobjectifs;
	}

	public int[] getPosYobjectifs() {
		return posYobjectifs;
	}

	public void setPosYobjectifs(int[] posYobjectifs) {
		this.posYobjectifs = posYobjectifs;
	}

	@Override
	public void creerPartie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mainPartieAux() {
		// TODO Auto-generated method stub
		
	}

}
