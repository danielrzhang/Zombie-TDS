package game;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

	private static final int playerWidth = 360, playerHeight = 220;
	private static final int tileWidth = 128, tileHeight = 128;
	private static final int buttonWidth = 514, buttonHeight = 66;
	private static final int itemWidth = 150, itemHeight = 150;

	// Tiles
	public static BufferedImage grass, metal, stone, water, crate, brick, tree, shrub;

	// Bullets
	public static BufferedImage smallBullet, mediumBullet, largeBullet;

	// Weapons
	public static BufferedImage kabar, glock19, remington870, m16, landmine;

	// Items
	public static BufferedImage coin, medkit, glock19Magazine, shells, m16Magazine, scatteredBullets;

	// Inventory
	public static BufferedImage inventory;

	// Item animations
	public static BufferedImage[] coinAnimation, landmineAnimation;

	// Player animations
	public static BufferedImage[] kabarAttack, glock19Shoot, glock19Reload, remington870Shoot, remington870Reload, m16Shoot, 
									m16Reload, landmineAttack;

	// Zombie animations
	public static BufferedImage[] zombieAttack, zombieWalk;

	// UI Button animations
	public static BufferedImage[] helpButton, restartButton, returnButton, settingsButton, startButton, backButton, plusButton;

	// Fonts
	public static Font defaultZombieFont, tinyZombieFont, miniZombieFont, smallZombieFont, regularZombieFont, largeZombieFont;

	// Sound effects
	public static Sound glock19ReloadSFX, glock19ShotSFX, remington870ReloadSFX, remington870ShotSFX, m16ReloadSFX, m16ShotSFX, 
	playerHurt1SFX, gunSwitchSFX, knifeSlashSFX, bloodSplatterSFX;

	public static void init() {
		
		/************* Spritesheet *************/
		
		SpriteSheet masterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/AnimationSheet.png"));
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ButtonSheet.png"));
		SpriteSheet coinSheet = new SpriteSheet(ImageLoader.loadImage("/textures/CoinSheet.png"));
		SpriteSheet weaponsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons.png"));
		SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Items.png"));
		SpriteSheet landmineSheet = new SpriteSheet(ImageLoader.loadImage("/textures/LandmineSheet.png"));

		/************* Array initializations *************/

		// Player animations
		kabarAttack = new BufferedImage[11];
		glock19Shoot = new BufferedImage[6];
		glock19Reload = new BufferedImage[15];
		remington870Shoot = new BufferedImage[10];
		remington870Reload = new BufferedImage[20];
		m16Shoot = new BufferedImage[6];
		m16Reload = new BufferedImage[20];
		landmineAttack = new BufferedImage[15];

		// Zombie animations
		zombieWalk = new BufferedImage[17];
		zombieAttack = new BufferedImage[9];

		// UI Buttons
		helpButton = new BufferedImage[2];
		restartButton = new BufferedImage[2];
		returnButton = new BufferedImage[2];
		settingsButton = new BufferedImage[2];
		startButton = new BufferedImage[2];
		backButton = new BufferedImage[2];
		plusButton = new BufferedImage[2];

		// Coin animation
		coinAnimation = new BufferedImage[10];

		// Landmine animation
		landmineAnimation = new BufferedImage[3];

		/************* Variable value assignment *************/

		// Tiles
		grass = tileSheet.crop(0, 0, tileWidth, tileHeight);
		metal = tileSheet.crop(tileWidth, 0, tileWidth, tileHeight);
		stone = tileSheet.crop(tileWidth * 2, 0, tileWidth, tileHeight);
		water = tileSheet.crop(tileWidth * 3, 0, tileWidth, tileHeight);
		crate = tileSheet.crop(tileWidth * 4, 0, tileWidth, tileHeight);
		brick = tileSheet.crop(tileWidth * 5, 0, tileWidth, tileHeight);
		tree = tileSheet.crop(tileWidth * 6, 0, tileWidth, tileHeight);
		shrub = tileSheet.crop(tileWidth * 7, 0, tileWidth, tileHeight);

		// Bullets
		smallBullet = ImageLoader.loadImage("/textures/BulletSmall.png");
		mediumBullet = ImageLoader.loadImage("/textures/BulletMedium.png");
		largeBullet = ImageLoader.loadImage("/textures/BulletLarge.png");

		// Weapons
		kabar = weaponsSheet.crop(0, 0, itemWidth, itemHeight);
		glock19 = weaponsSheet.crop(itemWidth, 0, itemWidth, itemHeight);
		remington870 = weaponsSheet.crop(itemWidth * 2, 0, itemWidth, itemHeight);
		m16 = weaponsSheet.crop(itemWidth * 3, 0, itemWidth, itemHeight);
		landmine = weaponsSheet.crop(itemWidth * 4, 0, itemWidth, itemHeight);

		// Items
		coin = itemSheet.crop(0, 0, itemWidth, itemHeight);
		medkit = itemSheet.crop(itemWidth, 0, itemWidth, itemHeight);
		glock19Magazine = itemSheet.crop(itemWidth * 2, 0, itemWidth, itemHeight);
		shells = itemSheet.crop(itemWidth * 3, 0, itemWidth, itemHeight);
		m16Magazine = itemSheet.crop(itemWidth * 4, 0, itemWidth, itemHeight);
		scatteredBullets = itemSheet.crop(itemWidth * 6, 0, itemWidth, itemHeight);

		// Inventory
		inventory = ImageLoader.loadImage("/textures/Inventory.png");

		/*** Item animations ***/
		
		// Coin animation
		for (int i = 0; i < coinAnimation.length; i++) {
			coinAnimation[i] = coinSheet.crop(itemWidth * i, 0, itemWidth, itemHeight);
		}
		
		// Landmine animation
		for (int i = 0; i < landmineAnimation.length; i++) {
			landmineAnimation[i] = landmineSheet.crop(itemWidth * i, 0, itemWidth, itemHeight);
		}

		// Player animations
		for (int i = 0; i < kabarAttack.length; i++) {
			kabarAttack[i] = masterSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}

		for (int i = 0; i < glock19Shoot.length; i++) {
			glock19Shoot[i] = masterSheet.crop(playerWidth * i, playerHeight, playerWidth, playerHeight);
		}

		for (int i = 0; i < glock19Reload.length; i++) {
			glock19Reload[i] = masterSheet.crop(playerWidth * i, playerHeight * 2, playerWidth, playerHeight);
		}

		for (int i = 0; i < remington870Shoot.length; i++) {
			remington870Shoot[i] = masterSheet.crop(playerWidth * i, playerHeight * 3, playerWidth, playerHeight);
		}

		for (int i = 0; i < remington870Reload.length; i++) {
			remington870Reload[i] = masterSheet.crop(playerWidth * i, playerHeight * 4, playerWidth, playerHeight);
		}

		for (int i = 0; i < m16Shoot.length; i++) {
			m16Shoot[i] = masterSheet.crop(playerWidth * i, playerHeight * 5, playerWidth, playerHeight);
		}

		for (int i = 0; i < m16Reload.length; i++) {
			m16Reload[i] = masterSheet.crop(playerWidth * i, playerHeight * 6, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < landmineAttack.length; i++) {
			landmineAttack[i] = masterSheet.crop(playerWidth * i, playerHeight * 7, playerWidth, playerHeight);
		}

		// Zombie animations
		for (int i = 0; i < zombieWalk.length; i++) {
			zombieWalk[i] = masterSheet.crop(playerWidth * i, playerHeight * 8, playerWidth, playerHeight);
		}

		for (int i = 0; i < zombieAttack.length; i++) {
			zombieAttack[i] = masterSheet.crop(playerWidth * i, playerHeight * 9, playerWidth, playerHeight);
		}

		// UI Button animations
		helpButton[0] = buttonSheet.crop(0, 0, buttonWidth, buttonHeight);
		helpButton[1] = buttonSheet.crop(buttonWidth, 0, buttonWidth, buttonHeight);
		restartButton[0] = buttonSheet.crop(buttonWidth * 2, 0, buttonWidth, buttonHeight);
		restartButton[1] = buttonSheet.crop(buttonWidth * 3, 0, buttonWidth, buttonHeight);
		returnButton[0] = buttonSheet.crop(buttonWidth * 4, 0, buttonWidth, buttonHeight);
		returnButton[1] = buttonSheet.crop(buttonWidth * 5, 0, buttonWidth, buttonHeight);
		settingsButton[0] = buttonSheet.crop(buttonWidth * 6, 0, buttonWidth, buttonHeight);
		settingsButton[1] = buttonSheet.crop(buttonWidth * 7, 0, buttonWidth, buttonHeight);
		startButton[0] = buttonSheet.crop(buttonWidth * 8, 0, buttonWidth, buttonHeight);
		startButton[1] = buttonSheet.crop(buttonWidth * 9, 0, buttonWidth, buttonHeight);
		backButton[0] = buttonSheet.crop(buttonWidth * 10, 0, buttonHeight * 2, buttonHeight);
		backButton[1] = buttonSheet.crop(buttonWidth * 10 + buttonHeight * 2, 0, buttonHeight * 2, buttonHeight);
		plusButton[0] = buttonSheet.crop(buttonWidth * 10 + buttonHeight * 4, 0, buttonHeight, buttonHeight);
		plusButton[1] = buttonSheet.crop(buttonWidth * 10 + buttonHeight * 5, 0, buttonHeight, buttonHeight);

		// Fonts
		try {
			defaultZombieFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/ZombieFont.ttf"));
			tinyZombieFont = defaultZombieFont.deriveFont(20f);
			miniZombieFont = defaultZombieFont.deriveFont(25f);
			smallZombieFont = defaultZombieFont.deriveFont(30f);
			regularZombieFont = defaultZombieFont.deriveFont(40f);
			largeZombieFont = defaultZombieFont.deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/ZombieFont.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		// Sound effects
		//			glock19ReloadSFX = new Sound("res/audio/Glock19Reload.wav", -30);
		//			glock19ShotSFX = new Sound("res/audio/Glock19Shot.wav", -20);
		//			remington870ReloadSFX = new Sound("res/audio/Remington870Reload.wav", -30);
		//			remington870ShotSFX = new Sound("res/audio/Remington870Shot.wav", -25);
		//			m16ReloadSFX = new Sound("res/audio/M16Reload.wav", -30);
		//			m16ShotSFX = new Sound("res/audio/M16Shot.wav", -20);
		//			playerHurt1SFX = new Sound("res/audio/PlayerHurt1.wav", -80);
		//			gunSwitchSFX = new Sound("res/audio/GunSwitch.wav", -10);
		//			knifeSlashSFX = new Sound("res/audio/KnifeSlash.wav", -20);
		//			bloodSplatterSFX = new Sound("res/audio/BloodSplatter.wav", -20);

		// Temporary Mute
		glock19ReloadSFX = new Sound("res/audio/Glock19Reload.wav", -80);
		glock19ShotSFX = new Sound("res/audio/Glock19Shot.wav", -80);
		remington870ReloadSFX = new Sound("res/audio/Remington870Reload.wav", -80);
		remington870ShotSFX = new Sound("res/audio/Remington870Shot.wav", -80);
		m16ReloadSFX = new Sound("res/audio/M16Reload.wav", -80);
		m16ShotSFX = new Sound("res/audio/M16Shot.wav", -80);
		playerHurt1SFX = new Sound("res/audio/PlayerHurt1.wav", -80);
		gunSwitchSFX = new Sound("res/audio/GunSwitch.wav", -80);
		knifeSlashSFX = new Sound("res/audio/KnifeSlash.wav", -80);
		bloodSplatterSFX = new Sound("res/audio/BloodSplatter.wav", -80);
	}
}