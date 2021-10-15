package game.main.entity;

import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class Entity {

	protected String name;
	protected int id;
	protected int x; //entity's absolute x position
	protected int y; //entity's absolute y position
	private boolean removed; //tells us if the entity has been removed from the level or not
	public Map map; //the map that the entity is on!
	public Sprite sprite; //the visual sprite of the entity
	
	public Entity(int x, int y, Sprite sprite) { //constructor
		this.x = x; 
		this.y = y;
		this.sprite = sprite;
		removed = false; //when you create an entity, it shouldn't be removed by default
	}
	
	public Entity() { //empty constructor for extension
		
	}
	
	public void setID(int i) {
		this.id = i;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMap(Map map) { //move the entity to another map?
		this.map = map;
	}
	
	public void remove() { //remove the entity
		removed = true;
	}
	
	public boolean removed() { //tells us if the entity is removed
		return removed;
	}
	
	public int getX() { //get the x position of the entity
		return x;
	}
	
	public int getY() { //get the y position of the entity
		return y;
	}
	
}
