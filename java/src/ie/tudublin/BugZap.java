package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet {
    public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(HSB);
		background(0);

	}

	float x1, y1, x2, y2;
	
	public void draw()
	{	
		strokeWeight(2);
		stroke(0, 255, 255);
		line(x1, y1, x2, y2);
    }
}

