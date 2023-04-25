package weapons;

import game.Animation;
import game.Handler;

public abstract class MeleeWeapon extends Weapon {

	protected Animation attack;
	
	public MeleeWeapon(Handler handler) {
		super(handler);		
		weaponType = "Melee Weapon";
	}
	
	public abstract void attack();
	
	public Animation getAttack() {
		return attack;
	}
}
