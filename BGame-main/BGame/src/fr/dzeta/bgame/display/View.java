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
			int[] origin = this.computeOrigin(entity.getPosition(), entity);
			entity.render(this.computeCoef(origin, entity), this.computeAngle(origin, entity), origin, cursor);
		}
	}
	
	public void display(Graphics g) {
		for(Displayable entity : this.content) {
			entity.display(g, this.computeOrigin(entity.getPosition(), entity), cursor);
		}
	}
	
	private int[] computeOrigin(final Points point, Displayable entity) {
		if(point.getZ() != 0) {
			final int[] pointC = {point.getX() + entity.getWidth(), point.getY() + entity.getHeight()};
			final int[] temp = {point.getX(), point.getY()};
			final double beta = this.computeAngle(temp, entity)[1];
			final int[] pointHomotC = {pointC[0] + (int)(Math.cos(beta) * this.computeCoef(pointC) * point.getZ())
					, pointC[1] + (int)(Math.sin(beta) * this.computeCoef(pointC) * point.getZ())};
			final double homotCoef = this.distance(pointHomotC, cursor) / this.distance(pointC, cursor);
			final int homotWidth = (int) (entity.getWidth() * homotCoef); 
			final int homotHeight = (int) (entity.getHeight() * homotCoef);
			final int homotDepth = (int)(entity.getDepth() * homotCoef);
			entity.setDisplayWidth(homotWidth);
			entity.setDisplayHeight(homotHeight);
			entity.setDisplayDepth(homotDepth);
			final int[] pos = {pointHomotC[0] - homotWidth, pointHomotC[1] - homotHeight};
			return pos;
		}
		entity.setDisplayWidth(entity.getWidth());
		entity.setDisplayHeight(entity.getHeight());
		entity.setDisplayDepth(entity.getDepth());
		final int[] pos = {point.getX(), point.getY()};
		return pos;
		
	}
	
	private double computeCoef(int[] origin, Displayable entity) {
		final double tempX = Math.abs(cursor[0] - (origin[0] + entity.getWidth()));
		final double tempY = Math.abs(cursor[1] - (origin[1] + entity.getHeight()));
		return (Math.sqrt(tempX * tempX + tempY * tempY) * MAX_COEF) / maxCursorDistance;
	}
	
	private double distance(int[] point1, int[] point2) {
		return Math.sqrt((point2[0] - point1[0])*(point2[0] - point1[0]) - (point2[1] - point1[1])*(point2[1] - point1[1]));
	}
	
	private double computeCoef(int[] point) {
		final double tempX = Math.abs(cursor[0] - point[0]);
		final double tempY = Math.abs(cursor[1] - point[1]);
		return (Math.sqrt(tempX * tempX + tempY * tempY) * MAX_COEF) / maxCursorDistance;
	}
	
	private double[] computeAngle(int[] origin, Displayable entity) {
		final int height = entity.getHeight();
		final int width = entity.getWidth();
		double[] coefs = new double[3];
		
		int[] alphaVertex = new int[2];
		int[] betaVertex = new int[2];
		int[] gammaVertex = new int[2];
		
		if(origin[0] < cursor[0]) {
			if(origin[1] < cursor[1]) {
				alphaVertex[0] = origin[0] + width;
				alphaVertex[1] = origin[1];
				
				betaVertex[0] = origin[0] + width;
				betaVertex[1] = origin[1] + height;
				
				gammaVertex[0] = origin[0];
				gammaVertex[1] = origin[1] + height;
			}
			else {
				alphaVertex[0] = origin[0] + width;
				alphaVertex[1] = origin[1] + height;
				
				betaVertex[0] = origin[0] + width;
				betaVertex[1] = origin[1];
				
				gammaVertex[0] = origin[0];
				gammaVertex[1] = origin[1];
			}
		}
		else {
			if(origin[1] < cursor[1]) {
				alphaVertex[0] = origin[0];
				alphaVertex[1] = origin[1];
				
				betaVertex[0] = origin[0];
				betaVertex[1] = origin[1] + height;
				
				gammaVertex[0] = origin[0] + width;
				gammaVertex[1] = origin[1] + height;
			}
			else {
				alphaVertex[0] = origin[0];
				alphaVertex[1] = origin[1] + height;
				
				betaVertex[0] = origin[0];
				betaVertex[1] = origin[1];
				
				gammaVertex[0] = origin[0] + width;
				gammaVertex[1] = origin[1];
			}
		}
		
		double tempX;
		double tempY;
		/* COMPUTING ALPHA*/
		tempX = Math.abs(cursor[0] - alphaVertex[0]);
		tempY = Math.abs(cursor[1] - alphaVertex[1]);
		coefs[0] = Math.atan(tempY / tempX);
		
		/* COMPUTING BETA*/
		tempX = Math.abs(cursor[0] - betaVertex[0]);
		tempY = Math.abs(cursor[1] - betaVertex[1]);
		coefs[1] = Math.atan(tempY / tempX);
		
		/* COMPUTING GAMMA*/
		tempX = Math.abs(cursor[0] - gammaVertex[0]);
		tempY = Math.abs(cursor[1] - gammaVertex[1]);
		coefs[2] = Math.atan(tempY / tempX);
		
		return coefs;
	}
	
	public void setContent(List<Displayable> content) {
		this.content = content;
	}
	
}
