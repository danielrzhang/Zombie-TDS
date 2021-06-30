package item;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Assets;
import game.Handler;

public class Glock19Magazine extends Item {

	public Glock19Magazine(Handler handler, float x, float y) {
		super(handler, x, y);
		name = "Glock 19 Magazine";
		texture = Assets.glock19Magazine;
		hitbox.x = 0;
		hitbox.y = 0;
		hitbox.width = DEFAULT_ITEM_WIDTH;
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
