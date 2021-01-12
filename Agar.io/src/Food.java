import java.awt.Color;
import java.awt.Graphics;

public class Food {

    private int x, y;
    private int vx, vy, evx, evy;
    private int rad;
    private int mass;
    private Color color;

    public Food(int x, int y, int w, int h) {
    	mass = 200;
    	rad = (int) (Math.sqrt(mass / Math.PI));
    	
        this.x = (int)(Math.random()*(w - rad) + x);
        this.y = (int)(Math.random()*(h - rad) + y);

        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        color = new Color(red,green,blue);
    }

    public void paint(Graphics g) {
        update();
        rad = (int) (Math.sqrt(mass / Math.PI));
        g.setColor(color);
        g.fillOval(x, y, rad*2, rad*2);
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public void setEVX(int vx) {
    	evx = vx;
    }
    
    public void setEVY(int vy) {
    	evy = vy;
    }
    
    public int getVX() {
    	return vx;
    }
    
    public int getVY() {
    	return vy;
    }
    
    public void setVX(int x) {
    	vx *= x;
    }
    
    public void setVY(int y) {
    	vy *= y;
    }
    
    public int getRad() {
    	return rad;
    }
    
    public int getMass() {
    	return mass;
    }
    
    public void update() {
        x += vx;
        x += evx;
        y += vy;
        y += evy;
    }
}