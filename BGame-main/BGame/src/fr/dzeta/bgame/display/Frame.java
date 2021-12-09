package fr.dzeta.bgame.display;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.dzeta.bgame.display.handler.GUIHandler;
import fr.dzeta.bgame.main.Main;
public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2850809065384245767L;
	
	private static final String ICON_PATH = "./lib/icon/icon.png";
	
	public Frame() {
		this.setSize(GUIHandler.FRAME_WIDTH, GUIHandler.FRAME_HEIGHT);
		this.setTitle("Basic Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Main.flush();
				super.windowClosing(e);
			}
			
		});
		
		ImageIcon icon = new ImageIcon(Frame.ICON_PATH);
		Image img = icon.getImage();
		this.setIconImage(img);
	}	
}
