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
    private static String ip;
    public static String port;

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
        User user = new User();
        whiteboard = new Whiteboard(frame, drawService, managementService);
        whiteboard.setDoubleBuffered(true);
        frame.getContentPane().add(whiteboard, BorderLayout.CENTER);
        chatroom = new Chatroom(frame, chatService, managementService,user);
        chatroom.setPreferredSize(new Dimension(1200, 200));
        frame.getContentPane().add(chatroom, BorderLayout.SOUTH);


        whiteboard.user = user;



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
        if(args.length != 2){
            System.err.println("Usage: IPAddress Port");
            System.exit(0);
        }
        ip = args[0];
        port = args[1];
        String baseUrl = "rmi://" + ip + ":" + port + "/";

        try {
             drawService = (DrawService) Naming.lookup(baseUrl + "draw");
             managementService = (ManagementService) Naming.lookup(baseUrl + "management");
             chatService = (ChatService) Naming.lookup(baseUrl + "chat");
        } catch (NotBoundException e) {
            System.err.println("Error establishing connection to Server, please ensure you have the correct ip address and port number configured.");
            System.exit(-1);
        } catch (MalformedURLException e) {
            System.err.println("Error establishing connection to Server, please ensure you have the correct ip address and port number configured.");
            System.exit(-1);
        } catch (RemoteException e) {
            System.err.println("Error establishing connection to Server, please ensure you have the correct ip address and port number configured.");
            System.exit(-1);
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
