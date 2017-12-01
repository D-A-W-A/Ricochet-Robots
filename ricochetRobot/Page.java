package ricochetRobot;

import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;

import javax.swing.*;

public class Page extends JPanel {

	JFrame jftest = new JFrame("Ricochet Robot");
	private final int size = 550;
	
	public Page(int size) {
		jftest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jftest.setContentPane(this);
		Toolkit tk = Toolkit.getDefaultToolkit();
		// Dimension screenSize = tk.getScreenSize();
		// int screenHeight = screenSize.height;
		// int screenWidth = screenSize.width;
		// jftest.setSize(screenWidth/2, screenHeight/2);
		// jftest.setLocation(screenWidth / 4, screenHeight / 4);
		// jftest.pack();
		jftest.setSize(size,size);
		// int placeWidth = tk.getScreenSize().width/2;
		// int placeHeight = tk.getScreenSize().height/2;
		// jftest.setLocation(placeHeight, placeWidth);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i<16 ; i++) {
			int coordx = getWidth() * i / 16;
			int coordy = getHeight() * i / 16;
			g.drawLine(coordx, 0, coordx, getWidth());
			g.drawLine(0, coordy, getHeight(), coordy);
		}
	} 

	//Graphics2D g2 = (Graphics2D) g
	//g2.addComponent(new Line2D.Float(...))

	public static void main(String args[]) {
		Page newPage = new Page(550);
		newPage.jftest.setVisible(true);
	}

}