package item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import game.Handler;

public class ItemManager<T> {
	
	private Handler handler;
	private ArrayList<Item> itemList;
	private int count;
	
	public ItemManager(Handler handler) {
		this.handler = handler;
		itemList = new ArrayList<Item>();
		count = itemList.size();
	}
	
	public void tick() {
		Iterator<Item> iterator = itemList.iterator();
		while (iterator.hasNext()){
			Item item = iterator.next();
			item.tick();

			if (item.isPickedUp()) {
				iterator.remove();
			} 
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			item.render(g);
		}
	}
	
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public void removeItem(Item item) {
		itemList.remove(item);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
