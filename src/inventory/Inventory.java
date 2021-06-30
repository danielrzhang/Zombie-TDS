package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import game.Handler;
import item.Item;

public class Inventory {

	private Handler handler;
	private boolean active;
	private HashMap<String, Integer> inventoryItems;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		active = false;
		inventoryItems = new HashMap<String, Integer>();
		
		inventoryItems.put("Glock 19 Magazine", 2);
		inventoryItems.put("Remington 870 Shells", 2);
		inventoryItems.put("M16 Magazine", 1);
	}
	
	public void tick() {
		if (handler.getKeyManager().isKeyE()) {
			active = !active;
			handler.getHUD().setActive(!active);
			handler.getWeaponsManager().setActive(!active);
		}
		
		if (!active) {
			return;
		}	
		
//		System.out.println("Inventory:");
//		
//		for (HashMap.Entry<String, Integer> inventoryItems : inventoryItems.entrySet()) {
//		    String key = inventoryItems.getKey();
//		    Integer value = inventoryItems.getValue();
//		    System.out.println(key + ": " + value);
//		}
	}
	
	public void render(Graphics g) {
		if (!active) {
			return;
		}
				
		g.setColor(new Color(0, 0, 0, 127));
		g.fillRect(0, 0, 1200, 600);
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
