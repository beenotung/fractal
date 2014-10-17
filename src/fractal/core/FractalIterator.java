package fractal.core;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import myutils.Utils;
import fractal.gui.FractalCanvas;

public class FractalIterator implements Runnable {

	private Thread thread;
	private boolean alive = false;
	private LinkedList<Layer> layers;

	private FractalCanvas fractalCanvas;

	/** implement **/
	@Override
	public void run() {
		fractalCanvas.setVisible(true);
		while (alive) {
			addLayer();
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public FractalIterator(FractalCanvas fractalCanvas) {
		this.fractalCanvas = fractalCanvas;
		layers = new LinkedList<Layer>();
	}

	private void addLayer() {

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
