package ricochetRobot;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	Grille grille = new Grille();
	
	public Fenetre() {
		
		// Obtiens et configure les dimentions de la fenetre
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setSize(screenWidth/2 + screenWidth/3, screenHeight/2 + screenHeight/3);

		// Definis le titre et les parametres de la fenetre
		this.setTitle("Ma première fenêtre Java");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set un contenu
		this.getContentPane().add(grille, BorderLayout.CENTER);
		this.getContentPane().add(new JButton("Bonjour"), BorderLayout.WEST);
		this.getContentPane().add(new JButton("Bonjour"), BorderLayout.EAST);
		this.getContentPane().add(new JButton("Bonjour"), BorderLayout.NORTH);
		this.getContentPane().add(new JButton("Bonjour"), BorderLayout.SOUTH);

		// Affiche la fenetre
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
	}
}