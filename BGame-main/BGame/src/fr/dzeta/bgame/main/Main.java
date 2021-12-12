package fr.dzeta.bgame.main;

import fr.dzeta.bgame.display.handler.GUIHandler;
import fr.dzeta.bgame.time.ApplicationInfo;

public class Main {
	
	private static final int GAME_FPS = 60;
	private static final int GAME_FRAME_INTERVALE = 1000 / GAME_FPS;

	public static boolean RUN = true;
	public static ApplicationInfo info;
	
	static {
		info = new ApplicationInfo().load();
	}
	
	public static void main(String[] args) {
		info.increaseOpenning();
		GUIHandler.init();
		Main.run();
	}
	
	private static void run() {
		long base = 0;
		long elapsed = 0;
		int tick = 0;
		
		while(Main.RUN) {
			if(tick == 60) {
				tick = 0;
				Main.tick(elapsed);
			}
			else {
				tick += 1;
			}
			base = System.currentTimeMillis();
			GUIHandler.render();
			GUIHandler.refresh();
			elapsed = base - System.currentTimeMillis(); 
			if(elapsed < GAME_FRAME_INTERVALE) {
				try {
					Thread.sleep(GAME_FRAME_INTERVALE - elapsed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void flush() {
		info.flush();
	}
	
	private static void tick(final long elapsed) {
		info.increaseCount();
	}

}
