import Enums.DrawType;
import Enums.EventType;

import java.awt.*;
import java.util.ArrayList;

public class ManagementEvent {
    public int Id;
    public EventType eventType;
    public ArrayList<Shape> shapes;

    public ManagementEvent(EventType eventType){
        this.eventType = eventType;
    }
}
