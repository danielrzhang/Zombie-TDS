package hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Assets;

public class HotbarBox {
	
	private Rectangle box;
	private BufferedImage texture;
	private int x, y, sideLength;
	private boolean selected;
	private Color baseColor;
	private Graphics2D g2d;
	private String keyBindString;
	private Boolean keyBind;
	private BasicStroke stroke;
	
	public HotbarBox(BufferedImage texture, int x, int y, int sideLength, String keyBindString) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.sideLength = sideLength;
		this.keyBindString = keyBindString;
		
		box = new Rectangle(x, y, sideLength, sideLength);
		selected = false;
		baseColor = new Color(47, 79, 79, 127);
		keyBind = null;
		stroke = new BasicStroke(2);
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g2d = (Graphics2D) g;

		drawHotbarBox(g);
		drawKeyBind(g);
	}
	
	public void drawHotbarBox(Graphics g) {
		g.setColor(baseColor);
		g.fillRect(x, y, sideLength, sideLength);
		g.drawImage(texture, x, y, sideLength, sideLength, null);
	}
	
	public void drawKeyBind(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(Assets.tinyZombieFont);
		g2d.setStroke(stroke);
		g2d.drawRect(x + 27, y - 30, 20, 20);
		g.drawString(keyBindString, x + 33, y - 15);
	}

	public Rectangle getBox() {
		return box;
	}

	public void setBox(Rectangle box) {
		this.box = box;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getKeyBindString() {
		return keyBindString;
	}

	public void setKeyBindString(String keyBindString) {
		this.keyBindString = keyBindString;
	}

	public Boolean getKeyBind() {
		return keyBind;
	}

	public void setKeyBind(Boolean keyBind) {
		this.keyBind = keyBind;
	}
}
