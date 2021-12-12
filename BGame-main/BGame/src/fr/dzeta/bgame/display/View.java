package fr.dzeta.bgame.display;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import fr.dzeta.bgame.display.handler.GUIHandler;
import fr.dzeta.bgame.utils.Points;

public class View {
	
	public static final double MAX_COEF = 0.5D;
	
	private static int[] cursor = {(int)GUIHandler.FRAME_WIDTH / 2, (int)GUIHandler.FRAME_HEIGHT/2};
	private static int maxCursorDistance = (int) Math.sqrt(cursor[0] * cursor[0] + cursor[1] * cursor[1]);
	
	private List<Displayable> content;
	
	public View() {
		this.content = new ArrayList<>();
	}
	
	public View(List<Displayable> content) {
		this.content = content;
	}
	
	public void render() {
		for(Displayable entity : this.content) {
			int[] origin = this.computeOrigin(entity.getPosition());
			entity.render(this.computeCoef(origin, entity), this.computeAngle(origin, entity), origin, cursor);
		}
	}
	
	public void display(Graphics g) {
		for(Displayable entity : this.content) {
			entity.display(g, this.computeOrigin(entity.getPosition()), cursor);
		}
	}
	
	private double computeCoef(int[] origin, Displayable entity) {
		final double tempX = Math.abs(cursor[0] - (origin[0] + entity.getWidth()));
		final double tempY = Math.abs(cursor[1] - (origin[1] + entity.getHeight()));
		return (Math.sqrt(tempX * tempX + tempY * tempY) * MAX_COEF) / maxCursorDistance;
	}
	
	private double computeAngle(int[] origin, Displayable entity) {
		final double tempX = Math.abs(cursor[0] - (origin[0] + entity.getWidth()));
		final double tempY = Math.abs(cursor[1] - (origin[1] + entity.getHeight()));
		return Math.atan(tempY / tempX);
	}
	
	private int[] computeOrigin(final Points point) {
		final int[] pos = {point.getX(), point.getY()};
		return pos;
	}
	
	public void setContent(List<Displayable> content) {
		this.content = content;
	}
	
}
