package Client;

import javax.swing.*;

public class Popup {
    private JFrame frame = null;

    public Popup(JFrame frame){
        this.frame = frame;
    }

    public void popupError(String message){
        JOptionPane.showMessageDialog(frame, "Error: " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String popupUsername(){
        return JOptionPane.showInputDialog(null, "Enter username", "Login", JOptionPane.QUESTION_MESSAGE);
    }

    public int popupConfirm(String message, String title){
        return JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION);
    }
}
