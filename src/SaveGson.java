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
	ArrayList<Integer> lastaction;

	public SaveGson() {

		url="";
		cshape = null;
		shape = null;
		lastaction =null;

	}

	public void save(ArrayList<Shape> shapes) throws Exception {



		Writer writer = new FileWriter("test" + ".json");
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

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("square")) {
						Square s = new Gson().fromJson(firstFig, Square.class);
						shape.add(s);
						cshape.add(s);

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("ellipse")) {
						Ellipse e = new Gson().fromJson(firstFig, Ellipse.class);
						shape.add(e);
						cshape.add(e);

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("circle")) {
						Circle c = new Gson().fromJson(firstFig, Circle.class);
						shape.add(c);
						cshape.add(c);

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("line")) {
						Line l = new Gson().fromJson(firstFig, Line.class);
						shape.add(l);
						cshape.add(l);

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

						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("text")) {
						Text tx = new Gson().fromJson(firstFig, Text.class);
						shape.add(tx);
						cshape.add(tx);
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("freehand")) {
						Freehand fh = new Gson().fromJson(firstFig, Freehand.class);
						shape.add(fh);
						cshape.add(fh);
						lastaction.add(shapeindex);
						shapeindex++;

					} else if (valueName.equals("eraser")) {
						Eraser er = new Gson().fromJson(firstFig, Eraser.class);
						shape.add(er);
						cshape.add(er);
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
