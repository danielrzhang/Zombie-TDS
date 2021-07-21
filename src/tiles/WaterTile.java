package tiles;

import game.Assets;

public class WaterTile extends Tile {

	public WaterTile(int id) {
		super(Assets.water, id);
	}

	@Override
	public boolean isSolidWalkable() {
		return true;
	}
	
	@Override
	public boolean isSolidShootable() {
		return false;
	}
}
