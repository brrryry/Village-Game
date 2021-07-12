package game.main.entity.ore;

public class BasicOre extends Ore {
	
	private String name = "Basic Ore";
	private int price = 1; 
	private int weight = 1;
	private int size = 6;
	private int color = 0xff000000;
	
	public BasicOre(int x, int y) {
		super(x, y);
		this.setName(name);
		this.setPrice(price);
		this.setWeight(weight);
		this.setSize(size);
		this.setColor(color);
	}
	
}
