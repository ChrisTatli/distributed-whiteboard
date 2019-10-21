import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Line extends Shape {

	private Point start;
	private Point end;


	public Line(Point start, Point end, Color color) {
		super(color);
		this.start = start;
		this.end = end;
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
	void draw(Graphics graphics)
	{
		graphics.setColor(this.getColor());
		graphics.drawLine(start.x, start.y, end.x, end.y);
	}


}
