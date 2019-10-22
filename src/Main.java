import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.EventHandler;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    public static Whiteboard whiteboard;
    public static Chatroom chatroom;

    private static void InitGui(){
        final JFrame frame = new JFrame("Whiteboard");

        frame.getContentPane().setLayout(new BorderLayout());
        whiteboard = new Whiteboard(frame);
        frame.getContentPane().add(whiteboard, BorderLayout.CENTER);
        chatroom = new Chatroom(frame);
        chatroom.setPreferredSize(new Dimension(1200, 200));
        frame.getContentPane().add(chatroom, BorderLayout.SOUTH);


        frame.setSize(1200, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        DrawEventHandler drawEventHandler = new DrawEventHandler(whiteboard);
        whiteboard.handler = drawEventHandler;

        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                whiteboard.requestFocusInWindow();
            }
        });

        new Thread(drawEventHandler).start( );
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(() -> InitGui());
    }

}
