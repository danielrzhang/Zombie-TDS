package entityStatic;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Assets;
import game.Handler;

public class LandmineEntity extends StaticEntity {

	public LandmineEntity(Handler handler, float x, float y, float width, float height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.landmine, (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), (int) width, (int) height, null);
	}
	
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
