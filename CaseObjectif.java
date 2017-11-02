package ricochetRobot;

public class CaseObjectif extends Case {
	private int robotRequis;

	public CaseObjectif() {
		super();
		robotRequis = (int) (Math.random() * 4); // Attribut aléatoirement un des 4 Robots
	}

	public int getRobotRequis() {
		return robotRequis;
	}

	public void setRobotRequis(int robotRequis) {
		this.robotRequis = robotRequis;
	}

}
