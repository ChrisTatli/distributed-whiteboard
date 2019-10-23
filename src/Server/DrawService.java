package Server;

import Events.DrawEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DrawService extends Remote {

    void addDrawEvent(DrawEvent event) throws RemoteException;

    ArrayList<DrawEvent> getDrawEvents(int startFrom) throws RemoteException;

}
