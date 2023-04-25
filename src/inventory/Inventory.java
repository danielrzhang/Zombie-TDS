package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import game.Assets;
import game.Handler;
import item.Item;

public class Inventory {

	private Handler handler;
	private boolean active;
	private HashMap<String, Integer> inventoryItems;
	private Color backColor;
	
	private InventoryBox[] resourcesAndAmmo;
	private InventoryBox[] hotbar;
	
	private BufferedImage[] resourcesAndAmmoImages;
	private BufferedImage[] hotbarImages;
	
	private int xStart = 250;
	private int gap = 150;
	
	private int resourcesAndAmmoY = 170;
	private int hotbarY = 370;
	
	private int coinWidth = 60, coinHeight = 60, coinXOffset = 15, coinYOffset = 15;
	private int medkitWidth = 70, medkitHeight = 70, medkitXOffset = 10, medkitYOffset = 10;
	private int glock19MagazineWidth = 80, glock19MagazineHeight = 70, glock19MagazineXOffset = 5, glock19MagazineYOffset = 5;
	private int remington870ShellWidth = 70, remington870ShellHeight = 70, remington870ShellXOffset = 10, remington870ShellYOffset = 5;
	private int m16MagazineWidth = 70, m16MagazineHeight = 70, m16MagazineXOffset = 5, m16MagazineYOffset = 10;
	
	private int weaponsLength = 90, weaponsOffset = 0;
	
	public Inventory(Handler handler) {
		this.handler = handler;

		inventoryItems = new HashMap<String, Integer>();
		
		inventoryItems.put("Coin", 0);
		inventoryItems.put("Glock 19 Magazine", 2);
		inventoryItems.put("Remington 870 Shells", 2);
		inventoryItems.put("M16 Magazine", 1);

		active = false;
		
		backColor = new Color(220, 220, 220);
		
		resourcesAndAmmo = new InventoryBox[5];
		hotbar = new InventoryBox[5];
		
		resourcesAndAmmoImages = new BufferedImage[] {Assets.coin, Assets.medkit, Assets.glock19Magazine, Assets.shells, Assets.m16Magazine};
		hotbarImages = new BufferedImage[] {Assets.kabar, Assets.glock19, Assets.remington870, Assets.m16, Assets.landmine};
		
		initializeInventory();
	}
	
	public void initializeInventory() {
		
		for (int i = 0; i < 5; i++) {
			resourcesAndAmmo[i] = new InventoryBox(handler, resourcesAndAmmoImages[i], xStart + gap * i, resourcesAndAmmoY);
			hotbar[i] = new InventoryBox(handler, hotbarImages[i], xStart + gap * i, hotbarY);
		}
		
		resourcesAndAmmo[0].setSpecifics(coinWidth, coinHeight, coinXOffset, coinYOffset);
		resourcesAndAmmo[1].setSpecifics(medkitWidth, medkitHeight, medkitXOffset, medkitYOffset);
		resourcesAndAmmo[2].setSpecifics(glock19MagazineWidth, glock19MagazineHeight, glock19MagazineXOffset, glock19MagazineYOffset);
		resourcesAndAmmo[3].setSpecifics(remington870ShellWidth, remington870ShellHeight, remington870ShellXOffset, remington870ShellYOffset);
		resourcesAndAmmo[4].setSpecifics(m16MagazineWidth, m16MagazineHeight, m16MagazineXOffset, m16MagazineYOffset);
		
		for (int i = 0; i < hotbar.length; i++) {
			hotbar[i].setSpecifics(weaponsLength, weaponsLength, weaponsOffset, weaponsOffset);
		}
	}
	
	public void tick() {
		if (handler.getKeyManager().isKeyE()) {
			active = !active;
			handler.getWeaponsManager().setActive(!active);
			handler.getHUD().setActive(!active);
		}
		
		if (!active) {
			return;
		}	
		tickAllInventoryRows();
	}
	
	public void render(Graphics g) {
		if (!active) {
			return;
		}
		drawInventory(g);
	}
	
	public void tickAllInventoryRows() {
		for (int i = 0; i < 5; i++) {
			resourcesAndAmmo[i].tick();
			hotbar[i].tick();
		}
	}
	
	public void drawInventory(Graphics g) {
		drawBaseInventory(g);
		drawResourcesAndAmmo(g);
		drawHotbar(g);
	}
	
	public void drawBaseInventory(Graphics g) {
		g.setColor(new Color(0, 0, 0, 127));
		g.fillRect(0, 0, 1200, 600);
		g.drawImage(Assets.inventory, 200, 100, null);
	}
	
	public void drawResourcesAndAmmo(Graphics g) {
		g.setColor(backColor);
		for (int i = 0; i < resourcesAndAmmo.length; i++) {
			resourcesAndAmmo[i].render(g);
		}
	}
	
	public void drawHotbar(Graphics g) {
		g.setColor(backColor);
		
		for (int i = 0; i < 5; i++) {
			hotbar[i].render(g);
		}
	}
	
	public void addItem(Item item) {
		if (inventoryItems.containsKey(item.getName())) {
			inventoryItems.put(item.getName(), inventoryItems.get(item.getName()) + item.getCount());
		} else {
			inventoryItems.put(item.getName(), item.getCount());
		}
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public HashMap<String, Integer> getInventoryItems() {
		return inventoryItems;
	}
}
