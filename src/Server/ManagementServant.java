package Server;

import Events.ManagementEvent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ManagementServant extends UnicastRemoteObject implements ManagementService {

    public ArrayList<ManagementEvent> managementEvents;
    private int eventNumber = 0;
    protected ManagementServant() throws RemoteException {
        managementEvents = new ArrayList<>();
        this.eventNumber = 0;
    }

    @Override
    public ArrayList<ManagementEvent> getManagementEvents(int from) throws RemoteException {
        return new ArrayList(managementEvents.subList(from, managementEvents.size()));
    }

    @Override
    public void addManagementEvent(ManagementEvent event) throws RemoteException {
        event.Id = eventNumber++;
        managementEvents.add(event);
    }
}
