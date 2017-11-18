package ricochetRobot;

/**
 * Une Partie de Ricochet-Robots Classique<br>
 *
 * 
 * @author Dorian
 *
 */
public class PartieClassique extends Partie {

	private int[][] dispositionMursPlateau = new int [16][16];
	private int [] posXobjectifs = new int [17]; // Case 0 : Objectif 1 / Case 1 : Objectif 2 ...
	private int [] posYobjectifs = new int [17];
	
	public PartieClassique() {
		super();
		// Ligne 1
		dispositionMursPlateau [0][2] = 1;
		dispositionMursPlateau [0][12] = 1;
		
		// Ligne 2
		dispositionMursPlateau [1][3] = 1;
		dispositionMursPlateau [1][9] = 3;
		
		// Ligne 3
		dispositionMursPlateau [3][15] = 3;
		
		// Ligne 4
		dispositionMursPlateau [0][2] = 1;
		dispositionMursPlateau [0][2] = 1;
		dispositionMursPlateau [0][2] = 1;
		
		
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
