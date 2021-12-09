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
	public void display(Graphics g, final int[] origin, final int[] cursor) {
		Polygon[] copy = super.shapes.toArray(new Polygon[super.shapes.size()]);
		byte[] rgb = new byte[0];
		if(origin[0] < cursor[0]) {
			if(origin[1] < cursor[1]) {
				rgb = new byte[]{(byte) 0x74, (byte) 0x64, (byte) 0x54};
			}
			else {
				rgb = new byte[]{(byte) 0x74, (byte) 0x64, (byte) 0x82};
			}
		}
		byte index = 0;
		for(Polygon p : copy) {
			int temp = rgb[index] & 0xFF;
			g.setColor(new Color(temp, temp, temp));
			g.fillPolygon(p);
			index += 1;
		}
	}
	
	@Override
	public void render(final double coef, final double angle, final int[] origin, final int[] cursor) {
		super.shapes.clear();
		final int x = origin[0];
		final int y = origin[1];
		
		final int[] xF1 = {x, x, x + width, x + width};
		final int[] yF1 = {y, y + height, y + height, y};
		super.addShape(new Polygon(xF1, yF1, xF1.length));
		
		if(x < cursor[0]) {
			final int[] xF2 = {x + width
					, x + width + (int)(Math.cos(angle) * (depth * coef))
					, x + width + (int)(Math.cos(angle) * (depth * coef))
					, x + width};
			final int[] yF2 = {y + height
					, y + height + (int)(Math.sin(angle) * (depth * coef))
					, y + (int)(Math.sin(angle) * (depth * coef))
					, y};
			super.addShape(new Polygon(xF2, yF2, xF2.length));
			
			final double beta = RIGHT_ANGLE - angle;
			if(y < cursor[1]) {
				
				final int[] xF3 = {x
						, x + width
						, x + width + (int)(Math.sin(beta) * (depth * coef))
						, x + (int)(Math.sin(beta) * (depth * coef))};
				final int[] yF3 = {y + height
						, y + height
						, y + height + (int)(Math.cos(beta) * (depth * coef))
						, y + height + (int)(Math.cos(beta) * (depth * coef))};
				super.addShape(new Polygon(xF3, yF3, xF3.length));
			}
			else {
				
				final int[] xF3 = {x
						, x + width
						, x + width + (int)(Math.sin(beta) * (depth * coef))
						, x + (int)(Math.sin(beta) * (depth * coef))};
				final int[] yF3 = {y
						, y
						, y + (int)(Math.cos(beta) * (depth * coef))
						, y + (int)(Math.cos(beta) * (depth * coef))};
				super.addShape(new Polygon(xF3, yF3, xF3.length));
			}
		}
		
	}
}
