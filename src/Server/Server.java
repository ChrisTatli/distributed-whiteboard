package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.net.ServerSocketFactory;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Server {
	
	public enum Message {
		NEW_WB, OPEN_WB, JOIN_WB, UPDATE, CHAT
	}
	
	// Declare the port number
	private static int port = 3005;
	
	// Identifies the user number connected
	private static int counter = 0;
	
	// List of whiteboards
	static ArrayList<ArrayList<JsonObject>> whiteboards = new ArrayList<ArrayList<JsonObject>>();
	
	// List of whiteboard chats
	static ArrayList<ArrayList<String>> whiteboardChats = new ArrayList<ArrayList<String>>();

	public static void main(String[] args)
	{
		
		// TESTING TODO
		whiteboards.add(new ArrayList<JsonObject> ());
		JsonObject testJson = new JsonObject();
		testJson.addProperty("test", "testValue");
		whiteboards.get(0).add(testJson);
		whiteboardChats.add(new ArrayList<String>());
		whiteboardChats.get(0).add("Hello");
		whiteboardChats.get(0).add("Hi");
		
		
		ServerSocketFactory factory = ServerSocketFactory.getDefault();
		
		try(ServerSocket server = factory.createServerSocket(port))
		{
			System.out.println("Waiting for client connection-");
			
			// Wait for connections.
			while(true)
			{
				Socket client = server.accept();
				counter++;
				System.out.println("Client "+counter+": Applying for connection!");
							
				// Start a new thread for a connection
				Thread t = new Thread(() -> serveClient(client));
				t.start();
			}
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static void serveClient(Socket client)
	{
		try(Socket clientSocket = client)
		{
			// Input stream
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
		    
		    int curWB = -1; // Current whiteboard
		    int curWBElement = 0; // Current whiteboard json element
		    int curWBCElement = 0; // Current whiteboard chat message
		    
		    // Read input Json
		    JsonParser parser = new JsonParser();
		    Gson gson = new Gson();
		    
		    while(true)
		    {
		    	JsonObject reply = new JsonObject();
		    	
		    	if (input.available() > 0) {
		    		JsonObject clientMessage = (JsonObject) parser.parse(input.readUTF());
		    		//String messageType = clientMessage.get("messageType").toString();
		    		Message messageType = gson.fromJson(clientMessage.get("messageType"), Message.class);
		    		System.out.println("CLIENT: "+ messageType.toString());
				    
		    		switch(messageType) {
		    		case OPEN_WB:
		    			reply.add("messageType", gson.toJsonTree(Server.Message.OPEN_WB, Server.Message.class));
				    	reply.addProperty("whiteboards", wbList());
				    	// Serialise the json and send to client
				    	output.writeUTF(gson.toJson(reply));
		    			break;
		    		case JOIN_WB:
		    			curWB = gson.fromJson(clientMessage.get("selectedWB"), int.class);
		    			System.out.println(curWB);
		    			// TODO if server has saved whiteboard, send to client
		    			break;
		    		case UPDATE:
		    			ArrayList<JsonObject> openWB = whiteboards.get(curWB);
		    			openWB.add((JsonObject) clientMessage.get("update"));
		    			break;
		    		case NEW_WB:
		    			System.out.println(whiteboards.size());
		    			whiteboards.add(new ArrayList<JsonObject>());
		    			whiteboardChats.add(new ArrayList<String> ());
		    			curWB = whiteboards.size() - 1;
		    			System.out.println(whiteboards.size());
		    			break;
		    		case CHAT:
		    			ArrayList<String> openWBC = whiteboardChats.get(curWB);
		    			openWBC.add(gson.fromJson(clientMessage.get("chat"), String.class));
		    			break;
		    		default:
		    			break;
		    		}
		    		
		    	}
		    	else {
		    		// Checks for updates and sends to client
			    	if(curWB >= 0) { // Has the client chosen a whiteboard
			    		ArrayList<JsonObject> openWB = whiteboards.get(curWB); 
			    		if (openWB.size() > curWBElement) { // Are there new updates
			    			reply.add("messageType", gson.toJsonTree(Server.Message.UPDATE, Server.Message.class));
			    			reply.add("update", openWB.get(curWBElement));
			    			output.writeUTF(gson.toJson(reply));
			    			curWBElement++;
			    		}
			    		else { // Checks for chat updates and sends to client
				    		ArrayList<String> openWBC = whiteboardChats.get(curWB); 
				    		if (openWBC.size() > curWBCElement) { // Are there new chat messages
				    			reply.add("messageType", gson.toJsonTree(Server.Message.CHAT, Server.Message.class));
				    			reply.addProperty("chat", openWBC.get(curWBCElement));
				    			output.writeUTF(gson.toJson(reply));
				    			curWBCElement++;
				    		}
				    	}
			    	}
		    	}
		    	
		    }
		    
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*private static void serveClientChat(Socket client)
	{
		try(Socket clientSocket = client)
		{
			// Input stream
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
		    
		    int curWB = -1;
		    int curWBCElement = 0;
		    
		    // Read input Json
		    JsonParser parser = new JsonParser();
		    Gson gson = new Gson();
		    
		    while(true)
		    {
		    	JsonObject reply = new JsonObject();
		    	
		    	if (input.available() > 0) {
		    		JsonObject clientMessage = (JsonObject) parser.parse(input.readUTF());
		    		//String messageType = clientMessage.get("messageType").toString();
		    		Message messageType = gson.fromJson(clientMessage.get("messageType"), Message.class);
		    		System.out.println("CLIENT: "+ messageType.toString());
				    
		    		switch(messageType) {
		    		case JOIN_WB:
		    			curWB = gson.fromJson(clientMessage.get("selectedWB"), int.class);
		    			System.out.println(curWB);
		    			// TODO if server has saved whiteboard, send to client
		    			break;
		    		case CHAT:
		    			ArrayList<String> openWBC = whiteboardChats.get(curWB);
		    			openWBC.add(gson.fromJson(clientMessage.get("chat"), String.class));
		    		default:
		    			break;
		    		}
		    		
		    	}
		    	else {
		    		// Checks for chat updates and sends to client
			    	if(curWB >= 0) { // Has the client chosen a whiteboard
			    		ArrayList<String> openWBC = whiteboardChats.get(curWB); 
			    		if (openWBC.size() > curWBCElement) { // Are there new chat messages
			    			reply.add("messageType", gson.toJsonTree(Server.Message.CHAT, Server.Message.class));
			    			reply.addProperty("chat", openWBC.get(curWBCElement));
			    			output.writeUTF(gson.toJson(reply));
			    			curWBCElement++;
			    		}
			    	}
		    	}
		    	
		    }
		    
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}*/
	
	/*private static String listToString (ArrayList<String> arlist) {
		String strList = "";
		for (String s : arlist) {
			if(!strList.equals("")) {
				strList = strList + "," + s;
			}
			else {
				strList += s;
			}
		}
		return strList;
	}*/
	
	private static String wbList() {
		String wbs = "";
		for(int i = 0; i< whiteboards.size(); i++) {
			if(!wbs.equals("")) {
				wbs = wbs + "," + "Whiteboard " + (i + 1);
			}
			else {
				wbs += "Whiteboard 1";
			}
		}
		return wbs;
	}

}
