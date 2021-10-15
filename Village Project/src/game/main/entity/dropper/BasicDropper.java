package game.main.entity.dropper;

import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class BasicDropper extends Dropper {

	private String name = "Basic Dropper";
	private int id = 0;
	private int xLength = 16;
	private int yLength = 16;
	private int droprate = 60;
	private Sprite sprite = Sprite.debug1;
	
	public BasicDropper(int x, int y, int direction, Map map) {
		super(x, y, direction, map);
		this.setName(name);
		this.setID(id);
		this.setDroprate(droprate);
		this.setSprite(sprite);
		this.setXLength(xLength);
		this.setYLength(yLength);
	}
	
	public int getOreID() {
		return 1;
	}

}
