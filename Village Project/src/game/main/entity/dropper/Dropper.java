package game.main.entity.dropper;

import java.util.Random;

import game.main.entity.Entity;
import game.main.entity.ore.BasicOre;
import game.main.entity.ore.Ore;
import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

public class Dropper extends Entity {
	
	public Random r = new Random();
	
	protected Map map;
	protected String name;
	protected int direction;
	protected Sprite sprite;
	protected Ore ore;
	protected int droprate;
	
	public Dropper(int x, int y, int direction, Map map, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.map = map;
		this.sprite = sprite;
	}
	
	public void setDroprate(int dr) {
		this.droprate = dr;
	}
	
	public int getDroprate() {
		return droprate;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getOreID() {
		return 0;
	}
	
	public void update() {
		
	}
	
	public void dropOre() {
		int xshift = 8;
		int yshift = 8;
		
		if(direction == 0) yshift -= 16; //up
		if(direction == 1) xshift += 16; //right
		if(direction == 2) yshift += 16; //down
		if(direction == 3) xshift -= 16; //left
		
		switch(this.getOreID()) {
			case 1:
				this.map.add(new BasicOre(this.x + xshift + r.nextInt(48), this.y + yshift));
				break;
			default:
				this.map.add(new BasicOre(this.x + xshift, this.y + yshift));
				break;
		}
		
	}
	
	
}
