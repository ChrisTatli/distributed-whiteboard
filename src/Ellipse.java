
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Ellipse extends Shape {

	protected int eX, eY, eWidth, eHight;

	public Ellipse() {
		super();
		eX = 0;
		eY = 0;
		eWidth = 0;
		eHight = 0;
	}

	public int getEX() {
		return eX;
	}

	public void setEX(int eX) {
		this.eX = eX;
	}

	public int getEY() {
		return eY;
	}

	public void setEY(int eY) {
		this.eY = eY;
	}

	public int getEWidth() {
		return eWidth;
	}

	public void setEWidth(int eWidth) {
		this.eWidth = eWidth;
	}

	public int getEHight() {
		return eHight;
	}

	public void setEHight(int eHight) {
		this.eHight = eHight;
	}

	@Override
	public Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		int x = e.getX();
		int y = e.getY();
		int eX = ((Ellipse) currentShapes.get(i)).getEX();
		int eY = ((Ellipse) currentShapes.get(i)).getEY();
		int eWidth = ((Ellipse) currentShapes.get(i)).getEWidth();
		int eHight = ((Ellipse) currentShapes.get(i)).getEHight();
		if ((x >= eX && x <= (eX + eWidth)) && (y >= eY && y <= (eY + eHight))) {
			return currentShapes.get(i);
		}
		return null;
	}

	@Override
	public void move(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int width = ((Ellipse) selectedShape).getEWidth();
		int hight = ((Ellipse) selectedShape).getEHight();
		((Ellipse) selectedShape).setEX(x - (width / 2));
		((Ellipse) selectedShape).setEY(y - (hight / 2));
	}

	@Override
	public void resize(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int eX = ((Ellipse) selectedShape).getEX();
		int eY = ((Ellipse) selectedShape).getEY();
		if (x >= eX && y >= eY) {
			((Ellipse) selectedShape).setEWidth(x - eX);
			((Ellipse) selectedShape).setEHight(y - eY);
		}
	}
}
