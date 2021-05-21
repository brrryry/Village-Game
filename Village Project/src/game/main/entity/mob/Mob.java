package game.main.entity.mob;

import java.util.Objects;

import game.main.entity.Entity;
import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public abstract class Mob extends Entity {

	protected int walkspeed; //mob walkspeed
	protected int x, y; //mob coordinates (pixels)
	protected int health; //mob health
	
	protected enum Direction { //sense of direction of a mob
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction mobdir;
	
	public void move(int xmove, int ymove) {
		if(xmove != 0 && ymove != 0) {
			move(xmove, 0);
			move(0, ymove);
			return;
		}
		
		if(xmove > 0) mobdir = Direction.RIGHT;
		if(xmove < 0) mobdir = Direction.LEFT;
		if(ymove > 0) mobdir = Direction.UP;
		if(ymove < 0) mobdir = Direction.DOWN;
		
		if(collision(xmove, ymove)) return;
		if(!collision(xmove, ymove)) {
			this.x += xmove;
			this.y += ymove;
		}
		
		if(x < (this.sprite.getBound() * -1)) x = this.sprite.getBound() * -1 - 1;
		if(y < (this.sprite.getBound() * -1)) y = this.sprite.getBound() * -1 - 1;
		if(x + 16 - this.sprite.getBound() >= Map.MAPSIZE * 16) x = Map.MAPSIZE * 16 - 15 + this.sprite.getBound();
		if(y + 16 - this.sprite.getBound() >= Map.MAPSIZE * 16) y = Map.MAPSIZE * 16 - 15 + this.sprite.getBound();
	}
	
	public int abs(int value) {
		if(value < 0) return -1;
		return 1;
	}
	
	public boolean collision(int xmove, int ymove) {
		
		int leftbound = x + this.sprite.getBound();
		int rightbound = x + 16 - this.sprite.getBound();
		int upbound = y + this.sprite.getBound();
		int downbound = y + 16 - this.sprite.getBound();
		
		for(int pixelnum = this.sprite.getBound() + 1; pixelnum < 15 - this.sprite.getBound(); pixelnum++) {
			if(xmove < 0 && (leftbound / 16 < 0 || map.mapTiles[leftbound / 16][(y + pixelnum) / 16].solid())) return true;
			if(xmove > 0 && (rightbound / 16 >= Map.MAPSIZE || map.mapTiles[rightbound / 16][(y + pixelnum) / 16].solid())) return true;
			if(ymove < 0 && (upbound / 16 < 0 || map.mapTiles[(x + pixelnum ) / 16][upbound / 16].solid())) return true;
			if(ymove > 0 && (downbound / 16 >= Map.MAPSIZE || map.mapTiles[(x + pixelnum) / 16][downbound / 16].solid())) return true;
		}
		
		return false;
	}
	
	public abstract void update();
}
