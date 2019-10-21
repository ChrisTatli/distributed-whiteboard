import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Circle extends Shape {

	private Point start;
	private Point end;
	private int diameter;

	public Circle(Point start, Point end, Color color) {
		super(color);
		this.start = start;
		this.end = end;
	}

	private void CalculateDiameter(Point start, Point end){
		diameter = Math.abs(start.x - end.x);
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
		CalculateDiameter(start, end);
		graphics.setColor(this.getColor());
		graphics.drawOval(start.x, start.y, diameter, diameter);
	}


}
