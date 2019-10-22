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
		NEW_WB, OPEN_WB, JOIN_WB, UPDATE, CHAT, REJECT, DISCONNECT, USER, ACCEPT
	}
	
	// Declare the port number
	private static int port = 3005;
	
	// Identifies the user number connected
	private static int counter = 0;
	
	// List of whiteboards
	static ArrayList<Whiteboard> whiteboards = new ArrayList<Whiteboard>();
	
	// List of whiteboard chats
	static ArrayList<ArrayList<ChatMessage>> whiteboardChats = new ArrayList<ArrayList<ChatMessage>>();

	public static void main(String[] args)
	{
		
		// TESTING TODO
		whiteboards.add(new Whiteboard("manager1", "whiteboard1"));
		JsonObject testJson = new JsonObject();
		testJson.addProperty("test", "testValue");
		whiteboards.get(0).addEvent(testJson);
		
		whiteboardChats.add(new ArrayList<ChatMessage>());
		whiteboardChats.get(0).add(new ChatMessage("testUser1", "Hello"));
		whiteboardChats.get(0).add(new ChatMessage("testUser2", "Hi"));
		
		
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
		    boolean isManager = false;
		    String username = "";
		    boolean connected = false;
		    
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
		    		case USER:
		    			username = gson.fromJson(clientMessage.get("user"), String.class);
		    			break;
		    		case OPEN_WB:
		    			reply.add("messageType", gson.toJsonTree(Server.Message.OPEN_WB, Server.Message.class));
				    	reply.addProperty("whiteboards", listToString());
				    	// Serialise the json and send to client
				    	output.writeUTF(gson.toJson(reply));
		    			break;
		    		case JOIN_WB:
		    			curWB = gson.fromJson(clientMessage.get("selectedWB"), int.class);
		    			Whiteboard appliedWB = whiteboards.get(curWB);
		    			if(!appliedWB.getManager().equals(gson.fromJson(clientMessage.get("user"), String.class))) {
		    				// TODO send message to chat with join flag for manager to review
		    				if (!appliedWB.getActiveUsers().contains(appliedWB.getManager())) {
		    					reply.add("messageType", gson.toJsonTree(Server.Message.REJECT, Server.Message.class));
		    					output.writeUTF(gson.toJson(reply));
		    				}
		    				else {
		    					ArrayList<ChatMessage> openWBC = whiteboardChats.get(curWB);
		    					ChatMessage applyMessage = new ChatMessage(username, username);
		    					applyMessage.setFlag(Server.Message.JOIN_WB);
			    				openWBC.add(applyMessage);
			    				isManager = false;	
		    				}
		    			}
		    			else {
		    				isManager = true;
		    				connected = true;
			    			appliedWB.addActiveUser(gson.fromJson(clientMessage.get("user"), String.class));
		    			}
		    			System.out.println(curWB);
		    			// TODO if server has saved whiteboard, send to client
		    			// TODO check username, if it is the manager, open WB, else make popup on manager screen
		    			// to ask to join or decline
		    			break;
		    		case UPDATE:
		    			Whiteboard openWB = whiteboards.get(curWB);
		    			openWB.addEvent((JsonObject) clientMessage.get("update"));
		    			break;
		    		case NEW_WB:
		    			System.out.println(whiteboards.size());
		    			Whiteboard newWB = new Whiteboard(gson.fromJson(clientMessage.get("manager"), String.class), gson.fromJson(clientMessage.get("whiteboardName"), String.class));
		    			newWB.addActiveUser(gson.fromJson(clientMessage.get("manager"), String.class));
		    			whiteboards.add(newWB);
		    			whiteboardChats.add(new ArrayList<ChatMessage>());
		    			curWB = whiteboards.size() - 1;
		    			System.out.println(whiteboards.size());
		    			isManager = true;
		    			connected = true;
		    			break;
		    		case CHAT:
		    			ArrayList<ChatMessage> openWBC = whiteboardChats.get(curWB);
		    			openWBC.add(gson.fromJson(clientMessage.get("chat"), ChatMessage.class));
		    			break;
		    		case DISCONNECT:
		    			whiteboards.get(curWB).removeActiveUser(gson.fromJson(clientMessage.get("user"), String.class));
		    			connected = false;
		    			break;
		    		default:
		    			break;
		    		}
		    		
		    	}
		    	else {
		    		// Checks for updates and sends to client
			    	if(curWB >= 0) { // Has the client chosen a whiteboard
			    		Whiteboard openWB = whiteboards.get(curWB); 
			    		if (openWB.getEvents().size() > curWBElement && connected) {// Are there new updates
			    			reply.add("messageType", gson.toJsonTree(Server.Message.UPDATE, Server.Message.class));
			    			reply.add("update", openWB.getEvents().get(curWBElement));
			    			output.writeUTF(gson.toJson(reply));
			    			curWBElement++;
			    		}
			    		else { // Checks for chat updates and sends to client
				    		ArrayList<ChatMessage> openWBC = whiteboardChats.get(curWB); 
				    		if (openWBC.size() > curWBCElement) { // Are there new chat messages
				    			if (openWBC.get(curWBCElement).getFlag() == Server.Message.CHAT && connected) {
					    			reply.add("messageType", gson.toJsonTree(Server.Message.CHAT, Server.Message.class));
					    			reply.add("chat", gson.toJsonTree(openWBC.get(curWBCElement), ChatMessage.class));
					    			output.writeUTF(gson.toJson(reply));	
				    			}
				    			else if (openWBC.get(curWBCElement).getFlag() == Server.Message.JOIN_WB){
				    				if(isManager) {
				    					// Popup asking if user can connect
				    					reply.add("messageType", gson.toJsonTree(Server.Message.USER, Server.Message.class));
				    					System.out.println(openWBC.get(curWBCElement).getUser());
				    					reply.addProperty("user", openWBC.get(curWBCElement).getUser());
				    					output.writeUTF(gson.toJson(reply));
				    				}
				    			}
				    			else if (openWBC.get(curWBCElement).getFlag() == Server.Message.REJECT) {
				    				System.out.println("REJECT RECIEVED");
				    				System.out.println(username + ":" + openWBC.get(curWBCElement).getMessage());
				    				if(openWBC.get(curWBCElement).getMessage().equals(username)) {
				    					reply.add("messageType", gson.toJsonTree(Server.Message.REJECT, Server.Message.class));
				    					output.writeUTF(gson.toJson(reply));
				    				}
				    			}
				    			else if (openWBC.get(curWBCElement).getFlag() == Server.Message.ACCEPT) {
				    				if(openWBC.get(curWBCElement).getMessage().equals(username)) {
				    					connected = true;
				    					whiteboards.get(curWB).addActiveUser(username);
				    					System.out.println("USER ACCEPTED");
				    				}
				    			}
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
	
	private static String listToString() {
		String wbList = "";
		for (Whiteboard wb : whiteboards) {
			if(!wbList.equals("")) {
				wbList = wbList + "," + wb.getWBName();
			}
			else {
				wbList += wb.getWBName();
			}
		}
		return wbList;
	}
	
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
