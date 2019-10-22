package Shapes;

import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Freehand extends Shapes.Shape {

	private ArrayList<Point> pointList;


	public Freehand(ArrayList<Point> points, Color color, int strokeWidth) {
		super(color, strokeWidth);
		pointList = points;

	}



	@Override
	public void draw(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setStroke(this.getStrokeWidth());
		g2.setColor(this.getColor());
		for (int i = 0; i < pointList.size()-1; i++) {
			Point first = pointList.get(i);
			Point second = pointList.get(i+1);
			g2.drawLine(first.x,first.y, second.x, second.y );
		}
	}


}
