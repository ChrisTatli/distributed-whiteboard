package Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.ParsePosition;
import java.util.ArrayList;


public class Square extends Shape {
	private Point start;
	private Point end;
	private int length;


	public Square(Point start, Point end, Color color, int strokeWidth){
		super(color, strokeWidth);
		this.start = start;
		this.end = end;
	}

	private void CalculateLength(Point start, Point end){
		this.length = Math.abs(start.x - end.x);
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
		Point point = GetStartingPoint(start,end);
		CalculateLength(start,end);


		g2.drawRect(point.x, point.y, length, length);
	}

	
}
