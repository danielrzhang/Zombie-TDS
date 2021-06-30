package weapons;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;
import usable.Usable;

public class WeaponsManager {

	private Handler handler;
	private Weapon currentWeapon;
	private Glock19 glock19;
	private Remington870 remington870;
	private M16 m16;
	private Gun currentGun;
	private MeleeWeapon currentMelee, kabar;
	private boolean active;

	public WeaponsManager(Handler handler) {
		this.handler = handler;
		kabar = new KABAR(handler);
		glock19 = new Glock19(handler);
		remington870 = new Remington870(handler);
		m16 = new M16(handler);

		currentGun = glock19;
		currentMelee = kabar;
		currentWeapon = kabar;
		active = true;
	}

	public void tick() {
		getWeaponsInput();
		currentGun.getReload().tick();
		currentGun.getShoot().tick();
		currentMelee.getAttack().tick();
		glock19.tick();
		remington870.tick();
		m16.tick();
		m16.switchAuto();
	}

	public BufferedImage getCurrentAnimationFrame() {
		if (currentWeapon.getWeaponType().equals("Gun")) {
			if ((handler.getMouseManager().isShoot() || currentGun.getShoot().isAnimated()) && !currentGun.getReload().isAnimated()) {
				return shoot();
			} else if ((handler.getKeyManager().isKeyR() || currentGun.getReload().isAnimated()) && !currentGun.getShoot().isAnimated()) {
				return reload();
			} else {
				return currentGun.getReload().getDefaultFrame();
			}
		} else {
			if (handler.getMouseManager().isShoot() || currentMelee.getAttack().isAnimated()) {
				return meleeAttack();
			} else {
				return currentMelee.getAttack().getDefaultFrame();
			}
		}
	}

	public void getWeaponsInput() {
		if ((handler.getKeyManager().isKey1() && !currentWeapon.getName().equals("KA-BAR")) || (handler.getKeyManager().isKey2() && !currentWeapon.getName().equals("Glock 19")) || (handler.getKeyManager().isKey3() && !currentWeapon.getName().equals("Remington 870")) || (handler.getKeyManager().isKey4() && !currentWeapon.getName().equals("M16"))) {
			currentGun.getReload().setAnimated(false);
			currentGun.getShoot().setAnimated(false);
			currentMelee.getAttack().setAnimated(false);
			Assets.gunSwitchSFX.play();

			if (handler.getKeyManager().isKey1()) {
				currentWeapon = currentMelee = kabar;
				currentWeapon.setName(kabar.getName());
			} else if (handler.getKeyManager().isKey2()) {
				currentWeapon = currentGun = glock19;
				currentWeapon.setName(glock19.getName());
			} else if (handler.getKeyManager().isKey3()) {
				currentWeapon = currentGun = remington870;
				currentWeapon.setName(remington870.getName());
			} else if (handler.getKeyManager().isKey4()) {
				currentWeapon = currentGun = m16;
				currentWeapon.setName(m16.getName());
			}
		}
	}

	public BufferedImage shoot() {		
		if (System.currentTimeMillis() - currentGun.getLastAttackTime() >= currentGun.getShoot().getCooldown() && currentGun.getNumberOfCurrentRounds() > 0) {
			if (currentGun.getName().equals("M16") && ((m16.isSemiAuto() && handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) || (!m16.isSemiAuto() && handler.getMouseManager().isShoot()))) {
				currentGun.shoot();
			} else if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1)) {
				currentGun.shoot();
			}
		}
		return currentGun.getShoot().getCurrentFrame();
	}

	public BufferedImage reload() {
		if (handler.getKeyManager().isKeyR() && System.currentTimeMillis() - currentGun.getLastReloadTime() >= currentGun.getReload().getCooldown() && currentWeapon.getWeaponType().equals("Gun")  && currentGun.getNumberOfCurrentRounds() == 0 && currentGun.getNumberOfMagazines() > 0) {
			currentGun.reload();
		}
		currentGun.updateHUD();
		return currentGun.getReload().getCurrentFrame();
	}

	public BufferedImage meleeAttack() {
		if (handler.getMouseManager().buttonJustPressed(MouseEvent.BUTTON1) && System.currentTimeMillis() - currentMelee.getLastAttackTime() >= currentMelee.getAttack().getCooldown()) {
			currentMelee.attack();
		}
		return currentMelee.getAttack().getCurrentFrame();
	}

	public Glock19 getGlock19() {
		return glock19;
	}

	public Remington870 getRemington870() {
		return remington870;
	}

	public M16 getM16() {
		return m16;
	}

	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public Gun getCurrentGun() {
		return currentGun;
	}

	public MeleeWeapon getCurrentMelee() {
		return currentMelee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public MeleeWeapon getKabar() {
		return kabar;
	}

	public void setKabar(MeleeWeapon kabar) {
		this.kabar = kabar;
	}

	public void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	public void setGlock19(Glock19 glock19) {
		this.glock19 = glock19;
	}

	public void setRemington870(Remington870 remington870) {
		this.remington870 = remington870;
	}

	public void setM16(M16 m16) {
		this.m16 = m16;
	}

	public void setCurrentGun(Gun currentGun) {
		this.currentGun = currentGun;
	}

	public void setCurrentMelee(MeleeWeapon currentMelee) {
		this.currentMelee = currentMelee;
	}
}
