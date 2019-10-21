import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Server.Server.Message;

public class ClientFrame {
	
	// IP and port
	private static String ip = "localhost";
	private static int port = 3005;
	
	public ClientFrame(frame f) {
		
	}
	
	public static int ServerConnectOption(frame f) {
		String[] buttons = {"New WhiteBoard", "Connect To WhiteBoard"};
		int conection = JOptionPane.showOptionDialog(f, "New or Existing Whiteboard", "What would you like to do", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
		return conection;
	}

	public static void ServerConnect(int option, frame f) {
		try(Socket socket = new Socket(ip, port);)
		{
			// Output and Input Stream
			DataInputStream input = new DataInputStream(socket.getInputStream());
			
		    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		    
		    Message sendData;
		    if(option == 0) {
		    	sendData = Message.NEW_WB;
		    }
		    else {
		    	sendData = Message.OPEN_WB;
		    }
		    
		    // JSON message to client
		    Gson gson = new Gson();
		    
		    JsonObject jsonMessage = new JsonObject();
		    jsonMessage.addProperty("type", gson.toJson(sendData));
		    
	    	output.writeUTF(gson.toJson(jsonMessage));
	    	System.out.println("Data sent to Server--> " + sendData);
	    	output.flush();
	    	
		    while(true)
		    {
		    	String serverMessage = input.readUTF();
	    		System.out.println(serverMessage);
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
