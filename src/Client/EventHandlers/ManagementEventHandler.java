package Client.EventHandlers;

import Client.Chatroom;
import Client.Popup;
import Client.UserList;
import Client.Whiteboard;
import Enums.EventType;
import Events.DrawEvent;
import Events.ManagementEvent;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ManagementEventHandler implements Runnable{
    private Whiteboard whiteboard;
    private Chatroom chatroom;
    private int eventId = 0;
    private boolean isAdmin = false;
    private Popup popup = new Popup(null);

    public ManagementEventHandler(Whiteboard whiteboard, Chatroom chatroom){
        this.whiteboard = whiteboard;
        this.chatroom = chatroom;
    }

    private void HandleManagementEvent(ManagementEvent event) throws RemoteException {
        switch (event.eventType){
            case JOINREQ:
                if(isAdmin){
                    int choice = popup.popupConfirm(event.user.userName +  " would like to join.", "Join Request");
                    if(choice == JOptionPane.YES_OPTION){
                        ManagementEvent approveEvent = new ManagementEvent(EventType.APPROVED);
                        approveEvent.user = event.user;
                        whiteboard.managementService.approveUser(approveEvent);
                    } else {
                        ManagementEvent declineEvent = new ManagementEvent((EventType.DECLINED));
                        declineEvent.user = event.user;
                        whiteboard.managementService.declineUser(declineEvent);
                    }
                }
                break;

            case APPROVED:
                if(event.user.userId.equals(whiteboard.user.userId)){
                    whiteboard.setFrameVisibility();
                    DrawEventHandler drawEventHandler = new DrawEventHandler(whiteboard);

                    new Thread(drawEventHandler).start();
                    ChatEventHandler chatEventHandler = new ChatEventHandler(chatroom);
                    new Thread(chatEventHandler).start();
                }
                break;
            case DECLINED:
                if(event.user.userId.equals(whiteboard.user.userId)){
                    popup.popupError("Admin declined access to the session.");
                    System.exit(0);
                }
                break;
            case ADMIN:
                if(event.user.userId.equals(whiteboard.user.userId)){
                    isAdmin = true;

                    whiteboard.setFrameVisibility();
                    DrawEventHandler drawEventHandler = new DrawEventHandler(whiteboard);
                    new Thread(drawEventHandler).start();
                    ChatEventHandler chatEventHandler = new ChatEventHandler(chatroom);
                    new Thread(chatEventHandler).start();

                }
                break;
            case UPDATEUSERS:
                chatroom.userList.RefreshList(event.userNames);
                break;
            case KICK:
                if(event.username.equals(whiteboard.user.userName)){
                    popup.popupError("Admin kicked  you from the session.");
                    System.exit(0);
                }
                break;
            case RESTRICTED:
                if(event.user.userId.equals(whiteboard.user.userId)){
                    popup.popupError("This action is restricted to admins.");
                }
                break;




        }
    }

    @Override
    public void run() {
        while(true){
            ArrayList<ManagementEvent> managementEvents = new ArrayList<>();;
            try {
                managementEvents = whiteboard.managementService.getManagementEvents(eventId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            for(ManagementEvent event: managementEvents){
                try {
                    HandleManagementEvent(event);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            if(managementEvents.size() > 0){
                ManagementEvent latestEvent = managementEvents.get(managementEvents.size()-1);
                eventId = latestEvent.Id + 1;
            }

        }
    }
}
