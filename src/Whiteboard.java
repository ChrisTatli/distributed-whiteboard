import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;



@SuppressWarnings("serial")
public class Whiteboard extends JPanel implements ActionListener  {
	final JFrame frame;
	private ArrayList<Shape> shapes = new ArrayList<>();
	private DrawType drawType = DrawType.FREE;


	public Whiteboard(JFrame frame) {
		this.frame = frame;
		BuildMenu();
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	@Override
	public synchronized void paintComponent(Graphics graphics){
		for (Shape shape : shapes) {
			shape.draw(graphics);
		}
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

		JRadioButtonMenuItem ovalMenuItem = new JRadioButtonMenuItem("Oval");
		ovalMenuItem.setActionCommand("Oval");
		ovalMenuItem.addActionListener(this);
		group.add(ovalMenuItem);
		shapeMenu.add(ovalMenuItem);

		JRadioButtonMenuItem ellipseMenuItem = new JRadioButtonMenuItem("Ellipse");
		ellipseMenuItem.setActionCommand("Ellipse");
		ellipseMenuItem.addActionListener(this);
		group.add(ellipseMenuItem);
		shapeMenu.add(ellipseMenuItem);

		menuBar.add(shapeMenu);

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
				break;
			case "Line":
				drawType = DrawType.LINE;
				break;
			case "Rectangle":
				drawType = DrawType.RECT;
				break;
			case "Square":
				drawType = DrawType.SQUARE;
				break;
			case "Circle":
				drawType = DrawType.CIRCLE;
				break;
			case "Oval":
				drawType = DrawType.OVAL;
				break;
			case "Ellipse":
				drawType = DrawType.ELLIPSE;
				break;

		}
	}
}
