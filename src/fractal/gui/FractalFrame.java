package fractal.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import myutils.Utils;

public class FractalFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NAME = "Fractal Frame";

	private final int WIDTH, HEIGHT;
	private final float SCALE;

	private Thread thread;
	boolean alive = false;
	private FractalCanvas fractalCanvas;

	/** implement **/
	@Override
	public void run() {
		setVisible(true);
		fractalCanvas.setVisible(true);
		while (alive) {
			try {
				render();
			} catch (Exception e1) {
				fractalCanvas.start();
			}
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public FractalFrame(int width, int height, float scale) {
		setVisible(false);
		setTitle(NAME);
		WIDTH = width;
		HEIGHT = height;
		SCALE = scale;
		fractalCanvas = new FractalCanvas(WIDTH, HEIGHT, SCALE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.add(fractalCanvas, BorderLayout.CENTER);
		this.pack();
	}

	public FractalCanvas getFractalCanvas() {
		return fractalCanvas;
	}

	private void render() throws Exception {
		fractalCanvas.render();
	}

	public void start() {
		setPreferredSize(fractalCanvas.getPreferredSize());
		pack();
		fractalCanvas.start();
		if (thread == null)
			thread = new Thread(this);
		alive = true;
		thread.start();
	}

	public void stop() {
		alive = false;
	}

}
