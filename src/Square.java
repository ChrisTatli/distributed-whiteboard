import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Square extends Shape{

	protected int x,y,l;
	
	public Square(){
		x=y=l=0;
	}

	public int getSquareX() {
		return x;
	}

	public void setSquareX(int x) {
		this.x = x;
	}

	public int getSquareY() {
		return y;
	}

	public void setSquareY(int y) {
		this.y = y;
	}

	public int getSquareL() {
		return l;
	}

	public void setSquareL(int l) {
		this.l = l;
	}

	@Override
	public Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		
		int x = e.getX();
		int y = e.getY();
		int sX = ((Square) currentShapes.get(i)).getSquareX();
		int sY = ((Square) currentShapes.get(i)).getSquareY();
		int sL = ((Square) currentShapes.get(i)).getSquareL();
		
		if ((x >= sX && x <= (sX + sL)) && (y >= sY && y <= (sY + sL))) {
			return currentShapes.get(i);
		}
		return null;
	}

	@Override
	public void move(MouseEvent e, Shape selectedShape) {
		// TODO Auto-generated method stub
		
		int x = e.getX();
		int y = e.getY();
		int sL = ((Square) selectedShape).getSquareL();
		((Square) selectedShape).setSquareX(x - (sL / 2));
		((Square) selectedShape).setSquareY(y - (sL / 2));
	}

	@Override
	public void resize(MouseEvent e, Shape selectedShape) {
		
		int x = e.getX();
		int y = e.getY();
		int sX = ((Square) selectedShape).getSquareX();
		int sY = ((Square) selectedShape).getSquareY();
		if (x >= sX && y >= sY) {
			((Square) selectedShape).setSquareL(x - sX);	
		}
		
	}
	
}
