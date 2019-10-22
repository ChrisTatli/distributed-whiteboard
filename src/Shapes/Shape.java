package Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Shape {

	private Color color;
	private int strokeWidth;
	protected boolean isFilled;


	public Shape(Color color, int strokeWidth) {
		this.color = color;
		this.strokeWidth = strokeWidth;
		isFilled = true;
	}

	public Shape(Color color) {
		this.color = color;
		this.strokeWidth = strokeWidth;
		isFilled = true;
	}

	public Shape(){

	}



	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public Color getColor() {
		return color;
	}
	public Stroke getStrokeWidth(){return new BasicStroke(strokeWidth);}

	


	public abstract void draw(Graphics graphics);

}
