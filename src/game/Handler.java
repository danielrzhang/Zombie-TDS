package game;

import bullets.BulletManager;
import entity.EntityManager;
import entity.Player;
import hud.HUD;
import input.KeyManager;
import input.MouseManager;
import item.MasterWorldItemManager;
import states.StateManager;
import weapons.WeaponsManager;
import world.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}

	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public StateManager getStateManager() {
		return game.getStateManager();
	}
	
	public BulletManager getBulletManager() {
		return world.getBulletManager();
	}
	
	public EntityManager getEntityManager() {
		return world.getEntityManager();
	}
	
	public MasterWorldItemManager getMasterWorldItemManager() {
		return world.getMasterWorldItemManager();
	}
	
	public Player getPlayer() {
		return world.getPlayer();
	}
	
	public WeaponsManager getWeaponsManager() {
		return world.getWeaponsManager();
	}
	
	public HUD getHUD() {
		return world.getHUD();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
