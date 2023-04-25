package weapons;

import game.Animation;
import game.Handler;
import game.Sound;

public abstract class Gun extends Weapon {

	protected int numberOfMagazines, numberOfCurrentRounds;
	protected Sound reloadSFX;
	protected Animation reload, shoot;
	public static int GLOCK19_ROUNDS = 15, REMINGTON870_ROUNDS = 8, M16_ROUNDS = 30;
	protected float shootingAngle, bulletX, bulletY, currentGunLength, currentGunAngleAddition;
	protected long lastReloadTime;
	
	public Gun(Handler handler) {
		super(handler);
		weaponType = "Gun";
		lastReloadTime = System.currentTimeMillis();
	}
	
	public abstract void tick();
		
	public abstract void shoot();
	
	public abstract void reload();
	
	public abstract void updateHUD();
	
	public abstract int reloadMaxRounds();
	
	public void getBulletData() {
		bulletX = handler.getPlayer().getGunPointX();
		bulletY = handler.getPlayer().getGunPointY();
		shootingAngle = handler.getPlayer().getShootingAngle();
	}
	
	public float getShootingAngle() {
		return shootingAngle;
	}

	public void setShootingAngle(float shootingAngle) {
		this.shootingAngle = shootingAngle;
	}
	
	public Animation getReload() {
		return reload;
	}
	
	public Animation getShoot() {
		return shoot;
	}
	
	public int getNumberOfMagazines() {
		return numberOfMagazines;
	}
	
	public void setNumberOfMagazines(int numberOfMagazines) {
		this.numberOfMagazines = numberOfMagazines;
	}
	
	public int getNumberOfCurrentRounds() {
		return numberOfCurrentRounds;
	}
	
	public void setNumberOfCurrentRounds(int numberOfCurrentRounds) {
		this.numberOfCurrentRounds = numberOfCurrentRounds;
	}
	
	public Sound getReloadSFX() {
		return reloadSFX;
	}

	public float getBulletX() {
		return bulletX;
	}

	public void setBulletX(float bulletX) {
		this.bulletX = bulletX;
	}

	public float getBulletY() {
		return bulletY;
	}

	public void setBulletY(float bulletY) {
		this.bulletY = bulletY;
	}

	public float getCurrentGunLength() {
		return currentGunLength;
	}

	public void setCurrentGunLength(float currentGunLength) {
		this.currentGunLength = currentGunLength;
	}

	public float getCurrentGunAngleAddition() {
		return currentGunAngleAddition;
	}

	public void setCurrentGunAngleAddition(float currentGunAngleAddition) {
		this.currentGunAngleAddition = currentGunAngleAddition;
	}

	public void setReloadSFX(Sound reloadSFX) {
		this.reloadSFX = reloadSFX;
	}

	public void setReload(Animation reload) {
		this.reload = reload;
	}

	public void setShoot(Animation shoot) {
		this.shoot = shoot;
	}

	public long getLastReloadTime() {
		return lastReloadTime;
	}

	public void setLastReloadTime(long lastReloadTime) {
		this.lastReloadTime = lastReloadTime;
	}
}
