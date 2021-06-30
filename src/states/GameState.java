package states;

import java.awt.Graphics;

import game.Handler;
import world.World;

public class GameState extends State {

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		stateName = "GameState";
	}
	
	@Override
	public void tick() {
		world.tick();
		
		if (handler.getWorld().getPlayer().getHealth() <= 0) {
			handler.getStateManager().setState(new DeathState(handler));
		}
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
