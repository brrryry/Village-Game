package game.main.entity;

import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class Entity {

	private int x, y;
	private boolean removed;
	public Map map;
	public Sprite sprite;
	
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		removed = false;
	}
	
	public Entity() {
		
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	private void remove() {
		removed = true;
	}
	
	private boolean removed() {
		return removed;
	}
	
}
