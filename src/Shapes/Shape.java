package Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Shape {

	private int color;
	private int strokeWidth;


	public Shape(Color color, int strokeWidth) {
		this.color = color.getRGB();
		this.strokeWidth = strokeWidth;

	}

	public Shape(Color color) {
		this.color = color.getRGB();
		this.strokeWidth = strokeWidth;

	}
	


	public Color getColor() {
		return new Color(color);
	}
	public Stroke getStrokeWidth(){return new BasicStroke(strokeWidth);}

	


	public abstract void draw(Graphics graphics);

}
