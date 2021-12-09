package fr.dzeta.bgame.display.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import fr.dzeta.bgame.display.Displayable;

public class Player extends Displayable{
	
	public Player() {
		super.shapes = new ArrayList<>();
		super.width = 50;
		super.height = 50;
		super.depth = 50;
	}
	
	@Override
	public void display(Graphics g) {
		Polygon[] copy = super.shapes.toArray(new Polygon[super.shapes.size()]);
		for(Polygon p : copy) {
			g.setColor(Color.DARK_GRAY);
			g.fillPolygon(p);
		}
	}
	
	@Override
	public void render(final double coef, final double angle, final int[] origin) {
		final int x = origin[0];
		final int y = origin[1];
		
		final int[] xF1 = {x, x, x + width, x + width};
		final int[] yF1 = {y, y + height, y + height, y};
		super.addShape(new Polygon(xF1, yF1, xF1.length));
		
		final int[] xF2 = {x + width
				, x + width + (int)(Math.cos(angle) * (depth * coef))
				, x + width + (int)(Math.cos(angle) * (depth * coef))
				, x + width};
		final int[] yF2 = {y + height
				, y + height + (int)(Math.sin(angle) * (depth * coef))
				, y + (int)(Math.sin(angle) * (depth * coef))
				, y};
		super.addShape(new Polygon(xF2, yF2, xF2.length));
	}
}
