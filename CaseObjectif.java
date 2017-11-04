package ricochetRobot;

/**
 * Case que le RobotRequis doit atteindre. Le robotRequis par défaut est le Robot d'id = 1
 * 
 * @author Dorian
 *
 */
public class CaseObjectif extends Case {
	private int robotRequis;

	public CaseObjectif() {
		super();
		robotRequis = 1;
	}

	public int getRobotRequis() {
		return robotRequis;
	}

	public void setRobotRequis(int robotRequis) {
		this.robotRequis = robotRequis;
	}

}
