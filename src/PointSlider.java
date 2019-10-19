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

	public class PointSlider extends JDialog {
		private JPanel contentPane;
		private JLabel lblNewLabel;
		private int pointSize;
		
		public PointSlider() {
			setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
			setBounds(100, 100, 450, 300);
		    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    //setVisible(true);
		    pointSize = 10;
		    contentPane = new JPanel();
			lblNewLabel = new JLabel("Select size");
		}
		
		public void setPointSize() {
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			lblNewLabel.setBounds(167, 18, 117, 16);
			contentPane.add(lblNewLabel);
			
			JSlider slider = new JSlider();
			slider.setValue(10);
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
					
					
					
					pointSize = slider.getValue();
					
					dispose();
				}
			});
			
			btnNewButton.setBounds(167, 194, 117, 29);
			contentPane.add(btnNewButton);
		}
		
		
		
		public int getPointSize() {
			return pointSize;
		}
	}