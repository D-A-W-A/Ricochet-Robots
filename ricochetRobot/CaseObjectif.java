package ricochetRobot;

/**
 * Case que le RobotRequis doit atteindre. Le robotRequis par defaut est le Robot d'id = 1
 * 
 * @author Dorian
 *
 */
public class CaseObjectif extends Case {
	private char robotRequis;

	public CaseObjectif() {
		super();
		robotRequis = 'R';

	}

	public int getRobotRequis() {
		return robotRequis;
	}

	public void setRobotRequis(char robotRequis) {
		this.robotRequis = robotRequis;
	}
	
	public boolean reussite() {
		return (getRobot() != null) && getRobot().getCouleur() == robotRequis;
	}

	public String toString() {
		if (!this.estVide()) {
			if (estOccupe())
				return getRobot().toString();
			else
				return ("O");
		} else {
			return "";
		}
	}

}
