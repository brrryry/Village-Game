package game.main.entity.mob;

import java.util.Random;

import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class TestMob extends Mob {
	
	private int walkspeed = 2;
	private int direction = 0;
	
	public TestMob(int x, int y, Sprite sprite, Map map) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.map = map;
		map.add(this);
	}
	
	int counter = 0;
	
	public void update() {
		Random newRand = new Random();
		counter = newRand.nextInt(100);
		//System.out.println("X: " + this.x + " Y: " + this.y);
		
		int xmove = 0;
		int ymove = 0;
		
		if(direction == 0) xmove++;
		if(direction == 1) ymove++;
		if(direction == 2) xmove--;
		if(direction == 3) ymove--;
		
		if(counter == 50) {
			this.direction = newRand.nextInt(4);
		}
		
		if(collision(xmove, ymove)) direction = (direction + 2) % 4;
		
		

		move(xmove, ymove);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}
