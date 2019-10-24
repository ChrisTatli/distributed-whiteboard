package Server;

import Events.ChatEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatService extends Remote {
    void addChatEvent(ChatEvent event) throws RemoteException;

    ArrayList<ChatEvent> getChatEvents(int from) throws RemoteException;

}
