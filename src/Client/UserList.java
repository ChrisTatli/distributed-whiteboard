package Client;

import Enums.EventType;
import Events.ManagementEvent;
import Server.ManagementService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserList extends JPanel implements ListSelectionListener, ActionListener {

    private ArrayList<String> userNames;
    private ManagementService managementService;
    private JList userList;
    private JScrollPane users;
    private String selected;
    private User user;

    public UserList(ManagementService managementService, User user){
        this.managementService = managementService;
        this.userNames = new ArrayList<>();
        this.user = user;
        InitPanel();

    }
    private void InitPanel(){
        userList = new JList(userNames.toArray());
        userList.addListSelectionListener(this);
        users = new JScrollPane(userList);
        users.setPreferredSize(new Dimension(150, 180));

        JButton kick = new JButton("Kick");
        kick.addActionListener(this);

        add(users);

        add(kick, BorderLayout.SOUTH);
        users.repaint();
    }

    public void RefreshList( ArrayList<String> userNames){
        userList.setListData(userNames.toArray());
        users.revalidate();
        users.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == userList && !e.getValueIsAdjusting()){
            selected = (String) userList.getSelectedValue();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ManagementEvent managementEvent = new ManagementEvent(EventType.KICK);
        managementEvent.username = selected;
        managementEvent.user = user;
        try {
            managementService.kickUser(managementEvent);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
