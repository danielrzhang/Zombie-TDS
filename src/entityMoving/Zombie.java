package entityMoving;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import game.Animation;
import game.Assets;
import game.Handler;
import health.EnemyHealthBar;
import item.Coin;
import item.Glock19Magazine;
import item.M16Magazine;
import item.Medkit;
import item.Remington870Shells;
import tiles.Tile;

public class Zombie extends MovingEntity {

	private float pivotX, pivotY, damageDealt;
	private long lastAttackTime;
	private Rectangle playerHitbox, zombieHitbox;
	private Point knifePoint;
	private Animation zombieAttack, zombieWalk;
	private EnemyHealthBar healthBar;
	private boolean capDrop;


	public Zombie(Handler handler, float x, float y, float width, float height, float movementAngle) {
		super(handler, x, y, width, height, movementAngle);

		hitbox.x = 40;
		hitbox.y = 25;
		hitbox.width = 80;
		hitbox.height = 80;

		health = DEFAULT_ZOMBIE_HEALTH;
//		speed = (float) ((Math.random()*(251 - 50) + 100) / 100.0);
		speed = 2;

		entityName = "Zombie";

		zombieAttack = new Animation(50, Assets.zombieAttack, 1000);
		zombieWalk = new Animation((int) speed * 25, Assets.zombieWalk, 0);

		damageDealt = 10;

		this.movementAngle = 0;

		lastAttackTime = 0;

		healthBar = new EnemyHealthBar(handler, this); 
		capDrop = false;
		knifePoint = new Point();
	}

	@Override
	public void tick() {
		updateHitboxes();
		getMovement();
		zombieMove();
		updateMovementAngle();
		hurtByKnife();
		zombieAttack.tick();
		zombieWalk.tick();
		healthBar.tick(this);
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		AffineTransform backup = g2d.getTransform();
		AffineTransform transform = new AffineTransform();		

		transform.rotate(movementAngle, pivotX, pivotY);

		g2d.transform(transform);
		g2d.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), 
				(int) width, (int) height, null);
		g2d.setColor(Color.RED);
		g2d.drawRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);

		//		g2d.setColor(Color.white);
		//		g2d.fillOval((int) pivotX - 5, (int) pivotY - 5, 10, 10);
		g2d.setTransform(backup);
		healthBar.render(g);
	}

	public BufferedImage getCurrentAnimationFrame() {
		if (collisionWithPlayer()) {
			return attack();
		} else {
			return walk();
		}
	}

	public BufferedImage attack() {
		if (System.currentTimeMillis() - lastAttackTime >= zombieAttack.getCooldown()) {
			lastAttackTime = System.currentTimeMillis();
			zombieAttack.setAnimated(true);	
			Assets.playerHurt1SFX.play();
		}

		if (zombieAttack.getIndex() == zombieAttack.getNumFrames() - 1) {
			zombieAttack.setAnimated(false);
			handler.getWorld().getPlayer().hurt(damageDealt);
		}
		return zombieAttack.getCurrentFrame();
	}

	public BufferedImage walk() {
		zombieWalk.setAnimated(true);
		return zombieWalk.getCurrentFrame();
	}

	public boolean collisionWithPlayer() {
		// Collision between player right side and zombie left side
		if (Math.abs(playerHitbox.x + playerHitbox.width - zombieHitbox.x) <= 3) {
			if (zombieHitbox.y <= playerHitbox.y + playerHitbox.height && zombieHitbox.y + zombieHitbox.height >= playerHitbox.y) {
				playerHitbox.x = (int) (zombieHitbox.x - playerHitbox.width);
				return true;
			}
		}

		// Collision between player left side and zombie right side
		else if (Math.abs(playerHitbox.x - (zombieHitbox.x + zombieHitbox.width)) <= 3) {
			if (zombieHitbox.y <= playerHitbox.y + playerHitbox.height && zombieHitbox.y + zombieHitbox.height >= playerHitbox.y) {
				playerHitbox.x = (int) (zombieHitbox.x + zombieHitbox.width);
				return true;
			}
		}

		// Collision between player top side and zombie bottom side
		else if (Math.abs(playerHitbox.y + playerHitbox.height - zombieHitbox.y) <= 3) {
			if (zombieHitbox.x <= playerHitbox.x + playerHitbox.width && zombieHitbox.x + zombieHitbox.width >= playerHitbox.x) {
				playerHitbox.y = (int) (zombieHitbox.y - playerHitbox.height);
				return true;
			}
		}

		// Collision between player bottom side and zombie top side
		else if (Math.abs(playerHitbox.y - (zombieHitbox.y + zombieHitbox.height)) <= 3) {
			if (zombieHitbox.x <= playerHitbox.x + playerHitbox.width && zombieHitbox.x + zombieHitbox.width >= playerHitbox.x) {
				playerHitbox.y = (int) (zombieHitbox.y + zombieHitbox.height);
				return true;
			}
		}
		return false;
	}

	public void updateHitboxes() {
		playerHitbox = handler.getPlayer().getHitbox();
		zombieHitbox = getHitbox();
		knifePoint.x = (int) handler.getPlayer().getKnifePointX();
		knifePoint.y = (int) handler.getPlayer().getKnifePointY();
	}

	public void getMovement() {
		xMove = (float) (speed * Math.cos(movementAngle));
		yMove = (float) (speed * Math.sin(movementAngle));
	}

	public void zombieMove() {
		if (!checkEntityCollisions(xMove, 0)) {
			zombieMoveX();
		} 

		if (!checkEntityCollisions(0, yMove)) {
			zombieMoveY();
		}
	}

	public void updateMovementAngle() {
		pivotX = x + hitbox.x + (hitbox.width / 2) - handler.getGameCamera().getXOffset();
		pivotY = y + hitbox.y + (hitbox.height / 2) - handler.getGameCamera().getYOffset();
		movementAngle = (float) Math.atan2(handler.getWorld().getPlayer().getPivotY() - pivotY, handler.getWorld().getPlayer().getPivotX() - pivotX);
	}

	public void zombieMoveX() {		
		if (xMove > 0) { // Moving right

			int tempX = (int) (x + xMove + hitbox.x + hitbox.width) / Tile.TILE_WIDTH;

			if (!collisionWithTileWalking(tempX, (int) (y + hitbox.y) / Tile.TILE_HEIGHT) 
					&& !collisionWithTileWalking(tempX, (int) (y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = (float) (tempX * Tile.TILE_WIDTH - hitbox.x - hitbox.width - 1);
			}

		} else if (xMove < 0) { // Moving left
			int tempX = (int) (x + xMove + hitbox.x) / Tile.TILE_WIDTH;

			if (!collisionWithTileWalking(tempX, (int) (y + hitbox.y) / Tile.TILE_HEIGHT) 
					&& !collisionWithTileWalking(tempX, (int) (y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = (float) (tempX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - hitbox.x);
			}
		}
	}

	public void zombieMoveY() {		
		if (yMove < 0) { // Moving up
			int tempY = (int) (y + yMove + hitbox.y) / Tile.TILE_HEIGHT;

			if (!collisionWithTileWalking((int) (x + hitbox.x) / Tile.TILE_WIDTH, tempY)
					&& !collisionWithTileWalking((int) (x + hitbox.x + hitbox.width) / Tile.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
				y = (float) (tempY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - hitbox.y);
			}
		} else if (yMove > 0) { // Moving down
			int tempY = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT;

			if (!collisionWithTileWalking((int) (x + hitbox.x) / Tile.TILE_WIDTH, tempY)
					&& !collisionWithTileWalking((int) (x + hitbox.x + hitbox.width) / Tile.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
				y = (float) (tempY * Tile.TILE_HEIGHT - hitbox.y - hitbox.height - 1);
			}
		}
	}

	public void hurtByKnife() {
		if (zombieHitbox.contains(knifePoint) && handler.getWeaponsManager().getCurrentWeapon().getName().equals("KA-BAR") 
				&& handler.getWeaponsManager().getCurrentMelee().getAttack().isAnimated() && 
				handler.getWeaponsManager().getCurrentMelee().getAttack().getIndex() == handler.getWeaponsManager().getCurrentMelee().getAttack().getNumFrames() - 1) {
			handler.getWeaponsManager().getCurrentMelee().getAttack().setAnimated(false); 
			hurt(50);
			Assets.bloodSplatterSFX.play();
		}
	}

	@Override
	public void die() {
		dropRandomItem();
	}

	public void dropRandomItem() {
		if (!capDrop) {
			int rand = (int) (Math.random() * 25);

			if (rand <= 1) {
				Medkit medkit = new Medkit(handler, pivotX + handler.getGameCamera().getXOffset(), pivotY + handler.getGameCamera().getYOffset());
				handler.getMasterWorldItemManager().getMedkitManager().addItem(medkit);
			} else if (rand <= 2) {
				Glock19Magazine glock19Magazine = new Glock19Magazine(handler, pivotX + handler.getGameCamera().getXOffset(), pivotY + handler.getGameCamera().getYOffset());
				handler.getMasterWorldItemManager().getGlock19MagazineManager().addItem(glock19Magazine);
			} else if (rand <= 3) {
				Remington870Shells remington870Shells = new Remington870Shells(handler, pivotX + handler.getGameCamera().getXOffset(), pivotY + handler.getGameCamera().getYOffset());
				handler.getMasterWorldItemManager().getRemington870ShellManager().addItem(remington870Shells);
			} else if (rand <= 4) {
				M16Magazine m16Magazine = new M16Magazine(handler, pivotX + handler.getGameCamera().getXOffset(), pivotY + handler.getGameCamera().getYOffset());
				handler.getMasterWorldItemManager().getM16MagazineManager().addItem(m16Magazine);
			} else {
				Coin coin = new Coin(handler, pivotX + handler.getGameCamera().getXOffset(), pivotY + handler.getGameCamera().getYOffset());
				handler.getMasterWorldItemManager().getCoinManager().addItem(coin);
			}
		}
		capDrop = true;
	}

	public float getPivotX() {
		return pivotX;
	}

	public float getPivotY() {
		return pivotY;
	}

	public Rectangle getHitbox() {
		return new Rectangle((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
	}
}
