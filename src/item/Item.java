package item;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import usable.Usable;

public abstract class Item extends Usable {
	
	public static final int COIN_WIDTH = 20, COIN_HEIGHT = 20;
	public static final int DEFAULT_ITEM_WIDTH = 30, DEFAULT_ITEM_HEIGHT = 40;
	
	protected float x, y;
	protected String name;
	protected boolean pickedUp;
	protected Rectangle hitbox;

	public Item(Handler handler, float x, float y) {
		super(handler);
		this.x = x;
		this.y = y;
		count = 1;
		pickedUp = false;
		hitbox = new Rectangle((int) x, (int) y, DEFAULT_ITEM_WIDTH, DEFAULT_ITEM_HEIGHT);
	}
	
	public abstract void tick();

	public abstract void render(Graphics g);
	
	public abstract Rectangle getHitbox();
	
	public void collectItem() {
		if (handler.getPlayer().getHitbox().intersects(getHitbox())) {
			pickedUp = true;
			handler.getPlayer().getInventory().addItem(this);
		}
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
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
