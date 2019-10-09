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
public class frame extends JFrame  {
	private String input = "default";
	private JButton Square, Circle, Ellipse, rectangle, triangle, line, delete,
			colorChooser, move, resize, changeC, Freehand, text, eraser;
	private JRadioButton filled, border;
	protected static String url;
	MouseClass paint;
	private saveGson savegson;
	private JFileChooser SLfile;
	private JMenuBar bar;
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

	public frame() {

		Color panel = new Color(169, 221, 217);
		PressedUndo = false;
		url = "";
		paint = new MouseClass();
		//setSize(990, 770);
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
		//eraser.setBounds(0,35,40,40);
		eraser.setBounds(hoff,off+voff,side,side);
		Square=new JButton(new ImageIcon("Assets/square.png"));
		//Square.setBounds(0,35,40,40);
		Square.setBounds(hoff,off+2*voff+side,side,side);
		triangle=new JButton(new ImageIcon("Assets/triangle.png"));
		//triangle.setBounds(45, 80, 40, 40);
		triangle.setBounds(hoff,off+3*voff+side*2,side,side);
		line=new JButton(new ImageIcon("Assets/line.png"));
		//line.setBounds(0, 125, 40, 40);
		line.setBounds(hoff,off+4*voff+side*3,side,side);
		rectangle=new JButton(new ImageIcon("Assets/rectangle.jpg"));
		//rectangle.setBounds(45, 125, 40, 40);
		rectangle.setBounds(hoff,off+5*voff+side*4,side,side);
		Ellipse=new JButton(new ImageIcon("Assets/ellipse.png"));
		//Ellipse.setBounds(0,170,40,40);
		Ellipse.setBounds(hoff,off+6*voff+side*5,side,side);
		Circle=new JButton(new ImageIcon("Assets/circle.png"));
		//Circle.setBounds(45, 170, 40, 40);
		Circle.setBounds(hoff,off+7*voff+side*6,side,side);
		Freehand=new JButton(new ImageIcon("Assets/freehand.png"));
		//Freehand.setBounds(0,215,40,40);
		Freehand.setBounds(hoff,off+8*voff+side*7,side,side);
		text=new JButton(new ImageIcon("Assets/text.jpg"));
		//text.setBounds(45,215,40,40);
		text.setBounds(hoff,off+9*voff+side*8,side,side);
		textField = new JTextField("Write here");
		//textField.setBounds(0,260,90,50);
		textField.setBounds(0,off+10*voff+side*9,78,50);
		slider = new JSlider();
		//slider.setBounds(0, 315, 100,35);
		slider.setBounds(0,off+11*voff+side*9+50,90,50);
		slider.setValue(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(5);
		slider.setMaximum(50);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		
		
//		eraser=new JButton(new ImageIcon("eraser.png"));
//		//eraser.setBounds(0,35,40,40);
//		eraser.setBounds(hoff,off+voff,side,side);
//		Square=new JButton(new ImageIcon("square.png"));
//		//Square.setBounds(0,35,40,40);
//		Square.setBounds(hoff,off+2*voff+side,side,side);
//		triangle=new JButton(new ImageIcon("triangle.png"));
//		//triangle.setBounds(45, 80, 40, 40);
//		triangle.setBounds(hoff,off+3*voff+side*2,side,side);
//		line=new JButton(new ImageIcon("line.png"));
//		//line.setBounds(0, 125, 40, 40);
//		line.setBounds(hoff,off+4*voff+side*3,side,side);
//		rectangle=new JButton(new ImageIcon("rectangle.jpg"));
//		//rectangle.setBounds(45, 125, 40, 40);
//		rectangle.setBounds(hoff,off+5*voff+side*4,side,side);
//		Ellipse=new JButton(new ImageIcon("ellipse.png"));
//		//Ellipse.setBounds(0,170,40,40);
//		Ellipse.setBounds(hoff,off+6*voff+side*5,side,side);
//		Circle=new JButton(new ImageIcon("circle.png"));
//		//Circle.setBounds(45, 170, 40, 40);
//		Circle.setBounds(hoff,off+7*voff+side*6,side,side);
//		Freehand=new JButton(new ImageIcon("freehand.png"));
//		//Freehand.setBounds(0,215,40,40);
//		Freehand.setBounds(hoff,off+8*voff+side*7,side,side);
//		text=new JButton(new ImageIcon("text.jpg"));
//		//text.setBounds(45,215,40,40);
//		text.setBounds(hoff,off+9*voff+side*8,side,side);
		
		
//		textField = new JTextField("Write here");
//		//textField.setBounds(0,260,90,50);
//		textField.setBounds(0,off+10*voff+side*9,78,50);
//		slider = new JSlider();
//		//slider.setBounds(0, 315, 100,35);
//		slider.setBounds(0,off+11*voff+side*9+50,90,50);
//		slider.setValue(20);
//		slider.setPaintTicks(true);
//		slider.setPaintLabels(true);
//		slider.setMinimum(5);
//		slider.setMaximum(50);
//		slider.setMajorTickSpacing(10);
//		slider.setPaintTicks(true);


		Bshapes = new JPanel();

		Bshapes.setBackground(panel);
		Bshapes.setLayout(null);
		Bshapes.setBounds(0, 0, 80, 900);
		Bshapes.add(Square);
		Bshapes.add(triangle);
		Bshapes.add(line);
		Bshapes.add(rectangle);
		Bshapes.add(Ellipse);
		Bshapes.add(Circle);
		Bshapes.add(Freehand);
		Bshapes.add(text);
		//Bshapes.add(textField);
		//Bshapes.add(slider);
		Bshapes.add(eraser);
		add(Bshapes);


//		move=new JButton(new ImageIcon("Assets/select.png"));
//		move.setToolTipText("move");
//		//move.setBounds(0, 400,40 ,40);
//		move.setBounds(hoff,off+12*voff+side*9+100,side,side);
//		resize=new JButton(new ImageIcon("Assets/size.png"));
//		resize.setToolTipText("resize");
//		//resize.setBounds(45, 400, 40, 40);
//		resize.setBounds(hoff,off+13*voff+side*10+100,side,side);
//		delete=new JButton(new ImageIcon("Assets/delete.png"));
//		delete.setToolTipText("delete");
//		//delete.setBounds(0,445,40,40);
//		delete.setBounds(hoff,off+14*voff+side*11+100,side,side);
//		changeC=new JButton(new ImageIcon("Assets/paint.png"));
//		//changeC.setBounds(45, 445, 40, 40);
//		changeC.setBounds(hoff,off+15*voff+side*12+100,side,side);
//		changeC.setToolTipText("change color");
//		colorChooser=new JButton(new ImageIcon("Assets/palette.jpg"));
//		//colorChooser.setBounds(0, 490, 85, 80);
//		colorChooser.setBounds(hoff,off+16*voff+side*13+100,side,side);
//		colorChooser.setToolTipText("choose color");
//		filled = new JRadioButton("filled");
//		//filled.setBounds(0, 600, 60, 20);
//		filled.setBounds(5,off+18*voff+side*14+100,60,20);
//		border = new JRadioButton("border");
//		//border.setBounds(0, 640, 70, 20);
//		border.setBounds(5,off+18*voff+side*15+100,60,20);
//		Bshapes.add(move);
//		Bshapes.add(resize);
//		Bshapes.add(delete);
//		Bshapes.add(changeC);
//		Bshapes.add(colorChooser);
//		Bshapes.add(filled);
//		Bshapes.add(border);
		
		
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
		changeC=new JButton(new ImageIcon("Assets/paint.png"));
		//changeC.setBounds(45, 445, 40, 40);
		changeC.setBounds(hoff,off+13*voff+side*12,side,side);
		changeC.setToolTipText("change color");
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
		Bshapes.add(move);
		Bshapes.add(resize);
		Bshapes.add(delete);
		Bshapes.add(changeC);
		Bshapes.add(colorChooser);
		Bshapes.add(filled);
		Bshapes.add(border);

		//paint.setBounds(85, 25, 1005, 700);
		paint.setBounds(80, 20, 1200, 900);
		add(paint);
		savegson = new saveGson();

		//paint.setBounds(85, 25, 1005, 700);
		paint.setBounds(80, 20, 1200, 900);
		add(paint);
		savegson = new saveGson();




		jason.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int retval = 0;
				FileChooserSave(retval);
				if (retval == JFileChooser.APPROVE_OPTION) {
					url = "" + SLfile.getSelectedFile();
					try {
						savegson.save(paint.currentShapes);
					} catch (Exception e1) {
					}
				}
			}
		});

		jfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int returnValue = 0;

				//OpenFile(retval);
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
					} catch (Exception e1) {
						e1.printStackTrace();
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
				} catch (Exception e2) {
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

		Ellipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.flag = 1;
				PressedUndo = false;
			}
		});

		Circle.addActionListener(new ActionListener() {
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

		Square.addActionListener(new ActionListener() {
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

		Freehand.addActionListener(new ActionListener() {
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
//
//		text.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				paint.flag = 12;
//
//				PressedUndo = false;
//
//
//				//gText gt = new gText();
//				//gt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				//gt.setVisible(true);
//
//				paint.message = textField.getText();
//				paint.fz = slider.getValue();
//
//			}
//		});
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
				paint.fz = tempFontSize;
			}
		});
		

		colorChooser.addActionListener(new ActionListener() {
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

	private void OpenFile(int retval) {
		SLfile = new JFileChooser();
		retval = SLfile.showOpenDialog(null);
		SLfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		SLfile.setBackground(Color.white);
	}

	private void FileChooserSave(int retval) {
		SLfile = new JFileChooser();
		retval = SLfile.showSaveDialog(null);
		SLfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		SLfile.setBackground(Color.white);
	}

	public static void main(String[] args) {
		try {
			frame f = new frame();
			f.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("unexpected error");
		}
	}

}
