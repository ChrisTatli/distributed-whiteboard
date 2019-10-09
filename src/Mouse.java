import javax.swing.event.MouseInputAdapter;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Mouse extends MouseInputAdapter {


    private Point start;
    private ArrayList<Point> points;
    private DrawType drawType;

    @Override
    public void mouseMoved(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
        if(drawType == DrawType.FREE){
            points = new ArrayList<Point>();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        Point end = e.getPoint();
        if(drawType == DrawType.FREE){
            points.add(new Point(e.getX(),e.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        Point end = e.getPoint();

    }

    public Point getStart() {
        return start;
    }

}
