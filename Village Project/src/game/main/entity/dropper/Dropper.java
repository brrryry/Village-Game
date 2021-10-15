package game.main.entity.dropper;

import java.util.Random;

import game.main.entity.Entity;
import game.main.entity.ore.BasicOre;
import game.main.entity.ore.Ore;
import game.main.graphics.sprite.Sprite;
import game.main.map.Map;

/**
 * The Dropper class serves as a template for droppers.
 * The reason we used a "template" instead of a single constructor for every dropper is because of situations in the future with different dropper functions.
 * @author bryan.chan
 */

public class Dropper extends Entity {
	
	protected Map map;
	protected int id;
	protected String name;
	protected int direction, xLength, yLength;
	protected Sprite sprite;
	protected Ore ore;
	protected int droprate;
	protected int timer = 0;
	
	public Dropper(int x, int y, int direction, Map map) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.map = map;
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}
	
	public void setXLength(int x) {
		this.xLength = x;
	}
	
	public void setYLength(int y) {
		this.yLength = y;
	}
	
	public int getXLength() {
		return xLength;
	}
	
	public int getYLength() {
		return yLength;
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
	
	public void setName(String s) {
		this.name = s;
	}
	
	
	public void update() {
		this.timer++;
		if(this.timer % this.getDroprate() == 0 && !this.map.getOreLimit()) {
			this.dropOre();
			this.timer = 0;
		}
	}
	
	
	
	public void dropOre() {
		int xshift = 8;
		int yshift = 8;
		
		if(direction == 0) yshift -= 16; //up
		if(direction == 1) xshift += 16; //right
		if(direction == 2) yshift += 16; //down
		if(direction == 3) xshift -= 16; //left
		
		switch(this.getID()) {
			case 0:
				this.map.add(new BasicOre(this.x + xshift, this.y + yshift, this.map));
				break;
			default:
				this.map.add(new BasicOre(this.x + xshift, this.y + yshift, this.map));
				break;
		}
		
	}
	
	
}
