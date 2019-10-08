import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Stack;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class SaveGson {
	String url;
	ArrayList<Shape> cshape;
	ArrayList<Shape> shape;
	Stack<Item> undo;
	ArrayList<Integer> lastaction;

	public SaveGson() {

		url="";
		cshape = null;
		shape = null;
		undo=null;
		lastaction =null;

	}

	public void save(ArrayList<Shape> shapes) throws Exception {



		Writer writer = new FileWriter(Whiteboard.url + ".json");
		Gson gson = new GsonBuilder().create();


		gson.toJson(shapes, writer);
		writer.close();

	}

	public void load(String url, ArrayList<Shape> cshape, ArrayList<Shape> shape) throws Exception {


		JsonReader reader = new JsonReader(new FileReader(url));

		//GET THE JSON-FILE AS JSON OBJECT
		JsonParser parser = new JsonParser();
		JsonArray objs = (JsonArray) parser.parse(reader);

		int shapeindex = 0;

		while (shapeindex < objs.size()) {
			JsonObject firstFig = (JsonObject) objs.get(shapeindex);


			for (String keyStr : firstFig.keySet()) {
				Object keyvalue = firstFig.get(keyStr);
				if (keyStr.equals("name")){
					String valueName =keyvalue.toString();
					valueName = valueName.replace("\"", "");


					if (valueName.equals("rectangle")) {
						Rectangle r = new Gson().fromJson(firstFig, Rectangle.class);
						shape.add(r);
						cshape.add(r);
						undo.push(new Item(shapeindex, "rectangle", r.getrX() + ","
								+ r.getrY() + "," + r.getrWidth() + "," + r.getrHight()
								+ "," + r.getColor().getRGB() + "," + r.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("square")) {
						Square s = new Gson().fromJson(firstFig, Square.class);
						shape.add(s);
						cshape.add(s);
						undo.push(new Item(shapeindex, "square", s.getPosx() + ","
								+ s.getPosy() + "," + s.getL() + "," + s.getL() + ","
								+ s.getColor().getRGB() + "," + s.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("ellipse")) {
						Ellipse e = new Gson().fromJson(firstFig, Ellipse.class);
						shape.add(e);
						cshape.add(e);
						undo.push(new Item(shapeindex, "ellipse", e.getcX() + ","
								+ e.getcY() + "," + e.getcWidth() + "," + e.getcHeight()
								+ "," + e.getColor().getRGB() + "," + e.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("circle")) {
						Circle c = new Gson().fromJson(firstFig, Circle.class);
						shape.add(c);
						cshape.add(c);
						undo.push(new Item(shapeindex, "circle", c.getX() + ","
								+ c.getY() + "," + c.getRadius() + "," + c.getRadius()
								+ "," + c.getColor().getRGB() + "," + c.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("line")) {
						Line l = new Gson().fromJson(firstFig, Line.class);
						shape.add(l);
						cshape.add(l);
						undo.push(new Item(shapeindex, "line", l.getX1() + ","
								+ l.getY1() + "," + l.getX2() + "," + l.getY2() + ","
								+ l.getColor().getRGB() + "," + l.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("triangle")) {
						Triangle t = new Gson().fromJson(firstFig, Triangle.class);
						shape.add(t);
						cshape.add(t);
						String x = t.getxPoints()[0] + "," + t.getxPoints()[1] + ","
								+ t.getxPoints()[2];
						String y = t.getyPoints()[0] + "," + t.getyPoints()[1] + ","
								+ t.getyPoints()[2];
						undo.push(new Item(shapeindex, "triangle", t.gettX() + ","
								+ t.gettY() + "," + x + "," + y + ","
								+ t.getColor().getRGB() + "," + t.isFilled()));

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("text")) {
						Text tx = new Gson().fromJson(firstFig, Text.class);
						shape.add(tx);
						cshape.add(tx);
						undo.push(new Item(shapeindex, "rectangle", tx.getPosx() + "," + tx.getPosy() + "," + tx.getWidth() + ","
								+ tx.getHight() + "," + tx.getColor().getRGB() + ","
								+ true));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("freehand")) {
						Freehand fh = new Gson().fromJson(firstFig, Freehand.class);
						shape.add(fh);
						cshape.add(fh);
						undo.push(new Item(shapeindex, "rectangle", fh.getFX() + "," + fh.getFY() + "," + fh.getLX() + ","
								+ fh.getLY() + "," + fh.getColor().getRGB() + ","
								+ true));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("eraser")) {
						Eraser er = new Gson().fromJson(firstFig, Eraser.class);
						shape.add(er);
						cshape.add(er);
						undo.push(new Item(shapeindex, "rectangle", er.getFX() + "," + er.getFY() + "," + er.getLX() + ","
								+ er.getLY() + "," + er.getColor().getRGB() + ","
								+ true));
						lastaction.add(shapeindex);
						shapeindex++;

					}

				}
			}



		}

		this.shape = shape;
		this.cshape =cshape;

	}


}
