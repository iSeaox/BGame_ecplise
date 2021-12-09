package fr.dzeta.bgame.display;

import java.awt.Graphics;
import java.util.List;

import fr.dzeta.bgame.display.handler.GUIHandler;
import fr.dzeta.bgame.utils.Points;

public class View {
	
	private static final double MAX_ANGLE = 45 * Math.PI / 180;
	private static final double MAX_COEF = 0.4D;
	
	private static int[] cursor = {(int)GUIHandler.FRAME_WIDTH / 2, (int)GUIHandler.FRAME_HEIGHT/2};
	
	private List<Displayable> content;
	
	public View(List<Displayable> content) {
		this.content = content;
	}
	
	public void render() {
		for(Displayable entity : this.content) {
			int[] origin = this.computeOrigin(entity.getPosition());
			entity.render(MAX_COEF, this.computeAngle(origin, entity), origin);
		}
	}
	
	public void display(Graphics g) {
		for(Displayable entity : this.content) {
			entity.display(g);
		}
	}
	
	private double computeAngle(int[] origin, Displayable entity) {
		final int tempX = cursor[0] - (origin[0] + entity.getWidth());
		final int tempY = cursor[1] - (origin[1] + entity.getHeight());
		System.out.println(tempX + " " + tempY);
		System.out.println(Math.atan(tempY / tempX));
		System.out.println(cursor[0]+ " "+ cursor[1]);
		return Math.atan(tempY / tempX);
	}
	
	private int[] computeOrigin(final Points point) {
		final int[] pos = {point.getX(), point.getY()};
		return pos;
	}
}
