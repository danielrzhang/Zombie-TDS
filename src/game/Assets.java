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
	private static final int zombieWidth = 330, zombieHeight = 300;
	private static final int buttonWidth = 514, buttonHeight = 66;
	private static final int coinWidth = 52, coinHeight = 53;
	private static final int weaponsWidth = 150, weaponsHeight = 150;
	private static final int imageCounterWidth = 75, imageCounterHeight = 100;
	
	public static BufferedImage grass, metal, stone, water, crate, brick, tree, shrub, smallBullet, mediumBullet, largeBullet, 
								 kabar, glock19, remington870, m16, medkit, glock19Magazine, m16Magazine, scatteredBullets, 
								 shells;
	public static BufferedImage[] kabarAttack, glock19Shoot, glock19Reload, remington870Shoot, remington870Reload, m16Shoot, m16Reload, zombie, 
									helpButton, restartButton, returnButton, settingsButton, startButton, backButton, plusButton, 
									coin;
	public static Font defaultZombieFont, tinyZombieFont, miniZombieFont, smallZombieFont, regularZombieFont, largeZombieFont;
	
	public static Sound glock19ReloadSFX, glock19ShotSFX, remington870ReloadSFX, remington870ShotSFX, m16ReloadSFX, m16ShotSFX, 
						playerHurt1SFX, gunSwitchSFX, knifeSlashSFX, bloodSplatterSFX;
	
	public static void init() {
		// SpriteSheet initializations
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		SpriteSheet kabarSheet = new SpriteSheet(ImageLoader.loadImage("/textures/KABARAttack.png"));
		SpriteSheet glock19ShootSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ShootGlock19.png"));
		SpriteSheet glock19ReloadSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ReloadGlock19.png"));
		SpriteSheet remington870ShootSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ShootRemington870.png"));
		SpriteSheet remington870ReloadSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ReloadRemington870.png"));
		SpriteSheet m16ShootSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ShootM16.png"));
		SpriteSheet m16ReloadSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ReloadM16.png"));
		SpriteSheet zombieSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ZombieAttack.png"));
		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ButtonSheet.png"));
		SpriteSheet coinSheet = new SpriteSheet(ImageLoader.loadImage("/textures/CoinSheet.png"));
		SpriteSheet weaponsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons.png"));
		SpriteSheet imageCounterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ImageCounter.png"));
		
		// BufferedImage array initializations
		
		// In-game
		kabarAttack = new BufferedImage[11];
		glock19Shoot = new BufferedImage[6];
		glock19Reload = new BufferedImage[15];
		remington870Shoot = new BufferedImage[10];
		remington870Reload = new BufferedImage[20];
		m16Shoot = new BufferedImage[6];
		m16Reload = new BufferedImage[20];
		zombie = new BufferedImage[9];
		
		// Items
		coin = new BufferedImage[8];
		
		// Buttons
		helpButton = new BufferedImage[2];
		restartButton = new BufferedImage[2];
		returnButton = new BufferedImage[2];
		settingsButton = new BufferedImage[2];
		startButton = new BufferedImage[2];
		backButton = new BufferedImage[2];
		plusButton = new BufferedImage[2];
		
		// BufferedImage array animation setting
		
		for (int i = 0; i < kabarAttack.length; i++) {
			kabarAttack[i] = kabarSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
				
		for (int i = 0; i < glock19Shoot.length; i++) {
			glock19Shoot[i] = glock19ShootSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < glock19Reload.length; i++) {
			glock19Reload[i] = glock19ReloadSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < remington870Shoot.length; i++) {
			remington870Shoot[i] = remington870ShootSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < remington870Reload.length; i++) {
			remington870Reload[i] = remington870ReloadSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < m16Shoot.length; i++) {
			m16Shoot[i] = m16ShootSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < m16Reload.length; i++) {
			m16Reload[i] = m16ReloadSheet.crop(playerWidth * i, 0, playerWidth, playerHeight);
		}
		
		for (int i = 0; i < zombie.length; i++) {
			zombie[i] = zombieSheet.crop(zombieWidth * i, 0, zombieWidth, zombieHeight);
		}
		
		// Items
		for (int i = 0; i < coin.length; i++) {
			coin[i] = coinSheet.crop(coinWidth * i, 0, coinWidth, coinHeight);
		}	
		
		glock19Magazine = imageCounterSheet.crop(0, 0, imageCounterWidth, imageCounterHeight);
		m16Magazine = imageCounterSheet.crop(imageCounterWidth, 0, imageCounterWidth, imageCounterHeight);
		shells = imageCounterSheet.crop(imageCounterWidth * 2, 0, imageCounterWidth + 25, imageCounterHeight);
		scatteredBullets = imageCounterSheet.crop(imageCounterWidth * 3 + 25, 0, imageCounterWidth + 25, imageCounterHeight);
		medkit = imageCounterSheet.crop(imageCounterWidth * 4 + 50, 0, imageCounterWidth + 25, imageCounterHeight);
				
		// BufferedImage array button loading
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
		
		// Bullet image loading
		smallBullet = ImageLoader.loadImage("/textures/BulletSmall.png");
		mediumBullet = ImageLoader.loadImage("/textures/BulletMedium.png");
		largeBullet = ImageLoader.loadImage("/textures/BulletLarge.png");

		// Gun Image loading
		kabar = weaponsSheet.crop(0, 0, weaponsWidth, weaponsHeight);
		glock19 = weaponsSheet.crop(weaponsWidth, 0, weaponsWidth, weaponsHeight);
		remington870 = weaponsSheet.crop(weaponsWidth * 2, 0, weaponsWidth, weaponsHeight);
		m16 = weaponsSheet.crop(weaponsWidth * 3, 0, weaponsWidth, weaponsHeight);
		
		// Tile sheet loading
		grass = tileSheet.crop(0, 0, tileWidth, tileHeight);
		metal = tileSheet.crop(tileWidth, 0, tileWidth, tileHeight);
		stone = tileSheet.crop(tileWidth * 2, 0, tileWidth, tileHeight);
		water = tileSheet.crop(tileWidth * 3, 0, tileWidth, tileHeight);
		crate = tileSheet.crop(tileWidth * 4, 0, tileWidth, tileHeight);
		brick = tileSheet.crop(tileWidth * 5, 0, tileWidth, tileHeight);
		tree = tileSheet.crop(tileWidth * 6, 0, tileWidth, tileHeight);
		shrub = tileSheet.crop(tileWidth * 7, 0, tileWidth, tileHeight);
		
		// Font loading
		try {
			defaultZombieFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/ZombieFont.ttf"));
			tinyZombieFont = defaultZombieFont.deriveFont(15f);
			miniZombieFont = defaultZombieFont.deriveFont(25f);
			smallZombieFont = defaultZombieFont.deriveFont(30f);
			regularZombieFont = defaultZombieFont.deriveFont(40f);
			largeZombieFont = defaultZombieFont.deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/ZombieFont.ttf")));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		// Audio initialization
//		glock19ReloadSFX = new Sound("res/audio/Glock19Reload.wav", -30);
//		glock19ShotSFX = new Sound("res/audio/Glock19Shot.wav", -20);
//		remington870ReloadSFX = new Sound("res/audio/Remington870Reload.wav", -30);
//		remington870ShotSFX = new Sound("res/audio/Remington870Shot.wav", -25);
//		m16ReloadSFX = new Sound("res/audio/M16Reload.wav", -30);
//		m16ShotSFX = new Sound("res/audio/M16Shot.wav", -20);
//		playerHurt1SFX = new Sound("res/audio/PlayerHurt1.wav", -80);
//		gunSwitchSFX = new Sound("res/audio/GunSwitch.wav", -10);
//		knifeSlashSFX = new Sound("res/audio/KnifeSlash.wav", -20);
//		bloodSplatterSFX = new Sound("res/audio/BloodSplatter.wav", -20);
		
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