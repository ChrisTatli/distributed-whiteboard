import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.ParsePosition;
import java.util.ArrayList;


public class Square extends Shape{
	private Point start;
	private Point end;
	private int length;


	public Square(Point start, Point end, Color color){
		super(color);
		this.start = start;
		this.end = end;
	}

	private void CalculateLength(Point start, Point end){
		this.length = Math.abs(start.x - end.x);
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
		CalculateLength(start,end);
		graphics.setColor(this.getColor());

		graphics.drawRect(start.x, start.y, length, length);
	}

	
}
