package health;

import java.awt.Color;
import java.awt.Graphics;

import entityMoving.MovingEntity;
import entityMoving.Zombie;
import game.Handler;

public class EnemyHealthBar {

	private Handler handler;
	
	private float x, y, borderWidth;
	private float healthBarWidth, healthBarHeight;
	private float backBarWidth, backBarHeight;
	
	private static float DEFAULT_BAR_WIDTH = 100, DEFAULT_BAR_HEIGHT = 10;
	private static float DEFAULT_BORDER_WIDTH = 3;
	
	public EnemyHealthBar(Handler handler, Zombie zombie) {
		this.handler = handler;
		x = zombie.getX();
		y = zombie.getY();
		borderWidth = DEFAULT_BORDER_WIDTH;
		healthBarWidth = DEFAULT_BAR_WIDTH;
		healthBarHeight = DEFAULT_BAR_HEIGHT;
		backBarWidth = DEFAULT_BAR_WIDTH + (borderWidth * 2);
		backBarHeight = DEFAULT_BAR_HEIGHT + (borderWidth * 2);
	}
	
	public void tick(Zombie zombie) {
		reduceEnemyHealth(zombie);
		updateHealthBarPosition(zombie);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect((int) (x + 15 - borderWidth - handler.getGameCamera().getXOffset()), (int) (y + 15 - borderWidth - handler.getGameCamera().getYOffset()), (int) backBarWidth, (int) backBarHeight, 5, 5);
		g.setColor(Color.RED);
		g.fillRect((int) (x + 15 - handler.getGameCamera().getXOffset()), (int) (y + 15 - handler.getGameCamera().getYOffset()), (int) healthBarWidth, (int) healthBarHeight);
	}
	
	public void updateHealthBarPosition(Zombie zombie) {
		x = zombie.getX();
		y = zombie.getY();
	}
	
	public void reduceEnemyHealth(Zombie zombie) {
		 healthBarWidth = zombie.getHealth() * (DEFAULT_BAR_WIDTH / MovingEntity.DEFAULT_ZOMBIE_HEALTH); 
	}
}
