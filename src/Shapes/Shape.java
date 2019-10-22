package Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Shape implements Serializable {

	private Color color;
	private int strokeWidth;


	public Shape(Color color, int strokeWidth) {
		this.color = color;
		this.strokeWidth = strokeWidth;

	}

	public Shape(Color color) {
		this.color =color;
		this.strokeWidth = strokeWidth;

	}
	


	public Color getColor() {
		return color;
	}
	public Stroke getStrokeWidth(){return new BasicStroke(strokeWidth);}

	


	public abstract void draw(Graphics graphics);

}
