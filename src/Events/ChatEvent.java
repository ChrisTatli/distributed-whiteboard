package Events;

import Enums.EventType;

import java.io.Serializable;

public class ChatEvent implements Serializable {
    public int Id;
    public EventType eventType;
    public String userName;
    public String message;


    public ChatEvent(EventType eventType){this.eventType = eventType;}

    @Override
    public String toString(){
        return userName + ": " + message + "\n";
    }

}
