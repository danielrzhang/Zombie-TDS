package world;

import java.awt.Graphics;

import bullets.BulletManager;
import entity.EntityManager;
import entityMoving.Player;
import game.Handler;
import health.PlayerHealthBar;
import hud.HUD;
import item.MasterWorldItemManager;
import tiles.Tile;
import utils.Utils;
import weapons.WeaponsManager;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	private EntityManager entityManager;
	private BulletManager bulletManager;
	private MasterWorldItemManager masterWorldItemManager;
	private WeaponsManager weaponsManager;
	private HUD hud;
	
	private Player player;
	private PlayerHealthBar playerHealthBar;
		
	public World(Handler handler, String path) {
		this.handler = handler;
		player = new Player(handler, 0, 0);
		playerHealthBar = new PlayerHealthBar(handler, player.getX(), player.getY());
		entityManager = new EntityManager(handler, player);
		bulletManager = new BulletManager(handler);
		masterWorldItemManager = new MasterWorldItemManager(handler);
		weaponsManager = new WeaponsManager(handler);
		hud = new HUD(handler);
				
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}

	public void tick() {
		masterWorldItemManager.tick();
		weaponsManager.tick();
		bulletManager.tick();
		entityManager.tick();
		playerHealthBar.tick();
		hud.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getXOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getXOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getYOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getYOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for (int j = yStart; j < yEnd; j++) {
			for (int i = xStart; i < xEnd; i++) {
				getTile(i, j).render(g, (int) (i * Tile.TILE_WIDTH - handler.getGameCamera().getXOffset()), 
										(int) (j * Tile.TILE_HEIGHT - handler.getGameCamera().getYOffset()));
			}
		}
		masterWorldItemManager.render(g);
		bulletManager.render(g);
		entityManager.render(g);
		playerHealthBar.render(g);
		hud.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tileArray[tiles[x][y]];
		
		if (t == null) {
			return Tile.grassTile;
		}
		
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				tiles[i][j] = Utils.parseInt(tokens[(i + j * width) + 4]);
			}
		}
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public BulletManager getBulletManager() {
		return bulletManager;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public PlayerHealthBar getPlayerHealthBar() {
		return playerHealthBar;
	}
	
	public MasterWorldItemManager getMasterWorldItemManager() {
		return masterWorldItemManager;
	}
	
	public WeaponsManager getWeaponsManager() {
		return weaponsManager;
	}
	
	public HUD getHUD() {
		return hud;
	}
}
