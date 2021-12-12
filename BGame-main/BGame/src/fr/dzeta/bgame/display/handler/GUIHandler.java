package fr.dzeta.bgame.display.handler;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import fr.dzeta.bgame.display.Displayable;
import fr.dzeta.bgame.display.Frame;
import fr.dzeta.bgame.display.Panel;
import fr.dzeta.bgame.display.View;
import fr.dzeta.bgame.display.objects.Player;
import fr.dzeta.bgame.listener.KeyHandler;
import fr.dzeta.bgame.main.Main;
import fr.dzeta.bgame.time.TimeUtils;
import fr.dzeta.bgame.utils.Points;

public class GUIHandler {
	
	public static final int FRAME_WIDTH = 854;
	public static final int FRAME_HEIGHT = 480;
	//public static final int FRAME_WIDTH = 1280;
	//public static final int FRAME_HEIGHT = 720;
	
	
	private static View view;
	private static List<Displayable> viewContents;
	
	private static Frame frame;
	private static Panel panel;
	private static JLabel timer;
	
	private static Player player;
	
	public static void render() {
		GUIHandler.view.setContent(GUIHandler.viewContents);
		GUIHandler.view.render();
	}
	
	public static void refresh() {
		GUIHandler.timer.setText("Openning: "+Main.info.getOpenning()+"  timer: "
				+TimeUtils.parseSecond(Main.info.getCount()) + " coef: "+View.MAX_COEF);
		GUIHandler.frame.repaint();
	}
	
	public static void init() {
		GUIHandler.viewContents = new ArrayList<>();
		
		GUIHandler.frame = new Frame();
		GUIHandler.panel = new Panel();
		frame.setContentPane(GUIHandler.panel);
		frame.addKeyListener(new KeyHandler());
		
		player = new Player();
		player.setPosition(new Points(100, 100, 0));
		GUIHandler.viewContents.add(player);
		
		Player player2 = new Player();
		player2.setPosition(new Points(300, 0, 0));
		GUIHandler.viewContents.add(player2);
		
		Player player3 = new Player();
		player3.setPosition(new Points(700, 0, 0));
		GUIHandler.viewContents.add(player3);
		
		Player player4 = new Player();
		player4.setPosition(new Points(100, 350, 0));
		GUIHandler.viewContents.add(player4);
		
		Player player5 = new Player();
		player5.setPosition(new Points(600, 90, 0));
		GUIHandler.viewContents.add(player5);
		
		Player player6 = new Player();
		player6.setPosition(new Points(600 + player6.getWidth(), 300, 0));
		GUIHandler.viewContents.add(player6);
		
		Player player7 = new Player();
		player7.setPosition(new Points(600, 300, 0));
		GUIHandler.viewContents.add(player7);
		GUIHandler.view = new View(GUIHandler.viewContents);
		
		timer = new JLabel("Openning: "+Main.info.getOpenning()+"  timer: "
				+TimeUtils.parseSecond(Main.info.getCount()));
		timer.setBounds(10, 0, (timer.getText().length()) * 10, 30);
		GUIHandler.panel.add(timer);
		
		GUIHandler.frame.setVisible(true);
	}

	public static View getView() {
		return view;
	}
}
