package game.main.entity;

public class Entity {

	private int x, y;
	private boolean removed;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		removed = false;
	}
	
	private void remove() {
		removed = true;
	}
	
	private boolean removed() {
		return removed;
	}
	
}
