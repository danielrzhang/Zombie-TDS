package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;

public class InventoryBox {
	
	protected final int SIDE_LENGTH = 90;
	
	private Handler handler;
	private int x, y, imageWidth, imageHeight, xOffset, yOffset;
	private Color boxColor;
	private BufferedImage texture;
	
	public InventoryBox(Handler handler, BufferedImage texture, int x, int y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.texture = texture;
//		boxColor = new Color(220, 220, 220);
		boxColor = new Color(100, 100, 100);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		drawBox(g);
		drawItem(g);
	}
	
	public void drawBox(Graphics g) {
		g.setColor(boxColor);
		g.fillRect(x, y, SIDE_LENGTH, SIDE_LENGTH);
	}
	
	public void drawItem(Graphics g) {
		if (texture == null) {
			g.drawImage(null, x, y, null);
		} else {
			g.drawImage(texture, x + xOffset, y + yOffset, imageWidth, imageHeight, null);
		}
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
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

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		this.boxColor = boxColor;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getXOffset() {
		return xOffset;
	}

	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}

	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public void setSpecifics(int imageWidth, int imageHeight, int xOffset, int yOffset) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
