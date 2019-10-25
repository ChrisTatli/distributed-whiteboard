package Client;

import Enums.DrawType;
import Enums.EventType;
import Events.DrawEvent;
import Events.ManagementEvent;
import Server.DrawService;
import Server.ManagementService;
import Shapes.Shape;
import com.google.gson.Gson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class Whiteboard extends JPanel implements ActionListener {


	final JFrame frame;
	private JMenuBar menuBar;
	private Mouse mouse;
	private KeyBoard keyBoard;
	public User user;
	public DrawService drawService;
	public ManagementService managementService;

	private ArrayList<Shapes.Shape> shapes = new ArrayList<>();
	private DrawType drawType = DrawType.FREE;

	public JFrame getFrame() {
		return frame;
	}

	public Whiteboard(JFrame frame, DrawService drawService, ManagementService managementService) {

		this.frame = frame;
		this.frame.setVisible(false);

		this.drawService = drawService;
		this.managementService = managementService;
		this.menuBar = BuildMenu();
		this.frame.setJMenuBar(this.menuBar);
		mouse = new Mouse(drawType, this);
		mouse.setColor(Color.BLACK);
		keyBoard = new KeyBoard(drawType, this);
		addKeyListener(keyBoard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	public void setFrameVisibility(){
		this.frame.setVisible(true);
	}



	public synchronized void clear(){
		shapes.clear();
	}

	@Override
	public synchronized void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		for (Shapes.Shape shape : shapes) {
			shape.draw(graphics);
		}
	}

	public synchronized  void addShape(Shape shape){
	    shapes.add(shape);
    }

	private JMenuBar BuildMenu(){
		JMenuBar menuBar = new JMenuBar();

		//FILE MENU

			JMenu fileMenu = new JMenu("File");

			JMenuItem newBoard = new JMenuItem("New");
			newBoard.setActionCommand("New");
			newBoard.addActionListener(this);
			fileMenu.add(newBoard);
			fileMenu.addSeparator();

			JMenuItem load = new JMenuItem("Load");
			load.setActionCommand("Load");
			load.addActionListener(this);
			fileMenu.add(load);

			fileMenu.addSeparator();

			JMenuItem save = new JMenuItem(("Save"));
			save.setActionCommand(("Save"));
			save.addActionListener(this);
			fileMenu.add(save);

			menuBar.add(fileMenu);



		//Shapes
		JButton freeHand = new JButton(new ImageIcon("Assets/pointer.png"));
		freeHand.setActionCommand("Free");
		freeHand.addActionListener(this);
		menuBar.add(freeHand);

		JButton eraser = new JButton(new ImageIcon("Assets/erase.png"));
		eraser.setActionCommand("Erase");
		eraser.addActionListener(this);
		menuBar.add(eraser);

		JButton line = new JButton(new ImageIcon("Assets/line.png"));
		line.setActionCommand("Line");
		line.addActionListener(this);
		menuBar.add(line);



		JButton square = new JButton(new ImageIcon("Assets/square.png"));
		square.setActionCommand("Square");
		square.addActionListener(this);
		menuBar.add(square);

		JButton rectangle = new JButton(new ImageIcon("Assets/rectangle.png"));
		rectangle.setActionCommand("Rectangle");
		rectangle.addActionListener(this);
		menuBar.add(rectangle);

		JButton circle = new JButton(new ImageIcon("Assets/circle.png"));
		circle.setActionCommand("Circle");
		circle.addActionListener(this);
		menuBar.add(circle);

		JButton ellipse = new JButton(new ImageIcon("Assets/ellipse.png"));
		ellipse.setActionCommand("Ellipse");
		ellipse.addActionListener(this);
		menuBar.add(ellipse);

		JButton text = new JButton(new ImageIcon("Assets/text.png"));
		text.setActionCommand("Text");
		text.addActionListener(this);
		menuBar.add(text);






		//colors
		JButton colorMenu = new JButton(new ImageIcon("Assets/rgb.png"));
		colorMenu.setActionCommand("Color");
		colorMenu.addActionListener(this);

		menuBar.add(colorMenu);

		//StrokeWidth
		JSlider strokeSlider = new JSlider(1,5,1);
		Hashtable strokePos = new Hashtable();
		strokePos.put(1, new JLabel("1"));
		strokePos.put(2, new JLabel("2"));
		strokePos.put(3, new JLabel("3"));
		strokePos.put(4, new JLabel("4"));
		strokePos.put(5, new JLabel("5"));
		strokeSlider.setLabelTable(strokePos);
		strokeSlider.setMinorTickSpacing(1);
		strokeSlider.setPaintLabels(true);

		strokeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int strokeThickness = strokeSlider.getValue();
				mouse.setStrokeThickness(strokeThickness);
			}
		});
		menuBar.add(strokeSlider);

		//Eraser Slider
		JSlider eraserSlider = new JSlider(1,3,1);
		Hashtable eraserPos = new Hashtable();
		eraserPos.put(1, new JLabel("S"));
		eraserPos.put(2, new JLabel("M"));
		eraserPos.put(3, new JLabel("L"));
		eraserSlider.setLabelTable(eraserPos);
		eraserSlider.setMinorTickSpacing(1);
		eraserSlider.setPaintLabels(true);

		eraserSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int eraserSize = eraserSlider.getValue();
				mouse.setEraserSize(eraserSize);
			}
		});
		menuBar.add(eraserSlider);


		return menuBar;
	}




	private void loadWhiteboard(){
		ManagementEvent event = new ManagementEvent(EventType.LOAD);
		try {
			event.user = user;
			managementService.loadBoard(event);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void writeWhiteboard(){
		ArrayList<DrawEvent> drawEvents = null;
		try {
			drawEvents = drawService.getDrawEvents(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		JFileChooser jfc = new JFileChooser();
		int retVal = jfc.showSaveDialog(this);
		if(retVal == JFileChooser.APPROVE_OPTION){

			try{
				FileOutputStream fStream = new FileOutputStream(jfc.getSelectedFile(), false);
				ObjectOutputStream output = new ObjectOutputStream(fStream);
				output.writeObject(drawEvents);
				output.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public void readWhiteboard(){

		ArrayList<DrawEvent> drawEvents = null;

		JFileChooser jfc = new JFileChooser();
		int retval = jfc.showOpenDialog(this);
		if(retval == JFileChooser.APPROVE_OPTION){
			File file = jfc.getSelectedFile();
			try {

				FileInputStream inStream = new FileInputStream(file);
				ObjectInputStream input = new ObjectInputStream(inStream);
				Object object = input.readObject();


				drawEvents = (ArrayList<DrawEvent>) object;
				drawService.loadEvents(drawEvents);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}



	private void saveWhiteboard(){
		ManagementEvent event = new ManagementEvent(EventType.SAVE);
		try {
			event.user = user;
			managementService.saveBoard(event);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	private void newWhiteboard(){
		ManagementEvent event = new ManagementEvent(EventType.NEW);
		try {
			event.user = user;
			managementService.newBoard(event);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
			case "Load":
				loadWhiteboard();
				break;
			case "Save":
				saveWhiteboard();
				break;
			case "New":
				newWhiteboard();
				break;
			case "Free":
				drawType = DrawType.FREE;
				mouse.setDrawType(DrawType.FREE);
				break;
			case "Text":
				drawType = DrawType.TEXT;
				mouse.setDrawType(DrawType.TEXT);
				keyBoard.setDrawType(DrawType.TEXT);
				break;
			case "Line":
				drawType = DrawType.LINE;
				mouse.setDrawType(DrawType.LINE);
				break;
			case "Rectangle":
				drawType = DrawType.RECT;
				mouse.setDrawType(DrawType.RECT);
				break;
			case "Square":
				drawType = DrawType.SQUARE;
				mouse.setDrawType(DrawType.SQUARE);
				break;
			case "Circle":
				drawType = DrawType.CIRCLE;
				mouse.setDrawType(DrawType.CIRCLE);
				break;
			case "Ellipse":
				drawType = DrawType.ELLIPSE;
				mouse.setDrawType(DrawType.ELLIPSE);
				break;
			case "Color":
				Color color = JColorChooser.showDialog(this, "Select Color", null);
				mouse.setColor(color);
				break;
			case "Erase":
				drawType = DrawType.ERASE;
				mouse.setDrawType(DrawType.ERASE);
				break;
		}
	}
}
