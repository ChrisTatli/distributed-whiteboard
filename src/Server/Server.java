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
		NEW_WB, OPEN_WB
	}
	
	// Declare the port number
	private static int port = 3005;
	
	// Identifies the user number connected
	private static int counter = 0;
	
	// List of whiteboards
	static ArrayList<String> whiteboards = new ArrayList<String>();

	public static void main(String[] args)
	{
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
		    
		    // Read input Json
		    JsonParser parser = new JsonParser();
		    Gson gson = new Gson();
		    
		    while(true)
		    {
		    	if (input.available() > 0) {
		    		JsonObject clientMessage = (JsonObject) parser.parse(input.readUTF());
		    		//String messageType = clientMessage.get("messageType").toString();
		    		Message messageType = gson.fromJson(clientMessage.get("messageType"), Message.class);
		    		System.out.println("CLIENT: "+ messageType.toString());
		    		
		    		JsonObject reply = new JsonObject();
				    
				    if (messageType == Message.OPEN_WB) {
				    	reply.add("messageType", gson.toJsonTree(Server.Message.OPEN_WB, Server.Message.class));
				    	reply.addProperty("whiteboards", listToString(whiteboards));
				    	// Serialise the json and send to client
				    	output.writeUTF(gson.toJson(reply));
				    }
				    else {
				    	output.writeUTF("Message from Server");
				    }
		    	}	
		    }
		    
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static String listToString (ArrayList<String> arlist) {
		String strList = "";
		for (String s : arlist) {
			if(!strList.equals("")) {
				strList = strList + "," + s;
			}
		}
		return strList;
	}

}
