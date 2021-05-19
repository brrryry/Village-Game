package game.main.graphics.sprite;

public class Sprite {

	public int[] pixels;
	public int size;
	private Spritesheet sheet;
	
	
	//Random Color Sprites
	public static Sprite dirt1 = new Sprite(0, 0, 16, Spritesheet.tiles1);
	
	public Sprite(int x, int y, int size, Spritesheet sheet) {
		pixels = new int[size * size];
		x *= 16; //get x position of sprite
		y *= 16; //get y position of sprite
		
		for(int yy = 0; yy < size; yy++) {
			for(int xx = 0; xx < size; xx++) {
				pixels[xx + yy * size] = sheet.pixels[(x + xx) + (y + yy) * sheet.getWidth()];
				//System.out.println(sheet.pixels[(x + xx) + (y + yy) * sheet.getWidth()]);
			}
		}
	}
	
	public Sprite(int x, int y, int size, int col) {
		pixels = new int[size * size];
		for(int yy = 0; yy < size; yy++) {
			for(int xx = 0; xx < size; xx++) {
				pixels[xx + yy * size] = col;
			}
		}
	}
	
	
	
	
}
