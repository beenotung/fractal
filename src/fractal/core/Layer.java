package fractal.core;

import java.awt.geom.Line2D;
import java.util.Vector;

public class Layer {
	public Vector<Line2D> lines;

	public Layer(Vector<Line2D> lines) {
		this.lines = lines;
	}

	public Layer() {
		lines = new Vector<Line2D>();
	}
}
