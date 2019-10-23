package Server;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class Whiteboard {
	
	private String managerUser;
	private String whiteboardName;
	private ArrayList<String> activeUsers = new ArrayList<String>();
	private ArrayList<JsonObject> jsonEvents = new ArrayList<JsonObject>();
	
	public Whiteboard(String manager, String wbName) {
		this.managerUser = manager;
		this.whiteboardName = wbName;
	}
	
	public String getManager() {
		return this.managerUser;
	}
	
	public String getWBName() {
		return this.whiteboardName;
	}
	
	public ArrayList<JsonObject> getEvents() {
		return this.jsonEvents;
	}
	
	public void addEvent(JsonObject event) {
		jsonEvents.add(event);
	}
	
	public ArrayList<String> getActiveUsers() {
		return this.activeUsers;
	}
	
	public void addActiveUser(String user) {
		this.activeUsers.add(user);
	}
	
	public void removeActiveUser(String user) {
		this.activeUsers.remove(user);
	}
	
}
