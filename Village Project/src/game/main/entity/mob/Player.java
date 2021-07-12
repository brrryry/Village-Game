package game.main.entity.mob;

import game.main.Game;
import game.main.entity.dropper.BasicDropper;
import game.main.graphics.sprite.Sprite;
import game.main.input.Keyboard;
import game.main.input.Mouse;
import game.main.map.Map;
import game.main.tile.Tile;

public class Player extends Mob {

	private int walkspeed = 2;
	
	private String username;
	private Keyboard keyboard;
	private Mouse mouse;
	
	private boolean moving = false;
	
	public Player(String name, int x, int y, Sprite sprite, Map map, Keyboard keyboard, Mouse mouse) {
		this.username = name;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.map = map;
		this.keyboard = keyboard;
		this.mouse = mouse;
		map.add(this);
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
		
		if(mouse.mb != -1) {
			
			int tilex = (getCenterX() * Game.SCALE + this.mouse.x - (Game.WIDTH * Game.SCALE / 2)) / (16 * Game.SCALE);
			int tiley = (getCenterY() * Game.SCALE + this.mouse.y - (Game.HEIGHT * Game.SCALE / 2)) / (16 * Game.SCALE);
			
			//System.out.println(tilex + ", " + tiley);

			//placeTile(Tile.debugTile, tilex, tiley);
			if(!this.map.dropperOrConveyorExists(tilex * 16, tiley * 16)) placeDropper(new BasicDropper(tilex * 16, tiley * 16, 1, this.map, Sprite.debug1));
		}
		
		//System.out.println("X: " + this.x + " Y: " + this.y);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getCenterX() {
		return this.x + (this.sprite.size / 2);
	}
	
	public int getCenterY() {
		return this.y + (this.sprite.size / 2);
	}
	
	
}
