import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Freehand extends Shape   {

	private ArrayList<Point> pointList;


	public Freehand(ArrayList<Point> points, Color color) {
		super(color);
		pointList = points;

	}



	@Override
	Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		return null;
	}

	@Override
	void move(MouseEvent e, Shape selectedShape) {
	}

	@Override
	void resize(MouseEvent e, Shape selectedShape) {

	}

	@Override
	void draw(Graphics graphics) {
		for (int i = 1; i < pointList.size(); i++) {
			Point first = pointList.get(i-1);
			Point second = pointList.get(i);
			graphics.drawLine(first.x,first.y, second.x, second.y );
		}
	}


}
