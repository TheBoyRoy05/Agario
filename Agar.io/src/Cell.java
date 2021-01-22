import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	
	private int x, y;
    private int xpos, ypos;
    private int vx, vy;
    private int rad;
    private int mass;
    private float scale = 1;
    private Color color;

    public Cell() {
    	mass = 1000;
    	rad = (int) (Math.sqrt(mass / Math.PI));
        color = new Color(255, 0, 0);
        xpos = 400 - rad;
        ypos = 300 - rad;
        x = xpos;
        y = ypos;
    }

    public void paint(Graphics g) {
        update();
        if (mass / 10 < 500000) rad = (int) (Math.sqrt(mass / Math.PI));
        else {
        	int newM = mass/10;
        	scale = (float) ((Math.pow(newM/10, 2) + 10000000) / (4 * Math.pow(newM/10, 2) + 10000000));
        }
        xpos = 400 - rad;
        ypos = 300 - rad;
        g.setColor(color);
        g.fillOval(xpos, ypos, rad*2, rad*2);
    }
    
    public int getXPos() {
    	return xpos;
    }
    
    public int getYPos() {
    	return ypos;
    }
    
    public void incrX(int x) {
    	this.x += x;
    }
    
    public void incrY(int y) {
    	this.y += y;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public int getRad() {
    	return rad;
    }
    
    public int getMass() {
    	return mass;
    }
    
    public void setMass(int m) {
    	mass += m;
    }
    
    public float getScale() {
    	return scale;
    }

    public void update() {
        xpos += vx;
        ypos += vy;
    }
}