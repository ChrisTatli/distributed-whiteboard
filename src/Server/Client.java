package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	private static String username;
	
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
	    	
	    	// TODO Asks user to enter username
	    	// username = 
	    	
	    	// Asks user to connect or start new whiteboard
	    	Server.Message initM = ServerConnectOption() == 0 ? Server.Message.NEW_WB : Server.Message.OPEN_WB;
	    	
	    	initMessage.add("messageType", gson.toJsonTree(initM, Server.Message.class));
	    	initMessage.addProperty("selectedWB", "0");
	    	
	    	output.writeUTF(gson.toJson(initMessage));
	    	System.out.println("Sent json");
	    	output.flush();
	    	
		    while(true)
		    {
		    	JsonObject reply = new JsonObject();
		    	
		    	if(input.available() > 0) {
		    		JsonObject serverMessage = (JsonObject) parser.parse(input.readUTF());
		    		Server.Message messageType = gson.fromJson(serverMessage.get("messageType"), Message.class);
		    		
		    		switch (messageType) {
		    		case OPEN_WB:
		    			System.out.println("Whiteboards: " + serverMessage.get("whiteboards"));
		    			// TODO popup with available whiteboards then send to server OPEN_WB with selected wb
		    			int wbChoice = WhiteboardConnectOption(gson.fromJson(serverMessage.get("whiteboards"), String.class));
		    			reply.add("messageType", gson.toJsonTree(Server.Message.JOIN_WB, Server.Message.class));
		    	    	reply.addProperty("selectedWB", wbChoice);
		    	    	output.writeUTF(gson.toJson(reply));
		    			break;
		    		case UPDATE:
		    			JsonObject update = (JsonObject) serverMessage.get("update");
		    			System.out.println("Update: " + update.toString());
		    			break;
		    		case CHAT:
		    			String chatString = gson.fromJson(serverMessage.get("chat"), String.class);
		    			System.out.println("Chat: " + chatString);
		    			break;
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
	
	private void sendUpdate(JsonObject json, DataOutputStream output, Gson gson) throws IOException {
		JsonObject updateMessage = new JsonObject();
		updateMessage.add("messageType", gson.toJsonTree(Server.Message.UPDATE, Server.Message.class));
		updateMessage.add("update", json);
		output.writeUTF(gson.toJson(updateMessage));
	}
	
	private static int ServerConnectOption() {
		String[] buttons = {"New WhiteBoard", "Connect To WhiteBoard"};
		int conection = JOptionPane.showOptionDialog(null, "New or Existing Whiteboard", "What would you like to do", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
		return conection;
	}
	
	private static int WhiteboardConnectOption(String wbs) {
		String[] wbsArr = wbs.split(",");
		Object wbChoice = JOptionPane.showInputDialog(null, "Select a whiteboard", "Open Whiteboards", JOptionPane.QUESTION_MESSAGE, null, wbsArr, wbsArr[0]);
		return Arrays.asList(wbsArr).indexOf(wbChoice);
	}
	
	private static void sendChat(String chat, DataOutputStream output, Gson gson) throws IOException {
		JsonObject chatMessage = new JsonObject();
		chatMessage.add("messageType", gson.toJsonTree(Server.Message.CHAT, Server.Message.class));
		output.writeUTF(gson.toJson(chatMessage));
	}

}
