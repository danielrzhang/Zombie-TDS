package hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class HUD {

	private Handler handler;
	private int magazineTextX, bulletTextWidth, counterY, equippedWidth;
	private BufferedImage magazineToDisplay;
	private Color notSelectedColor;
	private long reloadMessageFlash;
	private boolean displayReloadMessage, active;
	private Hotbar hotbar;

	public HUD(Handler handler) {
		this.handler = handler;
		counterY = 580;	
		notSelectedColor = new Color(0, 0, 0, 127);
		reloadMessageFlash = System.currentTimeMillis();
		displayReloadMessage = true;
		active = true;
		hotbar = new Hotbar(handler);
	}
	
	public void tick() {
		hotbar.tick();
	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}
		displayGunHUD(g);
		hotbar.render(g);
	}
	
	public void displayGunHUD(Graphics g) {
		if (handler.getWeaponsManager().getCurrentWeapon().getWeaponType().equals("Gun")) {
			// Magazine and Bullet Counter
			g.setFont(Assets.regularZombieFont);
			displayMagazineCounterOnScreen(g);
			displayRoundCounterOnScreen(g);	

			// Screen Message Display
			g.setFont(Assets.largeZombieFont);
			displayScreenMessage(g);
		}
		displayEquippedWeapon(g);
	}

	public void displayMagazineCounterOnScreen(Graphics g) {
		if (handler.getWeaponsManager().getCurrentGun().getNumberOfMagazines() == 0) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}

		magazineTextX = 1000 - (20 * (int) Math.floor(Math.log10(handler.getWeaponsManager().getCurrentGun().getNumberOfMagazines())));
		bulletTextWidth = g.getFontMetrics(Assets.regularZombieFont).stringWidth(Integer.toString(handler.getWeaponsManager().getCurrentGun().getNumberOfCurrentRounds()));

		if (handler.getWeaponsManager().getCurrentGun().getName().equals("Glock 19")) {
			magazineToDisplay = Assets.glock19Magazine;
		} else if (handler.getWeaponsManager().getCurrentGun().getName().equals("Remington 870")) {
			magazineToDisplay = Assets.shells;
		} else if (handler.getWeaponsManager().getCurrentGun().getName().equals("M16")) {
			magazineToDisplay = Assets.m16Magazine;
		}
		g.drawString(Integer.toString(handler.getWeaponsManager().getCurrentGun().getNumberOfMagazines()), magazineTextX, counterY);
		g.drawImage(magazineToDisplay, magazineTextX - 60, counterY - 45, 50, 55, null);
	}

	public void displayRoundCounterOnScreen(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("|", 1040, counterY);
		if (handler.getWeaponsManager().getCurrentGun().getNumberOfCurrentRounds() == 0) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
		g.drawString(Integer.toString(handler.getWeaponsManager().getCurrentGun().getNumberOfCurrentRounds()), 1065, counterY);
		g.drawImage(Assets.scatteredBullets, 1080 + bulletTextWidth, counterY - 40, 50, 50, null);
	}

	public void displayEquippedWeapon(Graphics g) {		
		equippedWidth = g.getFontMetrics(Assets.regularZombieFont).stringWidth("Equipped: " + handler.getWeaponsManager().getCurrentWeapon().getName());

		g.setColor(Color.WHITE);
		g.setFont(Assets.miniZombieFont);
		g.drawString("Equipped: " + handler.getWeaponsManager().getCurrentWeapon().getName(), 180 - equippedWidth / 3, 550);

		if (handler.getWeaponsManager().getCurrentWeapon().getName().equals("M16")) {
			if (handler.getWeaponsManager().getM16().isSemiAuto()) {
				g.setColor(Color.WHITE);
				g.drawString("Semi-Auto", 40, 585);
				g.drawString("|", 170, 585); 
				g.setColor(notSelectedColor);
				g.drawString("Full Auto", 190, 585);
			} else {
				g.setColor(notSelectedColor);
				g.drawString("Semi-Auto", 40, 585);
				g.setColor(Color.WHITE);
				g.drawString("|", 170, 585);
				g.drawString("Full Auto", 190, 585);
			}
		}
	}

	public void displayScreenMessage(Graphics g) {
		if (handler.getWeaponsManager().getCurrentGun().getNumberOfCurrentRounds() == 0 && !handler.getWeaponsManager().getCurrentGun().getName().equals("KABAR")) {
			if (handler.getWeaponsManager().getCurrentGun().getNumberOfMagazines() == 0 && !handler.getWeaponsManager().getCurrentGun().getReload().isAnimated() && !handler.getWeaponsManager().getCurrentGun().getName().equals("KABAR")) {
				displayNoAmmoMessage(g);
			} else if (handler.getWeaponsManager().getCurrentGun().getNumberOfMagazines() != 0 && !handler.getWeaponsManager().getCurrentGun().getReload().isAnimated() && !handler.getWeaponsManager().getCurrentGun().getName().equals("KABAR")) {
				displayReloadMessage(g);
			}
		}
	}

	public void displayReloadMessage(Graphics g) {
		if (System.currentTimeMillis() - reloadMessageFlash >= 500) {
			reloadMessageFlash = System.currentTimeMillis();
			displayReloadMessage = !displayReloadMessage;
		}

		g.setColor(Color.WHITE);
		g.setFont(Assets.regularZombieFont);

		if (displayReloadMessage) {
			g.drawString("Press R to reload!", 430, 470);
		}
	}

	public void displayNoAmmoMessage(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(Assets.regularZombieFont);
		g.drawString("Out of ammo!", 480, 470);
	}

	public Handler getHandler() {
		return handler;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}