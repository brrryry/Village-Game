package game.main.tile;

import game.main.graphics.sprite.Sprite;

public class DebugTile extends Tile {

	public DebugTile(Sprite tileSprite) {
		super(tileSprite);
	}
	
	public boolean solid() {
		return true;
	}

}
