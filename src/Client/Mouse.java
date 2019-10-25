package Client;

import Enums.DrawType;
import Enums.EventType;
import Events.DrawEvent;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Mouse extends MouseInputAdapter {


    private Point start;
    private ArrayList<Point> points;
    private DrawType type;
    private Whiteboard whiteboard;
    private Color color;
    private String text;
    private int strokeThickness = 1;
    private int eraserSize = 1;

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
    public  void setStrokeThickness(int strokeThickness){this.strokeThickness = strokeThickness;}
    public  void setEraserSize(int eraserSize){this.eraserSize = eraserSize;}


    @Override
    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
        if(type == DrawType.FREE || type == DrawType.ERASE){
            points = new ArrayList<>();
        }
        else if(type == DrawType.TEXT){
            whiteboard.keyBoard.setStart(start);
            whiteboard.keyBoard.setText("");

            /*DrawEvent drawEvent = new DrawEvent(EventType.PRESSED);
            drawEvent.drawType = type;
            drawEvent.start = start;
            drawEvent.color = color;
            try {
                whiteboard.drawService.addDrawEvent(drawEvent);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }*/
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        Point end = e.getPoint();
        if(type == DrawType.FREE || type == DrawType.ERASE){
            points.add(new Point(e.getX(),e.getY()));
        }
        DrawEvent drawEvent = new DrawEvent(EventType.DRAG);
        drawEvent.drawType = type;
        drawEvent.points = points;
        drawEvent.start = start;
        drawEvent.end = end;
        drawEvent.color = color;
        drawEvent.strokeWidth = strokeThickness;
        drawEvent.eraserSize = eraserSize;

        try {
            whiteboard.drawService.addDrawEvent(drawEvent);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
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
        drawEvent.strokeWidth = strokeThickness;
        drawEvent.eraserSize = eraserSize;

        try {
            whiteboard.drawService.addDrawEvent(drawEvent);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }



}
