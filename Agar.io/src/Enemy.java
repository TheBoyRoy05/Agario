import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    private int x, y;
    private int vx, vy, evx, evy;
    private int rad;
    private int mass;
    private int speed;
    private double theta;
    private Color color;

    public Enemy() {
    	int randx = 0, randy = 0;
    	
    	while(randx == 0) randx = (int)(Math.random()*100-200);
    	while(randy == 0) randy = (int)(Math.random()*100-200);
    	
    	mass = (int)(Math.random()*(2000)+600);;
    	rad = (int) (Math.sqrt(mass / Math.PI));
    	speed = 100/rad + 1;
    	theta = Math.atan(randx/randy);
    	
        x = (int)(Math.random()*(2000-rad));
        y = (int)(Math.random()*(2000-rad));
        
        vx = Math.round((float)(speed*Math.sin(theta)));
        vy = Math.round((float)(speed*Math.cos(theta)));

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