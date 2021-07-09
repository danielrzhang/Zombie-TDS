package item;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Assets;
import game.Handler;

public class Remington870Shells extends WorldItem {

	public Remington870Shells(Handler handler, float x, float y) {
		super(handler, x, y);
		name = "Remington 870 Shells";
		texture = Assets.shells;
		hitbox.x = 0;
		hitbox.y = 0;
		hitbox.width = DEFAULT_ITEM_WIDTH + 5;
		hitbox.height = DEFAULT_ITEM_HEIGHT;
	}

	@Override
	public void tick() {
		collectItem();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height, null);
//		g.setColor(Color.red);
//		g.drawRect((int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
	}

	@Override
	public Rectangle getHitbox() {
		return new Rectangle((int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), hitbox.width, hitbox.height);
	}
}
