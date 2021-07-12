package game.main.entity.conveyor;

import game.main.entity.Entity;

public class Conveyor extends Entity {

	protected int speed;
	
	public Conveyor(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean walled() {
		return false;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	
	
}
