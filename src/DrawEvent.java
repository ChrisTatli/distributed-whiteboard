import Enums.DrawType;
import Enums.EventType;

import java.awt.*;
import java.util.ArrayList;

public class DrawEvent {
    public int Id;
    public EventType eventType;
    public Point start;
    public Point end;
    public ArrayList<Point> points;
    public DrawType drawType;
    public Color color;
    public String text;
    public int strokeWidth;
    public int eraserSize;

    public DrawEvent(EventType eventType){
        this.eventType = eventType;
    }
}
