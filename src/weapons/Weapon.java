package weapons;

import game.Handler;
import game.Sound;
import usable.Usable;

public abstract class Weapon extends Usable {

	protected float speed;
	protected String weaponType;
	protected long lastAttackTime;
	protected Sound attackSFX;
			
	public Weapon(Handler handler) {
		super(handler);
		lastAttackTime = System.currentTimeMillis();
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	
	public String getWeaponType() {
		return weaponType;
	}
	
	public long getLastAttackTime() {
		return lastAttackTime;
	}

	public void setLastAttackTime(long lastAttackTime) {
		this.lastAttackTime = lastAttackTime;
	}
	
	public void setAttackSFX(Sound attackSFX) {
		this.attackSFX = attackSFX;
	}
	
	public Sound getAttackSFX() {
		return attackSFX;
	}
}
