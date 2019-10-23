package Client;

import Enums.DrawType;
import Enums.EventType;
import Events.DrawEvent;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public void setDrawType(DrawType type){
        this.type = type;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(type == DrawType.TEXT){
            if(text == null){
                text = "";
            }
            char c = e.getKeyChar();
            if(c == '\n'){

            } else {
                text += c;
            }
            DrawEvent drawEvent = new DrawEvent(EventType.KEYSTROKE);

            drawEvent.drawType = type;
            drawEvent.start = start;
            drawEvent.text = text;
            drawEvent.color = color;

            //whiteboard.addDrawEvent(drawEvent);
        }
    }
}
