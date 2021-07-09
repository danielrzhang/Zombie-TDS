package weapons;

import java.awt.Graphics;

import bullets.Bullet;
import bullets.SmallBullet;
import entity.MovingEntity;
import game.Animation;
import game.Assets;
import game.Handler;

public class Glock19 extends Gun {
		
	public Glock19(Handler handler) {
		super(handler);
		reload = new Animation(75, Assets.glock19Reload, 0);
		shoot = new Animation(15, Assets.glock19Shoot, 250);
		speed = 3.0f;
		name = "Glock 19";
		numberOfCurrentRounds = GLOCK19_ROUNDS;
		reloadSFX = Assets.glock19ReloadSFX;
		attackSFX = Assets.glock19ShotSFX;
		currentGunLength = 70;
		currentGunAngleAddition = 20;
	}
	
	@Override
	public void tick() {
		numberOfMagazines = handler.getPlayer().getInventory().getInventoryItems().get("Glock 19 Magazine");
	}
	
	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void shoot() {
		lastAttackTime = System.currentTimeMillis();
		
		getBulletData();		

		shoot.setAnimated(true);
		numberOfCurrentRounds -= 1;
		handler.getBulletManager().addBullet(new SmallBullet(handler, bulletX, bulletY, MovingEntity.SMALL_BULLET_WIDTH, MovingEntity.SMALL_BULLET_HEIGHT, shootingAngle, 16.7f, Bullet.GLOCK19_BULLET_SPEED));
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
			numberOfMagazines = handler.getPlayer().getInventory().getInventoryItems().put("Glock 19 Magazine", handler.getPlayer().getInventory().getInventoryItems().get("Glock 19 Magazine") - 1);
			numberOfCurrentRounds = GLOCK19_ROUNDS;
		}
	}

	@Override
	public int reloadMaxRounds() {
		return GLOCK19_ROUNDS;
	}
}