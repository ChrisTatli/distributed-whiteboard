package Shapes;

import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Circle extends Shapes.Shape {

	private Point start;
	private Point end;
	private int diameter;

	public Circle(Point start, Point end, Color color, int strokeWidth) {
		super(color,strokeWidth);
		this.start = start;
		this.end = end;
	}

	private void CalculateDiameter(Point start, Point end){
		diameter = Math.abs(start.x - end.x);
	}

	private Point GetStartingPoint(Point start, Point end){
		Point point = new Point();
		point.x = start.x < end.x ? start.x : end.x;
		point.y = start.y < end.y ? start.y : end.y;
		return point;
	}


	@Override
	public void draw(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setColor(this.getColor());
		g2.setStroke(this.getStrokeWidth());
		Point point = GetStartingPoint(start, end);
		CalculateDiameter(start, end);

		g2.drawOval(point.x, point.y, diameter, diameter);
	}


}
