package Server;

import Events.ChatEvent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServant extends UnicastRemoteObject implements ChatService {
    private ArrayList<ChatEvent> chatEvents;
    private int eventNumber;

    protected ChatServant() throws RemoteException {
        chatEvents = new ArrayList<>();
        eventNumber = 0;
    }

    @Override
    public synchronized void addChatEvent(ChatEvent event) throws RemoteException {
        event.Id = eventNumber++;
        chatEvents.add(event);
    }

    @Override
    public synchronized ArrayList<ChatEvent> getChatEvents(int from) throws RemoteException {
        return new ArrayList(chatEvents.subList(from, chatEvents.size()));
    }
}
