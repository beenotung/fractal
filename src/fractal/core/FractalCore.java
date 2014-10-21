package fractal.core;

import fractal.gui.FractalFrame;

public class FractalCore {

	private final int WIDTH, HEIGHT;
	private final float SCALE;

	private FractalFrame fractalFrame;
	private FractalIterator fractalIterator;

	public FractalCore(int width, int height, float scale, double rate) {
		WIDTH = width;
		HEIGHT = height;
		SCALE = scale;
		fractalFrame = new FractalFrame(WIDTH, HEIGHT, SCALE);
		fractalIterator = new FractalIterator(fractalFrame.getFractalCanvas(),rate);
	}

	public void start() {
		fractalFrame.start();
		fractalIterator.start();
	}
}
