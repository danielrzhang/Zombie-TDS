package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tileArray = new Tile[8];
	public static Tile grassTile = new GrassTile(0);
	public static Tile metalTile = new MetalTile(1);
	public static Tile pathTile = new PathTile(2);
	public static Tile waterTile = new WaterTile(3);
	public static Tile crateTile = new CrateTile(4);
	public static Tile brickTile = new BrickTile(5);
	public static Tile shrubTile = new ShrubTile(6);
	public static Tile treeTile = new TreeTile(7);
	
	public static final int TILE_WIDTH = 100, TILE_HEIGHT = 100;
	
	protected BufferedImage texture;
	protected final int tileID;
		
	public Tile(BufferedImage texture, int tileID) {
		this.texture = texture;
		this.tileID = tileID;
		
		tileArray[tileID] = this;
	}

	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolidWalkable() {
		return false;
	}
	
	public boolean isSolidShootable() {
		return false;
	}
	
	public int getTileId() {
		return tileID;
	}
}
