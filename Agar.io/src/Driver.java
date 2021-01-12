import java.awt.Color;
import java.awt.Font;
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
	ArrayList<Food> food = new ArrayList<Food>();
	Cell p;
	
	boolean follow = false;
	boolean alive = true;
	
	int dx, dy;
	int evx, evy;
	int rectx, recty;
	int worldw = 2000, worldh = 2000;
	int max = 0;
	int count = 0;
	int speed;
	double theta;
	
	Font Futura = new Font("Futura", Font.BOLD, 20);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		int speed = Math.round(75 / p.getRad()) + 1;
		
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
		
		for(int j = 0; j < food.size(); j++) {
			Food f = food.get(j);
			if (alive) {
				f.paint(g);
				f.setEVX(evx);
				f.setEVY(evy);
				collide(p, f, j);
				for(Enemy e: enemies) {
					collide(e, f, j);
				}
			}
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
				
				for(int j = 0; j < enemies.size(); j++) {
					Enemy e2 = enemies.get(j);
					collide(e, e2, i, j);
				}
			}
		}
		
		if (follow) {
			dx = (int) (MouseInfo.getPointerInfo().getLocation().getX() - p.getXPos() - p.getRad());
			dy = (int) (MouseInfo.getPointerInfo().getLocation().getY() - p.getYPos() - 1.4 * p.getRad());
			System.out.println(dx + ", " + dy);
		}
		
		if (alive) {
			g.setColor(Color.black);
			g.setFont(Futura);
			g.drawString("Mass: " + p.getMass() / 10, 80, 500);
			g.drawRect(rectx, recty, worldw, worldh);
			rectx += evx;
			recty += evy;
			p.paint(g);
			p.incrX(evx);
			p.incrY(evy);
		}
		
		if(count == 5) {
			food.add(new Food(rectx, recty, worldw, worldh));
			count = 0;
		}else count++;
	}
	
	public void collide(Cell p, Enemy e, int i) {
		int pcenterx = p.getXPos() + p.getRad();
		int pcentery = (int) (p.getYPos() + 1.4*p.getRad());
		int ecenterx = e.getX() + e.getRad();
		int ecentery = (int) (e.getY() + 1.4*e.getRad());
		if (p.getMass() > e.getMass() + 200) {
			if(pyth(pcenterx - ecenterx, pcentery - ecentery) < p.getRad()) {
				enemies.remove(i);
				p.setMass(e.getMass());
			}
		}
		else if(e.getMass() > p.getMass() + 200) {
			if(pyth(pcenterx - ecenterx, pcentery - ecentery) < e.getRad()) {
				alive = false;
			}
		}
	}
	
	public void collide(Cell p, Food f, int i) {
		int pcenterx = p.getXPos() + p.getRad();
		int pcentery = (int) (p.getYPos() + 1.4*p.getRad());
		int fcenterx = f.getX() + f.getRad();
		int fcentery = (int) (f.getY() + 1.4*f.getRad());
		if(pyth(pcenterx - fcenterx, pcentery - fcentery) < p.getRad()) {
			food.remove(i);
			p.setMass(f.getMass());
		}
	}
	
	public void collide(Enemy e, Enemy e2, int i, int j) {
		int ecenterx = e.getX() + e.getRad();
		int ecentery = (int) (e.getY() + 1.4*e.getRad());
		int e2centerx = e2.getX() + e2.getRad();
		int e2centery = (int) (e2.getY() + 1.4*e2.getRad());
		if (e.getMass() > e2.getMass() + 200) {
			if(pyth(e2centerx - ecenterx, e2centery - ecentery) < e.getRad()) {
				enemies.remove(j);
				e.setMass(e2.getMass());
			}
		}
		else if(e2.getMass() > e.getMass() + 200) {
			if(pyth(e2centerx - ecenterx, e2centery - ecentery) < e2.getRad()) {
				enemies.remove(i);
				e2.setMass(e.getMass());
			}
		}
	}
	
	public void collide(Enemy e, Food f, int i) {
		int ecenterx = e.getX() + e.getRad();
		int ecentery = (int) (e.getY() + 1.4*e.getRad());
		int fcenterx = f.getX() + f.getRad();
		int fcentery = (int) (f.getY() + 1.4*f.getRad());
		if(pyth(ecenterx - fcenterx, ecentery - fcentery) < e.getRad()) {
			food.remove(i);
			e.setMass(f.getMass());
		}
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
			enemies.add(new Enemy());
		}
		
		for(int i = 0; i < 200; i++) {
			food.add(new Food(rectx, recty, worldw, worldh));
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