import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

public class gText extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	MouseClass paint;
	String message = "default";
	int fontSize  = 20;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gText frame = new gText();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gText() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(111, 66, 226, 53);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Write your Text");
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
}
