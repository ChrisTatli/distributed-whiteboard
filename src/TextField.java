import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextField extends JDialog {
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private String message;
	private int fontSize;
	
	public TextField() {
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    //setVisible(true);
	    message = "";
	    fontSize = 12;
	    contentPane = new JPanel();
		textField = new JTextField();
		lblNewLabel = new JLabel("Write your Text");
	}
	
	public void setText() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField.setBounds(111, 66, 226, 53);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel.setBounds(167, 18, 117, 16);
		contentPane.add(lblNewLabel);
		
		JSlider slider = new JSlider();
		slider.setValue(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(5);
		slider.setMaximum(50);
		slider.setBounds(135, 135, 190, 35);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		contentPane.add(slider);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				message = textField.getText();
				fontSize = slider.getValue();
				
				dispose();
			}
		});
		
		btnNewButton.setBounds(167, 194, 117, 29);
		contentPane.add(btnNewButton);
	}
	
	public String getText() {
		return message;
	}
	
	public int getFontSize() {
		return fontSize;
	}
}