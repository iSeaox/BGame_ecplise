package fr.dzeta.bgame.display;

import javax.swing.JPanel;

import fr.dzeta.bgame.display.handler.GUIHandler;

import java.awt.Color;
import java.awt.Graphics;

public class Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -892709140099326573L;
	
	public Panel() {
		this.setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, GUIHandler.FRAME_WIDTH, GUIHandler.FRAME_HEIGHT);
		
		g.setColor(Color.BLACK);
		g.drawLine(GUIHandler.FRAME_WIDTH / 2, 0, GUIHandler.FRAME_WIDTH / 2, GUIHandler.FRAME_HEIGHT);
		g.drawLine(0, GUIHandler.FRAME_HEIGHT / 2, GUIHandler.FRAME_WIDTH, GUIHandler.FRAME_HEIGHT / 2);
		
		GUIHandler.getView().display(g);
	}
}
