package item;

import java.awt.Rectangle;

import game.Handler;

public abstract class WorldItem extends Item {

	public static final int COIN_WIDTH = 20, COIN_HEIGHT = 20;
	public static final int DEFAULT_ITEM_WIDTH = 30, DEFAULT_ITEM_HEIGHT = 40;
	
	protected float x, y;
	protected boolean pickedUp;
	protected Rectangle hitbox;

	public WorldItem(Handler handler, float x, float y) {
		super(handler);
		this.x = x;
		this.y = y;
		pickedUp = false;
		hitbox = new Rectangle((int) x, (int) y, DEFAULT_ITEM_WIDTH, DEFAULT_ITEM_HEIGHT);
	}
	
	public abstract Rectangle getHitbox();
	
	public void collectItem() {
		if (handler.getPlayer().getHitbox().intersects(getHitbox())) {
			pickedUp = true;
			handler.getPlayer().getInventory().addItem(this);
		}
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

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
}
