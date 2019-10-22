package Shapes;

import Shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Eraser extends Shapes.Shape {

	private ArrayList<Point> pointList;
	private int size;
	private static final int scaleFactor = 10;


	public Eraser(ArrayList points, int size, Color color) {
		super(color);
		this.size = size * scaleFactor;
		pointList = points;
	}



	private void erase(Graphics graphics, Point point, int size){
		graphics.fillRect(point.x, point.y, size, size);
	}

	@Override
	public void  draw(Graphics graphics) {
		//Keep the color that we were using so we dont have to reselect it
		Color temp = graphics.getColor();
		graphics.setColor(this.getColor());
		for (int i = 0; i < pointList.size()-1; i++) {
			erase(graphics,pointList.get(i), size);
		}
		graphics.setColor(temp);
	}
}

