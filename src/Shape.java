import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Shape {

	protected Color color;
	protected String name;
	protected boolean isFilled;
	protected boolean isDrawn;

	public Shape() {
		color = null;
		name = null;
		isFilled = true;
		isDrawn = true;
	}

	public boolean isDrawn() {
		return isDrawn;
	}

	public void setDraw(boolean isDrawn) {
		this.isDrawn = isDrawn;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	

	abstract Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i);

	abstract void move(MouseEvent e, Shape selectedShape);

	abstract void resize(MouseEvent e, Shape selectedShape);

	abstract void draw(Graphics graphics);





}
