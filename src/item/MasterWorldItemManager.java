package item;

import java.awt.Graphics;

import game.Handler;

public class MasterWorldItemManager {

	private Handler handler;
	private WorldItemManager<Coin> coinManager;
	private WorldItemManager<Medkit> medkitManager;
	private WorldItemManager<Glock19Magazine> glock19MagazineManager;
	private WorldItemManager<Remington870Shells> remington870ShellManager;
	private WorldItemManager<M16Magazine> m16MagazineManager;
	
	public MasterWorldItemManager(Handler handler) {
		this.handler = handler;
		coinManager = new WorldItemManager<Coin>(handler);
		medkitManager = new WorldItemManager<Medkit>(handler);
		glock19MagazineManager = new WorldItemManager<Glock19Magazine>(handler);
		remington870ShellManager = new WorldItemManager<Remington870Shells>(handler);
		m16MagazineManager = new WorldItemManager<M16Magazine>(handler);
	}
	
	public void tick() {
		coinManager.tick();
		medkitManager.tick();
		glock19MagazineManager.tick();
		remington870ShellManager.tick();
		m16MagazineManager.tick();
	}
	
	public void render(Graphics g) {
		medkitManager.render(g);
		glock19MagazineManager.render(g);
		remington870ShellManager.render(g);
		m16MagazineManager.render(g);
		coinManager.render(g);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public WorldItemManager<Coin> getCoinManager() {
		return coinManager;
	}

	public void setCoinManager(WorldItemManager<Coin> coinManager) {
		this.coinManager = coinManager;
	}
	
	public WorldItemManager<Medkit> getMedkitManager() {
		return medkitManager;
	}

	public void setMedkitManager(WorldItemManager<Medkit> medkitManager) {
		this.medkitManager = medkitManager;
	}

	public WorldItemManager<Glock19Magazine> getGlock19MagazineManager() {
		return glock19MagazineManager;
	}

	public void setGlock19MagazineManager(WorldItemManager<Glock19Magazine> glock19MagazineManager) {
		this.glock19MagazineManager = glock19MagazineManager;
	}

	public WorldItemManager<Remington870Shells> getRemington870ShellManager() {
		return remington870ShellManager;
	}

	public void setRemington870ShellManager(WorldItemManager<Remington870Shells> remington870ShellManager) {
		this.remington870ShellManager = remington870ShellManager;
	}

	public WorldItemManager<M16Magazine> getM16MagazineManager() {
		return m16MagazineManager;
	}

	public void setM16MagazineManager(WorldItemManager<M16Magazine> m16MagazineManager) {
		this.m16MagazineManager = m16MagazineManager;
	}
}
