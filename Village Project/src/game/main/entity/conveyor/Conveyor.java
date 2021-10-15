package game.main.entity.conveyor;

import game.main.entity.Entity;
import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

/**
 * The Conveyor class serves as a template for conveyors.
 * The reason we used a "template" instead of a single constructor for every conveyor is because of situations in the future with different conveyor functions.
 * @author bryan.chan
 */

public class Conveyor extends Entity {
	
	//Conveyor Properties
	protected Map map;
	protected String name;
	protected int id;
	protected int speed;
	protected int direction;
	protected int xLength;
	protected int yLength;
	protected Sprite sprite;
	
	//Constructor
	public Conveyor(int x, int y, int direction, Map map) {
		this.x = x;  
		this.y = y;
		this.direction = direction;
		this.map = map;
	}
	
	/**
	 * This method rotates a conveyor.
	 * @param Nothing
	 * @return Nothing
	 */
	public Sprite rotate() {
		Sprite s = sprite.rotateConveyor(this);
		return s;
	}
	
	/**
	 * Getters and setters of properties
	 */
	public int getDirection() {
		return direction;
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setDirection(int d) {
		this.direction = d;
	}

	
	public void setXLength(int x) {
		this.xLength = x;
	}
	
	public void setYLength(int y) {
		this.yLength = y;
	}
	
	public int getXLength() {
		return xLength;
	}
	
	public int getYLength() {
		return yLength;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	
	
	
	
}
