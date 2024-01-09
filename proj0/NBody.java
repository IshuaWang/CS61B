public class NBody{
	public static double readRadius(String file) {
		In in = new In(file);
		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int number = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[number];
		for(int i = 0; i < number; i++) {
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		double t = 0;
		int n = planets.length;

		StdAudio.play("audio/2001.mid");

		StdDraw.enableDoubleBuffering();

		while(t < T) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			for(int i = 0; i < n; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByX(planets);
			}
			for(int i = 0; i < n; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(Planet p: planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}