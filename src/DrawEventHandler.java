import java.awt.*;
import java.util.ArrayList;

public class DrawEventHandler implements Runnable{
    private Whiteboard whiteboard;
    private int eventId = 0;


    public DrawEventHandler(Whiteboard whiteboard){
        this.whiteboard = whiteboard;
    }

    private void HandleDrawEvent(DrawEvent event){
        switch(event.eventType){
            case DRAG:
                Graphics graphics = whiteboard.getGraphics();
                switch (event.drawType){
                    case RECT:
                        Rectangle rect = new Rectangle(event.start, event.end, event.color);
                        rect.draw(graphics);
                        break;
                    case LINE:
                        Line line = new Line(event.start, event.end, event.color);
                        line.draw(graphics);
                        break;
                    case ELLIPSE:
                        Ellipse ellipse = new Ellipse(event.start, event.end, event.color);
                        ellipse.draw(graphics);
                        break;
                    case CIRCLE:
                        Circle circle = new Circle(event.start, event.end, event.color);
                        circle.draw(graphics);
                        break;
                    case SQUARE:
                        Square square = new Square(event.start, event.end, event.color);
                        square.draw(graphics);
                        break;
                    case FREE:
                        Freehand free = new Freehand(event.points, event.color);
                        free.draw(graphics);
                        break;

                }
                whiteboard.frame.repaint();
                break;
            case RELEASE:
                switch (event.drawType){
                    case RECT:
                        whiteboard.addShape(new Rectangle(event.start, event.end, event.color));
                        break;
                    case LINE:
                        whiteboard.addShape(new Line(event.start, event.end, event.color));
                        break;
                    case ELLIPSE:
                        whiteboard.addShape(new Ellipse(event.start, event.end, event.color));
                        break;
                    case SQUARE:
                        whiteboard.addShape(new Square(event.start, event.end, event.color));
                        break;
                    case CIRCLE:
                        whiteboard.addShape(new Circle(event.start, event.end, event.color));
                        break;
                    case FREE:
                        whiteboard.addShape(new Freehand(event.points, event.color));
                        break;

                }
                whiteboard.frame.repaint();
                break;

        }
    }

    @Override
    public void run() {
        while(true){
            ArrayList<DrawEvent> drawEvents;
            drawEvents = whiteboard.getDrawEvents(eventId);

            for (DrawEvent event: drawEvents ) {
                HandleDrawEvent(event);
            }

            if(drawEvents.size() > 0) {
                DrawEvent latestEvent = drawEvents.get(drawEvents.size() -1);
                eventId = latestEvent.Id + 1;
            }


        }
    }
}
