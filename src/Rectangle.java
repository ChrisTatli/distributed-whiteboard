import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Rectangle extends Shape {

	private Point start;
	private Point end;
	private int width;
	private int height;


	public Rectangle(Point start, Point end, Color color) {
		super(color);
		this.start = start;
		this.end = end;
	}

	private void CalculateDimensions(Point start, Point end){
		this.width = Math.abs(start.x - end.x);
		this.height = Math.abs(start.y - end.y);
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
		CalculateDimensions(start, end);
		//TODO this is probs wrong due to the way it calculates points
		graphics.setColor(this.getColor());
		graphics.drawRect(start.x, start.y, width, height );
	}




}
