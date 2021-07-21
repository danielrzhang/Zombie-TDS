package weapons;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import bullets.Bullet;
import bullets.MediumBullet;
import entityMoving.MovingEntity;
import game.Animation;
import game.Assets;
import game.Handler;

public class M16 extends Gun {
	
	private boolean semiAuto;
	
	public M16(Handler handler) {
		super(handler);
		reload = new Animation(75, Assets.m16Reload, 0);
		shoot = new Animation(5, Assets.m16Shoot, 100);
		speed = 3.0f;
		name = "M16";
		numberOfCurrentRounds = 30;
		reloadSFX = Assets.m16ReloadSFX;
		attackSFX = Assets.m16ShotSFX;
		currentGunLength = 100;
		currentGunAngleAddition = 11;
		semiAuto = true;
		texture = Assets.m16;
	}
	
	@Override
	public void tick() {
		numberOfMagazines = handler.getPlayer().getInventory().getInventoryItems().get("M16 Magazine");
		switchAuto();
	}
	
	@Override
	public void render(Graphics g) {
		
	}
	
	@Override
	public void shoot() {
		lastAttackTime = System.currentTimeMillis();
		
		getBulletData();

		shoot.setAnimated(true);
		handler.getBulletManager().addBullet(new MediumBullet(handler, bulletX, bulletY, MovingEntity.MEDIUM_BULLET_WIDTH, MovingEntity.MEDIUM_BULLET_HEIGHT, shootingAngle, 10f, Bullet.M16_BULLET_SPEED));
		numberOfCurrentRounds -= 1;
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
			numberOfMagazines = handler.getPlayer().getInventory().getInventoryItems().put("M16 Magazine", handler.getPlayer().getInventory().getInventoryItems().get("M16 Magazine") - 1);
			numberOfCurrentRounds = M16_ROUNDS;
		}
	}
	
	public void switchAuto() {
		if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON3) && handler.getWeaponsManager().getCurrentWeapon().getName().equals("M16")) {
			semiAuto = !semiAuto;
		}
	}

	@Override
	public int reloadMaxRounds() {
		return M16_ROUNDS;
	}
	
	public void setSemiAuto(boolean semiAuto) {
		this.semiAuto = semiAuto;
	}
	
	public boolean isSemiAuto() {
		return semiAuto;
	}
}
