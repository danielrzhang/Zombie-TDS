package hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Assets;

public class HotbarBoxHUD {
	
	private Rectangle box;
	private BufferedImage weapon;
	private int x, y, sideLength;
	private boolean currentlySelected;
	private Color notSelectedColor, selectedColor;
	private Graphics2D g2d;
	private BasicStroke stroke;
	private String keyBindString;
	private boolean keyBind;
	
	public HotbarBoxHUD(BufferedImage weapon, int x, int y, int sideLength, String keyBindString) {
		this.weapon = weapon;
		this.x = x;
		this.y = y;
		this.sideLength = sideLength;
		this.keyBindString = keyBindString;
		box = new Rectangle(x, y, sideLength, sideLength);
		currentlySelected = false;
		notSelectedColor = new Color(47, 79, 79, 127);
		selectedColor = new Color(255, 255, 255);
		stroke = new BasicStroke(2);
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g2d = (Graphics2D) g;

		drawHotbarBox(g);
		checkIfSelected(g);
		drawKeyBind(g);
	}
	
	public void drawHotbarBox(Graphics g) {
		g.setColor(notSelectedColor);
		g.fillRect(x, y, sideLength, sideLength);
		g.drawImage(weapon, x, y, sideLength, sideLength, null);
	}
	
	public void checkIfSelected(Graphics g) {
		if (currentlySelected) {
			g.setColor(selectedColor);
			g2d.setStroke(stroke);
			g2d.drawRect(x, y, sideLength + 1, sideLength + 1);
		} 
	}
	
	public void drawKeyBind(Graphics g) {
		g.setColor(Color.white);
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

	public BufferedImage getWeapon() {
		return weapon;
	}

	public void setWeapon(BufferedImage weapon) {
		this.weapon = weapon;
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

	public boolean isCurrentlySelected() {
		return currentlySelected;
	}

	public void setCurrentlySelected(boolean currentlySelected) {
		this.currentlySelected = currentlySelected;
	}
}
