package game.main.entity.particle;

import game.main.entity.Entity;
import game.main.graphics.sprite.Sprite;

public class Particle extends Entity {

	public int[] pixels;
	
	public Particle(int x, int y, int col) {
		this.x = x;
		this.y = y;
		pixels = new int[1];
		
	}
	
	public Particle(int x, int y, Sprite sprite) {
		
	}
	
}
