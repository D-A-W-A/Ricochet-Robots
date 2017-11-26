package ricochetRobot;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CaseGrille extends JPanel {

	public CaseGrille() {
	}

	public void paintComponent(Graphics g) {
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(this.getWidth(), this.getHeight(), this.getWidth(), 0);
	}
}