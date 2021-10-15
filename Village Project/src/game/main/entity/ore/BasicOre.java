package game.main.entity.ore;

import game.main.map.Map;

public class BasicOre extends Ore {
	
	private String name = "Basic Ore";
	private int price = 1; 
	private int weight = 1;
	private int size = 4;
	private int color = 0xff000000;
	
	public BasicOre(int x, int y, Map map) {
		super(x, y, map);
		this.setName(name);
		this.setPrice(price);
		this.setWeight(weight);
		this.setSize(size);
		this.setColor(color);
	}
	
}
