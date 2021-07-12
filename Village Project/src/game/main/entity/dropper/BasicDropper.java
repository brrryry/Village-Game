package game.main.entity.dropper;

import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class BasicDropper extends Dropper {

	private int droprate = 60;
	
	public BasicDropper(int x, int y, int direction, Map map, Sprite sprite) {
		super(x, y, direction, map, sprite);
		this.setDroprate(droprate);
	}
	
	public int getOreID() {
		return 1;
	}

}
