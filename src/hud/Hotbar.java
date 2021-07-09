package hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Assets;
import game.Handler;

public class Hotbar {

	private HotbarBox[] hotbarArray;
	private Handler handler;
	private Graphics2D g2d;
	private Color selectedColor;
	private BasicStroke stroke;
	private int xStart, y, sideLength, gap, currentHotbarIndex;

	public Hotbar(Handler handler) {
		this.handler = handler;
		selectedColor = Color.WHITE;
		stroke = new BasicStroke(2);
		xStart = 360;
		sideLength = 75;
		y = 520;
		gap = 100;

		hotbarArray = new HotbarBox[5];

		hotbarArray[0] = new HotbarBox(Assets.kabar, xStart, y, sideLength, "1");
		hotbarArray[1] = new HotbarBox(Assets.glock19, xStart + gap, y, sideLength, "2");
		hotbarArray[2] = new HotbarBox(Assets.remington870, xStart + gap * 2, y, sideLength, "3");
		hotbarArray[3] = new HotbarBox(Assets.m16, xStart + gap * 3, y, sideLength, "4");
		hotbarArray[4] = new HotbarBox(null, xStart + gap * 4, y, sideLength, "5");
	
		currentHotbarIndex = 0;
		hotbarArray[currentHotbarIndex].setSelected(true);
	}

	public void tick() {
		updateSelectedHotbar();
	}

	public void render(Graphics g) {
		renderHotbar(g);
	}
	
	public void updateSelectedHotbar() {
		updateSelected();

		if (handler.getKeyManager().isKey1()) {
			currentHotbarIndex = 0;
		} else if (handler.getKeyManager().isKey2()) {
			currentHotbarIndex = 1;
		} else if (handler.getKeyManager().isKey3()) {
			currentHotbarIndex = 2;
		} else if (handler.getKeyManager().isKey4()) {
			currentHotbarIndex = 3;
		} else if (handler.getKeyManager().isKey5()) {
			currentHotbarIndex = 4;
		}
	}
	
	public void updateSelected() {
		hotbarArray[currentHotbarIndex].setSelected(true);
		
		for (int i = 0; i < hotbarArray.length; i++) {
			if (i != currentHotbarIndex) {
				hotbarArray[i].setSelected(false);
			}
		}
	}
	
	public void renderHotbar(Graphics g) {
		for (int i = 0; i < hotbarArray.length; i++) {
			hotbarArray[i].render(g);
		}
		displaySelected(g);
	}

	public void displaySelected(Graphics g) {
		g2d = (Graphics2D) g;

		for (int i = 0; i < hotbarArray.length; i++) {
			if (hotbarArray[currentHotbarIndex].isSelected()) {
				g.setColor(selectedColor);
				g2d.setStroke(stroke);
				g2d.drawRect(xStart + gap * currentHotbarIndex, y, sideLength + 1, sideLength + 1);
			} 
		}
	}
}
