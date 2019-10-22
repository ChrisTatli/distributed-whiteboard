import Enums.DrawType;
import Shapes.Shape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class Whiteboard extends JPanel implements ActionListener {
	final JFrame frame;
	private Mouse mouse;
	private KeyBoard keyBoard;
	public DrawEventHandler handler;

	private ArrayList<Shapes.Shape> shapes = new ArrayList<>();
	private DrawType drawType = DrawType.FREE;
	private Color color;
	public ArrayList<DrawEvent> drawEvents;
	private int eventNumber;

	public synchronized ArrayList getDrawEvents(int from){
		return new ArrayList(drawEvents.subList(from, drawEvents.size()));
	}

	public synchronized void addDrawEvent(DrawEvent event){
		event.Id = eventNumber++;
		drawEvents.add(event);
	}


	public Whiteboard(JFrame frame) {
		this.frame = frame;
		BuildMenu();
		eventNumber = 0;
		drawEvents = new ArrayList<>();
		mouse = new Mouse(drawType, this);
		keyBoard = new KeyBoard(drawType, this);
		addKeyListener(keyBoard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	@Override
	public synchronized void paintComponent(Graphics graphics){
		for (Shapes.Shape shape : shapes) {
			shape.draw(graphics);
		}
	}

	public synchronized  void addShape(Shape shape){
	    shapes.add(shape);
    }

	private void BuildMenu(){
		JMenuBar menuBar = new JMenuBar();

		//FILE MENU
		JMenu fileMenu = new JMenu("File");

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


		//SHAPE MENU
		JMenu shapeMenu = new JMenu("Shapes");
		ButtonGroup group = new ButtonGroup();

		JRadioButtonMenuItem freeHandMenuItem = new JRadioButtonMenuItem("Free Hand");
		freeHandMenuItem.setActionCommand("Free");
		freeHandMenuItem.addActionListener(this);
		freeHandMenuItem.setSelected(true);
		group.add(freeHandMenuItem);
		shapeMenu.add(freeHandMenuItem);

		JRadioButtonMenuItem textMenuItem = new JRadioButtonMenuItem("Text");
		textMenuItem.setActionCommand("SText");
		textMenuItem.addActionListener(this);
		group.add(textMenuItem);
		shapeMenu.add(textMenuItem);

		JRadioButtonMenuItem lineMenuItem = new JRadioButtonMenuItem("Line");
		lineMenuItem.setActionCommand("Line");
		lineMenuItem.addActionListener(this);
		group.add(lineMenuItem);
		shapeMenu.add(lineMenuItem);

		JRadioButtonMenuItem rectMenuItem = new JRadioButtonMenuItem("Rectangle");
		rectMenuItem.setActionCommand("Rectangle");
		rectMenuItem.addActionListener(this);
		group.add(rectMenuItem);
		shapeMenu.add(rectMenuItem);

		JRadioButtonMenuItem squareMenuItem = new JRadioButtonMenuItem("Square");
		squareMenuItem.setActionCommand("Square");
		squareMenuItem.addActionListener(this);
		group.add(squareMenuItem);
		shapeMenu.add(squareMenuItem);

		JRadioButtonMenuItem circleMenuItem = new JRadioButtonMenuItem("Circle");
		circleMenuItem.setActionCommand("Circle");
		circleMenuItem.addActionListener(this);
		group.add(circleMenuItem);
		shapeMenu.add(circleMenuItem);


		JRadioButtonMenuItem ellipseMenuItem = new JRadioButtonMenuItem("Ellipse");
		ellipseMenuItem.setActionCommand("Ellipse");
		ellipseMenuItem.addActionListener(this);
		group.add(ellipseMenuItem);
		shapeMenu.add(ellipseMenuItem);

		JRadioButtonMenuItem eraseMenuItem = new JRadioButtonMenuItem("Erase");
		eraseMenuItem.setActionCommand("Erase");
		eraseMenuItem.addActionListener(this);
		group.add(eraseMenuItem);
		shapeMenu.add(eraseMenuItem);

		menuBar.add(shapeMenu);

		//Colors
		JButton colorMenu = new JButton("Color");
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





		frame.setJMenuBar(menuBar);
	}


	private void loadWhiteboard(){

	}

	private void saveWhiteboard(){

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
				color = JColorChooser.showDialog(this, "Select Color", color);
				mouse.setColor(color);
				break;
			case "Erase":
				drawType = DrawType.ERASE;
				mouse.setDrawType(DrawType.ERASE);
				break;
		}
	}
}
