import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Eraser extends Shape   {

	private ArrayList<Point> pointList;
	private int firstX, lastX, firstY, lastY;

	public Eraser() {
		super();
		pointList = null;
		firstX = 0;
		lastX = 0;
		firstY = 0;
		lastY = 0;

	}


	public ArrayList<Point> getPoints() {
		return pointList;
	}

	public void setPoints(ArrayList<Point> pointList) {
		this.pointList= pointList;
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

	}
}

