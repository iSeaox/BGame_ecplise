package fr.dzeta.bgame.display;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

import fr.dzeta.bgame.utils.Points;

public abstract class Displayable {
	
	public static final double RIGHT_ANGLE = 90 * Math.PI / 180;
	
	protected List<Polygon> shapes;
	protected Points position;
	
	protected int width;
	protected int height;
	protected int depth;
	
	public abstract void display(Graphics g, final int[] origin, final int[] cursor);
	public abstract void render(final double coef, final double angle[], final int[] origin, final int[] cursor);
	
	public List<Polygon> getShapes() {
		return shapes;
	}
	
	public void addShape(final Polygon polygon) {
		this.shapes.add(polygon);
	}
	
	public Points getPosition() {
		return position;
	}
	
	public void setPosition(Points position) {
		this.position = position;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
}
