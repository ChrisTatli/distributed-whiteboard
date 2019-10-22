import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class Text extends Shape {
	
	private Point start;
	private String text;

	
	public Text(Point start, String text, Color color){
		super(color);
		this.start = start;
		this.text = text;
	}
	
	


	@Override
	Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {

		return null;
	}

	@Override
	void move(MouseEvent e, Shape selectedShape) {
	}

	@Override
	void resize(MouseEvent e, Shape selectedShape){
	}

	@Override
	void draw(Graphics graphics) {

	}


}
