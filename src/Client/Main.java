package Client;

import Server.DrawService;
import Server.ManagementService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.*;

public class Main {
    public static Whiteboard whiteboard;
    public static Chatroom chatroom;
    public static DrawService drawService;
    public static ManagementService managementService;

    private static void InitGui(){
        final JFrame frame = new JFrame("Client.Whiteboard");

        frame.getContentPane().setLayout(new BorderLayout());
        whiteboard = new Whiteboard(frame, drawService, managementService);
        frame.getContentPane().add(whiteboard, BorderLayout.CENTER);
        chatroom = new Chatroom(frame);
        chatroom.setPreferredSize(new Dimension(1200, 200));
        frame.getContentPane().add(chatroom, BorderLayout.SOUTH);


        frame.setSize(1200, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        DrawEventHandler drawEventHandler = new DrawEventHandler(whiteboard);
        ManagementEventHandler managementEventHandler = new ManagementEventHandler(whiteboard);


        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                whiteboard.requestFocusInWindow();
            }
        });

        new Thread(drawEventHandler).start();
        new Thread(managementEventHandler).start();
    }

    public static void main(String[] args){
        try {
             drawService = (DrawService) Naming.lookup("rmi://localhost:5099/draw");
             managementService = (ManagementService) Naming.lookup("rmi://localhost:5099/management");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(() -> InitGui());
    }

}
