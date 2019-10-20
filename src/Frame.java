import java.awt.Color;
import java.awt.Graphics2D;
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
public class Frame extends JFrame  {
	private String input = "default";
	private JButton square, circle, ellipse, rectangle, triangle, line, delete,
			colorChooser, move, resize, changeColor, freehand, text, eraser;
	private JRadioButton filled, border;
	protected static String url;
	private MouseClass paint;
	private SaveGson saveGson;
	private JFileChooser saveOpenFile;
	private JMenuBar bar;
	private JMenu file;
	private JMenu edit;
	private JMenu save;
	private JMenu open;
	private JMenuItem jason;
	private JMenuItem jFile;
	private JMenuItem undo;
	private JMenuItem redo;
	private JPanel bShapes;
	private boolean pressedUndo;
	private JTextField textField;
	private JSlider slider;

	public void setText(String text) {
		input = text;
		//System.out.println(input);
	}
	public String getText() {
		return input;
	}

	public Frame() {

		Color panel = new Color(169, 221, 217);
		pressedUndo = false;
		url = "";
		paint = new MouseClass();
		//setSize(990, 770);
		setSize(1200, 900);
		setBackground(panel);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);


		jason = new JMenuItem("As Json");
		jFile = new JMenuItem("Json file");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		edit = new JMenu("Edit");
		edit.add(undo);
		edit.add(redo);
		save = new JMenu("Save");
		save.add(jason);
		open = new JMenu("Open");
		open.add(jFile);
		file = new JMenu("File");
		file.add(save);
		file.add(open);
		bar = new JMenuBar();
		bar.add(file);
		bar.add(edit);
		bar.setBounds(0, 0, 1200, 20);

		add(bar);
		int off =20;
		int voff = 5;
		int hoff=15;
		int side = 40;

		eraser=new JButton(new ImageIcon("Assets/eraser.png"));
		//Eraser.setBounds(0,35,40,40);
		eraser.setBounds(hoff,off+voff,side,side);
		square=new JButton(new ImageIcon("Assets/square.png"));
		//Square.setBounds(0,35,40,40);
		square.setBounds(hoff,off+2*voff+side,side,side);
		triangle=new JButton(new ImageIcon("Assets/triangle.png"));
		//triangle.setBounds(45, 80, 40, 40);
		triangle.setBounds(hoff,off+3*voff+side*2,side,side);
		line=new JButton(new ImageIcon("Assets/line.png"));
		//line.setBounds(0, 125, 40, 40);
		line.setBounds(hoff,off+4*voff+side*3,side,side);
		rectangle=new JButton(new ImageIcon("Assets/rectangle.jpg"));
		//rectangle.setBounds(45, 125, 40, 40);
		rectangle.setBounds(hoff,off+5*voff+side*4,side,side);
		ellipse=new JButton(new ImageIcon("Assets/ellipse.png"));
		//Ellipse.setBounds(0,170,40,40);
		ellipse.setBounds(hoff,off+6*voff+side*5,side,side);
		circle=new JButton(new ImageIcon("Assets/circle.png"));
		//Circle.setBounds(45, 170, 40, 40);
		circle.setBounds(hoff,off+7*voff+side*6,side,side);
		freehand=new JButton(new ImageIcon("Assets/freehand.png"));
		//Freehand.setBounds(0,215,40,40);
		freehand.setBounds(hoff,off+8*voff+side*7,side,side);
		text=new JButton(new ImageIcon("Assets/text.jpg"));
		//Text.setBounds(45,215,40,40);
		text.setBounds(hoff,off+9*voff+side*8,side,side);


		bShapes = new JPanel();
		bShapes.setBackground(panel);
		bShapes.setLayout(null);
		bShapes.setBounds(0, 0, 80, 900);
		bShapes.add(square);
		bShapes.add(triangle);
		bShapes.add(line);
		bShapes.add(rectangle);
		bShapes.add(ellipse);
		bShapes.add(circle);
		bShapes.add(freehand);
		bShapes.add(text);
		//Bshapes.add(textField);
		//Bshapes.add(slider);
		bShapes.add(eraser);
		add(bShapes);


	
		
		move=new JButton(new ImageIcon("Assets/select.png"));
		move.setToolTipText("move");
		//move.setBounds(0, 400,40 ,40);
		move.setBounds(hoff,off+10*voff+side*9,side,side);
		resize=new JButton(new ImageIcon("Assets/size.png"));
		resize.setToolTipText("resize");
		//resize.setBounds(45, 400, 40, 40);
		resize.setBounds(hoff,off+11*voff+side*10,side,side);
        delete=new JButton(new ImageIcon("Assets/delete.png"));
        delete.setToolTipText("delete");
        //delete.setBounds(0,445,40,40);
        delete.setBounds(hoff,off+12*voff+side*11,side,side);
		changeColor=new JButton(new ImageIcon("Assets/paint.png"));
		//changeC.setBounds(45, 445, 40, 40);
		changeColor.setBounds(hoff,off+13*voff+side*12,side,side);
		changeColor.setToolTipText("change color");
		colorChooser=new JButton(new ImageIcon("Assets/palette.jpg"));
		//colorChooser.setBounds(0, 490, 85, 80);
		colorChooser.setBounds(hoff,off+14*voff+side*13,side,side);
		colorChooser.setToolTipText("choose color");
		filled = new JRadioButton("filled");
		//filled.setBounds(0, 600, 60, 20);
		filled.setBounds(5,off+15*voff+side*14,60,20);
		border = new JRadioButton("border");
		//border.setBounds(0, 640, 70, 20);
		border.setBounds(5,off+16*voff+side*15,60,20);
		bShapes.add(move);
		bShapes.add(resize);
		bShapes.add(delete);
		bShapes.add(changeColor);
		bShapes.add(colorChooser);
		bShapes.add(filled);
		bShapes.add(border);

		//paint.setBounds(85, 25, 1005, 700);
		paint.setBounds(80, 20, 1200, 900);
		add(paint);
		saveGson = new SaveGson();

		//paint.setBounds(85, 25, 1005, 700);
		paint.setBounds(80, 20, 1200, 900);
		add(paint);
		saveGson = new SaveGson();




		jason.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int retval = 0;
				FileChooserSave(retval);
				if (retval == JFileChooser.APPROVE_OPTION) {
					url = "" + saveOpenFile.getSelectedFile();
					try {
						saveGson.save(paint.currentShapes);
					} catch (Exception exception) {
					}
				}
			}
		});

		jFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int returnValue = 0;

				//OpenFile(retval);
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					url = "" + jfc.getSelectedFile().getAbsolutePath();
					try {
						saveGson.load(url, paint.currentShapes, paint.shapes,
								paint.ur.undo, paint.ur.lastAction);
						paint.currentShapes =saveGson.cshape;
						paint.shapes = saveGson.shape;
						paint.ur.undo = saveGson.undo;
						paint.ur.lastAction = saveGson.lastaction;
						paint.repaint();
					} catch (Exception exception) {
						exception.printStackTrace();
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
						pressedUndo = true;
						paint.ur.undo(paint.currentShapes);
						paint.repaint();
					}
				} catch (Exception exception) {
				}
			}
		});

		redo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (pressedUndo == true && !paint.ur.redo.isEmpty()&&!paint.ur.undo.isEmpty()) {
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
				pressedUndo = false;

			}
		});

		move.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				paint.flag = 6;
				pressedUndo = false;
			}
		});

		ellipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 1;
				pressedUndo = false;
			}
		});

		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 7;
				pressedUndo = false;
			}
		});

		rectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 2;
				pressedUndo = false;
			}
		});

		square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 8;
				pressedUndo = false;
			}
		});

		triangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 3;
				pressedUndo = false;
			}
		});

		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 4;
				pressedUndo = false;
			}
		});

		freehand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 11;
				pressedUndo = false;
			}
		});

		eraser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 13;
				pressedUndo = false;
//				PointSlider ps = new PointSlider();
//				ps.setPointSize();
//				ps.setVisible(true);
//				int tempPointSize = ps.getPointSize();
//				paint.pointSize = tempPointSize;
			}
		});

		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 12;
				TextField tf = new TextField();
				tf.setText();
				tf.setVisible(true);
				String tempText = tf.getText();
				int tempFontSize = tf.getFontSize();
				paint.message = tempText;
				paint.fontSize = tempFontSize;
			}
		});
		

		colorChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.color = JColorChooser.showDialog(paint, "Choose a color",
						paint.color);
				pressedUndo = false;
			}
		});

		changeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 9;
				pressedUndo = false;
			}
		});

		filled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.isFilled = true;
				pressedUndo = false;
				border.setSelected(false);
			}
		});

		border.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.isFilled = false;
				pressedUndo = false;
				filled.setSelected(false);
			}
		});

		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressedUndo = false;
				paint.flag = 10;
			}
		});

	}

	private void OpenFile(int retval) {
		saveOpenFile = new JFileChooser();
		retval = saveOpenFile.showOpenDialog(null);
		saveOpenFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		saveOpenFile.setBackground(Color.white);
	}

	private void FileChooserSave(int retval) {
		saveOpenFile = new JFileChooser();
		retval = saveOpenFile.showSaveDialog(null);
		saveOpenFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		saveOpenFile.setBackground(Color.white);
	}

	public static void main(String[] args) {
		try {
			Frame f = new Frame();
			f.setVisible(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("unexpected error");
		}
	}

}
