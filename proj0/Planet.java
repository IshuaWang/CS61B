public class Planet{
	public static final double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double xDistance = xxPos - p.xxPos;
		double yDistance = yyPos - p.yyPos;
		return Math.pow(xDistance*xDistance + yDistance*yDistance, 0.5);
	}

	public double calcForceExertedBy(Planet p){
		double distance = calcDistance(p);
		return G * mass * p.mass / (distance * distance);
	}

	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double netForceX = 0;
		for(int i = 0; i < planets.length; i++) {
			if(this.equals(planets[i])) continue;
			netForceX += calcForceExertedByX(planets[i]);
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double netForceY = 0;
		for(int i = 0; i < planets.length; i++) {
			if(this.equals(planets[i])) continue;
			netForceY += calcForceExertedByY(planets[i]);
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY){
		double accX = fX / mass;
		double accY = fY / mass;
		xxVel += accX * dt;
		yyVel += accY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}