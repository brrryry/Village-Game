package game.main.tile;

import game.main.graphics.sprite.Sprite;

public class Tile {

	public Sprite tileSprite;
	
	public static Tile dirtTile = new DirtTile(Sprite.dirt1);
	public static Tile debugTile = new DebugTile(Sprite.debug1);
	
	
	public Tile(Sprite tileSprite) {
		this.tileSprite = tileSprite;
	}
	
	public boolean solid() {
		return false;
	}
	
	
}
