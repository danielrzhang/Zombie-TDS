package item;

import java.awt.Graphics;

import game.Handler;

public class MasterItemManager {

	private Handler handler;
	private ItemManager<Coin> coinManager;
	private ItemManager<Medkit> medkitManager;
	private ItemManager<Glock19Magazine> glock19MagazineManager;
	private ItemManager<Remington870Shells> remington870ShellManager;
	private ItemManager<M16Magazine> m16MagazineManager;
	
	public MasterItemManager(Handler handler) {
		this.handler = handler;
		coinManager = new ItemManager<Coin>(handler);
		medkitManager = new ItemManager<Medkit>(handler);
		glock19MagazineManager = new ItemManager<Glock19Magazine>(handler);
		remington870ShellManager = new ItemManager<Remington870Shells>(handler);
		m16MagazineManager = new ItemManager<M16Magazine>(handler);
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

	public ItemManager<Coin> getCoinManager() {
		return coinManager;
	}

	public void setCoinManager(ItemManager<Coin> coinManager) {
		this.coinManager = coinManager;
	}
	
	public ItemManager<Medkit> getMedkitManager() {
		return medkitManager;
	}

	public void setMedkitManager(ItemManager<Medkit> medkitManager) {
		this.medkitManager = medkitManager;
	}

	public ItemManager<Glock19Magazine> getGlock19MagazineManager() {
		return glock19MagazineManager;
	}

	public void setGlock19MagazineManager(ItemManager<Glock19Magazine> glock19MagazineManager) {
		this.glock19MagazineManager = glock19MagazineManager;
	}

	public ItemManager<Remington870Shells> getRemington870ShellManager() {
		return remington870ShellManager;
	}

	public void setRemington870ShellManager(ItemManager<Remington870Shells> remington870ShellManager) {
		this.remington870ShellManager = remington870ShellManager;
	}

	public ItemManager<M16Magazine> getM16MagazineManager() {
		return m16MagazineManager;
	}

	public void setM16MagazineManager(ItemManager<M16Magazine> m16MagazineManager) {
		this.m16MagazineManager = m16MagazineManager;
	}
}
