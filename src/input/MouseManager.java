package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean[] buttons, justPressed, cantPress;
	private boolean shoot, leftPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;

	public MouseManager() {
		buttons = new boolean[256];
		justPressed = new boolean[buttons.length];
		cantPress = new boolean[buttons.length];
	}
	
	public void tick() {
		for (int i = 0; i < buttons.length; i++) {
			if (cantPress[i] && !buttons[i]) {
				cantPress[i] = false;
			} else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}

			if (!cantPress[i] && buttons[i]) {
				justPressed[i] = true;
			}
		}
		
		shoot = buttons[MouseEvent.BUTTON1];
		leftPressed = buttons[MouseEvent.BUTTON1];
	}
	
	public boolean buttonJustPressed(int keyCode){
//		if(keyCode < 0 || keyCode >= buttons.length) {
//			return false;
//		}
		return justPressed[keyCode];
	}

	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
		
		if (uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if (uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
	
	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	public UIManager getUIManager() {
		return uiManager;
	}
}
