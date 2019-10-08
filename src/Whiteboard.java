import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;



@SuppressWarnings("serial")
public class Whiteboard extends JFrame  {
	private String input = "default";
	private JButton square, circle, ellipse, rectangle, triangle, line, delete,
			colorPicker, move, resize, changeC, freehand, text, eraser;
	private JRadioButton filled, border;
	protected static String url;
	MouseClass paint;
	private SaveGson savegson;
	private JFileChooser SLfile;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenu edit;
	private JMenu save;
	private JMenu load;
	private JMenuItem jason;
	private JMenuItem jfile;
	private JMenuItem undo;
	private JMenuItem redo;
	private JPanel Bshapes;
	private boolean PressedUndo;
	private JTextField textField;
	private JSlider slider;

	public void setText(String text) {
		input = text;
		//System.out.println(input);
	}
	public String getText() {
		return input;
	}

	public Whiteboard() {

		Color panel = new Color(169, 221, 217);
		PressedUndo = false;
		url = "";
		paint = new MouseClass();
		setSize(1200, 900);
		setBackground(panel);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);


		jason = new JMenuItem("As Json");
		jfile = new JMenuItem("Json file");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		edit = new JMenu("Edit");
		edit.add(undo);
		edit.add(redo);
		save = new JMenu("save");
		save.add(jason);
		load = new JMenu("load");
		load.add(jfile);
		file = new JMenu("File");
		file.add(save);
		file.add(load);
		menuBar = new JMenuBar();
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.setBounds(0, 0, 1200, 20);

		add(menuBar);
		int off =20;
		int voff = 5;
		int hoff=15;
		int side = 40;

		eraser=new JButton(new ImageIcon("Assets/eraser.png"));

		eraser.setBounds(hoff,off+voff,side,side);
		square =new JButton(new ImageIcon("Assets/square.png"));

		square.setBounds(hoff,off+2*voff+side,side,side);
		triangle=new JButton(new ImageIcon("Assets/triangle.png"));

		triangle.setBounds(hoff,off+3*voff+side*2,side,side);
		line=new JButton(new ImageIcon("Assets/line.png"));

		line.setBounds(hoff,off+4*voff+side*3,side,side);
		rectangle=new JButton(new ImageIcon("Assets/rectangle.jpg"));

		rectangle.setBounds(hoff,off+5*voff+side*4,side,side);
		ellipse =new JButton(new ImageIcon("Assets/ellipse.png"));

		ellipse.setBounds(hoff,off+6*voff+side*5,side,side);
		circle =new JButton(new ImageIcon("Assets/circle.png"));

		circle.setBounds(hoff,off+7*voff+side*6,side,side);
		freehand =new JButton(new ImageIcon("Assets/freehand.png"));

		freehand.setBounds(hoff,off+8*voff+side*7,side,side);
		text=new JButton(new ImageIcon("Assets/text.jpg"));

		text.setBounds(hoff,off+9*voff+side*8,side,side);
		textField = new JTextField("Write here");

		textField.setBounds(0,off+10*voff+side*9,78,50);
		slider = new JSlider();

		slider.setBounds(0,off+11*voff+side*9+50,90,50);
		slider.setValue(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(5);
		slider.setMaximum(50);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);


		Bshapes = new JPanel();

		Bshapes.setBackground(panel);
		Bshapes.setLayout(null);
		Bshapes.setBounds(0, 0, 80, 900);
		Bshapes.add(square);
		Bshapes.add(triangle);
		Bshapes.add(line);
		Bshapes.add(rectangle);
		Bshapes.add(ellipse);
		Bshapes.add(circle);
		Bshapes.add(freehand);
		Bshapes.add(text);
		Bshapes.add(textField);
		Bshapes.add(slider);
		Bshapes.add(eraser);
		add(Bshapes);


		move=new JButton(new ImageIcon("Assets/select.png"));
		move.setToolTipText("move");

		move.setBounds(hoff,off+12*voff+side*9+100,side,side);
		resize=new JButton(new ImageIcon("Assets/size.png"));
		resize.setToolTipText("resize");

		resize.setBounds(hoff,off+13*voff+side*10+100,side,side);
		delete=new JButton(new ImageIcon("Assets/delete.png"));
		delete.setToolTipText("delete");

		delete.setBounds(hoff,off+14*voff+side*11+100,side,side);
		changeC=new JButton(new ImageIcon("Assets/paint.png"));

		changeC.setBounds(hoff,off+15*voff+side*12+100,side,side);
		changeC.setToolTipText("change color");
		colorPicker =new JButton(new ImageIcon("Assets/palette.jpg"));

		colorPicker.setBounds(hoff,off+16*voff+side*13+100,side,side);
		colorPicker.setToolTipText("choose color");
		filled = new JRadioButton("filled");

		filled.setBounds(5,off+18*voff+side*14+100,60,20);
		border = new JRadioButton("border");

		border.setBounds(5,off+18*voff+side*15+100,60,20);
		Bshapes.add(move);
		Bshapes.add(resize);
		Bshapes.add(delete);
		Bshapes.add(changeC);
		Bshapes.add(colorPicker);
		Bshapes.add(filled);
		Bshapes.add(border);

		paint.setBounds(80, 20, 1200, 900);
		add(paint);
		savegson = new SaveGson();




		jason.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int retval = 0;
				FileChooserSave();
				if (retval == JFileChooser.APPROVE_OPTION) {
					url = "" + SLfile.getSelectedFile();
					try {
						savegson.save(paint.currentShapes);
					} catch (Exception ex) {
					}
				}
			}
		});

		jfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int returnValue = 0;
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					url = "" + jfc.getSelectedFile().getAbsolutePath();
					try {
						savegson.load(url, paint.currentShapes, paint.shapes,
								paint.ur.undo, paint.ur.lastaction);
						paint.currentShapes =savegson.cshape;
						paint.shapes = savegson.shape;
						paint.ur.undo = savegson.undo;
						paint.ur.lastaction = savegson.lastaction;
						paint.repaint();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});

		undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (paint.ur.undo.isEmpty()) {
					} else {
						PressedUndo = true;
						paint.ur.undo(paint.currentShapes);
						paint.repaint();
					}
				} catch (Exception ex) {
				}
			}
		});

		redo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (PressedUndo == true && !paint.ur.redo.isEmpty()&&!paint.ur.undo.isEmpty()) {
					paint.ur.redo(paint.currentShapes);
					paint.repaint();
				} else {
				}
			}
		});

		resize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				paint.flag = 5;
				PressedUndo = false;

			}
		});

		move.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				paint.flag = 6;
				PressedUndo = false;
			}
		});

		ellipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 1;
				PressedUndo = false;
			}
		});

		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 7;
				PressedUndo = false;
			}
		});

		rectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 2;
				PressedUndo = false;
			}
		});

		square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 8;
				PressedUndo = false;
			}
		});

		triangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 3;
				PressedUndo = false;
			}
		});

		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 4;
				PressedUndo = false;
			}
		});

		freehand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 11;
				PressedUndo = false;
			}
		});

		eraser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 13;
				PressedUndo = false;
			}
		});

		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 12;

				PressedUndo = false;

				paint.message = textField.getText();
				paint.fz = slider.getValue();

			}
		});

		colorPicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.color = JColorChooser.showDialog(paint, "Choose a color",
						paint.color);
				PressedUndo = false;
			}
		});

		changeC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 9;
				PressedUndo = false;
			}
		});

		filled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.isFilled = true;
				PressedUndo = false;
				border.setSelected(false);
			}
		});

		border.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.isFilled = false;
				PressedUndo = false;
				filled.setSelected(false);
			}
		});

		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PressedUndo = false;
				paint.flag = 10;
			}
		});

	}

	private void BuildMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("");
		menuItem.setActionCommand("NewBoard");
		menuItem.addActionListener(this);
		menu.add(menuItem);


	}


	private void FileChooserSave() {
		SLfile = new JFileChooser();
		SLfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		SLfile.setBackground(Color.white);
	}

	public static void main(String[] args) {
		try {
			Whiteboard whiteboard = new Whiteboard();
			whiteboard.setVisible(true);
		} catch (Exception e) {
			System.out.println("unexpected error");
		}
	}

}
