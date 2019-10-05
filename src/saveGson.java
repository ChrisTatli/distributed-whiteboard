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





public class saveGson {
	String url;
	ArrayList<Shape> cshape;
	ArrayList<Shape> shape;
	Stack<Item> undo;
	ArrayList<Integer> lastaction;
	
	public void save(ArrayList<Shape> shapes) throws Exception {

		
	
		Writer writer = new FileWriter(frame.url + ".json");
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
			JsonObject firstFig = (JsonObject) objs.get(0);

			int shapeindex = 1;
			/*for (String keyStr : firstFig.keySet()) {
		        Object keyvalue = firstFig.get(keyStr);
		        //Print key and value
		        System.out.println("key: "+ keyStr + " value: " + keyvalue);
		        if (keyStr.equals("name")){
		        	String valueName =keyvalue.toString();
		        	valueName = valueName.replace("\"", "");
		        	if (valueName.equals("rectangle")) {
		        		Rectangle r = new Rectangle();
		        		r.setName("rectangle");
		        		for (String key : firstFig.keySet()) {
		    		        Object value = firstFig.get(key);
		    		        String valueN =value.toString();
		    		        valueN = valueN.replace("\"", "");
		    		        
		    		      //***********************
		    		        
		    		        if (key.equals("color")) {
		    		        	r.setColor((Color) value);
		    		        }
		    		        else if (key.equals("isDraw")) {
		    		        	r.setDraw((boolean) value);
		    		        }
		    		        else if (key.equals("isFilled")) {
		    		        	r.setFilled((boolean) value);
		    		        } 
		    		        //***********************
		    		        
		    		        else if (key.equals("rX")) {
		    		        	try {
									r.setrX(Integer.valueOf(valueN));
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		    		        }
		    		        else if (key.equals("rY")) {
		    		        	r.setrY((Integer) value);
		    		        }
		    		        else if (key.equals("rWidth")) {
		    		        	r.setrWidth((Integer) value);
		    		        }
		    		        else if (key.equals("rHight")) {
		    		        	r.setrHight((Integer) value);
		    		        }
		    		        
						
						
						shape.add(r);
						cshape.add(r);

						undo.push(new Item(shapeindex, "rectangle", r.getrX() + ","
								+ r.getrY() + "," + r.getrWidth() + "," + r.getrHight()
								+ "," + r.getColor().getRGB() + "," + r.isFilled()));
						lastaction.add(shapeindex);
						shapeindex++;
		        		}
		        	}
		        }

		 
		    
			}*/
		
			this.shape = shape;
			this.cshape = cshape;
			this.lastaction = lastaction;
			this.undo = undo;
			
			
	}


}
