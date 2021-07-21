package entityMoving;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import game.Handler;
import inventory.Inventory;
import tiles.Tile;

public class Player extends MovingEntity {
	
	private float shootingAngle, pivotX, pivotY, gunPointX, gunPointY, knifePointX, knifePointY;
	private Inventory inventory;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, MovingEntity.DEFAULT_PLAYER_WIDTH, MovingEntity.DEFAULT_PLAYER_HEIGHT, 0);
		
		speed = DEFAULT_PLAYER_SPEED;
		health = DEFAULT_PLAYER_HEALTH;
		
		hitbox.x = 20;
		hitbox.y = 15;
		hitbox.width = 80;
		hitbox.height = 80;
		
		shootingAngle = 0;
		
		entityName = "Player";
		inventory = new Inventory(handler);
		
		knifePointX = (float) (pivotX + (90 * Math.cos(shootingAngle)));
		knifePointY = (float) (pivotY + (90 * Math.sin(shootingAngle)));
	}

	public void tick() {
		inventory.tick();

		if (inventory.isActive()) {
			return;
		}
		getMovementInput();
		playerMove();
		handler.getGameCamera().centerOnEntity(this);
		updateShootingAngle();
		updateKnifePoint();
	}

	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		AffineTransform backup = g2d.getTransform();
		AffineTransform transform = new AffineTransform();		

		transform.rotate(shootingAngle, pivotX, pivotY);

		g2d.transform(transform);
		g2d.drawImage(handler.getWeaponsManager().getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), 
				(int) width, (int) height, null);

//		g2d.setColor(Color.WHITE);
//		g2d.fillOval((int) (pivotX) - 5, (int) (pivotY) - 5, 10, 10);	
//		g2d.setColor(Color.RED);
//		g2d.drawRect((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
		g2d.setTransform(backup);
		
//		g2d.setColor(Color.WHITE);
//		g2d.fillOval((int) knifePointX, (int) knifePointY, 5, 5);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	public void playerMove() {
		if (!checkEntityCollisions(xMove, 0)) {
			playerMoveX();
		}

		if (!checkEntityCollisions(0, yMove)) {
			playerMoveY();
		}
	}

	public void playerMoveX() {
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

	public void playerMoveY() {
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

	private void getMovementInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().isUp() && !handler.getKeyManager().isDown() && !handler.getKeyManager().isLeft() && !handler.getKeyManager().isRight()) {
			movementAngle = 270;
		} else if (!handler.getKeyManager().isUp() && handler.getKeyManager().isDown() && !handler.getKeyManager().isLeft() && !handler.getKeyManager().isRight()) {
			movementAngle = 90;
		} else if (!handler.getKeyManager().isUp() && !handler.getKeyManager().isDown() && handler.getKeyManager().isLeft() && !handler.getKeyManager().isRight()) {
			movementAngle = 180;
		} else if (!handler.getKeyManager().isUp() && !handler.getKeyManager().isDown() && !handler.getKeyManager().isLeft() && handler.getKeyManager().isRight()) {
			movementAngle = 0;
		} else if (handler.getKeyManager().isUp() && !handler.getKeyManager().isDown() && !handler.getKeyManager().isLeft() && handler.getKeyManager().isRight()) {
			movementAngle = 315;
		} else if (handler.getKeyManager().isUp() && !handler.getKeyManager().isDown() && handler.getKeyManager().isLeft() && !handler.getKeyManager().isRight()) {
			movementAngle = 225;
		} else if (!handler.getKeyManager().isUp() && handler.getKeyManager().isDown() && !handler.getKeyManager().isLeft() && handler.getKeyManager().isRight()) {
			movementAngle = 45;
		} else if (!handler.getKeyManager().isUp() && handler.getKeyManager().isDown() && handler.getKeyManager().isLeft() && !handler.getKeyManager().isRight()) {
			movementAngle = 135;
		} else if (handler.getKeyManager().isUp() && !handler.getKeyManager().isDown() && handler.getKeyManager().isLeft() && handler.getKeyManager().isRight()) {
			movementAngle = 270;
		} else if (handler.getKeyManager().isUp() && handler.getKeyManager().isDown() && !handler.getKeyManager().isLeft() && handler.getKeyManager().isRight()) {
			movementAngle = 0;
		} else if (!handler.getKeyManager().isUp() && handler.getKeyManager().isDown() && handler.getKeyManager().isLeft() && handler.getKeyManager().isRight()) {
			movementAngle = 90;
		} else if (handler.getKeyManager().isUp() && handler.getKeyManager().isDown() && handler.getKeyManager().isLeft() && !handler.getKeyManager().isRight()) {
			movementAngle = 180;
		} else {
			return;
		}
		xMove = (float) (handler.getWeaponsManager().getCurrentGun().getSpeed() * Math.cos(Math.toRadians(movementAngle)));
		yMove = (float) (handler.getWeaponsManager().getCurrentGun().getSpeed() * Math.sin(Math.toRadians(movementAngle)));
	}

	public void updateShootingAngle() {
		gunPointX = (float) (x + (width / 3) + handler.getWeaponsManager().getCurrentGun().getCurrentGunLength() * Math.cos(shootingAngle + Math.toRadians(handler.getWeaponsManager().getCurrentGun().getCurrentGunAngleAddition())));
		gunPointY = (float) (y + (height / 2) + handler.getWeaponsManager().getCurrentGun().getCurrentGunLength() * Math.sin(shootingAngle + Math.toRadians(handler.getWeaponsManager().getCurrentGun().getCurrentGunAngleAddition())));
		pivotX = x + (width / 3) - handler.getGameCamera().getXOffset();
		pivotY = y + (height / 2) - handler.getGameCamera().getYOffset();
		shootingAngle = (float) Math.atan2(handler.getMouseManager().getMouseY() - pivotY, handler.getMouseManager().getMouseX() - pivotX);
	}
	
	public void updateKnifePoint() {
		knifePointX = (float) (pivotX + (120 * Math.cos(shootingAngle)));
		knifePointY = (float) (pivotY + (120 * Math.sin(shootingAngle)));
	}

	public void die() {
		
	}
	
	public float getShootingAngle() {
		return shootingAngle;
	}
	
	public float getPivotX() {
		return pivotX;
	}
	
	public float getPivotY() {
		return pivotY;
	}
	
	public float getGunPointX() {
		return gunPointX;
	}
	
	public float getGunPointY() {
		return gunPointY;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public float getKnifePointX() {
		return knifePointX;
	}
	
	public float getKnifePointY() {
		return knifePointY;
	}
}
