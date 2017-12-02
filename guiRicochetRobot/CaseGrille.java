package guiRicochetRobot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Partie graphique d'une case : Elle n'affiche pas encore les murs mais bientot
 * :)
 * 
 * @author Dorian
 *
 * 
 */
public class CaseGrille extends JButton implements MouseListener {
	private int posX;
	private int posY;

	private boolean isNext = false;
	private boolean hasRobot = false;
	private boolean isObjective = false;

	/**
	 * - 1 : Un angle Gauche-Haut<br>
	 * - 2 : Un angle Haut-Droite<br>
	 * - 3 : Un angle Droite-Bas<br>
	 * - 4 : Un angle Bas-Gauche<br>
	 * - Autre : Une case normale, sans aucuns murs<br>
	 * <br>
	 */
	private int murs = 0;

	// Change lorsque la souris passe au dessus
	int hover = 0;

	public CaseGrille() {
		posX = 0;
		posY = 0;
		this.addMouseListener(this);
	}

	public CaseGrille(int x, int y, int mur) {
		this.posX = x;
		this.posY = y;
		this.murs = mur;
		this.addMouseListener(this);

	}

	public boolean isObjective() {
		return isObjective;
	}

	public void setObjective(boolean isObjective) {
		this.isObjective = isObjective;
	}

	public int getMurs() {
		return murs;
	}

	public void setMurs(int murs) {
		this.murs = murs;
		this.repaint();
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

	public boolean isNext() {
		return isNext;
	}

	public void setNext(boolean isNext) {
		this.isNext = isNext;
	}

	public boolean isHasRobot() {
		return hasRobot;
	}

	public void setHasRobot(boolean hasRobot) {
		this.hasRobot = hasRobot;
	}

	public void paintComponent(Graphics g) {
		
		// Reset la case en la coloriant en blanc
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Si la souris est au dessus, la case est coloriee en gris
		if (hover == 1 && isNext) {
			g.setColor(Color.decode("#4d0000"));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		} else if (hover == 1) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		// Dessine les bords de la case
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());

		// Dessine le Robot si il est sur la case
		if (this.hasRobot) {
			g.setColor(Color.blue);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
		}

		if (this.isObjective) {
			g.setColor(Color.red);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
			g.setColor(Color.white);
			g.fillOval((this.getWidth() / 4)+10, (this.getHeight() / 4)+10, (this.getWidth() / 2)-20,( this.getHeight() / 2)-20);
		}
		g.setColor(Color.black);
		// Dessine les murs s'il y en a
		if (this.murs == 1) {
			g.fillRect(0, 0, 5, this.getHeight());
			g.fillRect(0, 0, this.getWidth(), 5);
		}
		if (this.murs == 2) {
			g.fillRect(0, 0, this.getWidth(), 5);
			g.fillRect(this.getWidth() - 5, 0, this.getWidth(), this.getHeight());
		}
		if (this.murs == 3) {
			g.fillRect(this.getWidth() - 5, 0, this.getWidth(), this.getHeight());
			g.fillRect(0, this.getHeight() - 5, this.getWidth(), this.getHeight());
		}
		if (this.murs == 4) {
			g.fillRect(0, this.getHeight() - 5, this.getWidth(), this.getHeight());
			g.fillRect(0, 0, 5, this.getHeight());
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("COUCOU");

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		hover = 1;
		this.repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		hover = 0;
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