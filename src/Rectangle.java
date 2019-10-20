import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Rectangle extends Shape {

	protected int x, y, width, hight;

	public Rectangle() {
		super();
		x = 0;
		y = 0;
		width = 0;
		hight = 0;
	}

	public int getRectangleX() {
		return x;
	}

	public void setRectangleX(int x) {
		this.x = x;
	}

	public int getRectangleY() {
		return y;
	}

	public void setRectangleY(int y) {
		this.y = y;
	}

	public int getRectangleWidth() {
		return width;
	}

	public void setRectangleWidth(int width) {
		this.width = width;
	}

	public int getRectangleHight() {
		return hight;
	}

	public void setRectangleHight(int hight) {
		this.hight = hight;
	}


	@Override
	public Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		int x = e.getX();
		int y = e.getY();
		int rX = ((Rectangle) currentShapes.get(i)).getRectangleX();
		int rY = ((Rectangle) currentShapes.get(i)).getRectangleY();
		int rWidth = ((Rectangle) currentShapes.get(i)).getRectangleWidth();
		int rHight = ((Rectangle) currentShapes.get(i)).getRectangleHight();
		if ((x >= rX && x <= (rX + rWidth)) && (y >= rY && y <= (rY + rHight))) {
			return currentShapes.get(i);
		}
		return null;
	}

	@Override
	public void move(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int width = ((Rectangle) selectedShape).getRectangleWidth();
		int hight = ((Rectangle) selectedShape).getRectangleHight();
		((Rectangle) selectedShape).setRectangleX(x - (width / 2));
		((Rectangle) selectedShape).setRectangleY(y - (hight / 2));
	}

	@Override
	public void resize(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int rX = ((Rectangle) selectedShape).getRectangleX();
		int rY = ((Rectangle) selectedShape).getRectangleY();
		if (x >= rX && y >= rY) {
			((Rectangle) selectedShape).setRectangleWidth(x - rX);
			((Rectangle) selectedShape).setRectangleHight(y - rY);
		}
	
	}

}
