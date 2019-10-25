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
    public synchronized void addDrawEvent(DrawEvent event) throws RemoteException {
        event.Id = eventNumber++;
        drawEvents.add(event);
    }

    @Override
    public synchronized ArrayList<DrawEvent> getDrawEvents(int from) throws RemoteException {
        if(from > drawEvents.size()){
            from = 0;
        }
        ArrayList events = new ArrayList(drawEvents.subList(from, drawEvents.size()));
        return events;
    }

    @Override
    public synchronized void clearEvents(DrawEvent event) throws RemoteException {

        drawEvents.clear();
        eventNumber = 0;
        addDrawEvent(event);
    }

    public synchronized void loadEvents(ArrayList<DrawEvent> events) throws RemoteException{
        drawEvents.clear();
        eventNumber = 0;
        for (DrawEvent event: events
             ) {
            addDrawEvent(event);
        }


    }

   }
