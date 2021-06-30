package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys, justPressed, cantPress;
	private boolean up, down, left, right, reload;

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			} else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}

			if (!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		reload = keys[KeyEvent.VK_R];
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length) {
			return false;
		}
		return justPressed[keyCode];
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}
	
	public boolean isKey1() {
		return keyJustPressed(KeyEvent.VK_1);
	}
	
	public boolean isKey2() {
		return keyJustPressed(KeyEvent.VK_2);
	}
	
	public boolean isKey3() {
		return keyJustPressed(KeyEvent.VK_3);
	}
	
	public boolean isKey4() {
		return keyJustPressed(KeyEvent.VK_4);
	}
	
	public boolean isKey5() {
		return keyJustPressed(KeyEvent.VK_5);
	}
	
	public boolean isKeyE() {
		return keyJustPressed(KeyEvent.VK_E);
	}
	
	public boolean isKeyR() {
		return keyJustPressed(KeyEvent.VK_R);
	}
}
