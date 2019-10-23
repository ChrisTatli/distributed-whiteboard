package Server;

import Events.ManagementEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ManagementService extends Remote {
    ArrayList<ManagementEvent> getManagementEvents(int from) throws RemoteException;

    void addManagementEvent(ManagementEvent event) throws RemoteException;


}
