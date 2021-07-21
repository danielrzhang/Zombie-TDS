package bullets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import entityMoving.MovingEntity;
import game.Assets;
import game.Handler;

public class MediumBullet extends Bullet {

	public MediumBullet(Handler handler, float x, float y, float width, float height, float movementAngle, float damage, float speed) {
		super(handler, x, y, width, height, movementAngle, damage, speed);
		this.speed = speed;
		
		hitbox.x = 0;
		hitbox.y = 0;
		hitbox.width = (int) MovingEntity.MEDIUM_BULLET_WIDTH;
		hitbox.height = (int) MovingEntity.MEDIUM_BULLET_HEIGHT;		
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		AffineTransform backup = g2d.getTransform();
		AffineTransform transform = new AffineTransform();		

		transform.rotate(movementAngle, x - handler.getGameCamera().getXOffset(), y - handler.getGameCamera().getYOffset());

		g2d.transform(transform);
		g2d.drawImage(Assets.largeBullet, (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), 
				(int) width, (int) height, null);	
//		g2d.setColor(Color.RED);
//		g2d.drawRect((int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), (int) hitbox.getWidth(), (int) hitbox.getHeight());
		g2d.setTransform(backup);
	}

	@Override
	public void die() {
		
	}

	@Override
	public Rectangle getHitbox() {
		return new Rectangle((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
	}
}
