package bullets;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Handler;

public class BulletManager {

	private Handler handler;
	private ArrayList<Bullet> bullets;
	
	public BulletManager(Handler handler) {
		this.handler = handler;
		bullets = new ArrayList<Bullet>();
	}
	
	public void tick() {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			
			if (!b.isActive()) {
				bullets.remove(i);
			} else {
				b.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.render(g);
		}
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	
	public void removeBullet(Bullet b) {
		bullets.remove(b);
	}

	public Handler getHandler() {
		return handler;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
