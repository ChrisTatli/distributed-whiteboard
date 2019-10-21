import Enums.DrawType;
import Enums.EventType;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Mouse extends MouseInputAdapter {


    private Point start;
    private ArrayList<Point> points;
    private DrawType type;
    private Whiteboard whiteboard;
    private Color color;

    Mouse(DrawType type, Whiteboard whiteboard){
        this.type = type;
        this.whiteboard = whiteboard;
    }

    public void setDrawType(DrawType type){
        this.type = type;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void mouseMoved(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
        if(type == DrawType.FREE){
            points = new ArrayList<>();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        Point end = e.getPoint();
        if(type == DrawType.FREE){
            points.add(new Point(e.getX(),e.getY()));
        }
        DrawEvent drawEvent = new DrawEvent(EventType.DRAG);
        drawEvent.drawType = type;
        drawEvent.points = points;
        drawEvent.start = start;
        drawEvent.end = end;
        drawEvent.color = color;

        whiteboard.addDrawEvent(drawEvent);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        Point end = e.getPoint();

        DrawEvent drawEvent = new DrawEvent(EventType.RELEASE);
        drawEvent.drawType = type;
        drawEvent.points = points;
        drawEvent.start = start;
        drawEvent.end = end;
        drawEvent.color = color;

        whiteboard.addDrawEvent(drawEvent);
    }


}
