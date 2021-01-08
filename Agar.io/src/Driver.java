import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
 
public class Driver extends JPanel implements MouseListener, ActionListener{
 
	//Create ArrayList for enemies
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Cell p;
	
	boolean follow = false;
	boolean alive = true;
	
	int dx, dy;
	int evx, evy;
	int rectx, recty;
	int worldw = 2000, worldh = 2000;
	int max = 0;
	int speed;
	double theta;
 
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		int speed = (100 / p.getRad()) + 1;
		
		if(dy == 0) {
			if (dx > 0) theta = 0;
			else theta = Math.PI;
		}else theta = Math.abs(Math.atan(dx/dy));
		
		if (dx >= 0 && p.getXPos() <= rectx + worldw - 2*p.getRad()) evx = Math.round((float)(-1*speed*Math.sin(theta)));
		else if(p.getXPos() >= rectx) evx = Math.round((float)(speed*Math.sin(theta)));
		else evx = 0;
		
		if (dy >= 0 && p.getYPos() <= recty + worldh - 2*p.getRad()) evy = Math.round((float)(-1*speed*Math.cos(theta)));
		else if(p.getYPos() >= recty) evy = Math.round((float)(speed*Math.cos(theta)));
		else evy = 0;
		
		g.drawRect(rectx, recty, worldw, worldh);
		rectx += evx;
		recty += evy;
		
		if (alive) {
			p.paint(g);
			p.incrX(evx);
			p.incrY(evy);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			
			if (alive) {
				e.paint(g);
				e.setEVX(evx);
				e.setEVY(evy);
				
				if(e.getX() <= rectx && e.getVX() <= 0) e.setVX(-1);
				else if(e.getX() >= rectx + worldw - 2 * e.getRad() && e.getVX() >= 0) e.setVX(-1);
				
				if(e.getY() <= recty && e.getVY() <= 0) e.setVY(-1);
				else if(e.getY() >= recty + worldh - 2 * e.getRad() && e.getVY() >= 0) e.setVY(-1);
				
				collide(p, e, i);
			}
		}
		
		if (follow) {
			dx = (int) MouseInfo.getPointerInfo().getLocation().getX() - p.getXPos() - p.getRad();
			dy = (int) MouseInfo.getPointerInfo().getLocation().getY() - p.getYPos() - 2*p.getRad();
		}
	}
	
	public void collide(Cell p, Enemy e, int i) {
		int pcenterx = p.getXPos() - p.getRad();
		int pcentery = p.getYPos() - 2*p.getRad();
		int ecenterx = e.getX() - e.getRad();
		int ecentery = e.getY() - 2*e.getRad();
		if (p.getMass() > e.getMass() + 20) {
			if(pyth(pcenterx - ecenterx, pcentery - ecentery) < p.getRad()) {
				enemies.remove(i);
				p.setMass(e.getMass());
			}
		}
		else if(e.getMass() > p.getMass() + 20) {
			if(pyth(pcenterx - ecenterx, pcentery - ecentery) < e.getRad()) {
				alive = false;
			}
		}
	}
	
	public void collide(Enemy e, Enemy e2) {
		
	}
	
	public double pyth(int a, int b) {
		return Math.sqrt((a*a) + (b*b));
	}

	public Driver(){
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800,600);
		frame.add(this);
		frame.addMouseListener(this);
		
		for(int i = 0 ; i < 50; i++) {
			enemies.add( new Enemy() );
		}	
 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Timer t = new Timer(16, this);
		t.start();
		
		p = new Cell();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}
 
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
 
	@Override
	public void mouseEntered(MouseEvent arg0) {
		follow = true;
	}
 
	@Override
	public void mouseExited(MouseEvent arg0) {
		follow = false;
	}
 
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
 
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		
	}
}