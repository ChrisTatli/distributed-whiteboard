package Client;

import javax.swing.*;
import java.awt.*;

public class UserList extends JPanel {

    public UserList(){
        Init();
    }
    private void Init(){
        JTextArea userArea = new JTextArea();
        JScrollPane users = new JScrollPane(userArea);
        users.setPreferredSize(new Dimension(150, 200));
        add(users);
    }

}
