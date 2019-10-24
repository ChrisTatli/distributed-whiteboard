package Events;

import Client.User;
import Enums.DrawType;
import Enums.EventType;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ManagementEvent implements Serializable {
    public int Id;
    public EventType eventType;
    public User user;
    public ArrayList<String> userNames;
    public String username;



    public ManagementEvent(EventType eventType){
        this.eventType = eventType;
    }
}
