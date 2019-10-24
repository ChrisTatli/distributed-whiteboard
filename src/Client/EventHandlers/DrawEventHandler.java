package Client.EventHandlers;

import Client.Whiteboard;
import Shapes.*;
import Shapes.Rectangle;
import Events.DrawEvent;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class DrawEventHandler implements Runnable{
    private Whiteboard whiteboard;
    private int eventId = 0;


    public DrawEventHandler(Whiteboard whiteboard){
        this.whiteboard = whiteboard;
    }

    private void HandleDrawEvent(DrawEvent event){
        switch(event.eventType){
            case NEW:
                whiteboard.clear();
                whiteboard.repaint();
                break;
            case SAVE:
                whiteboard.writeWhiteboard();
                break;
            case LOAD:
                whiteboard.clear();
                whiteboard.readWhiteboard();
                whiteboard.repaint();
                break;
            case DRAG:
                Graphics graphics = whiteboard.getGraphics();
                switch (event.drawType){
                    case RECT:
                        Shapes.Rectangle rect = new Rectangle(event.start, event.end, event.color, event.strokeWidth);
                        rect.draw(graphics);
                        break;
                    case LINE:
                        Line line = new Line(event.start, event.end, event.color, event.strokeWidth);
                        line.draw(graphics);
                        break;
                    case ELLIPSE:
                        Ellipse ellipse = new Ellipse(event.start, event.end, event.color, event.strokeWidth);
                        ellipse.draw(graphics);
                        break;
                    case CIRCLE:
                        Circle circle = new Circle(event.start, event.end, event.color, event.strokeWidth);
                        circle.draw(graphics);
                        break;
                    case SQUARE:
                        Square square = new Square(event.start, event.end, event.color, event.strokeWidth);
                        square.draw(graphics);
                        break;
                    case FREE:
                        Freehand free = new Freehand(event.points, event.color, event.strokeWidth);
                        free.draw(graphics);
                        break;
                    case ERASE:
                        Eraser eraser = new Eraser(event.points, event.eraserSize, whiteboard.getBackground() );
                        eraser.draw(graphics);
                        break;

                }
                whiteboard.repaint();

                break;
            case RELEASE:
                switch (event.drawType){
                    case RECT:
                        whiteboard.addShape(new Shapes.Rectangle(event.start, event.end, event.color, event.strokeWidth));
                        break;
                    case LINE:
                        whiteboard.addShape(new Line(event.start, event.end, event.color, event.strokeWidth));
                        break;
                    case ELLIPSE:
                        whiteboard.addShape(new Ellipse(event.start, event.end, event.color, event.strokeWidth));
                        break;
                    case SQUARE:
                        whiteboard.addShape(new Square(event.start, event.end, event.color, event.strokeWidth));
                        break;
                    case CIRCLE:
                        whiteboard.addShape(new Circle(event.start, event.end, event.color, event.strokeWidth));
                        break;
                    case FREE:
                        whiteboard.addShape(new Freehand(event.points, event.color, event.strokeWidth));
                        break;
                    case ERASE:
                        whiteboard.addShape(new Eraser(event.points, event.eraserSize, whiteboard.getBackground()));
                        break;

                }
                whiteboard.repaint();
                break;
            case PRESSED:
                Text text = new Text(event.start,"event.text", Color.BLACK);
                whiteboard.addShape((new Text(event.start,"event.text", Color.BLACK)));
                text.draw(whiteboard.getGraphics());
                whiteboard.repaint();
                break;
        }
    }

    @Override
    public void run() {
        while(true){
            ArrayList<DrawEvent> drawEvents = new ArrayList<>();
            try {
                drawEvents = whiteboard.drawService.getDrawEvents(eventId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(drawEvents.size() > 0){
                for (DrawEvent event: drawEvents ) {
                    HandleDrawEvent(event);
                }
            }


            if(drawEvents.size() > 0) {
                DrawEvent latestEvent = drawEvents.get(drawEvents.size() - 1);
                eventId = latestEvent.Id + 1;
            }


        }
    }
}
