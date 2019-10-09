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
		color = Color.BLACK;
	}

	public void setFX(int x) {
		this.firstX = x;
	}

	public int getFX() {
		return firstX;
	}

	public void setLX(int x) {
		this.lastX = x;
	}

	public int getLX() {
		return lastX;
	}

	public void setFY(int y) {
		this.firstY = y;
	}

	public int getFY() {
		return firstY;
	}

	public void setLY(int y) {
		this.lastY = y;
	}

	public int getLY() {
		return lastY;
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
		/*int x = e.getX();
		int y = e.getY();
		ArrayList<Point>  points = ((Freehand) selectedShape).getPoints();

		for(Point point: points) {
			int x1 =point.getX()+x;
			int y1 =point.getY()+y;

			pointList2.add(new Point(x1, y1, 5, color));

		}

		((Freehand) selectedShape).setPoints(pointList2);*/
	}

	@Override
	void resize(MouseEvent e, Shape selectedShape) {

	}

	@Override
	void draw(Graphics graphics) {

	}
}

