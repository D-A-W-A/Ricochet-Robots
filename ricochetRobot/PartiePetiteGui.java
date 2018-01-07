package ricochetRobot;


/**
 * Genere une partie avec un petit plateau
 * @author Dorian
 *
 */
public class PartiePetiteGui extends PartieClassiqueGui {

	public PartiePetiteGui() {
		super();

		// ----- Init des tableaux ----
		int[][] m = new int[8][8];
		int[] ox = new int[5];
		int[] oy = new int[5];

		// ----- Génération des murs ----
		// Ligne 1
		m[0][0] = 3;
		m[0][3] = 4;

		// Ligne 2
		m [1][6] = 3;
		
		// Ligne 3
		m[2][1] = 1;
		
		// Ligne 6
		m[5][6] = 2;
		
		// Ligne 7
		m[6][0] = 1;
		m[6][2] = 4;
		
		// Ligne 8
		m[7][7] = 1;
		
		// ---- Init des objectifs
		// Obj 1
		ox[0] = 0;
		oy[0] = 3;
		
		// Obj 2
		ox [1] = 1;
		oy[1] = 6;
		
		// Obj 3
		ox [2] = 2;
		oy [2] = 1;
		
		// Obj 4
		ox[3] = 5;
		oy[3] = 6;
		
		// Obj 5
		ox[4] = 6;
		oy[4] = 2;
		
		
		// ---- Attribution a la partie ----
		setDispositionMursPlateau(m);
		setPosXObjectifs(ox);
		setPosYObjectifs(oy);
	}
	
	public void placerRobotPartie(Plateau p) {
		int posRobot[] = p.placerRobotRandom(0);
		while ((posRobot[0] == 0 && posRobot[1] == 0) || (posRobot[0] == 7 && posRobot[1] == 7)) {
			p.supprimerRobot(0);
			posRobot = p.placerRobotRandom(0);
		}
	}
	
	public void actionRecommencer() {
		dispose();
		mainPartie();
	}

	
	public static void mainPartie() {
		PartiePetiteGui p = new PartiePetiteGui();
		p.mainPartieAux();
	}
	
	public static void main(String[] args) {
		PartiePetiteGui.mainPartie();
	}

}
