package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import Server.Server.Message;

public class Client {
	
	// IP and port
	private static String ip = "localhost";
	private static int port = 3005;
	
	public static void main(String[] args) 
	{
	
		try(Socket socket = new Socket(ip, port);)
		{
			// Output and Input Stream
			DataInputStream input = new DataInputStream(socket.getInputStream());
			
		    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		    
		    JsonObject initMessage = new JsonObject();
	    	
	    	JsonParser parser = new JsonParser();
	    	Gson gson = new Gson();
	    	
	    	//initMessage.add("messageType", gson.toJsonTree(Server.Message.OPEN_WB, Server.Message.class));
	    	initMessage.add("messageType", gson.toJsonTree(Server.Message.JOIN_WB, Server.Message.class));
	    	initMessage.addProperty("selectedWB", "0");
	    	
	    	output.writeUTF(gson.toJson(initMessage));
	    	System.out.println("Sent json");
	    	output.flush();
	    	
		    while(true)
		    {
		    	if(input.available() > 0) {
		    		JsonObject serverMessage = (JsonObject) parser.parse(input.readUTF());
		    		Server.Message messageType = gson.fromJson(serverMessage.get("messageType"), Message.class);
		    		
		    		switch (messageType) {
		    		case OPEN_WB:
		    			System.out.println("Whiteboards: " + serverMessage.get("whiteboards"));
		    			break;
		    		case UPDATE:
		    			JsonObject update = (JsonObject) serverMessage.get("update");
		    			System.out.println("Update: " + update.toString());
		    		default:
		    			break;
		    		}	
		    	}
		    }
		    
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

}
