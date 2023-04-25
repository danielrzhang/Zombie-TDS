package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x, y, width, height, health;
	protected Rectangle hitbox;
	protected boolean active;
	protected String entityName;

	public Entity(Handler handler, float x, float y, float width, float height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		active = true;
		hitbox = new Rectangle();
	}

	public abstract void tick();

	public abstract void render(Graphics g);
	
	public abstract Rectangle getHitbox();

	public abstract void die();

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}

			if (e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}


	public void hurt(float damageDealt) {	
		health -= damageDealt;

		if (health <= 0) {
			die();
			active = false;
		}
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + hitbox.x + xOffset), (int) (y + hitbox.y + yOffset), hitbox.width, hitbox.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEntityName() {
		return entityName;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
}
