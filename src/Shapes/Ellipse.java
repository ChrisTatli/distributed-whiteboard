package Shapes;

import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Ellipse extends Shapes.Shape {

	private Point start;
	private Point end;
	private int width;
	private int height;

	public Ellipse(Point start, Point end, Color color, int strokeWidth) {
		super(color,strokeWidth);
		this.start = start;
		this.end = end;
	}

	private void CalculateDimensions(Point start, Point end){
		this.width = Math.abs(start.x - end.x);
		this.height = Math.abs(start.y - end.y);
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
		g2.setStroke(this.getStrokeWidth());
		graphics.setColor(this.getColor());

		Point point = GetStartingPoint(start, end);
		CalculateDimensions(start,end);


		g2.drawOval(point.x, point.y, width, height);
	}


}
