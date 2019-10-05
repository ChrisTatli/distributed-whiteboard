
import java.awt.Color;
import java.io.File;

import java.util.ArrayList;
import java.util.Stack;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;


public class loadJson {

	ArrayList<Shape> cshape;
	ArrayList<Shape> shape;
	Stack<Item> undo;
	ArrayList<Integer> lastaction;
	/*
	 * we save shapes in array and each element of thie array is an object of
	 * shape with it's attributes
	 */
	

	public void load(String url, ArrayList<Shape> cshape2, ArrayList<Shape> shape2,
			Stack<Item> undo2, ArrayList<Integer> lastaction2) throws Exception {
		
		this.cshape =cshape2;
		this.shape = shape2;
		this.undo = undo2;
		this.lastaction= lastaction2;

		cshape.clear();
		shape.clear();
		int shapeindex = 0;

		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createJsonParser(url);

		while (parser.nextToken() != JsonToken.END_ARRAY) {
			System.out.println(parser.nextToken());
			System.out.println(JsonToken.END_ARRAY);
			String type = parser.getCurrentName();
			if ("rectangle".equals(type) == true) {
				parser.nextToken();
				Rectangle r = new Rectangle();
				Object[] Stringtoint = Stringtoint(parser.getText());
				r.setrX((Integer) Stringtoint[0]);
				r.setrY((Integer) Stringtoint[1]);
				r.setrWidth((Integer) Stringtoint[2]);
				r.setrHight((Integer) Stringtoint[3]);
				r.setColor(new Color((Integer) Stringtoint[4]));
				r.setFilled(((Boolean) Stringtoint[5]));
				r.setName("rectangle");
				this.shape.add(r);
				this.cshape.add(r);

				this.undo.push(new Item(shapeindex, "rectangle", r.getrX() + ","
						+ r.getrY() + "," + r.getrWidth() + "," + r.getrHight()
						+ "," + r.getColor().getRGB() + "," + r.isFilled()));
				this.lastaction.add(shapeindex);
				shapeindex++;

			} else if ("square".equals(type) == true) {
				parser.nextToken();
				Square r = new Square();
				Object[] Stringtoint = Stringtoint(parser.getText());
				r.setPosx((Integer) Stringtoint[0]);
				r.setPosy((Integer) Stringtoint[1]);
				r.setL((Integer) Stringtoint[2]);
				r.setColor(new Color((Integer) Stringtoint[4]));
				r.setFilled(((Boolean) Stringtoint[5]));
				r.setName("square");
				this.shape.add(r);
				this.cshape.add(r);

				this.undo.push(new Item(shapeindex, "square", r.getPosx() + ","
						+ r.getPosy() + "," + r.getL() + "," + r.getL() + ","
						+ r.getColor().getRGB() + "," + r.isFilled()));
				this.lastaction.add(shapeindex);
				shapeindex++;

			}

			else if ("ellipse".equals(type) == true) {

				parser.nextToken();
				Ellipse c = new Ellipse();
				Object[] Stringtoint = Stringtoint(parser.getText());
				c.setcX((Integer) Stringtoint[0]);
				c.setcY((Integer) Stringtoint[1]);
				c.setcWidth((Integer) Stringtoint[2]);
				c.setcHight((Integer) Stringtoint[3]);
				c.setColor(new Color((Integer) Stringtoint[4]));
				c.setFilled(((Boolean) Stringtoint[5]));
				c.setName("ellipse");
				this.shape.add(c);
				this.cshape.add(c);

				this.undo.push(new Item(shapeindex, "ellipse", c.getcX() + ","
						+ c.getcY() + "," + c.getcWidth() + "," + c.getcHight()
						+ "," + c.getColor().getRGB() + "," + c.isFilled()));
				this.lastaction.add(shapeindex);
				shapeindex++;

			} else if ("circle".equals(type) == true) {

				parser.nextToken();
				Circle c = new Circle();
				Object[] Stringtoint = Stringtoint(parser.getText());
				c.setX((Integer) Stringtoint[0]);
				c.setY((Integer) Stringtoint[1]);
				c.setRadius((Integer) Stringtoint[2]);
				c.setColor(new Color((Integer) Stringtoint[4]));
				c.setFilled(((Boolean) Stringtoint[5]));
				c.setName("circle");
				this.shape.add(c);
				this.cshape.add(c);

				this.undo.push(new Item(shapeindex, "circle", c.getX() + ","
						+ c.getY() + "," + c.getRadius() + "," + c.getRadius()
						+ "," + c.getColor().getRGB() + "," + c.isFilled()));
				lastaction.add(shapeindex);
				shapeindex++;

			} else if ("line".equals(type) == true) {
				parser.nextToken();
				Line l = new Line();
				Object[] Stringtoint = Stringtoint(parser.getText());
				l.setX1((Integer) Stringtoint[0]);
				l.setY1((Integer) Stringtoint[1]);
				l.setX2((Integer) Stringtoint[2]);
				l.setY2((Integer) Stringtoint[3]);
				l.setColor(new Color((Integer) Stringtoint[4]));
				l.setFilled(((Boolean) Stringtoint[5]));
				l.setName("line");
				this.shape.add(l);
				this.cshape.add(l);

				this.undo.push(new Item(shapeindex, "line", l.getX1() + ","
						+ l.getY1() + "," + l.getX2() + "," + l.getY2() + ","
						+ l.getColor().getRGB() + "," + l.isFilled()));
				this.lastaction.add(shapeindex);
				shapeindex++;

			} else if ("triangle".equals(type) == true) {
				parser.nextToken();
				Triangle t = new Triangle();
				int[] attr = convert(parser.getText());
				t.settX((Integer) attr[0]);
				t.settY((Integer) attr[1]);
				t.setxPoints(new int[] { attr[2], attr[3], attr[4] });
				t.setyPoints(new int[] { attr[5], attr[6], attr[7] });
				t.setColor(new Color((Integer) attr[8]));

				if (parser.getText().charAt(parser.getTextLength() - 2) == 'u') {
					t.setFilled(true);
				} else {
					t.setFilled(false);
				}
				t.setName("triangle");
				this.shape.add(t);
				this.cshape.add(t);

				String x = t.getxPoints()[0] + "," + t.getxPoints()[1] + ","
						+ t.getxPoints()[2];
				String y = t.getyPoints()[0] + "," + t.getyPoints()[1] + ","
						+ t.getyPoints()[2];
				this.undo.push(new Item(shapeindex, "triangle", t.gettX() + ","
						+ t.gettY() + "," + x + "," + y + ","
						+ t.getColor().getRGB() + "," + t.isFilled()));

				lastaction.add(shapeindex);
				shapeindex++;

			}
			
		}
		

	}

	private Object[] Stringtoint(String elements) {

		int[] attr = convert(elements);
		int a = attr[0];
		int b = attr[1];
		int c = attr[2];
		int d = attr[3];
		int color = attr[4];
		Boolean filled;
		if (elements.charAt(elements.length() - 2) == 'u') {
			filled = true;
		} else {
			filled = false;
		}
		Object[] array = { a, b, c, d, color, filled };
		return array;
	}

	private int[] convert(String a) {

		String[] s = a.split(",");
		int[] array = new int[s.length - 1];
		for (int i = 0; i < s.length - 1; i++) {
			array[i] = Integer.parseInt(s[i]);
		}
		return array;
	}

}
