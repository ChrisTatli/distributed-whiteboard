import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Freehand extends Shape   {

	private ArrayList<Point> pointList;
	private int x1, x2, y1, y2;

	public Freehand() {
		super();
		pointList = null;
		x1 = 0;
		x2 = 0;
		y1 = 0;
		y2 = 0;
	}

	public void setFX(int x) {
		this.x1 = x;
	}

	public int getFX() {
		return x1;
	}

	public void setLX(int x) {
		this.x2 = x;
	}

	public int getLX() {
		return x2;
	}

	public void setFY(int y) {
		this.y1 = y;
	}

	public int getFY() {
		return y1;
	}

	public void setLY(int y) {
		this.y2 = y;
	}

	public int getLY() {
		return y2;
	}

	public ArrayList<Point> getPoints() {
		return pointList;
	}

	public void setPoints(ArrayList<Point> pointList) {
		this.pointList= pointList;
	}



	@Override
	public Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		return null;
	}

	@Override
	public void move(MouseEvent e, Shape selectedShape) {

	}

	@Override
	public void resize(MouseEvent e, Shape selectedShape) {

	}



}
