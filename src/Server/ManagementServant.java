package Server;

import Client.User;
import Enums.EventType;
import Events.ManagementEvent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ManagementServant extends UnicastRemoteObject implements ManagementService {

    private ArrayList<ManagementEvent> managementEvents;
    private int eventNumber = 0;
    private User managerUser;
    private String whiteboardName;
    private ArrayList<String> activeUsers;


    protected ManagementServant() throws RemoteException {
        managementEvents = new ArrayList<>();
        activeUsers = new ArrayList<>();

        this.eventNumber = 0;
    }

    @Override
    public synchronized ArrayList<ManagementEvent> getManagementEvents(int from) throws RemoteException {
        return new ArrayList(managementEvents.subList(from, managementEvents.size()));
    }

    @Override
    public synchronized void addManagementEvent(ManagementEvent event) throws RemoteException {
        event.Id = eventNumber++;
        managementEvents.add(event);
    }

    @Override
    public synchronized void joinWhiteboard(ManagementEvent event) throws RemoteException {

        if(activeUsers.isEmpty()){
            managerUser = event.user;
            event.user.isAdmin = true;

            activeUsers.add(event.user.userName);
            ManagementEvent adminEvent = new ManagementEvent(EventType.ADMIN);
            adminEvent.user = managerUser;
            adminEvent.userNames = activeUsers;
            addManagementEvent(adminEvent);
            ManagementEvent usersEvent = new ManagementEvent(EventType.UPDATEUSERS);
            usersEvent.userNames = activeUsers;
            addManagementEvent(usersEvent);
        }
        else{
            addManagementEvent(event);
        }

    }

    @Override
    public synchronized void approveUser(ManagementEvent event) throws RemoteException {
        activeUsers.add(event.user.userName);
        ManagementEvent approvedEvent = new ManagementEvent((EventType.APPROVED));
        approvedEvent.user = event.user;
        approvedEvent.userNames = activeUsers;
        addManagementEvent(approvedEvent);

        ManagementEvent usersEvent = new ManagementEvent((EventType.UPDATEUSERS));

        usersEvent.userNames = activeUsers;
        addManagementEvent(usersEvent);
    }

    @Override
    public void kickUser(ManagementEvent event) throws RemoteException {
        if(event.username.equals(managerUser.userName)){return;}
        activeUsers.remove(event.username);
        addManagementEvent(event);
        ManagementEvent usersEvent = new ManagementEvent((EventType.UPDATEUSERS));

        usersEvent.userNames = activeUsers;
        addManagementEvent(usersEvent);
    }

    @Override
    public boolean isUniqueUsername(ManagementEvent event) throws RemoteException {
        return !activeUsers.contains(event.user.userName);
    }

    @Override
    public void declineUser(ManagementEvent event) throws RemoteException {
        ManagementEvent me = new ManagementEvent(EventType.DECLINED);
        me.user = event.user;
        addManagementEvent(me);
    }

    @Override
    public ArrayList<String> getAllUsers(ManagementEvent event) throws RemoteException {
        return activeUsers;
    }
}
