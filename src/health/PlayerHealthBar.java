package health;

import java.awt.Color;
import java.awt.Graphics;

import entityMoving.MovingEntity;
import game.Handler;

public class PlayerHealthBar {
	
	private Handler handler;
	private float x, y, borderWidth;
	
	private float healthBarWidth, healthBarHeight;
	private float backBarWidth, backBarHeight;
	
	private static float DEFAULT_BAR_WIDTH = 200, DEFAULT_BAR_HEIGHT = 20;
	private static float DEFAULT_BORDER_WIDTH = 5; 
	
	public PlayerHealthBar(Handler handler, float x, float y) {
		this.handler = handler;
		this.x = 50;
		this.y = 30;
		borderWidth = DEFAULT_BORDER_WIDTH;
		healthBarWidth = DEFAULT_BAR_WIDTH;
		healthBarHeight = DEFAULT_BAR_HEIGHT;
		backBarWidth = DEFAULT_BAR_WIDTH + (borderWidth * 2);
		backBarHeight = DEFAULT_BAR_HEIGHT + (borderWidth * 2);
	}
	
	public void tick() {
		reducePlayerHealth();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect((int) (x - borderWidth), (int) (y - borderWidth), (int) backBarWidth, (int) backBarHeight, 10, 10);
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, (int) healthBarWidth, (int) healthBarHeight);
	}
	
	public void reducePlayerHealth() {
		 healthBarWidth = handler.getWorld().getPlayer().getHealth() * (DEFAULT_BAR_WIDTH / MovingEntity.DEFAULT_PLAYER_HEALTH); 
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRedBarWidth() {
		return healthBarWidth;
	}
	
	public float getRedBarHeight() {
		return healthBarHeight;
	}
	
	public float getBlackBarWidth() {
		return backBarWidth;
	}
	
	public float getBlackBarHeight() {
		return backBarHeight;
	}
}
