package tiles;

import game.Assets;

public class MetalTile extends Tile {

	public MetalTile(int id) {
		super(Assets.metal, id);
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
