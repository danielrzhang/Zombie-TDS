package bullets;

import entity.Entity;
import entity.MovingEntity;
import game.Handler;
import tiles.Tile;

public abstract class Bullet extends MovingEntity {

	protected float damage;
	
	public static float GLOCK19_BULLET_SPEED = 30f, REMINGTON870_BULLET_SPEED = 20f, M16_BULLET_SPEED = 50f;

	public Bullet(Handler handler, float x, float y, float width, float height, float movementAngle, float damage, float speed) {
		super(handler, x, y, width, height, movementAngle);
		entityName = "Bullet";
		health = 1;
		this.damage = damage;
	}

	public void tick() {
		bulletMove();
		getMovement();
		checkZombieCollision();
	}

	public void getMovement() {
		xMove = (float) (speed * Math.cos(movementAngle));
		yMove = (float) (speed * Math.sin(movementAngle));
	}	
	
	public void bulletMove() {
		bulletMoveX();
		bulletMoveY();
	}
	
	public void bulletMoveX() {
		if (xMove > 0) { // Moving right

			int tempX = (int) (x + xMove + hitbox.x + hitbox.width) / Tile.TILE_WIDTH;

			if (!collisionWithTileShooting(tempX, (int) (y + hitbox.y) / Tile.TILE_HEIGHT) 
					&& !collisionWithTileShooting(tempX, (int) (y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = (float) (tempX * Tile.TILE_WIDTH - hitbox.x - hitbox.width - 1);
				active = false;
			}

		} else if (xMove < 0) { // Moving left
			int tempX = (int) (x + xMove + hitbox.x) / Tile.TILE_WIDTH;

			if (!collisionWithTileShooting(tempX, (int) (y + hitbox.y) / Tile.TILE_HEIGHT) 
					&& !collisionWithTileShooting(tempX, (int) (y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = (float) (tempX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - hitbox.x);
				active = false;
			}
		}
	}

	public void bulletMoveY() {
		if (yMove < 0) { // Moving up
			int tempY = (int) (y + yMove + hitbox.y) / Tile.TILE_HEIGHT;

			if (!collisionWithTileShooting((int) (x + hitbox.x) / Tile.TILE_WIDTH, tempY)
					&& !collisionWithTileShooting((int) (x + hitbox.x + hitbox.width) / Tile.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
				y = (float) (tempY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - hitbox.y);
				active = false;
			}
		} else if (yMove > 0) { // Moving down
			int tempY = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT;

			if (!collisionWithTileShooting((int) (x + hitbox.x) / Tile.TILE_WIDTH, tempY)
					&& !collisionWithTileShooting((int) (x + hitbox.x + hitbox.width) / Tile.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
				y = (float) (tempY * Tile.TILE_HEIGHT - hitbox.y - hitbox.height - 1);
				active = false;
			}
		}
	}
	
	public void checkZombieCollision() {
		for (Entity e: handler.getWorld().getEntityManager().getEntities()) {	
			if (getHitbox().intersects(e.getHitbox()) && e.getEntityName().equals("Zombie")) {
				hurt(1);
				e.hurt(damage);
			}
		}
	}
	
	@Override
	public void die() {
		
	}

	public float getDamage() {
		return damage;
	}
}