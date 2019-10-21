import Enums.DrawType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


@SuppressWarnings("serial")
public class Whiteboard extends JPanel implements ActionListener  {
	final JFrame frame;
	private Mouse mouse;
	public DrawEventHandler handler;

	private ArrayList<Shape> shapes = new ArrayList<>();
	private DrawType drawType = DrawType.FREE;
	private Color color = Color.BLACK;
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
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	@Override
	public synchronized void paintComponent(Graphics graphics){
		for (Shape shape : shapes) {
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

		menuBar.add(shapeMenu);

		//Colors
		JButton colorMenu = new JButton("Color");
		colorMenu.setActionCommand("Color");
		colorMenu.addActionListener(this);

		menuBar.add(colorMenu);

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

		}
	}
}
