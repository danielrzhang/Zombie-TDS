package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entityMoving.MovingEntity;
import entityMoving.Player;
import entityMoving.Zombie;
import game.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		addEntity(new Zombie(handler, 600, 150, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		addEntity(new Zombie(handler, 800, 150, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		addEntity(new Zombie(handler, 1000, 250, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		addEntity(new Zombie(handler, 400, 150, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		addEntity(new Zombie(handler, 1000, 350, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 450, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 550, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 750, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 850, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 950, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1100, 950, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 950, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 150, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 250, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 350, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 450, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 550, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 750, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 850, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1050, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1150, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1250, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1350, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1450, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1550, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 1750, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 600, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 700, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 800, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 900, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1000, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1100, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1200, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1300, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1400, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1500, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1600, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
		// addEntity(new Zombie(handler, 1700, 650, MovingEntity.DEFAULT_ZOMBIE_WIDTH, MovingEntity.DEFAULT_ZOMBIE_HEIGHT, 0));
	}

	public void tick() {
		Iterator<Entity> iterator = entities.iterator();
		while (iterator.hasNext()){
			Entity e = iterator.next();
			e.tick();

			if (!e.isActive()) {
				iterator.remove();
			} 
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		player.postRender(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public Handler getHandler() {
		return handler;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
