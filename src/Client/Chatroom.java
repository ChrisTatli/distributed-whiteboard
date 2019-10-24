package Client;

import Enums.EventType;
import Events.ChatEvent;
import Server.ChatService;
import Server.ManagementService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class Chatroom extends JPanel implements ActionListener {
    final JFrame frame;
    public ManagementService managementService;
    public ChatService chatService;
    public User user;
    public UserList userList;
    private JTextField input;
    private JTextArea chatArea;


    public Chatroom(JFrame frame, ChatService chatService, ManagementService managementService){
        this.frame = frame;
        this.managementService = managementService;
        this.chatService = chatService;

        Init();
    }

    public void Init(){
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.PAGE_AXIS));


        chatArea = new JTextArea(9,60);
        JScrollPane chat = new JScrollPane(chatArea);
        chat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);

        JPanel inputPanel = new JPanel();

         input = new JTextField(55);
        JButton button = new JButton("Send");
        button.addActionListener(this);
        inputPanel.add(input);
        inputPanel.add(button);

        chatPanel.add(chat);
        chatPanel.add(inputPanel);



        userList = new UserList(managementService, user);


        add(chatPanel);

        add(userList);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ChatEvent chatEvent = new ChatEvent(EventType.SENDCHAT);
        chatEvent.userName = user.userName;
        chatEvent.message = input.getText();
        try {
            chatService.addChatEvent(chatEvent);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        input.setText("");
    }


    public void appendChatMessage(String chatMessage){
        chatArea.append(chatMessage);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}
