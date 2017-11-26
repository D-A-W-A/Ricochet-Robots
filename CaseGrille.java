package ricochetRobot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class CaseGrille extends JPanel implements MouseListener {
	private int posX;
	private int posY;
	int hover = 0;
	
	public CaseGrille() {
		posX = 0;
		posY = 0;
		this.addMouseListener(this);
	}

	public CaseGrille(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.addMouseListener(this);
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

	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (hover == 1) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("COUCOU");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		hover=1;
		this.repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		hover=0;
		this.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}