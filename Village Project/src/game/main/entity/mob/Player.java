package game.main.entity.mob;

import game.main.graphics.sprite.Sprite;
import game.main.input.Keyboard;
import game.main.map.Map;

public class Player extends Mob {

	private int walkspeed = 2;
	
	private String username;
	private Keyboard keyboard;
	
	private boolean moving = false;
	
	public Player(String name, int x, int y, Sprite sprite, Map map, Keyboard keyboard) {
		this.username = name;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.map = map;
		this.keyboard = keyboard;
	}

	public void update() {
		int xmove = 0;
		int ymove = 0;
		if(keyboard.up) ymove -= walkspeed;
		if(keyboard.down) ymove += walkspeed;
		if(keyboard.left) xmove -= walkspeed;
		if(keyboard.right) xmove += walkspeed;
		
		if(xmove != 0 || ymove != 0) {
			move(xmove, ymove);
			moving = true;
		} else moving = false;
		
		//System.out.println("X: " + this.x + " Y: " + this.y);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
}
