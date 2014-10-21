package fractal.launcher;

import fractal.core.FractalCore;

public class FractalLauncher {

	public static void main(String[] args) {
		FractalCore fractalCore=new FractalCore(800,800,1,0.5);
		fractalCore.start();
	}

}
