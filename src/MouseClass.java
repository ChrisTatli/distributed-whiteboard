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
	private Ellipse c;
	private Circle cir;
	private Rectangle r;
	private Square sq;
	private Triangle t;
	private Line l;
	private Freehand fh;
	private Text tx;
	private Eraser er;
	protected int flag;
	protected String message;
	protected int fz;
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
		fz=20;
		message = "Default";
		color = Color.BLACK;
		shapes = new ArrayList<Shape>();
		currentShapes = new ArrayList<Shape>();
		pointList = new ArrayList<Point>();



		addMouseListener(this);
		addMouseMotionListener(this);
		ur = new UR();




	}
	//*************************************************   MOUSE EVENTS ON THE CANVAS *************************************
	//MOUSE PRESSED ON CANVAS
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
					c = new Ellipse();
					c.setColor(color);
					c.setName("ellipse");
					c.setFilled(isFilled);
					c.setEX(ev.getX());
					c.setEY(ev.getY());
				} else if (flag == 2) {
					r = new Rectangle();
					r.setColor(color);
					r.setName("rectangle");
					r.setFilled(isFilled);
					r.setRectangleX(ev.getX());
					r.setRectangleY(ev.getY());
				} else if (flag == 3) {
					t = new Triangle();
					t.setColor(color);
					t.setName("triangle");
					t.setFilled(isFilled);
					t.setTriangleX(ev.getX());
					t.setTriangleY(ev.getY());
				} else if (flag == 4) {
					l = new Line();
					l.setColor(color);
					l.setName("line");
					l.setX1(ev.getX());
					l.setY1(ev.getY());
				} else if (flag == 7) {
					cir = new Circle();
					cir.setColor(color);
					cir.setName("circle");
					cir.setFilled(isFilled);
					cir.setX(ev.getX());
					cir.setY(ev.getY());
				} else if (flag == 8) {
					sq = new Square();
					sq.setColor(color);
					sq.setName("square");
					sq.setFilled(isFilled);
					sq.setSquareX(ev.getX());
					sq.setSquareY(ev.getY());

				} else if (flag == 11) {
					fh = new Freehand();
					fh.setColor(color);
					fh.setName("freehand");
					pointX = ev.getX() ;   // the clicked point is stored without the offset
					pointY = ev.getY() ;   // the clicked point is stored without the offset
					fh.setFY(pointY);
					fh.setFX(pointX);
					pointList.add(new Point(pointX, pointY));
					fh.setPoints(pointList);  // the clicked point is added to a list

				} else if (flag == 12) {
					tx = new Text();
					tx.setColor(color);
					tx.setName("Text");
					tx.setFilled(isFilled);
					tx.setText(message);
					tx.setFSize(fz);
					tx.setPosx(ev.getX());
					tx.setPosy(ev.getY());

				}  else if (flag == 13) {
					er = new Eraser();
					er.setColor(Color.white);
					er.setName("Eraser");
					pointX = ev.getX() ;   // the clicked point is stored without the offset
					pointY = ev.getY() ;   // the clicked point is stored without the offset
					er.setFY(pointY);
					er.setEraserX(pointX);
					pointList.add(new Point(pointX, pointY));
					er.setPoints(pointList);  // the clicked point is added to a list

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
						fh.setPoints(pointList);

						shapes.add(fh);
					} else if (flag == 13) {
						pointX = e.getX(); //the dragging the mouse does the same thing as clicking, it adds points
						pointY = e.getY();
						pointList.add(new Point(pointX, pointY));
						er.setPoints(pointList);

						shapes.add(er);
					}
					else if (flag == 12) {
						tx.setPosx(e.getX());
						tx.setPosy(e.getY());

						shapes.add(tx);
					}
					else if (flag == 1) {
						c.setEX(Math.min(e.getX(), startX));
						c.setEY(Math.min(e.getY(), startY));
						c.setEWidth(Math.abs(e.getX() - startX));
						c.setEHight(Math.abs(e.getY() - startY));

						shapes.add(c);
					} else if (flag == 2) {
						r.setRectangleX(Math.min(e.getX(), startX));
						r.setRectangleY(Math.min(e.getY(), startY));
						r.setRectangleWidth(Math.abs(e.getX() - startX));
						r.setRectangleHight(Math.abs(e.getY() - startY));

						shapes.add(r);
					} else if (flag == 3) {
						int base = e.getX() - t.getTriangleX();
						int hight = e.getY() - t.getTriangleY();
						int[] xPoints = { (t.getTriangleX() + base / 2), t.getTriangleX(),
								e.getX() };
						int[] yPoints = { t.getTriangleY(), (t.getTriangleY() + hight),
								e.getY() };
						t.setXPoints(xPoints);
						t.setYPoints(yPoints);
						shapes.add(t);
					} else if (flag == 4) {
						l.setX2(e.getX());
						l.setY2(e.getY());
						shapes.add(l);
					} else if (flag == 7) {
						cir.setX(Math.min(e.getX(), startX));
						cir.setRadius(Math.abs(e.getX() - startX));
						shapes.add(cir);

					} else if (flag == 8) {
						sq.setSquareX(Math.min(e.getX(), startX));

						sq.setSquareL(Math.abs(e.getX() - startX));

						shapes.add(sq);

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
					currentShapes.add(c);

					pos = c.getEX() + "," + c.getEY() + "," + c.getEWidth() + ","
							+ c.getEHight() + "," + c.getColor().getRGB() + ","
							+ c.isFilled();
					item.setName("ellipse");
					pushItem();

				} else if (flag == 2) {
					currentShapes.add(r);
					pos = r.getRectangleX() + "," + r.getRectangleY() + "," + r.getRectangleWidth() + ","
							+ r.getRectangleHight() + "," + r.getColor().getRGB() + ","
							+ r.isFilled();
					item.setName("rectangle");
					pushItem();

				} else if (flag == 3) {
					currentShapes.add(t);
					String x = t.getXPoints()[0] + "," + t.getXPoints()[1] + ","
							+ t.getXPoints()[2];
					String y = t.getYPoints()[0] + "," + t.getYPoints()[1] + ","
							+ t.getYPoints()[2];
					pos = t.getTriangleX() + "," + t.getTriangleY() + "," + x + "," + y + ","
							+ t.getColor().getRGB() + "," + t.isFilled();
					item.setName("triangle");
					pushItem();

				} else if (flag == 4) {
					currentShapes.add(l);
					pos = l.getX1() + "," + l.getY1() + "," + l.getX2() + ","
							+ l.getY2() + "," + l.getColor().getRGB() + ","
							+ l.isFilled();

					item.setName("line");
					pushItem();

				} else if (flag == 7) {
					currentShapes.add(cir);
					pos = cir.getX() + "," + cir.getY() + "," + cir.getRadius()
							+ "," + cir.getRadius() + "," + cir.getColor().getRGB()
							+ "," + cir.isFilled();

					item.setName("circle");
					pushItem();

				} else if (flag == 8) {
					currentShapes.add(sq);
					pos = sq.getSquareX() + "," + sq.getSquareY() + "," + sq.getSquareL() + ","
							+ sq.getSquareL() + "," + sq.getColor().getRGB() + ","
							+ sq.isFilled();
					item.setName("square");
					pushItem();

				} else if (flag == 11) {
					fh.setLX(e.getX());
					fh.setLY(e.getY());
					currentShapes.add(fh);
					pos = fh.getFX() + "," + fh.getFY() + "," + fh.getLX() + ","
							+ fh.getLY() + "," + fh.getColor().getRGB() + ","
							+ true;
					item.setName("freehand");
					pushItem();
					pointList = new ArrayList<Point>();

				} else if (flag == 13) {
					er.setLX(e.getX());
					er.setLY(e.getY());
					currentShapes.add(er);
					pos = er.getEX() + "," + er.getFY() + "," + er.getLX() + ","
							+ er.getLY() + "," + er.getColor().getRGB() + ","
							+ true;
					item.setName("Eraser");
					pushItem();
					pointList = new ArrayList<Point>();
				}
				else if (flag == 12) {
					currentShapes.add(tx);
					pos = tx.getPosx() + "," + tx.getPosy() + "," + tx.getWidth() + ","
							+ tx.getHight() + "," + tx.getColor().getRGB() + ","
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
				cir = new Circle();
				if (cir.select(e, currentShapes, i) != null) {
					selectedShape = cir.select(e, currentShapes, i);
					help = i;
					helpp = "circle";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("ellipse")) {
				c = new Ellipse();
				if (c.select(e, currentShapes, i) != null) {
					selectedShape = c.select(e, currentShapes, i);
					help = i;
					helpp = "ellipse";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("square")) {
				sq = new Square();
				if (sq.select(e, currentShapes, i) != null) {
					selectedShape = sq.select(e, currentShapes, i);
					help = i;
					helpp = "square";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("rectangle")) {
				r = new Rectangle();
				if (r.select(e, currentShapes, i) != null) {
					selectedShape = r.select(e, currentShapes, i);
					help = i;
					helpp = "rectangle";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("triangle")) {
				t = new Triangle();
				if (t.select(e, currentShapes, i) != null) {
					selectedShape = t.select(e, currentShapes, i);
					help = i;
					helpp = "triangle";

					break;
				}
			} else if (currentShapes.get(i).getName().equals("line")) {
				l = new Line();
				if (l.select(e, currentShapes, i) != null) {
					selectedShape = l.select(e, currentShapes, i);
					help = i;
					helpp = "line";
					break;
				}
			} else if (currentShapes.get(i).getName().equals("freehand")) {
				fh = new Freehand();
				if (fh.select(e, currentShapes, i) != null) {
					selectedShape = fh.select(e, currentShapes, i);
					help = i;
					helpp = "freehand";
					break;
				}
			}
			else if (currentShapes.get(i).getName().equals("Text")) {
				tx = new Text();
				if (tx.select(e, currentShapes, i) != null) {
					selectedShape = tx.select(e, currentShapes, i);
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
				cir.move(e, selectedShape);
			} else if (selectedShape.getName().equals("square")) {
				sq.move(e, selectedShape);
			} else if (selectedShape.getName().equals("ellipse")) {
				c.move(e, selectedShape);
			} else if (selectedShape.getName().equals("rectangle")) {
				r.move(e, selectedShape);
			} else if (selectedShape.getName().equals("triangle")) {
				t.move(e, selectedShape);
			} else if (selectedShape.getName().equals("line")) {
				l.move(e, selectedShape);
			} else if (selectedShape.getName().equals("freehand")) {
				fh.move(e, selectedShape);
			}  else if (selectedShape.getName().equals("Text")) {
				tx.move(e, selectedShape);
			}
			repaint();
		}
	}

	public void resize(MouseEvent e) {
		if (selectedShape != null) {
			if (selectedShape.getName().equals("circle")) {
				cir.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("square")) {
				sq.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("ellipse")) {
				c.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("rectangle")) {
				r.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("triangle")) {
				t.resize(e, selectedShape);
			} else if (selectedShape.getName().equals("line")) {
				l.resize(e, selectedShape);
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
					if (tx != null) {
						tx.setHight((int)bounds.getHeight());
						tx.setWidth(g.getFontMetrics().stringWidth(tx.getText()));
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
