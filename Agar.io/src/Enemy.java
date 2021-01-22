import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    private int x, y;
    private int vx, vy, evx, evy;
    private int randx = 0, randy = 0;
    private int rad;
    private int mass;
    private int speed, prevSpeed;
    private double theta;
    private Color color;

    public Enemy(int x, int y, int w, int h) {
    	
    	while(randx == 0) randx = (int)(Math.random()*200-100);
    	while(randy == 0) randy = (int)(Math.random()*200-100);
    	
    	mass = (int)(Math.random()*(2000)+600);
    	rad = (int)(Math.sqrt(mass / Math.PI));
    	speed = 75/rad + 1;
    	
    	//while(Math.toDegrees(theta) == 0 || Math.toDegrees(theta) == 90 ||
    		  //Math.toDegrees(theta) == 180 || Math.toDegrees(theta) == 270) {
    		theta = Math.atan(randx / randy);
    	//}
    		
    	vx = Math.round((float) (speed*Math.sin(theta)));
        vy = Math.round((float) (speed*Math.cos(theta)));
    	
        this.x = (int)(Math.random()*(w - rad) + x);
        this.y = (int)(Math.random()*(h - rad) + y);

        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        color = new Color(red,green,blue);
    }

    public void paint(Graphics g) {
    	if(vy == 0 || vx == 0) {
        	theta = Math.atan((Math.random()*200-100) / (Math.random()*200-100));
        	vx = Math.round((float)(speed*Math.sin(theta)));
            vy = Math.round((float)(speed*Math.cos(theta)));
    	}
    	prevSpeed = speed;
        rad = (int) (Math.sqrt(mass / Math.PI));
        speed = 75/rad + 1;
        if(prevSpeed != speed) {
        	theta = Math.atan(vx / vy);
        	vx = Math.round((float)(speed*Math.sin(theta)));
            vy = Math.round((float)(speed*Math.cos(theta)));
        }
        update();
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
    
    public void setMass(int m) {
    	mass += m;
    }
    
    public void update() {
        x += vx;
        x += evx;
        y += vy;
        y += evy;
    }
}