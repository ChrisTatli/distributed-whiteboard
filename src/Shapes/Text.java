package Shapes;

import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class Text extends Shapes.Shape {
	
	private Point start;
	private String text;

	
	public Text(Point start, String text, Color color){
		super(color);
		this.start = start;
		this.text = text;
	}
	
	



	@Override
	public void draw(Graphics graphics) {
		int spacing = graphics.getFontMetrics().getHeight();
		graphics.setColor(this.getColor());
		graphics.drawString(text, start.x, start.y+spacing);
	}


}
