import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.*;


public class MouseClass extends JPanel implements MouseListener,
		MouseMotionListener {


	JPanel canvas;
	private Color penColor = new Color(0, 0, 0);
	private int pointX, pointY, pointR;
	ArrayList<Shape> shapes;
	ArrayList<Shape> currentShapes;
	private ArrayList<Point> pointList;
	private ArrayList<Freehand> curves;
	private ArrayList<Freehand> currentCurves;
	private Ellipse c;
	private Circle cir;
	private Rectangle r;
	private Square sq;
	private Triangle t;
	private Line l;
	private Point point;
	private Freehand fh;
	private Text tx;
	private Eraser er;
	int flag;
	String message;
	int fz;
	private int startx, starty;
	Color color;
	boolean isFilled;
	private Shape selectedShape;

	String pos, helpp;
	int help;



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



	}
	@Override
	public void mousePressed(MouseEvent ev) {

		try {
			startx = ev.getX();
			startx = ev.getX();
			starty = ev.getY();
			if (flag == 5 || flag == 6) {
				select(ev);
			} else {
				if (flag == 1) {
					c = new Ellipse();
					c.setColor(color);
					c.setName("ellipse");
					c.setFilled(isFilled);
					c.setcX(ev.getX());
					c.setcY(ev.getY());
				} else if (flag == 2) {
					r = new Rectangle();
					r.setColor(color);
					r.setName("rectangle");
					r.setFilled(isFilled);
					r.setrX(ev.getX());
					r.setrY(ev.getY());
				} else if (flag == 3) {
					t = new Triangle();
					t.setColor(color);
					t.setName("triangle");
					t.setFilled(isFilled);
					t.settX(ev.getX());
					t.settY(ev.getY());
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
					sq.setPosx(ev.getX());
					sq.setPosy(ev.getY());

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
					tx.setName("text");
					tx.setFilled(isFilled);
					tx.setText(message);
					tx.setFSize(fz);
					tx.setPosx(ev.getX());
					tx.setPosy(ev.getY());

				}  else if (flag == 13) {
					er = new Eraser();
					er.setColor(Color.white);
					er.setName("eraser");
					pointX = ev.getX() ;   // the clicked point is stored without the offset
					pointY = ev.getY() ;   // the clicked point is stored without the offset
					er.setFY(pointY);
					er.setFX(pointX);
					pointList.add(new Point(pointX, pointY));
					er.setPoints(pointList);  // the clicked point is added to a list

				}
			}
		} catch (Exception eee) {

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
						c.setcX(Math.min(e.getX(), startx));
						c.setcY(Math.min(e.getY(), starty));
						c.setcWidth(Math.abs(e.getX() - startx));
						c.setcHeight(Math.abs(e.getY() - starty));

						shapes.add(c);
					} else if (flag == 2) {
						r.setrX(Math.min(e.getX(), startx));
						r.setrY(Math.min(e.getY(), starty));
						r.setrWidth(Math.abs(e.getX() - startx));
						r.setrHight(Math.abs(e.getY() - starty));

						shapes.add(r);
					} else if (flag == 3) {
						int base = e.getX() - t.gettX();
						int hight = e.getY() - t.gettY();
						int[] xPoints = { (t.gettX() + base / 2), t.gettX(),
								e.getX() };
						int[] yPoints = { t.gettY(), (t.gettY() + hight),
								e.getY() };
						t.setxPoints(xPoints);
						t.setyPoints(yPoints);
						shapes.add(t);
					} else if (flag == 4) {
						l.setX2(e.getX());
						l.setY2(e.getY());
						shapes.add(l);
					} else if (flag == 7) {
						cir.setX(Math.min(e.getX(), startx));
						cir.setRadius(Math.abs(e.getX() - startx));
						shapes.add(cir);

					} else if (flag == 8) {
						sq.setPosx(Math.min(e.getX(), startx));

						sq.setL(Math.abs(e.getX() - startx));

						shapes.add(sq);

					}

					repaint();
				}
			}
		} catch (Exception eee) {

		}
	}






	//To release the mouse means to save the parameters of the figure and save them into the current shapes array
	@Override
	public void mouseReleased(MouseEvent e) {
		try{


			selectedShape = null;

			if (flag != 5 && flag != 6) {
				if (flag == 1) {
					currentShapes.add(c);

					pos = c.getcX() + "," + c.getcY() + "," + c.getcWidth() + ","
							+ c.getcHeight() + "," + c.getColor().getRGB() + ","
							+ c.isFilled();


				} else if (flag == 2) {
					currentShapes.add(r);
					pos = r.getrX() + "," + r.getrY() + "," + r.getrWidth() + ","
							+ r.getrHight() + "," + r.getColor().getRGB() + ","
							+ r.isFilled();


				} else if (flag == 3) {
					currentShapes.add(t);
					String x = t.getxPoints()[0] + "," + t.getxPoints()[1] + ","
							+ t.getxPoints()[2];
					String y = t.getyPoints()[0] + "," + t.getyPoints()[1] + ","
							+ t.getyPoints()[2];
					pos = t.gettX() + "," + t.gettY() + "," + x + "," + y + ","
							+ t.getColor().getRGB() + "," + t.isFilled();


				} else if (flag == 4) {
					currentShapes.add(l);
					pos = l.getX1() + "," + l.getY1() + "," + l.getX2() + ","
							+ l.getY2() + "," + l.getColor().getRGB() + ","
							+ l.isFilled();



				} else if (flag == 7) {
					currentShapes.add(cir);
					pos = cir.getX() + "," + cir.getY() + "," + cir.getRadius()
							+ "," + cir.getRadius() + "," + cir.getColor().getRGB()
							+ "," + cir.isFilled();



				} else if (flag == 8) {
					currentShapes.add(sq);
					pos = sq.getPosx() + "," + sq.getPosy() + "," + sq.getL() + ","
							+ sq.getL() + "," + sq.getColor().getRGB() + ","
							+ sq.isFilled();


				} else if (flag == 11) {
					fh.setLX(e.getX());
					fh.setLY(e.getY());
					currentShapes.add(fh);
					pos = fh.getFX() + "," + fh.getFY() + "," + fh.getLX() + ","
							+ fh.getLY() + "," + fh.getColor().getRGB() + ","
							+ true;

					pointList = new ArrayList<Point>();

				} else if (flag == 13) {
					er.setLX(e.getX());
					er.setLY(e.getY());
					currentShapes.add(er);
					pos = er.getFX() + "," + er.getFY() + "," + er.getLX() + ","
							+ er.getLY() + "," + er.getColor().getRGB() + ","
							+ true;

					pointList = new ArrayList<Point>();

				}
				else if (flag == 12) {
					currentShapes.add(tx);
					pos = tx.getPosx() + "," + tx.getPosy() + "," + tx.getWidth() + ","
							+ tx.getHight() + "," + tx.getColor().getRGB() + ","
							+ true;


				}

				if (flag != 9) {

				}
			} else {
				if (flag != 9 && flag != 10) {


				}
			}
		}catch (Exception ewe) {
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


					selectedShape.setFilled(isFilled);


				}
			} else if (flag == 10) {
				select(e);
				delete();

			}
			repaint();
		}catch (Exception eas) {
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
			pos = "" + ((Ellipse) currentShapes.get(help)).getcX() + ","
					+ ((Ellipse) currentShapes.get(help)).getcY() + ","
					+ ((Ellipse) currentShapes.get(help)).getcWidth() + ","
					+ ((Ellipse) currentShapes.get(help)).getcHeight() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "square") {
			pos = "" + ((Square) currentShapes.get(help)).getPosx() + ","
					+ ((Square) currentShapes.get(help)).getPosy() + ","
					+ ((Square) currentShapes.get(help)).getL() + ","
					+ ((Square) currentShapes.get(help)).getL() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "triangle") {
			String x = ((Triangle) currentShapes.get(help)).getxPoints()[0]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getxPoints()[1]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getxPoints()[2];
			String y = ((Triangle) currentShapes.get(help)).getyPoints()[0]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getyPoints()[1]
					+ ","
					+ ((Triangle) currentShapes.get(help)).getyPoints()[2];
			pos = "" + ((Triangle) currentShapes.get(help)).gettX() + ","
					+ ((Triangle) currentShapes.get(help)).gettY() + "," + x
					+ "," + y + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "line") {
			pos = "" + ((Line) currentShapes.get(help)).getX1() + ","
					+ ((Line) currentShapes.get(help)).getY1() + ","
					+ ((Line) currentShapes.get(help)).getX2() + ","
					+ ((Line) currentShapes.get(help)).getY2() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "rectangle") {
			pos = "" + ((Rectangle) currentShapes.get(help)).getrX() + ","
					+ ((Rectangle) currentShapes.get(help)).getrY() + ","
					+ ((Rectangle) currentShapes.get(help)).getrWidth() + ","
					+ ((Rectangle) currentShapes.get(help)).getrHight() + ","
					+ currentShapes.get(help).getColor().getRGB();
		} else if (helpp == "text") {
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
		}catch (Exception edd) {
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
			else if (currentShapes.get(i).getName().equals("text")) {
				tx = new Text();
				if (tx.select(e, currentShapes, i) != null) {
					selectedShape = tx.select(e, currentShapes, i);
					help = i;
					helpp = "text";
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
			}  else if (selectedShape.getName().equals("text")) {
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
			}
			repaint();
		}
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
