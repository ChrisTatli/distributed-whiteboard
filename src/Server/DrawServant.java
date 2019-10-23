package Server;

import Events.DrawEvent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DrawServant extends UnicastRemoteObject implements DrawService {

    private ArrayList<DrawEvent> drawEvents;
    private int eventNumber;

    protected DrawServant() throws RemoteException {
        this.drawEvents = new ArrayList<>();
        this.eventNumber = 0;
    }

    @Override
    public void addDrawEvent(DrawEvent event) throws RemoteException {
        event.Id = eventNumber++;
        drawEvents.add(event);
    }

    @Override
    public ArrayList<DrawEvent> getDrawEvents(int from) throws RemoteException {
        return new ArrayList(drawEvents.subList(from, drawEvents.size()));
    }
}
