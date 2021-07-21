package game;

import entity.Entity;
import tiles.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		}
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;
		checkBlankSpace();
	}
	
	public float getXOffset() {
		return xOffset;
	}

	public float getYOffset() {
		return yOffset;
	}
}
