package Client.EventHandlers;

import Client.Chatroom;
import Events.ChatEvent;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatEventHandler implements Runnable{
    private Chatroom chatroom;
    private int eventId;

    public ChatEventHandler(Chatroom chatroom){
        this.chatroom  = chatroom;

    }

    private void HandleChatEvent(ChatEvent event){
        switch (event.eventType){
            case SENDCHAT:
                chatroom.appendChatMessage(event.toString());
                break;
        }
    }
    @Override
    public void run() {
        while(true){
            ArrayList<ChatEvent> chatEvents = new ArrayList<>();

            try {
                chatEvents = chatroom.chatService.getChatEvents(eventId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(chatEvents.size() > 0){
                for(ChatEvent event: chatEvents){
                    HandleChatEvent(event);
                }
            }

            if(chatEvents.size() > 0){
                ChatEvent latestEvent = chatEvents.get(chatEvents.size()-1);
                eventId = latestEvent.Id + 1;
            }
        }
    }
}
