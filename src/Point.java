
import java.awt.*;

public class Point {
	private int pointX, pointY;
	private Color color;

	public Point(int  pointX, int pointY) {
		this.pointX = pointX;
		this.pointY = pointY;
	}
	public int getX() {
		return pointX;
	}
	public int getY() {
		return pointY;
	}

	public void drawLine(Graphics2D g2, int x1, int y1, int x2, int y2) {
		g2.setColor(color);

		if (x2 == 0 && y2 == 0) {
			x2=x1;
			y2=y1;

		}

		g2.setStroke(new BasicStroke(10));
		
		g2.drawLine(x1, y1, x2, y2);
		

	}
	public void drawEraserLine(Graphics2D g2, int x1, int y1, int x2, int y2, int pointSize) {
		g2.setColor(Color.white);
		
		
		if (x2 == 0 && y2 == 0) {
			x2=x1;
			y2=y1;

		}


		g2.setStroke(new BasicStroke(pointSize));
		
		g2.drawLine(x1, y1, x2, y2);

	}

}
