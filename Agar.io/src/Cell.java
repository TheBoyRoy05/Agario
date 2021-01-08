import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	
	private int x, y;
    private int xpos, ypos;
    private int vx, vy;
    private int rad;
    private int mass;
    private Color color;

    public Cell() {
    	mass = 1000;
    	rad = (int) (Math.sqrt(mass / Math.PI));
        
        color = new Color(255, 0, 0);
    }

    public void paint(Graphics g) {
        update();
        rad = (int) (Math.sqrt(mass / Math.PI));
        xpos = 400 - rad;
        ypos = 300 - rad;
        x = xpos;
        y = ypos;
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

    public void update() {
        xpos += vx;
        ypos += vy;
    }
}