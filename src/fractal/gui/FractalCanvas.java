package fractal.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class FractalCanvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final int WIDTH;
	public final int HEIGHT;
	public final float SCALE;
	private BufferedImage bufferedImage;
	private int[] pixels;

	private boolean alive = false;

	private BufferStrategy bufferStrategy;
	private Graphics graphics;

	public FractalCanvas(int width, int height, float scale) {
		WIDTH = width;
		HEIGHT = height;
		SCALE = scale;
		bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
	}

	public void fillPixels(int colorCode) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = colorCode;
		}
	}

	public int getPixel(int x, int y) {
		return pixels[x + y * WIDTH];
	}

	public void setPixel(int x, int y, int colorCode) {
		pixels[x + y * WIDTH] = colorCode;
	}

	public void render() throws Exception {
		if (!alive)
			throw new Exception("Canvas not alive");
		// graphics.drawImage(bufferedImage, 0, 0, WIDTH, HEIGHT, null);
		bufferStrategy.show();
	}

	public void drawLine(Line2D line2d) {
		graphics.drawLine(Math.round(Math.round(line2d.getX1())),
				Math.round(Math.round(line2d.getY1())),
				Math.round(Math.round(line2d.getX2())),
				Math.round(Math.round(line2d.getY2())));
	}

	public void start() {
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
		alive = true;
	}

	public void stop() {
		alive = false;
	}

}
