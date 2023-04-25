package tiles;

import game.Assets;

public class BrickTile extends Tile {

	public BrickTile(int id) {
		super(Assets.brick, id);
	}

	@Override
	public boolean isSolidWalkable() {
		return true;
	}
	
	@Override
	public boolean isSolidShootable() {
		return true;
	}
}
