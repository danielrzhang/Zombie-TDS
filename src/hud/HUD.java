package hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class HUD {

	private Handler handler;
	private HotbarBoxHUD[] hotbar;
	private int magazineTextX, bulletTextWidth, counterY, equippedWidth;
	private BufferedImage magazineToDisplay;
	private Color notSelectedColor;
	private long reloadMessageFlash;
	private boolean displayReloadMessage, active;

	public HUD(Handler handler) {
		this.handler = handler;
		hotbar = new HotbarBoxHUD[5];
		
//		for (int i = 300; i )

		hotbar[0] = new HotbarBoxHUD(Assets.kabar, 360, 520, 75, "1");
		hotbar[1] = new HotbarBoxHUD(Assets.glock19, 460, 520, 75, "2");
		hotbar[2] = new HotbarBoxHUD(Assets.remington870, 560, 520, 75, "3");
		hotbar[3] = new HotbarBoxHUD(Assets.m16, 660, 520, 75, "4");
		hotbar[4] = new HotbarBoxHUD(Assets.medkit, 760, 520, 75, "5");
		hotbar[0].setCurrentlySelected(true);
		counterY = 580;	
		notSelectedColor = new Color(0, 0, 0, 127);
		reloadMessageFlash = System.currentTimeMillis();
		displayReloadMessage = true;
		active = true;
	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}
		
		if (handler.getWeaponsManager().getCurrentWeapon().getWeaponType().equals("Gun")) {
			// Magazine and Bullet Counter
			g.setFont(Assets.regularZombieFont);
			displayMagazineCounterOnScreen(g);
			displayRoundCounterOnScreen(g);	

			// Screen Message Display
			g.setFont(Assets.largeZombieFont);
			displayScreenMessage(g);
		}

		// Weapons Hotbar Display
		displayHotbar(g);
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

	public void displayHotbar(Graphics g) {
		if (handler.getKeyManager().isKey1()) {
			setSelected(0);
		} else if (handler.getKeyManager().isKey2()) {
			setSelected(1);
		} else if (handler.getKeyManager().isKey3()) {
			setSelected(2);
		} else if (handler.getKeyManager().isKey4()) {
			setSelected(3);
		}
		renderHotbar(g);
	}

	public void setSelected(int index) {
		hotbar[index].setCurrentlySelected(true);
		for (int i = 0; i < hotbar.length; i++) {
			if (i != index) {
				hotbar[i].setCurrentlySelected(false);
			}
		}
	}

	public void renderHotbar(Graphics g) {
		for (int i = 0; i < hotbar.length; i++) {
			hotbar[i].render(g);
		}
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