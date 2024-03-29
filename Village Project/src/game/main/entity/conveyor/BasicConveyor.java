package game.main.entity.conveyor;

import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class BasicConveyor extends Conveyor {

	private String name = "Basic Conveyor";
	private int id = 10000;
	
	private int speed = 2;
	private int xLength = 16;
	private int yLength = 16;
	public Sprite sprite = Sprite.basicConveyor;
	
	public BasicConveyor(int x, int y, int direction, Map map) {
		super(x, y, direction, map);
		this.setSpeed(speed);
		this.setID(id);
		this.setSprite(sprite);
		this.setXLength(xLength);
		this.setYLength(yLength);
		this.setName(name);
		for(int i = 0; i < this.direction; i++) this.setSprite(this.rotate());
	}
	
}
