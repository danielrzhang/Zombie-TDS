package item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;

public abstract class Item {
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected int count;

	public Item(Handler handler) {
		this.handler = handler;
		count = 1;
	}
	
	public abstract void tick();

	public abstract void render(Graphics g);
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
}
