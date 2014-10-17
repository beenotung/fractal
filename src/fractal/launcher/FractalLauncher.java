package fractal.launcher;

import fractal.core.FractalCore;

public class FractalLauncher {

	public static void main(String[] args) {
		FractalCore fractalCore=new FractalCore(800,600,1);
		fractalCore.start();
	}

}
