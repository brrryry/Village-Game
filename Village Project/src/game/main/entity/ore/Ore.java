package game.main.entity.ore;

import game.main.entity.Entity;

public class Ore extends Entity {
	
	protected String name;
	protected int price, size, color, weight;
	
	public Ore(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
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
