package Server;

public class ChatMessage {
	
	private String username;
	private String message;
	private Server.Message chatFlag = Server.Message.CHAT;
	
	public ChatMessage(String username, String message) {
		this.message = message;
		this.username = username;
	}
	
	public String getUser() {
		return this.username;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public String toString() {
		return username + ": " + message;
	}
	
	public Server.Message getFlag() {
		return this.chatFlag;
	}
	
	public void setFlag(Server.Message chatFlag) {
		this.chatFlag = chatFlag;
	}
	
}
