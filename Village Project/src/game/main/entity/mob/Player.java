package game.main.entity.mob;

import game.main.Game;
import game.main.entity.Inventory;
import game.main.entity.conveyor.Conveyor;
import game.main.entity.conveyor.LongConveyor;
import game.main.entity.dropper.BasicDropper;
import game.main.entity.dropper.Dropper;
import game.main.graphics.sprite.Sprite;
import game.main.input.Keyboard;
import game.main.input.Mouse;
import game.main.map.Map;

public class Player extends Mob {

	private int walkspeed = 2;
	
	private String username;
	private Keyboard keyboard;
	private Mouse mouse;
	private Inventory inventory;
	
	private boolean moving = false;
	
	public Player(String name, int x, int y, Sprite sprite, Map map, Keyboard keyboard, Mouse mouse, Inventory i) {
		this.username = name;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.map = map;
		this.keyboard = keyboard;
		this.mouse = mouse;
		this.inventory = i;
		map.add(this);
	}

	static int direction = 0;
	static boolean placed = false;
	
	public void update() {
		int xmove = 0;
		int ymove = 0;
		if(keyboard.up) ymove -= walkspeed;
		if(keyboard.down) ymove += walkspeed;
		if(keyboard.left) xmove -= walkspeed;
		if(keyboard.right) xmove += walkspeed;

		if(keyboard.r && !placed) {
			System.out.println(direction);
			placed = true;
			direction++;
			if(direction > 3) direction = 0;
		} else if(!keyboard.r) placed = false;
		
		if(xmove != 0 || ymove != 0) {
			move(xmove, ymove);
			moving = true;
		} else moving = false;
		
		if(mouse.mb != -1) {
			
			int tilex = (this.x * Game.SCALE + this.mouse.x - (Game.WIDTH * Game.SCALE / 2)) / (16 * Game.SCALE);
			int tiley = (this.y * Game.SCALE + this.mouse.y - (Game.HEIGHT * Game.SCALE / 2)) / (16 * Game.SCALE);
			
			
			//System.out.println(tilex + ", " + tiley);

			//placeTile(Tile.debugTile, tilex, tiley);
			
			Conveyor c = new LongConveyor(tilex * 16, tiley * 16, direction, this.map);
			Dropper d = new BasicDropper(tilex * 16, tiley * 16, direction, this.map);
			
			if(!this.map.blockExists(tilex * 16, tiley * 16, c) && inMap(tilex, tiley) && mouse.mb == 1 && inventory.itemExists(c)) {
				placeConveyor(c);
				inventory.remove(c);
			}
			
			if(!this.map.blockExists(tilex * 16, tiley * 16, d) && inMap(tilex, tiley) && mouse.mb != 1 && inventory.itemExists(d)) {
				placeDropper(d);
				inventory.remove(d);
			}
		}
		
		//System.out.println("X: " + this.x + " Y: " + this.y);
	}
	
	public boolean inMap(int tilex, int tiley) {
		if(tilex >= 0 && tilex < this.map.mapTiles.length && tiley >= 0 && tiley < this.map.mapTiles.length) return true;
		return false;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getCenterX() {
		return this.x + (this.sprite.xsize / 2);
	}
	
	public int getCenterY() {
		return this.y + (this.sprite.ysize / 2);
	}
	
	
}
