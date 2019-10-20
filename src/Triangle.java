import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Triangle extends Shape {

	protected int[] xPoints, yPoints;
	protected int tX, tY;

	public Triangle() {
		super();
		xPoints = null;
		yPoints = null;
		tX = 0;
		tY = 0;
	}

	public int[] getXPoints() {
		return xPoints;
	}

	public void setXPoints(int[] xPoints) {
		this.xPoints = xPoints;
	}

	public int[] getYPoints() {
		return yPoints;
	}

	public void setYPoints(int[] yPoints) {
		this.yPoints = yPoints;
	}

	public int getTriangleX() {
		return tX;
	}

	public void setTriangleX(int tX) {
		this.tX = tX;
	}

	public int getTriangleY() {
		return tY;
	}

	public void setTriangleY(int tY) {
		this.tY = tY;
	}

	@Override
	public Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		int x = e.getX();
		int y = e.getY();
		int tX1 = ((Triangle) currentShapes.get(i)).getTriangleX();
		int tY1 = ((Triangle) currentShapes.get(i)).getTriangleY();
		int tX2 = ((Triangle) currentShapes.get(i)).getXPoints()[2];
		int tY2 = ((Triangle) currentShapes.get(i)).getYPoints()[2];
		if ((x >= tX1 && x <= tX2) && (y >= tY1 && y <= tY2)) {
			return currentShapes.get(i);
		} else if ((x <= tX1 && x >= tX2) && (y <= tY1 && y >= tY2)) {
			return currentShapes.get(i);
		} else if ((x >= tX1 && x <= tX2) && (y <= tY1 && y >= tY2)) {
			return currentShapes.get(i);
		} else if ((x <= tX1 && x >= tX2) && (y >= tY1 && y <= tY2)) {
			return currentShapes.get(i);
		}
		return null;
	}

	@Override
	public void move(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int tX1 = ((Triangle) selectedShape).getTriangleX();
		int tY1 = ((Triangle) selectedShape).getTriangleY();
		int tX2 = ((Triangle) selectedShape).getXPoints()[2];
		int tY2 = ((Triangle) selectedShape).getYPoints()[2];
		int width = tX2 - tX1;
		int hight = tY2 - tY1;
		((Triangle) selectedShape).setTriangleX(x - width / 2);
		((Triangle) selectedShape).setTriangleY(y - hight / 2);
		int[] xPoints = { (((Triangle) selectedShape).getTriangleX() + width / 2),
				((Triangle) selectedShape).getTriangleX(),
				(((Triangle) selectedShape).getTriangleX() + width) };
		int[] yPoints = { ((Triangle) selectedShape).getTriangleY(),
				(((Triangle) selectedShape).getTriangleY() + hight),
				(((Triangle) selectedShape).getTriangleY() + hight) };
		((Triangle) selectedShape).setXPoints(xPoints);
		((Triangle) selectedShape).setYPoints(yPoints);
	}

	@Override
	public void resize(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int tX1 = ((Triangle) selectedShape).getTriangleX();
		int tY1 = ((Triangle) selectedShape).getTriangleY();
		int width = x - tX1;
		int[] xPoints = { tX1 + (width / 2), tX1, x };
		int[] yPoints = { tY1, y, y };
		((Triangle) selectedShape).setXPoints(xPoints);
		((Triangle) selectedShape).setYPoints(yPoints);
	}

}
