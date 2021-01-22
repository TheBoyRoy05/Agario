import java.awt.Color;
import java.awt.Graphics;

public class Food {

    private int x, y;
    private int vx, vy, evx, evy;
    private int worldx, worldy;
    private int rad;
    private int mass;
    private double scale;
    private Color color;

    public Food(int x, int y, int w, int h) {
    	mass = 200;
    	rad = (int) (Math.sqrt(mass / Math.PI));
    	
        this.x = (int)(Math.random()*(w - rad) + x);
        this.y = (int)(Math.random()*(h - rad) + y);
        worldx = (int) (this.x / scale);
        worldy = (int) (this.y / scale);

        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        color = new Color(red,green,blue);
    }

    public void paint(Graphics g) {
        update();
        rad = (int) (Math.sqrt(mass / Math.PI));
        //rad = (int) (Math.round(200*scale));
        g.setColor(color);
        g.fillOval(x, y, rad*2, rad*2);
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public int getX() {
    	return x;
    }
    
    public void setY(int y) {
    	this.y = y;
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
    
    public void setRad(int r) {
    	rad = r;
    }
    
    public int getRad() {
    	return rad;
    }
    
    public int getMass() {
    	return mass;
    }
    
    public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	
	public int getWorldx() {
		return worldx;
	}

	public void setWorldx(int worldx) {
		this.worldx = worldx;
	}

	public int getWorldy() {
		return worldy;
	}

	public void setWorldy(int worldy) {
		this.worldy = worldy;
	}

	public void update() {
        x += vx;
        x += evx;
        y += vy;
        y += evy;
    }
}