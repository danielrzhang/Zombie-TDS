package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.Handler;

public class UIManager {
	
	private Handler handler;
	private ArrayList<UIObject> uiObjects;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		this.uiObjects = new ArrayList<UIObject>();
	}

	public void tick() {
		for (int i = 0; i < uiObjects.size(); i++) {
			UIObject u = uiObjects.get(i);
			u.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < uiObjects.size(); i++) {
			UIObject u = uiObjects.get(i);
			u.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for (int i = 0; i < uiObjects.size(); i++) {
			UIObject u = uiObjects.get(i);
			u.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for (int i = 0; i < uiObjects.size(); i++) {
			UIObject u = uiObjects.get(i);
			u.onMouseRelease(e);
		}
	}
	
	public void addUIObject(UIObject u) {
		uiObjects.add(u);
	}
	
	public void removeUIObject(UIObject u) {
		uiObjects.remove(u);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getUiObjects() {
		return uiObjects;
	}

	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}
}
