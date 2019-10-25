package Client;

import Enums.DrawType;
import Enums.EventType;
import Events.DrawEvent;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import static java.awt.event.KeyEvent.VK_ENTER;

public class KeyBoard extends KeyAdapter {

    private Point start;
    private DrawType type;
    private Whiteboard whiteboard;
    private Color color;
    private String text;

    KeyBoard(DrawType type, Whiteboard whiteboard){
        this.type = type;
        this.whiteboard = whiteboard;
    }

    public void setStart(Point start) {
        this.start = start;
    }
    public void setText(String text){
        this.text = text;
    }




    public void setDrawType(DrawType type){
        this.type = type;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(start != null && type == DrawType.TEXT){

                char c = e.getKeyChar();
                if(c == VK_ENTER){
                    start = null;
                    text = null;
                    return;
                } else {
                text += c;
                }
                DrawEvent drawEvent = new DrawEvent(EventType.KEYSTROKE);

                drawEvent.drawType = type;
                drawEvent.start = start;
                drawEvent.text = text;
                drawEvent.color = color;
            try {
                whiteboard.drawService.addDrawEvent(drawEvent);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }


                }
                }
                }
