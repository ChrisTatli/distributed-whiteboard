package Shapes;

import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Line extends Shapes.Shape {

	private Point start;
	private Point end;


	public Line(Point start, Point end, Color color, int strokeWidth) {
		super(color, strokeWidth);
		this.start = start;
		this.end = end;
	}



	@Override
	public void draw(Graphics graphics)
	{
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setStroke(this.getStrokeWidth());
		g2.setColor(this.getColor());
		g2.drawLine(start.x, start.y, end.x, end.y);
	}


}
