import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

/* 
 * class for undo and redo any action performed but delete action is'nt supported
 */

public class UR {
	protected Stack<Item> undo;
	protected Stack<Item> redo;
	protected Rectangle r;
	protected Item object;
	protected ArrayList<Integer> lastAction;

	// constructor
	public UR() {
		undo = new Stack<Item>();
		redo = new Stack<Item>();
		lastAction = new ArrayList<Integer>();
	}

	// undo
	public void undo(ArrayList<Shape> cshapes) {
		try {
			object = new Item();
			object = undo.get(lastAction.get(lastAction.size() - 1));
			lastAction.remove(lastAction.size() - 1);

			String pos = object.getPositions();
			Object[] p = returnpos(pos);
			Object[] temp = (Object[]) p[0];
			if (temp.length == 0) {
				cshapes.get(object.getI()).setDraw(false);
			} else {

				int[] oldpos = (int[]) temp[temp.length - 1];
				DetectShapeType(oldpos, object, cshapes, (Boolean) p[1]);
			}
		} catch (Exception exception) {

		}
	}

	// redo
	public void redo(ArrayList<Shape> cshape) {
		try {
			t = redo.pop();
			object = undo.get(t.getI());
			if (cshape.get(t.getI()).isDraw() == false) {
				cshape.get(t.getI()).setDraw(true);
			}
			if (object.getPositions().equals("")) {
				object.setPositions(t.getPositions());
			} else {
				object.setPositions(object.getPositions() + "="
						+ t.getPositions());
			}
			undo.set(t.getI(), object);
			int[] newpos = (int[]) convert(t.getPositions())[0];
			Boolean filled;
			String d = (String) convert(t.getPositions())[1];
			if (d.charAt(0) == 'f') {
				
				filled = false;
			} else {
				filled = true;
			}
			DetectShapeType(newpos, object, cshape, filled);
			lastAction.add(t.getI());
		} catch (Exception exception) {

		}
	}

	// method that update the position of selected shape in the stack
	public void updatselected(int i, String pos, Stack<Item> undo) {
		try{
		object = new Item();
		object = undo.get(i);
		object.setPositions(object.getPositions() + "=" + pos);
		undo.set(i, object);
		}catch (Exception exception) {
		
		}
	}

	Item t;

	// method that return the attributes of object saved in stack
	private Object[] returnpos(String pos) {

		String[] s = pos.split("=");
		Object[] res = new Object[s.length - 1];
		String temp = "";

		for (int i = 0; i < s.length - 1; i++) {
			Object[] obj = convert(s[i]);
			res[i] = obj[0];
			temp = (String) obj[1];
		}
		t = new Item();
		t.setName(object.getName());
		t.setI(object.getI());
		t.setPositions(s[s.length - 1]);

		redo.push(t);

		int h = 0;
		for (int i = pos.length() - 1; i >= 0; i--) {
			if (pos.charAt(i) == '=') {
				h = i;
				break;
			}
		}
		object.setPositions(object.getPositions().substring(0, h));
		Boolean filled;
		if (temp.equals("") == true) {
			for (int i = pos.length() - 1; i > 0; i--) {
				if (pos.charAt(i) == ',') {
					temp = pos.substring(i, pos.length());
					break;
				}
			}
		}
		if (temp.charAt(0) == 'f') {
			filled = false;
		} else {
			filled = true;
		}
		return new Object[] { res, filled };

	}

	// convert the postion of the object from string as we saved to int
	private Object[] convert(String a) {
		String[] s = a.split(",");
		int[] array = new int[9];
		for (int i = 0; i < s.length - 1; i++) {
			if (s[i] != null) {
				array[i] = Integer.parseInt(s[i]);
			}
		}
		Object[] res = new Object[] { array, s[s.length - 1] };
		return res;
	}

	// method that update stake undo and redo after delete
	public void updastackAfterDel(Stack<Item> undo, Stack<Item> redo, int help) {
		for (int i = 0; i < undo.size(); i++) {
			if (undo.get(i).getI() > help) {
				undo.get(i).setI(undo.get(i).getI() - 1);
			}
		}
		for (int i = redo.size() - 1; i >= 0; i--) {
			if (redo.get(i).getI() == help) {
				redo.remove(i);
			}
		}
	}

	private void DetectShapeType(int[] oldpos, Item object,
			ArrayList<Shape> cshapes, Boolean filled) {

		if (object.getName() == "circle") {

			((Circle) cshapes.get(object.getI())).setX(oldpos[0]);
			((Circle) cshapes.get(object.getI())).setY(oldpos[1]);
			((Circle) cshapes.get(object.getI())).setRadius(oldpos[2]);
			cshapes.get(object.getI()).setColor(new Color((oldpos[4])));

		} else if (object.getName() == "ellipse") {
			((Ellipse) cshapes.get(object.getI())).setEX(oldpos[0]);
			((Ellipse) cshapes.get(object.getI())).setEY(oldpos[1]);
			((Ellipse) cshapes.get(object.getI())).setEWidth(oldpos[2]);
			((Ellipse) cshapes.get(object.getI())).setEHight(oldpos[3]);
			cshapes.get(object.getI()).setColor(new Color((oldpos[4])));
		} else if (object.getName() == "rectangle") {

			((Rectangle) cshapes.get(object.getI())).setRectangleX(oldpos[0]);
			((Rectangle) cshapes.get(object.getI())).setRectangleY(oldpos[1]);
			((Rectangle) cshapes.get(object.getI())).setRectangleWidth(oldpos[2]);
			((Rectangle) cshapes.get(object.getI())).setRectangleHight(oldpos[3]);
			cshapes.get(object.getI()).setColor(new Color((oldpos[4])));
		} else if (object.getName() == "square") {

			((Square) cshapes.get(object.getI())).setSquareX(oldpos[0]);
			((Square) cshapes.get(object.getI())).setSquareY(oldpos[1]);
			((Square) cshapes.get(object.getI())).setSquareL(oldpos[2]);
			cshapes.get(object.getI()).setColor(new Color((oldpos[4])));

		} else if (object.getName() == "line") {

			((Line) cshapes.get(object.getI())).setX1(oldpos[0]);
			((Line) cshapes.get(object.getI())).setY1(oldpos[1]);
			((Line) cshapes.get(object.getI())).setX2(oldpos[2]);
			((Line) cshapes.get(object.getI())).setY2(oldpos[3]);
			cshapes.get(object.getI()).setColor(new Color((oldpos[4])));
		} else if (object.getName() == "triangle") {

			((Triangle) cshapes.get(object.getI())).setTriangleX(oldpos[0]);
			((Triangle) cshapes.get(object.getI())).setTriangleY(oldpos[1]);
			((Triangle) cshapes.get(object.getI())).setXPoints(new int[] {
					oldpos[2], oldpos[3], oldpos[4] });
			((Triangle) cshapes.get(object.getI())).setYPoints(new int[] {
					oldpos[5], oldpos[6], oldpos[7] });
			cshapes.get(object.getI()).setColor(new Color((oldpos[8])));
		}
		cshapes.get(object.getI()).setFilled(filled);
	}

}
