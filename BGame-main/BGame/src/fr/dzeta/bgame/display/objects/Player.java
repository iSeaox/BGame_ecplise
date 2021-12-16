package fr.dzeta.bgame.display.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import fr.dzeta.bgame.display.Displayable;

public class Player extends Displayable{
	
	public Player() {
		super.shapes = new ArrayList<>();
		super.width = 80;
		super.height = 80;
		super.depth = 80;
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
		else {
			if(origin[1] < cursor[1]) {
				rgb = new byte[]{(byte) 0x64, (byte) 0x74, (byte) 0x54};
			}
			else {
				rgb = new byte[]{(byte) 0x6A, (byte) 0x84, (byte) 0x74};
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
	public void render(final double coef, final double angle[], final int[] origin, final int[] cursor) {
		super.shapes.clear();
		final int x = origin[0];
		final int y = origin[1];
		
		final int[] xF1 = {x, x, x + width, x + width};
		final int[] yF1 = {y, y + height, y + height, y};
		super.addShape(new Polygon(xF1, yF1, xF1.length));
		
		final int xBeta = (int) (Math.cos(angle[1]) * (depth *coef));
		final int yBeta = (int) (Math.sin(angle[1]) * (depth *coef));
		if(x < cursor[0]) {
			final int[] xF2 = {x + width
					, x + width + xBeta
					, x + width + xBeta
					, x + width};
			if(y < cursor[1]) {
				final int[] yF2 = {y + height
						, y + height + yBeta
						, y + (int)(xBeta / Math.tan(RIGHT_ANGLE - angle[0]))
						, y};
				super.addShape(new Polygon(xF2, yF2, xF2.length));
				
				final int[] xF3 = {x
						, x + (int)(yBeta / Math.tan(angle[2]))
						, xF2[1]
						, xF2[0]};
				final int[] yF3 = {y + height
						, y + height + yBeta
						, yF2[1]
						, yF2[0]};
				super.addShape(new Polygon(xF3, yF3, xF3.length));
			}
			else {
				final int[] yF2 = {y
						, y - yBeta
						, y + height - (int)(xBeta / Math.tan(RIGHT_ANGLE - angle[0]))
						, y + height};
				super.addShape(new Polygon(xF2, yF2, xF2.length));
				
				final int[] xF3 = {x
						, x + (int)(yBeta / Math.tan(angle[2]))
						, xF2[1]
						, xF2[0]};
				final int[] yF3 = {y
						, y - yBeta
						, yF2[1]
						, yF2[0]};
				super.addShape(new Polygon(xF3, yF3, xF3.length));
			}
			
		}
		else {
			final int[] xF2 = {x
					, x
					, x - xBeta
					, x - xBeta};
			if(y < cursor[1]) {
				final int[] yF2 = {y
						, y + height
						, y + height + yBeta
						, y + (int)(xBeta / Math.tan(RIGHT_ANGLE - angle[0]))};
				super.addShape(new Polygon(xF2, yF2, xF2.length));
				
				final int[] xF3 = {x
						, x + width
						, x + width - (int)(yBeta / Math.tan(angle[2]))
						, xF2[2]};
				final int[] yF3 = {y + height
						, + y + height
						, y + height + yBeta
						, yF2[2]};
				super.addShape(new Polygon(xF3, yF3, xF3.length));
			}
			else {
				final int[] yF2 = {y
						, y + height
						, y + height - (int)(xBeta / Math.tan(RIGHT_ANGLE - angle[0]))
						, y - yBeta};
				super.addShape(new Polygon(xF2, yF2, xF2.length));
				
				final int[] xF3 = {x
						, x + width
						, x + width - (int)(yBeta / Math.tan(angle[2]))
						, x - xBeta};
				final int[] yF3 = {y
						, y
						, y - yBeta
						, y - yBeta};
				super.addShape(new Polygon(xF3, yF3, xF3.length));
			}
		}
		
	}
}
