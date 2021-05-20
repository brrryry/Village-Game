package game.main.entity.mob;

import game.main.entity.Entity;
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
		
		if(!collision(xmove, ymove)) {
			this.x += xmove;
			this.y += ymove;
		}
		
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x > (Map.MAPSIZE - 1) * 16) x = (Map.MAPSIZE - 1) * 16;
		if(y > (Map.MAPSIZE - 1) * 16) y = (Map.MAPSIZE - 1) * 16;
	}
	
	public int abs(int value) {
		if(value < 0) return -1;
		return 1;
	}
	
	public boolean collision(int xmove, int ymove) {
		
		int leftbound = x + 4;
		int rightbound = x + 13;
		int upbound = y + 4;
		int downbound = y + 13;
		
		if(xmove < 0 && (map.mapTiles[leftbound / 16][y / 16].solid() || map.mapTiles[leftbound / 16][upbound / 16].solid() || map.mapTiles[leftbound / 16][downbound / 16].solid())) return true;
		if(xmove > 0 && (map.mapTiles[rightbound / 16][y / 16].solid() || map.mapTiles[rightbound / 16][upbound / 16].solid() || map.mapTiles[rightbound / 16][downbound / 16].solid())) return true;
		if(ymove < 0 && (map.mapTiles[x / 16][upbound / 16].solid() || map.mapTiles[leftbound / 16][upbound / 16].solid() || map.mapTiles[rightbound / 16][upbound / 16].solid())) return true;
		if(ymove > 0 && (map.mapTiles[x / 16][downbound / 16].solid() || map.mapTiles[leftbound / 16][downbound / 16].solid() || map.mapTiles[rightbound / 16][downbound / 16].solid())) return true;
		
		return false;
	}
	
	public abstract void update();
}
