package weapons;

import bullets.Bullet;
import bullets.SmallBullet;
import entity.MovingEntity;
import game.Animation;
import game.Assets;
import game.Handler;

public class Remington870 extends Gun {
	
	public Remington870(Handler handler) {
		super(handler);
		reload = new Animation(75, Assets.remington870Reload, 0);
		shoot = new Animation(75, Assets.remington870Shoot, 1000);
		speed = 3.0f;
		name = "Remington 870";
		numberOfMagazines = 2;
		numberOfCurrentRounds = REMINGTON870_ROUNDS;
		reloadSFX = Assets.remington870ReloadSFX;
		attackSFX = Assets.remington870ShotSFX;
		currentGunLength = 100;
		currentGunAngleAddition = 11;
	}
	
	@Override
	public void tick() {
		numberOfMagazines = handler.getPlayer().getInventory().getInventoryItems().get("Remington 870 Shells");
	}
	
	@Override
	public void shoot() {
		lastAttackTime = System.currentTimeMillis();
		
		getBulletData();

		shoot.setAnimated(true);
		numberOfCurrentRounds -= 1;
		for (int i = -5; i < 5; i++) {
			handler.getBulletManager().addBullet(new SmallBullet(handler, bulletX, bulletY, MovingEntity.SMALL_BULLET_WIDTH, MovingEntity.SMALL_BULLET_HEIGHT, (float) (shootingAngle - Math.toRadians(2) + Math.toRadians(i * 4)), 10f, Bullet.REMINGTON870_BULLET_SPEED));
		}
		attackSFX.play();
		
		if (shoot.getIndex() == shoot.getNumFrames() - 1) {
			shoot.setAnimated(false);
		}
	}

	@Override
	public void reload() {
		lastReloadTime = System.currentTimeMillis();
		reload.setAnimated(true);
		
		if (reload.getIndex() == 0) {
			reloadSFX.play();
		}
	}
	
	@Override
	public void updateHUD() {
		if (reload.getIndex() == reload.getNumFrames() - 1) {
			reload.setAnimated(false);
			numberOfMagazines = handler.getPlayer().getInventory().getInventoryItems().put("Remington 870 Shells", handler.getPlayer().getInventory().getInventoryItems().get("Remington 870 Shells") - 1);
			numberOfCurrentRounds = REMINGTON870_ROUNDS;
		}
	}

	@Override
	public int reloadMaxRounds() {
		return REMINGTON870_ROUNDS;
	}
}
