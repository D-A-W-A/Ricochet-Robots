package ricochetRobot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * Partie graphique d'une case : Elle n'affiche pas encore les murs mais bientot :)
 *  
 * @author Dorian
 *
 *	
 */
public class CaseGrille extends JButton implements MouseListener {
	private int posX;
	private int posY;
	
	// Change lorsque la souris passe au dessus
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
		// Reset la case en la coloriant en blanc
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Si la souris est au dessus, la case est coloriee en gris
		if (hover == 1) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
		// Dessine les bords de la case
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("COUCOU");

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