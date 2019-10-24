package Client;

import Client.EventHandlers.DrawEventHandler;
import Client.EventHandlers.ManagementEventHandler;
import Enums.EventType;
import Events.ManagementEvent;
import Server.ChatService;
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
    public static ChatService chatService;

    private static void InitGui(){
        final JFrame frame = new JFrame("Client.Whiteboard");
        final Popup popup = new Popup(frame);
        frame.setSize(1200, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                whiteboard.requestFocusInWindow();
            }
        });


        frame.addWindowListener(new FrameListener(frame, popup));
        frame.getContentPane().setLayout(new BorderLayout());
        whiteboard = new Whiteboard(frame, drawService, managementService);
        whiteboard.setDoubleBuffered(true);
        frame.getContentPane().add(whiteboard, BorderLayout.CENTER);
        chatroom = new Chatroom(frame, chatService, managementService);
        chatroom.setPreferredSize(new Dimension(1200, 200));
        frame.getContentPane().add(chatroom, BorderLayout.SOUTH);

        User user = new User();
        whiteboard.user = user;
        chatroom.user = user;


        ManagementEventHandler managementEventHandler = new ManagementEventHandler(whiteboard,chatroom);


        new Thread(managementEventHandler).start();


        while(user.userName==null || user.userName.isEmpty()){
            user.userName = popup.popupUsername();
            if(user.userName == null || user.userName.isEmpty())continue;
            ManagementEvent event = new ManagementEvent(EventType.JOINREQ);
            event.user = user;

            try {
                if(managementService.isUniqueUsername(event) == false){
                    user.userName = "";
                    popup.popupError("Invalid Username");
                } else {
                    whiteboard.user  = user;
                    managementService.joinWhiteboard(event);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }







        //frame.setVisible(true);

    }

    public static void main(String[] args){
        try {
             drawService = (DrawService) Naming.lookup("rmi://localhost:5099/draw");
             managementService = (ManagementService) Naming.lookup("rmi://localhost:5099/management");
             chatService = (ChatService) Naming.lookup("rmi://localhost:5099/chat");


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


class FrameListener extends WindowAdapter{
    Popup popup;
    JFrame frame;
    public FrameListener(JFrame frame, Popup popup){
        this.popup = popup;
        this.frame = frame;
    }

    public void windowClosing(WindowEvent e){
        int choice = popup.popupConfirm("Are you should you want to exit?", "Exit");
        if(choice == JOptionPane.YES_OPTION){
                System.exit(0);
        } else {
            frame.setVisible(true);
        }
    }
}
