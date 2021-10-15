package game.main.entity.ore;

import game.main.entity.Entity;
import game.main.entity.conveyor.Conveyor;
import game.main.map.Map;

public class Ore extends Entity {
	
	protected String name;
	protected int price, size, color, weight, upgrades, xVel, yVel;
	
	public Ore(int x, int y, Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.upgrades = 0;
	}
	
	public void move(Conveyor c) {
		
		int xmove = 0;
		int ymove = 0;
		
		switch(c.getDirection()) {
			case 0:
				ymove -= c.getSpeed(); //-4
				if(this.yVel == 0) this.yVel += ymove; //-4
				break;
			case 1:
				xmove += c.getSpeed();
				if(this.xVel == 0) this.xVel += xmove;
				break;
			case 2:
				ymove += c.getSpeed();
				if(this.yVel == 0) this.yVel += ymove;
				break;
			case 3:
				xmove -= c.getSpeed();
				if(this.xVel == 0) this.xVel += xmove;
				break;
		}
		
		//System.out.println(ymove);
		//System.out.println("YVEL " + this.yVel);
		
		if(xmove == 0 && this.xVel > 0) {
			this.xVel -= this.weight;
			if(this.xVel < 0) this.xVel = 0;
		}
		if(xmove == 0 && this.xVel < 0) {
			this.xVel += this.weight;
			if(this.xVel > 0) this.xVel = 0;
		}
		
		if(ymove == 0 && this.yVel > 0) {
			this.yVel -= this.weight;
			if(this.yVel < 0) this.yVel = 0;
		}
		if(ymove == 0 && this.yVel < 0) {
			this.yVel += this.weight;
			if(this.yVel > 0) this.yVel = 0;
		}
		
		
		this.x += this.xVel;
		this.y += this.yVel;
		
	}
	
	public void update() {
		Conveyor con = this.map.oreOnConveyor(this);
		if(con == null) this.remove();
		else this.move(con);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXVel(int x) {
		this.xVel = x;
	}
	
	public void setYVel(int y) {
		this.yVel = y;
	}
	
	public int getXVel() {
		return xVel;
	}
	
	public int getYVel() {
		return yVel;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void addUpgrades(int u) {
		this.upgrades += u;
	}
	
	public void setPrice(int p) {
		this.price = p;
	}
	
	public void setWeight(int w) {
		this.weight = w;
	}
	
	public void setSize(int s) {
		this.size = s;
	}
	
	public void addPrice(int p) {
		this.price += p;
	}
	
	public void multiplyPrice(double p) {
		this.price *= p;
	}
	
	public void multiplySize(double p) {
		this.size *= p;
	}
	
	public void setColor(int c) {
		this.color = c;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getColor() {
		return color;
	}
	
}
