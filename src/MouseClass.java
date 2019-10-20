import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.*;


public class MouseClass extends JPanel implements MouseListener,
		MouseMotionListener {


	private JPanel canvas;
	protected UR ur;
	private Color penColor = new Color(0, 0, 0);
	private int pointX, pointY, pointR;
	protected ArrayList<Shape> shapes;
	protected ArrayList<Shape> currentShapes;
	protected ArrayList<Point> pointList;
	protected ArrayList<Freehand> curves;
	protected ArrayList<Freehand> currentCurves;
	private Ellipse ellipse;
	private Circle circle;
	private Rectangle rectangle;
	private Square square;
	private Triangle triangle;
	private Line line;
	private Freehand freehand;
	private Text text;
	private Eraser eraser;
	protected int flag;
	protected String message;
	protected int fontSize;
	private int startX, startY;
	protected Color color;
	protected boolean isFilled;
	private Shape selectedShape;
	private Item item;
	private String pos, helpp;
	private int help;
	
	public MouseClass() {

		pos = "";
		helpp = "";
		canvas = new JPanel();
		canvas.setBackground(Color.white);
		setVisible(true);
		canvas.setVisible(true);
		flag = 0;
		fontSize=20;
		message = "Default";
		color = Color.BLACK;
		shapes = new ArrayList<Shape>();
		currentShapes = new ArrayList<Shape>();
		pointList = new ArrayList<Point>();



		addMouseListener(this);
		addMouseMotionListener(this);
		ur = new UR();




	}
	//Mouse events on th Canvas
	//It calls for the methods in the class SHAPE and the figure's class
	//to set color of border and fill
	//through the event it catches the point where the click happens
	@Override
	public void mousePressed(MouseEvent ev) {

		try {
			startX = ev.getX();
			startY = ev.getY();
			if (flag == 5 || flag == 6) {
				select(ev);
			} else {
				if (flag == 1) {
					ellipse = new Ellipse();
					ellipse.setColor(color);
					ellipse.setName("ellipse");
					ellipse.setFilled(isFilled);
					ellipse.setEX(ev.getX());
					ellipse.setEY(ev.getY());
				} else if (flag == 2) {
					rectangle = new Rectangle();
					rectangle.setColor(color);
					rectangle.setName("rectangle");
					rectangle.setFilled(isFilled);
					rectangle.setRectangleX(ev.getX());
					rectangle.setRectangleY(ev.getY());
				} else if (flag == 3) {
					triangle = new Triangle();
					triangle.setColor(color);
					triangle.setName("triangle");
					triangle.setFilled(isFilled);
					triangle.setTriangleX(ev.getX());
					triangle.setTriangleY(ev.getY());
				} else if (flag == 4) {
					line = new Line();
					line.setColor(color);
					line.setName("line");
					line.setX1(ev.getX());
					line.setY1(ev.getY());
				} else if (flag == 7) {
					circle = new Circle();
					circle.setColor(color);
					circle.setName("circle");
					circle.setFilled(isFilled);
					circle.setX(ev.getX());
					circle.setY(ev.getY());
				} else if (flag == 8) {
					square = new Square();
					square.setColor(color);
					square.setName("square");
					square.setFilled(isFilled);
					square.setSquareX(ev.getX());
					square.setSquareY(ev.getY());

				} else if (flag == 11) {
					freehand = new Freehand();
					freehand.setColor(color);
					freehand.setName("freehand");
					pointX = ev.getX() ;   // the clicked point is stored without the offset
					pointY = ev.getY() ;   // the clicked point is stored without the offset
					freehand.setFY(pointY);
					freehand.setFX(pointX);
					pointList.add(new Point(pointX, pointY));
					freehand.setPoints(pointList);  // the clicked point is added to a list

				} else if (flag == 12) {
					text = new Text();
					text.setColor(color);
					text.setName("Text");
					text.setFilled(isFilled);
					text.setText(message);
					text.setFSize(fontSize);
					text.setPosx(ev.getX());
					text.setPosy(ev.getY());

				}  else if (flag == 13) {
					eraser = new Eraser();
					eraser.setColor(Color.white);
					eraser.setName("Eraser");
					pointX = ev.getX() ;   // the clicked point is stored without the offset
					pointY = ev.getY() ;   // the clicked point is stored without the offset
					eraser.setFY(pointY);
					eraser.setEraserX(pointX);
					pointList.add(new Point(pointX, pointY));
					eraser.setPoints(pointList);  // the clicked point is added to a list

				}
			}
		} catch (Exception exception) {

		}
	}
	// EVENT WHEN MOUSE IS DRAGGED
	// The figure classes get their parameters from this
	//
	@Override
	public void mouseDragged(MouseEvent e) {
		try {


			if (e.getX() > 1100 || e.getY() >= 830) {

			} else {
				if (flag == 6) {
					move(e);
				} else if (flag == 5) {
					resize(e);
				}
				else {
					if (flag == 11) {
						pointX = e.getX(); //the dragging the mouse does the same thing as clicking, it adds points
						pointY = e.getY();
						pointList.add(new Point(pointX, pointY));
						freehand.setPoints(pointList);

						shapes.add(freehand);
					} else if (flag == 13) {
						pointX = e.getX(); //the dragging the mouse does the same thing as clicking, it adds points
						pointY = e.getY();
						pointList.add(new Point(pointX, pointY));
						eraser.setPoints(pointList);

						shapes.add(eraser);
					}
					else if (flag == 12) {
						text.setPosx(e.getX());
						text.setPosy(e.getY());

						shapes.add(text);
					}
					else if (flag == 1) {
						ellipse.setEX(Math.min(e.getX(), startX));
						ellipse.setEY(Math.min(e.getY(), startY));
						ellipse.setEWidth(Math.abs(e.getX() - startX));
						ellipse.setEHight(Math.abs(e.getY() - startY));

						shapes.add(ellipse);
					} else if (flag == 2) {
						rectangle.setRectangleX(Math.min(e.getX(), startX));
						rectangle.setRectangleY(Math.min(e.getY(), startY));
						rectangle.setRectangleWidth(Math.abs(e.getX() - startX));
						rectangle.setRectangleHight(Math.abs(e.getY() - startY));

						shapes.add(rectangle);
					} else if (flag == 3) {
						int base = e.getX() - triangle.getTriangleX();
						int hight = e.getY() - triangle.getTriangleY();
						int[] xPoints = { (triangle.getTriangleX() + base / 2), triangle.getTriangleX(),
								e.getX() };
						int[] yPoints = { triangle.getTriangleY(), (triangle.getTriangleY() + hight),
								e.getY() };
						triangle.setXPoints(xPoints);
						triangle.setYPoints(yPoints);
						shapes.add(triangle);
					} else if (flag == 4) {
						line.setX2(e.getX());
						line.setY2(e.getY());
						shapes.add(line);
					} else if (flag == 7) {
						circle.setX(Math.min(e.getX(), startX));
						circle.setRadius(Math.abs(e.getX() - startX));
						shapes.add(circle);

					} else if (flag == 8) {
						square.setSquareX(Math.min(e.getX(), startX));

						square.setSquareL(Math.abs(e.getX() - startX));

						shapes.add(square);

					}

					repaint();
				}
			}
		} catch (Exception exception) {

		}
	}


	private void pushItem() {
		try{
			item.setI(currentShapes.size() - 1);

			item.setPositions(item.getPositions() + pos);

			ur.undo.push(item);
		}catch (Exception exception) {
			// TODO: handle exception
		}
	}



	//To release the mouse means to save the parameters of the figure and save them into the current shapes array
	@Override
	public void mouseReleased(MouseEvent e) {
		try{


			selectedShape = null;
			item = new Item();
			if (flag != 5 && flag != 6) {
				if (flag == 1) {
					currentShapes.add(ellipse);

					pos = ellipse.getEX() + "," + ellipse.getEY() + "," + ellipse.getEWidth() + ","
							+ ellipse.getEHight() + "," + ellipse.getColor().getRGB() + ","
							+ ellipse.isFilled();
					item.setName("ellipse");
					pushItem();

				} else if (flag == 2) {
					currentShapes.add(rectangle);
					pos = rectangle.getRectangleX() + "," + rectangle.getRectangleY() + "," + rectangle.getRectangleWidth() + ","
							+ rectangle.getRectangleHight() + "," + rectangle.getColor().getRGB() + ","
							+ rectangle.isFilled();
					item.setName("rectangle");
					pushItem();

				} else if (flag == 3) {
					currentShapes.add(triangle);
					String x = triangle.getXPoints()[0] + "," + triangle.getXPoints()[1] + ","
							+ triangle.getXPoints()[2];
					String y = triangle.getYPoints()[0] + "," + triangle.getYPoints()[1] + ","
							+ triangle.getYPoints()[2];
					pos = triangle.getTriangleX() + "," + triangle.getTriangleY() + "," + x + "," + y + ","
							+ triangle.getColor().getRGB() + "," + triangle.isFilled();
					item.setName("triangle");
					pushItem();

				} else if (flag == 4) {
					currentShapes.add(line);
					pos = line.getX1() + "," + line.getY1() + "," + line.getX2() + ","
							+ line.getY2() + "," + line.getColor().getRGB() + ","
							+ line.isFilled();

					item.setName("line");
					pushItem();

				} else if (flag == 7) {
					currentShapes.add(circle);
					pos = circle.getX() + "," + circle.getY() + "," + circle.getRadius()
							+ "," + circle.getRadius() + "," + circle.getColor().getRGB()
							+ "," + circle.isFilled();

					item.setName("circle");
					pushItem();

				} else if (flag == 8) {
					currentShapes.add(square);
					pos = square.getSquareX() + "," + square.getSquareY() + "," + square.getSquareL() + ","
							+ square.getSquareL() + "," + square.getColor().getRGB() + ","
							+ square.isFilled();
					item.setName("square");
					pushItem();

				} else if (flag == 11) {
					freehand.setLX(e.getX());
					freehand.setLY(e.getY());
					currentShapes.add(freehand);
					pos = freehand.getFX() + "," + freehand.getFY() + "," + freehand.getLX() + ","
							+ freehand.getLY() + "," + freehand.getColor().getRGB() + ","
							+ true;
					item.setName("freehand");
					pushItem();
					pointList = new ArrayList<Point>();

				} else if (flag == 13) {
					eraser.setLX(e.getX());
					eraser.setLY(e.getY());
					currentShapes.add(eraser);
					pos = eraser.getEX() + "," + eraser.getFY() + "," + eraser.getLX() + ","
							+ eraser.getLY() + "," + eraser.getColor().getRGB() + ","
							+ true;
					item.setName("Eraser");
					pushItem();
					pointList = new ArrayList<Point>();
				}
				else if (flag == 12) {
					currentShapes.add(text);
					pos = text.getPosx() + "," + text.getPosy() + "," + text.getWidth() + ","
							+ text.getHight() + "," + text.getColor().getRGB() + ","
							+ true;
					item.setName("Text");
					pushItem();

				}

				if (flag != 9) {
					ur.lastAction.add(currentShapes.size() - 1);
					ur.lastAction.add(currentCurves.size() - 1);
				}
			} else {
				if (flag != 9 && flag != 10) {

					ur.lastAction.add(help);
					ur.updatselected(help,
							updateshape(helpp, help, pos, currentShapes), ur.undo);
				}
			}
		}catch (Exception exception) {
			// TODO: handle exception
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try{
			if (flag == 9) {
				select(e);
				if (selectedShape != null) {
					selectedShape.setColor(color);

					ur.lastAction.add(help);
					selectedShape.setFilled(isFilled);
					ur.updatselected(help,
							updateshape(helpp, help, pos, currentShapes), ur.undo);

				}
			} else if (flag == 10) {
				select(e);
				delete();

				while (ur.lastAction.contains(help) == true) {
					for (int i = 0; i < ur.lastAction.size(); i++) {
						if (ur.lastAction.get(i) == help)
							ur.lastAction.remove(i);
					}
				}
				for (int i = 0; i < ur.lastAction.size(); i++) {
					if (ur.lastAction.get(i) > help) {
						ur.lastAction.set(i, ur.lastAction.get(i) - 1);
					}
				}
				try {
					ur.undo.remove(help);
					ur.updastackAfterDel(ur.undo, ur.redo, help);

					ur.redo.remove(help);
				} catch (Exception exception) {

				}
			}
			repaint();
		}catch (Exception exception) {
			// TODO: handle exception
		}
	}




	private String updateshape(String helpp, int help, String pos,
							   ArrayList<Shape> currentShapes) {

		if (helpp == "circle") {
			pos = "" + ((Circle) currentShapes.get(help)).getX() + ","
					+ ((Circle) currentShapes.get(help)).getY() + ","
					+ ((Circle) currentShapes.get(help)).getRadius() + ","
					+ ((Circle) currentShapes.get(help)).getRadius() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "ellipse") {
			pos = "" + ((Ellipse) currentShapes.get(help)).getEX() + ","
					+ ((Ellipse) currentShapes.get(help)).getEY() + ","
					+ ((Ellipse) currentShapes.get(help)).getEWidth() + ","
					+ ((Ellipse) currentShapes.get(help)).getEHight() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "square") {
			pos = "" + ((Square) currentShapes.get(help)).getSquareX() + ","
					+ ((Square) currentShapes.get(help)).getSquareY() + ","
					+ ((Square) currentShapes.get(help)).getSquareL() + ","
					+ ((Square) currentShapes.get(help)).getSquareL() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "triangle") {
			String x = ((Triangle) currentShapes.get(help)).getXPoints()[0]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getXPoints()[1]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getXPoints()[2];
			String y = ((Triangle) currentShapes.get(help)).getYPoints()[0]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getYPoints()[1]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getYPoints()[2];
			pos = "" + ((Triangle) currentShapes.get(help)).getTriangleX() + ","
					+ ((Triangle) currentShapes.get(help)).getTriangleY() + "," + x
					+ "," + y + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "line") {
			pos = "" + ((Line) currentShapes.get(help)).getX1() + ","
					+ ((Line) currentShapes.get(help)).getY1() + ","
					+ ((Line) currentShapes.get(help)).getX2() + ","
					+ ((Line) currentShapes.get(help)).getY2() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "rectangle") {
			pos = "" + ((Rectangle) currentShapes.get(help)).getRectangleX() + ","
					+ ((Rectangle) currentShapes.get(help)).getRectangleY() + ","
					+ ((Rectangle) currentShapes.get(help)).getRectangleWidth() + ","
					+ ((Rectangle) currentShapes.get(help)).getRectangleHight() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "Text") {
			pos = "" + ((Text) currentShapes.get(help)).getPosx() + ","
					+ ((Text) currentShapes.get(help)).getPosy() + ","
					+ ((Text) currentShapes.get(help)).getWidth() + ","
					+ ((Text) currentShapes.get(help)).getHight() + ","
					+ currentShapes.get(help).getColor().getRGB();
		}
		pos += "," + currentShapes.get(help).isFilled();
		return pos;
	}

	private void delete() {
		try{
			if (selectedShape != null) {
				for (int i = shapes.size() - 1; i >= 0; i--) {
					if (shapes.get(i).toString().equals(selectedShape.toString())) {
						shapes.remove(i);
					}
				}
				for (int i = 0; i < currentShapes.size(); i++) {
					if (currentShapes.get(i).toString()
							.equals(selectedShape.toString()))
						currentShapes.remove(i);
				}
			}
		}catch (Exception exception) {
			// TODO: handle exception
		}
	}






	public void select(MouseEvent e) {
		for (int i = currentShapes.size() - 1; i >= 0; i--) {
			if (currentShapes.get(i).getName().equals("circle")) {
				circle = new Circle();
				if (circle.select(e, currentShapes, i) != null) {
					selectedShape = circle.select(e, currentShapes, i);
					help = i;
					helpp = "circle";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("ellipse")) {
				ellipse = new Ellipse();
				if (ellipse.select(e, currentShapes, i) != null) {
					selectedShape = ellipse.select(e, currentShapes, i);
					help = i;
					helpp = "ellipse";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("square")) {
				square = new Square();
				if (square.select(e, currentShapes, i) != null) {
					selectedShape = square.select(e, currentShapes, i);
					help = i;
					helpp = "square";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("rectangle")) {
				rectangle = new Rectangle();
				if (rectangle.select(e, currentShapes, i) != null) {
					selectedShape = rectangle.select(e, currentShapes, i);
					help = i;
					helpp = "rectangle";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("triangle")) {
				triangle = new Triangle();
				if (triangle.select(e, currentShapes, i) != null) {
					selectedShape = triangle.select(e, currentShapes, i);
					help = i;
					helpp = "triangle";

					break;
				}
			} else if (currentShapes.get(i).getName().equals("line")) {
				line = new Line();
				if (line.select(e, currentShapes, i) != null) {
					selectedShape = line.select(e, currentShapes, i);
					help = i;
					helpp = "line";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("freehand")) {
				freehand = new Freehand();
				if (freehand.select(e, currentShapes, i) != null) {
					selectedShape = freehand.select(e, currentShapes, i);
					help = i;
					helpp = "freehand";
					break;
				}
			}
			else if (currentShapes.get(i).getName().equals("Text")) {
				text = new Text();
				if (text.select(e, currentShapes, i) != null) {
					selectedShape = text.select(e, currentShapes, i);
					help = i;
					helpp = "Text";
					break;
				}
			}
		}
	}

	private void move(MouseEvent e) {
		if (selectedShape != null) {
			if (selectedShape.getName().equals("circle")) {
				circle.move(e, selectedShape);
			} else if (selectedShape.getName().equals("square")) {
				square.move(e, selectedShape);
			} else if (selectedShape.getName().equals("ellipse")) {
				ellipse.move(e, selectedShape);
			} else if (selectedShape.getName().equals("rectangle")) {
				rectangle.move(e, selectedShape);
			} else if (selectedShape.getName().equals("triangle")) {
				triangle.move(e, selectedShape);
			} else if (selectedShape.getName().equals("line")) {
				line.move(e, selectedShape);
			} else if (selectedShape.getName().equals("freehand")) {
				freehand.move(e, selectedShape);
			}  else if (selectedShape.getName().equals("Text")) {
				text.move(e, selectedShape);
			}
			repaint();
		}
	}

	public void resize(MouseEvent e) {
		if (selectedShape != null) {
			if (selectedShape.getName().equals("circle")) {
				circle.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("square")) {
				square.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("ellipse")) {
				ellipse.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("rectangle")) {
				rectangle.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("triangle")) {
				triangle.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("line")) {
				line.resize(e, selectedShape);
			} /*else if (selectedShape.getName().equals("Text")) {
				t.resize(e, selectedShape);
			} */
			repaint();
		}
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponents(graphics);
		Graphics2D g =(Graphics2D)graphics;
		g.setStroke(new BasicStroke(10));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1100, 830);
		int i = 0;
		
		for (i = 0; i < shapes.size(); i++) {
			g.setStroke(new BasicStroke(10));
			Point point = new Point(0,0);
			
			if (shapes.get(i).isDraw()) {


				if (shapes.get(i).getName().equals("freehand")) {
					int x2 = 0;
					int y2 = 0;
					g.setColor(shapes.get(i).getColor());
					
					for (int j = 0; j < ((Freehand)shapes.get(i)).getPoints().size(); j++) {
						point = ((Freehand)shapes.get(i)).getPoints().get(j);
						int x1 =point.getX();
						int y1 =point.getY();
						
						point.drawFreehandLine(g, x1, y1, x2, y2);
						
						x2=x1;
						y2=y1;
						

					}
					
					
				} else if (shapes.get(i).getName().equals("Eraser")) {
					int x2 = 0;
					int y2 = 0;
					for (int j = 0; j < ((Eraser)shapes.get(i)).getPoints().size(); j++) {
						point = ((Eraser)shapes.get(i)).getPoints().get(j);
						int x1 =point.getX();
						int y1 =point.getY();
						
						point.drawEraserLine(g, x1, y1, x2, y2);
						
						x2=x1;
						y2=y1;
						
					}
					
				}
				else if (shapes.get(i).getName().equals("Text")) {
					Font font;
					FontRenderContext context;

					g.setFont(new Font("TimesRoman", Font.PLAIN, ((Text) shapes.get(i)).getFSize()));

					g.setColor(shapes.get(i).getColor());
					g.drawString(((Text) shapes.get(i)).getText(),
							((Text) shapes.get(i)).getPosx(), ((Text) shapes.get(i)).getPosy());



					context = g.getFontRenderContext();
					Rectangle2D bounds = g.getFont().getStringBounds(((Text) shapes.get(i)).getText(), context);
					if (text != null) {
						text.setHight((int)bounds.getHeight());
						text.setWidth(g.getFontMetrics().stringWidth(text.getText()));
					}


				}

				else if (shapes.get(i).getName().equals("circle")) {

					g.setColor(shapes.get(i).getColor());
					if (shapes.get(i).isFilled())
						g.fillOval(((Circle) shapes.get(i)).getX(),
								((Circle) shapes.get(i)).getY(),
								((Circle) shapes.get(i)).getRadius(),
								((Circle) shapes.get(i)).getRadius());

					else
						g.drawOval(((Circle) shapes.get(i)).getX(),
								((Circle) shapes.get(i)).getY(),
								((Circle) shapes.get(i)).getRadius(),
								((Circle) shapes.get(i)).getRadius());

					// cir.draw(g, shapes, i);
				} else if (shapes.get(i).getName().equals("ellipse")) {

					g.setColor(shapes.get(i).getColor());
					if (shapes.get(i).isFilled())
						g.fillOval(((Ellipse) shapes.get(i)).getEX(),
								((Ellipse) shapes.get(i)).getEY(),
								((Ellipse) shapes.get(i)).getEWidth(),
								((Ellipse) shapes.get(i)).getEHight());

					else
						g.drawOval(((Ellipse) shapes.get(i)).getEX(),
								((Ellipse) shapes.get(i)).getEY(),
								((Ellipse) shapes.get(i)).getEWidth(),
								((Ellipse) shapes.get(i)).getEHight());
					// c.draw(g, shapes, i);
				} else if (shapes.get(i).getName().equals("square")) {
					g.setColor(shapes.get(i).getColor());
					if (shapes.get(i).isFilled())
						g.fillRect(((Square) shapes.get(i)).getSquareX(),
								((Square) shapes.get(i)).getSquareY(),
								((Square) shapes.get(i)).getSquareL(),
								((Square) shapes.get(i)).getSquareL());
					else
						g.drawRect(((Square) shapes.get(i)).getSquareX(),
								((Square) shapes.get(i)).getSquareY(),
								((Square) shapes.get(i)).getSquareL(),
								((Square) shapes.get(i)).getSquareL());
					// sq.draw(g, shapes, i);
				} else if (shapes.get(i).getName().equals("rectangle")) {
					g.setColor(shapes.get(i).getColor());
					if (shapes.get(i).isFilled())
						g.fillRect(((Rectangle) shapes.get(i)).getRectangleX(),
								((Rectangle) shapes.get(i)).getRectangleY(),
								((Rectangle) shapes.get(i)).getRectangleWidth(),
								((Rectangle) shapes.get(i)).getRectangleHight());
					else
						g.drawRect(((Rectangle) shapes.get(i)).getRectangleX(),
								((Rectangle) shapes.get(i)).getRectangleY(),
								((Rectangle) shapes.get(i)).getRectangleWidth(),
								((Rectangle) shapes.get(i)).getRectangleHight());
					// r.draw(g, shapes, i);
				} else if (shapes.get(i).getName().equals("triangle")) {
					// t.draw(g, shapes, i);
					g.setColor(shapes.get(i).getColor());
					if (shapes.get(i).isFilled())
						g.fillPolygon(((Triangle) shapes.get(i)).getXPoints(),
								((Triangle) shapes.get(i)).getYPoints(), 3);
					else
						g.drawPolygon(((Triangle) shapes.get(i)).getXPoints(),
								((Triangle) shapes.get(i)).getYPoints(), 3);

				} else if (shapes.get(i).getName().equals("line")) {
					// l.draw(g, shapes, i);
					g.setColor(shapes.get(i).getColor());
					g.drawLine(((Line) shapes.get(i)).getX1(),
							((Line) shapes.get(i)).getY1(),
							((Line) shapes.get(i)).getX2(),
							((Line) shapes.get(i)).getY2());

				}
			}
		}
		g.finalize();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
