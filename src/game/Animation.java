package game;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index;
	private long lastTime, timer, cooldown;
	private BufferedImage[] frames;
	private boolean isAnimated;

	public Animation(int speed, BufferedImage[] frames, long cooldown) {
		this.speed = speed;
		this.frames = frames;
		this.cooldown = cooldown;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		isAnimated = false;
	}

	public void tick() {
		if (!isAnimated) {
			index = 0;
		} else {
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
	
			if (timer > speed) {
				index++;
				timer = 0;
	
				if (index >= frames.length) {
					isAnimated = false;
					index = 0;
				}
			}
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	public int getNumFrames() {
		return frames.length;
	}

	public boolean isAnimated() {
		return isAnimated;
	}

	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
	}
	
	public void setCooldown(long cooldown) {
		this.cooldown = cooldown;
	}
	
	public long getCooldown() {
		return cooldown;
	}
	
	public BufferedImage getDefaultFrame() {
		return frames[0];
	}
}
