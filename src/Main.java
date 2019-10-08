import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    public static Whiteboard whiteboard;

    private static void InitGui(){
        final JFrame frame = new JFrame("Whiteboard");

        frame.getContentPane().setLayout(new BorderLayout());
        whiteboard = new Whiteboard(frame);
        frame.getContentPane().add(whiteboard, BorderLayout.CENTER);

        frame.setSize(1200, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(() -> InitGui());
    }

}
