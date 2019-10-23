package Client;

import javax.swing.*;
import java.awt.*;

public class Chatroom extends JPanel {
    final JFrame frame;

    public Chatroom(JFrame frame){
        this.frame = frame;
        Init();
    }

    public void Init(){
        JTextArea chatArea = new JTextArea();
        JScrollPane chat = new JScrollPane(chatArea);
        chat.setPreferredSize(new Dimension(800, 200));
        UserList userList = new UserList();



        chatArea.setLineWrap(true);
        chatArea.setEditable(false);
        chat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JButton closeServer = new JButton("Close Server");
        add(chat);
        add(userList);
        add(closeServer);
    }

}
