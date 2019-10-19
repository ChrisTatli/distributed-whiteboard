import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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



		Writer writer = new FileWriter(Frame.url + ".json");
		Gson gson = new GsonBuilder().create();


		gson.toJson(shapes, writer);
		writer.close();

	}

	public void load(String url, ArrayList<Shape> cshape, ArrayList<Shape> shape,
					 Stack<Item> undo, ArrayList<Integer> lastaction) throws Exception {


		JsonReader reader = new JsonReader(new FileReader(url));


		//Type foundListType = new TypeToken<ArrayList<Object>>() {}.getType();
		//ArrayList<Shape> data = new Gson().fromJson( reader, foundListType);





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
						undo.push(new Item(shapeindex, "rectangle", r.getRectangleX() + ","
								+ r.getRectangleY() + "," + r.getRectangleWidth() + "," + r.getRectangleHight()
								+ "," + r.getColor().getRGB() + "," + r.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("square")) {
						Square s = new Gson().fromJson(firstFig, Square.class);
						shape.add(s);
						cshape.add(s);
						undo.push(new Item(shapeindex, "square", s.getSquareX() + ","
								+ s.getSquareY() + "," + s.getSquareL() + "," + s.getSquareL() + ","
								+ s.getColor().getRGB() + "," + s.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("ellipse")) {
						Ellipse e = new Gson().fromJson(firstFig, Ellipse.class);
						shape.add(e);
						cshape.add(e);
						undo.push(new Item(shapeindex, "ellipse", e.getEX() + ","
								+ e.getEY() + "," + e.getEWidth() + "," + e.getEHight()
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
						String x = t.getXPoints()[0] + "," + t.getXPoints()[1] + ","
								+ t.getXPoints()[2];
						String y = t.getYPoints()[0] + "," + t.getYPoints()[1] + ","
								+ t.getYPoints()[2];
						undo.push(new Item(shapeindex, "triangle", t.getTriangleX() + ","
								+ t.getTriangleY() + "," + x + "," + y + ","
								+ t.getColor().getRGB() + "," + t.isFilled()));

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("Text")) {
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

					} else if (valueName.equals("Eraser")) {
						Eraser er = new Gson().fromJson(firstFig, Eraser.class);
						shape.add(er);
						cshape.add(er);
						undo.push(new Item(shapeindex, "rectangle", er.getEX() + "," + er.getFY() + "," + er.getLX() + ","
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
		this.lastaction =lastaction;
		this.undo =undo;

	}


}
