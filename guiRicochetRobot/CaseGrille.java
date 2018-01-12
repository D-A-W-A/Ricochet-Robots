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
 * Partie graphique d'une case
 * 
 * @author Dorian
 *
 * 
 */
public class CaseGrille extends JButton implements MouseListener {
	private int posX;
	private int posY;

	/**
	 * 1 : Gauche, 2 : Haut, 3 : Droite, 4 : Bas
	 */
	private int isNext = 0;

	/**
	 * La case s'affichera differement si elle possede un robot ou si c'est
	 * l'objectif
	 */
	private int hasRobot = 0;
	private boolean isObjective = false;
	private boolean selected = false;

	/**
	 * - 1 : Un angle Gauche-Haut<br>
	 * - 2 : Un angle Haut-Droite<br>
	 * - 3 : Un angle Droite-Bas<br>
	 * - 4 : Un angle Bas-Gauche<br>
	 * - Autre : Une case normale, sans aucuns murs<br>
	 * <br>
	 */
	private int murs = 0;

	/**
	 * Change lorsque la souris passe au dessus
	 */
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

	public int isNext() {
		return isNext;
	}

	public void setNext(int isNext) {
		this.isNext = isNext;
	}

	public int getHasRobot() {
		return hasRobot;
	}

	public void setHasRobot(int hasRobot) {
		this.hasRobot = hasRobot;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Affiche la case avec les bonnes formes, couleurs, etc.
	 */
	public void paintComponent(Graphics g) {
		// Reset la case
		resetCase(g);

		// Applique la couleur du hover
		hoverCase(g);
		backgroundSelected(g);

		// Dessine les bords de la case
		bordCase(g);

		// Dessine le Robot si il est sur la case
		dessineRobot(g);

		// Dessine l'objectif
		dessineObjectif(g);

		// Dessine les murs s'il y en a
		dessineMurs(g);
	}

	/**
	 * Reset la case en la coloriant en blanc
	 * 
	 * @param g
	 */
	private void resetCase(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Met la case selectionnee en evidence
	 * 
	 * @param g
	 */
	private void backgroundSelected(Graphics g) {
		if (selected) {
			//g.setColor(Color.decode("#6495ED"));
			g.setColor(Color.red);
			g.fillRect(10, this.getHeight() - 5, this.getWidth()-20, 5);
			g.fillRect(10, 0, this.getWidth()-20, 5);
			g.fillRect(getWidth()-5, 10, 5, getHeight()-20);
			g.fillRect(0, 10, 5, getHeight()-20);
		}
	}

	/**
	 * Si la souris est au dessus, la case est coloriee<br>
	 * - En rouge si elle est next <br>
	 * - En gris sinon
	 * 
	 * @param g
	 */
	private void hoverCase(Graphics g) {
		if (hover == 1 && hasRobot != 0) {
			g.setColor(Color.decode("#6495ED"));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		} else if (hover == 1 && isNext != 0) {
			g.setColor(Color.decode("#4d0000"));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

		} else if (hover == 1) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

	}

	/**
	 * Dessine le robot si il est sur la case
	 * 
	 * @param g
	 */
	private void dessineRobot(Graphics g) {
		if (this.hasRobot == 1) {
			g.setColor(Color.blue);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
		}
		else if (hasRobot == 2) {
			g.setColor(Color.red);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
		}
		else if (hasRobot == 3) {
			g.setColor(Color.yellow);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
		}
		else if (hasRobot == 4) {
			g.setColor(Color.green);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
		}
	}

	/**
	 * Dessine l'objectif
	 * 
	 * @param g
	 */
	private void dessineObjectif(Graphics g) {
		if (this.isObjective && hasRobot == 0) {
			g.setColor(Color.red);
			g.fillOval(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
			g.setColor(Color.white);
			g.fillOval((this.getWidth() / 4) + this.getWidth() / 10, (this.getHeight() / 4) + this.getWidth() / 10,
					(this.getWidth() / 2) - this.getHeight() / 5, (this.getHeight() / 2) - this.getHeight() / 5);
		}
	}

	/**
	 * Dessine les murs s'il y en a
	 * 
	 * @param g
	 */
	private void dessineMurs(Graphics g) {
		g.setColor(Color.black);
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

	/**
	 * Dessine les bords de la case
	 * 
	 * @param g
	 */
	private void bordCase(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}

	@Override
	/**
	 * Case cliquee
	 */
	public void mouseClicked(MouseEvent e) {
		// System.out.println("COUCOU");

	}

	@Override
	/**
	 * Case survolee
	 */
	public void mouseEntered(MouseEvent e) {
		hover = 1;
		this.repaint();

	}

	@Override
	/**
	 * Case quitee
	 */
	public void mouseExited(MouseEvent e) {
		hover = 0;
		this.repaint();

	}

	@Override
	/**
	 * Clic prolonge
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Clic lache
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}