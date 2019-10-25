package Server;

import Events.ManagementEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ManagementService extends Remote {
    ArrayList<ManagementEvent> getManagementEvents(int from) throws RemoteException;

    void addManagementEvent(ManagementEvent event) throws RemoteException;

    void joinWhiteboard(ManagementEvent event) throws RemoteException;

    void approveUser(ManagementEvent event) throws  RemoteException;

    void kickUser(ManagementEvent event) throws RemoteException;

    boolean isUniqueUsername(ManagementEvent event) throws RemoteException;

    void declineUser(ManagementEvent event ) throws RemoteException;

    ArrayList<String> getAllUsers(ManagementEvent event) throws RemoteException;

    void removeUser(ManagementEvent event) throws RemoteException;

     void newBoard(ManagementEvent event) throws  RemoteException;

     void saveBoard(ManagementEvent event) throws RemoteException;

     void loadBoard(ManagementEvent event) throws RemoteException;

}
