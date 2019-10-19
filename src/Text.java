import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class Text extends Shape {
	
	protected String txt;
	protected int posx,posy, FSize;
	protected int width, hight;
	
	public Text(){
		super();
		txt = "";
		posx =0;
		posy = 0;
		width = 0;
		hight =0;
	}
	
	
	public void setText(String input) {
		this.txt = input;
	}

	public String getText() {
		return txt;
	}
	
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosx() {
		return posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
	public int getPosy() {
		return posy;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int Width) {
		this.width = Width;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int Hight) {
		this.hight = Hight;
	}
	public void setFSize(int size) {
		this.FSize = size;
	}
	public int getFSize() {
		return FSize;
	}
	
	@Override
	public Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i) {
		int x = e.getX();
		int y = e.getY();
		int X = ((Text) currentShapes.get(i)).getPosx();
		int Y = ((Text) currentShapes.get(i)).getPosy();
		int Width = ((Text) currentShapes.get(i)).getWidth();
		int Hight = ((Text) currentShapes.get(i)).getHight();
		if ((x >= X && x <= (X + Width)) && (y <= Y && y >= (Y - Hight))) {
			return currentShapes.get(i);
		}
		return null;
	}

	@Override
	public void move(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
	
		((Text) selectedShape).setPosx(x);
		((Text) selectedShape).setPosy(y);
	}

	@Override
	public void resize(MouseEvent e, Shape selectedShape) {
	
	
	}


	
}
