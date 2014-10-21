package fractal.core;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Vector;

import fractal.gui.FractalCanvas;

public class FractalIterator implements Runnable {

	private final double rate;;

	private Thread thread;
	private boolean alive = false;
	private LinkedList<Layer> layers;

	private FractalCanvas fractalCanvas;

	/** implement **/
	@Override
	public void run() {
		fractalCanvas.setVisible(true);
		initLayer();
		while (alive) {
			addLayer();
			try {
				thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public FractalIterator(FractalCanvas fractalCanvas, double rate) {
		this.fractalCanvas = fractalCanvas;
		this.rate = rate;
		layers = new LinkedList<Layer>();
	}

	private void initLayer() {
		layers = new LinkedList<Layer>();
		Vector<Line2D> lines = new Vector<Line2D>();
		// Point2D p1 = new Point2D.Float(0, fractalCanvas.HEIGHT);
		// Point2D p2 = new Point2D.Float(fractalCanvas.WIDTH / 2,
		// fractalCanvas.HEIGHT / 2);
		Point2D p1 = new Point2D.Float(0, 0);
		Point2D p2 = new Point2D.Float(fractalCanvas.WIDTH / 2, fractalCanvas.HEIGHT / 2);
		Line2D line2d = new Line2D.Float(p1, p2);
		lines.add(line2d);
		layers.add(new Layer(lines));
		fractalCanvas.drawLine(line2d);
	}

	private void addLayer() {
		Layer newLayer = new Layer();
		for (Line2D line2d : layers.get(layers.size() - 1).lines) {
			double distance = line2d.getP1().distance(line2d.getP2()) * rate;
			double y = line2d.getY2() - line2d.getY1();
			double x = line2d.getX2() - line2d.getX1();
			int xDir = Double.compare(line2d.getX2(), line2d.getX1());
			int yDir = Double.compare(line2d.getY2(), line2d.getY1());
			double theta;
			if (xDir == 0) {
				if (yDir < 0)
					theta = Math.PI * 1.5;
				else
					theta = Math.PI / 2.0;
			} else {
				if (xDir > 0) {
					theta = Math.atan(y / x);
					if (yDir < 0)
						theta += Math.PI * 2.0;
				} else {
					theta = Math.atan(y / x);
					if (yDir > 0)
						theta *= -1;
					theta += Math.PI;
				}
			}
			Line2D line2d1 = new Line2D.Float(line2d.getP2(), dest(line2d.getP2(),
					distance, theta - Math.PI / 3.0));
			Line2D line2d2 = new Line2D.Float(line2d.getP2(), dest(line2d.getP2(),
					distance, theta + Math.PI / 3.0));
			newLayer.lines.add(line2d1);
			newLayer.lines.add(line2d2);
		}
		for (Line2D line2d : newLayer.lines) {
			fractalCanvas.drawLine(line2d);
		}
		layers.add(newLayer);
	}

	private Point2D dest(Point2D src, double r, double theta) {
		while (theta > Math.PI * 2)
			theta -= Math.PI * 2;
		while (theta < 0)
			theta += Math.PI * 2;
		double x = Math.cos(theta) * r + src.getX();
		double y = Math.sin(theta) * r + src.getY();
		return new Point2D.Double(x, y);
	}

	public void start() {
		if (thread == null)
			thread = new Thread(this);
		alive = true;
		thread.start();
	}

	public void stop() {
		alive = false;
	}
}
