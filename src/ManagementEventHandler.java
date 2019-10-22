import java.util.ArrayList;

public class ManagementEventHandler implements Runnable{
    private Whiteboard whiteboard;
    private int eventId = 0;

    public ManagementEventHandler(Whiteboard whiteboard){
        this.whiteboard = whiteboard;
    }

    private void HandleManagementEvent(ManagementEvent event){
        switch (event.eventType){
            case NEW:
                whiteboard.clear();
                whiteboard.frame.repaint();
                break;
            case SAVE:
                whiteboard.writeWhiteboard();
                break;
            case LOAD:
                whiteboard.clear();
                whiteboard.readWhiteboard();
                whiteboard.frame.repaint();
                break;


        }
    }

    @Override
    public void run() {
        while(true){
            ArrayList<ManagementEvent> managementEvents;
            managementEvents = whiteboard.getManagementEvents(eventId);

            for(ManagementEvent event: managementEvents){
                HandleManagementEvent(event);
            }

            if(managementEvents.size() > 0){
                ManagementEvent latestEvent = managementEvents.get(managementEvents.size()-1);
                eventId = latestEvent.Id + 1;
            }

        }
    }
}
