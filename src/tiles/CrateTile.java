package tiles;

import game.Assets;

public class CrateTile extends Tile {

	public CrateTile(int id) {
		super(Assets.crate, id);
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
