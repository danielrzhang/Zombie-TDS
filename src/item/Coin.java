package item;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Animation;
import game.Assets;
import game.Handler;

public class Coin extends Item {

	private int value;
	protected Animation animation;

	public Coin(Handler handler, float x, float y) {
		super(handler, x, y);
		name = "Coin";
		texture = Assets.coin[7];
		animation = new Animation(100, Assets.coin, 0);
		value = 1;
		hitbox.x = 0;
		hitbox.y = 0;
		hitbox.width = COIN_WIDTH;
		hitbox.height = COIN_HEIGHT;
	}

	@Override
	public void tick() {
		setContinuousAnimation();
		animation.tick();
		collectItem();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(animation.getCurrentFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height, null);
//		g.setColor(Color.red);
//		g.drawRect((int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), COIN_WIDTH, COIN_HEIGHT);
	
	}
	
	public void setContinuousAnimation() {
		if (!animation.isAnimated()) {
			animation.setAnimated(true);
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Rectangle getHitbox() {
		return new Rectangle((int) (x + hitbox.x - handler.getGameCamera().getXOffset()), (int) (y + hitbox.y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}
