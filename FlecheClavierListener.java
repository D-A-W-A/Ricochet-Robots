package ricochetRobot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Premier Listener pour la version du jeu sous terminal.<br>
 * Ce listener ecoute les fleches du clavier et fait une action lorque l'une
 * d'elle est pressee
 * 
 * @author Dorian
 *
 */
public class FlecheClavierListener extends JFrame implements KeyListener {

	JFrame fenetreListener;

	public FlecheClavierListener() {
		fenetreListener = new JFrame();
		fenetreListener.addKeyListener(this);
	}
	
	public void lancer() {
		fenetreListener.setVisible(true);
	}
	
	public void pause () {
		fenetreListener.setVisible(false);
	}
	
	public void stop () {
		setVisible(false); //you can't see me!
		dispose(); //Destroy the JFrame object
	}

	// VK_DOWN
	// VK_LEFT
	// VK_RIGHT
	// VK_UP
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_DOWN) {
			System.out.println("BAS");
		}
		if (code == KeyEvent.VK_UP) {
			System.out.println("HAUT");
		}
		if (code == KeyEvent.VK_LEFT) {
			System.out.println("GAUCHE");
		}
		if (code == KeyEvent.VK_RIGHT) {
			System.out.println("DROITE");
		}
		if (code == KeyEvent.VK_ESCAPE) {
			pause();
			System.exit(1);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void main (String[] args) {
		FlecheClavierListener l = new FlecheClavierListener();
		l.lancer();
	}

}
