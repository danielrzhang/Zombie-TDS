package entityMoving;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import entity.Entity;
import game.Handler;

public abstract class MovingEntity extends Entity {

	public static final int DEFAULT_PLAYER_HEALTH = 100;
	public static final float DEFAULT_PLAYER_SPEED = 4.0f;
	public static final float DEFAULT_PLAYER_WIDTH = 180, DEFAULT_PLAYER_HEIGHT = 110;

	public static final int DEFAULT_ZOMBIE_HEALTH = 100;
	public static final float DEFAULT_ZOMBIE_SPEED = 1.0f;
	public static final float DEFAULT_ZOMBIE_WIDTH = 180, DEFAULT_ZOMBIE_HEIGHT = 120;
	
	public static final float SMALL_BULLET_WIDTH = 12, SMALL_BULLET_HEIGHT = 5;
	public static final float MEDIUM_BULLET_WIDTH = 22, MEDIUM_BULLET_HEIGHT = 5;
	public static final float LARGE_BULLET_WIDTH = 30, LARGE_BULLET_HEIGHT = 5;

	protected float speed;
	protected float xMove, yMove, movementAngle;

	protected Graphics2D g2d;
	protected AffineTransform backup, transform;

	public MovingEntity(Handler handler, float x, float y, float width, float height, float movementAngle) {
		super(handler, x, y, width, height);
		this.movementAngle = movementAngle;
		xMove = 0;
		yMove = 0;
		movementAngle = 0;
	}	

	public boolean collisionWithTileWalking(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolidWalkable();
	}
	
	public boolean collisionWithTileShooting(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolidShootable();
	}

	public float getXMove() {
		return xMove;
	}

	public void setXMove(float xMove) {
		this.xMove = xMove;
	}

	public float getYMove() {
		return yMove;
	}

	public void setYMove(float yMove) {
		this.yMove = yMove;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getMovementAngle() {
		return movementAngle;
	}

	public void setMovementAngle(float movementAngle) {
		this.movementAngle = movementAngle;
	}
}
