package item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import game.Handler;

public class WorldItemManager<T> {
	
	private Handler handler;
	private ArrayList<WorldItem> worldItemList;
	private int count;
	
	public WorldItemManager(Handler handler) {
		this.handler = handler;
		worldItemList = new ArrayList<WorldItem>();
		count = worldItemList.size();
	}
	
	public void tick() {
		Iterator<WorldItem> iterator = worldItemList.iterator();
		while (iterator.hasNext()){
			WorldItem worldItem = iterator.next();
			worldItem.tick();

			if (worldItem.isPickedUp()) {
				iterator.remove();
			} 
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < worldItemList.size(); i++) {
			WorldItem worldItem = worldItemList.get(i);
			worldItem.render(g);
		}
	}
	
	public void addItem(WorldItem worldItem) {
		worldItemList.add(worldItem);
	}
	
	public void removeItem(WorldItem worldItem) {
		worldItemList.remove(worldItem);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<WorldItem> getWorldItemList() {
		return worldItemList;
	}

	public void setItemList(ArrayList<WorldItem> worldItemList) {
		this.worldItemList = worldItemList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
