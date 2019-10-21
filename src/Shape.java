import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Shape {

	private Color color;
	protected boolean isFilled;


	public Shape(Color color) {
		this.color = color;
		isFilled = true;
	}

	public Shape(){

	}



	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public Color getColor() {
		return color;
	}

	

	

	abstract Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i);

	abstract void move(MouseEvent e, Shape selectedShape);

	abstract void resize(MouseEvent e, Shape selectedShape);

	abstract void draw(Graphics graphics);

}
